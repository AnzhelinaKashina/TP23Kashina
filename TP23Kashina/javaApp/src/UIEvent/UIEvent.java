package UIEvent;

import Models.Event;
import Services.EventService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public abstract class UIEvent {
    protected EventService Service;
    protected Scanner Scanner;

    public abstract void printEventName();

    public UIEvent(EventService service, java.util.Scanner scanner) {
        Service = service;
        Scanner = scanner;

        printEventName();
    }

    public abstract void Invoke();

    protected Date parseDate(String dateString) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.parse(dateString);
    }

    protected void displayEventDetails(Event event) {
        System.out.println("ID: " + event.getId());
        System.out.println("Тип: " + event.getType());
        System.out.println("Дата: " + formatDate(event.getDate()));
        System.out.println("Описание: " + event.getDescription());
        System.out.println("------------------------------");
    }

    protected String formatDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(date);
    }

    protected Event findEventById(int eventId) {
        List<Event> events = Service.getAllEventsFromDatabase();
        for (Event event : events) {
            if (event.getId() == eventId) {
                return event;
            }
        }
        return null;
    }
}
