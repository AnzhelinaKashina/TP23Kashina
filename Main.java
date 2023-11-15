import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

// Data Layer
class Event {
    private int id;
    private String type;
    private Date date;
    private String description;

    // Конструктор
    public Event(int id, String type, Date date, String description) {
        this.id = id;
        this.type = type;
        this.date = date;
        this.description = description;
    }

    // Геттеры
    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public Date getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }
}

// Бизнес-логика (Business Layer)
class EventService { }
    public List<Event> getEventsByDate(Date date) {

    }

    public List<Event> getAllEvents() {
        return events;
    }

    public void updateEvent(Event updatedEvent) {

    }

    private boolean isSameDate(Date date1, Date date2) {}

// Presentation Layer
class UserInterface {
    private EventService eventService;
    private Scanner scanner;

    public UserInterface() {
        eventService = new EventService();
        scanner = new Scanner(System.in);
    }

    public void showMenu() {

    }

    public void getUserInput() {
    }

    private void addEvent() {

    }

    private void deleteEvent() {

    }

    private void displayAllEvents() {

    }

    private void displayEventsByDate() {
    }

    private void editEvent() {

    }

    private void displayEvents(List<Event> events) {

    }

    private void showEventDetails(Event event) {
    }

// Пользователь
public class Main {
    public static void main(String[] args) {
        UserInterface userInterface = new UserInterface();
        userInterface.getUserInput();
    }
}
