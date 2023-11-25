package Repo;

import Domain.Friendship;
import Domain.Tuple;
import Domain.User;
import Domain.Validators.UserValidator;
import Domain.Exceptions.FriendDoesNotExist;
import Domain.Exceptions.FriendAlreadyExists;

public class UserRepo extends InMemoryRepository<Long, User>
{
    public UserRepo(UserValidator validator)
    {
        super(validator);
    }

    /**
     *
     * @param friendship is the Friendship that will be made
     * @return a Tuple of Strings that are names of the 2 new friends
     * @throws FriendAlreadyExists if the ids in the tuple correspond to Users that are already friends
     */
    public Tuple<String, String> CreateFriendship(Friendship friendship) throws FriendAlreadyExists
    {
        Long leftID = friendship.getId().getLeft();
        Long rightID = friendship.getId().getRight();
        this.entities.get(leftID).addFriend(this.entities.get(rightID));
        this.entities.get(rightID).addFriend(this.entities.get(leftID));

        return new Tuple<>(this.entities.get(leftID).getName(), this.entities.get(rightID).getName());
    }

    /**
     *
     * @param friendship is the Friendship that will be broken
     * @return a Tuple of Strings that are names of the 2 initial friends
     * @throws FriendDoesNotExist if the ids in the tuple correspond to Users that aren't friends
     */
    public Tuple<String, String> RemoveFriendship(Friendship friendship) throws FriendDoesNotExist
    {
        Long leftID = friendship.getId().getLeft();
        Long rightID = friendship.getId().getRight();
        this.entities.get(leftID).removeFriend(rightID);
        this.entities.get(rightID).removeFriend(leftID);

        return new Tuple<>(this.entities.get(leftID).getName(), this.entities.get(rightID).getName());
    }
}
