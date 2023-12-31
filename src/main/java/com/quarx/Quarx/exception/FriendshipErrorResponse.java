package com.quarx.Quarx.exception;

public class FriendshipErrorResponse {

    private int status;
    private String errorMessage;
    private long timeStamp;

    public FriendshipErrorResponse(){

    }

    public FriendshipErrorResponse(int status, String errorMessage, long timeStamp){
        this.status = status;
        this.errorMessage = errorMessage;
        this.timeStamp = timeStamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }
}
