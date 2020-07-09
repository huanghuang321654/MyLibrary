/*
 * Created by JFormDesigner on Wed Jul 01 15:43:10 CST 2020
 */

package com.library.ui;

import com.library.entity.Operator;
import com.library.service.OperatorService;
import com.library.service.impl.OperatorServiceImpl;
import com.library.lib.TempleOperatorName;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * @author 4
 */
public class LoginFrame {
    //调用操作员服务层方便功能的调用
    public static OperatorService operatorService = new OperatorServiceImpl();
    //用于控制登陆主窗口
    public static JFrame LoginName;

    public LoginFrame() {
        initComponents();
    }

    private void exitButtonActionPerformed(ActionEvent e) {
        // 退出按钮实现功能
        LoginName.dispose();

    }

    private void loginButtonActionPerformed(ActionEvent e) {
        // 登录按钮判断操作员是否存在于数据库中，存在则登录成功
        String username = LoginNametextField.getText();
        String password = LoginPasswordField.getText();
        Operator operator = new Operator();
        operator.setName(username);
        operator.setPassword(password);
        //判断输入的账号和密码是否存在于操作员数据库中
        if (operatorService.login(operator)) {
            //使用一个临时对象来存储用户登陆的信息，方便后续方法的使用
            TempleOperatorName.templeOperator = new Operator();
            TempleOperatorName.templeOperator.setName(operator.getName());
            TempleOperatorName.templeOperator.setPassword(operator.getPassword());
            TempleOperatorName.templeOperator.setAdmin(operatorService.queryByOperatorName(operator.getName()).get(0).getAdmin());
            TempleOperatorName.templeOperator.setId(operatorService.queryByOperatorName(operator.getName()).get(0).getId());
            JOptionPane.showMessageDialog(null, "登录成功！");
            //判断登陆的账号是否为管理员，而打开不同的窗口
            if (TempleOperatorName.templeOperator.getAdmin() == 0) {
                new OperatorMainFrame().setVisible(true);
                LoginName.dispose();
            } else {
                new MainFrame().setVisible(true);
                LoginName.dispose();
            }
        } else {
            //若不满足条件则提示登陆失败
            JOptionPane.showMessageDialog(null, "登录失败！");
        }
    }

    private void registerButtonActionPerformed(ActionEvent e) {
        // 注册功能，打开注册窗口
        RegisterFrame registerFrame = new RegisterFrame();
        registerFrame.getContentPane().setBackground(Color.LIGHT_GRAY);
        registerFrame.setVisible(true);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        LoginName = new JFrame();
        SystemTitlelabel = new JLabel();
        LoginNamelabel = new JLabel();
        LoginNametextField = new JTextField();
        LoginPasswordlabel = new JLabel();
        loginButton = new JButton();
        exitButton = new JButton();
        registerButton = new JButton();
        LoginPasswordField = new JPasswordField();

        //======== LoginName ========
        {
            LoginName.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
            Container LoginNameContentPane = LoginName.getContentPane();
            LoginNameContentPane.setLayout(null);

            //---- SystemTitlelabel ----
            SystemTitlelabel.setText("\u56fe\u4e66\u7ba1\u7406\u7cfb\u7edf");
            SystemTitlelabel.setFont(SystemTitlelabel.getFont().deriveFont(SystemTitlelabel.getFont().getSize() + 8f));
            SystemTitlelabel.setHorizontalAlignment(SwingConstants.CENTER);
            LoginNameContentPane.add(SystemTitlelabel);
            SystemTitlelabel.setBounds(165, 15, 240, 30);

            //---- LoginNamelabel ----
            LoginNamelabel.setText("\u8d26\u53f7:");
            LoginNamelabel.setHorizontalAlignment(SwingConstants.CENTER);
            LoginNamelabel.setFont(LoginNamelabel.getFont().deriveFont(LoginNamelabel.getFont().getSize() + 6f));
            LoginNameContentPane.add(LoginNamelabel);
            LoginNamelabel.setBounds(55, 80, 80, 30);
            LoginNameContentPane.add(LoginNametextField);
            LoginNametextField.setBounds(120, 75, 335, 40);

            //---- LoginPasswordlabel ----
            LoginPasswordlabel.setText("\u5bc6\u7801:");
            LoginPasswordlabel.setFont(LoginPasswordlabel.getFont().deriveFont(LoginPasswordlabel.getFont().getSize() + 6f));
            LoginPasswordlabel.setHorizontalAlignment(SwingConstants.CENTER);
            LoginNameContentPane.add(LoginPasswordlabel);
            LoginPasswordlabel.setBounds(55, 175, 80, 30);

            //---- loginButton ----
            loginButton.setText("\u767b\u5f55");
            loginButton.setFont(loginButton.getFont().deriveFont(loginButton.getFont().getSize() + 4f));
            loginButton.addActionListener(e -> loginButtonActionPerformed(e));
            LoginNameContentPane.add(loginButton);
            loginButton.setBounds(245, 250, 90, 35);

            //---- exitButton ----
            exitButton.setText("\u9000\u51fa");
            exitButton.setFont(exitButton.getFont().deriveFont(exitButton.getFont().getSize() + 4f));
            exitButton.addActionListener(e -> exitButtonActionPerformed(e));
            LoginNameContentPane.add(exitButton);
            exitButton.setBounds(370, 250, 85, 35);

            //---- registerButton ----
            registerButton.setText("\u6ce8\u518c");
            registerButton.setFont(registerButton.getFont().deriveFont(registerButton.getFont().getSize() + 4f));
            registerButton.addActionListener(e -> registerButtonActionPerformed(e));
            LoginNameContentPane.add(registerButton);
            registerButton.setBounds(120, 250, 85, 35);
            LoginNameContentPane.add(LoginPasswordField);
            LoginPasswordField.setBounds(120, 170, 340, 35);

            LoginNameContentPane.setPreferredSize(new Dimension(570, 380));
            LoginName.pack();
            LoginName.setLocationRelativeTo(LoginName.getOwner());
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel SystemTitlelabel;
    private JLabel LoginNamelabel;
    private JTextField LoginNametextField;
    private JLabel LoginPasswordlabel;
    private JButton loginButton;
    private JButton exitButton;
    private JButton registerButton;
    private JPasswordField LoginPasswordField;

    // JFormDesigner - End of variables declaration  //GEN-END:variables
    public static void main(String[] args) {
        LoginFrame loginFrame = new LoginFrame();
        LoginName.getContentPane().setBackground(Color.LIGHT_GRAY);
        LoginName.setVisible(true);
    }


}


