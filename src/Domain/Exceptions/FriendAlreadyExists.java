package Domain.Exceptions;

public class FriendAlreadyExists extends Exception
{
    public FriendAlreadyExists()
    {
        super("Friend already exists!");
    }

    public FriendAlreadyExists(String message)
    {
        super(message);
    }
}
