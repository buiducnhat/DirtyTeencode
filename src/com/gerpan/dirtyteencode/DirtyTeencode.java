package com.gerpan.dirtyteencode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Clipboard;

public class DirtyTeencode extends JFrame {
    public DirtyTeencode() {
        initComponents();
        readSourceText("src//com//gerpan//dirtyteencode//resource//dirty_teencode_characters.txt", dictCharacters); //Read key map from file teencode characters
        readSourceText("src//com//gerpan//dirtyteencode//resource//dirty_teencode_words.txt", dictWords); //Read key map from file teencode words
        setDefaultButton(); //Convert button would be clicked when press Enter key
    }

    private void initComponents() {
        //set up main Frame
        this.setBounds(500, 220, 500, 200);
        this.setLayout(null);
        this.setResizable(false);
        this.setVisible(true);
        this.setTitle("Dirty T33nK0d3");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //end set up frame

        //set up input and output label
        inputLabel = new JLabel("Input");
        inputLabel.setBounds(20, 10, 50, 30);
        this.add(inputLabel);

        outputLabel = new JLabel("Output");
        outputLabel.setBounds(20, 40, 50, 30);
        this.add(outputLabel);
        //end set up input and output label

        //setup input textfield and output textfield
        inputTextField = new JTextField();
        inputTextField.setBounds(75, 12, 400, 30);
        this.add(inputTextField);

        outputTextField = new JTextField();
        outputTextField.setEditable(false);
        outputTextField.setBounds(75, 42, 400, 30);
        this.add(outputTextField);
        //end set up input and output textfield

        //setup convert button
        convertButton = new JButton("CONVERT");
        convertButton.setFont(new Font("Bauhaus 93", Font.PLAIN, 15));
        convertButton.setBackground(new Color(10, 10, 10));
        convertButton.setForeground(new Color(39, 231, 0));
        convertButton.setBounds(180, 90, 100, 50);
        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                convertButton_Click();
            }
        });
        this.add(convertButton);
        //end setup convert button

        //init HashMap
        dictCharacters = new HashMap<String, String>();
        dictWords = new HashMap<String, String>();
    }

    private void setDefaultButton() {
        //Set default button for app, when press Enter convert Button will be clicked!
        JRootPane rootPane = SwingUtilities.getRootPane(convertButton);
        rootPane.setDefaultButton(convertButton);
    }

    private void convertButton_Click() {
        //read input text from input text field
        inputData = inputTextField.getText().toLowerCase();
        outputData = inputData;

        //replace all possible words before replacing characters
        analysisInputData(dictWords);
        analysisInputData(dictCharacters);

        //display output to output textfield
        outputTextField.setText(outputData);

        //copy output text to clipboard
        StringSelection stringSelection = new StringSelection(outputData);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);

        //set message for successfully conversion
        JOptionPane.showMessageDialog(this, "C0py th4`nh c0^n9!\nDjrty t33nk0d3 - Đ3^'n l4` đ0'n ♥", "th0^n9 p4'0 - FPJ W4RNIN9", JOptionPane.PLAIN_MESSAGE, new ImageIcon("src//com//gerpan//dirtyteencode//resource//icon_message.png"));
    }

    private void analysisInputData(HashMap<String, String> dict) {
        for (String i : dict.keySet()) {
            outputData = outputData.replaceAll(i, dict.get(i));
        }
    }

    private void readSourceText(String filePath, HashMap<String, String> dict) {
        //Use array lise to analysis spaces of input string per line, then add corresponding key-value to HashMap
        try (BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "UTF8"))) {
            String temp = "";
            ArrayList<String> dataInLine = new ArrayList<String>();
            while ((temp = in.readLine()) != null) {
                dataInLine = new ArrayList<String>(Arrays.asList(temp.split(" ")));
                dataInLine.removeIf(s -> s.isEmpty());
                //System.out.println(dataInLine);
                dict.put(dataInLine.get(0), dataInLine.get(1));
            }
            //System.out.println(dictCharacters);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new DirtyTeencode();
    }

    //creat variables need for this app
    private JTextField inputTextField;
    private JTextField outputTextField;
    private JLabel inputLabel;
    private JLabel outputLabel;
    private JButton convertButton;
    private HashMap<String, String> dictCharacters;
    private HashMap<String, String> dictWords;
    private String inputData;
    private String outputData;
}













