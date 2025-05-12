package grsu.by.exception;

public class MappingException extends RuntimeException {
    public MappingException(String message) {
        super(MappingException.class.getName() + " on " + message);
    }
}
