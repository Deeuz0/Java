import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TempConverter extends JFrame {
    private JTextField instructionField;
    private JTextField fahrenheitField;
    private JTextField celsiusField;
    private JTextField kelvinField;
    private JButton calculateButton;
    private JButton clearButton;

    public TempConverter() {
        setTitle("Daniel's Temperature Converter");
        setSize(450, 180);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Instruction Panel
        instructionField = new JTextField("Enter a temperature in any field and then press the Calculate button");
        instructionField.setEditable(false);
        instructionField.setBackground(Color.BLACK);
        instructionField.setForeground(Color.WHITE);
        add(instructionField, BorderLayout.NORTH);

        // Main Panel
        JPanel mainPanel = new JPanel(new GridLayout(3, 3, 5, 5));

        // Fahrenheit Row
        mainPanel.add(new JLabel("Fahrenheit Temp:"));
        fahrenheitField = new JTextField("212");
        mainPanel.add(fahrenheitField);
        clearButton = new JButton("Clear Fields");
        mainPanel.add(clearButton);

        // Celsius Row
        mainPanel.add(new JLabel("Celsius Temp:"));
        celsiusField = new JTextField("100.0");
        mainPanel.add(celsiusField);
        mainPanel.add(new JLabel("")); // Empty cell

        // Kelvin Row
        mainPanel.add(new JLabel("Kelvin Temp:"));
        kelvinField = new JTextField("373.15");
        mainPanel.add(kelvinField);
        calculateButton = new JButton("Calculate");
        mainPanel.add(calculateButton);

        add(mainPanel, BorderLayout.CENTER);

        // Action Listener
        ButtonHandler handler = new ButtonHandler();
        calculateButton.addActionListener(handler);
        clearButton.addActionListener(handler);

        setVisible(true);
    }

    private class ButtonHandler implements ActionListener, Daniel_TempConvertable {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == clearButton) {
                fahrenheitField.setText("");
                celsiusField.setText("");
                kelvinField.setText("");
            } else if (e.getSource() == calculateButton) {
                try {
                    if (!fahrenheitField.getText().isEmpty()) {
                        double f = Double.parseDouble(fahrenheitField.getText());
                        if (f < ABS_ZERO_F) throw new Exception("Below absolute zero!");
                        double c = convertFtoC(f);
                        double k = convertFtoK(f);
                        celsiusField.setText(String.format("%.2f", c));
                        kelvinField.setText(String.format("%.2f", k));
                    } else if (!celsiusField.getText().isEmpty()) {
                        double c = Double.parseDouble(celsiusField.getText());
                        if (c < ABS_ZERO_C) throw new Exception("Below absolute zero!");
                        double f = convertCtoF(c);
                        double k = convertCtoK(c);
                        fahrenheitField.setText(String.format("%.2f", f));
                        kelvinField.setText(String.format("%.2f", k));
                    } else if (!kelvinField.getText().isEmpty()) {
                        double k = Double.parseDouble(kelvinField.getText());
                        if (k < ABS_ZERO_K) throw new Exception("Below absolute zero!");
                        double c = convertKtoC(k);
                        double f = convertKtoF(k);
                        celsiusField.setText(String.format("%.2f", c));
                        fahrenheitField.setText(String.format("%.2f", f));
                    } else {
                        JOptionPane.showMessageDialog(null, "Please enter a value in one field.");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Invalid input: " + ex.getMessage());
                    fahrenheitField.setText("");
                    celsiusField.setText("");
                    kelvinField.setText("");
                    fahrenheitField.requestFocus();
                }
            }
        }

        public double convertFtoC(double fTemp) {
            return (5.0 / 9.0) * (fTemp - 32);
        }

        public double convertCtoF(double cTemp) {
            return (9.0 / 5.0) * cTemp + 32;
        }

        public double convertCtoK(double cTemp) {
            return cTemp + 273.15;
        }

        public double convertKtoC(double kTemp) {
            return kTemp - 273.15;
        }

        public double convertFtoK(double fTemp) {
            return convertCtoK(convertFtoC(fTemp));
        }

        public double convertKtoF(double kTemp) {
            return convertCtoF(convertKtoC(kTemp));
        }
    }

    public static void main(String[] args) {
        new TempConverter();
    }
}