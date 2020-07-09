/*
 * Created by JFormDesigner on Sun Jul 05 22:25:10 CST 2020
 */

package com.library.ui;

import java.awt.event.*;

import com.library.entity.BookInfor;
import com.library.service.BookInforService;
import com.library.service.impl.BookInforServiceImpl;

import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.*;

/**
 * @author 4
 */
public class SearchBook extends JFrame {
    BookInforService bookInforService = new BookInforServiceImpl();

    public SearchBook() {
        initComponents();
    }

    private void searchbuttonActionPerformed(ActionEvent e) {
        // 搜索按钮
        if (searchcomboBox.getSelectedItem().equals("通过书名查询")) {
            if (!searchtextField.getText().trim().isEmpty()) {
                showListDataByBookName(searchtextField.getText().trim());
            } else {
                JOptionPane.showMessageDialog(null, "请输入书名！");
            }

        } else if (searchcomboBox.getSelectedItem().equals("通过ISBN查询")) {
            if (!searchtextField.getText().trim().isEmpty()) {
                showListData(searchtextField.getText().trim());
            } else {
                JOptionPane.showMessageDialog(null, "请输入ISBN！");
            }
        } else {
            showListData();
        }
    }

    private void cancelbuttonActionPerformed(ActionEvent e) {
        // 取消按钮
        this.dispose();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        titlelabel = new JLabel();
        scrollPane1 = new JScrollPane();
        searchBookTable = new JTable();
        searchbutton = new JButton();
        cancelbutton = new JButton();
        searchcomboBox = new JComboBox<>();
        searchtextField = new JTextField();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- titlelabel ----
        titlelabel.setText("\u56fe\u4e66\u641c\u7d22");
        titlelabel.setHorizontalAlignment(SwingConstants.CENTER);
        titlelabel.setFont(titlelabel.getFont().deriveFont(titlelabel.getFont().getStyle() & ~Font.ITALIC, titlelabel.getFont().getSize() + 8f));
        contentPane.add(titlelabel);
        titlelabel.setBounds(160, 5, 205, 50);

        //======== scrollPane1 ========
        {

            //---- searchBookTable ----
            searchBookTable.setModel(new DefaultTableModel(
                new Object[][] {
                    {null, null, null, null, null, null, null, null},
                    {null, null, null, null, null, null, null, null},
                },
                new String[] {
                    null, null, null, null, null, null, null, null
                }
            ));
            scrollPane1.setViewportView(searchBookTable);
        }
        contentPane.add(scrollPane1);
        scrollPane1.setBounds(5, 55, 520, 250);

        //---- searchbutton ----
        searchbutton.setText("\u641c\u7d22");
        searchbutton.setFont(searchbutton.getFont().deriveFont(searchbutton.getFont().getSize() + 4f));
        searchbutton.addActionListener(e -> searchbuttonActionPerformed(e));
        contentPane.add(searchbutton);
        searchbutton.setBounds(265, 315, 105, 40);

        //---- cancelbutton ----
        cancelbutton.setText("\u53d6\u6d88");
        cancelbutton.setFont(cancelbutton.getFont().deriveFont(cancelbutton.getFont().getSize() + 4f));
        cancelbutton.addActionListener(e -> cancelbuttonActionPerformed(e));
        contentPane.add(cancelbutton);
        cancelbutton.setBounds(380, 315, 105, 40);

        //---- searchcomboBox ----
        searchcomboBox.setModel(new DefaultComboBoxModel<>(new String[] {
            "\u67e5\u8be2\u5168\u90e8\u56fe\u4e66",
            "\u901a\u8fc7\u4e66\u540d\u67e5\u8be2",
            "\u901a\u8fc7ISBN\u67e5\u8be2"
        }));
        contentPane.add(searchcomboBox);
        searchcomboBox.setBounds(5, 315, 125, searchcomboBox.getPreferredSize().height);
        contentPane.add(searchtextField);
        searchtextField.setBounds(145, 320, 105, 35);

        contentPane.setPreferredSize(new Dimension(535, 430));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
        showListData();
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel titlelabel;
    private JScrollPane scrollPane1;
    private JTable searchBookTable;
    private JButton searchbutton;
    private JButton cancelbutton;
    private JComboBox<String> searchcomboBox;
    private JTextField searchtextField;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    public void showListData() {
        java.util.List<BookInfor> bookInfors = bookInforService.queryAll();
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
        searchBookTable.setModel(new DefaultTableModel(
                datas, new String[]{"图书编号", "类别编号", "图书名称", "作者", "译者", "出版社", "出版日期", "书籍价格", "书籍数量"}
        ));
        scrollPane1.setViewportView(searchBookTable);
    }

    public void showListData(String ISBN) {
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
        searchBookTable.setModel(new DefaultTableModel(
                datas, new String[]{"图书编号", "类别编号", "图书名称", "作者", "译者", "出版社", "出版日期", "书籍价格", "书籍数量"}
        ));
        scrollPane1.setViewportView(searchBookTable);
    }

    public void showListDataByBookName(String bookName) {
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
        searchBookTable.setModel(new DefaultTableModel(
                datas, new String[]{"图书编号", "类别编号", "图书名称", "作者", "译者", "出版社", "出版日期", "书籍价格", "书籍数量"}
        ));
        scrollPane1.setViewportView(searchBookTable);
    }

    public static void main(String[] args) {
        new SearchBook().setVisible(true);
    }
}
