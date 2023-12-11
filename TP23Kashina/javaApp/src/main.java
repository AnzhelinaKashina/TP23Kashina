import Services.EventService;
import View.UserInterface;

// Пользователь (User)
public class main {
    public static void main(String[] args) {
        EventService service = new EventService();
        UserInterface userInterface = new UserInterface(service);
        userInterface.getUserInput();
    }
}
