package pl.chmielewski.Euro.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(UserByIdNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionResponse UserByIdNotFoundHandler(UserByIdNotFoundException ex){
        return new ExceptionResponse(
                ex.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                new Date()
        );
    }

    @ResponseBody
    @ExceptionHandler(UserByEmailNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionResponse UserByEmailNotFoundHandler(UserByEmailNotFoundException ex){
        return new ExceptionResponse(
                ex.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                new Date()
        );
    }

    @ResponseBody
    @ExceptionHandler(PaymentNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionResponse PaymentNotFound (PaymentNotFoundException ex){
        return new ExceptionResponse(
                ex.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                new Date()
        );
    }

    @ResponseBody
    @ExceptionHandler(SameUserEmailException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ExceptionResponse SameUserEmailHandler(SameUserEmailException ex){
        return new ExceptionResponse(
                ex.getMessage(),
                HttpStatus.CONFLICT.value(),
                new Date()
        );
    }
}
