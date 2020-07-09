/*
 * Created by JFormDesigner on Sun Jul 05 12:48:15 CST 2020
 */

package com.library.ui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * @author 4
 */
public class BorrowFrame extends JFrame {
    public BorrowFrame() {
        initComponents();
    }

    private void borrowbuttonActionPerformed(ActionEvent e) {
        // 图书借阅按钮
        new BookBorrowFrame().setVisible(true);
    }

    private void returnbuttonActionPerformed(ActionEvent e) {
        // 图书归还
        new BookReturnFrame().setVisible(true);
    }

    private void searchbuttonActionPerformed(ActionEvent e) {
        // 图书搜索
        new SearchBook().setVisible(true);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        titlelabel = new JLabel();
        borrowbutton = new JButton();
        returnbutton = new JButton();
        searchbutton = new JButton();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- titlelabel ----
        titlelabel.setText("\u56fe\u4e66\u501f\u9605\u7ba1\u7406");
        titlelabel.setHorizontalAlignment(SwingConstants.CENTER);
        titlelabel.setFont(titlelabel.getFont().deriveFont(titlelabel.getFont().getSize() + 8f));
        contentPane.add(titlelabel);
        titlelabel.setBounds(185, 15, 125, 30);

        //---- borrowbutton ----
        borrowbutton.setText("\u56fe\u4e66\u501f\u9605");
        borrowbutton.setFont(borrowbutton.getFont().deriveFont(borrowbutton.getFont().getSize() + 4f));
        borrowbutton.addActionListener(e -> borrowbuttonActionPerformed(e));
        contentPane.add(borrowbutton);
        borrowbutton.setBounds(170, 90, 160, 45);

        //---- returnbutton ----
        returnbutton.setText("\u56fe\u4e66\u5f52\u8fd8");
        returnbutton.setFont(returnbutton.getFont().deriveFont(returnbutton.getFont().getSize() + 4f));
        returnbutton.addActionListener(e -> returnbuttonActionPerformed(e));
        contentPane.add(returnbutton);
        returnbutton.setBounds(170, 175, 160, 45);

        //---- searchbutton ----
        searchbutton.setText("\u56fe\u4e66\u641c\u7d22");
        searchbutton.setFont(searchbutton.getFont().deriveFont(searchbutton.getFont().getSize() + 4f));
        searchbutton.addActionListener(e -> searchbuttonActionPerformed(e));
        contentPane.add(searchbutton);
        searchbutton.setBounds(170, 255, 160, 45);

        contentPane.setPreferredSize(new Dimension(495, 465));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel titlelabel;
    private JButton borrowbutton;
    private JButton returnbutton;
    private JButton searchbutton;

    // JFormDesigner - End of variables declaration  //GEN-END:variables
    public static void main(String[] args) {
        new BorrowFrame().setVisible(true);
    }
}
