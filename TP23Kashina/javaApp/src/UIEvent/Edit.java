package UIEvent;

import Models.Event;
import Services.EventService;

import java.util.Date;
import java.util.Scanner;

public class Edit extends UIEvent{
    @Override
    public void printEventName() {
        System.out.println("5. Редактировать событие");
    }

    public Edit(EventService service, java.util.Scanner scanner) {
        super(service, scanner);
    }

    @Override
    public void Invoke() {
        System.out.println("--- Редактирование события ---");
        System.out.print("Введите ID события для редактирования: ");
        int eventId = Scanner.nextInt();
        Scanner.nextLine(); // считывание перевода строки после ввода числа

        Event existingEvent = findEventById(eventId);

        if (existingEvent != null) {
            System.out.print("Введите новый тип события (" + existingEvent.getType() + "): ");
            String newType = Scanner.nextLine();
            System.out.print("Введите новую дату события (" + formatDate(existingEvent.getDate()) + "): ");
            String newDateString = Scanner.nextLine();
            System.out.print("Введите новое описание события (" + existingEvent.getDescription() + "): ");
            String newDescription = Scanner.nextLine();

            try {
                Date newDate = newType.isEmpty() ? existingEvent.getDate() : parseDate(newDateString);
                Event updatedEvent = new Event(
                        newType.isEmpty() ? existingEvent.getType() : newType,
                        newDate, newDescription.isEmpty() ? existingEvent.getDescription() : newDescription);
                Service.updateEventInDatabase(updatedEvent);
                System.out.println("Событие успешно отредактировано.");
            } catch (Exception e) {
                System.out.println("Ошибка при редактировании события: " + e.getMessage());
            }
        } else {
            System.out.println("Событие с указанным ID не найдено.");
        }
    }
}
