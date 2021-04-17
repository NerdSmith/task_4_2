package ru.vsu.cs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class MainFrame extends JFrame {
    private JPanel mainPanel;
    private JTextField inputTextField;
    private JButton sortBtn;
    private JTextField outputTextField;
    private JButton loadFromFileBtn;

    public MainFrame() {
        super("Application");
        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(100, 100, 900, 500);
        this.setMinimumSize(new Dimension(800, 400));

        loadFromFileBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                chooser.setCurrentDirectory(new File("./files"));
                int result = chooser.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    String name = chooser.getSelectedFile().getPath();
                    Scanner scanner;
                    try {
                        scanner = new Scanner(new File(name));
                    } catch (FileNotFoundException fileNotFoundException) {
                        outputTextField.setText("File not found");
                        return;
                    }
                    inputTextField.setText(scanner.nextLine());
                }
            }
        });

        sortBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] splittedLine = inputTextField.getText().split(" ");
                if (splittedLine[0].matches("-?\\d+(\\.\\d+)?")) {
                    Integer[] inputArr = ArrayUtils.toInteger(splittedLine);
                    SmoothSort.sort(inputArr);
                    outputTextField.setText(Arrays.toString(inputArr));
                }
            }
        });
    }
}
