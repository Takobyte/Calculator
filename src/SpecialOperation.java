import java.math.BigDecimal;

// Special Operators that do not require two numbers to perform
// Mathematical operations
public interface SpecialOperation {

    // Effect: given a number perform mathematical operation and return
    // BigDecimal
    BigDecimal operation(String number);
}
