public interface Dao {
        //As a user, I can login.
        boolean login_check_acc(String email, String password);
        boolean login_sav_acc(String email, String password);

        //As a customer, I can apply for a new bank account with a starting balance.
        void add_new_checking_acc(String fname, String lname, String email, String password, Double deposit, Double balance);
        void add_new_savings_acc(String fname, String lname, String email, String password, Double deposit, Double balance);
        //As a customer, I can view the balance of a specific account.
        double get_checking_balance(String email);
        double get_savings_balance(String email);
        //As a customer, I can make a withdrawal or deposit to a specific account.
        void deposit_checking_acc(double amount, String email);
        void deposit_savings_acc(double amount, String email);
        void withdraw_checking_acc(double amount, String email);
        void withdraw_savings_acc(double amount, String email);
        //As the system, I reject invalid transactions.

        //Ex: * A withdrawal that would result in a negative balance.
        //A deposit or withdrawal of negative money.
        //As an employee, I can approve or reject an account.
        void approve_checking_acc(String email);
        void approve_savings_acc(String email);
        void reject_checking_acc(String email);
        void reject_savings_acc(String email);
        //As an employee, I can view a customer's bank accounts.

        //As a user, I can register for a customer account.

        //As a customer, I can post a money transfer to another account.
        void transfer_checking_to_outside_checking(double amount, String recipientEmail, String yourEmail);
        void transfer_checking_to_outside_savings(double amount, String recipientEmail, String yourEmail);
        void transfer_savings_to_outside_checking(double amount, String senders_email, String receivers_email);
        void transfer_savings_to_outside_savings(double amount, String senders_email, String receivers_email);

        void transfer_to_check_acc(double transfer, String email1);

        void transfer_to_sav_acc(double transfer, String email1);

        void transfer(String email2, double transfer);

        //As a customer, I can accept a money transfer from another account.
        //A an employee, I can view a log of all transactions.
    }


