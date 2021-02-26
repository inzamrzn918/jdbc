package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class ShowWindow extends Frame {

    public ShowWindow(List<StudentModel> list){
        try {
            String[][] data;
            if(list.size()>0)
                data = new String[list.size()][5];
            else
                data = new String[][]{};
            for (int i = 0; i <list.size() ; i++) {
                    data[i][0] = String.valueOf(list.get(i).getId());
                    data[i][1] = String.valueOf(list.get(i).getRoll_no());
                    data[i][2] = list.get(i).getName();
                    data[i][3] = String.valueOf(list.get(i).getContact());
                    data[i][4] = list.get(i).getAddress();
            }
            String[] column = {"ID", "Roll No", "Name", "Contact", "Address"};
            JTable jt = new JTable(data, column);
            jt.setBounds(30, 40, 200, 300);
            JScrollPane sp = new JScrollPane(jt);




            jt.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            jt.getSelectionModel().addListSelectionListener(event -> {
                if(!event.getValueIsAdjusting()){
                    StudentModel model = new StudentModel(
                            Integer.parseInt(jt.getValueAt(jt.getSelectedRow(),0).toString()),
                            jt.getValueAt(jt.getSelectedRow(),2).toString(),
                            jt.getValueAt(jt.getSelectedRow(),4).toString(),
                            Integer.parseInt(jt.getValueAt(jt.getSelectedRow(),1).toString()),
                            Long.parseLong(jt.getValueAt(jt.getSelectedRow(),3).toString())
                    );
                    new EditUI(model);
                }

            });


            jt.setDefaultEditor(Object.class, null);
            add(sp);
            addWindowListener(
                    new WindowAdapter() {
                        public void windowClosing(
                                WindowEvent we)
                        {
                            dispose();
                        }
                    });
            setSize(400, 400);
            setVisible(true);
        }catch (Exception e){
            JOptionPane.showMessageDialog(this,e.getMessage());
            e.printStackTrace();
        }

    }


}
