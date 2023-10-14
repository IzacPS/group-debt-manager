package me.izac.groupdebtmanager.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ApplicationExceptionHandle extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserNotFound.class)
    public ResponseEntity<String> handleUserNotFoundException(UserNotFound userNotFound){
        return ResponseEntity.badRequest().body(userNotFound.getMessage());
    }
    @ExceptionHandler(GroupNotFound.class)
    public ResponseEntity<String> handleGroupNotFoundException(GroupNotFound groupNotFound){
        return ResponseEntity.badRequest().body(groupNotFound.getMessage());
    }
    @ExceptionHandler(DebtNotFound.class)
    public ResponseEntity<String> handleDebtNotFoundException(DebtNotFound debtNotFound){
        return ResponseEntity.badRequest().body(debtNotFound.getMessage());
    }
}
