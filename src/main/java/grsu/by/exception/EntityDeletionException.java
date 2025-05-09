package grsu.by.exception;

import java.util.Map;

public class EntityDeletionException extends AbstractException {

    private static final String MESSAGE = " Can not delete specified entity ";
    public EntityDeletionException(Map<String, String> details) {
        super(MESSAGE, details);
    }
}
