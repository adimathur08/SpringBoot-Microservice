package com.amathur.addressbook.exception;

public class AddressNotFoundException extends AddressException
{
    public AddressNotFoundException()
    {
        super();
    }

    public AddressNotFoundException(String message)
    {
        super(message);
    }

    public AddressNotFoundException(String message, Throwable e)
    {
        super(message, e);
    }
}
