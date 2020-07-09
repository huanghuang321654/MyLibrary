/*
 * Created by JFormDesigner on Mon Jul 06 11:53:45 CST 2020
 */

package com.library.ui;

import com.library.entity.Operator;
import com.library.lib.TempleOperatorName;
import com.library.service.OperatorService;
import com.library.service.impl.OperatorServiceImpl;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * @author 4
 */
public class ChangePasswordFrame extends JFrame {
    OperatorService operatorService = new OperatorServiceImpl();

    public ChangePasswordFrame() {
        initComponents();
    }

    private void defineButtonActionPerformed(ActionEvent e) {
        // 修改密码确认按钮
        String oldPassword = oldPasswordField.getText().trim();
        String newPassword = newPasswordField.getText().trim();
        String newPassword2 = newPasswordField2.getText().trim();
        if (!oldPasswordField.getText().trim().isEmpty()) {
            if (TempleOperatorName.templeOperator.getPassword().equals(oldPassword)) {
                if (!newPassword.isEmpty() && !newPassword2.isEmpty()) {
                    if (newPassword.matches("\\w{6,18}")) {
                        if (newPassword.equals(newPassword2)) {
                            Operator operator = operatorService.queryByOperatorName(TempleOperatorName.templeOperator.getName()).get(0);
                            System.out.println(operator);
                            operator.setPassword(newPassword);
                            System.out.println(operator);
                            operatorService.update(operator);
                            TempleOperatorName.templeOperator.setPassword(newPassword);
                            JOptionPane.showMessageDialog(null, "密码修改成功！");
                        } else {
                            JOptionPane.showMessageDialog(null, "新密码与确认密码不一致,密码修改失败！");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "新密码必须为6~18为的字母或者数字");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "请输入新密码与确认密码！");
                }
            } else {
                JOptionPane.showMessageDialog(null, "您的旧密码输入不正确");
            }
        } else {
            JOptionPane.showMessageDialog(null, "请输入旧密码！");
        }
    }

    private void cancelButtonActionPerformed(ActionEvent e) {
        // 取消按钮
        this.dispose();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        titlelabel = new JLabel();
        newPasswordLabel = new JLabel();
        newPasswordLabel2 = new JLabel();
        oldPasswordLabel = new JLabel();
        defineButton = new JButton();
        cancelButton = new JButton();
        oldPasswordField = new JPasswordField();
        newPasswordField = new JPasswordField();
        newPasswordField2 = new JPasswordField();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- titlelabel ----
        titlelabel.setText("\u4fee\u6539\u5bc6\u7801");
        titlelabel.setHorizontalAlignment(SwingConstants.CENTER);
        titlelabel.setFont(titlelabel.getFont().deriveFont(titlelabel.getFont().getSize() + 10f));
        contentPane.add(titlelabel);
        titlelabel.setBounds(205, 25, 120, 30);

        //---- newPasswordLabel ----
        newPasswordLabel.setText("\u8bf7\u8f93\u5165\u65b0\u5bc6\u7801\uff1a");
        newPasswordLabel.setFont(newPasswordLabel.getFont().deriveFont(newPasswordLabel.getFont().getSize() + 2f));
        contentPane.add(newPasswordLabel);
        newPasswordLabel.setBounds(55, 155, 110, 40);

        //---- newPasswordLabel2 ----
        newPasswordLabel2.setText("\u8bf7\u786e\u8ba4\u65b0\u5bc6\u7801\uff1a");
        newPasswordLabel2.setFont(newPasswordLabel2.getFont().deriveFont(newPasswordLabel2.getFont().getSize() + 2f));
        contentPane.add(newPasswordLabel2);
        newPasswordLabel2.setBounds(55, 215, 110, 40);

        //---- oldPasswordLabel ----
        oldPasswordLabel.setText("\u8bf7\u8f93\u5165\u65e7\u5bc6\u7801\uff1a");
        oldPasswordLabel.setFont(oldPasswordLabel.getFont().deriveFont(oldPasswordLabel.getFont().getSize() + 2f));
        contentPane.add(oldPasswordLabel);
        oldPasswordLabel.setBounds(55, 95, 110, 40);

        //---- defineButton ----
        defineButton.setText("\u786e\u8ba4");
        defineButton.setFont(defineButton.getFont().deriveFont(defineButton.getFont().getSize() + 4f));
        defineButton.addActionListener(e -> defineButtonActionPerformed(e));
        contentPane.add(defineButton);
        defineButton.setBounds(135, 300, 95, 40);

        //---- cancelButton ----
        cancelButton.setText("\u53d6\u6d88");
        cancelButton.setFont(cancelButton.getFont().deriveFont(cancelButton.getFont().getSize() + 4f));
        cancelButton.addActionListener(e -> cancelButtonActionPerformed(e));
        contentPane.add(cancelButton);
        cancelButton.setBounds(295, 300, 95, 40);
        contentPane.add(oldPasswordField);
        oldPasswordField.setBounds(160, 95, 210, 40);
        contentPane.add(newPasswordField);
        newPasswordField.setBounds(160, 155, 210, 40);
        contentPane.add(newPasswordField2);
        newPasswordField2.setBounds(160, 215, 210, 40);

        contentPane.setPreferredSize(new Dimension(530, 415));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel titlelabel;
    private JLabel newPasswordLabel;
    private JLabel newPasswordLabel2;
    private JLabel oldPasswordLabel;
    private JButton defineButton;
    private JButton cancelButton;
    private JPasswordField oldPasswordField;
    private JPasswordField newPasswordField;
    private JPasswordField newPasswordField2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
