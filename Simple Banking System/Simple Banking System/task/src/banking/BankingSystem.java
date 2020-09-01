package banking;

import java.sql.*;
import java.util.Random;
import java.util.Scanner;

public class BankingSystem {
    private static final Random rnd = new Random();

    private static final String IIN = "400000";
    private static final int CUSTOMER_ACCOUNT_NUMBER_LENGTH = 9;
    private static final int PIN_CODE_LENGTH = 4;
    private int transfer;
    private final Scanner scanner;
    private Connection dbConnection;

    public BankingSystem() {
        String url = "jdbc:sqlite:" + Main.dbName;
        scanner = new Scanner(System.in);

        try {
            dbConnection = DriverManager.getConnection(url);

            try (Statement statement = dbConnection.createStatement()) {
                statement.executeUpdate("CREATE TABLE IF NOT EXISTS card (" +
                        "\"id\" INTEGER PRIMARY KEY, " +
                        "\"number\" TEXT UNIQUE, " +
                        "\"pin\" TEXT, " +
                        "\"balance\" INTEGER DEFAULT 0);"
                );
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createAccount() {
        String cardNumber = generateCardNumber();
        String pinCode = generatePinCode();

        if (dbConnection != null) {
            String query = "INSERT INTO card (number, pin) VALUES (?, ?);";

            try (PreparedStatement statement = dbConnection.prepareStatement(query)) {
                statement.setString(1, cardNumber);
                statement.setString(2, pinCode);
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        System.out.println("\n" +
                "Your card has been created\n" +
                "Your card number:\n" +
                cardNumber + "\n" +
                "Your card PIN:\n" +
                pinCode + "\n"
        );
    }
    public BankAccount logIn() {
        System.out.println("\nEnter your card number:");
        String cardNumber = scanner.nextLine();

        System.out.println("Enter your PIN:");
        String pinCode = scanner.nextLine();

        String query = "SELECT number, pin, balance FROM card WHERE number = ? AND pin = ?;";

        try (PreparedStatement statement = dbConnection.prepareStatement(query)) {
            statement.setString(1, cardNumber);
            statement.setString(2, pinCode);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new BankAccount(
                            resultSet.getString("number"),
                            resultSet.getString("pin"),
                            resultSet.getInt("balance")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
    public void closeAccount(BankAccount account) throws SQLException {
        String query = "DELETE FROM card WHERE number = \""+account.getCardNumber()+"\";";
        Statement statement = dbConnection.createStatement();
        statement.executeUpdate(query);
        System.out.println("The account has been closed!");
        return;
    }
    public void addIncome(BankAccount account,int income){
        String query = "UPDATE card SET balance = balance + "+income+" WHERE number = \""+account.getCardNumber()+"\";";
        try {
            Statement statement = dbConnection.createStatement();
            statement.executeUpdate(query);
            System.out.println("Success!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void doTransfer(BankAccount account){
        System.out.println("Enter card number:");
        String cardNumber=scanner.nextLine();
        String query = "SELECT number, pin, balance FROM card WHERE number = ? ;";
        try (PreparedStatement statement = dbConnection.prepareStatement(query)) {
            statement.setString(1, cardNumber);
            } catch (SQLException throwables) {
            System.out.println("Probably you made mistake in the card number. Please try again!");
            return;
        }
        if (account.getCardNumber().equals(cardNumber)){
            System.out.println("You can't transfer money to the same account!");
            return;
        }

        System.out.println("Enter how much money you want to transfer:");
        transfer = scanner.nextInt();
        if (transfer>account.getBalance()){
            System.out.println("Not enough money!");
            return;
        }
        String queryTransferOnCard = "UPDATE card SET balance=balance+"+transfer+" WHERE number = \""+cardNumber+"\";";
        String queryTransferFromCard = "UPDATE card SET balance=balance-"+transfer+" WHERE number = \""+account.getCardNumber()+"\";";
        try {
            Statement statement = dbConnection.createStatement();
            statement.executeUpdate(queryTransferFromCard);
            statement.executeUpdate(queryTransferOnCard);
            System.out.println("Success!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public int getTransfer(){
        return this.transfer;
    }
    public Connection getDBConnection() {
        return dbConnection;
    }
    public void closeDBConnection() {
        try {
            if (dbConnection != null && !dbConnection.isClosed()) {
                dbConnection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private String generateCardNumber() {
        StringBuilder cardNumber = new StringBuilder();
        cardNumber.append(IIN);

        for (int i = 0; i < CUSTOMER_ACCOUNT_NUMBER_LENGTH; i++) {
            cardNumber.append(rnd.nextInt(10));
        }

        cardNumber.append(calculateChecksum(cardNumber.toString()));

        return cardNumber.toString();
    }
    private String generatePinCode() {
        StringBuilder pinCode = new StringBuilder();

        for (int i = 0; i < PIN_CODE_LENGTH; i++) {
            pinCode.append(rnd.nextInt(10));
        }

        return pinCode.toString();
    }
    private int calculateChecksum(String cardNumber) {
        int sum = 0;

        for (int i = 0; i < cardNumber.length(); i++) {
            int digit = Integer.parseInt(String.valueOf(cardNumber.charAt(i)));
            if (i % 2 == 0) {
                digit *= 2;
            }
            if (digit > 9) {
                digit -= 9;
            }

            sum += digit;
        }

        for (int i = 0; ; i++) {
            sum += i;
            if (sum % 10 == 0) {
                return i;
            }
            sum -= i;
        }
    }
}