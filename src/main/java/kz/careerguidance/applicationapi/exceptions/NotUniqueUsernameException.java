package kz.careerguidance.applicationapi.exceptions;

public class NotUniqueUsernameException extends RuntimeException{
    public NotUniqueUsernameException(String errorMessage) {
        super(errorMessage);
    }
}
