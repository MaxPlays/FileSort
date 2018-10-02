package me.MaxPlays.FileSort;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 Copyright 2018 Maximilian Negedly

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 */
public class GUI {

    private JTextField directory;
    private JButton select;
    private JTextField type;
    private JButton add;
    private JButton remove;
    private JList types;
    private JCheckBox r;
    private JCheckBox m;
    private JCheckBox o;
    private JPanel panel;
    private JButton sort;
    private DefaultListModel model = new DefaultListModel();

    public GUI() {

        JFrame frame = new JFrame("FileSort v." + FileSort.version);
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(panel.getPreferredSize());
        frame.setLocationRelativeTo(null);
        frame.setMinimumSize(panel.getPreferredSize());

        types.setModel(model);
        model.addElement("*");

        types.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        frame.setVisible(true);
        m.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(o.isEnabled()){
                    o.setEnabled(false);
                    o.setSelected(false);
                }else{
                    o.setEnabled(true);
                }
            }
        });
        select.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser c = new JFileChooser();
                c.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                c.setMultiSelectionEnabled(false);
                c.setDialogTitle("Select directory");
                c.showOpenDialog(panel);
                if(c.getSelectedFile() != null){
                    directory.setText(c.getSelectedFile().getAbsolutePath());
                }
            }
        });
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(type.getText().length() > 0){
                    if(model.contains("*"))
                        model.removeElement("*");
                    if(!model.contains("." + type.getText().toLowerCase())){
                        model.addElement("." + type.getText().toLowerCase());
                        type.setText("");
                    }
                }
            }
        });
        remove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(types.getSelectedValue() != null && !types.getSelectedValue().equals("*")){
                    model.removeElement(types.getSelectedValue());
                    if(model.getSize() == 0)
                        model.addElement("*");
                }
            }
        });
        type.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(type.getText().length() > 0){
                    if(model.contains("*"))
                        model.removeElement("*");
                    if(!model.contains("." + type.getText().toLowerCase())){
                        model.addElement("." + type.getText().toLowerCase());
                        type.setText("");
                    }
                }
            }
        });
        sort.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(directory.getText().length() > 0){
                    StringBuilder sb = new StringBuilder();
                    for(int i = 0; i < model.getSize(); i++){
                        if(!model.getElementAt(i).equals("*"))
                            sb.append(model.getElementAt(i).toString().substring(1)).append(i == (model.getSize() - 1) ? "" : ",");
                    }
                    if(JOptionPane.showConfirmDialog(panel, "Do you want to sort the files in the directory " + directory.getText() + " ?") == 0) {
                        System.out.println(sb.toString());
                        sort.setEnabled(false);
                        new Sort(directory.getText(), sb.toString(), r.isSelected(), m.isSelected(), o.isSelected(), true);
                        sort.setEnabled(true);
                        JOptionPane.showMessageDialog(panel, "Sorted");
                    }

                }
            }
        });
    }
}
