package UI;

import Domain.Tuple;
import Service.Service;
import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;
import java.util.function.Consumer;

public class UI
{
    private final Service service;
    private final Scanner scanner;
    private final Map<String, Consumer<String>> behaviours;

    public UI(Service _service)
    {
        this.service = _service;
        scanner = new Scanner(System.in);
        behaviours = new HashMap<>();
        behaviours.put("Print", this::Print);
        behaviours.put("AddUser", this::AddUser);
        behaviours.put("RemoveUser", this::RemoveUser);
        behaviours.put("CreateFriendship", this::CreateFriendship);
        behaviours.put("BreakFriendship", this::BreakFriendship);
        behaviours.put("Communities", this::Communities);
        behaviours.put("LargestCommunity", this::LargestCommunity);
    }

    /**
     *
     * @param command the command to execute
     * The function prints the contents of the repo
     */
    public void Print(String command)
    {
        service.getUsers().forEach(System.out::println);
    }

    /**
     *
     * @param command the command to execute
     * The function transforms the command into data used to create a User and add it into the repo
     */
    public void AddUser(String command)
    {
        String[] words = command.split(" ");
        if (words.length < 3)
        {
            System.err.println("Format: 'AddUser [Long: id] [String: name]'");
        }
        long id;
        try
        {
            id = Long.parseLong(words[1]);
        }
        catch (NumberFormatException e)
        {
            System.err.println("id must be type Long!");
            return;
        }
        StringBuilder name = new StringBuilder(words[2]);
        for (int i = 3; i < words.length; i ++)
        {
            name.append(" ").append(words[i]);
        }

        service.AddUser(id, new String(name));
    }

    /**
     *
     * @param command the command to execute
     * The function transforms the command into data used to delete a User from the repo
     */
    public void RemoveUser(String command)
    {
        String[] words = command.split(" ");
        if (words.length < 2)
        {
            System.err.println("Format: 'RemoveUser [Long: id]'");
        }
        long id;
        try
        {
            id = Long.parseLong(words[1]);
        }
        catch (NumberFormatException e)
        {
            System.err.println("id must be type Long!");
            return;
        }
        service.RemoveUser(id);
    }

    /**
     *
     * @param command the command to execute
     * The function transforms the command into data (2 Longs) and creates a friendship of them
     */
    public void CreateFriendship(String command)
    {
        String[] words = command.split(" ");
        if (words.length != 3)
        {
            System.err.println("Format: 'CreateFriendship [Long: id1] [Long: id2]'");
        }
        long id1, id2;
        try
        {
            id1 = Long.parseLong(words[1]);
            id2 = Long.parseLong(words[2]);
        }
        catch (NumberFormatException e)
        {
            System.err.println("id must be type Long!");
            return;
        }
        Tuple<String, String> friendNames = service.CreateFriendship(id1, id2);
        System.out.println("'" + friendNames.getLeft() + "' and '" + friendNames.getRight() + "' are now friends!");
    }

    /**
     *
     * @param command the command to execute
     * The function transforms the command into data (2 Longs) and breaks the friendship between them
     */
    public void BreakFriendship(String command)
    {
        String[] words = command.split(" ");
        if (words.length != 3)
        {
            System.err.println("Format: 'BreakFriendship [Long: id1] [Long: id2]'");
        }
        long id1, id2;
        try
        {
            id1 = Long.parseLong(words[1]);
            id2 = Long.parseLong(words[2]);
        }
        catch (NumberFormatException e)
        {
            System.err.println("id must be type Long!");
            return;
        }
        Tuple<String, String> friendNames = service.RemoveFriendship(id1, id2);
        System.out.println("'" + friendNames.getLeft() + "' and '" + friendNames.getRight() + "' are no longer friends!");
    }

    /**
     *
     * @param command the command to execute
     * The function prints the number of communities
     */
    public void Communities(String command)
    {
        System.out.println("The number of communities is: " + service.getNumberOfCommunities());
    }

    /**
     *
     * @param command the command to execute
     * The function prints the biggest community
     */
    public void LargestCommunity(String command)
    {
        service.getBiggestCommunity().forEach(System.out::println);
    }

    /**
     * The function that prints the menu and reads the commands in a recursive loop
     */
    public void Run()
    {
        System.out.println("\nThe commands are as fallows: ");
        System.out.println("Print");
        System.out.println("AddUser [Long: id] [String: name]");
        System.out.println("RemoveUser [Long: id]");
        System.out.println("CreateFriendship [Long: id1] [Long: id2]");
        System.out.println("BreakFriendship [Long: id1] [Long: id2]");
        System.out.println("Communities");
        System.out.println("LargestCommunity");
        System.out.println("Exit\n");

        String command = scanner.nextLine();

        if (command.equals("Exit"))
        {
            return;
        }

        Consumer<String> behaviour = behaviours.get(command.split(" ")[0]);
        if (behaviour == null)
        {
            System.out.println("Invalid command!");
            Run();
            return;
        }

        behaviour.accept(command);

        Run();
    }
}
