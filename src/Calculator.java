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
    private int lnClick;
    private int rnClick;
    private Operators op;
    private final static int MAX_NUMBER = 10;

    public Calculator() {
        // init global variable
        op = Operators.EMPTY;
        ln = "";
        rn = "";
        lnClick = 0;
        rnClick = 0;

        // init Jframe
        JFrame f = new JFrame("Calculator");
        f.setContentPane(calculatorPanel);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.pack();
        f.setVisible(true);

        /*  Button listeners */

        // Operators
        plusBtn.addActionListener(e -> {
            addOperators(op.ADDITION);
        });
        minusBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        equalBtn.addActionListener(e -> {
            calculateResult();
            entryText.setText(entryText.getText() + " = " + result.toString());
            ln = "";
            rn = "";
            lnClick = 0;
            rnClick = 0;
            op = Operators.EMPTY;
        });


        // If operator buttons have not been clicked yet
        // keep adding number to ln (left number)
        zeroBtn.addActionListener(e -> addNumber(0));
        oneBtn.addActionListener(e -> addNumber(1));
        twoBtn.addActionListener(e -> addNumber(2));
        threeBtn.addActionListener(e -> addNumber(3));
        fourBtn.addActionListener(e -> addNumber(4));
        fiveBtn.addActionListener(e -> addNumber(5));
        sixBtn.addActionListener(e -> addNumber(6));
        sevenBtn.addActionListener(e -> addNumber(7));
        eightBtn.addActionListener(e -> addNumber(8));
        nineBtn.addActionListener(e -> addNumber(9));

        // Clear, ClearEntry, Back
        clearEntryBtn.addActionListener(e -> clearEntry());
        clearBtn.addActionListener(e -> clear());
        backBtn.addActionListener(e -> delete());
        percentBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        invertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        squareButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        rootButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        divideBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        multiplyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        decimalBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        changeSignBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    // Effect: adds number to the entry
    private void addNumber(int number) {
        String n = Integer.toString(number);
        if (op == Operators.EMPTY) {
            if (ln.isEmpty()) {
                entryText.setText("");
                ln = n;
                lnClick++;
            } else if (lnClick < MAX_NUMBER) {
                ln = ln.concat(n);
                lnClick++;
            }
            entryText.setText(ln);
        } else {
            if (rnClick < MAX_NUMBER) {
                rn = rn.concat(n);
                entryText.setText(entryText.getText() + n);
                rnClick++;
            }
        }
    }

    // Modifies: modifies op, ln
    // Effects: if left number is empty you add 0 before it, or
    // if result is not empty you add result before it
    // otherwise add operator after the left number
    // However, if rn is present instead, combine ln and rn and add operator
    private void addOperators(Operators op) {
        //if left side is empty
        if (ln.isEmpty() && this.op != op) {
            this.op = op;
            // if left side is empty and result box is empty
            if (resultText.getText().isEmpty()) {
                ln = "0";
                entryText.setText("0" + " " + toStringOperator(op) + " ");
            }
            //add result box to the ln if result box is not empty
            else if (!resultText.getText().isEmpty()){
                ln = resultText.getText();
                entryText.setText(resultText.getText() + " " + toStringOperator(op) + " ");
            }
        } else { // if left side is not empty
            // if right side is not empty then combine ln+rn
            this.op = op;
            if (!rn.isEmpty()) {
                BigDecimal combined_num = new Addition().operation(ln, rn);
                ln = combined_num.toString();
                rn = "";
                lnClick = 0;
                rnClick = 0;
                entryText.setText(entryText.getText() + " " + toStringOperator(op) + " ");
            } // otherwise just add operator at the end of left number
            else {
                entryText.setText(entryText.getText() + " " + toStringOperator(op) + " ");
            }
        }
    }

    // Modifies: Operator to string
    // Effects: give string output from Operator
    private String toStringOperator(Operators op) {
        String result;
        switch(op) {
            case ADDITION:
                result = "+";
                break;
            case SUBTRACTION:
                result = "-";
                break;
            case MULTIPLICATION:
                result = "x";
                break;
            case DIVISION:
                result = "÷";
                break;
            case INVERSE:
                result = "1 /";
                break;
            case PERCENT:
                result = "%";
                break;
            case SQUARE:
                result = "^2";
                break;
            case SQUAREROOT:
                result = "√";
                break;
            default:
                result = "";
                break;
        }
        return result;
    }

    // Effect: if Equals Operator has been used to determine the
    // result, clear both entryText and resultText. If Equals Operator
    // does not exist, then only clear the entryText
    private void clearEntry() {
        String res = result.toString();

        // look for the last character of the result
        res = res.substring(res.length() - 1);

        if (res == "=") {
            clear();
        } else {
            entryText.setText("");
            ln = "";
            rn = "";
            lnClick = 0;
            rnClick = 0;
            op = op.EMPTY;
        }
    }

    // Effect: clear both entryText and resultText
    private void clear() {
        entryText.setText("");
        resultText.setText("");
        ln = "";
        rn = "";
        lnClick = 0;
        rnClick = 0;
        op = op.EMPTY;
    }

    // Effect: delete the last character of the entryText
    private void delete() {
        String entry = entryText.getText();
        // only perform if entry is not empty
        if (!entry.isEmpty()) {
            if (op != op.EMPTY && !rn.isEmpty()) {
                // delete the last entry of the entryText
                // delete right numbers only if results have not been calculated
                entry = entry.substring(0, entry.length() - 1);
                entryText.setText(entry);
                rn = rn.substring(0, rn.length() - 1);
                rnClick--;
            } else if (!ln.isEmpty() && rn.isEmpty() && op == op.EMPTY){

                // delete the last entry of the entryText
                // delete left numbers only if operator has not been used
                entry = entry.substring(0, entry.length() - 1);
                entryText.setText(entry);
                ln = ln.substring(0, ln.length() - 1);
                lnClick--;
            }
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
                result = new BigDecimal(ln);
                break;
        }
        op = op.EQUALS;
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
