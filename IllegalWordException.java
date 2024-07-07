/**
 * Class IllegalWordException extends from IllegalArgumentException
 * creates a IllegalWordException object derived from IllegalArgumentException, and uses its constructor to display a custom message
 */
public class IllegalWordException extends IllegalArgumentException {
    /**
     * 1-argument constructor to input string to be displayed in exception
     * @param s
     *          the string to be displayed in exception
     */
    public IllegalWordException(String s) {
        super(s);
    }
}
