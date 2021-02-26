package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class EditUI extends Frame {

    private JTextField nameTextField, rollNoTextField, contactTextField;
    private JTextArea addressTextField;
    private Statement smt = null;

    public EditUI(StudentModel model){
        Connection con = DB.connect();
        try {
            smt = con.createStatement();

            JLabel nameLabel = new JLabel("Name of the Student:");
            nameLabel.setBounds(50, 50, 250, 20);
            nameTextField = new JTextField(model.getName());
            nameTextField.setBounds(250, 50, 250, 20);

            JLabel rollNoLabel = new JLabel("Roll Number:");
            rollNoLabel.setBounds(50, 100, 250, 20);
            rollNoTextField = new JTextField(String.valueOf(model.getRoll_no()));
            rollNoTextField.setBounds(250, 100, 250, 20);

            JLabel contactLabel = new JLabel("Contact Number:");
            contactLabel.setBounds(50, 150, 250, 20);
            contactTextField = new JTextField(String.valueOf(model.getContact()));
            contactTextField.setBounds(250, 150, 250, 20);

            JLabel adressLabel = new JLabel("Address:");
            adressLabel.setBounds(50, 200, 250, 20);
            addressTextField = new JTextArea(model.getAddress());
            addressTextField.setBounds(250, 200, 250, 90);

            JButton btnUpdate = new JButton("Update");
            btnUpdate.setBounds(380, 350, 150, 30);



            btnUpdate.addActionListener(actionEvent -> {
                int id = model.getId();
                String name = nameTextField.getText();
                String address = addressTextField.getText();
                int roll_no = Integer.parseInt(rollNoTextField.getText());
                long contact = Long.parseLong(contactTextField.getText());

                String sql = "UPDATE `student` SET `name` = '"+name+"', `roll_no` = '"+roll_no+"'," +
                        " `address` = '"+address+"', `contact` = '"+contact+"' WHERE `id`='"+id+"'";
                try {
                    smt.executeUpdate(sql);
                    JOptionPane.showMessageDialog(this,"Updated Successfully. Relaunch View Window to get Effected");
                    dispose();
                } catch (SQLException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(this,e.getMessage());

                }
            });


            add(nameLabel);
            add(rollNoLabel);
            add(contactLabel);
            add(adressLabel);
            add(nameTextField);
            add(rollNoTextField);
            add(contactTextField);
            add(addressTextField);
            add(btnUpdate);


            addWindowListener(
                    new WindowAdapter() {
                        public void windowClosing(
                                WindowEvent we)
                        {
                           dispose();
                        }
                    });
            setSize(640, 480);
            setLayout(null);
            setVisible(true);
            setBackground(Color.lightGray);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,e.getMessage());
            e.printStackTrace();
        }
    }
}
