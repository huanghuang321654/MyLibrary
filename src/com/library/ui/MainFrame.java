/*
 * Created by JFormDesigner on Thu Jul 02 09:32:04 CST 2020
 */

package com.library.ui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * @author 4
 */
public class MainFrame extends JFrame {
    public MainFrame() {
        initComponents();
    }

    private void booktypebuttonActionPerformed(ActionEvent e) {
        // 图书类别管理功能模块
        new BookTypeFrame().setVisible(true);
    }

    private void bookinfobuttonActionPerformed(ActionEvent e) {
        // 图书信息管理功能模块
        new BookInfoFrame().setVisible(true);
    }

    private void readerinfobuttonActionPerformed(ActionEvent e) {
        // 读者信息管理功能模块
        new ReaderFrame().setVisible(true);
    }

    private void orderbuttonActionPerformed(ActionEvent e) {
        // 新书订购功能模块
        new OrderFrame().setVisible(true);

    }

    private void borrowbuttonActionPerformed(ActionEvent e) {
        // 图书借阅管理按钮
        new BorrowFrame().setVisible(true);
    }

    private void systemmaintainbuttonActionPerformed(ActionEvent e) {
        // 系统维护
        new OperatorFrame().setVisible(true);
    }

    private void backbuttonActionPerformed(ActionEvent e) {
        // TODO add your code here
        LoginFrame.LoginName.setVisible(true);
        this.dispose();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        titlelabel = new JLabel();
        booktypebutton = new JButton();
        bookinfobutton = new JButton();
        readerinfobutton = new JButton();
        orderbutton = new JButton();
        borrowbutton = new JButton();
        systemmaintainbutton = new JButton();
        backbutton = new JButton();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- titlelabel ----
        titlelabel.setText("\u7ba1\u7406\u5458\u4e3b\u7a97\u53e3");
        titlelabel.setHorizontalAlignment(SwingConstants.CENTER);
        titlelabel.setFont(titlelabel.getFont().deriveFont(titlelabel.getFont().getSize() + 8f));
        contentPane.add(titlelabel);
        titlelabel.setBounds(180, 15, 180, 40);

        //---- booktypebutton ----
        booktypebutton.setText("\u56fe\u4e66\u7c7b\u522b\u7ba1\u7406");
        booktypebutton.setFont(booktypebutton.getFont().deriveFont(booktypebutton.getFont().getSize() + 4f));
        booktypebutton.addActionListener(e -> booktypebuttonActionPerformed(e));
        contentPane.add(booktypebutton);
        booktypebutton.setBounds(85, 70, 180, 40);

        //---- bookinfobutton ----
        bookinfobutton.setText("\u56fe\u4e66\u4fe1\u606f\u7ba1\u7406");
        bookinfobutton.setFont(bookinfobutton.getFont().deriveFont(bookinfobutton.getFont().getSize() + 4f));
        bookinfobutton.addActionListener(e -> bookinfobuttonActionPerformed(e));
        contentPane.add(bookinfobutton);
        bookinfobutton.setBounds(275, 70, 190, 40);

        //---- readerinfobutton ----
        readerinfobutton.setText("\u8bfb\u8005\u4fe1\u606f\u7ba1\u7406");
        readerinfobutton.setFont(readerinfobutton.getFont().deriveFont(readerinfobutton.getFont().getSize() + 4f));
        readerinfobutton.addActionListener(e -> readerinfobuttonActionPerformed(e));
        contentPane.add(readerinfobutton);
        readerinfobutton.setBounds(85, 135, 180, 40);

        //---- orderbutton ----
        orderbutton.setText("\u65b0\u4e66\u8ba2\u8d2d\u7ba1\u7406");
        orderbutton.setFont(orderbutton.getFont().deriveFont(orderbutton.getFont().getSize() + 4f));
        orderbutton.addActionListener(e -> orderbuttonActionPerformed(e));
        contentPane.add(orderbutton);
        orderbutton.setBounds(85, 195, 180, 40);

        //---- borrowbutton ----
        borrowbutton.setText("\u56fe\u4e66\u501f\u9605\u7ba1\u7406");
        borrowbutton.setFont(borrowbutton.getFont().deriveFont(borrowbutton.getFont().getSize() + 4f));
        borrowbutton.addActionListener(e -> borrowbuttonActionPerformed(e));
        contentPane.add(borrowbutton);
        borrowbutton.setBounds(275, 135, 190, 40);

        //---- systemmaintainbutton ----
        systemmaintainbutton.setText("\u7cfb\u7edf\u7ef4\u62a4");
        systemmaintainbutton.setFont(systemmaintainbutton.getFont().deriveFont(systemmaintainbutton.getFont().getSize() + 4f));
        systemmaintainbutton.addActionListener(e -> systemmaintainbuttonActionPerformed(e));
        contentPane.add(systemmaintainbutton);
        systemmaintainbutton.setBounds(275, 195, 190, 40);

        //---- backbutton ----
        backbutton.setText("\u9000\u51fa\u5f53\u524d\u8d26\u53f7");
        backbutton.setFont(backbutton.getFont().deriveFont(backbutton.getFont().getSize() + 4f));
        backbutton.addActionListener(e -> backbuttonActionPerformed(e));
        contentPane.add(backbutton);
        backbutton.setBounds(360, 250, 145, 40);

        contentPane.setPreferredSize(new Dimension(540, 430));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel titlelabel;
    private JButton booktypebutton;
    private JButton bookinfobutton;
    private JButton readerinfobutton;
    private JButton orderbutton;
    private JButton borrowbutton;
    private JButton systemmaintainbutton;
    private JButton backbutton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    public static void main(String[] args) {
        new MainFrame().setVisible(true);
    }
}
