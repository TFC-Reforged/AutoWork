package org.opencommunity.envel.autowork;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class MainForm extends JFrame {
    public JPanel MainForm;
    public JButton buildbutton;
    public JPanel panel1;
    public JTextArea contentlist;
    public JTextArea filenames;
    public JTextArea filllist;
    private JTextField filefolder;

    public MainForm(String title) {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(panel1);
        this.pack();
        buildbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                // получить названия файлов
                String[] s = filenames.getText().split("\\r?\\n");
                ArrayList<String> filenames = new ArrayList<>(Arrays.asList(s));
                System.out.println(filenames);

                // получить заполнители
                String[] d = filllist.getText().split("\\r?\\n");
                ArrayList<String> filllist = new ArrayList<>(Arrays.asList(d));
                System.out.println(filllist);

                // получить папку назначения
                String filefolderlist = filefolder.getText();

                // создать файлы
                for (int i = 0; i < filenames.size(); i++) {
                    System.out.println(filenames.get(i));
                    try {
                        File genfiles = new File(filefolderlist + filenames.get(i) + ".json");
                        genfiles.createNewFile();

                        // получить содержимое
                        JTextArea area = contentlist;

                        // запись в файл
                        try (BufferedWriter fileOut = new BufferedWriter(new FileWriter(filefolderlist + filenames.get(i) + ".json"))) {
                            area.write(fileOut);
                        }
                        // обработка исключений
                    } catch (Exception ex1) {
                        JOptionPane.showMessageDialog(MainForm, "Something went wrong",
                                "ERROR", JOptionPane.WARNING_MESSAGE);
                    }
                }

                JOptionPane.showMessageDialog(MainForm, "Files created",
                        "Done", JOptionPane.NO_OPTION);

            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new MainForm("OpenCommunity TFC:Reforged Assets Builder");
        frame.setVisible(true);
    }
}