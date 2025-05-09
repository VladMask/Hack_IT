package grsu.by.exception.handler;

import grsu.by.exception.EntityNotFoundException;
import grsu.by.exception.dto.ErrorMessageDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(value = {EntityNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessageDto mapNotFoundException(EntityNotFoundException ex) {

        String formattedDate = Instant.now().toString();

        return ErrorMessageDto.builder()
                .message(ex.getMessage())
                .date(formattedDate)
                .details(ex.getDetails())
                .build();
    }

}
