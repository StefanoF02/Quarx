package com.quarx.Quarx.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class FriendshipExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<FriendshipErrorResponse> handleException(FriendshipException friendshipException){
        FriendshipErrorResponse error = new FriendshipErrorResponse(HttpStatus.NOT_FOUND.value(),friendshipException.getMessage(),System.currentTimeMillis());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler
    public ResponseEntity<FriendshipErrorResponse> handleException(Exception exception){
        FriendshipErrorResponse error = new FriendshipErrorResponse(HttpStatus.BAD_REQUEST.value(),exception.getMessage(),System.currentTimeMillis());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
