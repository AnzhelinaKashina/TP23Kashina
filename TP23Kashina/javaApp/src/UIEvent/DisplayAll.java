package UIEvent;

import Models.Event;
import Services.EventService;

import java.util.List;
import java.util.Scanner;

public class DisplayAll extends UIEvent{
    @Override
    public void printEventName() {
        System.out.println("3. Просмотреть все события");
    }

    public DisplayAll(EventService service, java.util.Scanner scanner) {
        super(service, scanner);
    }

    @Override
    public void Invoke() {
        System.out.println("--- Все события ---");
        List<Event> events = Service.getAllEventsFromDatabase();

        if (events.isEmpty()) {
            System.out.println("Нет событий для отображения.");
        } else {
            for (Event event : events) {
                displayEventDetails(event);
            }
        }
    }
}
