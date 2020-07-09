/*
 * Created by JFormDesigner on Mon Jul 06 10:09:17 CST 2020
 */

package com.library.ui;

import java.awt.event.*;

import com.library.entity.BookInfor;
import com.library.entity.Operator;
import com.library.service.OperatorService;
import com.library.service.impl.OperatorServiceImpl;

import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * @author 4
 */
public class OperatorFrame extends JFrame {
    OperatorService operatorService = new OperatorServiceImpl();

    public OperatorFrame() {
        initComponents();
    }

    private void changePasswordButtonActionPerformed(ActionEvent e) {
        // 修改密码功能
        new ChangePasswordFrame().setVisible(true);
    }

    private void operatorManagebuttonActionPerformed(ActionEvent e) {
        // 操作员管理功能
        new OperatorManageFrame().setVisible(true);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        titleLabel = new JLabel();
        operatorManagebutton = new JButton();
        changePasswordButton = new JButton();

        //======== this ========
        setTitle("\u7cfb\u7edf\u7ef4\u62a4");
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- titleLabel ----
        titleLabel.setText("\u7cfb\u7edf\u7ef4\u62a4");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(titleLabel.getFont().deriveFont(titleLabel.getFont().getSize() + 8f));
        contentPane.add(titleLabel);
        titleLabel.setBounds(225, 5, 145, 35);

        //---- operatorManagebutton ----
        operatorManagebutton.setText("\u64cd\u4f5c\u5458\u7ba1\u7406");
        operatorManagebutton.setFont(operatorManagebutton.getFont().deriveFont(operatorManagebutton.getFont().getSize() + 4f));
        operatorManagebutton.addActionListener(e -> operatorManagebuttonActionPerformed(e));
        contentPane.add(operatorManagebutton);
        operatorManagebutton.setBounds(205, 90, 190, 45);

        //---- changePasswordButton ----
        changePasswordButton.setText("\u4fee\u6539\u5bc6\u7801");
        changePasswordButton.setFont(changePasswordButton.getFont().deriveFont(changePasswordButton.getFont().getSize() + 4f));
        changePasswordButton.addActionListener(e -> changePasswordButtonActionPerformed(e));
        contentPane.add(changePasswordButton);
        changePasswordButton.setBounds(205, 200, 190, 45);

        contentPane.setPreferredSize(new Dimension(595, 410));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel titleLabel;
    private JButton operatorManagebutton;
    private JButton changePasswordButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

}
