package me.izac.groupdebtmanager.exception;

public class UserNotFound extends RuntimeException{
    public UserNotFound(Long id){
        super("User id={ " + id + " } not found!");
    }
    public UserNotFound(String email){
        super("User email={ " + email + " } not found!");
    }
}
