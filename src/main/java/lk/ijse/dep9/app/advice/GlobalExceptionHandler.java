package lk.ijse.dep9.app.advice;

import lk.ijse.dep9.app.dto.ErrorResponseMessageDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Throwable.class)
    public ErrorResponseMessageDTO uncaughtExceptions(Throwable t){
        t.printStackTrace();
        return new ErrorResponseMessageDTO(t.getMessage(), 405);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponseMessageDTO validationExceptions(MethodArgumentNotValidException e){
        String message = e.getFieldErrors().stream().map(err -> err.getField() + ": " + err.getDefaultMessage() + ", ")
                .reduce((prev, curr) -> prev + curr).get();
        return new ErrorResponseMessageDTO(message, 400);
    }

}
