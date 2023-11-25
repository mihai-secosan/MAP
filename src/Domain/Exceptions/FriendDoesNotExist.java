package Domain.Exceptions;

public class FriendDoesNotExist extends Exception
{
    public FriendDoesNotExist()
    {
        super("Friend does not exists!");
    }

    public FriendDoesNotExist(String message)
    {
        super(message);
    }
}
