package Domain;

import java.util.HashSet;
import java.util.Queue;
import java.util.LinkedList;

public class CommunityFinder
{
    /**
     *
     * @param user is the User whose community will be returned
     * @return the set of all the other Users in the community
     */
    public static HashSet<User> getCommunity(User user)
    {
        HashSet<User> community = new HashSet<>();
        community.add(user);
        Queue<User> queue = new LinkedList<>();
        queue.add(user);
        while (!queue.isEmpty())
        {
            User next = queue.peek();
            queue.remove();
            for (User friend : next.getFriends())
            {
                if (community.add(friend))
                {
                    queue.add(friend);
                }
            }
        }
        return community;
    }
}
