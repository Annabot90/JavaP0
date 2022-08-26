public class SavingsAcc {
    private int accNumber;
    private String fname;
    private String lname;
    private String email;
    private double balance;

    public SavingsAcc(int accNumber, String fname, String lname, String email, double balance) {
        this.accNumber = accNumber;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.balance = balance;
    }

    public int getAccNumber() {
        return accNumber;
    }

    public void setAccNumber(int accNumber) {
        this.accNumber = accNumber;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "SavingsAcc{" +
                "accNumber=" + accNumber +
                ", fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", email='" + email + '\'' +
                ", balance=" + balance +
                '}';
    }
}
