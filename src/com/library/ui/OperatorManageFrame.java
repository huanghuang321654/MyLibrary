/*
 * Created by JFormDesigner on Mon Jul 06 10:20:19 CST 2020
 */

package com.library.ui;

import java.awt.event.*;

import com.library.entity.BookInfor;
import com.library.entity.Operator;
import com.library.entity.Reader;
import com.library.lib.TempleOperatorName;
import com.library.service.OperatorService;
import com.library.service.impl.OperatorServiceImpl;

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
public class OperatorManageFrame extends JFrame {
    OperatorService operatorService = new OperatorServiceImpl();

    public OperatorManageFrame() {
        initComponents();
    }

    private void querybuttonActionPerformed(ActionEvent e) {
        // 查询按钮
        if (querycomboBox.getSelectedItem().equals("通过用户名查询")) {
            if (!queryTextField.getText().trim().isEmpty()) {
                showListDataByName(queryTextField.getText().trim());
            } else {
                JOptionPane.showMessageDialog(null, "请输入用户名！");
            }

        } else if (querycomboBox.getSelectedItem().equals("通过编号查询")) {
            if (!queryTextField.getText().trim().isEmpty()) {
                if (queryTextField.getText().trim().matches("\\d{1,3}")) {
                    showListData(Integer.parseInt(queryTextField.getText().trim()));
                } else {
                    JOptionPane.showMessageDialog(null, "编号格式不正确，请输入001类型！");
                }

            } else {
                JOptionPane.showMessageDialog(null, "请输入编号！");
            }
        } else {
            showListData();
        }
    }

    private void deletebuttonActionPerformed(ActionEvent e) {
        // 删除操作员信息按钮
        Operator operator = getRowData();
        if (operator != null) {
            if (operatorService.delete(operator.getId()) >= 1) {
                JOptionPane.showMessageDialog(null, "编号为" + operator.getId() + "的操作员信息删除成功");
            } else {
                JOptionPane.showMessageDialog(null, "编号为" + operator.getId() + "的操作员信息删除失败");
            }
        } else {
            // 如果没有选中任何行，提示用户
            JOptionPane.showMessageDialog(null, "请选中需要删除的操作员信息");
            return;
        }
        // 当删除成功后，需要对表数据进行再次加载，实现刷新效果
        showListData();
    }

    private void updatebuttonActionPerformed(ActionEvent e) {
        // 修改操作员信息按钮
        Operator operator = getRowData();
        if (operator != null) {
            if(operatorBan(operator))
            {
                if (operatorService.update(operator) >= 1) {
                    JOptionPane.showMessageDialog(null, "编号为" + operator.getId() + "的操作员信息更新成功");
                } else {
                    JOptionPane.showMessageDialog(null, "编号为" + operator.getId() + "的操作员信息更新失败");
                }
            }
        } else {
            // 如果没有选中任何行，提示用户
            JOptionPane.showMessageDialog(null, "请选中需要更新的操作员信息");
            return;
        }
        // 当更新成功后，需要对表数据进行再次加载，实现刷新效果
        showListData();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        titleLabel = new JLabel();
        scrollPane1 = new JScrollPane();
        operatorManagetable = new JTable();
        deletebutton = new JButton();
        querybutton = new JButton();
        updatebutton = new JButton();
        queryTextField = new JTextField();
        querycomboBox = new JComboBox<>();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- titleLabel ----
        titleLabel.setText("\u64cd\u4f5c\u5458\u7ba1\u7406");
        titleLabel.setFont(titleLabel.getFont().deriveFont(titleLabel.getFont().getSize() + 8f));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(titleLabel);
        titleLabel.setBounds(310, 0, 130, 30);

        //======== scrollPane1 ========
        {

            //---- operatorManagetable ----
            operatorManagetable.setModel(new DefaultTableModel(
                new Object[][] {
                    {null, null, null, null, null, null, null, null, null},
                    {null, null, null, null, null, null, null, null, null},
                },
                new String[] {
                    null, null, null, null, null, null, null, null, null
                }
            ));
            scrollPane1.setViewportView(operatorManagetable);
        }
        contentPane.add(scrollPane1);
        scrollPane1.setBounds(10, 30, 700, 235);

        //---- deletebutton ----
        deletebutton.setText("\u5220\u9664");
        deletebutton.setFont(deletebutton.getFont().deriveFont(deletebutton.getFont().getSize() + 4f));
        deletebutton.addActionListener(e -> deletebuttonActionPerformed(e));
        contentPane.add(deletebutton);
        deletebutton.setBounds(475, 290, 80, 35);

        //---- querybutton ----
        querybutton.setText("\u67e5\u8be2");
        querybutton.setFont(querybutton.getFont().deriveFont(querybutton.getFont().getSize() + 4f));
        querybutton.addActionListener(e -> querybuttonActionPerformed(e));
        contentPane.add(querybutton);
        querybutton.setBounds(375, 290, 80, 35);

        //---- updatebutton ----
        updatebutton.setText("\u4fee\u6539");
        updatebutton.setFont(updatebutton.getFont().deriveFont(updatebutton.getFont().getSize() + 4f));
        updatebutton.addActionListener(e -> updatebuttonActionPerformed(e));
        contentPane.add(updatebutton);
        updatebutton.setBounds(575, 290, 80, 35);
        contentPane.add(queryTextField);
        queryTextField.setBounds(250, 295, 85, 35);

        //---- querycomboBox ----
        querycomboBox.setModel(new DefaultComboBoxModel<>(new String[] {
            "\u67e5\u8be2\u6240\u6709\u64cd\u4f5c\u5458",
            "\u901a\u8fc7\u7528\u6237\u540d\u67e5\u8be2",
            "\u901a\u8fc7\u7f16\u53f7\u67e5\u8be2"
        }));
        contentPane.add(querycomboBox);
        querycomboBox.setBounds(100, 295, 125, querycomboBox.getPreferredSize().height);

        contentPane.setPreferredSize(new Dimension(745, 395));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
        showListData();
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel titleLabel;
    private JScrollPane scrollPane1;
    private JTable operatorManagetable;
    private JButton deletebutton;
    private JButton querybutton;
    private JButton updatebutton;
    private JTextField queryTextField;
    private JComboBox<String> querycomboBox;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    public void showListData() {
        List<Operator> operators = operatorService.queryAll();
        String[][] datas = new String[operators.size()][8];
        for (int i = 0; i < datas.length; i++) {
            datas[i][0] = operators.get(i).getId().toString();
            datas[i][1] = operators.get(i).getName();
            datas[i][2] = operators.get(i).getSex();
            datas[i][3] = operators.get(i).getAge().toString();
            datas[i][4] = operators.get(i).getIdentityCard();
            datas[i][5] = operators.get(i).getWorkDate().toString();
            datas[i][6] = operators.get(i).getTel();
            datas[i][7] = operators.get(i).getAdmin().toString();
        }
        operatorManagetable.setModel(new DefaultTableModel(
                datas, new String[]{"编号", "用户名", "性别", "年龄", "证件号", "工作时间", "电话号码", "是否为管理员"}
        ));
        scrollPane1.setViewportView(operatorManagetable);
    }

    public void showListData(Integer id) {
        List<Operator> operators = operatorService.queryByOperatorId(id);
        String[][] datas = new String[operators.size()][8];
        for (int i = 0; i < datas.length; i++) {
            datas[i][0] = operators.get(i).getId().toString();
            datas[i][1] = operators.get(i).getName();
            datas[i][2] = operators.get(i).getSex();
            datas[i][3] = operators.get(i).getAge().toString();
            datas[i][4] = operators.get(i).getIdentityCard();
            datas[i][5] = operators.get(i).getWorkDate().toString();
            datas[i][6] = operators.get(i).getTel();
            datas[i][7] = operators.get(i).getAdmin().toString();
        }
        operatorManagetable.setModel(new DefaultTableModel(
                datas, new String[]{"编号", "用户名", "性别", "年龄", "证件号", "工作时间", "电话号码", "是否为管理员"}
        ));
        scrollPane1.setViewportView(operatorManagetable);
    }

    public void showListDataByName(String name) {
        List<Operator> operators = operatorService.queryByOperatorName(name);
        String[][] datas = new String[operators.size()][9];
        for (int i = 0; i < datas.length; i++) {
            datas[i][0] = operators.get(i).getId().toString();
            datas[i][1] = operators.get(i).getName();
            datas[i][2] = operators.get(i).getSex();
            datas[i][3] = operators.get(i).getAge().toString();
            datas[i][4] = operators.get(i).getIdentityCard();
            datas[i][5] = operators.get(i).getWorkDate().toString();
            datas[i][6] = operators.get(i).getTel();
            datas[i][7] = operators.get(i).getAdmin().toString();
        }
        operatorManagetable.setModel(new DefaultTableModel(
                datas, new String[]{"编号", "用户名", "性别", "年龄", "证件号", "工作时间", "电话号码", "是否为管理员"}
        ));
        scrollPane1.setViewportView(operatorManagetable);
    }

    public Operator getRowData() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Operator operator = null;
        int index = operatorManagetable.getSelectedRow();
        // 如果下标不为-1，则选中行为数据行
        if (index != -1) {
            // 取得表格对象的数据模型
            TableModel model = operatorManagetable.getModel();
            // 在表格对象模型中，根据选中的行和列，获取相应的数据值
            String id = model.getValueAt(index, 0).toString();
            String name = model.getValueAt(index, 1).toString();
            String sex = model.getValueAt(index, 2).toString();
            String age = model.getValueAt(index, 3).toString();
            String identityCard = model.getValueAt(index, 4).toString();
            String tempWorkDate = model.getValueAt(index, 5).toString();
            String tel = model.getValueAt(index, 6).toString();
            String admin = model.getValueAt(index, 7).toString();
            operator = new Operator();

            if(id.matches("\\d{1,4}"))
            {
                operator.setId(Integer.parseInt(id));
            }else
            {
                operator.setId(TempleOperatorName.templeOperator.getId());
            }

            operator.setName(name);

            operator.setSex(sex);

            if(age.matches("[1-9]\\d{0,3}"))
            {
                operator.setAge(Integer.parseInt(age));
            }else
            {
                JOptionPane.showMessageDialog(null,"操作员年龄不规范，已经默认设置为0");
                operator.setId(0);
            }

            operator.setIdentityCard(identityCard);

            if(tempWorkDate.matches("[1-9]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|1[1-9]|2[1-9]|3[0-1])"))
            {
                Date workDate = null;
                try {
                    workDate = sdf.parse(tempWorkDate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if(workDate.getTime() < System.currentTimeMillis())
                {
                    operator.setWorkDate(new java.sql.Date(workDate.getTime()));
                }else
                {
                    JOptionPane.showMessageDialog(null,"不可超过当前时间");
                    operator.setWorkDate(new java.sql.Date(System.currentTimeMillis()));
                }
            }else
            {
                operator.setWorkDate(new java.sql.Date(System.currentTimeMillis()));
            }

            operator.setTel(tel);

            if(admin.matches("1|0"))
            {
                operator.setAdmin(Integer.parseInt(admin));
            }else
            {
                JOptionPane.showMessageDialog(null,"是否为管理员只能为0或1，已经默认设置为0");
                operator.setAdmin(0);
            }

        }

        operator.setPassword(TempleOperatorName.templeOperator.getPassword());
        // 如果没有选中任何一行 就意味着bookType为null 可以提供给外部判断
        return operator;
    }

    public static void main(String[] args) {
        new OperatorManageFrame().setVisible(true);
    }

    public static boolean operatorBan(Operator operator) {
        boolean sexBoo = false;
        boolean ageBoo = false;
        boolean identityCardBoo = false;
        boolean workdateBoo = false;
        boolean telBoo = false;
        boolean adminBoo = false;
        boolean passwordBoo = false;
        boolean flag = false;
        if (operator.getSex().equals("男") || operator.getSex().equals("女")) {
            sexBoo = true;
        } else {
            JOptionPane.showMessageDialog(null, "性别出错");
        }

        if (!operator.getAge().toString().isEmpty()) {
            if (operator.getAge().toString().matches("[1-9]\\d{0,3}")) {
                ageBoo = true;
            } else {
                JOptionPane.showMessageDialog(null, "年龄输入不规范，请输入去（1~9999）");
            }
        } else {
            JOptionPane.showMessageDialog(null, "请输入读者年龄！");
        }

        if (!operator.getIdentityCard().isEmpty()) {
            if (operator.getIdentityCard().matches("[1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]")) {
                identityCardBoo = true;
            } else {
                JOptionPane.showMessageDialog(null, "证件错误，请输入正确的证件号");
            }
        } else {
            JOptionPane.showMessageDialog(null, "请输入正确的证件号");
        }

        if (!operator.getWorkDate().toString().isEmpty()) {
            if (operator.getWorkDate().toString().matches("[1-9]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|1[1-9]|2[1-9]|3[0-1])")) {
                if (operator.getWorkDate().getTime() < System.currentTimeMillis()) {
                    workdateBoo = true;
                }
            } else {
                JOptionPane.showMessageDialog(null, "请输入2020-05-06格式时间");
            }
        } else {
            JOptionPane.showMessageDialog(null, "请输入2020-05-06格式时间");
        }

        if (!operator.getTel().isEmpty()) {
            if (operator.getTel().matches("1[34578]\\d{9}")) {
                telBoo = true;
            } else {
                JOptionPane.showMessageDialog(null, "请输入正确11位电话号码");
            }
        } else {
            JOptionPane.showMessageDialog(null, "请输入正确11位电话号码");
        }

        if (operator.getAdmin() != null) {
            if (operator.getAdmin() == 0 || operator.getAdmin() == 1) {
                adminBoo = true;
            } else {
                JOptionPane.showMessageDialog(null, "是否为管理员只能为0或1");
            }
        } else {
            JOptionPane.showMessageDialog(null, "请输入是否为管理员！");
        }

//        if (!operator.getPassword().isEmpty()) {
//            if (operator.getPassword().toString().matches("\\w{6,18}")) {
//                passwordBoo = true;
//            } else {
//                JOptionPane.showMessageDialog(null, "密码不规范，请输入6-18位字母或者数字");
//            }
//        } else {
//            JOptionPane.showMessageDialog(null, "请输入密码！");
//        }


        if (sexBoo == true && ageBoo == true && identityCardBoo == true && workdateBoo == true && telBoo == true && adminBoo == true  ) {
            flag = true;
            return flag;
        }
        return flag;
    }
}
