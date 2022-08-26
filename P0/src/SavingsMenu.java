import java.util.Scanner;

public class SavingsMenu {
    static Dao dao = DaoFactory.getAccounts();
    static Scanner sc = new Scanner(System.in);
    public static void menu(String email1) {
        System.out.println("1. Deposit");
        System.out.println("2. Withdraw");
        System.out.println("3. Transfer");
        System.out.println("4. View Balance");
        System.out.println("6. Exit");
        System.out.println("Please enter your choice : ");
        int choice = Integer.parseInt(sc.nextLine());
        switch (choice) {
            case 1 -> {
                System.out.println("Enter amount to deposit : ");
                double deposit = Double.parseDouble(sc.nextLine());
                if (deposit < 0) {
                    System.out.println("Whoops!! You Entered a negative amount. Please try again.");
                    break;
                }
                dao.deposit_savings_acc(deposit, email1);
            }
            case 2 -> {
                System.out.println("Enter amount to withdraw : ");
                double withdraw = Double.parseDouble(sc.nextLine());
                if (withdraw < 0) {
                    System.out.println("Whoops!! You Entered a negative amount. Please try again.");
                    break;
                }
                dao.withdraw_savings_acc(withdraw, email1);
            }
            case 3 -> {
                System.out.println("Enter email to transfer to: ");
                String email2 = sc.nextLine();
                System.out.println("Enter amount to transfer : ");
                double transfer = Double.parseDouble(sc.nextLine());
                if (transfer < 0) {
                    System.out.println("Whoops!! You Entered a negative amount. Please try again.");
                    break;
                }
                dao.transfer(email2, transfer);
            }
            case 4 -> {
                System.out.println("which account would you like to view?\n1. Checking\n2. Savings");
                int accChoice = Integer.parseInt(sc.nextLine());
                if (accChoice == 1) {
                    dao.get_checking_balance(email1);
                } else if (accChoice == 2) {
                    dao.get_savings_balance(email1);
                } else {
                    System.out.println("Invalid choice");
                }
            }
            case 6 -> System.exit(0);
            default -> System.out.println("Invalid choice");
        }
    }
}
