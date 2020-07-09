/*
 * Created by JFormDesigner on Fri Jul 03 11:34:38 CST 2020
 */

package com.library.ui;

import com.library.entity.BookInfor;
import com.library.entity.Reader;
import com.library.service.BookInforService;
import com.library.service.ReaderService;
import com.library.service.impl.BookInforServiceImpl;
import com.library.service.impl.ReaderServiceImpl;

import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;

/**
 * @author 4
 */
public class InsertReaderFrame extends JFrame {
    public SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public InsertReaderFrame() {
        initComponents();
    }

    private void defineButtonActionPerformed(ActionEvent e) {
        // 读者信息添加确定按钮
        ReaderService readerService = new ReaderServiceImpl();
        Reader reader = new Reader();
        String name = nameTextField.getText().trim();
        String sex = sexComboBox.getSelectedItem().toString();
        String age = ageTextField.getText();
        String identityCard = identityTextField.getText().trim();
        String date = dateTextField.getText().trim();
        String maxNum = maxTextField.getText();
        String tel = telTextField.getText().trim();
        String keepMoney = keepMoneyTextField.getText().trim();
        String zj = zjComboBox.getSelectedItem().toString();
        String zy = zyTextField.getText().trim();
        String ISBN = ISBNTextField.getText().trim();
        String bzTime = bzTimeTextField.getText().trim();
        boolean nameBoo = false;
        boolean sexBoo = false;
        boolean ageBoo = false;
        boolean identityCardBoo = false;
        boolean dateBoo = false;
        boolean maxNumBoo = false;
        boolean telBoo = false;
        boolean keepMoneyBoo = false;
        boolean zyBoo = false;
        boolean ISBNBoo = false;
        boolean bzTimeBoo = false;

        if (!name.isEmpty()) {
            reader.setName(name);
            nameBoo = true;
        } else {
            JOptionPane.showMessageDialog(null, "请输入读者名字！");
        }

        if (sex.equals("男") || sex.equals("女")) {
            reader.setSex(sex);
            sexBoo = true;
        } else {
            JOptionPane.showMessageDialog(null, "请选择读者名字！");
        }

        if (!age.isEmpty()) {
            if (age.matches("[1-9]\\d{0,3}")) {
                reader.setAge(Integer.parseInt(age));
                ageBoo = true;
            } else {
                JOptionPane.showMessageDialog(null, "年龄输入不规范，请输入去（1~9999）");
            }
        } else {
            JOptionPane.showMessageDialog(null, "请选择读者年龄！");
        }

        if (!identityCard.isEmpty()) {
            if (identityCard.matches("[1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]")) {
                reader.setIdentityCard(identityCard);
                identityCardBoo = true;
            } else {
                JOptionPane.showMessageDialog(null, "证件错误，请输入正确的证件号");
            }
        } else {
            JOptionPane.showMessageDialog(null, "请输入正确的证件号");
        }

        if (!date.isEmpty()) {
            if (date.matches("[1-9]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|1[1-9]|2[1-9]|3[0-1])")) {
                Date date1 = null;
                try {
                    date1 = sdf.parse(date);
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }
                reader.setDate(new java.sql.Date(date1.getTime()));
                dateBoo = true;
            } else {
                JOptionPane.showMessageDialog(null, "请输入2020-05-06格式时间");
            }
        } else {
            JOptionPane.showMessageDialog(null, "请输入2020-05-06格式时间");
        }

        if (maxNum != null) {
            if (maxNum.matches("[1-9]\\d{0,3}")) {
                reader.setMaxNum(Integer.parseInt(maxNum));
                maxNumBoo = true;
            } else {
                JOptionPane.showMessageDialog(null, "最大借阅量不规范，请输入（1-9999）");
            }
        } else {
            JOptionPane.showMessageDialog(null, "请输入最大借阅量");
        }

        if (!tel.isEmpty()) {
            if (tel.matches("1[34578]\\d{9}")) {
                reader.setTel(tel);
                telBoo = true;
            } else {
                JOptionPane.showMessageDialog(null, "请输入正确11位电话号码");
            }
        } else {
            JOptionPane.showMessageDialog(null, "请输入正确11位电话号码");
        }

        if (keepMoney != null) {
            if (keepMoney.matches("([0-9]{1,4})")) {
                reader.setKeepMoney(Double.parseDouble(keepMoney));
                keepMoneyBoo = true;
            } else {
                JOptionPane.showMessageDialog(null, "押金格式不规范，请输入（0~9999）");
            }
        } else {
            JOptionPane.showMessageDialog(null, "请输入押金！");
        }

        if (zjComboBox.getSelectedItem().equals("中国大陆居民身份证")) {
            reader.setZj(0);
        } else if (zjComboBox.getSelectedItem().equals("港澳台居民身份证")) {
            reader.setZj(1);
        } else if (zjComboBox.getSelectedItem().equals("国内外通行护照")) {
            reader.setZj(3);
        } else if (zjComboBox.getSelectedItem().equals("军人证")) {
            reader.setZj(4);
        } else {
            reader.setZj(5);
        }


        if (!zy.isEmpty()) {

            reader.setZy(zy);
            zyBoo = true;
        } else {
            JOptionPane.showMessageDialog(null, "请输入读者职业！");
        }

        //判断输入的ISBN是否符合标准
        if (!ISBN.isEmpty()) {
            if (ISBN.matches("\\d{3}")) {
                if (!readerService.queryByReaderISBN(ISBN).isEmpty()) {
                    JOptionPane.showMessageDialog(null, "读者编号重复，无需重新添加！");
                } else {
                    reader.setISBN(ISBN);
                    ISBNBoo = true;
                }
            } else {
                JOptionPane.showMessageDialog(null, "规范错误，读者编号请输入（001-999）类型！");
            }
        } else {
            JOptionPane.showMessageDialog(null, "请输入读者编号！");
        }

        if (!bzTime.isEmpty()) {
            if (bzTime.matches("[1-9]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|1[1-9]|2[1-9]|3[0-1])")) {
                Date date1 = null;
                try {
                    date1 = sdf.parse(bzTime);
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }
                if(date1.getTime() < System.currentTimeMillis())
                {
                    reader.setBztime(new java.sql.Date(date1.getTime()));
                    bzTimeBoo = true;
                }else
                {
                    JOptionPane.showMessageDialog(null, "时间不可超前");
                }
            } else {
                JOptionPane.showMessageDialog(null, "请输入2020-05-06格式时间");
            }
        } else {
            JOptionPane.showMessageDialog(null, "请输入2020-05-06格式时间");
        }

        if (nameBoo == true && sexBoo == true && ageBoo == true && identityCardBoo == true && dateBoo == true && maxNumBoo == true && telBoo == true && keepMoneyBoo == true && zyBoo == true && ISBNBoo == true && bzTimeBoo == true) {
            readerService.insert(reader);
            JOptionPane.showMessageDialog(null, "添加读者信息成功！");
            this.setVisible(false);
            new ReaderFrame().setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "添加读者信息失败！");
        }
    }

    private void cancelButtonActionPerformed(ActionEvent e) {
        // 取消按钮
        this.dispose();
        new ReaderFrame().setVisible(true);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        titlelabel = new JLabel();
        namelabel = new JLabel();
        nameTextField = new JTextField();
        sexlabel = new JLabel();
        agelabel = new JLabel();
        ageTextField = new JTextField();
        sexComboBox = new JComboBox<>();
        identitylabel = new JLabel();
        identityTextField = new JTextField();
        datelabel = new JLabel();
        dateTextField = new JTextField();
        maxlabel = new JLabel();
        maxTextField = new JTextField();
        tellabel = new JLabel();
        telTextField = new JTextField();
        keepMoneyLabel = new JLabel();
        keepMoneyTextField = new JTextField();
        zjlabel = new JLabel();
        zjComboBox = new JComboBox<>();
        zyLabel = new JLabel();
        zyTextField = new JTextField();
        ISBNLabel = new JLabel();
        ISBNTextField = new JTextField();
        bzTimeLabel = new JLabel();
        bzTimeTextField = new JTextField();
        defineButton = new JButton();
        cancelButton = new JButton();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- titlelabel ----
        titlelabel.setText("\u8bfb\u8005\u4fe1\u606f\u7ba1\u7406");
        titlelabel.setHorizontalAlignment(SwingConstants.CENTER);
        titlelabel.setFont(titlelabel.getFont().deriveFont(titlelabel.getFont().getSize() + 8f));
        contentPane.add(titlelabel);
        titlelabel.setBounds(190, 0, 140, 30);

        //---- namelabel ----
        namelabel.setText("\u59d3\u540d\uff1a");
        namelabel.setHorizontalAlignment(SwingConstants.CENTER);
        namelabel.setFont(namelabel.getFont().deriveFont(namelabel.getFont().getSize() + 4f));
        contentPane.add(namelabel);
        namelabel.setBounds(20, 55, 55, 25);
        contentPane.add(nameTextField);
        nameTextField.setBounds(75, 55, 145, nameTextField.getPreferredSize().height);

        //---- sexlabel ----
        sexlabel.setText("\u6027\u522b\uff1a");
        sexlabel.setHorizontalAlignment(SwingConstants.CENTER);
        sexlabel.setFont(sexlabel.getFont().deriveFont(sexlabel.getFont().getSize() + 4f));
        contentPane.add(sexlabel);
        sexlabel.setBounds(265, 55, 55, 25);

        //---- agelabel ----
        agelabel.setText("\u5e74\u9f84\uff1a");
        agelabel.setHorizontalAlignment(SwingConstants.CENTER);
        agelabel.setFont(agelabel.getFont().deriveFont(agelabel.getFont().getSize() + 4f));
        contentPane.add(agelabel);
        agelabel.setBounds(15, 100, 60, 25);
        contentPane.add(ageTextField);
        ageTextField.setBounds(70, 95, 145, 27);

        //---- sexComboBox ----
        sexComboBox.setModel(new DefaultComboBoxModel<>(new String[]{
                "\u672a\u9009\u62e9",
                "\u7537",
                "\u5973"
        }));
        contentPane.add(sexComboBox);
        sexComboBox.setBounds(330, 50, 85, 40);

        //---- identitylabel ----
        identitylabel.setText("\u8eab\u4efd\u8bc1\u53f7\uff1a");
        identitylabel.setHorizontalAlignment(SwingConstants.CENTER);
        identitylabel.setFont(identitylabel.getFont().deriveFont(identitylabel.getFont().getSize() + 4f));
        contentPane.add(identitylabel);
        identitylabel.setBounds(5, 190, 80, 25);
        contentPane.add(identityTextField);
        identityTextField.setBounds(85, 190, 150, 27);

        //---- datelabel ----
        datelabel.setText("\u4f1a\u5458\u8bc1\u6709\u6548\u65e5\u671f\uff1a");
        datelabel.setHorizontalAlignment(SwingConstants.CENTER);
        datelabel.setFont(datelabel.getFont().deriveFont(datelabel.getFont().getSize() + 4f));
        contentPane.add(datelabel);
        datelabel.setBounds(240, 100, 130, 25);
        contentPane.add(dateTextField);
        dateTextField.setBounds(375, 100, 125, 27);

        //---- maxlabel ----
        maxlabel.setText("\u6700\u5927\u501f\u4e66\u91cf\uff1a");
        maxlabel.setHorizontalAlignment(SwingConstants.CENTER);
        maxlabel.setFont(maxlabel.getFont().deriveFont(maxlabel.getFont().getSize() + 4f));
        contentPane.add(maxlabel);
        maxlabel.setBounds(250, 145, 100, 25);
        contentPane.add(maxTextField);
        maxTextField.setBounds(350, 140, 145, 27);

        //---- tellabel ----
        tellabel.setText("\u7535\u8bdd\u53f7\u7801\uff1a");
        tellabel.setHorizontalAlignment(SwingConstants.CENTER);
        tellabel.setFont(tellabel.getFont().deriveFont(tellabel.getFont().getSize() + 4f));
        contentPane.add(tellabel);
        tellabel.setBounds(0, 275, 100, 25);
        contentPane.add(telTextField);
        telTextField.setBounds(100, 275, 150, 27);

        //---- keepMoneyLabel ----
        keepMoneyLabel.setText("\u62bc\u91d1\uff1a");
        keepMoneyLabel.setHorizontalAlignment(SwingConstants.CENTER);
        keepMoneyLabel.setFont(keepMoneyLabel.getFont().deriveFont(keepMoneyLabel.getFont().getSize() + 4f));
        contentPane.add(keepMoneyLabel);
        keepMoneyLabel.setBounds(275, 275, 60, 25);
        contentPane.add(keepMoneyTextField);
        keepMoneyTextField.setBounds(340, 275, 140, 27);

        //---- zjlabel ----
        zjlabel.setText("\u8bc1\u4ef6\u7c7b\u578b\uff1a");
        zjlabel.setHorizontalAlignment(SwingConstants.CENTER);
        zjlabel.setFont(zjlabel.getFont().deriveFont(zjlabel.getFont().getSize() + 4f));
        contentPane.add(zjlabel);
        zjlabel.setBounds(10, 145, 80, 25);

        //---- zjComboBox ----
        zjComboBox.setModel(new DefaultComboBoxModel<>(new String[]{
                "\u4e2d\u56fd\u5927\u9646\u5c45\u6c11\u8eab\u4efd\u8bc1",
                "\u6e2f\u6fb3\u53f0\u5c45\u6c11\u8eab\u4efd\u8bc1",
                "\u56fd\u5185\u5916\u901a\u884c\u62a4\u7167",
                "\u519b\u4eba\u8bc1",
                "\u5176\u4ed6"
        }));
        contentPane.add(zjComboBox);
        zjComboBox.setBounds(90, 140, 150, 30);

        //---- zyLabel ----
        zyLabel.setText("\u804c\u4e1a\uff1a");
        zyLabel.setHorizontalAlignment(SwingConstants.CENTER);
        zyLabel.setFont(zyLabel.getFont().deriveFont(zyLabel.getFont().getSize() + 4f));
        contentPane.add(zyLabel);
        zyLabel.setBounds(10, 235, 70, 25);
        contentPane.add(zyTextField);
        zyTextField.setBounds(75, 235, 160, 27);

        //---- ISBNLabel ----
        ISBNLabel.setText("\u8bfb\u8005\u7f16\u53f7\uff1a");
        ISBNLabel.setHorizontalAlignment(SwingConstants.CENTER);
        ISBNLabel.setFont(ISBNLabel.getFont().deriveFont(ISBNLabel.getFont().getSize() + 4f));
        contentPane.add(ISBNLabel);
        ISBNLabel.setBounds(255, 190, 85, 25);
        contentPane.add(ISBNTextField);
        ISBNTextField.setBounds(345, 190, 155, 27);

        //---- bzTimeLabel ----
        bzTimeLabel.setText("\u529e\u8bc1\u65e5\u671f\uff1a");
        bzTimeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        bzTimeLabel.setFont(bzTimeLabel.getFont().deriveFont(bzTimeLabel.getFont().getSize() + 4f));
        contentPane.add(bzTimeLabel);
        bzTimeLabel.setBounds(265, 235, 80, 25);
        contentPane.add(bzTimeTextField);
        bzTimeTextField.setBounds(350, 235, 120, 27);

        //---- defineButton ----
        defineButton.setText("\u786e\u5b9a");
        defineButton.setFont(defineButton.getFont().deriveFont(defineButton.getFont().getSize() + 4f));
        defineButton.addActionListener(e -> defineButtonActionPerformed(e));
        contentPane.add(defineButton);
        defineButton.setBounds(new Rectangle(new Point(135, 315), defineButton.getPreferredSize()));

        //---- cancelButton ----
        cancelButton.setText("\u53d6\u6d88");
        cancelButton.setFont(cancelButton.getFont().deriveFont(cancelButton.getFont().getSize() + 4f));
        cancelButton.addActionListener(e -> cancelButtonActionPerformed(e));
        contentPane.add(cancelButton);
        cancelButton.setBounds(295, 315, 78, 30);

        contentPane.setPreferredSize(new Dimension(540, 415));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel titlelabel;
    private JLabel namelabel;
    private JTextField nameTextField;
    private JLabel sexlabel;
    private JLabel agelabel;
    private JTextField ageTextField;
    private JComboBox<String> sexComboBox;
    private JLabel identitylabel;
    private JTextField identityTextField;
    private JLabel datelabel;
    private JTextField dateTextField;
    private JLabel maxlabel;
    private JTextField maxTextField;
    private JLabel tellabel;
    private JTextField telTextField;
    private JLabel keepMoneyLabel;
    private JTextField keepMoneyTextField;
    private JLabel zjlabel;
    private JComboBox<String> zjComboBox;
    private JLabel zyLabel;
    private JTextField zyTextField;
    private JLabel ISBNLabel;
    private JTextField ISBNTextField;
    private JLabel bzTimeLabel;
    private JTextField bzTimeTextField;
    private JButton defineButton;
    private JButton cancelButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    public static void main(String[] args) {
        new InsertReaderFrame().setVisible(true);
    }
}
