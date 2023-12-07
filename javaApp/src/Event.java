import java.util.Date;
// Слой данных (Data Layer)
class Event {
    private static int nextId = 1;

    private int id;
    private String type;
    private Date date;
    private String description;

    // Конструктор
    public Event(String type, Date date, String description) {
        this.id = nextId++;
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
