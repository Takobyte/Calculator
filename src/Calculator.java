import javax.swing.*;
import java.math.BigDecimal;

public class Calculator {
    private JTextField entryText;
    private JButton percentBtn;
    private JButton clearEntryBtn;
    private JButton clearBtn;
    private JButton backBtn;
    private JButton invertButton;
    private JButton squareButton;
    private JButton rootButton;
    private JButton divideBtn;
    private JButton sevenBtn;
    private JButton eightBtn;
    private JButton nineBtn;
    private JButton multiplyButton;
    private JButton fourBtn;
    private JButton fiveBtn;
    private JButton sixBtn;
    private JButton minusBtn;
    private JButton oneBtn;
    private JButton twoBtn;
    private JButton threeBtn;
    private JButton plusBtn;
    private JButton equalBtn;
    private JButton decimalBtn;
    private JButton zeroBtn;
    private JButton changeSignBtn;
    private JPanel calculatorPanel;
    private JTextField resultText;

    private BigDecimal result;
    private String ln;
    private String rn;
    private Operators op;

    public Calculator() {
        JFrame f = new JFrame("Calculator");
        f.setContentPane(calculatorPanel);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.pack();
        f.setVisible(true);
    }


    // Effect: if Equals Operator has been used to determine the
    // result, clear both entryText and resultText. If Equals Operator
    // does not exist, then only clear the entryText
    private void clearEntry() {
        String res = result.toString();

        // look for the last character of the result
        res = res.substring(res.length() - 1);

        if (res == "=") {
            entryText.setText("");
            resultText.setText("");
        } else {
            entryText.setText("");
        }
    }

    // Effect: clear both entryText and resultText
    private void clear() {
        entryText.setText("");
        resultText.setText("");
    }

    // Effect: delete the last character of the entryText
    private void delete() {
        String entry = entryText.getText();

        if (!entry.isEmpty()) {
            // delete the last entry of the entryText
            entry = entry.substring(0, entry.length() - 1);
            entryText.setText(entry);
        }
    }


    // Effect: calculate the result depending on the Operator used
    // and display the text
    private void calculateResult() {
        switch (op) {
            case ADDITION:
                result = new Addition().operation(ln,rn);
                break;
            case SUBTRACTION:
                result = new Subtraction().operation(ln,rn);
                break;
            case MULTIPLICATION:
                result = new Multiplication().operation(ln,rn);
                break;
            case DIVISION:
                result = new Division().operation(ln,rn);
                break;
            case INVERSE:
                if (!rn.isEmpty()) {
                    result = new Inverse().operation(ln, rn);
                } else {
                    result = new Inverse().operation(ln);
                }
                break;
            case PERCENT:
                result = new Percent().operation(ln);
                break;
            case SQUARE:
                result = new Square().operation(ln);
                break;
            case SQUAREROOT:
                result = new SquareRoot().operation(ln);
                break;
            default:
                result = new BigDecimal("0");
                break;
        }
        resultText.setText(result.toString());
    }
}
