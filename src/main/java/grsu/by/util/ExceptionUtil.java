package grsu.by.util;

import grsu.by.exception.EntityDeletionException;
import grsu.by.exception.EntityNotFoundException;
import lombok.experimental.UtilityClass;

import java.time.Instant;
import java.util.Map;

@UtilityClass
public class ExceptionUtil {

    public <T> EntityNotFoundException throwEntityNotFoundException(Class<T> clazz, String id) {
        return new EntityNotFoundException(
                Map.of(
                        "class", clazz.getName(),
                        "id", id,
                        "date", Instant.now().toString()
                )
        );
    }

    public <T> EntityDeletionException throwEntityDeletionException(Class<T> clazz, String id) {
        return new EntityDeletionException(
                Map.of(
                        "class", clazz.getName(),
                        "id", id,
                        "date", Instant.now().toString()
                )
        );
    }

}
