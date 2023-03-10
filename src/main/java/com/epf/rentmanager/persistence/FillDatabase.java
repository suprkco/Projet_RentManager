package com.epf.rentmanager.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.h2.tools.DeleteDbFiles;

import com.epf.rentmanager.persistence.ConnectionManager;

public class FillDatabase {


    public static void main(String[] args) throws Exception {
        try {
            DeleteDbFiles.execute("~", "RentManagerDatabase", true);
            insertWithPreparedStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

	private static void insertWithPreparedStatement() throws SQLException {
        Connection connection = ConnectionManager.getConnection();
        PreparedStatement createPreparedStatement = null;

        List<String> createTablesQueries = new ArrayList<>();
        createTablesQueries.add("CREATE TABLE IF NOT EXISTS Client(id INT primary key auto_increment, client_id INT, lastname VARCHAR(100), firstname VARCHAR(100), email VARCHAR(100), birthday DATETIME)");
        createTablesQueries.add("CREATE TABLE IF NOT EXISTS Vehicle(id INT primary key auto_increment, manufacturer VARCHAR(100), model VARCHAR(100), nb_places TINYINT(255))");
        createTablesQueries.add("CREATE TABLE IF NOT EXISTS Reservation(id INT primary key auto_increment, client_id INT, foreign key(client_id) REFERENCES Client(id), vehicle_id INT, foreign key(vehicle_id) REFERENCES Vehicle(id), startTime DATETIME, endTime DATETIME)");

        try {
            connection.setAutoCommit(false);

            for (String createQuery : createTablesQueries) {
            	createPreparedStatement = connection.prepareStatement(createQuery);
	            createPreparedStatement.executeUpdate();
	            createPreparedStatement.close();
            }

            // Filling the database with Vehicles, Clients, and Reservations
            Statement stmt = connection.createStatement();
            stmt.execute("INSERT INTO Vehicle(manufacturer, model, nb_places) VALUES('Renault', 'RS01', 4)");
            stmt.execute("INSERT INTO Vehicle(manufacturer, model, nb_places) VALUES('Peugeot', '9x8 Hypercar', 4)");
            stmt.execute("INSERT INTO Vehicle(manufacturer, model, nb_places) VALUES('Seat', 'Cupra', 4)");
            stmt.execute("INSERT INTO Vehicle(manufacturer, model, nb_places) VALUES('Nissan', 'GTR35', 4)");
            stmt.execute("INSERT INTO Vehicle(manufacturer, model, nb_places) VALUES('BMW', 'M3', 4)");
            stmt.execute("INSERT INTO Vehicle(manufacturer, model, nb_places) VALUES('Audi', 'RS7', 4)");
            stmt.execute("INSERT INTO Vehicle(manufacturer, model, nb_places) VALUES('Ford', 'Mustang', 4)");
            stmt.execute("INSERT INTO Vehicle(manufacturer, model, nb_places) VALUES('Mercedes', 'AMG GT', 4)");

            stmt.execute("INSERT INTO Client(lastname, firstname, email, birthday) VALUES('Dupont', 'Jean', 'jean.dupont@email.com', '1988-01-22')");
            stmt.execute("INSERT INTO Client(lastname, firstname, email, birthday) VALUES('Morin', 'Sabrina', 'sabrina.morin@email.com', '1988-01-22')");
            stmt.execute("INSERT INTO Client(lastname, firstname, email, birthday) VALUES('Afleck', 'Steeve', 'steeve.afleck@email.com', '1988-01-22')");
            stmt.execute("INSERT INTO Client(lastname, firstname, email, birthday) VALUES('Rousseau', 'Jacques', 'jacques.rousseau@email.com', '1988-01-22')");
            stmt.execute("INSERT INTO Client(lastname, firstname, email, birthday) VALUES('Girard', 'Marie', 'marie.girard@email.com', '1985-06-11')");
            stmt.execute("INSERT INTO Client(lastname, firstname, email, birthday) VALUES('Côté', 'Sophie', 'sophie.cote@email.com', '1990-08-22')");
            stmt.execute("INSERT INTO Client(lastname, firstname, email, birthday) VALUES('Lavoie', 'Louis', 'louis.lavoie@email.com', '1982-02-28')");
            stmt.execute("INSERT INTO Client(lastname, firstname, email, birthday) VALUES('Bernard', 'François', 'francois.bernard@email.com', '1984-11-15')");

            stmt.execute("INSERT INTO Reservation(client_id, vehicle_id, startTime, endTime) VALUES(1, 1, '2023-03-15 10:00:00', '2023-03-15 12:00:00')");
            stmt.execute("INSERT INTO Reservation(client_id, vehicle_id, startTime, endTime) VALUES(2, 2, '2023-03-15 14:00:00', '2023-03-16 10:00:00')");
            stmt.execute("INSERT INTO Reservation(client_id, vehicle_id, startTime, endTime) VALUES(3, 3, '2023-03-17 09:00:00', '2023-03-18 17:00:00')");
            stmt.execute("INSERT INTO Reservation(client_id, vehicle_id, startTime, endTime) VALUES(4, 4, '2023-03-18 10:00:00', '2023-03-19 10:00:00')");
            stmt.execute("INSERT INTO Reservation(client_id, vehicle_id, startTime, endTime) VALUES(1, 2, '2023-03-20 09:00:00', '2023-03-21 17:00:00')");
            stmt.execute("INSERT INTO Reservation(client_id, vehicle_id, startTime, endTime) VALUES(2, 3, '2023-03-22 08:00:00', '2023-03-22 17:00:00')");
            stmt.execute("INSERT INTO Reservation(client_id, vehicle_id, startTime, endTime) VALUES(3, 4, '2023-03-23 09:00:00', '2023-03-24 10:00:00')");

            connection.commit();
            System.out.println("Success!");
        } catch (SQLException e) {
            System.out.println("Exception Message " + e.getLocalizedMessage());
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
    }
}
