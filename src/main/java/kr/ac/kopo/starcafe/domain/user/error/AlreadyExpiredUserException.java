package kr.ac.kopo.starcafe.domain.user.error;

public class AlreadyExpiredUserException extends RuntimeException{
    public AlreadyExpiredUserException(String message) {
        super(message);
    }
}
