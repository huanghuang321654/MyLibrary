/*
 * Created by JFormDesigner on Fri Jul 03 23:30:53 CST 2020
 */

package com.library.ui;

import java.awt.event.*;

import com.library.entity.BookInfor;
import com.library.entity.Order;
import com.library.entity.Reader;
import com.library.service.BookInforService;
import com.library.service.OrderService;
import com.library.service.impl.BookInforServiceImpl;
import com.library.service.impl.OrderServiceImpl;

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
public class CheckNewBookFrame extends JFrame {
    public OrderService orderService = new OrderServiceImpl();

    public CheckNewBookFrame() {
        initComponents();
    }

    private void querybuttonActionPerformed(ActionEvent e) {
        // 查询按钮
        if (querytextField.getText().trim().isEmpty()) {
            showListData();
        } else {
            showListData(querytextField.getText().trim());
        }
    }

    private void checkedbuttonActionPerformed(ActionEvent e) {
        // 验收功能，如果验收成功则相应图书会增加到图书信息表，如果是旧书则增加图书数量
        BookInforService bookInforService = new BookInforServiceImpl();
        Order order = getRowData();
        if (order != null) {
            if (!bookInforService.queryByISBN(order.getISBN()).isEmpty()) {
                order.setCheckAndAccept(1);
                orderService.update(order);
                bookInforService.update(new BookInfor(order.getISBN(), order.getTypeId(), order.getBookName(), order.getWriter(), order.getTranslator(), order.getPublisher(), order.getPublicationDate(), order.getPrice(), order.getNumber()));
                JOptionPane.showMessageDialog(null, "验收成功，图书数量已增加！");
            } else {
                order.setCheckAndAccept(1);
                orderService.update(order);
                bookInforService.insert(new BookInfor(order.getISBN(), order.getTypeId(), order.getBookName(), order.getWriter(), order.getTranslator(), order.getPublisher(), order.getPublicationDate(), order.getPrice(), order.getNumber()));
                JOptionPane.showMessageDialog(null, "验收成功，已加入图书信息表！");
            }
        } else {
            // 如果没有选中任何行，提示用户
            JOptionPane.showMessageDialog(null, "请选中需要验收的新书订购信息");
            return;
        }
        // 当更新成功后，需要对表数据进行再次加载，实现刷新效果
        showListData();
    }

    private void deletebuttonActionPerformed(ActionEvent e) {
        // 删除新购订单按钮
        Order order = getRowData();
        if (order != null) {
            if (orderService.delete(order.getISBN()) >= 1) {
                JOptionPane.showMessageDialog(null, "编号为" + order.getISBN() + "的新购订单删除成功");
            } else {
                JOptionPane.showMessageDialog(null, "编号为" + order.getISBN() + "的新购订单删除失败");
            }
        } else {
            // 如果没有选中任何行，提示用户
            JOptionPane.showMessageDialog(null, "请选中需要删除的新购订单");
            return;
        }
        // 当删除成功后，需要对表数据进行再次加载，实现刷新效果
        showListData();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        titlelabel = new JLabel();
        scrollPane1 = new JScrollPane();
        newBookOrderTable = new JTable();
        querybutton = new JButton();
        checkedbutton = new JButton();
        querylabel = new JLabel();
        querytextField = new JTextField();
        deletebutton = new JButton();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- titlelabel ----
        titlelabel.setText("\u9a8c\u6536\u65b0\u4e66");
        titlelabel.setFont(titlelabel.getFont().deriveFont(titlelabel.getFont().getSize() + 8f));
        titlelabel.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(titlelabel);
        titlelabel.setBounds(250, 5, 160, titlelabel.getPreferredSize().height);

        //======== scrollPane1 ========
        {

            //---- newBookOrderTable ----
            newBookOrderTable.setModel(new DefaultTableModel(
                    new Object[][]{
                            {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                            {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                    },
                    new String[]{
                            null, null, null, null, null, null, null, null, null, null, null, null, null, null
                    }
            ));
            scrollPane1.setViewportView(newBookOrderTable);
        }
        contentPane.add(scrollPane1);
        scrollPane1.setBounds(5, 40, 755, 245);

        //---- querybutton ----
        querybutton.setText("\u67e5\u8be2");
        querybutton.setFont(querybutton.getFont().deriveFont(querybutton.getFont().getSize() + 3f));
        querybutton.addActionListener(e -> querybuttonActionPerformed(e));
        contentPane.add(querybutton);
        querybutton.setBounds(400, 310, 90, 40);

        //---- checkedbutton ----
        checkedbutton.setText("\u9a8c\u6536");
        checkedbutton.setFont(checkedbutton.getFont().deriveFont(checkedbutton.getFont().getSize() + 3f));
        checkedbutton.addActionListener(e -> checkedbuttonActionPerformed(e));
        contentPane.add(checkedbutton);
        checkedbutton.setBounds(505, 310, 90, 40);

        //---- querylabel ----
        querylabel.setText("\u4e66\u7c4d\u7f16\u53f7:");
        querylabel.setFont(querylabel.getFont().deriveFont(querylabel.getFont().getSize() + 3f));
        contentPane.add(querylabel);
        querylabel.setBounds(150, 315, 70, 30);
        contentPane.add(querytextField);
        querytextField.setBounds(230, 315, 150, 30);

        //---- deletebutton ----
        deletebutton.setText("\u5220\u9664");
        deletebutton.setFont(deletebutton.getFont().deriveFont(deletebutton.getFont().getSize() + 3f));
        deletebutton.addActionListener(e -> deletebuttonActionPerformed(e));
        contentPane.add(deletebutton);
        deletebutton.setBounds(610, 310, 90, 40);

        contentPane.setPreferredSize(new Dimension(765, 415));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
        showListData();
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel titlelabel;
    private JScrollPane scrollPane1;
    private JTable newBookOrderTable;
    private JButton querybutton;
    private JButton checkedbutton;
    private JLabel querylabel;
    private JTextField querytextField;
    private JButton deletebutton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    public void showListData() {
        //刷新展示界面
        List<Order> orders = orderService.queryAll();
        String[][] datas = new String[orders.size()][14];
        for (int i = 0; i < datas.length; i++) {
            datas[i][0] = orders.get(i).getISBN();
            datas[i][1] = orders.get(i).getTypeId().toString();
            datas[i][2] = orders.get(i).getBookName();
            datas[i][3] = orders.get(i).getWriter();
            datas[i][4] = orders.get(i).getTranslator();
            datas[i][5] = orders.get(i).getPublisher();
            datas[i][6] = orders.get(i).getPublicationDate().toString();
            datas[i][7] = orders.get(i).getPrice().toString();
            datas[i][8] = orders.get(i).getDate().toString();
            datas[i][9] = orders.get(i).getNumber().toString();
            datas[i][10] = orders.get(i).getOperator();
            datas[i][11] = orders.get(i).getCheckAndAccept().toString();
            datas[i][12] = orders.get(i).getZk().toString();
            datas[i][13] = orders.get(i).getOrderprice().toString();

        }
        newBookOrderTable.setModel(new DefaultTableModel(
                datas, new String[]{"书籍编号", "书籍类型", "书籍名字", "作者", "译者", "出版社", "出版日期", "书籍价格", "订阅日期", "订购数量", "操作员", "是否验收", "书籍折扣", "订单总额"}
        ));
        scrollPane1.setViewportView(newBookOrderTable);
    }

    public void showListData(String ISBN) {
        //通过ISBN查询
        List<Order> orders = orderService.queryByISBN(ISBN);
        String[][] datas = new String[orders.size()][14];
        for (int i = 0; i < datas.length; i++) {
            datas[i][0] = orders.get(i).getISBN();
            datas[i][1] = orders.get(i).getTypeId().toString();
            datas[i][2] = orders.get(i).getBookName();
            datas[i][3] = orders.get(i).getWriter();
            datas[i][4] = orders.get(i).getTranslator();
            datas[i][5] = orders.get(i).getPublisher();
            datas[i][6] = orders.get(i).getPublicationDate().toString();
            datas[i][7] = orders.get(i).getPrice().toString();
            datas[i][8] = orders.get(i).getDate().toString();
            datas[i][9] = orders.get(i).getNumber().toString();
            datas[i][10] = orders.get(i).getOperator();
            datas[i][11] = orders.get(i).getCheckAndAccept().toString();
            datas[i][12] = orders.get(i).getZk().toString();
            datas[i][13] = orders.get(i).getOrderprice().toString();

        }
        newBookOrderTable.setModel(new DefaultTableModel(
                datas, new String[]{"书籍编号", "书籍类型", "书籍名字", "作者", "译者", "出版社", "出版日期", "书籍价格", "订阅日期", "订购数量", "操作员", "是否验收", "书籍折扣", "订单总额"}
        ));
        scrollPane1.setViewportView(newBookOrderTable);
    }

    public Order getRowData() {
        //通过光标进行查询
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Order order = null;
        int index = newBookOrderTable.getSelectedRow();
        // 如果下标不为-1，则选中行为数据行
        if (index != -1) {
            // 取得表格对象的数据模型
            TableModel model = newBookOrderTable.getModel();
            // 在表格对象模型中，根据选中的行和列，获取相应的数据值
            String ISBN = model.getValueAt(index, 0).toString();
            Integer typeId = Integer.parseInt(model.getValueAt(index, 1).toString());
            String bookName = model.getValueAt(index, 2).toString();
            String writer = model.getValueAt(index, 3).toString();
            String translator = model.getValueAt(index, 4).toString();
            String publisher = model.getValueAt(index, 5).toString();
            Date publictionDate = null;
            try {
                publictionDate = sdf.parse(model.getValueAt(index, 6).toString());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Double price = Double.parseDouble(model.getValueAt(index, 7).toString());
            Date date = null;
            try {
                date = sdf.parse(model.getValueAt(index, 8).toString());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Integer number = Integer.parseInt(model.getValueAt(index, 9).toString());
            String operator = model.getValueAt(index, 10).toString();
            Integer checkAndAccept = Integer.parseInt(model.getValueAt(index, 11).toString());
            Double zk = Double.parseDouble(model.getValueAt(index, 12).toString());
            Double orderPrice = Double.parseDouble(model.getValueAt(index, 13).toString());
            order = new Order();
            order.setISBN(ISBN);
            order.setTypeId(typeId);
            order.setBookName(bookName);
            order.setWriter(writer);
            order.setTranslator(translator);
            order.setPublisher(publisher);
            order.setPublicationDate(new java.sql.Date(publictionDate.getTime()));
            order.setPrice(price);
            order.setDate(new java.sql.Date(date.getTime()));
            order.setNumber(number);
            order.setOperator(operator);
            order.setCheckAndAccept(checkAndAccept);
            order.setZk(zk);
            order.setOrderprice(orderPrice);
        }
        // 如果没有选中任何一行 就意味着bookType为null 可以提供给外部判断
        return order;
    }

    public static void main(String[] args) {
        new CheckNewBookFrame().setVisible(true);
    }
}
