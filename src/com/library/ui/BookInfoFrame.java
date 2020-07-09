/*
 * Created by JFormDesigner on Thu Jul 02 18:59:58 CST 2020
 */

package com.library.ui;

import java.awt.event.*;

import com.library.entity.BookInfor;
import com.library.entity.BookType;
import com.library.entity.Reader;
import com.library.service.BookInforService;
import com.library.service.BookTypeService;
import com.library.service.impl.BookInforServiceImpl;
import com.library.service.impl.BookTypeServiceImpl;

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
public class BookInfoFrame extends JFrame {
    //调用图书信息和图书类别服务层
    public static BookInforService bookInforService = new BookInforServiceImpl();
    public static BookTypeService bookTypeService = new BookTypeServiceImpl();

    public BookInfoFrame() {
        initComponents();
    }

    private void insertButtonActionPerformed(ActionEvent e) {
        // 添加图书信息按钮
        new InsertBookInfoFrame().setVisible(true);
        this.setVisible(false);
    }

    private void deleteButtonActionPerformed(ActionEvent e) {
        // 删除图书信息按钮，使用光标进行删除
        BookInfor bookInfor = getRowData();
        if (bookInfor != null) {
            if (bookInforService.delete(bookInfor.getISBN()) >= 1) {
                JOptionPane.showMessageDialog(null, "编号为" + bookInfor.getISBN() + "的图书信息删除成功");
            } else {
                JOptionPane.showMessageDialog(null, "编号为" + bookInfor.getISBN() + "的图书信息删除失败");
            }
        } else {
            // 如果没有选中任何行，提示用户
            JOptionPane.showMessageDialog(null, "请选中需要删除的图书信息");
            return;
        }
        // 当删除成功后，需要对表数据进行再次加载，实现刷新效果
        showListData();
    }

    private void updateButtonActionPerformed(ActionEvent e) {
        // 修改图书信息按钮
        Date date = new java.sql.Date(System.currentTimeMillis());
        BookInfor bookInfor = getRowData();
        if (bookInfor != null) {
            if (bookInforBan(bookInfor)) { //调用一个判断格式的方法来判断更改的信息是否符合规范，不规范则会返回false
                if (bookInforService.update(bookInfor) >= 1) {
                    JOptionPane.showMessageDialog(null, "编号为" + bookInfor.getISBN() + "的图书信息更新成功");
                } else {
                    JOptionPane.showMessageDialog(null, "编号为" + bookInfor.getISBN() + "的图书信息更新失败");
                }
            } else {
                JOptionPane.showMessageDialog(null, "修改失败，请检查是否输入符合规范信息");
            }
        } else {
            // 如果没有选中任何行，提示用户
            JOptionPane.showMessageDialog(null, "请选中需要更新的图书信息");
            return;
        }
        // 当更新成功后，需要对表数据进行再次加载，实现刷新效果
        showListData();
    }

    private void queryButtonActionPerformed(ActionEvent e) {
        // 查询按钮，判断用户选择哪种查询方式，分别调用不同的查询方法
        if (querycomboBox.getSelectedItem().equals("通过书名查询")) {
            if (!querytextField.getText().trim().isEmpty()) {
                showListDataByBookName(querytextField.getText().trim());
            } else {
                JOptionPane.showMessageDialog(null, "请输入书名！");
            }

        } else if (querycomboBox.getSelectedItem().equals("通过ISBN查询")) {
            if (!querytextField.getText().trim().isEmpty()) {
                showListData(querytextField.getText().trim());
            } else {
                JOptionPane.showMessageDialog(null, "请输入ISBN！");
            }
        } else {
            showListData();
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        titlelabel = new JLabel();
        scrollPane1 = new JScrollPane();
        bookInfoTable = new JTable();
        insertButton = new JButton();
        deleteButton = new JButton();
        queryButton = new JButton();
        updateButton = new JButton();
        querytextField = new JTextField();
        querycomboBox = new JComboBox<>();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- titlelabel ----
        titlelabel.setText("\u56fe\u4e66\u4fe1\u606f\u7ba1\u7406");
        titlelabel.setFont(titlelabel.getFont().deriveFont(titlelabel.getFont().getSize() + 8f));
        titlelabel.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(titlelabel);
        titlelabel.setBounds(270, 15, 185, 30);

        //======== scrollPane1 ========
        {

            //---- bookInfoTable ----
            bookInfoTable.setModel(new DefaultTableModel(
                    new Object[][]{
                            {null, null, null, null, null, null, null, null},
                            {null, null, null, null, null, null, null, null},
                    },
                    new String[]{
                            null, null, null, null, null, null, null, null
                    }
            ));
            scrollPane1.setViewportView(bookInfoTable);
        }
        contentPane.add(scrollPane1);
        scrollPane1.setBounds(5, 60, 715, 230);

        //---- insertButton ----
        insertButton.setText("\u6dfb\u52a0\u56fe\u4e66");
        insertButton.setFont(insertButton.getFont().deriveFont(insertButton.getFont().getSize() + 4f));
        insertButton.addActionListener(e -> insertButtonActionPerformed(e));
        contentPane.add(insertButton);
        insertButton.setBounds(545, 310, 120, 35);

        //---- deleteButton ----
        deleteButton.setText("\u5220\u9664\u56fe\u4e66");
        deleteButton.setFont(deleteButton.getFont().deriveFont(deleteButton.getFont().getSize() + 4f));
        deleteButton.addActionListener(e -> deleteButtonActionPerformed(e));
        contentPane.add(deleteButton);
        deleteButton.setBounds(395, 350, 115, 35);

        //---- queryButton ----
        queryButton.setText("\u67e5\u8be2\u56fe\u4e66");
        queryButton.setFont(queryButton.getFont().deriveFont(queryButton.getFont().getSize() + 4f));
        queryButton.addActionListener(e -> queryButtonActionPerformed(e));
        contentPane.add(queryButton);
        queryButton.setBounds(395, 310, 115, 35);

        //---- updateButton ----
        updateButton.setText("\u4fee\u6539\u56fe\u4e66");
        updateButton.setFont(updateButton.getFont().deriveFont(updateButton.getFont().getSize() + 4f));
        updateButton.addActionListener(e -> updateButtonActionPerformed(e));
        contentPane.add(updateButton);
        updateButton.setBounds(545, 350, 120, 35);
        contentPane.add(querytextField);
        querytextField.setBounds(170, 310, 170, 35);

        //---- querycomboBox ----
        querycomboBox.setModel(new DefaultComboBoxModel<>(new String[]{
                "\u67e5\u8be2\u6240\u6709\u56fe\u4e66",
                "\u901a\u8fc7\u4e66\u540d\u67e5\u8be2",
                "\u901a\u8fc7ISBN\u67e5\u8be2"
        }));
        contentPane.add(querycomboBox);
        querycomboBox.setBounds(new Rectangle(new Point(25, 310), querycomboBox.getPreferredSize()));

        contentPane.setPreferredSize(new Dimension(730, 420));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
        showListData();
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel titlelabel;
    private JScrollPane scrollPane1;
    private JTable bookInfoTable;
    private JButton insertButton;
    private JButton deleteButton;
    private JButton queryButton;
    private JButton updateButton;
    private JTextField querytextField;
    private JComboBox<String> querycomboBox;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    public void showListData() {
        //刷新展示界面信息功能
        List<BookInfor> bookInfors = bookInforService.queryAll();
        String[][] datas = new String[bookInfors.size()][9];
        for (int i = 0; i < datas.length; i++) {
            datas[i][0] = bookInfors.get(i).getISBN();
            datas[i][1] = bookInfors.get(i).getTypeId().toString();
            datas[i][2] = bookInfors.get(i).getBookName();
            datas[i][3] = bookInfors.get(i).getWriter();
            datas[i][4] = bookInfors.get(i).getTranslator();
            datas[i][5] = bookInfors.get(i).getPublisher();
            datas[i][6] = bookInfors.get(i).getDate().toString();
            datas[i][7] = bookInfors.get(i).getPrice().toString();
            datas[i][8] = bookInfors.get(i).getQuantity().toString();
        }
        bookInfoTable.setModel(new DefaultTableModel(
                datas, new String[]{"图书编号", "类别编号", "图书名称", "作者", "译者", "出版社", "出版日期", "书籍价格", "书籍数量"}
        ));
        scrollPane1.setViewportView(bookInfoTable);
    }

    public void showListData(String ISBN) {
        //通过ISBN查询，从而刷新展示界面功能
        List<BookInfor> bookInfors = bookInforService.queryByISBN(ISBN);
        String[][] datas = new String[bookInfors.size()][9];
        for (int i = 0; i < datas.length; i++) {
            datas[i][0] = bookInfors.get(i).getISBN();
            datas[i][1] = bookInfors.get(i).getTypeId().toString();
            datas[i][2] = bookInfors.get(i).getBookName();
            datas[i][3] = bookInfors.get(i).getWriter();
            datas[i][4] = bookInfors.get(i).getTranslator();
            datas[i][5] = bookInfors.get(i).getPublisher();
            datas[i][6] = bookInfors.get(i).getDate().toString();
            datas[i][7] = bookInfors.get(i).getPrice().toString();
            datas[i][8] = bookInfors.get(i).getQuantity().toString();
        }
        bookInfoTable.setModel(new DefaultTableModel(
                datas, new String[]{"图书编号", "类别编号", "图书名称", "作者", "译者", "出版社", "出版日期", "书籍价格", "书籍数量"}
        ));
        scrollPane1.setViewportView(bookInfoTable);
    }

    public void showListDataByBookName(String bookName) {
        //通过书名查询并且刷新展示界面
        List<BookInfor> bookInfors = bookInforService.queryByBookName(bookName);
        String[][] datas = new String[bookInfors.size()][9];
        for (int i = 0; i < datas.length; i++) {
            datas[i][0] = bookInfors.get(i).getISBN();
            datas[i][1] = bookInfors.get(i).getTypeId().toString();
            datas[i][2] = bookInfors.get(i).getBookName();
            datas[i][3] = bookInfors.get(i).getWriter();
            datas[i][4] = bookInfors.get(i).getTranslator();
            datas[i][5] = bookInfors.get(i).getPublisher();
            datas[i][6] = bookInfors.get(i).getDate().toString();
            datas[i][7] = bookInfors.get(i).getPrice().toString();
            datas[i][8] = bookInfors.get(i).getQuantity().toString();
        }
        bookInfoTable.setModel(new DefaultTableModel(
                datas, new String[]{"图书编号", "类别编号", "图书名称", "作者", "译者", "出版社", "出版日期", "书籍价格", "书籍数量"}
        ));
        scrollPane1.setViewportView(bookInfoTable);
    }

    public BookInfor getRowData() {
        //获取光标所点击的对象信息
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        BookInfor bookInfor = null;
        int index = bookInfoTable.getSelectedRow();
        // 如果下标不为-1，则选中行为数据行
        if (index != -1) {
            // 取得表格对象的数据模型
            TableModel model = bookInfoTable.getModel();
            // 在表格对象模型中，根据选中的行和列，获取相应的数据值
            String ISBN = model.getValueAt(index, 0).toString();
            String typeId = model.getValueAt(index, 1).toString();
            String bookName = model.getValueAt(index, 2).toString();
            String writer = model.getValueAt(index, 3).toString();
            String translator = model.getValueAt(index, 4).toString();
            String publisher = model.getValueAt(index, 5).toString();
            String tempDate = model.getValueAt(index, 6).toString();
            String price = model.getValueAt(index, 7).toString();
            String quantity = model.getValueAt(index, 8).toString();
            bookInfor = new BookInfor();
            bookInfor.setISBN(ISBN);
            if (typeId.matches("\\d{1,4}")) {
                if (!bookTypeService.queryByBookTypeId(Integer.parseInt(typeId)).isEmpty()) {
                    bookInfor.setTypeId(Integer.parseInt(typeId));
                } else {
                    bookInfor.setTypeId(999);
                }
            } else {
                bookInfor.setTypeId(999);
            }
            bookInfor.setBookName(bookName);
            bookInfor.setWriter(writer);
            bookInfor.setTranslator(translator);
            bookInfor.setPublisher(publisher);
            if (tempDate.matches("[1-9]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|1[1-9]|2[1-9]|3[0-1])")) {
                Date date = null;
                try {
                    date = sdf.parse(tempDate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if (date.getTime() < System.currentTimeMillis()) {
                    bookInfor.setDate(new java.sql.Date(date.getTime()));
                } else {
                    JOptionPane.showMessageDialog(null, "不可超过当前时间,现已设置为当前时间");
                    bookInfor.setDate(new java.sql.Date(System.currentTimeMillis()));
                }
            } else {
                JOptionPane.showMessageDialog(null, "请输入正确的2020-05-06日期格式,现已设置为当前时间");
                bookInfor.setDate(new java.sql.Date(System.currentTimeMillis()));
            }
            if (price.matches("\\d{1,4}|\\d{1,4}.\\d{1,2}")) {
                bookInfor.setPrice(Double.parseDouble(price));
            } else {
                JOptionPane.showMessageDialog(null, "请输入正确存在的图书数量,现已设置为默认值9999");
                bookInfor.setPrice(9999.0);
            }
            if (quantity.matches("\\d{1,4}")) {
                bookInfor.setQuantity(Integer.parseInt(quantity));
            } else {
                JOptionPane.showMessageDialog(null, "请输入正确存在的图书数量,现已设置为默认值50");
                bookInfor.setQuantity(50);
            }
        }
        // 如果没有选中任何一行 就意味着bookType为null 可以提供给外部判断
        return bookInfor;
    }

    public static void main(String[] args) {
        new BookInfoFrame().setVisible(true);
    }

    //自己增加的方法方便更改信息时的判断信息是否符合规范，符合则返回true
    public static boolean bookInforBan(BookInfor bookInfor) {
        BookTypeService bookTypeService = new BookTypeServiceImpl();
        boolean typeIdBoo = false;
        boolean bookNameBoo = false;
        boolean writerBoo = false;
        boolean translatorBoo = false;
        boolean publisherBoo = false;
        boolean dateBoo = false;
        boolean priceBoo = false;
        boolean quantityBoo = false;

        if (!bookTypeService.queryByBookTypeId(bookInfor.getTypeId()).isEmpty()) {
            typeIdBoo = true;
        } else {
            JOptionPane.showMessageDialog(null, "请输入正确存在的图书类别！");
        }

        if (!bookInfor.getBookName().isEmpty()) {
            bookNameBoo = true;
        } else {
            JOptionPane.showMessageDialog(null, "请输入书名");
        }

        if (!bookInfor.getWriter().isEmpty()) {
            writerBoo = true;
        } else {
            JOptionPane.showMessageDialog(null, "请输入书名");
        }

        if (!bookInfor.getTranslator().isEmpty()) {
            translatorBoo = true;
        } else {
            JOptionPane.showMessageDialog(null, "请输入译者");
        }

        if (!bookInfor.getPublisher().isEmpty()) {
            publisherBoo = true;
        } else {
            JOptionPane.showMessageDialog(null, "请输入出版社");
        }


        if (!bookInfor.getDate().toString().isEmpty()) {
            if (bookInfor.getDate().toString().matches("[1-9]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|1[1-9]|2[1-9]|3[0-1])")) {
                if (bookInfor.getDate().getTime() < System.currentTimeMillis()) {
                    dateBoo = true;
                }
            } else {
                JOptionPane.showMessageDialog(null, "请输入2020-05-06格式时间");
            }
        } else {
            JOptionPane.showMessageDialog(null, "请输入2020-05-06格式时间");
        }

        if (bookInfor.getPrice() != null) {
            if (bookInfor.getPrice() >= 0 && bookInfor.getPrice() <= 9999) {
                priceBoo = true;
            } else {
                JOptionPane.showMessageDialog(null, "图书价格不规范，请输入（1-9999）");
            }
        } else {
            JOptionPane.showMessageDialog(null, "请输入图书价格");
        }

        if (bookInfor.getQuantity() != null) {
            if (bookInfor.getQuantity() >= 0 && bookInfor.getQuantity() <= 9999) {
                quantityBoo = true;
            } else {
                JOptionPane.showMessageDialog(null, "数量请输入0-9999");
            }
        } else {
            JOptionPane.showMessageDialog(null, "请输入书本数量");
        }

        if (typeIdBoo == true && bookNameBoo == true && writerBoo == true && translatorBoo == true && publisherBoo == true && dateBoo == true && priceBoo == true && quantityBoo == true) {
            return true;
        }
        return false;
    }
}
