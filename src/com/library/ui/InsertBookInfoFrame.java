/*
 * Created by JFormDesigner on Thu Jul 02 19:47:54 CST 2020
 */

package com.library.ui;

import com.library.entity.BookInfor;
import com.library.entity.BookType;
import com.library.service.BookInforService;
import com.library.service.BookTypeService;
import com.library.service.impl.BookInforServiceImpl;
import com.library.service.impl.BookTypeServiceImpl;

import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;

/**
 * @author 4
 */
public class InsertBookInfoFrame extends JFrame {
    JOptionPane jOptionPane = new JOptionPane();
    public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public InsertBookInfoFrame() {
        initComponents();
    }

    private void definebuttonActionPerformed(ActionEvent e) {
        // 图书信息添加确定按钮
        BookInforService bookInforService = new BookInforServiceImpl();
        BookTypeService bookTypeService = new BookTypeServiceImpl();
        BookInfor bookInfor = new BookInfor();
        String ISBN = ISBNtextField.getText().trim();
        String typeId = typetextField.getText().trim();
        String bookName = boonametextField.getText().trim();
        String writer = writertextField.getText().trim();
        String translator = transtortextField.getText().trim();
        String publisher = publishertextField.getText().trim();
        String date = datetextField.getText().trim();
        String price = pricetextField.getText().trim();
        String quantity = quantitytextField.getText().trim();
        //用于判断输入格式是否规范
        boolean ISBNBoo = false;
        boolean typeIdBoo = false;
        boolean bookNameBoo = false;
        boolean writerBoo = false;
        boolean translatorBoo = false;
        boolean publisherBoo = false;
        boolean dateBoo = false;
        boolean priceBoo = false;
        boolean quantityBoo = false;

        //判断输入的ISBN是否符合标准
        if (!ISBN.isEmpty()) {
            if (ISBN.matches("\\d{3}")) {
                if (!bookInforService.queryByISBN(ISBN).isEmpty()) {
                    JOptionPane.showMessageDialog(null, "此书已经存在，无需重新添加！");
                } else {
                    bookInfor.setISBN(ISBN);
                    ISBNBoo = true;
                }
            } else {
                JOptionPane.showMessageDialog(null, "规范错误，书籍编号请输入001类型！");
            }
        } else {
            JOptionPane.showMessageDialog(null, "请输入书籍编号！");
        }

        //判断typeId是否合理
        if (typeId != null) {
            if (typeId.matches("\\d{1,3}")) {
                if (!bookTypeService.queryByBookTypeId(Integer.parseInt(typeId)).isEmpty()) {
                    bookInfor.setTypeId(Integer.parseInt(typeId));
                    typeIdBoo = true;
                } else {
                    JOptionPane.showMessageDialog(null, "不存在这种书籍类型！");
                }
            } else {
                JOptionPane.showMessageDialog(null, "规范错误，书籍类型请输入（0~999）");
            }
        } else {
            JOptionPane.showMessageDialog(null, "请输入书籍类型！");
        }

        //判断书名是否合理
        if (!bookName.isEmpty()) {
            bookInfor.setBookName(bookName);
            bookNameBoo = true;
        } else {
            JOptionPane.showMessageDialog(null, "请输入书名！");
        }

        //判断作者是否合理
        if (!writer.isEmpty()) {
            bookInfor.setWriter(writer);
            writerBoo = true;
        } else {
            JOptionPane.showMessageDialog(null, "请输入作者名！");
        }

        if (!translator.isEmpty()) {
            bookInfor.setTranslator(translator);
            translatorBoo = true;
        } else {
            JOptionPane.showMessageDialog(null, "请输入译者名！");
        }

        if (!publisher.isEmpty()) {
            bookInfor.setPublisher(publisher);
            publisherBoo = true;
        } else {
            JOptionPane.showMessageDialog(null, "请输入出版社名！");
        }

        if (!date.isEmpty()) {
            if (date.matches("[1-9]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|1[1-9]|2[1-9]|3[0-1])")) {
                Date date1 = null;
                try {
                    date1 = sdf.parse(date);
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }
                if(date1.getTime() > System.currentTimeMillis())
                {
                    JOptionPane.showMessageDialog(null, "不可超过当前日期！");
                }else
                {
                    bookInfor.setDate(new java.sql.Date(date1.getTime()));
                    dateBoo = true;
                }
            } else {
                JOptionPane.showMessageDialog(null, "出版日期规范错误，请输入2020-05-06格式！");
            }
        } else {
            JOptionPane.showMessageDialog(null, "请输入出版日期！");
        }

        if (price != null) {
            if (price.matches("[1-9]\\d{0,3}")) {
                if(Double.parseDouble(price) >= 0)
                {
                    bookInfor.setPrice(Double.parseDouble(price));
                    priceBoo = true;
                }else
                {
                    JOptionPane.showMessageDialog(null, "书籍价格不可为负数！");
                }
            } else {
                JOptionPane.showMessageDialog(null, "规范错误，书籍价格请输入（0~9999）");
            }
        } else {
            JOptionPane.showMessageDialog(null, "请输入书籍价格！");
        }

        if (quantity != null) {
            if (quantity.toString().matches("\\d{1,4}")) {
                if(Integer.parseInt(quantity) < 0)
                {
                    JOptionPane.showMessageDialog(null, "书籍数量不可为负数！");
                }else
                {
                    bookInfor.setQuantity(Integer.parseInt(quantity));
                    quantityBoo = true;
                }
            } else {
                JOptionPane.showMessageDialog(null, "规范错误，书籍数量请输入（0~9999）");
            }
        } else {
            JOptionPane.showMessageDialog(null, "请输入书籍数量！");
        }

        if (ISBNBoo == true && typeIdBoo == true && bookNameBoo == true && writerBoo == true && translatorBoo == true && publisherBoo == true && dateBoo == true && priceBoo == true && quantityBoo == true) {
            bookInforService.insert(bookInfor);
            JOptionPane.showMessageDialog(null, "添加图书成功！");
            this.setVisible(false);
            new BookInfoFrame().setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "添加图书失败！");
        }
    }

    private void cancelbuttonActionPerformed(ActionEvent e) {
        // 取消按钮
        this.setVisible(false);
        new BookInfoFrame().setVisible(true);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        titlelabel = new JLabel();
        ISBNlabel = new JLabel();
        ISBNtextField = new JTextField();
        typelabel = new JLabel();
        typetextField = new JTextField();
        booknamelabel = new JLabel();
        writerlabel = new JLabel();
        boonametextField = new JTextField();
        writertextField = new JTextField();
        transtorlabel = new JLabel();
        transtortextField = new JTextField();
        publisherlabel = new JLabel();
        publishertextField = new JTextField();
        datelabel = new JLabel();
        pricelabel = new JLabel();
        datetextField = new JTextField();
        pricetextField = new JTextField();
        definebutton = new JButton();
        cancelbutton = new JButton();
        quantitylabel = new JLabel();
        quantitytextField = new JTextField();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- titlelabel ----
        titlelabel.setText("\u56fe\u4e66\u4fe1\u606f\u6dfb\u52a0\u529f\u80fd");
        titlelabel.setHorizontalAlignment(SwingConstants.CENTER);
        titlelabel.setFont(titlelabel.getFont().deriveFont(titlelabel.getFont().getSize() + 8f));
        contentPane.add(titlelabel);
        titlelabel.setBounds(160, 5, 205, 45);

        //---- ISBNlabel ----
        ISBNlabel.setText("\u56fe\u4e66\u7f16\u53f7\uff1a");
        ISBNlabel.setHorizontalAlignment(SwingConstants.CENTER);
        ISBNlabel.setFont(ISBNlabel.getFont().deriveFont(ISBNlabel.getFont().getSize() + 4f));
        contentPane.add(ISBNlabel);
        ISBNlabel.setBounds(45, 50, 100, 25);
        contentPane.add(ISBNtextField);
        ISBNtextField.setBounds(145, 50, 280, 30);

        //---- typelabel ----
        typelabel.setText("\u7c7b\u522b\u7f16\u53f7\uff1a");
        typelabel.setHorizontalAlignment(SwingConstants.CENTER);
        typelabel.setFont(typelabel.getFont().deriveFont(typelabel.getFont().getSize() + 4f));
        contentPane.add(typelabel);
        typelabel.setBounds(45, 90, 100, 25);
        contentPane.add(typetextField);
        typetextField.setBounds(145, 90, 280, 30);

        //---- booknamelabel ----
        booknamelabel.setText("\u56fe\u4e66\u540d\uff1a");
        booknamelabel.setHorizontalAlignment(SwingConstants.CENTER);
        booknamelabel.setFont(booknamelabel.getFont().deriveFont(booknamelabel.getFont().getSize() + 4f));
        contentPane.add(booknamelabel);
        booknamelabel.setBounds(45, 130, 100, 25);

        //---- writerlabel ----
        writerlabel.setText("\u4f5c\u8005\u540d\uff1a");
        writerlabel.setHorizontalAlignment(SwingConstants.CENTER);
        writerlabel.setFont(writerlabel.getFont().deriveFont(writerlabel.getFont().getSize() + 4f));
        contentPane.add(writerlabel);
        writerlabel.setBounds(45, 175, 100, 25);
        contentPane.add(boonametextField);
        boonametextField.setBounds(145, 130, 280, 30);
        contentPane.add(writertextField);
        writertextField.setBounds(145, 170, 280, 30);

        //---- transtorlabel ----
        transtorlabel.setText("\u8bd1\u8005\u540d\uff1a");
        transtorlabel.setHorizontalAlignment(SwingConstants.CENTER);
        transtorlabel.setFont(transtorlabel.getFont().deriveFont(transtorlabel.getFont().getSize() + 4f));
        contentPane.add(transtorlabel);
        transtorlabel.setBounds(45, 210, 100, 25);
        contentPane.add(transtortextField);
        transtortextField.setBounds(145, 210, 285, 30);

        //---- publisherlabel ----
        publisherlabel.setText("\u51fa\u7248\u793e\u540d\uff1a");
        publisherlabel.setHorizontalAlignment(SwingConstants.CENTER);
        publisherlabel.setFont(publisherlabel.getFont().deriveFont(publisherlabel.getFont().getSize() + 4f));
        contentPane.add(publisherlabel);
        publisherlabel.setBounds(45, 245, 100, 25);
        contentPane.add(publishertextField);
        publishertextField.setBounds(145, 245, 285, 25);

        //---- datelabel ----
        datelabel.setText("\u51fa\u7248\u65e5\u671f\uff1a");
        datelabel.setHorizontalAlignment(SwingConstants.CENTER);
        datelabel.setFont(datelabel.getFont().deriveFont(datelabel.getFont().getSize() + 4f));
        contentPane.add(datelabel);
        datelabel.setBounds(45, 280, 100, 25);

        //---- pricelabel ----
        pricelabel.setText("\u4e66\u672c\u4ef7\u683c\uff1a");
        pricelabel.setHorizontalAlignment(SwingConstants.CENTER);
        pricelabel.setFont(pricelabel.getFont().deriveFont(pricelabel.getFont().getSize() + 4f));
        contentPane.add(pricelabel);
        pricelabel.setBounds(45, 315, 100, 25);
        contentPane.add(datetextField);
        datetextField.setBounds(145, 280, 285, 25);
        contentPane.add(pricetextField);
        pricetextField.setBounds(145, 315, 285, 25);

        //---- definebutton ----
        definebutton.setText("\u786e\u5b9a");
        definebutton.setFont(definebutton.getFont().deriveFont(definebutton.getFont().getSize() + 4f));
        definebutton.addActionListener(e -> definebuttonActionPerformed(e));
        contentPane.add(definebutton);
        definebutton.setBounds(new Rectangle(new Point(140, 395), definebutton.getPreferredSize()));

        //---- cancelbutton ----
        cancelbutton.setText("\u53d6\u6d88");
        cancelbutton.setFont(cancelbutton.getFont().deriveFont(cancelbutton.getFont().getSize() + 4f));
        cancelbutton.addActionListener(e -> cancelbuttonActionPerformed(e));
        contentPane.add(cancelbutton);
        cancelbutton.setBounds(new Rectangle(new Point(305, 395), cancelbutton.getPreferredSize()));

        //---- quantitylabel ----
        quantitylabel.setText("\u4e66\u672c\u6570\u91cf\uff1a");
        quantitylabel.setHorizontalAlignment(SwingConstants.CENTER);
        quantitylabel.setFont(quantitylabel.getFont().deriveFont(quantitylabel.getFont().getSize() + 4f));
        contentPane.add(quantitylabel);
        quantitylabel.setBounds(45, 345, 100, 25);
        contentPane.add(quantitytextField);
        quantitytextField.setBounds(140, 345, 285, 25);

        contentPane.setPreferredSize(new Dimension(540, 470));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel titlelabel;
    private JLabel ISBNlabel;
    private JTextField ISBNtextField;
    private JLabel typelabel;
    private JTextField typetextField;
    private JLabel booknamelabel;
    private JLabel writerlabel;
    private JTextField boonametextField;
    private JTextField writertextField;
    private JLabel transtorlabel;
    private JTextField transtortextField;
    private JLabel publisherlabel;
    private JTextField publishertextField;
    private JLabel datelabel;
    private JLabel pricelabel;
    private JTextField datetextField;
    private JTextField pricetextField;
    private JButton definebutton;
    private JButton cancelbutton;
    private JLabel quantitylabel;
    private JTextField quantitytextField;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
    public static void main(String[] args) {
        new InsertBookInfoFrame().setVisible(true);
    }
}
