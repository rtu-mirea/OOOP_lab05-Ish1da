package com.company;

import java.awt.*;
import java.awt.event.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.*;
import java.io.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class MainWindow extends JFrame{
    private JLabel lab1 = new JLabel("Название товара");
    private JLabel lab2 = new JLabel("Цена");
    private JLabel lab3 = new JLabel("Количество");
    private JLabel lab4 = new JLabel("Тип предложения(0 - покупка; 1 - продажа)");
    private JTextField tex1 = new JTextField("", 5);
    private JTextField tex2 = new JTextField("", 5);
    private JTextField tex3 = new JTextField("", 5);
    private JTextField tex4 = new JTextField("", 5);
    private JButton but1 = new JButton("Добавить предложение");
    private JButton but2 = new JButton("Провести торги");
    private JButton but3 = new JButton("Выйти из учётной записи");
    private JButton but4 = new JButton("Загрузить данные");
    private JButton but5 = new JButton("Сохранить данные");
    private JButton but6 = new JButton("Изменить учётные записи");
    private String[] items = {
            "Завершенные",
            "Покупка",
            "Продажа"
    };
    private JComboBox comboBox = new JComboBox(items);
    private JTextArea tax = new JTextArea(1000, 1000);
    JScrollPane scroll = new JScrollPane (tax, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);


    public MainWindow(){
        super("Main");
        tax.setSize(300, 100);
        tax.setPreferredSize(tax.getSize());
        this.setBounds(500, 500, 1150, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        Container con1 = this.getContentPane();
        con1.setLayout(null);
        Container con = new JPanel();
        con.setLayout(new GridLayout(7, 2, 2, 2));

        con.add(lab1);
        con.add(tex1);
        con.add(lab2);
        con.add(tex2);
        con.add(lab3);
        con.add(tex3);
        con.add(lab4);
        con.add(tex4);
        but1.addActionListener(new ButtonEventListener());
        but2.addActionListener(new ButtonEventListener());
        but3.addActionListener(new ButtonEventListener());
        but4.addActionListener(new ButtonEventListener());
        but5.addActionListener(new ButtonEventListener());
        but6.addActionListener(new ButtonEventListener());
        comboBox.addActionListener(new ButtonEventListener());
        con.add(but1);
        con.add(but2);
        con.add(but3);
        con.add(but4);
        con.add(but5);
        con.add(but6);
        con1.add(con);
        con.setBounds(5,5,600, 230);
        con1.add(comboBox);
        comboBox.setBounds(610, 5, 150, 25);
//        con1.add(tax);
//        tax.setBounds(610, 35, 200, 200);
        con1.add(scroll);
        scroll.setBounds(610, 35, 500, 200);
        scroll.setVisible(true);

//        pack();
//        setResizable(false);
    }

    class ButtonEventListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            if (e.getSource() == but1) {
                if (Main.ts.getCurrentUser().getName() == "admin") {
                    JOptionPane.showMessageDialog(null, "Администратор не может участвовать в торгах", "Сообщение",JOptionPane.PLAIN_MESSAGE);
                } else {
                    String pt = tex1.getText();
                    int pe = Integer.parseInt(tex2.getText());
                    int c = Integer.parseInt(tex3.getText());
                    int t = Integer.parseInt(tex4.getText());
                    if (t == 0) {
                        Main.ts.buyerRequests.add(new Request(Main.ts.getCurrentUser(), pt, pe, c, t));
                    } else {
                        Main.ts.sellerRequests.add(new Request(Main.ts.getCurrentUser(), pt, pe, c, t));
                    }
                    JOptionPane.showMessageDialog(null, "Заявка успешно добавлена", "Сообщение",JOptionPane.PLAIN_MESSAGE);
                }
            };

            if (e.getSource() == but2) {
                if (Main.ts.getCurrentUser().getName().equals("admin")) {
                    Main.done.addAll(Main.ts.processRequests());
                    JOptionPane.showMessageDialog(null, "Торги завершены", "Сообщение",JOptionPane.PLAIN_MESSAGE);
                    e.setSource(comboBox);
                } else {
                    JOptionPane.showMessageDialog(null, "Торги может провести только администратор", "Сообщение",JOptionPane.PLAIN_MESSAGE);
                }

            };
            if (e.getSource() == but3) {
                Main.ts.setCurrentUser(null);
                Main.log.setVisible(true);
                Main.mw.setVisible(false);
            };
            if (e.getSource() == but4) {
                if (Main.ts.getCurrentUser().getName().equals("admin")) {
                    try {
                        JFileChooser fc = new JFileChooser();
                        if ( fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION ) {
                            try {
                                FileInputStream fileInputStream = new FileInputStream(fc.getSelectedFile());
                                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

                                TradeSystem ts = (TradeSystem) objectInputStream.readObject();
                                Main.ts = ts;
                            }
                            catch ( IOException ex ) {
                                System.out.println("Всё погибло!");
                            }
                        }
//                        FileInputStream fileInputStream = new FileInputStream("save.ser");
//                        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
//
//                        TradeSystem ts = (TradeSystem) objectInputStream.readObject();
//                        Main.ts = ts;
                    } catch (Throwable t) { }
                } else {
                    JOptionPane.showMessageDialog(null, "Изменить состояние системы может только администратор", "Сообщение",JOptionPane.PLAIN_MESSAGE);
                }
            };
            if (e.getSource() == but5) {
                if (Main.ts.getCurrentUser().getName().equals("admin")) {
                try {
                    System.out.println("1!");
//                    FileOutputStream outputStream = new FileOutputStream("save.ser");
//                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

//                    FileNameExtensionFilter filter = new FileNameExtensionFilter("*.TXT");
                    JFileChooser fc = new JFileChooser();
//                    fc.setFileFilter(filter);
                    System.out.println("2!");
                    if ( fc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION ) {
                        try {
                            System.out.println("1!");
                            FileOutputStream outputStream = new FileOutputStream(fc.getSelectedFile());
                            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
                            objectOutputStream.writeObject(Main.ts);

                            //
                            objectOutputStream.close();
                        }
                        catch ( IOException ex ) {
                            System.out.println("Всё погибло!");
                        }
                    }
                    //
//                    objectOutputStream.writeObject(Main.ts);
//
//                    //
//                    objectOutputStream.close();
                }
                catch (Throwable t) {System.out.println(t.getCause());}
                } else {
                    JOptionPane.showMessageDialog(null, "Изменить состояние системы может только администратор", "Сообщение",JOptionPane.PLAIN_MESSAGE);
                }
            };
            if (e.getSource() == comboBox) {
                int num = comboBox.getSelectedIndex();
                String result = "";
                switch (num) {//check for a match
                    case 0:
                        for (Request rq : Main.done) {
                            if (rq.getType() == 0) {
                                result += "Покупка " + "\r\n";
                            } else {
                                result += "Продажа " + "\r\n";
                            }

                            result += "товар:\t" + rq.getProduct() + " цена:\t" + rq.getPrice() + " исполнитель:\t" + rq.getRequester().getName() + "\r\n";
                        }
                        break;
                    case 1:
                        for (Request rq : Main.ts.buyerRequests) {
                            result += "товар:\t" + rq.getProduct() + "\tцена:\t" + rq.getPrice() + "\tкол-во:\t" + rq.getCount() + "\tпокупатель:\t" + rq.getRequester().getName() + "\r\n";
                        }
                        break;
                    case 2:
                        for (Request rq : Main.ts.sellerRequests) {
                            result += "товар:\t" + rq.getProduct() + "\tцена:\t" + rq.getPrice() + "\tкол-во:\t" + rq.getCount() + "\tпродавец:\t" + rq.getRequester().getName() + "\r\n";
                        }
                        break;

                    default:
                        result = "ERROR";
                        break;
                }
                tax.setText(result);
            };
            if (e.getSource() == but6) {
                Main.ed.reload();

                Main.ed.setVisible(true);
                Main.mw.setVisible(false);
            }
        }
    }
}

