package fr.univartois.raytracing.parser;

/**
 * Exception class for handling errors that occur during parsing of scene files.
 */
public class ParserException extends RuntimeException {

    /**
     * Constructs a new `ParserException` with the specified error message.
     *
     * @param message The error message describing the issue.
     */
    public ParserException(String message) {
        super(message);
    }
}
