package banking;

import java.sql.SQLException;
import java.util.Scanner;

public class BankingSystemInterface {
    private final Scanner scanner;
    private final BankingSystem banking;

    public BankingSystemInterface() {
        scanner = new Scanner(System.in);
        banking = new BankingSystem();
    }

    public void start() {
        mainLoop();
    }

    private void mainLoop() {
        while (true) {
            System.out.println(
                    "1. Create an account\n" +
                            "2. Log into account\n" +
                            "0. Exit"
            );
            switch (scanner.nextInt()) {
                case 1:
                    banking.createAccount();
                    break;

                case 2:
                    BankAccount result = banking.logIn();
                    if (result != null) {
                        System.out.println ("\nYou have successfully logged in!\n");
                        manageAccount(result);
                    } else {
                        System.out.println("\nWrong card number or PIN!\n");
                    }
                    break;

                case 0:
                    System.out.println("\nBye!");
                    banking.closeDBConnection();
                    return;

                default:
                    System.out.println("\nUNKNOWN OPERATION\n");
                    break;
            }
            try {
                if (banking.getDBConnection().isClosed()) {
                    return;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    private void manageAccount(BankAccount account) {
        while (true) {
            System.out.println(
                    "1. Balance\n" +
                            "2. Add income\n" +
                            "3. Do transfer\n" +
                            "4. Close account\n" +
                            "5. Log out \n" +
                            "0. Exit"
            );

            switch (scanner.nextInt()) {
                case 1:
                    System.out.println("\nBalance: " + account.getBalance() + "\n");
                    break;
                case 2:
                    System.out.println("\n Enter income:");
                    int income = scanner.nextInt();
                    account.addIncome(income);
                    banking.addIncome(account,income);
                    System.out.println("\nIncome was added!");
                    break;
                case 3:
                    System.out.println("Transfer");
                    banking.doTransfer(account);
                    account.decTransfer(banking.getTransfer());
                    break;
                case 4:
                    try {
                        banking.closeAccount(account);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    return;
                case 5:
                    System.out.println("\nYou have successfully logged out!\n");
                    return;
                case 0:
                    System.out.println("\nBye!");
                    banking.closeDBConnection();
                    return;
                default:
                    System.out.println("\nUNKNOWN OPERATION\n");
                    break;
            }
        }
    }
}