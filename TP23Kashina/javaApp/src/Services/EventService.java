package Services;

import Models.Event;
import Repository.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
// Бизнес-логика (Business Layer)
public class EventService {
    private Connection connection;

    public EventService() {
        try {
            connection = ConnectionManager.connect();
            createTable();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void createTable() throws SQLException {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS events (" +
                "id INT PRIMARY KEY AUTO_INCREMENT," +
                "type VARCHAR(255) NOT NULL," +
                "date DATE NOT NULL," +
                "description VARCHAR(255) NOT NULL)";

        try (Statement statement = connection.createStatement()) {
            statement.execute(createTableSQL);
        }
    }

    public void addEventToDatabase(Event event) {
        String insertSQL = "INSERT INTO events (type, date, description) VALUES (?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {
            preparedStatement.setString(1, event.getType());
            preparedStatement.setDate(2, new java.sql.Date(event.getDate().getTime()));
            preparedStatement.setString(3, event.getDescription());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteEventFromDatabase(int eventId) {
        String deleteSQL = "DELETE FROM events WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL)) {
            preparedStatement.setInt(1, eventId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Event> getAllEventsFromDatabase() {
        List<Event> events = new ArrayList<>();
        String selectSQL = "SELECT * FROM events";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(selectSQL)) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String type = resultSet.getString("type");
                Date date = resultSet.getDate("date");
                String description = resultSet.getString("description");
                events.add(new Event( type, date, description));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return events;
    }

    public List<Event> getEventsByDateFromDatabase(Date date) {
        List<Event> events = new ArrayList<>();
        String selectSQL = "SELECT * FROM events WHERE date = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(selectSQL)) {
            preparedStatement.setDate(1, new java.sql.Date(date.getTime()));
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String type = resultSet.getString("type");
                Date eventDate = resultSet.getDate("date");
                String description = resultSet.getString("description");
                events.add(new Event( type, eventDate, description));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return events;
    }

    public void updateEventInDatabase(Event updatedEvent) {
        String updateSQL = "UPDATE events SET type = ?, date = ?, description = ? WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(updateSQL)) {
            preparedStatement.setString(1, updatedEvent.getType());
            preparedStatement.setDate(2, new java.sql.Date(updatedEvent.getDate().getTime()));
            preparedStatement.setString(3, updatedEvent.getDescription());
            preparedStatement.setInt(4, updatedEvent.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
