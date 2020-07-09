/*
 * Created by JFormDesigner on Thu Jul 02 11:55:33 CST 2020
 */

package com.library.ui;

import com.library.entity.BookType;
import com.library.service.BookTypeService;
import com.library.service.impl.BookTypeServiceImpl;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * @author 4
 */
public class InsertBookTypeFrame extends JFrame {
    public InsertBookTypeFrame() {
        initComponents();
    }

    private void defineButtonActionPerformed(ActionEvent e) {
        // 图书类别信息添加确定按钮
        BookTypeService bookTypeService = new BookTypeServiceImpl();
        BookType bookType = new BookType();
        String id = booTypekIdTextField.getText().trim();
        String typeNmae = bookTypeNametextField.getText().trim();
        String days = daysTextField.getText().trim();
        String fk = fkTextField.getText().trim();
        //用于判断输入的图书类别信息是否合理
        boolean idBoo = false;
        boolean typeNameBoo = false;
        boolean daysBoo = false;
        boolean fkBoo = false;

        if (id != null) {
            if (id.matches("\\d{1,3}")) {
                bookType.setId(Integer.parseInt(id));
                idBoo = true;
            } else {
                JOptionPane.showMessageDialog(null, "规范错误，图书类型请输入（0~999）");
            }
        } else {
            JOptionPane.showMessageDialog(null, "请输入图书类型！");
        }

        if (!typeNmae.isEmpty()) {
            bookType.setTypeName(typeNmae);
            typeNameBoo = true;
        } else {
            JOptionPane.showMessageDialog(null, "请输入书名！");
        }

        if (days != null) {
            if (days.matches("[1-9]\\d{0,3}")) {
                if(Integer.parseInt(days) < 0)
                {
                    JOptionPane.showMessageDialog(null, "可借天数不可为负数");
                }else
                {
                    bookType.setDays(Integer.parseInt(days));
                    daysBoo = true;
                }
            } else {
                JOptionPane.showMessageDialog(null, "可借天数格式不规范，请输入（0~9999）");
            }
        } else {
            JOptionPane.showMessageDialog(null, "请输入可借天数！");
        }

        if (!fk.isEmpty()) {
            if (fk.matches("([0-9]{1,4}.[0-9]{1,4})")) {
                if(Double.parseDouble(fk) < 0)
                {
                    JOptionPane.showMessageDialog(null, "罚款金额不可为负数！");
                }else
                {
                    bookType.setFk(Double.parseDouble(fk));
                    fkBoo = true;
                }
            } else {
                JOptionPane.showMessageDialog(null, "罚款金额格式不规范，请输入（0.0~9999.0）");
            }
        } else {
            JOptionPane.showMessageDialog(null, "请输入罚款金额！");
        }

        if (idBoo == true && typeNameBoo == true && daysBoo == true && fkBoo == true) {
            bookTypeService.insert(bookType);
            JOptionPane.showMessageDialog(null, "添加成功！");
            this.setVisible(false);
            new BookTypeFrame().setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "添加失败");
        }

    }

    private void cancelButtonActionPerformed(ActionEvent e) {
        // TODO add your code here
        this.setVisible(false);
        new BookTypeFrame().setVisible(true);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        insertlabel = new JLabel();
        TypeIdlabel = new JLabel();
        booTypekIdTextField = new JTextField();
        TypeNamelabel2 = new JLabel();
        bookTypeNametextField = new JTextField();
        Dayslabel3 = new JLabel();
        daysTextField = new JTextField();
        Fklabel4 = new JLabel();
        fkTextField = new JTextField();
        defineButton = new JButton();
        cancelButton = new JButton();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- insertlabel ----
        insertlabel.setText("\u56fe\u4e66\u7c7b\u522b\u63d2\u5165\u529f\u80fd");
        insertlabel.setHorizontalAlignment(SwingConstants.CENTER);
        insertlabel.setFont(insertlabel.getFont().deriveFont(insertlabel.getFont().getSize() + 8f));
        contentPane.add(insertlabel);
        insertlabel.setBounds(180, 5, 180, 45);

        //---- TypeIdlabel ----
        TypeIdlabel.setText("\u56fe\u4e66\u7c7b\u522b\u7f16\u53f7\uff1a");
        TypeIdlabel.setFont(TypeIdlabel.getFont().deriveFont(TypeIdlabel.getFont().getSize() + 2f));
        TypeIdlabel.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(TypeIdlabel);
        TypeIdlabel.setBounds(55, 70, 110, 35);
        contentPane.add(booTypekIdTextField);
        booTypekIdTextField.setBounds(180, 70, 235, 35);

        //---- TypeNamelabel2 ----
        TypeNamelabel2.setText("\u56fe\u4e66\u7c7b\u522b\u540d\u79f0\uff1a");
        TypeNamelabel2.setFont(TypeNamelabel2.getFont().deriveFont(TypeNamelabel2.getFont().getSize() + 2f));
        TypeNamelabel2.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(TypeNamelabel2);
        TypeNamelabel2.setBounds(50, 130, 110, 35);
        contentPane.add(bookTypeNametextField);
        bookTypeNametextField.setBounds(180, 130, 235, 35);

        //---- Dayslabel3 ----
        Dayslabel3.setText("\u53ef\u501f\u5929\u6570\uff1a");
        Dayslabel3.setFont(Dayslabel3.getFont().deriveFont(Dayslabel3.getFont().getSize() + 2f));
        Dayslabel3.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(Dayslabel3);
        Dayslabel3.setBounds(50, 195, 110, 35);
        contentPane.add(daysTextField);
        daysTextField.setBounds(180, 195, 235, 35);

        //---- Fklabel4 ----
        Fklabel4.setText("\u8fdf\u8fd8\u4e00\u5929\u7f5a\u6b3e\u91d1\u989d\uff1a");
        Fklabel4.setFont(Fklabel4.getFont().deriveFont(Fklabel4.getFont().getSize() + 2f));
        Fklabel4.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(Fklabel4);
        Fklabel4.setBounds(30, 260, 145, 35);
        contentPane.add(fkTextField);
        fkTextField.setBounds(180, 260, 235, 35);

        //---- defineButton ----
        defineButton.setText("\u786e\u5b9a");
        defineButton.setFont(defineButton.getFont().deriveFont(defineButton.getFont().getSize() + 4f));
        defineButton.addActionListener(e -> defineButtonActionPerformed(e));
        contentPane.add(defineButton);
        defineButton.setBounds(110, 325, 100, 35);

        //---- cancelButton ----
        cancelButton.setText("\u53d6\u6d88");
        cancelButton.setFont(cancelButton.getFont().deriveFont(cancelButton.getFont().getSize() + 4f));
        cancelButton.addActionListener(e -> cancelButtonActionPerformed(e));
        contentPane.add(cancelButton);
        cancelButton.setBounds(280, 325, 100, 35);

        contentPane.setPreferredSize(new Dimension(540, 415));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel insertlabel;
    private JLabel TypeIdlabel;
    private JTextField booTypekIdTextField;
    private JLabel TypeNamelabel2;
    private JTextField bookTypeNametextField;
    private JLabel Dayslabel3;
    private JTextField daysTextField;
    private JLabel Fklabel4;
    private JTextField fkTextField;
    private JButton defineButton;
    private JButton cancelButton;

    // JFormDesigner - End of variables declaration  //GEN-END:variables
    public static void main(String[] args) {
        new InsertBookTypeFrame().setVisible(true);
    }
}
