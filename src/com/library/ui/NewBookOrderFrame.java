/*
 * Created by JFormDesigner on Fri Jul 03 19:19:58 CST 2020
 */

package com.library.ui;

import java.awt.event.*;

import com.library.lib.TempleOperatorName;
import com.library.entity.Order;
import com.library.service.BookInforService;
import com.library.service.BookTypeService;
import com.library.service.OrderService;
import com.library.service.impl.BookInforServiceImpl;
import com.library.service.impl.BookTypeServiceImpl;
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
public class NewBookOrderFrame extends JFrame {
    public SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd");
    public OrderService orderService = new OrderServiceImpl();

    public NewBookOrderFrame() {
        initComponents();
    }


    private void insertbuttonActionPerformed(ActionEvent e) {
        // 新书订购信息添加按钮
        OrderService orderService = new OrderServiceImpl();
        BookInforService bookInforService = new BookInforServiceImpl();
        BookTypeService bookTypeService = new BookTypeServiceImpl();
        Order order = new Order();
        String ISBN = ISBNtextField.getText().trim();
        String typeId = typeIdtextField.getText().trim();
        String bookName = bookNametextField.getText().trim();
        String writer = writerTextField.getText().trim();
        String translator = translatorTextField.getText().trim();
        String publisher = publisherTextField.getText().trim();
        String publicationDate = publicationDateTextField.getText().trim();
        String price = priceTextField.getText().trim();
        String number = numberTextField.getText().trim();
//        String operator = operatorTextField.getText().trim();
        String zk = zkTextField.getText().trim();
        boolean ISBNBoo = false;
        boolean typeIdBoo = false;
        boolean bookNameBoo = false;
        boolean writerBoo = false;
        boolean translatorBoo = false;
        boolean publisherBoo = false;
        boolean publicationDateBoo = false;
        boolean priceBoo = false;
        boolean numberBoo = false;
        boolean zkBoo = false;
        //判断输入的ISBN是否符合标准
        if (!ISBN.isEmpty()) {
            if (ISBN.matches("\\d{3}")) {
                if(!orderService.queryByISBN(ISBN).isEmpty())
                {
                    JOptionPane.showMessageDialog(null,"订单已经存在这本书的订购信息！");
                }else
                {
                    if (!bookInforService.queryByISBN(ISBN).isEmpty()) {
                        order.setNumber(bookInforService.queryByISBN(ISBN).get(0).getQuantity());
                        order.setISBN(ISBN);
                        ISBNBoo = true;
                    } else {
                        order.setISBN(ISBN);
                        ISBNBoo = true;
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "规范错误，书籍编号请输入001类型！");
            }
        } else {
            JOptionPane.showMessageDialog(null, "请输入书籍编号！");
        }

        if (!typeId.isEmpty()) {
            if (typeId.toString().matches("\\d{1,3}")) {
                if (!bookTypeService.queryByBookTypeId(Integer.parseInt(typeId)).isEmpty()) {
                    order.setTypeId(Integer.parseInt(typeId));
                    typeIdBoo = true;
                } else {
                    JOptionPane.showMessageDialog(null, "图书馆没有这种图书类型！");
                }

            } else {
                JOptionPane.showMessageDialog(null, "规范错误，书籍类型请输入（0~999）");
            }
        } else {
            JOptionPane.showMessageDialog(null, "请输入书籍类型！");
        }

        if (!bookName.isEmpty()) {
            order.setBookName(bookName);
            bookNameBoo = true;
        } else {
            JOptionPane.showMessageDialog(null, "请输入书名！");
        }

        if (!writer.isEmpty()) {
            order.setWriter(writer);
            writerBoo = true;
        } else {
            JOptionPane.showMessageDialog(null, "请输入作者名！");
        }

        if (!translator.isEmpty()) {
            order.setTranslator(translator);
            translatorBoo = true;
        } else {
            JOptionPane.showMessageDialog(null, "请输入译者名！");
        }

        if (!publisher.isEmpty()) {
            order.setPublisher(publisher);
            publisherBoo = true;
        } else {
            JOptionPane.showMessageDialog(null, "请输入出版社名！");
        }

        if (!publicationDate.isEmpty()) {
            if (publicationDate.matches("[1-9]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|1[1-9]|2[1-9]|3[0-1])")) {
                Date date1 = null;
                try {
                    date1 = sdf.parse(publicationDate);
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }
                if (date1.getTime() < System.currentTimeMillis()) {
                    order.setPublicationDate(new java.sql.Date(date1.getTime()));
                    publicationDateBoo = true;
                } else {
                    JOptionPane.showMessageDialog(null, "时间不可超前");
                }
            } else {
                JOptionPane.showMessageDialog(null, "出版日期规范错误，请输入2020-05-06格式！");
            }
        } else {
            JOptionPane.showMessageDialog(null, "请输入出版日期！");
        }

        if (!price.isEmpty()) {
            if (price.toString().matches("\\d{1,4}|\\d{1,4}.\\d{1,4}")) {
                order.setPrice(Double.parseDouble(price));
                priceBoo = true;
            } else {
                JOptionPane.showMessageDialog(null, "规范错误，书籍价格请输入（1~9999）");
                order.setPrice(1.0);
            }
        } else {
            JOptionPane.showMessageDialog(null, "请输入书籍价格！");
            order.setPrice(1.0);
        }

        order.setDate(new java.sql.Date(System.currentTimeMillis()));

        if (!number.isEmpty()) {
            if (number.toString().matches("[1-9]\\d{0,3}")) {
                if (order.getNumber() != null) {
                    Integer add = order.getNumber() + Integer.parseInt(number);
                    order.setNumber(add);
                } else {
                    order.setNumber(Integer.parseInt(number));
                }
                numberBoo = true;
            } else {
                JOptionPane.showMessageDialog(null, "订购数量规范错误，请输入（1~9999）");
                order.setNumber(1);
            }
        } else {
            JOptionPane.showMessageDialog(null, "请输入订购数量！");
            order.setNumber(1);
        }


        order.setOperator(TempleOperatorName.templeOperator.getName());


        if (!zk.isEmpty()) {
            if (zk.matches("0.[1-9]{1,4}|1.0{0,4}")) {
                order.setZk(Double.parseDouble(zk));
                zkBoo = true;
            } else {
                JOptionPane.showMessageDialog(null, "折扣不规范，请输入（0.1~1.0）！");
                order.setZk(1.0);
            }
        } else {
            JOptionPane.showMessageDialog(null, "请输入折扣！");
            order.setZk(1.0);
        }

        order.setCheckAndAccept(0); //添加新购图书时，默认验收为0，等待管理员验收

        if (!price.isEmpty() && !number.isEmpty() && !zk.isEmpty()) {
            if (zk.matches("0.[1-9]{1,2}|1.0") && number.matches("[1-9]\\d{0,3}") && price.matches("[1-9]\\d{0,3}")) {
                order.setOrderprice(Double.parseDouble(price) * Integer.parseInt(number) * Double.parseDouble(zk));
            } else {
                order.setOrderprice(0.0);
            }
        } else {
            order.setOrderprice(0.0);
        }

        if (ISBNBoo == true && typeIdBoo == true && bookNameBoo == true && writerBoo == true && translatorBoo == true && publisherBoo == true && publicationDateBoo == true && priceBoo == true && numberBoo == true && zkBoo == true) {
            orderService.insert(order);
            JOptionPane.showMessageDialog(null, "添加新购订单成功！");
            this.setVisible(false);
            new OrderFrame().setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "添加新购订单失败！");
        }
    }

    private void cancelbuttonActionPerformed(ActionEvent e) {
        // 新书订购信息取消按钮
        this.dispose();

    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        titlelabel = new JLabel();
        insertbutton = new JButton();
        cancelbutton = new JButton();
        ISBNLabel = new JLabel();
        ISBNtextField = new JTextField();
        typeIdLabel = new JLabel();
        typeIdtextField = new JTextField();
        bookNameLabel = new JLabel();
        bookNametextField = new JTextField();
        writerLabel = new JLabel();
        writerTextField = new JTextField();
        translatorLabel = new JLabel();
        translatorTextField = new JTextField();
        publisherLabel = new JLabel();
        publisherTextField = new JTextField();
        publicationDateLabel = new JLabel();
        publicationDateTextField = new JTextField();
        priceLabel = new JLabel();
        priceTextField = new JTextField();
        numberLabel = new JLabel();
        numberTextField = new JTextField();
        zkLabel = new JLabel();
        zkTextField = new JTextField();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- titlelabel ----
        titlelabel.setText("\u65b0\u4e66\u8ba2\u8d2d\u4fe1\u606f");
        titlelabel.setHorizontalAlignment(SwingConstants.CENTER);
        titlelabel.setFont(titlelabel.getFont().deriveFont(titlelabel.getFont().getSize() + 8f));
        contentPane.add(titlelabel);
        titlelabel.setBounds(190, 5, 120, 25);

        //---- insertbutton ----
        insertbutton.setText("\u6dfb\u52a0");
        insertbutton.setFont(insertbutton.getFont().deriveFont(insertbutton.getFont().getSize() + 4f));
        insertbutton.addActionListener(e -> insertbuttonActionPerformed(e));
        contentPane.add(insertbutton);
        insertbutton.setBounds(90, 345, 90, 40);

        //---- cancelbutton ----
        cancelbutton.setText("\u53d6\u6d88");
        cancelbutton.setFont(cancelbutton.getFont().deriveFont(cancelbutton.getFont().getSize() + 4f));
        cancelbutton.addActionListener(e -> cancelbuttonActionPerformed(e));
        contentPane.add(cancelbutton);
        cancelbutton.setBounds(265, 345, 90, 40);

        //---- ISBNLabel ----
        ISBNLabel.setText("\u4e66\u7c4d\u7f16\u53f7\uff1a");
        ISBNLabel.setFont(ISBNLabel.getFont().deriveFont(ISBNLabel.getFont().getSize() + 2f));
        ISBNLabel.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(ISBNLabel);
        ISBNLabel.setBounds(new Rectangle(new Point(10, 60), ISBNLabel.getPreferredSize()));
        contentPane.add(ISBNtextField);
        ISBNtextField.setBounds(80, 55, 105, 27);

        //---- typeIdLabel ----
        typeIdLabel.setText("\u7c7b\u522b\u7f16\u53f7\uff1a");
        typeIdLabel.setFont(typeIdLabel.getFont().deriveFont(typeIdLabel.getFont().getSize() + 2f));
        typeIdLabel.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(typeIdLabel);
        typeIdLabel.setBounds(255, 60, 70, 19);
        contentPane.add(typeIdtextField);
        typeIdtextField.setBounds(325, 55, 105, 27);

        //---- bookNameLabel ----
        bookNameLabel.setText("\u4e66\u7c4d\u540d\u5b57\uff1a");
        bookNameLabel.setFont(bookNameLabel.getFont().deriveFont(bookNameLabel.getFont().getSize() + 2f));
        bookNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(bookNameLabel);
        bookNameLabel.setBounds(10, 105, 70, 19);
        contentPane.add(bookNametextField);
        bookNametextField.setBounds(75, 105, 110, 27);

        //---- writerLabel ----
        writerLabel.setText("\u4f5c    \u8005\uff1a");
        writerLabel.setFont(writerLabel.getFont().deriveFont(writerLabel.getFont().getSize() + 2f));
        writerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(writerLabel);
        writerLabel.setBounds(5, 150, 70, 20);
        contentPane.add(writerTextField);
        writerTextField.setBounds(70, 150, 115, 27);

        //---- translatorLabel ----
        translatorLabel.setText("\u8bd1    \u8005\uff1a");
        translatorLabel.setFont(translatorLabel.getFont().deriveFont(translatorLabel.getFont().getSize() + 2f));
        translatorLabel.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(translatorLabel);
        translatorLabel.setBounds(255, 150, 70, 19);
        contentPane.add(translatorTextField);
        translatorTextField.setBounds(320, 145, 110, 27);

        //---- publisherLabel ----
        publisherLabel.setText("\u51fa \u7248 \u793e\uff1a");
        publisherLabel.setFont(publisherLabel.getFont().deriveFont(publisherLabel.getFont().getSize() + 2f));
        publisherLabel.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(publisherLabel);
        publisherLabel.setBounds(255, 105, 70, 19);
        contentPane.add(publisherTextField);
        publisherTextField.setBounds(320, 105, 110, 27);

        //---- publicationDateLabel ----
        publicationDateLabel.setText("\u51fa\u7248\u65e5\u671f\uff1a");
        publicationDateLabel.setFont(publicationDateLabel.getFont().deriveFont(publicationDateLabel.getFont().getSize() + 2f));
        publicationDateLabel.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(publicationDateLabel);
        publicationDateLabel.setBounds(10, 195, 70, 20);
        contentPane.add(publicationDateTextField);
        publicationDateTextField.setBounds(70, 195, 120, 27);

        //---- priceLabel ----
        priceLabel.setText("\u4e66\u7c4d\u4ef7\u683c\uff1a");
        priceLabel.setFont(priceLabel.getFont().deriveFont(priceLabel.getFont().getSize() + 2f));
        priceLabel.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(priceLabel);
        priceLabel.setBounds(250, 200, 70, 19);
        contentPane.add(priceTextField);
        priceTextField.setBounds(315, 195, 120, 27);

        //---- numberLabel ----
        numberLabel.setText("\u8ba2\u9605\u6570\u91cf\uff1a");
        numberLabel.setFont(numberLabel.getFont().deriveFont(numberLabel.getFont().getSize() + 2f));
        numberLabel.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(numberLabel);
        numberLabel.setBounds(250, 250, 70, 19);
        contentPane.add(numberTextField);
        numberTextField.setBounds(315, 245, 125, 27);

        //---- zkLabel ----
        zkLabel.setText("\u4e66\u7c4d\u6298\u6263\uff1a");
        zkLabel.setFont(zkLabel.getFont().deriveFont(zkLabel.getFont().getSize() + 2f));
        zkLabel.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(zkLabel);
        zkLabel.setBounds(5, 245, 70, zkLabel.getPreferredSize().height);
        contentPane.add(zkTextField);
        zkTextField.setBounds(70, 240, 120, 27);

        contentPane.setPreferredSize(new Dimension(520, 455));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel titlelabel;
    private JButton insertbutton;
    private JButton cancelbutton;
    private JLabel ISBNLabel;
    private JTextField ISBNtextField;
    private JLabel typeIdLabel;
    private JTextField typeIdtextField;
    private JLabel bookNameLabel;
    private JTextField bookNametextField;
    private JLabel writerLabel;
    private JTextField writerTextField;
    private JLabel translatorLabel;
    private JTextField translatorTextField;
    private JLabel publisherLabel;
    private JTextField publisherTextField;
    private JLabel publicationDateLabel;
    private JTextField publicationDateTextField;
    private JLabel priceLabel;
    private JTextField priceTextField;
    private JLabel numberLabel;
    private JTextField numberTextField;
    private JLabel zkLabel;
    private JTextField zkTextField;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    public static void main(String[] args) {
        new NewBookOrderFrame().setVisible(true);
    }
}
