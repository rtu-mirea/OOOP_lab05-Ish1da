package com.company;

import javax.swing.*;
        import java.awt.*;
        import java.awt.*;
        import java.awt.event.*;
        import java.io.FileInputStream;
        import java.io.FileOutputStream;
        import java.io.ObjectInputStream;
        import java.io.ObjectOutputStream;
        import javax.swing.*;
        import java.io.*;

public class Editor extends JFrame {
    public JComboBox comboBox = new JComboBox();
    private JLabel lab1 = new JLabel("Name");
    private JLabel lab2 = new JLabel("Login");
    private JLabel lab3 = new JLabel("Password");
    private JTextField tex1 = new JTextField("", 5);
    private JTextField tex2 = new JTextField("", 5);
    private JTextField tex3 = new JTextField("", 5);
    private JButton but1 = new JButton("Изменить");
    private JButton but2 = new JButton("Удалить");
    private JButton but3 = new JButton("Закрыть");

    public Editor(){
        super("Editor");
        this.setBounds(500, 500, 500, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        Container con = this.getContentPane();
        con.setLayout(new GridLayout(10, 1, 2, 2));
        con.add(comboBox);
        con.add(lab1);
        con.add(tex1);
        con.add(lab2);
        con.add(tex2);
        con.add(lab3);
        con.add(tex3);
        but1.addActionListener(new Editor.ButtonEventListener());
        but2.addActionListener(new Editor.ButtonEventListener());
        but3.addActionListener(new Editor.ButtonEventListener());
        con.add(but1);
        con.add(but2);
        con.add(but3);
    }

    public void reload(){
        comboBox.removeAllItems();
        for (Client rq : Main.ts.users){
            comboBox.addItem(rq.getName());
            System.out.println(rq.getName());
        }
        System.out.println(Main.ed.comboBox.getItemAt(1));
    }

    class ButtonEventListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            if (e.getSource() == but1) {
                Main.ts.users.set(comboBox.getSelectedIndex(), new Client(tex1.getText(), tex2.getText(), tex3.getText()));
                reload();
            };
            if (e.getSource() == but2) {
                Main.ts.users.remove(comboBox.getSelectedIndex());
                reload();
            };
            if (e.getSource() == but3) {
                Main.ed.setVisible(false);
                Main.mw.setVisible(true);
            }
        }
    }
}
