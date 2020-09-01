package banking;

public class BankAccount {
    private final String cardNumber;
    private final String PIN;
    private int balance;


    BankAccount(String cardNumber, String PIN, int balance) {
        this.cardNumber = cardNumber;
        this.PIN = PIN;
        this.balance = balance;
    }

    public String getCardNumber() {
        return cardNumber;
    }
    public String getPIN() {
        return PIN;
    }
    public int getBalance() {
        return balance;
    }
    public void addIncome(int income){
        this.balance = this.balance+income;
    }
   public void decTransfer(int dec){
        this.balance = this.balance - dec;
   }
}
