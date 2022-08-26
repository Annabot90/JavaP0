import java.util.Scanner;

public class CheckAccMenu {
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
                System.out.println("enter amount to deposit : ");
                double deposit = Double.parseDouble(sc.nextLine());
                if (deposit < 0) {
                    System.out.println("Whoops!! You Entered a negative amount. Please try again.");
                    break;
                }
                System.out.println(" which account would you like to deposit to?\n1. Checking\n2. Savings");
                int accChoice = Integer.parseInt(sc.nextLine());
                if (accChoice == 1) {
                    dao.deposit_checking_acc(deposit, email1);
                } else if (accChoice == 2) {
                    dao.deposit_savings_acc(deposit, email1);
                } else {
                    System.out.println("Invalid choice");
                }
                break;
            }
            case 2 -> {
                System.out.println("enter amount to withdraw : ");
                double withdraw = Double.parseDouble(sc.nextLine());
                if (withdraw < 0) {
                    System.out.println("Whoops!! You Entered a negative amount. Please try again.");
                    break;
                }
                System.out.println(" which account would you like to withdraw from?\n1. Checking\n2. Savings");
                int accChoice = Integer.parseInt(sc.nextLine());
                if (accChoice == 1) {
                    dao.withdraw_checking_acc(withdraw, email1);
                } else if (accChoice == 2) {
                    dao.withdraw_savings_acc(withdraw, email1);
                } else {
                    System.out.println("Invalid choice");
                }
                break;
            }
            case 3 -> {
                System.out.println(" type of account to transfer to?\n1. Checking\n2. Savings");
                int accChoice = Integer.parseInt(sc.nextLine());
                if (accChoice == 1) {
                    System.out.println("Enter amount to transfer : ");
                    double transfer = Double.parseDouble(sc.nextLine());
                    if (transfer < 0) {
                        System.out.println("Whoops!! You Entered a negative amount. Please try again.");
                        break;
                    }
                    dao.transfer_to_check_acc(transfer, email1);
                } else if (accChoice == 2) {
                    System.out.println("Enter amount to transfer : ");
                    double transfer = Double.parseDouble(sc.nextLine());
                    if (transfer < 0) {
                        System.out.println("Whoops!! You Entered a negative amount. Please try again.");
                        break;
                    }
                    dao.transfer_to_sav_acc(transfer, email1);
                } else {
                    System.out.println("Invalid choice");
                }
            }
            case 4 -> {
                System.out.println(" which account would you like to view?\n1. Checking\n2. Savings");
                int accChoice = Integer.parseInt(sc.nextLine());
                if (accChoice == 1) {
                    dao.get_checking_balance(email1);
                } else if (accChoice == 2) {
                    dao.get_savings_balance(email1);
                } else {
                    System.out.println("Invalid choice");
                }
            }
            case 6 -> {
                System.out.println("Thank you for using our services. Have a nice day!");
                System.exit(0);
            }
        }
    }
}
