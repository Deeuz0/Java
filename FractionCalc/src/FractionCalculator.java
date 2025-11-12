import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;

public class FractionCalculator extends JFrame {
    private JTextField numField, denField;
  private JTextArea outputArea;
    private ArrayList<Fraction> fractionList = new ArrayList<>();

    public FractionCalculator() {
       setTitle("Fraction Calculator");
     setSize(400, 300);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       setLayout(new FlowLayout());
      numField = new JTextField(5);
      denField = new JTextField(5);
      JButton buildFractionButton = new JButton("Build Fraction");
       JButton sortButton = new JButton("Sort Fractions");
       JComboBox<String> operationBox = new JComboBox<>(new String[]{"Decimal", "Reciprocal", "Lowest Terms", "Add", "Multiply", "Equals", "Greater Than"});
      outputArea = new JTextArea(5, 30);
       outputArea.setEditable(false);

		       add(new JLabel("Numerator:"));
		       add(numField);
		       add(new JLabel("Denominator:"));
		      add(denField);
		    add(buildFractionButton);
		       add(operationBox);
	   add(sortButton);
		        add(new JScrollPane(outputArea));

    buildFractionButton.addActionListener(e -> buildFraction());
      operationBox.addActionListener(e -> performOperation());
        sortButton.addActionListener(e -> sortFractions());
    }

    private void buildFraction() {
        try {
            String numText = numField.getText();
            String denText = denField.getText();

   if (numText.isEmpty() || denText.isEmpty()) throw new EmptyOperandException("Fields cannot be empty!");
           if (numText.length() > 10 || denText.length() > 10) throw new LongOperandException("Operand exceeds capacity!");
            
      int num = Integer.parseInt(numText);
            int den = Integer.parseInt(denText);
            Fraction fraction = new Fraction(num, den);

            fractionList.add(fraction);
            outputArea.append(fraction + "\n");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid integers.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (EmptyOperandException | LongOperandException | DivisionByZeroException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void performOperation() {
        if (fractionList.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Enter at least one fraction!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Fraction lastFraction = fractionList.get(fractionList.size() - 1);
        String operation = ((JComboBox<String>) getContentPane().getComponent(5)).getSelectedItem().toString();

        try {
            switch (operation) {
            case "Decimal":
                 outputArea.append("Decimal: " + lastFraction.toDecimal() + "\n");
               break;
          case "Reciprocal":
               outputArea.append("Reciprocal: " + lastFraction.toReciprocal() + "\n");
                break;
           case "Lowest Terms":
                  outputArea.append("Lowest Terms: " + lastFraction.lowestTerms() + "\n");
                   break;
              case "Add":               case "Multiply":
             case "Equals":
               case "Greater Than":
                if (fractionList.size() < 2) {
                        JOptionPane.showMessageDialog(this, "Need two fractions for this operation!", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    Fraction secondLast = fractionList.get(fractionList.size() - 2);
                    switch (operation) {
                        case "Add":
                            outputArea.append("Sum: " + secondLast.add(lastFraction) + "\n");
                            break;
                        case "Multiply":
                            outputArea.append("Product: " + secondLast.multiply(lastFraction) + "\n");
                            break;
                        case "Equals":
                            outputArea.append("Equal? " + secondLast.equals(lastFraction) + "\n");
                            break;
                        case "Greater Than":
                            outputArea.append("Greater? " + secondLast.greaterThan(lastFraction) + "\n");
                            break;
                    }
                    break;
            }
        } catch (DivisionByZeroException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void sortFractions() {
        Collections.sort(fractionList);
        outputArea.append("Sorted Fractions:\n");
        for (Fraction f : fractionList) {
            outputArea.append(f + "\n");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FractionCalculator().setVisible(true));
    }
}