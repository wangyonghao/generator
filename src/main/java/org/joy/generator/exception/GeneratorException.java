package org.joy.generator.exception;

public class GeneratorException extends RuntimeException {

    private static final long serialVersionUID = -4441995852407613175L;

    public GeneratorException() {}

    public GeneratorException(Throwable cause) {
        super(cause);
    }

    public GeneratorException(String message) {
        super(message);
    }

    public GeneratorException(String message, Throwable cause) {
        super(message, cause);
    }

}
