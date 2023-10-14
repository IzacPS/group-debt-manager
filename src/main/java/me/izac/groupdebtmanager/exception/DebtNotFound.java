package me.izac.groupdebtmanager.exception;

public class DebtNotFound extends RuntimeException{
    public DebtNotFound(Long id){
        super("Debt id={" + id + "} not found!");
    }
}
