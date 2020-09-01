package banking;
public class Main {
    public static String dbName;
    public static void main(String[] args) {
//           if (args.length == 2 && args[0].equals("-fileName") && (args[1].endsWith(".db") || args[1].endsWith(".s3db"))) {
//            dbName = args[1];
//            new BankingSystemInterface().start();
//        } else {
//            System.out.println("Please specify database file with -fileName parameter.");

         dbName = "/home/vadim/MyGame/dataBase.s3db";
         new BankingSystemInterface().start();

}

}
