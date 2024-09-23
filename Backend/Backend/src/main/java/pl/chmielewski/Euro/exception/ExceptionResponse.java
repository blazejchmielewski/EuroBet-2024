package pl.chmielewski.Euro.exception;

import java.util.Date;

public record ExceptionResponse (
    String message,
    Integer code,
    Date timestamp
){
}
