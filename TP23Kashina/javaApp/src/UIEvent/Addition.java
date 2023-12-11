package UIEvent;

import Models.Event;
import Services.EventService;

import java.util.Date;
import java.util.Scanner;

public class Addition extends UIEvent{
    @Override
    public void printEventName() {
        System.out.println("1. Добавить событие");
    }

    public Addition(EventService service, java.util.Scanner scanner) {
        super(service, scanner);
    }

    @Override
    public void Invoke() {
        System.out.println("--- Добавление события ---");
        System.out.print("Введите тип события (День рождения/Важная встреча): ");
        String type = Scanner.nextLine();
        System.out.print("Введите дату события (ГГГГ-ММ-ДД): ");
        String dateString = Scanner.nextLine();
        System.out.print("Введите описание события: ");
        String description = Scanner.nextLine();

        try {
            Date date = parseDate(dateString);
            Event event = new Event(type, date, description);
            Service.addEventToDatabase(event);
            System.out.println("Событие успешно добовлено.");
        } catch (Exception e) {
            System.out.println("Ошибка при добавлении события: " + e.getMessage());
        }
    }
}
