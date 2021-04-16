package net.b0at.torsion;

public class TorsionException extends RuntimeException {
    public TorsionException(String message) {
        super(message);
    }

    public TorsionException(String message, Throwable cause) {
        super(message, cause);
    }
}
