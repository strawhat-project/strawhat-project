package com.strawhat.dal;


import com.strawhat.StrawHatException;

public class AffectedRowsException extends StrawHatException {

    private static final long serialVersionUID = 7554949316841263390L;

    public AffectedRowsException() {
        super();
    }

    public AffectedRowsException(String message) {
        super(message);
    }

    public AffectedRowsException(Throwable cause) {
        super(cause);
    }

    public AffectedRowsException(String message, Throwable cause) {
        super(message, cause);
    }
}
