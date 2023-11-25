package Repo.Exceptions;

public class UserAlreadyExists extends Exception
{
    public UserAlreadyExists()
    {
        super("User already exists!");
    }

    public UserAlreadyExists(String message)
    {
        super(message);
    }
}
