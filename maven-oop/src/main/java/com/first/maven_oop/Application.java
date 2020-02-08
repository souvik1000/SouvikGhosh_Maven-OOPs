package com.first.maven_oop;

import org.apache.log4j.Logger;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;

public class Application {

    private static final Logger LOG = Logger.getLogger(Application.class);

    private GiftController giftController;

    ArrayList<Sweet> sweets;

    public Application() throws CreateDocumentConfigurationException {
        giftController = new GiftController();
        sweets = new ArrayList<Sweet>();
    }

    public void start() throws CreateDocumentConfigurationException {

        Object[] options = {"Yes, please", "I have configuration xml file",
                "No, thanks!"};

        int n = JOptionPane.showOptionDialog(null,
                "Would you like to create new random New Year's Gift?",
                "A Silly Question", JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, options, options[2]);

        estimateUserInput(n);
    }

    private void estimateUserInput(int n) throws CreateDocumentConfigurationException {
        switch (n) {
            case 0:
                process();
                break;
            case 1:
                chooseFile();
                break;
            case 2:
                goodBye();
                break;
        }
    }

    private void chooseFile() {

        JFileChooser chooser = new JFileChooser();

        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            File selectedFile = chooser.getSelectedFile();
            giftController.parseXmlFile(selectedFile);
        }
    }

    private void goodBye() {
        JOptionPane.showMessageDialog(null, "Good Bye!");
    }

    private void process() throws CreateDocumentConfigurationException {

        String[] input = divideUserInput();
        double start, end;

        if (input.length < 2) {
            errorMessage("Input error!");
            throw new InvalidUserInputDataException();
        }

        int quantity = Integer.parseInt(input[0]);
        start = Double.parseDouble(input[1]);
        end = Double.parseDouble(input[2]);

        giftController.showGiftContent(quantity);

        giftController.showSortedByWeight();

        giftController.showSortedBySugar();

        giftController.showExtractedSugar(start, end);

        giftController.writeToXmlFile();
    }

    private void errorMessage(String msg) {

        JOptionPane.showMessageDialog(null, msg, "Input error",
                JOptionPane.ERROR_MESSAGE);
    }

    private String[] divideUserInput() {
        return inputGiftInfo().trim().split("\\s");
    }

    private String inputGiftInfo() {
        String ResultString = "";
        JTextField QuantityField = new JTextField(5);
        JTextField MinSugarField = new JTextField(5);
        JTextField MaxSugarField = new JTextField(5);

        JPanel myPanel = new JPanel();

        myPanel.add(new JLabel("Total Quantity of New Year's Gift:"));
        myPanel.add(QuantityField);
        myPanel.add(Box.createHorizontalStrut(10)); 

        myPanel.add(new JLabel("Min SugarLevel (from 0):"));
        myPanel.add(MinSugarField);
        myPanel.add(Box.createHorizontalStrut(10)); 

        myPanel.add(new JLabel("Max SugarLevel (to 100):"));
        myPanel.add(MaxSugarField);

        int result = JOptionPane.showConfirmDialog(null, myPanel,
                "Please Enter Droid configuration",
                JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            ResultString = ResultString + QuantityField.getText() + " "
                    + MinSugarField.getText() + " " + MaxSugarField.getText();

            LOG.debug("the New Year's Gift is created, quantity "
                    + QuantityField.getText() + " with " + MinSugarField
                    + " min and " + MaxSugarField + " max sugar level level");
        }

        return ResultString;
    }

}