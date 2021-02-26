package com.company;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


// Creating the fee class
public class UI extends Frame {

    private JLabel nameLabel, rollNoLabel, contactLabel, adressLabel;
    private JTextField nameTextField, rollNoTextField, contactTextField;
    private JTextArea addressTextField;
    private Statement smt = null;
    private Connection con;
    private List<StudentModel> list;
    private JButton btnSubmit;
    private JButton btnSearch;
    private JButton btnShow;
    public UI(){
         con = DB.connect();
        try {
            smt = con.createStatement();

        nameLabel = new JLabel("Name of the Student:");
        nameLabel.setBounds(50, 50, 250, 20);
        nameTextField = new JTextField();
        nameTextField.setBounds(250, 50, 250, 20);

        rollNoLabel = new JLabel("Roll Number:");
        rollNoLabel.setBounds(50, 100, 250, 20);
        rollNoTextField = new JTextField();
        rollNoTextField.setBounds(250, 100, 250, 20);

        contactLabel = new JLabel("Contact Number:");
        contactLabel.setBounds(50, 150, 250, 20);
        contactTextField = new JTextField();
        contactTextField.setBounds(250, 150, 250, 20);

        adressLabel = new JLabel("Address:");
        adressLabel.setBounds(50, 200, 250, 20);
        addressTextField = new JTextArea();
        addressTextField.setBounds(250, 200, 250, 90);


        btnSubmit = new JButton("Submit");
        btnSubmit.setBounds(200, 350, 150, 30);
        btnShow = new JButton("Show");
        btnShow.setBounds(380, 350, 150, 30);

        btnSearch = new JButton("Search");
        btnSearch.setBounds(540, 50, 100, 20);
               // setBounds(260,50,70,20);

        add(nameLabel);
        add(rollNoLabel);
        add(contactLabel);
        add(adressLabel);
        add(nameTextField);
        add(btnSearch);
        add(rollNoTextField);
        add(contactTextField);
        add(adressLabel);
        add(addressTextField);
        add(btnSubmit);
        add(btnShow);


        btnSubmit.addActionListener(actionEvent -> {
            String name,address;
            int roll_no;
            long contact;
            name = nameTextField.getText();
            address = addressTextField.getText();
            try {
                roll_no = Integer.parseInt(rollNoTextField.getText());
                contact = Long.parseLong(contactTextField.getText());
                String sql = "INSERT INTO `student`(`name`, `roll_no`, `contact`, `address`) " +
                        "VALUES ('"+name+"','"+roll_no+"','"+contact+"','"+address+"')";

                smt.execute(sql);
                JOptionPane.showMessageDialog(this,"Student Record Saved");
                rollNoTextField.setText("");
                contactTextField.setText("");
                nameTextField.setText("");
                addressTextField.setText("");
            }catch (Exception e){
                JOptionPane.showMessageDialog(this,e.getMessage());
                e.printStackTrace();
            }

        });

        btnShow.addActionListener(actionEvent -> {
            try {
                new ShowWindow(getAllStudent());
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this,e.getMessage());
                e.printStackTrace();
            }
        });

        btnSearch.addActionListener(actionEvent -> {
           String search =  JOptionPane.showInputDialog("Enter Student information ");
           String sql = "SELECT * FROM `student` WHERE `name` LIKE '"+search+"' OR "
                   +"`roll_no` LIKE '"+search+"' OR `contact` LIKE '"+search+"'";

            ResultSet rs = null;
            List<StudentModel> searchList = new ArrayList<>();
            try {
                rs = smt.executeQuery(sql);
                while (rs.next()) {
                    StudentModel model = new StudentModel(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("address"),
                            rs.getInt("roll_no"),
                            rs.getLong("contact")
                    );
                    searchList.add(model);
                }
                if(searchList.size()>0){
                    new ShowWindow(searchList);
                }else {
                    JOptionPane.showMessageDialog(this,"No Match Found");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

         });

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
        }
    }



    private List<StudentModel> getAllStudent() throws SQLException {
        list = new ArrayList<>();
        String sql = "SELECT * FROM `student`";
        ResultSet rs = smt.executeQuery(sql);
        while (rs.next()){
            StudentModel model = new StudentModel(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("address"),
                    rs.getInt("roll_no"),
                    rs.getLong("contact")
            );
            list.add(model);
        }
        return list;
    }
}
