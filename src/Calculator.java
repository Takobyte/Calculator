import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
        // init global variable
        op = Operators.EMPTY;
        ln = "";
        rn = "";

        // init Jframe
        JFrame f = new JFrame("Calculator");
        f.setContentPane(calculatorPanel);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.pack();
        f.setVisible(true);

        // Button listeners
        plusBtn.addActionListener(e -> {
            op = Operators.ADDITION;
            if (ln.isEmpty()) {
                if (getResultText().isEmpty()) {
                    ln = "0";
                    entryText.setText("0 + ");
                } else {
                    entryText.setText(getResultText() + " + ");
                }
            } else {
                entryText.setText(ln + " + ");
            }
        });
        oneBtn.addActionListener(e -> {
            // If operator buttons have not been clicked yet
            // keep adding number to ln (left number)
            if (op == Operators.EMPTY) {
                if (ln.isEmpty()) {
                    entryText.setText("");
                    ln = "1";
                } else {
                    ln = ln.concat("1");
                }
                entryText.setText(ln);
            } else {
                rn = "1";
                entryText.setText(entryText.getText() + rn);
            }
        });
        equalBtn.addActionListener(e -> {
            calculateResult();
            entryText.setText(entryText.getText() + " = " + result.toString());
            ln = "";
            rn = "";
            op = Operators.EMPTY;
        });
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
    public void calculateResult() {

        switch (op) {
            case ADDITION:
                result = new Addition().operation(ln, rn);
                break;
            case SUBTRACTION:
                result = new Subtraction().operation(ln, rn);
                break;
            case MULTIPLICATION:
                result = new Multiplication().operation(ln, rn);
                break;
            case DIVISION:
                try {
                    result = new Division().operation(ln, rn);
                } catch (Exception e) {
                    resultText.setText("Division Undefined");
                    return;
                }
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
                try {
                    result = new SquareRoot().operation(ln);
                } catch (Exception e) {
                    resultText.setText("Invalid Input");
                    return;
                }
                break;
            default:
                result = new BigDecimal("0");
                break;
        }

        resultText.setText(result.toString());
    }

    // jUnit Test purposes only
    public void setOperator(Operators op) {
        this.op = op;
    }

    public void setLeftNumber(String ln) {
        this.ln = ln;
    }

    public void setRightNumber(String rn) {
        this.rn = rn;
    }

    public String getResultText() {
        return resultText.getText();
    }


}
