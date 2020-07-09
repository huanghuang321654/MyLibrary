/*
 * Created by JFormDesigner on Sun Jul 05 19:06:34 CST 2020
 */

package com.library.ui;

import java.awt.event.*;

import com.library.entity.BookInfor;
import com.library.entity.Borrow;
import com.library.entity.Reader;
import com.library.service.BookInforService;
import com.library.service.BookTypeService;
import com.library.service.BorrowService;
import com.library.service.ReaderService;
import com.library.service.impl.BookInforServiceImpl;
import com.library.service.impl.BookTypeServiceImpl;
import com.library.service.impl.BorrowServiceImpl;
import com.library.service.impl.ReaderServiceImpl;

import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.*;
import javax.swing.table.*;

/**
 * @author 4
 */
public class BookReturnFrame extends JFrame {
    BookInforService bookInforService = new BookInforServiceImpl();
    BorrowService borrowService = new BorrowServiceImpl();
    ReaderService readerService = new ReaderServiceImpl();
    BookTypeService bookTypeService = new BookTypeServiceImpl();

    public BookReturnFrame() {
        initComponents();
    }

    private void queryButtonActionPerformed(ActionEvent e) {
        // 查询按钮
        if (querycomboBox.getSelectedItem().equals("通过图书编号查询")) {
            if (!querytextField.getText().trim().isEmpty()) {
                showListData(querytextField.getText().trim());
            } else {
                JOptionPane.showMessageDialog(null, "请输入图书编号！");
            }
        } else {
            showListData();
        }
    }

    private void cancelButtonActionPerformed(ActionEvent e) {
        // 取消按钮
        this.dispose();
    }

    private void returnButtonActionPerformed(ActionEvent e) {
        // 图书归还按钮
        Borrow borrow = getRowData();
        Double borrowFk = null;
        if (borrow != null) {
            //归还图书如果超过归还日期会产生罚款
            String fk = bookTypeService.queryByBookTypeId(bookInforService.queryByISBN(borrow.getBookISBN()).get(0).getTypeId()).get(0).getFk().toString();
            if (borrow.getIsBack() == 1) {
                //如果是否归还项为1则表示已经归还
                JOptionPane.showMessageDialog(null, "此书已经归还，无需再次操作！");
            } else {
                //产生罚款
                if (System.currentTimeMillis() - borrow.getBackDate().getTime() > 0) {
                    Long tempTime = System.currentTimeMillis() - borrow.getBackDate().getTime();
                    int day1 = (int) (tempTime / 1000);
                    int day2 = day1 / (3600 * 24);
                    Double fk1 = Double.parseDouble(fk);
                    borrowFk = fk1 * day2;
                    borrow.setFk(borrowFk);
                } else {
                    borrow.setFk(0.0);
                }
                borrow.setIsBack(1);
                borrowService.update(borrow);
                BookInfor tempBookInfor = bookInforService.queryByISBN(borrow.getBookISBN()).get(0);
                Reader tempReader = readerService.queryByReaderISBN(borrow.getReaderISBN()).get(0);
                tempReader.setMaxNum(tempReader.getMaxNum() + 1);
                readerService.update(tempReader);
                bookInforService.update(new BookInfor(tempBookInfor.getISBN(), tempBookInfor.getTypeId(), tempBookInfor.getBookName(), tempBookInfor.getWriter(), tempBookInfor.getTranslator(), tempBookInfor.getPublisher(), tempBookInfor.getDate(), tempBookInfor.getPrice(), tempBookInfor.getQuantity() + 1));
                JOptionPane.showMessageDialog(null, "图书归还成功！");
            }
        } else {
            // 如果没有选中任何行，提示用户
            JOptionPane.showMessageDialog(null, "请选中需要归还的图书借阅信息");
            return;
        }
        // 当更新成功后，需要对表数据进行再次加载，实现刷新效果
        showListData();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        titlelabel = new JLabel();
        scrollPane1 = new JScrollPane();
        returnTable = new JTable();
        returnButton = new JButton();
        cancelButton = new JButton();
        queryButton = new JButton();
        querytextField = new JTextField();
        querycomboBox = new JComboBox<>();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- titlelabel ----
        titlelabel.setText("\u56fe\u4e66\u5f52\u8fd8");
        titlelabel.setHorizontalAlignment(SwingConstants.CENTER);
        titlelabel.setFont(titlelabel.getFont().deriveFont(titlelabel.getFont().getSize() + 8f));
        contentPane.add(titlelabel);
        titlelabel.setBounds(300, 10, 130, 30);

        //======== scrollPane1 ========
        {

            //---- returnTable ----
            returnTable.setModel(new DefaultTableModel(
                    new Object[][]{
                            {null, null, null, null, null, null, null, null},
                            {null, null, null, null, null, null, null, null},
                    },
                    new String[]{
                            null, null, null, null, null, null, null, null
                    }
            ));
            scrollPane1.setViewportView(returnTable);
        }
        contentPane.add(scrollPane1);
        scrollPane1.setBounds(5, 45, 710, 250);

        //---- returnButton ----
        returnButton.setText("\u5f52\u8fd8");
        returnButton.setFont(returnButton.getFont().deriveFont(returnButton.getFont().getSize() + 4f));
        returnButton.addActionListener(e -> returnButtonActionPerformed(e));
        contentPane.add(returnButton);
        returnButton.setBounds(470, 310, 105, 40);

        //---- cancelButton ----
        cancelButton.setText("\u53d6\u6d88");
        cancelButton.setFont(cancelButton.getFont().deriveFont(cancelButton.getFont().getSize() + 4f));
        cancelButton.addActionListener(e -> cancelButtonActionPerformed(e));
        contentPane.add(cancelButton);
        cancelButton.setBounds(585, 310, 105, 40);

        //---- queryButton ----
        queryButton.setText("\u67e5\u8be2");
        queryButton.setFont(queryButton.getFont().deriveFont(queryButton.getFont().getSize() + 4f));
        queryButton.addActionListener(e -> queryButtonActionPerformed(e));
        contentPane.add(queryButton);
        queryButton.setBounds(355, 310, 105, 40);
        contentPane.add(querytextField);
        querytextField.setBounds(180, 320, 140, 30);

        //---- querycomboBox ----
        querycomboBox.setModel(new DefaultComboBoxModel<>(new String[]{
                "\u67e5\u8be2\u6240\u6709\u501f\u9605\u8bb0\u5f55",
                "\u901a\u8fc7\u56fe\u4e66\u7f16\u53f7\u67e5\u8be2"
        }));
        contentPane.add(querycomboBox);
        querycomboBox.setBounds(15, 305, 125, 50);

        contentPane.setPreferredSize(new Dimension(725, 405));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
        showListData();
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel titlelabel;
    private JScrollPane scrollPane1;
    private JTable returnTable;
    private JButton returnButton;
    private JButton cancelButton;
    private JButton queryButton;
    private JTextField querytextField;
    private JComboBox<String> querycomboBox;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    public void showListData() {
        //刷新展示界面
        List<Borrow> borrows = borrowService.queryAll();
        String[][] datas = new String[borrows.size()][8];
        for (int i = 0; i < datas.length; i++) {
            datas[i][0] = borrows.get(i).getId().toString();
            datas[i][1] = borrows.get(i).getBookISBN();
            datas[i][2] = borrows.get(i).getOperatorId().toString();
            datas[i][3] = borrows.get(i).getReaderISBN();
            datas[i][4] = borrows.get(i).getIsBack().toString();
            datas[i][5] = borrows.get(i).getBorrowDate().toString();
            datas[i][6] = borrows.get(i).getBackDate().toString();
            datas[i][7] = borrows.get(i).getFk().toString();
        }
        returnTable.setModel(new DefaultTableModel(
                datas, new String[]{"借阅编号", "图书编号", "操作员ID", "读者编号", "是否归还", "借阅日期", "归还日期", "罚款金额"}
        ));
        scrollPane1.setViewportView(returnTable);
    }

    public void showListData(String bookISBN) {
        //通过图书编号进行查询
        List<Borrow> borrows = borrowService.queryByBookISBN(bookISBN);
        String[][] datas = new String[borrows.size()][8];
        for (int i = 0; i < datas.length; i++) {
            datas[i][0] = borrows.get(i).getId().toString();
            datas[i][1] = borrows.get(i).getBookISBN();
            datas[i][2] = borrows.get(i).getOperatorId().toString();
            datas[i][3] = borrows.get(i).getReaderISBN();
            datas[i][4] = borrows.get(i).getIsBack().toString();
            datas[i][5] = borrows.get(i).getBorrowDate().toString();
            datas[i][6] = borrows.get(i).getBackDate().toString();
            datas[i][7] = borrows.get(i).getFk().toString();
        }
        returnTable.setModel(new DefaultTableModel(
                datas, new String[]{"借阅编号", "图书编号", "操作员ID", "读者编号", "是否归还", "借阅日期", "归还日期", "罚款金额"}
        ));
        scrollPane1.setViewportView(returnTable);
    }

    public void showListDataByBookName(String bookName) {
        //通过图书名字进行查询
        List<Borrow> borrows = borrowService.queryByBookISBN(bookName);
        String[][] datas = new String[borrows.size()][8];
        for (int i = 0; i < datas.length; i++) {
            datas[i][0] = borrows.get(i).getId().toString();
            datas[i][1] = borrows.get(i).getBookISBN();
            datas[i][2] = borrows.get(i).getOperatorId().toString();
            datas[i][3] = borrows.get(i).getReaderISBN();
            datas[i][4] = borrows.get(i).getIsBack().toString();
            datas[i][5] = borrows.get(i).getBorrowDate().toString();
            datas[i][6] = borrows.get(i).getBackDate().toString();
            datas[i][7] = borrows.get(i).getFk().toString();
        }
        returnTable.setModel(new DefaultTableModel(
                datas, new String[]{"借阅编号", "图书编号", "操作员ID", "读者编号", "是否归还", "借阅日期", "归还日期", "罚款金额"}
        ));
        scrollPane1.setViewportView(returnTable);
    }

    public Borrow getRowData() {
        //获取光标对象
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Borrow borrow = null;
        int index = returnTable.getSelectedRow();
        // 如果下标不为-1，则选中行为数据行
        if (index != -1) {
            // 取得表格对象的数据模型
            TableModel model = returnTable.getModel();
            // 在表格对象模型中，根据选中的行和列，获取相应的数据值
            Integer id = Integer.parseInt(model.getValueAt(index, 0).toString());
            String bookISBN = model.getValueAt(index, 1).toString();
            Integer operatorId = Integer.parseInt(model.getValueAt(index, 2).toString());
            String readerISBN = model.getValueAt(index, 3).toString();
            Integer isBack = Integer.parseInt(model.getValueAt(index, 4).toString());
            Date borrowDate = null;
            try {
                borrowDate = sdf.parse(model.getValueAt(index, 5).toString());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Date returnDate = null;
            try {
                returnDate = sdf.parse(model.getValueAt(index, 6).toString());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Double fk = Double.parseDouble(model.getValueAt(index, 7).toString());

            borrow = new Borrow();
            borrow.setId(id);
            borrow.setBookISBN(bookISBN);
            borrow.setOperatorId(operatorId);
            borrow.setReaderISBN(readerISBN);
            borrow.setIsBack(isBack);
            borrow.setBorrowDate(new java.sql.Date(borrowDate.getTime()));
            borrow.setBackDate(new java.sql.Date(returnDate.getTime()));
            borrow.setFk(fk);
        }
        // 如果没有选中任何一行 就意味着bookType为null 可以提供给外部判断
        return borrow;
    }

    public static void main(String[] args) {
        new BookReturnFrame().setVisible(true);
    }
}
