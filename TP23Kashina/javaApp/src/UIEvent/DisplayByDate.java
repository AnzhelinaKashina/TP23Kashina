package UIEvent;

import Models.Event;
import Services.EventService;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class DisplayByDate extends UIEvent{
    @Override
    public void printEventName() {
        System.out.println("4. Просмотреть события по дате");
    }

    public DisplayByDate(EventService service, java.util.Scanner scanner) {
        super(service, scanner);
    }

    @Override
    public void Invoke() {
        System.out.println("--- События по дате ---");
        System.out.print("Введите дату (ГГГГ-ММ-ДД): ");
        String dateString = Scanner.nextLine();

        try {
            Date date = parseDate(dateString);
            List<Event> events = Service.getEventsByDateFromDatabase(date);

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
}
