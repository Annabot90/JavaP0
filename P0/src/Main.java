import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Dao dao = DaoFactory.getAccounts();
        Scanner sc = new Scanner(System.in);

        boolean flag = true;
        while(flag){
            System.out.println("1. Create Account");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.println("Please enter your choice : ");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice){
                case 1 -> {
                    System.out.println("Enter first name : "); String fname = sc.nextLine();
                    System.out.println("Enter last name : ");  String lname = sc.nextLine();
                    System.out.println("Enter email : ");      String email = sc.nextLine();
                    System.out.println("Enter password : ");   String password = sc.nextLine();
                    System.out.println("Enter Deposit Amount : "); double deposit = Double.parseDouble(sc.nextLine());
                    if (deposit < 0) {
                        System.out.println("Whoops!! You Entered a negative amount. Please try again.");
                        break;
                    }
                    System.out.println("Checking or Savings Account?\n1. Checking\n2. Savings");
                    int accountType = Integer.parseInt(sc.nextLine());
                    if (accountType == 1) {
                        dao.add_new_checking_acc(fname, lname, email, password, deposit, 0.0);
                    } else if (accountType == 2) {
                        dao.add_new_savings_acc(fname, lname, email, password, deposit, 0.0);
                    } else {
                        System.out.println("Invalid choice");
                    }
                }
                case 2 -> {
                    System.out.println("Enter email : ");      String email = sc.nextLine();
                    System.out.println("Enter password : ");   String password = sc.nextLine();
                    if (Objects.equals(email, "") || Objects.equals(password, "")) {
                        System.out.println("Invalid email or password");
                        break;
                    }
                    System.out.println("Checking or Savings Account?\n1. Checking\n2. Savings");
                    int accountType = Integer.parseInt(sc.nextLine());
                    if (accountType == 1) {
                        if(dao.login_check_acc(email, password)){CheckAccMenu.menu(email);}
                        else{System.out.println("Invalid email or password");}
                    } else if (accountType == 2) {
                        if(dao.login_sav_acc(email, password)){SavingsMenu.menu(email);}
                        else{System.out.println("Invalid email or password");}
                    } else {
                        System.out.println("Invalid choice");
                    }

                }
                case 3 -> {
                    System.out.println("Thank you for using our services");
                    flag = false;
                }
            }
        }
    }
}