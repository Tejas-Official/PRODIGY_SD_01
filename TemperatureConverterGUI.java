/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package temperatureconvertergui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TemperatureConverterGUI {

    private JFrame frame;
    private JTextField temperatureField;
    private JComboBox<String> unitComboBox;
    private JTextArea resultArea;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                new TemperatureConverterGUI();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public TemperatureConverterGUI() {
        frame = new JFrame("Temperature Converter");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel inputPanel = createInputPanel();
        frame.add(inputPanel, BorderLayout.NORTH);

        resultArea = new JTextArea();
        resultArea.setEditable(false);
        resultArea.setFont(new Font("Arial", Font.PLAIN, 16)); // Set a larger font
        resultArea.setLineWrap(true);
        resultArea.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(resultArea);
        frame.add(scrollPane, BorderLayout.CENTER);

        JButton convertButton = new JButton("Convert");
        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                convertTemperature();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(convertButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private JPanel createInputPanel() {
        JPanel inputPanel = new JPanel(new FlowLayout());
        inputPanel.setBackground(new Color(173, 216, 230)); // Set a light blue background color

        JLabel temperatureLabel = new JLabel("Temperature:");
        temperatureField = new JTextField(10);

        JLabel unitLabel = new JLabel("Unit:");
        String[] units = {"Celsius", "Fahrenheit", "Kelvin"};
        unitComboBox = new JComboBox<>(units);

        inputPanel.add(temperatureLabel);
        inputPanel.add(temperatureField);
        inputPanel.add(unitLabel);
        inputPanel.add(unitComboBox);

        return inputPanel;
    }

    private void convertTemperature() {
        try {
            double temperature = Double.parseDouble(temperatureField.getText());
            String selectedUnit = (String) unitComboBox.getSelectedItem();

            double convertedCelsius = 0;
            double convertedFahrenheit = 0;
            double convertedKelvin = 0;

            switch (selectedUnit) {
                case "Celsius":
                    convertedCelsius = temperature;
                    convertedFahrenheit = (temperature * 9/5) + 32;
                    convertedKelvin = temperature + 273.15;
                    break;
                case "Fahrenheit":
                    convertedCelsius = (temperature - 32) * 5/9;
                    convertedFahrenheit = temperature;
                    convertedKelvin = (temperature + 459.67) * 5/9;
                    break;
                case "Kelvin":
                    convertedCelsius = temperature - 273.15;
                    convertedFahrenheit = (temperature * 9/5) - 459.67;
                    convertedKelvin = temperature;
                    break;
            }

            String resultText = String.format("Converted to Celsius: %.2f\nConverted to Fahrenheit: %.2f\nConverted to Kelvin: %.2f",
                    convertedCelsius, convertedFahrenheit, convertedKelvin);

            resultArea.setText(resultText);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Invalid input. Please enter a valid number for temperature.");
        }
    }
}
