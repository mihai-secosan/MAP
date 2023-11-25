package Service;

import Domain.Exceptions.FriendAlreadyExists;
import Domain.Exceptions.FriendDoesNotExist;
import Domain.Friendship;
import Domain.Tuple;
import Domain.Validators.ValidationException;
import Repo.Exceptions.UserAlreadyExists;
import Repo.Exceptions.UserDoesNotExist;
import Repo.UserRepo;
import Domain.User;
import Domain.CommunityFinder;

import java.util.HashSet;
import java.util.Optional;

public class Service
{
    private final UserRepo repo;

    public Service(UserRepo i_m_repo)
    {
        this.repo = i_m_repo;
    }

    /**
     *
     * @param id the id of the User that will be added to repo
     * @param name the name of the User that will be added to repo
     */
    public void AddUser(Long id, String name)
    {
        try
        {
            Optional<User> user = repo.save(new User(id, name));
            if (user.isPresent()) throw new UserAlreadyExists("A user with the ID: " + id + " already exists:\n" + user.get());
        }
        catch (UserAlreadyExists | ValidationException e)
        {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }

    /**
     *
     * @param id the id of the User that will be removed from repo
     */
    public void RemoveUser(Long id)
    {
        try
        {
            Optional<User> optionalUser = repo.findOne(id);
            if (optionalUser.isEmpty()) throw new UserDoesNotExist("The user with ID: " + id + " does not exist!");
            for (Long _id : optionalUser.get().get_friend_ids())
            {
                RemoveFriendship(_id, id);
            }
            repo.delete(id);
        }
        catch (UserDoesNotExist e)
        {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }

    /**
     *
     * @param userID1 the id of the first User that will be added to a friendship
     * @param userID2 the id of the second User that will be added to a friendship
     * @return a Tuple of Strings that are names of the 2 new friends
     */
    public Tuple<String, String> CreateFriendship(Long userID1, Long userID2)
    {
        Friendship friendship = new Friendship();
        friendship.setId(new Tuple<>(userID1, userID2));
        Tuple<String, String> friendNames;
        try
        {
            friendNames = repo.CreateFriendship(friendship);
        }
        catch (FriendAlreadyExists e)
        {
            System.err.println("An error occurred: " + e.getMessage());
            friendNames = new Tuple<>("", "");
        }

        return friendNames;
    }

    /**
     *
     * @param userID1 the id of the first User that will lose a friend
     * @param userID2 the id of the second User that will lose a friend
     * @return a Tuple of Strings that are names of the 2 initial friends
     */
    public Tuple<String, String> RemoveFriendship(Long userID1, Long userID2)
    {
        Friendship friendship = new Friendship();
        friendship.setId(new Tuple<>(userID1, userID2));
        Tuple<String, String> friendNames;
        try
        {
            friendNames = repo.RemoveFriendship(friendship);
        }
        catch (FriendDoesNotExist e)
        {
            System.err.println("An error occurred: " + e.getMessage());
            friendNames = new Tuple<>("", "");
        }

        return friendNames;
    }

    /**
     *
     * @return a list of all the Users in repo
     */
    public Iterable<User> getUsers()
    {
        return repo.findAll();
    }

    /**
     *
     * @return a HashSet of all the Users that are in the biggest community at the moment
     */
    public Iterable<User> getBiggestCommunity()
    {
        HashSet<User> biggestCommunity = new HashSet<>();
        for (User user : getUsers())
        {
            HashSet<User> currentCommunity = CommunityFinder.getCommunity(user);
            if (currentCommunity.size() > biggestCommunity.size())
            {
                biggestCommunity = currentCommunity;
            }
        }
        return biggestCommunity;
    }

    /**
     *
     * @return the number of different communities
     */
    public Integer getNumberOfCommunities()
    {
        Integer ct = 0;
        HashSet<User> users = new HashSet<>();
        getUsers().forEach(users::add);
        while (!users.isEmpty())
        {
            User user = null;
            for (User _user : users)
            {
                user = _user;
                break;
            }
            ct ++;
            users.removeAll(CommunityFinder.getCommunity(user));
        }
        return ct;
    }
}
