/*
 * Created by JFormDesigner on Mon Jul 06 13:49:30 CST 2020
 */

package com.library.ui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * @author 4
 */
public class OperatorMainFrame extends JFrame {
    public OperatorMainFrame() {
        initComponents();
    }

    private void bookBorrowButtonActionPerformed(ActionEvent e) {
        // 操作员图书借阅功能
        new BorrowFrame().setVisible(true);
    }

    private void changePasswordButtonActionPerformed(ActionEvent e) {
        // 操作员更改密码
        new ChangePasswordFrame().setVisible(true);
    }

    private void backButtonActionPerformed(ActionEvent e) {
        // TODO add your code here
        LoginFrame.LoginName.setVisible(true);
        this.dispose();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        titlelabel = new JLabel();
        bookBorrowButton = new JButton();
        changePasswordButton = new JButton();
        backButton = new JButton();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- titlelabel ----
        titlelabel.setText("\u64cd\u4f5c\u5458\u4e3b\u7a97\u53e3");
        titlelabel.setFont(titlelabel.getFont().deriveFont(titlelabel.getFont().getSize() + 8f));
        titlelabel.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(titlelabel);
        titlelabel.setBounds(205, 10, 120, 35);

        //---- bookBorrowButton ----
        bookBorrowButton.setText("\u56fe\u4e66\u501f\u9605\u7ba1\u7406");
        bookBorrowButton.setFont(bookBorrowButton.getFont().deriveFont(bookBorrowButton.getFont().getSize() + 4f));
        bookBorrowButton.addActionListener(e -> bookBorrowButtonActionPerformed(e));
        contentPane.add(bookBorrowButton);
        bookBorrowButton.setBounds(80, 115, 165, 40);

        //---- changePasswordButton ----
        changePasswordButton.setText("\u66f4\u6539\u5bc6\u7801");
        changePasswordButton.setFont(changePasswordButton.getFont().deriveFont(changePasswordButton.getFont().getSize() + 4f));
        changePasswordButton.addActionListener(e -> changePasswordButtonActionPerformed(e));
        contentPane.add(changePasswordButton);
        changePasswordButton.setBounds(305, 115, 120, 40);

        //---- backButton ----
        backButton.setText("\u9000\u51fa\u5f53\u524d\u8d26\u53f7");
        backButton.setFont(backButton.getFont().deriveFont(backButton.getFont().getSize() + 4f));
        backButton.addActionListener(e -> backButtonActionPerformed(e));
        contentPane.add(backButton);
        backButton.setBounds(195, 180, 165, 40);

        contentPane.setPreferredSize(new Dimension(535, 350));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel titlelabel;
    private JButton bookBorrowButton;
    private JButton changePasswordButton;
    private JButton backButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    public static void main(String[] args) {
        new OperatorMainFrame().setVisible(true);
    }
}
