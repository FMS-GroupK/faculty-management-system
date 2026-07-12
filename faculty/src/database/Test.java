package database;

public class Test {

    public static void main(String[] args) {

        if (DBconnection.getConnection() != null) {
            System.out.println("Database Connected");
        } else {
            System.out.println("Connection Failed");
        }

    }
}