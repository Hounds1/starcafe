package kr.ac.kopo.starcafe.domain.category.error;

public class MemberNotFoundException extends RuntimeException{
    public MemberNotFoundException(String message) {
        super(message);
    }
}
