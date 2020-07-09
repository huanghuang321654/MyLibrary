/*
 * Created by JFormDesigner on Sun Jul 05 12:51:21 CST 2020
 */

package com.library.ui;

import java.awt.event.*;

import com.library.entity.BookInfor;
import com.library.entity.Borrow;
import com.library.entity.Reader;
import com.library.lib.TempleOperatorName;
import com.library.service.BookInforService;
import com.library.service.BorrowService;
import com.library.service.ReaderService;
import com.library.service.impl.BookInforServiceImpl;
import com.library.service.impl.BorrowServiceImpl;
import com.library.service.impl.ReaderServiceImpl;

import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.*;
import javax.swing.table.*;

/**
 * @author 4
 */
public class BookBorrowFrame extends JFrame {
    //设置日期格式
    public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    //调用需要用到的服务层功能
    BorrowService borrowService = new BorrowServiceImpl();
    BookInforService bookInforService = new BookInforServiceImpl();
    ReaderService readerService = new ReaderServiceImpl();
    Borrow borrow = new Borrow();

    public BookBorrowFrame() {
        initComponents();
    }

    private void defineButtonActionPerformed(ActionEvent e) {
        // 确定按钮既插入图书借阅记录
        String bookISBN = bookISBNTextField.getText().trim();
        String readerISBN = readerISBNTextField.getText().trim();
        boolean bookISBNBoo = false;
        boolean readerISBNBoo = false;
        //判断输入的图书ISBN是否符合标准
        if (!bookISBN.isEmpty()) {
            if (bookISBN.matches("\\d{3}")) {
                if (!bookInforService.queryByISBN(bookISBN).isEmpty()) {
                    borrow.setBookISBN(bookISBN);
                    bookISBNBoo = true;
                } else {
                    JOptionPane.showMessageDialog(null, "图书馆不存在编号为" + bookISBN + "这本书");
                }
            } else {
                JOptionPane.showMessageDialog(null, "规范错误，书籍编号请输入001类型！");
            }
        } else {
            JOptionPane.showMessageDialog(null, "请输入书籍编号！");
        }

        //判断输入的读者ISBN是否符合标准
        if (!readerISBN.isEmpty()) {
            if (readerISBN.matches("\\d{3}")) {
                if (!readerService.queryByReaderISBN(readerISBN).isEmpty()) {
                    borrow.setReaderISBN(readerISBN);
                    readerISBNBoo = true;
                } else {
                    JOptionPane.showMessageDialog(null, "图书馆不存在编号为" + readerISBN + "这位读者");
                }
            } else {
                JOptionPane.showMessageDialog(null, "规范错误，读者编号请输入001类型！");
            }
        } else {
            JOptionPane.showMessageDialog(null, "请输入读者编号！");
        }

        borrow.setBorrowDate(new java.sql.Date(System.currentTimeMillis()));  //借阅日期获取当前时间
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 30);
        Date date = calendar.getTime();
        borrow.setBackDate(new java.sql.Date(date.getTime()));               //应还日期直接在借阅日期上增加30天
        borrow.setIsBack(0);                                                //因为是借阅功能，所以默认设置未归还
        borrow.setOperatorId(TempleOperatorName.templeOperator.getId());
        borrow.setFk(0.0);

        //若输入的内容都符合输入标准，则进行借书判断
        if (bookISBNBoo == true && readerISBNBoo == true) {
            List<Borrow> list1 = borrowService.queryByBookISBN(bookISBN);
            List<Borrow> list2 = borrowService.queryByReaderISBN(readerISBN);
            System.out.println(list1);
            System.out.println(list2);
            //如果查询到的书本集合或者读者集合为空，代表读者没有借过这本书
            if (list1.isEmpty() || list2.isEmpty()) {
                //借阅记录增加一条记录
                borrowService.insert(borrow);
                BookInfor tempBookInfor = bookInforService.queryByISBN(bookISBN).get(0);
                //判断所借书本数量是否为0，若为0则借阅书本失败
                if (tempBookInfor.getQuantity() > 0) {
                    //否则则对图书数量减1，并且更新数据库
                    tempBookInfor.setQuantity(tempBookInfor.getQuantity() - 1);
                    bookInforService.update(tempBookInfor);
                } else {
                    JOptionPane.showMessageDialog(null, "这本书已经被借完了！");
                    return;
                }
                Reader tempReader = readerService.queryByReaderISBN(readerISBN).get(0);
                //判断读者的最大借书量是否为0，若为0则不可借阅
                if (tempReader.getMaxNum() > 0) {
                    //否则借书成功，但是读者借书量减1
                    tempReader.setMaxNum(tempReader.getMaxNum() - 1);
                } else {
                    JOptionPane.showMessageDialog(null, "你已超过最大借书量，借书失败！");
                    return;
                }
                readerService.update(tempReader);
                JOptionPane.showMessageDialog(null, "借书成功！");
                showListData();
            }
            boolean flag = false;
            boolean breakflag = false;
            //使用两个图书信息集合和通过读者ISBN进行查询的两个几个进行循环判断读者是否有已经借取本书但是未归还的情况
            for (Borrow borrow1 : list1) {
                for (Borrow borrow2 : list2) {
                    if (borrow1.getBookISBN().equals(borrow2.getBookISBN()) && borrow1.getReaderISBN().equals(borrow2.getReaderISBN()) && borrow1.getIsBack() == 0) {
                        flag = false;
                        breakflag = true;
                        break;
                    } else {
                        flag = true;
                    }
                }
                if (breakflag == true) {
                    break;
                }
            }

            //如果flag等于true则表示读者并未借过此书或者是借过此书但是已经归还
            if (flag == true) {
                if (readerService.queryByReaderISBN(readerISBN).get(0).getMaxNum() > 0) {
                    if (bookInforService.queryByISBN(bookISBN).get(0).getQuantity() > 0) {
                        borrowService.insert(borrow);
                        BookInfor tempBookInfor = bookInforService.queryByISBN(bookISBN).get(0);
                        tempBookInfor.setQuantity(tempBookInfor.getQuantity() - 1);
                        bookInforService.update(tempBookInfor);
                        Reader tempReader = readerService.queryByReaderISBN(readerISBN).get(0);
                        tempReader.setMaxNum(tempReader.getMaxNum() - 1);
                        readerService.update(tempReader);
                        JOptionPane.showMessageDialog(null, "借书成功！");
                        showListData();
                    } else {
                        JOptionPane.showMessageDialog(null, "这本书已经被借完了！");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "已经超过最大借书量！不可借阅");
                }
            } else {
                JOptionPane.showMessageDialog(null, "你已经借阅过此书并且还未归还！");
            }
        } else {
            JOptionPane.showMessageDialog(null, "借书失败，请检查填写信息是否正确！");
        }
    }


    private void cancelButtonActionPerformed(ActionEvent e) {
        // 取消按钮功能
        this.dispose();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        titlelabel = new JLabel();
        bookISBNLabel = new JLabel();
        bookISBNTextField = new JTextField();
        readerISBNLabel = new JLabel();
        readerISBNTextField = new JTextField();
        defineButton = new JButton();
        cancelButton = new JButton();
        scrollPane1 = new JScrollPane();
        bookBorrowTable = new JTable();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- titlelabel ----
        titlelabel.setText("\u56fe\u4e66\u501f\u9605");
        titlelabel.setHorizontalAlignment(SwingConstants.CENTER);
        titlelabel.setFont(titlelabel.getFont().deriveFont(titlelabel.getFont().getSize() + 8f));
        contentPane.add(titlelabel);
        titlelabel.setBounds(205, 10, 110, 30);

        //---- bookISBNLabel ----
        bookISBNLabel.setText("\u4e66\u7c4d\u7f16\u53f7\uff1a");
        bookISBNLabel.setHorizontalAlignment(SwingConstants.CENTER);
        bookISBNLabel.setFont(bookISBNLabel.getFont().deriveFont(bookISBNLabel.getFont().getSize() + 4f));
        contentPane.add(bookISBNLabel);
        bookISBNLabel.setBounds(10, 325, 105, 25);
        contentPane.add(bookISBNTextField);
        bookISBNTextField.setBounds(115, 320, 115, 32);

        //---- readerISBNLabel ----
        readerISBNLabel.setText("\u8bfb\u8005\u7f16\u53f7\uff1a");
        readerISBNLabel.setHorizontalAlignment(SwingConstants.CENTER);
        readerISBNLabel.setFont(readerISBNLabel.getFont().deriveFont(readerISBNLabel.getFont().getSize() + 4f));
        contentPane.add(readerISBNLabel);
        readerISBNLabel.setBounds(290, 325, 105, 25);
        contentPane.add(readerISBNTextField);
        readerISBNTextField.setBounds(380, 320, 115, 32);

        //---- defineButton ----
        defineButton.setText("\u786e\u5b9a");
        defineButton.setFont(defineButton.getFont().deriveFont(defineButton.getFont().getSize() + 4f));
        defineButton.addActionListener(e -> defineButtonActionPerformed(e));
        contentPane.add(defineButton);
        defineButton.setBounds(105, 370, 105, 35);

        //---- cancelButton ----
        cancelButton.setText("\u53d6\u6d88");
        cancelButton.setFont(cancelButton.getFont().deriveFont(cancelButton.getFont().getSize() + 4f));
        cancelButton.addActionListener(e -> cancelButtonActionPerformed(e));
        contentPane.add(cancelButton);
        cancelButton.setBounds(310, 370, 105, 35);

        //======== scrollPane1 ========
        {

            //---- bookBorrowTable ----
            bookBorrowTable.setModel(new DefaultTableModel(
                    new Object[][]{
                            {null, null, null, null, null, null, null},
                            {null, null, null, null, null, null, null},
                    },
                    new String[]{
                            null, null, null, null, null, null, null
                    }
            ));
            scrollPane1.setViewportView(bookBorrowTable);
        }
        contentPane.add(scrollPane1);
        scrollPane1.setBounds(25, 50, 475, 255);

        contentPane.setPreferredSize(new Dimension(525, 460));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
        showListData();
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel titlelabel;
    private JLabel bookISBNLabel;
    private JTextField bookISBNTextField;
    private JLabel readerISBNLabel;
    private JTextField readerISBNTextField;
    private JButton defineButton;
    private JButton cancelButton;
    private JScrollPane scrollPane1;
    private JTable bookBorrowTable;

    // JFormDesigner - End of variables declaration  //GEN-END:variables
    public void showListData() {
        //光标获取对象
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
        bookBorrowTable.setModel(new DefaultTableModel(
                datas, new String[]{"图书编号", "类别编号", "图书名称", "作者", "译者", "出版社", "出版日期", "书籍价格", "书籍数量"}
        ));
        scrollPane1.setViewportView(bookBorrowTable);
    }

}
