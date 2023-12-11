package UIEvent;

import Services.EventService;

import java.util.Scanner;

public class Exit extends UIEvent{
    @Override
    public void printEventName() {
        System.out.println("0. Выйти");
    }

    public Exit(EventService service, java.util.Scanner scanner) {
        super(service, scanner);
    }

    @Override
    public void Invoke() {
        System.out.println("До свидания!");
    }
}
