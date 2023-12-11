package UIEvent;

import Services.EventService;

import java.util.Scanner;

public class Delete extends UIEvent {

    @Override
    public void printEventName() {
        System.out.println("2. Удалить событие");
    }

    public Delete(EventService service, java.util.Scanner scanner) {
        super(service, scanner);
    }

    @Override
    public void Invoke() {
        System.out.println("--- Удаление события ---");
        System.out.print("Введите ID события для удаления: ");
        int eventId = Scanner.nextInt();
        Scanner.nextLine(); // считывание перевода строки после ввода числа

        Service.deleteEventFromDatabase(eventId);
        System.out.println("Событие успешно удалено.");
    }
}
