/*
 * Created by JFormDesigner on Thu Jul 02 14:07:23 CST 2020
 */

package com.library.ui;

import com.library.entity.Operator;
import com.library.service.OperatorService;
import com.library.service.impl.OperatorServiceImpl;

import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;

/**
 * @author 4
 */
public class RegisterFrame extends JFrame {
    public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    public static OperatorService operatorService = new OperatorServiceImpl();

    public RegisterFrame() {
        initComponents();
    }

    private void cancelbutton2ActionPerformed(ActionEvent e) {
        // 取消按钮功能
        this.setVisible(false);
    }

    private void registerbuttonActionPerformed(ActionEvent e) {
        // 注册按钮功能
        String name = nameField.getText().trim();
        String password = passwordField.getText().trim();
        String sex = sexcomboBox.getSelectedItem().toString().trim();
        String age = ageField.getText().trim();
        String identify = idField.getText().trim();
        String tel = telField.getText().trim();
        boolean nameBoo = false;
        boolean passwordBoo = false;
        boolean sexBoo = false;
        boolean ageBoo = false;
        boolean identityBoo = false;
        boolean telBoo = false;
        Operator operator = new Operator();
        if (!name.isEmpty()) {
            if (name.matches("\\w{6,18}")) {
                if (!operatorService.queryByOperatorName(name).isEmpty()) {
                    namelabel1.setText("用户名已经存在,请重新输入！");
                    namelabel1.setForeground(Color.red);
                } else {
                    operator.setName(name);
                    namelabel1.setText("符合规范");
                    namelabel1.setForeground(Color.black);
                    nameBoo = true;
                }
            } else {
                namelabel1.setText("用户名不符合规范,请输入6-18位的字母或者数字");
                namelabel1.setForeground(Color.red);
            }
        } else {
            namelabel1.setText("请输入用户名");
            namelabel1.setForeground(Color.red);
        }

        if (!password.isEmpty()) {
            if (password.matches("\\w{6,18}")) {
                operator.setPassword(password);
                passwordlabel1.setText("符合规范");
                passwordlabel1.setForeground(Color.black);
                passwordBoo = true;
            } else {
                passwordlabel1.setText("密码不规范,请输入6-18位的字母或者数字");
                passwordlabel1.setForeground(Color.red);
            }
        } else {
            passwordlabel1.setText("请输入密码");
            passwordlabel1.setForeground(Color.red);
        }

        if (sex.equals("男") || sex.equals("女")) {
            operator.setSex(sex);
            sexlabel1.setText("符合规范");
            sexlabel1.setForeground(Color.black);
            sexBoo = true;
        } else {
            sexlabel1.setText("请选择性别");
            sexlabel1.setForeground(Color.red);
        }

        if (!age.isEmpty()) {
            if (age.matches("\\d{1,4}")) {
                operator.setAge(Integer.parseInt(age));
                agelabel1.setText("符合规范");
                agelabel1.setForeground(Color.black);
                ageBoo = true;

            } else {
                agelabel1.setText("请输入正确年龄");
                agelabel1.setForeground(Color.red);
            }
        } else {
            agelabel1.setText("请输入年龄");
            agelabel1.setForeground(Color.red);
        }

        if (!identify.isEmpty()) {
            if (identify.matches("[1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]")) {
                operator.setIdentityCard(identify);
                identifylabel1.setText("符合规范");
                identifylabel1.setForeground(Color.black);
                identityBoo = true;
            } else {
                identifylabel1.setText("证件号格式错误");
                identifylabel1.setForeground(Color.red);
            }
        } else {
            identifylabel1.setText("请输入证件号");
            identifylabel1.setForeground(Color.red);
        }

        operator.setWorkDate(new java.sql.Date(System.currentTimeMillis())); //注册时间既为工作时间

        if (!tel.isEmpty()) {
            if (tel.matches("1[34578]\\d{9}")) {
                operator.setTel(tel);
                tellabel1.setText("符合规范");
                tellabel1.setForeground(Color.black);
                telBoo = true;
            } else {
                tellabel1.setText("请输入正确11位电话号码");
                tellabel1.setForeground(Color.red);
            }
        } else {
            operator.setTel(null);
            telBoo = true;
        }

        if (nameBoo == true && sexBoo == true && passwordBoo == true && ageBoo == true && identityBoo == true && telBoo == true) {
            operatorService.insert(operator);
            JOptionPane.showMessageDialog(null, "注册成功！");
            this.setVisible(false);
        } else {
            JOptionPane.showMessageDialog(null, "注册失败！");
        }
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        titlelabel = new JLabel();
        namelabel = new JLabel();
        nameField = new JTextField();
        passwordlabel = new JLabel();
        passwordField = new JPasswordField();
        sexlabel = new JLabel();
        sexcomboBox = new JComboBox<>();
        agelabel = new JLabel();
        ageField = new JTextField();
        idlabel = new JLabel();
        idField = new JTextField();
        tellabel = new JLabel();
        telField = new JTextField();
        registerbutton = new JButton();
        cancelbutton2 = new JButton();
        namelabel1 = new JLabel();
        passwordlabel1 = new JLabel();
        sexlabel1 = new JLabel();
        agelabel1 = new JLabel();
        identifylabel1 = new JLabel();
        tellabel1 = new JLabel();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- titlelabel ----
        titlelabel.setText("\u6ce8\u518c\u529f\u80fd");
        titlelabel.setHorizontalAlignment(SwingConstants.CENTER);
        titlelabel.setFont(titlelabel.getFont().deriveFont(titlelabel.getFont().getSize() + 8f));
        contentPane.add(titlelabel);
        titlelabel.setBounds(new Rectangle(new Point(230, 10), titlelabel.getPreferredSize()));

        //---- namelabel ----
        namelabel.setText("\u7528\u6237\u540d\uff1a");
        namelabel.setHorizontalAlignment(SwingConstants.CENTER);
        namelabel.setFont(namelabel.getFont().deriveFont(namelabel.getFont().getSize() + 4f));
        contentPane.add(namelabel);
        namelabel.setBounds(55, 50, 70, 30);
        contentPane.add(nameField);
        nameField.setBounds(130, 50, 195, 30);

        //---- passwordlabel ----
        passwordlabel.setText("\u5bc6    \u7801\uff1a");
        passwordlabel.setHorizontalAlignment(SwingConstants.CENTER);
        passwordlabel.setFont(passwordlabel.getFont().deriveFont(passwordlabel.getFont().getSize() + 4f));
        contentPane.add(passwordlabel);
        passwordlabel.setBounds(50, 95, 70, 25);
        contentPane.add(passwordField);
        passwordField.setBounds(130, 90, 195, 30);

        //---- sexlabel ----
        sexlabel.setText("\u6027    \u522b\uff1a");
        sexlabel.setHorizontalAlignment(SwingConstants.CENTER);
        sexlabel.setFont(sexlabel.getFont().deriveFont(sexlabel.getFont().getSize() + 4f));
        contentPane.add(sexlabel);
        sexlabel.setBounds(55, 130, 65, 25);

        //---- sexcomboBox ----
        sexcomboBox.setEditable(true);
        sexcomboBox.setModel(new DefaultComboBoxModel<>(new String[]{
                "\u8bf7\u9009\u62e9",
                "\u7537",
                "\u5973"
        }));
        contentPane.add(sexcomboBox);
        sexcomboBox.setBounds(130, 130, 95, 35);

        //---- agelabel ----
        agelabel.setText("\u5e74    \u9f84\uff1a");
        agelabel.setHorizontalAlignment(SwingConstants.CENTER);
        agelabel.setFont(agelabel.getFont().deriveFont(agelabel.getFont().getSize() + 4f));
        contentPane.add(agelabel);
        agelabel.setBounds(45, 170, 65, 25);
        contentPane.add(ageField);
        ageField.setBounds(130, 170, 195, 27);

        //---- idlabel ----
        idlabel.setText("\u8bc1\u4ef6\u53f7\uff1a");
        idlabel.setHorizontalAlignment(SwingConstants.CENTER);
        idlabel.setFont(idlabel.getFont().deriveFont(idlabel.getFont().getSize() + 4f));
        contentPane.add(idlabel);
        idlabel.setBounds(45, 210, 70, 25);
        contentPane.add(idField);
        idField.setBounds(125, 210, 195, 27);

        //---- tellabel ----
        tellabel.setText("\u7535\u8bdd\u53f7\u7801\uff1a");
        tellabel.setHorizontalAlignment(SwingConstants.CENTER);
        tellabel.setFont(tellabel.getFont().deriveFont(tellabel.getFont().getSize() + 4f));
        contentPane.add(tellabel);
        tellabel.setBounds(35, 250, 95, 25);
        contentPane.add(telField);
        telField.setBounds(125, 250, 195, 27);

        //---- registerbutton ----
        registerbutton.setText("\u6ce8\u518c");
        registerbutton.setFont(registerbutton.getFont().deriveFont(registerbutton.getFont().getSize() + 4f));
        registerbutton.addActionListener(e -> registerbuttonActionPerformed(e));
        contentPane.add(registerbutton);
        registerbutton.setBounds(120, 330, 85, 35);

        //---- cancelbutton2 ----
        cancelbutton2.setText("\u53d6\u6d88");
        cancelbutton2.setFont(cancelbutton2.getFont().deriveFont(cancelbutton2.getFont().getSize() + 4f));
        cancelbutton2.addActionListener(e -> cancelbutton2ActionPerformed(e));
        contentPane.add(cancelbutton2);
        cancelbutton2.setBounds(285, 330, 85, 35);

        //---- namelabel1 ----
        namelabel1.setText("\u8bf7\u8f93\u51656-18\u4f4d\u5b57\u6bcd\u6216\u6570\u5b57");
        namelabel1.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(namelabel1);
        namelabel1.setBounds(345, 50, 160, 30);

        //---- passwordlabel1 ----
        passwordlabel1.setText("\u8bf7\u8f93\u51656-18\u4f4d\u5b57\u6bcd\u6216\u8005\u6570\u5b57");
        passwordlabel1.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(passwordlabel1);
        passwordlabel1.setBounds(350, 95, 160, 30);

        //---- sexlabel1 ----
        sexlabel1.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(sexlabel1);
        sexlabel1.setBounds(355, 135, 160, 30);

        //---- agelabel1 ----
        agelabel1.setText("\u8bf7\u8f93\u5165\u5e74\u9f84");
        agelabel1.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(agelabel1);
        agelabel1.setBounds(350, 170, 160, 30);

        //---- identifylabel1 ----
        identifylabel1.setHorizontalAlignment(SwingConstants.CENTER);
        identifylabel1.setText("\u8bf7\u8f93\u516518\u4f4d\u8eab\u4efd\u8bc1\u53f7\u7801");
        contentPane.add(identifylabel1);
        identifylabel1.setBounds(355, 210, 160, 30);

        //---- tellabel1 ----
        tellabel1.setHorizontalAlignment(SwingConstants.CENTER);
        tellabel1.setText("\u8bf7\u8f93\u516511\u4f4d\u6b63\u786e\u7684\u53f7\u7801");
        contentPane.add(tellabel1);
        tellabel1.setBounds(335, 250, 185, 30);

        contentPane.setPreferredSize(new Dimension(540, 430));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel titlelabel;
    private JLabel namelabel;
    private JTextField nameField;
    private JLabel passwordlabel;
    private JPasswordField passwordField;
    private JLabel sexlabel;
    private JComboBox<String> sexcomboBox;
    private JLabel agelabel;
    private JTextField ageField;
    private JLabel idlabel;
    private JTextField idField;
    private JLabel tellabel;
    private JTextField telField;
    private JButton registerbutton;
    private JButton cancelbutton2;
    private JLabel namelabel1;
    private JLabel passwordlabel1;
    private JLabel sexlabel1;
    private JLabel agelabel1;
    private JLabel identifylabel1;
    private JLabel tellabel1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

}
