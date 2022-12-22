package lk.ijse.dep9.app.advice;

import lk.ijse.dep9.app.exceptions.AccessDeniedException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.*;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

//    @ExceptionHandler(Throwable.class)
//    public ErrorResponseMessageDTO uncaughtExceptions(Throwable t){
//        t.printStackTrace();
//        return new ErrorResponseMessageDTO(t.getMessage(), 405);
//    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, Object> validationExceptions(MethodArgumentNotValidException e){
        HashMap<String, Object> errorAttributes = new LinkedHashMap<>();
        errorAttributes.put("status", HttpStatus.BAD_REQUEST.value());
        errorAttributes.put("error", HttpStatus.BAD_REQUEST.getReasonPhrase());
        errorAttributes.put("timestamp", new Date().toString());
        List<String> validationErrList = e.getFieldErrors().stream()
                .map(err -> err.getField() + ": " + err.getDefaultMessage())
                .collect(Collectors.toList());
        errorAttributes.put("errors", validationErrList);
        return errorAttributes;
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(DuplicateKeyException.class)
    public Map<String, Object> duplicateEntityExceptionHandler(){
        HashMap<String, Object> errorAttributes = new LinkedHashMap<>();
        errorAttributes.put("status", HttpStatus.CONFLICT.value());
        errorAttributes.put("error", HttpStatus.CONFLICT.getReasonPhrase());
        errorAttributes.put("message", "Duplicate entity found");
        errorAttributes.put("timestamp", new Date().toString());
        return errorAttributes;
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(AccessDeniedException.class)
    public Map<String, Object> accessDeniedExceptionHandler(){
        HashMap<String, Object> errorAttributes = new LinkedHashMap<>();
        errorAttributes.put("status", HttpStatus.FORBIDDEN.value());
        errorAttributes.put("error", HttpStatus.FORBIDDEN.getReasonPhrase());
        errorAttributes.put("message", "Access Denied");
        errorAttributes.put("timestamp", new Date().toString());
        return errorAttributes;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EmptyResultDataAccessException.class)
    public Map<String, Object> emptyResultDataAccessExceptionHandler(){
        HashMap<String, Object> errorAttributes = new LinkedHashMap<>();
        errorAttributes.put("status", HttpStatus.NOT_FOUND.value());
        errorAttributes.put("error", HttpStatus.NOT_FOUND.getReasonPhrase());
        errorAttributes.put("message", "Entity not found");
        errorAttributes.put("timestamp", new Date().toString());
        return errorAttributes;
    }

}
