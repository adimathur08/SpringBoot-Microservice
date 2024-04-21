package com.amathur.addressbook.exception;

public class AddressException extends Exception
{
    public AddressException()
    {
        super();
    }

    public AddressException(String message)
    {
        super(message);
    }

    public AddressException(String message, Throwable e)
    {
        super(message, e);
    }

}
