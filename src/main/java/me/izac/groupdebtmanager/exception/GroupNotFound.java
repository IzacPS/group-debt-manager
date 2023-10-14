package me.izac.groupdebtmanager.exception;

public class GroupNotFound extends RuntimeException{
    public GroupNotFound(Long id){
        super("Group id={" + id + "} not found!");
    }
}
