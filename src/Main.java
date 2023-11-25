import Repo.UserRepo;
import Domain.Validators.UserValidator;
import Service.Service;
import UI.UI;

public class Main
{
    public static void main(String[] args)
    {
        UserValidator validator = new UserValidator();
        UserRepo repo = new UserRepo(validator);
        Service service = new Service(repo);

        service.AddUser(1L, "Secosan Mihai Sebastian");
        service.AddUser(2L, "Silion Liviu Mihai");
        service.AddUser(3L, "Silvastru Oana Maria");
        service.AddUser(4L, "Sim Robert");
        service.AddUser(5L, "Sima Alin");
        service.AddUser(6L, "Sima Grigore");
        service.AddUser(7L, "Alexandru Macedon");
        service.AddUser(8L, "Johny Bravo");
        service.AddUser(9L, "Adolph Blaine Charles David Earl Frederick Gerald Hubert Irvin John Kenneth Lloyd Martin Nero Oliver Paul Quincy Randolph Sherman Thomas Uncas Victor William Xerxes Yancy Zeus");
        service.AddUser(10L, "Jojo");
        service.AddUser(11L, "Brenner Vanessa");

        service.CreateFriendship(1L, 2L);
        service.CreateFriendship(1L, 5L);
        service.CreateFriendship(1L, 6L);
        service.CreateFriendship(1L, 8L);
        service.CreateFriendship(1L, 9L);
        service.CreateFriendship(1L, 10L);
        service.CreateFriendship(8L, 10L);
        service.CreateFriendship(10L, 9L);
        service.CreateFriendship(4L, 7L);
        service.CreateFriendship(3L, 11L);

        UI ui = new UI(service);

        ui.Run();
    }
}