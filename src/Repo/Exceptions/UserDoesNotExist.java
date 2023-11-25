package Repo.Exceptions;

public class UserDoesNotExist extends Exception
{
    public UserDoesNotExist()
    {
        super("User does not exists!");
    }

    public UserDoesNotExist(String message)
    {
        super(message);
    }
}
