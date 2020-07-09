/*
 * Created by JFormDesigner on Fri Jul 03 10:38:34 CST 2020
 */

package com.library.ui;

import java.awt.event.*;

import com.library.entity.BookInfor;
import com.library.entity.BookType;
import com.library.entity.Reader;
import com.library.service.ReaderService;
import com.library.service.impl.ReaderServiceImpl;

import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.*;
import javax.swing.table.*;

/**
 * @author 4
 */
public class ReaderFrame extends JFrame {
    public ReaderService readerService = new ReaderServiceImpl();

    public ReaderFrame() {
        initComponents();
    }

    private void deletebuttonActionPerformed(ActionEvent e) {
        // 删除按钮功能
        Reader reader = getRowData();
        if (reader != null) {
            if (readerService.delete(reader.getISBN()) >= 1) {
                JOptionPane.showMessageDialog(null, "编号为" + reader.getISBN() + "的读者信息删除成功");
            } else {
                JOptionPane.showMessageDialog(null, "编号为" + reader.getISBN() + "的读者信息删除失败");
            }
        } else {
            // 如果没有选中任何行，提示用户
            JOptionPane.showMessageDialog(null, "请选中需要删除的读者信息");
            return;
        }
        // 当删除成功后，需要对表数据进行再次加载，实现刷新效果
        showListData();
    }

    private void updatebuttonActionPerformed(ActionEvent e) {
        // 修改读者信息按钮
        Reader reader = getRowData();
        if (reader != null)
            if (readerBan(reader)) {
                if (readerService.update(reader) >= 1) {
                    JOptionPane.showMessageDialog(null, "编号为" + reader.getISBN() + "的读者信息更新成功");
                } else {
                    JOptionPane.showMessageDialog(null, "编号为" + reader.getISBN() + "的读者信息更新失败");
                }
            } else {
                JOptionPane.showMessageDialog(null, "信息不规范");
            }
        else {
            // 如果没有选中任何行，提示用户
            JOptionPane.showMessageDialog(null, "请选中需要更新的读者信息");
            return;
        }
        // 当更新成功后，需要对表数据进行再次加载，实现刷新效果
        showListData();
    }

    private void querybuttonActionPerformed(ActionEvent e) {
        // 查询按钮
        if (querycomboBox.getSelectedItem().equals("通过名字查询")) {
            if (!querytextField.getText().trim().isEmpty()) {
                showListDataByName(querytextField.getText().trim());
            } else {
                JOptionPane.showMessageDialog(null, "请输入读者名字！");
            }

        } else if (querycomboBox.getSelectedItem().equals("通过ISBN查询")) {
            if (!querytextField.getText().trim().isEmpty()) {
                showListDataByISBN(querytextField.getText().trim());
            } else {
                JOptionPane.showMessageDialog(null, "请输入ISBN！");
            }
        } else {
            showListData();
        }
    }

    private void insertbuttonActionPerformed(ActionEvent e) {
        // 插入按钮
        new InsertReaderFrame().setVisible(true);
        this.setVisible(false);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        titlelabel = new JLabel();
        scrollPane1 = new JScrollPane();
        readertable = new JTable();
        querybutton = new JButton();
        insertbutton = new JButton();
        deletebutton = new JButton();
        updatebutton = new JButton();
        querytextField = new JTextField();
        querycomboBox = new JComboBox<>();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- titlelabel ----
        titlelabel.setText("\u8bfb\u8005\u4fe1\u606f\u7ba1\u7406");
        titlelabel.setHorizontalAlignment(SwingConstants.CENTER);
        titlelabel.setFont(titlelabel.getFont().deriveFont(titlelabel.getFont().getSize() + 8f));
        contentPane.add(titlelabel);
        titlelabel.setBounds(285, 5, 145, 35);

        //======== scrollPane1 ========
        {

            //---- readertable ----
            readertable.setModel(new DefaultTableModel(
                    new Object[][]{
                            {null, null, null, null, null, null, null, null, null, null, null, null},
                            {null, null, null, null, null, null, null, null, null, null, null, null},
                    },
                    new String[]{
                            null, null, null, null, null, null, null, null, null, null, null, null
                    }
            ));
            scrollPane1.setViewportView(readertable);
        }
        contentPane.add(scrollPane1);
        scrollPane1.setBounds(5, 45, 700, 245);

        //---- querybutton ----
        querybutton.setText("\u67e5\u8be2");
        querybutton.setFont(querybutton.getFont().deriveFont(querybutton.getFont().getSize() + 4f));
        querybutton.addActionListener(e -> querybuttonActionPerformed(e));
        contentPane.add(querybutton);
        querybutton.setBounds(260, 315, 90, 35);

        //---- insertbutton ----
        insertbutton.setText("\u6dfb\u52a0");
        insertbutton.setFont(insertbutton.getFont().deriveFont(insertbutton.getFont().getSize() + 4f));
        insertbutton.addActionListener(e -> insertbuttonActionPerformed(e));
        contentPane.add(insertbutton);
        insertbutton.setBounds(455, 315, 100, 35);

        //---- deletebutton ----
        deletebutton.setText("\u5220\u9664");
        deletebutton.setFont(deletebutton.getFont().deriveFont(deletebutton.getFont().getSize() + 4f));
        deletebutton.addActionListener(e -> deletebuttonActionPerformed(e));
        contentPane.add(deletebutton);
        deletebutton.setBounds(355, 315, 95, 35);

        //---- updatebutton ----
        updatebutton.setText("\u4fee\u6539");
        updatebutton.setFont(updatebutton.getFont().deriveFont(updatebutton.getFont().getSize() + 4f));
        updatebutton.addActionListener(e -> updatebuttonActionPerformed(e));
        contentPane.add(updatebutton);
        updatebutton.setBounds(560, 315, 100, 35);
        contentPane.add(querytextField);
        querytextField.setBounds(150, 320, 105, querytextField.getPreferredSize().height);

        //---- querycomboBox ----
        querycomboBox.setModel(new DefaultComboBoxModel<>(new String[]{
                "\u67e5\u8be2\u6240\u6709\u8bfb\u8005",
                "\u901a\u8fc7\u540d\u5b57\u67e5\u8be2",
                "\u901a\u8fc7ISBN\u67e5\u8be2"
        }));
        contentPane.add(querycomboBox);
        querycomboBox.setBounds(10, 310, querycomboBox.getPreferredSize().width, 50);

        contentPane.setPreferredSize(new Dimension(720, 435));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
        showListData();
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel titlelabel;
    private JScrollPane scrollPane1;
    private JTable readertable;
    private JButton querybutton;
    private JButton insertbutton;
    private JButton deletebutton;
    private JButton updatebutton;
    private JTextField querytextField;
    private JComboBox<String> querycomboBox;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    public void showListData() {
        List<Reader> readers = readerService.queryAll();
        String[][] datas = new String[readers.size()][12];
        for (int i = 0; i < datas.length; i++) {
            datas[i][0] = readers.get(i).getName();
            datas[i][1] = readers.get(i).getSex();
            datas[i][2] = readers.get(i).getAge().toString();
            datas[i][3] = readers.get(i).getIdentityCard();
            datas[i][4] = readers.get(i).getDate().toString();
            datas[i][5] = readers.get(i).getMaxNum().toString();
            datas[i][6] = readers.get(i).getTel();
            datas[i][7] = readers.get(i).getKeepMoney().toString();
            datas[i][8] = readers.get(i).getZj().toString();
            datas[i][9] = readers.get(i).getZy();
            datas[i][10] = readers.get(i).getISBN();
            datas[i][11] = readers.get(i).getBztime().toString();
        }
        readertable.setModel(new DefaultTableModel(
                datas, new String[]{"姓名", "性别", "年龄", "身份证号", "会员有效期", "最大借书量", "电话号码", "押金", "证件类型", "职业", "读者编号", "办证日期"}
        ));
        scrollPane1.setViewportView(readertable);
    }

    public void showListDataByName(String name) {
        List<Reader> readers = readerService.queryByReaderName(name);
        String[][] datas = new String[readers.size()][12];
        for (int i = 0; i < datas.length; i++) {
            datas[i][0] = readers.get(i).getName();
            datas[i][1] = readers.get(i).getSex();
            datas[i][2] = readers.get(i).getAge().toString();
            datas[i][3] = readers.get(i).getIdentityCard();
            datas[i][4] = readers.get(i).getDate().toString();
            datas[i][5] = readers.get(i).getMaxNum().toString();
            datas[i][6] = readers.get(i).getTel();
            datas[i][7] = readers.get(i).getKeepMoney().toString();
            datas[i][8] = readers.get(i).getZj().toString();
            datas[i][9] = readers.get(i).getZy();
            datas[i][10] = readers.get(i).getISBN();
            datas[i][11] = readers.get(i).getBztime().toString();
        }
        readertable.setModel(new DefaultTableModel(
                datas, new String[]{"姓名", "性别", "年龄", "身份证号", "会员有效期", "最大借书量", "电话号码", "押金", "证件类型", "职业", "读者编号", "办证日期"}
        ));
        scrollPane1.setViewportView(readertable);
    }

    public void showListDataByISBN(String ISBN) {
        List<Reader> readers = readerService.queryByReaderISBN(ISBN);
        String[][] datas = new String[readers.size()][12];
        for (int i = 0; i < datas.length; i++) {
            datas[i][0] = readers.get(i).getName();
            datas[i][1] = readers.get(i).getSex();
            datas[i][2] = readers.get(i).getAge().toString();
            datas[i][3] = readers.get(i).getIdentityCard();
            datas[i][4] = readers.get(i).getDate().toString();
            datas[i][5] = readers.get(i).getMaxNum().toString();
            datas[i][6] = readers.get(i).getTel();
            datas[i][7] = readers.get(i).getKeepMoney().toString();
            datas[i][8] = readers.get(i).getZj().toString();
            datas[i][9] = readers.get(i).getZy();
            datas[i][10] = readers.get(i).getISBN();
            datas[i][11] = readers.get(i).getBztime().toString();
        }
        readertable.setModel(new DefaultTableModel(
                datas, new String[]{"姓名", "性别", "年龄", "身份证号", "会员有效期", "最大借书量", "电话号码", "押金", "证件类型", "职业", "读者编号", "办证日期"}
        ));
        scrollPane1.setViewportView(readertable);
    }

    public Reader getRowData() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Reader reader = null;
        int index = readertable.getSelectedRow();
        // 如果下标不为-1，则选中行为数据行
        if (index != -1) {
            // 取得表格对象的数据模型
            TableModel model = readertable.getModel();
            // 在表格对象模型中，根据选中的行和列，获取相应的数据值
            String name = model.getValueAt(index, 0).toString();
            String sex = model.getValueAt(index, 1).toString();
            String age = model.getValueAt(index, 2).toString();
            String identityCard = model.getValueAt(index, 3).toString();
            String tempDate = model.getValueAt(index, 4).toString();
            String maxNum = model.getValueAt(index, 5).toString();
            String tel = model.getValueAt(index, 6).toString();
            String keepMoney = model.getValueAt(index, 7).toString();
            String zj = model.getValueAt(index, 8).toString();
            String zy = model.getValueAt(index, 9).toString();
            String ISBN = model.getValueAt(index, 10).toString();
            String tempBzTime = model.getValueAt(index, 11).toString();
            reader = new Reader();
            //设置选中的读者名字
            reader.setName(name);
            //设置选中的读者性别
            reader.setSex(sex);

            if (age.matches("[1-9]\\d{0,3}")) {
                reader.setAge(Integer.parseInt(age));
            } else {
                reader.setAge(999);
            }

            reader.setIdentityCard(identityCard);

            if (tempDate.matches("[1-9]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|1[1-9]|2[1-9]|3[0-1])")) {
                Date date = null;
                try {
                    date = sdf.parse(tempDate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                reader.setDate(new java.sql.Date(date.getTime()));
            } else {
                JOptionPane.showMessageDialog(null,"会员证到期时间格式出错，默认设置为当前时间");
                reader.setDate(new java.sql.Date(System.currentTimeMillis()));
            }

            if (maxNum.matches("[1-9]\\d{0,3}")) {
                reader.setMaxNum(Integer.parseInt(maxNum));
            } else {
                JOptionPane.showMessageDialog(null,"最大借书量出错，默认设置为999");
                reader.setMaxNum(999);
            }

            reader.setTel(tel);

            if (keepMoney.matches("(\\d{1,4}.\\d{1,2})")) {
                reader.setKeepMoney(Double.parseDouble(keepMoney));
            } else {
                JOptionPane.showMessageDialog(null,"押金格式出错，默认设置为999");
                reader.setKeepMoney(999.0);
            }

            if (zj.matches("([0-5]{1})")) {
                reader.setZj(Integer.parseInt(zj));
            } else {
                reader.setZj(0);
            }

            reader.setZy(zy);

            reader.setISBN(ISBN);

            if (tempBzTime.matches("[1-9]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|1[1-9]|2[1-9]|3[0-1])")) {
                Date bzTime = null;
                try {
                    bzTime = sdf.parse(tempBzTime);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if (bzTime.getTime() < System.currentTimeMillis()) {
                    reader.setBztime(new java.sql.Date(bzTime.getTime()));
                } else {
                    JOptionPane.showMessageDialog(null,"办证时间不可超前，默认设置为当前时间");
                    reader.setBztime(new java.sql.Date(System.currentTimeMillis()));
                }
            } else {
                JOptionPane.showMessageDialog(null,"办证时间格式出错，默认设置为当前时间");
                reader.setBztime(new java.sql.Date(System.currentTimeMillis()));
            }
        }
        // 如果没有选中任何一行 就意味着bookType为null 可以提供给外部判断
        return reader;
    }

    public static void main(String[] args) {
        new ReaderFrame().setVisible(true);
    }

    public static boolean readerBan(Reader reader) {
        boolean sexBoo = false;
        boolean ageBoo = false;
        boolean identityCardBoo = false;
        boolean dateBoo = false;
        boolean maxNumBoo = false;
        boolean telBoo = false;
        boolean keepMoneyBoo = false;
        boolean zjBoo = false;
        boolean zyBoo = false;
        boolean bzTimeBoo = false;
        boolean flag = false;
        if (reader.getSex().equals("男") || reader.getSex().equals("女")) {
            sexBoo = true;
        } else {
            JOptionPane.showMessageDialog(null, "性别出错");
        }

        if (!reader.getAge().toString().isEmpty()) {
            if (reader.getAge().toString().matches("[1-9]\\d{0,3}")) {
                ageBoo = true;
            } else {
                JOptionPane.showMessageDialog(null, "年龄输入不规范，请输入去（1~9999）");
            }
        } else {
            JOptionPane.showMessageDialog(null, "请输入读者年龄！");
        }

        if (!reader.getIdentityCard().isEmpty()) {
            if (reader.getIdentityCard().matches("[1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]")) {
                identityCardBoo = true;
            } else {
                JOptionPane.showMessageDialog(null, "证件错误，请输入正确的证件号");
            }
        } else {
            JOptionPane.showMessageDialog(null, "请输入正确的证件号");
        }

        if (!reader.getDate().toString().isEmpty()) {
            if (reader.getDate().toString().matches("[1-9]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|1[1-9]|2[1-9]|3[0-1])")) {
                dateBoo = true;
            } else {
                JOptionPane.showMessageDialog(null, "请输入2020-05-06格式时间");
            }
        } else {
            JOptionPane.showMessageDialog(null, "请输入2020-05-06格式时间");
        }

        if (reader.getMaxNum() != null) {
            if (reader.getMaxNum().toString().matches("[1-9]\\d{0,3}")) {
                maxNumBoo = true;
            } else {
                JOptionPane.showMessageDialog(null, "最大借阅量不规范，请输入（1-9999）");
            }
        } else {
            JOptionPane.showMessageDialog(null, "请输入最大借阅量");
        }

        if (!reader.getTel().isEmpty()) {
            if (reader.getTel().matches("1[34578]\\d{9}")) {
                telBoo = true;
            } else {
                JOptionPane.showMessageDialog(null, "请输入正确11位电话号码");
            }
        } else {
            JOptionPane.showMessageDialog(null, "请输入正确11位电话号码");
        }

        if (reader.getKeepMoney() != null) {
            if (reader.getKeepMoney().toString().matches("(\\d{1,4}.\\d{1,2})")) {
                keepMoneyBoo = true;
            } else {
                JOptionPane.showMessageDialog(null, "押金格式不规范，请输入（0~9999）");
            }
        } else {
            JOptionPane.showMessageDialog(null, "请输入押金！");
        }

        if (reader.getZj() != null) {
            if (reader.getZj().toString().matches("([0-5]{1})")) {
                zjBoo = true;
            } else {
                JOptionPane.showMessageDialog(null, "证件类型不规范，请输入（0-5）");
            }
        } else {
            JOptionPane.showMessageDialog(null, "请输入证件类型！");
        }

        if (!reader.getZy().isEmpty()) {
            zyBoo = true;
        } else {
            JOptionPane.showMessageDialog(null, "请输入读者职业！");
        }

        if (!reader.getBztime().toString().isEmpty()) {
            if (reader.getBztime().toString().matches("[1-9]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|1[1-9]|2[1-9]|3[0-1])")) {
                if (reader.getBztime().getTime() < System.currentTimeMillis()) {
                    bzTimeBoo = true;
                } else {
                    JOptionPane.showMessageDialog(null, "办证日期不可超前");
                }
            } else {
                JOptionPane.showMessageDialog(null, "请输入2020-05-06格式时间");
            }
        } else {
            JOptionPane.showMessageDialog(null, "请输入2020-05-06格式时间");
        }

        if (sexBoo == true && ageBoo == true && identityCardBoo == true && dateBoo == true && maxNumBoo == true && telBoo == true && keepMoneyBoo == true && zyBoo == true && zjBoo == true && bzTimeBoo == true) {
            flag = true;
            return flag;
        }
        return flag;
    }
}
