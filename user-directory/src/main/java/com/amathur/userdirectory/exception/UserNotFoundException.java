package com.amathur.userdirectory.exception;

public class UserNotFoundException extends UserException
{
    public UserNotFoundException()
    {
        super();
    }

    public UserNotFoundException(String message)
    {
        super(message);
    }

    public UserNotFoundException(String message, Throwable e)
    {
        super(message, e);
    }
}
