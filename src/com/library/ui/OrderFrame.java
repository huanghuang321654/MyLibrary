/*
 * Created by JFormDesigner on Fri Jul 03 17:56:45 CST 2020
 */

package com.library.ui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * @author 4
 */
public class OrderFrame extends JFrame {
    public OrderFrame() {
        initComponents();
    }

    private void orderButtonActionPerformed(ActionEvent e) {
        // 新书订购功能
        new NewBookOrderFrame().setVisible(true);
    }

    private void checkBookButtonActionPerformed(ActionEvent e) {
        // 验收新书功能
        new CheckNewBookFrame().setVisible(true);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        titlelabel = new JLabel();
        orderButton = new JButton();
        checkBookButton = new JButton();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- titlelabel ----
        titlelabel.setText("\u65b0\u4e66\u8ba2\u8d2d\u7ba1\u7406");
        titlelabel.setHorizontalAlignment(SwingConstants.CENTER);
        titlelabel.setFont(titlelabel.getFont().deriveFont(titlelabel.getFont().getSize() + 8f));
        contentPane.add(titlelabel);
        titlelabel.setBounds(195, 10, 130, 25);

        //---- orderButton ----
        orderButton.setText("\u65b0\u4e66\u8ba2\u8d2d");
        orderButton.setFont(orderButton.getFont().deriveFont(orderButton.getFont().getSize() + 4f));
        orderButton.addActionListener(e -> orderButtonActionPerformed(e));
        contentPane.add(orderButton);
        orderButton.setBounds(95, 145, 175, 55);

        //---- checkBookButton ----
        checkBookButton.setText("\u9a8c\u6536\u65b0\u4e66");
        checkBookButton.setFont(checkBookButton.getFont().deriveFont(checkBookButton.getFont().getSize() + 4f));
        checkBookButton.addActionListener(e -> checkBookButtonActionPerformed(e));
        contentPane.add(checkBookButton);
        checkBookButton.setBounds(300, 145, 165, 55);

        contentPane.setPreferredSize(new Dimension(525, 390));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel titlelabel;
    private JButton orderButton;
    private JButton checkBookButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    public static void main(String[] args) {
        new OrderFrame().setVisible(true);
    }
}
