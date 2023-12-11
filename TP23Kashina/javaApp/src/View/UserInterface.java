package View;

import Models.Event;
import Services.*;
import UIEvent.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class UserInterface  {
    private EventService eventService;
    private Scanner scanner;

    private Dictionary<Integer, UIEvent> commands;

    public UserInterface(EventService service) {
        eventService = service;
        scanner = new Scanner(System.in);
        commands = new Hashtable<Integer, UIEvent>();

        System.out.println("--- Органайзер ---");
        System.out.print("Выберите операцию: ");
        commands.put(0, new Exit(eventService, scanner));
        commands.put(1, new Addition(eventService, scanner));
        commands.put(2, new Delete(eventService, scanner));
        commands.put(3, new DisplayAll(eventService, scanner));
        commands.put(4, new DisplayByDate(eventService, scanner));
        commands.put(5, new Edit(eventService, scanner));
    }

    public void getUserInput() {
        int choice;
        do {
            choice = scanner.nextInt();
            scanner.nextLine(); // считывание перевода строки после ввода числа

            UIEvent event = commands.get(choice);

            if (event == null) {
                System.out.println("Некорректный выбор. Попробуйте еще раз.");
                return;
            }

            event.Invoke();

            System.out.println();
        } while (choice != 0);
    }
}
