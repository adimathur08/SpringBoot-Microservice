package com.amathur.userdirectory.exception;

import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserException extends Exception
{
    UserException()
    {
        super();
    }

    public UserException(String message)
    {
        super(message);
    }

    public UserException(String message, Throwable e)
    {
        super(message, e);
    }
}
