package com.company;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.plaf.basic.BasicOptionPaneUI;

public class Login extends JFrame{
    private JLabel lab1 = new JLabel("Name");
    private JLabel lab2 = new JLabel("Login");
    private JLabel lab3 = new JLabel("Password");
    private JTextField tex1 = new JTextField("", 5);
    private JTextField tex2 = new JTextField("", 5);
    private JTextField tex3 = new JTextField("", 5);
    private JButton but1 = new JButton("Login");
    private JButton but2 = new JButton("Register");

    public Login(){
        super("Login");
        this.setBounds(500, 500, 200, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container con = this.getContentPane();
        con.setLayout(new GridLayout(4, 2, 10, 10));
        con.add(lab1);
        con.add(tex1);
        con.add(lab2);
        con.add(tex2);
        con.add(lab3);
        con.add(tex3);
        but1.addActionListener(new ButtonEventListener());
        but2.addActionListener(new ButtonEventListener());
        con.add(but1);
        con.add(but2);
    }

    class ButtonEventListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            if (e.getSource() == but1) {
                if (Main.ts.findUser(tex2.getText(), tex3.getText())) {
                    JOptionPane.showMessageDialog(null, "Вы успешно вошли под именем " + Main.ts.getCurrentUser().getName(), "Сообщение",JOptionPane.PLAIN_MESSAGE);
                    Main.log.setVisible(false);
                    Main.mw.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Неверный логин иили пароль", "Сообщение",JOptionPane.PLAIN_MESSAGE);
                }
            };
            if (e.getSource() == but2) {
                Main.ts.addUser(tex1.getText(), tex2.getText(), tex3.getText());
                JOptionPane.showMessageDialog(null, "Пользователь успешно зарегистрирован", "Сообщение",JOptionPane.PLAIN_MESSAGE);
            };
        }
    }
}
