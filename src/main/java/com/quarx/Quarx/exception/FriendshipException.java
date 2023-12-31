package com.quarx.Quarx.exception;

public class FriendshipException extends  RuntimeException{
    public FriendshipException(String message){
        super(message);
    }
    public FriendshipException(Throwable cause){
        super(cause);
    }
    public FriendshipException(String message, Throwable cause ){
        super(message,cause);
    }
}
