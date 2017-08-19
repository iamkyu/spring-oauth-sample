package io.iamkyu.exception;

/**
 * @author Kj Nam
 */
public class DuplicateEmailException extends Exception {
    public DuplicateEmailException(String message) {
        super(message);
    }
}
