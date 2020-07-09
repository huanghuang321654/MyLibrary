/*
 * Created by JFormDesigner on Thu Jul 02 10:48:44 CST 2020
 */

package com.library.ui;

import java.awt.event.*;

import com.library.entity.BookInfor;
import com.library.entity.BookType;
import com.library.service.BookInforService;
import com.library.service.BookTypeService;
import com.library.service.impl.BookInforServiceImpl;
import com.library.service.impl.BookTypeServiceImpl;

import java.awt.*;
import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 * @author 4
 */
public class BookTypeFrame extends JFrame {
    public static BookTypeService bookTypeService = new BookTypeServiceImpl();
    public static BookInforService bookInforService = new BookInforServiceImpl();

    public BookTypeFrame() {
        initComponents();
    }

    private void insertButtonActionPerformed(ActionEvent e) {
        // 插入按钮功能
        new InsertBookTypeFrame().setVisible(true);
        this.setVisible(false);
    }

    private void deleteButtonActionPerformed(ActionEvent e) {
        // 删除按钮功能
        BookType bookType = getRowData();
        if (bookType != null) {
            List<BookInfor> list = bookInforService.queryByBookType(bookType.getId());
            if (bookTypeService.delete(bookType.getId()) >= 1) {
                JOptionPane.showMessageDialog(null, "编号为" + bookType.getId() + "的图书信息删除成功");
            } else {
                JOptionPane.showMessageDialog(null, "编号为" + bookType.getId() + "的图书信息删除失败");
            }
            for(BookInfor bookInfor:list)
            {
                bookInfor.setTypeId(999);      //如果一个书籍类型被删除，那么图书馆中属于这个书籍类型的图书书籍类型就默认设置为999
                bookInforService.update(bookInfor);
            }
        } else {
            // 如果没有选中任何行，提示用户
            JOptionPane.showMessageDialog(null, "请选中需要删除的图书类别信息");
            return;
        }
        // 当删除成功后，需要对表数据进行再次加载，实现刷新效果
        showListData();
    }

    private void updateButtonActionPerformed(ActionEvent e) {
        // 更改图书类别按钮
        BookType bookType = getRowData();
        if (bookType != null) {
            if (!bookTypeBan(bookType)) {
                JOptionPane.showMessageDialog(null, "修改失败");
            } else {
                if (bookTypeService.update(bookType) >= 1) {
                    JOptionPane.showMessageDialog(null, "编号为" + bookType.getId() + "的图书类别信息更新成功");
                } else {
                    JOptionPane.showMessageDialog(null, "编号为" + bookType.getId() + "的图书类别信息更新失败");
                }
            }
        } else {
            // 如果没有选中任何行，提示用户
            JOptionPane.showMessageDialog(null, "请选中需要更新的图书类别信息");
            return;
        }
        // 当更新成功后，需要对表数据进行再次加载，实现刷新效果
        showListData();
    }

    private void queryButtonActionPerformed(ActionEvent e) {
        // 查询按钮
        if (querycomboBox.getSelectedItem().equals("通过图书类别名查询")) {
            if (!querytextField.getText().trim().isEmpty()) {
                showListData(querytextField.getText().trim());
            } else {
                JOptionPane.showMessageDialog(null, "请输入图书类别名！");
            }

        } else if (querycomboBox.getSelectedItem().equals("通过图书类别编号查询")) {
            if (!querytextField.getText().trim().isEmpty()) {
                if (querytextField.getText().trim().matches("\\d{1,4}")) {
                    showListDataById(Integer.parseInt(querytextField.getText().trim()));
                } else {
                    JOptionPane.showMessageDialog(null, "请输入正确的图书类别编号！");
                }
            } else {
                JOptionPane.showMessageDialog(null, "请输入图书类别编号！");
            }
        } else {
            showListData();
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        BookTypelabel = new JLabel();
        scrollPane1 = new JScrollPane();
        bookTypeTable = new JTable();
        queryButton = new JButton();
        insertButton = new JButton();
        deleteButton = new JButton();
        updateButton = new JButton();
        querytextField = new JTextField();
        querycomboBox = new JComboBox<>();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- BookTypelabel ----
        BookTypelabel.setText("\u56fe\u4e66\u7c7b\u522b\u7ba1\u7406");
        BookTypelabel.setHorizontalAlignment(SwingConstants.CENTER);
        BookTypelabel.setFont(BookTypelabel.getFont().deriveFont(BookTypelabel.getFont().getSize() + 8f));
        contentPane.add(BookTypelabel);
        BookTypelabel.setBounds(245, 0, 230, 30);

        //======== scrollPane1 ========
        {

            //---- bookTypeTable ----
            bookTypeTable.setModel(new DefaultTableModel(
                new Object[][] {
                    {null, null, null, null},
                    {null, null, null, null},
                },
                new String[] {
                    null, null, null, null
                }
            ) {
                boolean[] columnEditable = new boolean[] {
                    false, true, true, true
                };
                @Override
                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return columnEditable[columnIndex];
                }
            });
            scrollPane1.setViewportView(bookTypeTable);
        }
        contentPane.add(scrollPane1);
        scrollPane1.setBounds(115, 35, 490, 250);

        //---- queryButton ----
        queryButton.setText("\u67e5\u8be2\u56fe\u4e66\u7c7b\u522b");
        queryButton.setFont(queryButton.getFont().deriveFont(queryButton.getFont().getSize() + 2f));
        queryButton.addActionListener(e -> queryButtonActionPerformed(e));
        contentPane.add(queryButton);
        queryButton.setBounds(380, 290, 110, 40);

        //---- insertButton ----
        insertButton.setText("\u6dfb\u52a0\u56fe\u4e66\u7c7b\u522b");
        insertButton.setFont(insertButton.getFont().deriveFont(insertButton.getFont().getSize() + 2f));
        insertButton.addActionListener(e -> insertButtonActionPerformed(e));
        contentPane.add(insertButton);
        insertButton.setBounds(380, 335, 110, 40);

        //---- deleteButton ----
        deleteButton.setText("\u5220\u9664\u56fe\u4e66\u7c7b\u522b");
        deleteButton.setFont(deleteButton.getFont().deriveFont(deleteButton.getFont().getSize() + 2f));
        deleteButton.addActionListener(e -> deleteButtonActionPerformed(e));
        contentPane.add(deleteButton);
        deleteButton.setBounds(500, 290, 115, 40);

        //---- updateButton ----
        updateButton.setText("\u66f4\u6539\u56fe\u4e66\u7c7b\u522b");
        updateButton.setFont(updateButton.getFont().deriveFont(updateButton.getFont().getSize() + 2f));
        updateButton.addActionListener(e -> updateButtonActionPerformed(e));
        contentPane.add(updateButton);
        updateButton.setBounds(500, 335, 115, 40);
        contentPane.add(querytextField);
        querytextField.setBounds(225, 315, 135, querytextField.getPreferredSize().height);

        //---- querycomboBox ----
        querycomboBox.setModel(new DefaultComboBoxModel<>(new String[] {
            "\u67e5\u8be2\u6240\u6709\u56fe\u4e66\u7c7b\u522b",
            "\u901a\u8fc7\u56fe\u4e66\u7c7b\u522b\u540d\u67e5\u8be2",
            "\u901a\u8fc7\u56fe\u4e66\u7c7b\u522b\u7f16\u53f7\u67e5\u8be2"
        }));
        contentPane.add(querycomboBox);
        querycomboBox.setBounds(70, 305, 140, querycomboBox.getPreferredSize().height);

        contentPane.setPreferredSize(new Dimension(720, 410));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
        showListData();


    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel BookTypelabel;
    private JScrollPane scrollPane1;
    private JTable bookTypeTable;
    private JButton queryButton;
    private JButton insertButton;
    private JButton deleteButton;
    private JButton updateButton;
    private JTextField querytextField;
    private JComboBox<String> querycomboBox;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    public void showListData() {
        //刷新展示界面
        List<BookType> bookTypes = bookTypeService.queryAll();
        String[][] datas = new String[bookTypes.size()][4];
        for (int i = 0; i < datas.length; i++) {
            datas[i][0] = bookTypes.get(i).getId().toString();
            datas[i][1] = bookTypes.get(i).getTypeName();
            datas[i][2] = bookTypes.get(i).getDays().toString();
            datas[i][3] = bookTypes.get(i).getFk().toString();
        }
        bookTypeTable.setModel(new DefaultTableModel(
                datas, new String[]{"图书类别编号", "图书类别名称", "可借天数", "迟还一天的罚款金额"}
        ){
            boolean[] columnEditable = new boolean[] {
                    false, true, true, true
            };
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return columnEditable[columnIndex];
            }
        });
        scrollPane1.setViewportView(bookTypeTable);
    }

    public void showListData(String typeName) {
        //通过图书类别名字进行查询
        List<BookType> bookTypes = bookTypeService.queryByBookTypeName(typeName);
        String[][] datas = new String[bookTypes.size()][4];
        for (int i = 0; i < datas.length; i++) {
            datas[i][0] = bookTypes.get(i).getId().toString();
            datas[i][1] = bookTypes.get(i).getTypeName();
            datas[i][2] = bookTypes.get(i).getDays().toString();
            datas[i][3] = bookTypes.get(i).getFk().toString();
        }
        bookTypeTable.setModel(new DefaultTableModel(
                datas, new String[]{"图书类别编号", "图书类别名称", "可借天数", "迟还一天的罚款金额"}
        ));
        scrollPane1.setViewportView(bookTypeTable);
    }

    public void showListDataById(Integer id) {
        //通过图书ID进行查询
        List<BookType> bookTypes = bookTypeService.queryByBookTypeId(id);
        String[][] datas = new String[bookTypes.size()][4];
        for (int i = 0; i < datas.length; i++) {
            datas[i][0] = bookTypes.get(i).getId().toString();
            datas[i][1] = bookTypes.get(i).getTypeName();
            datas[i][2] = bookTypes.get(i).getDays().toString();
            datas[i][3] = bookTypes.get(i).getFk().toString();
        }
        bookTypeTable.setModel(new DefaultTableModel(
                datas, new String[]{"图书类别编号", "图书类别名称", "可借天数", "迟还一天的罚款金额"}
        ));
        scrollPane1.setViewportView(bookTypeTable);
    }

    public BookType getRowData() {
        //获取光标对象
        BookType bookType = null;
        int index = bookTypeTable.getSelectedRow();
        // 如果下标不为-1，则选中行为数据行
        if (index != -1) {
            // 取得表格对象的数据模型
            TableModel model = bookTypeTable.getModel();
            // 在表格对象模型中，根据选中的行和列，获取相应的数据值
            String bookTypeId = model.getValueAt(index, 0).toString();
            String bookTypeName = model.getValueAt(index, 1).toString();
            String days = model.getValueAt(index, 2).toString();
            String fk = model.getValueAt(index, 3).toString();
            bookType = new BookType();
            bookType.setId(Integer.parseInt(bookTypeId));
            bookType.setTypeName(bookTypeName);
            if(days.matches("\\d{1,4}"))
            {
                bookType.setDays(Integer.parseInt(days));
            }else
            {
                JOptionPane.showMessageDialog(null,"请输入正常天数（1-9999），现在已经设置为默认值30");
                bookType.setDays(30);
            }
            if(fk.matches("\\d{1,4}.\\d{1,2}"))
            {
                bookType.setFk(Double.parseDouble(fk));
            }else
            {
                JOptionPane.showMessageDialog(null,"请输入正常罚款（0.0-9999.0），现在已经设置为默认值0.5");
                bookType.setFk(0.5);
            }
        }
        // 如果没有选中任何一行 就意味着bookType为null 可以提供给外部判断
        return bookType;
    }

    public static void main(String[] args) {
        new BookTypeFrame().setVisible(true);
    }

    public static boolean bookTypeBan(BookType bookType) {
        boolean typeNameBoo = false;
        boolean daysBoo = false;
        boolean fkBoo = false;

        if (!bookType.getTypeName().isEmpty()) {
            typeNameBoo = true;
        } else {
            JOptionPane.showMessageDialog(null, "请输入图书类型名");
        }

        if (bookType.getDays() > 0 && bookType.getDays() < 999) {
            daysBoo = true;
        } else {
            JOptionPane.showMessageDialog(null, "请输入天数为0-999");
        }

        if (bookType.getFk() >= 0) {
            fkBoo = true;
        } else {
            JOptionPane.showMessageDialog(null, "罚款不可为负数");
        }

        if (typeNameBoo == true && daysBoo == true && fkBoo == true) {
            return true;
        } else {
            return false;
        }
    }

}
