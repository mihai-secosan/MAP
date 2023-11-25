package Domain;

import java.util.HashMap;
import java.util.StringJoiner;

import Domain.Exceptions.FriendAlreadyExists;
import Domain.Exceptions.FriendDoesNotExist;

public class User extends Entity<Long>
{
    private String name;
    private final HashMap<Long, User> friend_list;

    public User(Long _id, String _name)
    {
        setId(_id);
        this.name = _name;
        friend_list = new HashMap<>();
    }

    /**
     *
     * @return the name of this
     */
    public String getName()
    {
        return this.name;
    }

    /**
     *
     * @param NewName is the new name of the User
     */
    public void setName(String NewName)
    {
        this.name = NewName;
    }

    /**
     *
     * @param friend another User
     * @throws FriendAlreadyExists if the 2 users are friends already
     */
    public void addFriend(User friend) throws FriendAlreadyExists
    {
        if (friend_list.containsKey(friend.getId()))
        {
            throw new FriendAlreadyExists("User already in friend list");
        }
        friend_list.put(friend.getId(), friend);
    }

    /**
     *
     * @param id is the id of the friend that will be removed
     * @throws FriendDoesNotExist if id isn't a key from the friend_list
     */
    public void removeFriend(long id) throws FriendDoesNotExist
    {
        if (!friend_list.containsKey(id))
        {
            throw new FriendDoesNotExist("Friend does not exist");
        }
        friend_list.remove(id);
    }

    /**
     *
     * @return a list of all the friends
     */
    public Iterable<User> getFriends()
    {
        return friend_list.values();
    }

    /**
     *
     * @return an array of all the friend's ids
     */
    public Long[] get_friend_ids()
    {
        return friend_list.keySet().toArray(new Long[0]);
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (obj == null || getClass() != obj.getClass())
        {
            return false;
        }
        User other = (User) obj;
        return getId().equals(other.getId());
    }

    @Override
    public int hashCode()
    {
        return id.hashCode();
    }

    @Override
    public String toString()
    {
        StringJoiner friendNames = new StringJoiner(", ");
        friend_list.values().forEach(friend -> friendNames.add(friend.getName()));
        return "ID: " + id + " | Name: '" + name + "' | Friends: " + friendNames;
    }
}
