import java.util.Date;
import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
/*import java.util.Date;
import java.util.List;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;*/

public class cww {

    public class CarCategory {
        private Long id;
        private String name;
        private double dailyRate;

        public double getDailyRate() {
            return dailyRate;
        }


    }

    public class Car {
        private Long id;
        private String brand;
        private String model;
        private int year;
        private CarCategory category;

        public CarCategory getCategory() {
            return category;
        }



    }

    public class Customer {
        private Long id;
        private String name;
        private String email;


    }

    public class Rental {
        private Long id;
        private Car car;
        private Customer customer;
        private Date rentalDate;
        private Date returnDate;



        public double calculateRentalPrice() {
            long rentalDurationMillis = returnDate.getTime() - rentalDate.getTime();
            int rentalDurationDays = (int) (rentalDurationMillis / (1000 * 60 * 60 * 24));
            double dailyRate = car.getCategory().getDailyRate();
            double basePrice = dailyRate * rentalDurationDays;

            if (rentalDurationDays > 30) {
                throw new IllegalArgumentException("Maximum rental duration is 30 days.");
            }

            return basePrice;
        }
    }

    public class CarCategoryDAO {

            private final SessionFactory sessionFactory;

            public CarCategoryDAO(SessionFactory sessionFactory) {
                this.sessionFactory = sessionFactory;
            }

            public void createCarCategory(CarCategory carCategory) {
                try (Session session = sessionFactory.openSession()) {
                    Transaction transaction = session.beginTransaction();
                    session.persist(carCategory);
                    transaction.commit();
                }
            }

            public CarCategory getCarCategoryById(Long categoryId) {
                try (Session session = sessionFactory.openSession()) {
                    return session.get(CarCategory.class, categoryId);
                }
            }

            public void updateCarCategory(CarCategory carCategory) {
                try (Session session = sessionFactory.openSession()) {
                    Transaction transaction = session.beginTransaction();
                    session.merge(carCategory);
                    transaction.commit();
                }
            }

            public void deleteCarCategory(Long categoryId) {
                try (Session session = sessionFactory.openSession()) {
                    Transaction transaction = session.beginTransaction();
                    CarCategory carCategory = session.get(CarCategory.class, categoryId);
                    if (carCategory != null) {
                        session.delete(carCategory);
                    }
                    transaction.commit();
                }
            }
        }


    }



    public class CarHireUI extends Application {


        public void start(Stage primaryStage) {
            primaryStage.setTitle("CarHire Application");

            Label label = new Label("Welcome to CarHire!");
            Scene scene = new Scene(label, 400, 300);

            primaryStage.setScene(scene);
            primaryStage.show();
        }

        public static void main(String[] args) {
            launch(args);
        }
    }



    public class Rental {



    public double calculateRentalPrice() {
        // Calculate the number of days between rentalDate and returnDate
        long rentalDurationMillis = returnDate.getTime() - rentalDate.getTime();
        int rentalDurationDays = (int) (rentalDurationMillis / (1000 * 60 * 60 * 24));

        // Define pricing based on car type (you can adjust these values)
        double dailyRate = car.getCategory().getDailyRate();
        double basePrice = dailyRate * rentalDurationDays;

        // Ensure the rental duration does not exceed 30 days
        if (rentalDurationDays > 30) {
            throw new IllegalArgumentException("Maximum rental duration is 30 days.");
        }

        return basePrice;
    }
}

public class RentalUI {

    public boolean validateRentalDuration(int rentalDuration) {
        if (rentalDuration <= 0) {
            displayError("Rental duration must be greater than 0.");
            return false;
        } else if (rentalDuration > 30) {
            displayError("Maximum rental duration is 30 days.");
            return false;
        }
        return true;
    }

    private void displayError(String errorMessage) {
        // Implement code to display the error message to the user (e.g., show a dialog or update the UI)
        System.out.println("Error: " + errorMessage);
    }

    // Other UI-related methods
}







public class RentalDAO {
        public List<Rental> getOverdueRentals() {
                try (Session session = sessionFactory.openSession()) {
                    Date currentDate = new Date();
                    String hql = "FROM Rental r WHERE r.returnDate < :currentDate";
                    Query<Rental> query = session.createQuery(hql, Rental.class);
                    query.setParameter("currentDate", currentDate);
                    return query.list();
                }
            }



        private final SessionFactory sessionFactory;

            public RentalDAO(SessionFactory sessionFactory) {
                this.sessionFactory = sessionFactory;
            }

            public void createRental(Rental rental) {
                try (Session session = sessionFactory.openSession()) {
                    Transaction transaction = session.beginTransaction();
                    session.persist(rental);
                    transaction.commit();
                }
            }

            public Rental getRentalById(Long rentalId) {
                try (Session session = sessionFactory.openSession()) {
                    return session.get(Rental.class, rentalId);
                }
            }

            public void updateRental(Rental rental) {
                try (Session session = sessionFactory.openSession()) {
                    Transaction transaction = session.beginTransaction();
                    session.merge(rental);
                    transaction.commit();
                }
            }

            public void deleteRental(Long rentalId) {
                try (Session session = sessionFactory.openSession()) {
                    Transaction transaction = session.beginTransaction();
                    Rental rental = session.get(Rental.class, rentalId);
                    if (rental != null) {
                        session.delete(rental);
                    }
                    transaction.commit();
                }
            }
        }




    public class RentalUI {


        public boolean validateRentalDuration(int rentalDuration) {
            if (rentalDuration <= 0) {
                displayError("Rental duration must be greater than 0.");
                return false;
            } else if (rentalDuration > 30) {
                displayError("Maximum rental duration is 30 days.");
                return false;
            }
            return true;
        }

        private void displayError(String errorMessage) {
            // Implement code to display the error message to the user
            System.err.println("Error: " + errorMessage);
        }
    }




    public class UserAuthentication {

        private Map<String, String> users;

        public UserAuthentication() {
            users = new HashMap<>();
            // Add user credentials (username, password) to the map
            users.put("john", "password123");
            users.put("alice", "secret456");
        }

        public boolean authenticate(String username, String password) {
            String storedPassword = users.get(username);
            return storedPassword != null && storedPassword.equals(password);
        }

        public void main(String[] args) {
            UserAuthentication auth = new UserAuthentication();
            String username = "john";
            String password = "password123";

            if (auth.authenticate(username, password)) {
                System.out.println("Authentication successful for user: " + username);
            } else {
                System.out.println("Authentication failed for user: " + username);
            }
        }
    }


    public class HibernateUtil {

        private static final SessionFactory sessionFactory;

        static {
            try {

                Configuration configuration = new Configuration().configure("hibernate.cfg.xml");


                sessionFactory = configuration.buildSessionFactory();
            } catch (Throwable ex) {
                throw new ExceptionInInitializerError(ex);
            }
        }

        public static Session getSession() {
            return sessionFactory.openSession();
        }
    }



    public class DatabaseSetup {

        private static final String JDBC_URL = "jdbc:mysql://localhost:3306";
        private static final String DB_USER = "your_db_username";
        private static final String DB_PASSWORD = "your_db_password";
        private static final String DB_NAME = "carhire";

        public static void main(String[] args) {
            try {
                Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD);
                Statement statement = connection.createStatement();

                // Create the 'carhire' database if it doesn't exist
                String createDatabaseSQL = "CREATE DATABASE IF NOT EXISTS " + DB_NAME;
                statement.executeUpdate(createDatabaseSQL);

                // Switch to the 'carhire' database
                String useDatabaseSQL = "USE " + DB_NAME;
                statement.executeUpdate(useDatabaseSQL);

                // Create tables and define the database schema here
                String createCarsTableSQL = "CREATE TABLE IF NOT EXISTS cars ("
                        + "id INT AUTO_INCREMENT PRIMARY KEY,"
                        + "brand VARCHAR(255) NOT NULL,"
                        + "model VARCHAR(255) NOT NULL,"
                        + "year INT NOT NULL)";

                String createCustomersTableSQL = "CREATE TABLE IF NOT EXISTS customers ("
                        + "id INT AUTO_INCREMENT PRIMARY KEY,"
                        + "name VARCHAR(255) NOT NULL,"
                        + "email VARCHAR(255) NOT NULL)";

                // Execute SQL statements to create tables
                statement.executeUpdate(createCarsTableSQL);
                statement.executeUpdate(createCustomersTableSQL);

                // Close the connection
                statement.close();
                connection.close();

                System.out.println("Database setup completed.");
            } catch (SQLException e) {
                e.printStackTrace();
                System.err.println("Error setting up the database.");
            }
        }
    }

    public class CarHireApp {
        public static void main(String[] args) {

                    // Create a new JFrame window
                    JFrame frame = new JFrame("CarHire Application");
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setSize(400, 300);

                    // Create a label and add it to the frame
                    JLabel label = new JLabel("Welcome to CarHire!");
                    frame.add(label);

                    // Display the frame
                    frame.setVisible(true);
                }
            }





