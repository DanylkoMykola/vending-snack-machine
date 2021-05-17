package danylko.vendingsnackmachine.exception;

public class PurchaseParseException extends Exception {
    public PurchaseParseException() {
    }

    public PurchaseParseException(String message) {
        super(message);
    }

    public PurchaseParseException(String message, Throwable cause) {
        super(message, cause);
    }
}
