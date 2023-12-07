import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
class UserInterface implements OrganizerInterface {
    private EventService eventService;
    private Scanner scanner;

    public UserInterface() {
        eventService = new EventService();
        scanner = new Scanner(System.in);
    }

    public void showMenu() {
        System.out.println("--- Органайзер ---");
        System.out.println("1. Добавить событие");
        System.out.println("2. Удалить событие");
        System.out.println("3. Просмотреть все события");
        System.out.println("4. Просмотреть события по дате");
        System.out.println("5. Редактировать событие");
        System.out.println("0. Выйти");
        System.out.print("Выберите операцию: ");
    }

    public void getUserInput() {
        int choice;
        do {
            showMenu();
            choice = scanner.nextInt();
            scanner.nextLine(); // считывание перевода строки после ввода числа

            switch (choice) {
                case 1:
                    addEvent();
                    break;
                case 2:
                    deleteEvent();
                    break;
                case 3:
                    displayAllEvents();
                    break;
                case 4:
                    displayEventsByDate();
                    break;
                case 5:
                    editEvent();
                    break;
                case 0:
                    System.out.println("До свидания!");
                    break;
                default:
                    System.out.println("Некорректный выбор. Попробуйте еще раз.");
            }

            System.out.println();
        } while (choice != 0);
    }

    public void addEvent() {
        System.out.println("--- Добавление события ---");
        System.out.print("Введите тип события (День рождения/Важная встреча): ");
        String type = scanner.nextLine();
        System.out.print("Введите дату события (ГГГГ-ММ-ДД): ");
        String dateString = scanner.nextLine();
        System.out.print("Введите описание события: ");
        String description = scanner.nextLine();

        try {
            Date date = parseDate(dateString);
            Event event = new Event(type, date, description);
            eventService.addEventToDatabase(event);
            System.out.println("Событие успешно добовлено.");
        } catch (Exception e) {
            System.out.println("Ошибка при добавлении события: " + e.getMessage());
        }
    }

    public void deleteEvent() {
        System.out.println("--- Удаление события ---");
        System.out.print("Введите ID события для удаления: ");
        int eventId = scanner.nextInt();
        scanner.nextLine(); // считывание перевода строки после ввода числа

        eventService.deleteEventFromDatabase(eventId);
        System.out.println("Событие успешно удалено.");
    }
    public void displayAllEvents() {
        System.out.println("--- Все события ---");
        List<Event> events = eventService.getAllEventsFromDatabase();

        if (events.isEmpty()) {
            System.out.println("Нет событий для отображения.");
        } else {
            for (Event event : events) {
                displayEventDetails(event);
            }
        }
    }

    public void displayEventsByDate() {
        System.out.println("--- События по дате ---");
        System.out.print("Введите дату (ГГГГ-ММ-ДД): ");
        String dateString = scanner.nextLine();

        try {
            Date date = parseDate(dateString);
            List<Event> events = eventService.getEventsByDateFromDatabase(date);

            if (events.isEmpty()) {
                System.out.println("Нет событий для отображения на указанную дату.");
            } else {
                for (Event event : events) {
                    displayEventDetails(event);
                }
            }
        } catch (Exception e) {
            System.out.println("Ошибка при отображении событий по дате: " + e.getMessage());
        }
    }

    public void editEvent() {
        System.out.println("--- Редактирование события ---");
        System.out.print("Введите ID события для редактирования: ");
        int eventId = scanner.nextInt();
        scanner.nextLine(); // считывание перевода строки после ввода числа

        Event existingEvent = findEventById(eventId);

        if (existingEvent != null) {
            System.out.print("Введите новый тип события (" + existingEvent.getType() + "): ");
            String newType = scanner.nextLine();
            System.out.print("Введите новую дату события (" + formatDate(existingEvent.getDate()) + "): ");
            String newDateString = scanner.nextLine();
            System.out.print("Введите новое описание события (" + existingEvent.getDescription() + "): ");
            String newDescription = scanner.nextLine();

            try {
                Date newDate = newType.isEmpty() ? existingEvent.getDate() : parseDate(newDateString);
                Event updatedEvent = new Event(
                        newType.isEmpty() ? existingEvent.getType() : newType,
                        newDate, newDescription.isEmpty() ? existingEvent.getDescription() : newDescription);
                eventService.updateEventInDatabase(updatedEvent);
                System.out.println("Событие успешно отредактировано.");
            } catch (Exception e) {
                System.out.println("Ошибка при редактировании события: " + e.getMessage());
            }
        } else {
            System.out.println("Событие с указанным ID не найдено.");
        }
    }
    protected Event findEventById(int eventId) {
        List<Event> events = eventService.getAllEventsFromDatabase();
        for (Event event : events) {
            if (event.getId() == eventId) {
                return event;
            }
        }
        return null;
    }

    protected void displayEventDetails(Event event) {
        System.out.println("ID: " + event.getId());
        System.out.println("Тип: " + event.getType());
        System.out.println("Дата: " + formatDate(event.getDate()));
        System.out.println("Описание: " + event.getDescription());
        System.out.println("------------------------------");
    }

    protected Date parseDate(String dateString) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.parse(dateString);
    }

    protected String formatDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(date);
    }


}
