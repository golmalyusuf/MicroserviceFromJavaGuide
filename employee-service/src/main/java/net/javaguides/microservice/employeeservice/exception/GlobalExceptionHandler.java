package net.javaguides.microservice.employeeservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

/*Class for handling global Exception*/
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFound resourceNotFound, WebRequest webRequest){
        ErrorDetails userNotFound = new ErrorDetails(
                LocalDateTime.now(),
                resourceNotFound.getMessage(),
                webRequest.getDescription(false),
                "USER_NOT_FOUND"

        );
        return new ResponseEntity<>(userNotFound, HttpStatus.NOT_FOUND);
    }
}
