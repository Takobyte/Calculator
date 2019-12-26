import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.regex.Pattern;

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
    private boolean specialOp;
    private final static int MAX_NUMBER = 10;

    public Calculator() {
        // init global variable
        op = Operators.EMPTY;
        ln = "";
        rn = "";
        lnClick = 0;
        rnClick = 0;
        specialOp = false;

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
        minusBtn.addActionListener(e -> {
            addOperators(op.SUBTRACTION);
        });
        divideBtn.addActionListener(e -> {
            addOperators(op.DIVISION);
        });
        multiplyButton.addActionListener(e -> {
            addOperators(op.MULTIPLICATION);
        });
        equalBtn.addActionListener(e -> {
            // only add 0 when rn is empty and operators such as +-x/ have been used
            // or add 0 when entry text is empty and result text is empty
            // or when resultText is not a number
            if ((rn.isEmpty() && (op == Operators.ADDITION || op == Operators.SUBTRACTION || op == Operators.MULTIPLICATION || op == Operators.DIVISION)) ||
                    entryText.getText().isEmpty() && resultText.getText().isEmpty()) {
                addNumber(0);
            }
            // If equals is being repeated
            if (this.op == Operators.EQUALS) {
                if (entryText.getText().isEmpty()) { // if entry text is empty
                    // TODO: fix division undefined text coming into entry text
                    try {
                        BigDecimal resultNumber = new BigDecimal(resultText.getText());
                        entryText.setText(resultNumber.toString());
                        ln = resultText.getText();
                    } catch (Exception err) {
                        entryText.setText("0");
                    }
                    ln = "";
                    rn = "";
                    lnClick = 0;
                    rnClick = 0;
                    op = Operators.EQUALS;
                } else {
                    try {
                        BigDecimal resultNumber = new BigDecimal(resultText.getText());
                        entryText.setText(resultNumber.toString());
                        ln = resultText.getText();
                    } catch (Exception err) {
                        addNumber(0);
                    }
                    // do nothing
                }
            } else {
                if (!(ln.isEmpty() && rn.isEmpty())) {
                    calculateResult();
                    if (result != null) {
                        entryText.setText(entryText.getText() + " = " + result.toString());
                    }
                    ln = "";
                    rn = "";
                    lnClick = 0;
                    rnClick = 0;
                    op = Operators.EQUALS;
                } else {
                    ln = "";
                    rn = "";
                    lnClick = 0;
                    rnClick = 0;
                    entryText.setText(resultText.getText());
                    int resultNumber = Integer.parseInt(resultText.getText());
                    addNumber(resultNumber);
                    op = Operators.EQUALS;
                }
            }
        });

        // Special Operators
        changeSignBtn.addActionListener(e -> {
            // if ln is not empty and op is empty
            if (!ln.isEmpty() && op == op.EMPTY) { // change left number's sign
                BigDecimal changed_sign = new ChangeSign().operation(ln);
                ln = changed_sign.toString();
                entryText.setText(ln);
            } else if (!rn.isEmpty()) { // change right number's sign
                BigDecimal changed_sign = new ChangeSign().operation(rn);
                String tempET = entryText.getText().substring(0,entryText.getText().length() - rn.length());
                rn = changed_sign.toString();
                entryText.setText(tempET.concat(rn));
            } else if (op == op.EMPTY){ // change result's sign
                BigDecimal changed_sign = new ChangeSign().operation(resultText.getText());
                ln = changed_sign.toString();
                entryText.setText(ln);
            }
            this.op = op.CHANGESIGN;
        });
        percentBtn.addActionListener(e -> {
            // if LN is empty add 0 before adding %
            if (ln.isEmpty()) {
                try {
                    BigDecimal res = new BigDecimal(resultText.getText());
                    if (!res.toString().isEmpty()) {
                        ln = res.toString();
                        entryText.setText(ln + "%");
                        BigDecimal percentage = new Percent().operation(ln);
                        ln = percentage.toString();
                    }
                    ln = "0";
                    entryText.setText("0%");
                } catch (Exception textErr) {
                    ln = "0";
                    entryText.setText("0%");
                }
            }
            // if LN is not empty and RN is empty and Operators have not been used check if % has already been added
            else if ((rn.isEmpty()) &&
                    !(op == Operators.ADDITION || op == Operators.SUBTRACTION || op == Operators.MULTIPLICATION || op == Operators.DIVISION) &&
                    (!ln.contains("%"))) {
                entryText.setText(ln + "%");
                BigDecimal percentage = new Percent().operation(ln);
                ln = percentage.toString();
            }
            // if RN is not empty then add % to it
            else if (!rn.isEmpty()) {
                entryText.setText(entryText.getText() + "%");
                BigDecimal percentage = new Percent().operation(rn);
                rn = percentage.toString();
            }
            // if RN is empty and LN is not empty and operators are present, then add 0 before %
            else if ((rn.isEmpty() && !ln.isEmpty()) &&
                    !(op == Operators.ADDITION || op == Operators.SUBTRACTION || op == Operators.MULTIPLICATION || op == Operators.DIVISION)) {
                rn = "0";
                entryText.setText(entryText.getText() + rn + "%");
            }
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

        invertButton.addActionListener(e -> {
            // if ln is not empty and op is empty
            if (!ln.isEmpty() && (op == Operators.EMPTY || op == Operators.EQUALS)) { // add inversed result to entryText in LN
                BigDecimal inverted_num = new Inverse().operation(ln);
                ln = inverted_num.toString();
                lnClick = ln.length();
                entryText.setText(ln);
            } else if (!rn.isEmpty()) { // add inversed result to RN
                BigDecimal inverted_num = new Inverse().operation(rn);
                String temp_text = entryText.getText();
                temp_text = temp_text.substring(0,temp_text.length() - rn.length());
                rn = inverted_num.toString();
                entryText.setText(temp_text.concat(rn));
            } else if ((!resultText.getText().isEmpty()) && (op == Operators.EMPTY || op == Operators.EQUALS)) { // square to result
                BigDecimal inverted_num = new Inverse().operation(resultText.getText());
                ln = inverted_num.toString();
                entryText.setText(ln);
            }
        });
        squareButton.addActionListener(e -> {
            // if ln is not empty and op is empty
            if (!ln.isEmpty() && (op == Operators.EMPTY || op == Operators.EQUALS)) { // add square'd result to entryText in LN
                BigDecimal squared_num = new Square().operation(ln);
                ln = squared_num.toString();
                lnClick = ln.length();
                entryText.setText(ln);
            } else if (!rn.isEmpty()) { // add square'd result to RN
                BigDecimal squared_num = new Square().operation(rn);
                String temp_text = entryText.getText();
                temp_text = temp_text.substring(0,temp_text.length() - rn.length());
                rn = squared_num.toString();
                entryText.setText(temp_text.concat(rn));
            } else if ((!resultText.getText().isEmpty()) && (op == Operators.EMPTY || op == Operators.EQUALS)) { // square to result
                BigDecimal squared_num = new Square().operation(resultText.getText());
                ln = squared_num.toString();
                entryText.setText(ln);
            }
        });
        rootButton.addActionListener(e -> {
            // if ln is not empty and op is empty
            if (!ln.isEmpty() && (op == Operators.EMPTY || op == Operators.EQUALS)) { // add square'd result to entryText in LN
                BigDecimal sqrt_num = new SquareRoot().operation(ln);
                ln = sqrt_num.toString();
                lnClick = ln.length();
                entryText.setText(ln);
            } else if (!rn.isEmpty()) { // add square'd result to RN
                BigDecimal sqrt_num = new SquareRoot().operation(rn);
                String temp_text = entryText.getText();
                temp_text = temp_text.substring(0,temp_text.length() - rn.length());
                rn = sqrt_num.toString();
                entryText.setText(temp_text.concat(rn));
            } else if ((!resultText.getText().isEmpty()) && (op == Operators.EMPTY || op == Operators.EQUALS)) { // square to result
                BigDecimal sqrt_num = new SquareRoot().operation(resultText.getText());
                ln = sqrt_num.toString();
                entryText.setText(ln);
            }
        });
        decimalBtn.addActionListener(e -> {
            // restrict from being used twice in a row
            if (!ln.isEmpty() && (this.op == Operators.EMPTY || this.op == Operators.EQUALS)) {
                if (ln.contains(".")) {
                    // do nothing
                } else {
                    ln = ln.concat(".");
                    entryText.setText(entryText.getText().concat("."));
                }
            } else if (!rn.isEmpty()){
                if (rn.contains(".")) {
                    // do nothing
                } else {
                    rn = rn.concat(".");
                    entryText.setText(entryText.getText().concat("."));
                }
            } else {
                ln = "0.";
                entryText.setText(".");
            }
        });
    }

    // Effect: adds number to the entry
    private void addNumber(int number) {
        String n = Integer.toString(number);
        if (op == Operators.EMPTY || op == Operators.EQUALS) {
            if (ln.isEmpty()) {
                entryText.setText("");
                ln = n;
                lnClick++;
                op = Operators.EMPTY;
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
        if ((!resultText.getText().isEmpty() && result == null) && ln.isEmpty()) {
            return;
        }

        //if left side is empty
        if ((ln.isEmpty() && (this.op == op.EMPTY || this.op == op.EQUALS)) || ((rn.isEmpty() && ln.isEmpty()) )) {
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
            if ((!rn.isEmpty())) {
                BigDecimal combined_num = calculateLeft();
                ln = combined_num.toString();
                rn = "";
                lnClick = 0;
                rnClick = 0;
                this.op = op;
                entryText.setText(entryText.getText() + " " + toStringOperator(op) + " ");
            }
            // if right side is empty and operators have not been used yet
            // or if special operators are enabled then allow addition of operators
            else if ((rn.isEmpty() && (this.op == Operators.EMPTY || this.op == Operators.EQUALS)) ||
            (rn.isEmpty() && specialOp)) { // otherwise just add operator at the end of left number
                if (specialOp) {
                    BigDecimal combined_num = calculateLeft();
                    ln = combined_num.toString();
                    specialOp = false;
                }
                this.op = op;
                entryText.setText(entryText.getText() + " " + toStringOperator(op) + " ");
            }
        }
    }

    // Modifies: Operator to string
    // Effects: give string output from Operator
    private String toStringOperator(Operators op) {
        String result;
        this.op = op;
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
                result = "1/";
                specialOp = true;
                break;
            case PERCENT:
                result = "%";
                specialOp = true;
                break;
            case SQUARE:
                result = "^2";
                specialOp = true;
                break;
            case SQUAREROOT:
                result = "√";
                specialOp = true;
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
            specialOp = false;
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
        specialOp = false;
        op = op.EMPTY;
    }

    // Effect: delete the last character of the entryText
    private void delete() {
        String entry = entryText.getText();
        // only perform if entry is not empty
        if (!entry.isEmpty()) {
            // If operator has been used and right side is not empty delete from right number
            if (op != op.EMPTY && !rn.isEmpty()) {
                // delete the last entry of the entryText
                // delete right numbers only if results have not been calculated
                String lastChar = entryText.getText().substring(entryText.getText().length() - 1);
                boolean deleteLastChar = isNumber(lastChar);
                if (deleteLastChar) {
                    entry = entry.substring(0, entry.length() - 1);
                    entryText.setText(entry);
                    rn = rn.substring(0, rn.length() - 1);
                    rnClick--;
                }
            // if operator has not been used and left side is not empty delete from left side
            } else if (!ln.isEmpty() && rn.isEmpty() && (op == op.EMPTY || op == op.EQUALS)){
                // delete the last entry of the entryText
                // delete left numbers only if operator has not been used
                String lastChar = entryText.getText().substring(entryText.getText().length() - 1);
                boolean deleteLastChar = isNumber(lastChar);
                if (deleteLastChar) {
                    entry = entry.substring(0, entry.length() - 1);
                    entryText.setText(entry);
                    ln = ln.substring(0, ln.length() - 1);
                    lnClick--;
                }
            }
        }
    }

    public boolean isNumber(String s) {
        Pattern p = Pattern.compile("[0-9]");
        if (p.matcher(s).matches()) {
            return true;
        } else {
            return false;
        }
    }

    // Effect: calculate left side and return number
    public BigDecimal calculateLeft() {
        BigDecimal result;
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
                    result = new BigDecimal("0");
                    resultText.setText("Division Undefined");
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
                    result = new BigDecimal("0");
                    resultText.setText("Invalid Input");
                }
                break;
            default:
                result = new BigDecimal(ln);
                break;
        }
        return result;
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
