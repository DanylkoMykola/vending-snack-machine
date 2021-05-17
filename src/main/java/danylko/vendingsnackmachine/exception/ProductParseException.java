package danylko.vendingsnackmachine.exception;

public class ProductParseException extends Exception {

    public ProductParseException() {
    }

    public ProductParseException(String message) {
        super(message);
    }

    public ProductParseException(String message, Throwable cause) {
        super(message, cause);
    }
}
