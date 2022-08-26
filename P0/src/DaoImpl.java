import java.sql.Connection;
import java.sql.PreparedStatement;

    public class DaoImpl implements Dao{
        static Connection conn;
        public DaoImpl() {
            conn = ConnectFactory.getConnection();
        }
        @Override
        public boolean login_check_acc(String email, String password) {
            String sql = "SELECT * FROM checkacc WHERE email = ? AND password = ?";

            try{
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, email);
                ps.setString(2, password);
                ps.executeQuery();
                if (ps.getResultSet().next()) {
                    return true;

                }
            } catch (Exception e) {

            }
            return false;
        }


        @Override
        public boolean login_sav_acc(String email, String password) {
            String sql = "SELECT * FROM savingsacc WHERE email = ? AND password = ?";

            try{
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, email);
                ps.setString(2, password);
                ps.executeQuery();
                if (ps.getResultSet().next()) {
                    return true;

                }
            } catch (Exception e) {

            }
            return false;
        }

        @Override
        public void add_new_checking_acc(String fname, String lname, String email, String password, Double deposit, Double balance) {
            String sql = "INSERT INTO checkacc (fname, lname, email, password, deposit, balance) VALUES (?,?,?,?,?,?)";
            String sql1 = "UPDATE checkacc SET balance = (checkacc.deposit + checkacc.balance) WHERE email = ?";
            String sql2 = "UPDATE checkacc SET deposit = 0 WHERE email = ?";

            try{
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setString(1, fname);
                    ps.setString(2, lname);
                    ps.setString(3, email);
                    ps.setString(4, password);
                    ps.setDouble(5, deposit);
                    ps.setDouble(6, balance);
                    ps.executeUpdate();
                }
                PreparedStatement ps1 = conn.prepareStatement(sql1);
                ps1.setString(1, email);
                ps1.executeUpdate();
                PreparedStatement ps2 = conn.prepareStatement(sql2);
                ps2.setString(1, email);
                ps2.executeUpdate();
            } catch (Exception e) {
                throw new RuntimeException(e);


            }
            System.out.println("Account pending approval");

        }

        @Override
        public void add_new_savings_acc(String fname, String lname, String email, String password, Double deposit, Double balance) {
            String sql = "INSERT INTO savingsacc (fname, lname, email, password, deposit, balance) VALUES (?,?,?,?,?,?)";
            String sql1 = "UPDATE savingsacc SET balance = (savingsacc.deposit + savingsacc.balance) WHERE email = ?";
            String sql2 = "UPDATE savingsacc SET deposit = 0 WHERE email = ?";

            try {
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, fname);
                ps.setString(2, lname);
                ps.setString(3, email);
                ps.setString(4, password);
                ps.setDouble(5, deposit);
                ps.setDouble(6, balance);
                ps.executeUpdate();
                PreparedStatement ps1 = conn.prepareStatement(sql1);
                ps1.setString(1, email);
                ps1.executeUpdate();
                PreparedStatement ps2 = conn.prepareStatement(sql2);
                ps2.setString(1, email);
                ps2.executeUpdate();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        @Override
        public double get_checking_balance(String email) {
            String sql = "SELECT balance FROM checkacc WHERE email = ?";double balance = 0;
            try{
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, email);
                ps.executeQuery();
                if (ps.getResultSet().next()) {
                    balance = ps.getResultSet().getDouble("balance");
                    System.out.println("Your balance is: " + balance);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            return balance;
        }


        @Override
        public double get_savings_balance(String email) {
            String sql = "SELECT balance FROM savingsacc WHERE email = ?";
            double balance = 0;
            try{
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, email);
                ps.executeQuery();
                if (ps.getResultSet().next()) {
                    balance = ps.getResultSet().getDouble("balance");
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            return balance;
        }

        @Override
        public void deposit_checking_acc(double amount, String email) {
            String sql = "UPDATE checkacc SET deposit = ? WHERE email = ?";
            String sql1 = "UPDATE checkacc SET balance = (checkacc.deposit + checkacc.balance) WHERE email = ?";
            String sql2 = "UPDATE checkacc SET deposit = 0 WHERE email = ?";
            try{
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setDouble(1, amount);
                ps.setString(2, email);
                ps.executeUpdate();
                PreparedStatement ps1 = conn.prepareStatement(sql1);
                ps1.setString(1, email);
                ps1.executeUpdate();
                PreparedStatement ps2 = conn.prepareStatement(sql2);
                ps2.setString(1, email);
                ps2.executeUpdate();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }


        }

        @Override
        public void deposit_savings_acc(double amount, String email) {
            String sql = "UPDATE savingsacc SET deposit = ? WHERE email = ?";
            String sql1 = "UPDATE savingsacc SET balance = (savingsacc.deposit + savingsacc.balance) WHERE email = ?";
            String sql2 = "UPDATE savingsacc SET deposit = 0 WHERE email = ?";
            try{
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setDouble(1, amount);
                ps.setString(2, email);
                ps.executeUpdate();
                PreparedStatement ps1 = conn.prepareStatement(sql1);
                ps1.setString(1, email);
                ps1.executeUpdate();
                PreparedStatement ps2 = conn.prepareStatement(sql2);
                ps2.setString(1, email);
                ps2.executeUpdate();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }

        @Override
        public void withdraw_checking_acc(double amount, String email) {
            String sql = "UPDATE checkacc SET balance = (checkacc.balance - ?) WHERE email = ?";
            try{
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setDouble(1, amount);
                ps.setString(2, email);
                ps.executeUpdate();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }


        }

        @Override
        public void withdraw_savings_acc(double amount, String email) {
            String sql = "UPDATE savingsacc SET balance = (savingsacc.balance - ?) WHERE email = ?";
            try{
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setDouble(1, amount);
                ps.setString(2, email);
                ps.executeUpdate();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }


        }

        @Override
        public void approve_checking_acc(String email) {
            String sql = "UPDATE checkacc SET approved = 1 WHERE email = ?";
            try{
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, email);
                ps.executeUpdate();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }

        @Override
        public void approve_savings_acc(String email) {
            String sql = "UPDATE savingsacc SET approved = 1 WHERE email = ?";
            try{
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, email);
                ps.executeUpdate();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }


        }

        @Override
        public void reject_checking_acc(String email) {
            String sql = "DELETE FROM checkacc WHERE email = ?";
            try{
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, email);
                ps.executeUpdate();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }


        }

        @Override
        public void reject_savings_acc(String email) {
            String sql = "DELETE FROM savingsacc WHERE email = ?";
            try{
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, email);
                ps.executeUpdate();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }

        @Override
        public void transfer_checking_to_outside_checking(double amount, String recipientEmail, String yourEmail) {
            String sql = "UPDATE checkacc SET balance = (checkacc.balance - ?) WHERE email = ?";
            String sql1 = "UPDATE checkacc SET balance = (checkacc.balance + ?) WHERE email = ?";
            try{
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setDouble(1, amount);
                ps.setString(2, yourEmail);
                ps.executeUpdate();
                PreparedStatement ps1 = conn.prepareStatement(sql1);
                ps1.setDouble(1, amount);
                ps1.setString(2, recipientEmail);
                ps1.executeUpdate();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }

        @Override
        public void transfer_checking_to_outside_savings(double amount, String recipientEmail, String yourEmail) {
            String sql = "UPDATE checkacc SET balance = (checkacc.balance - ?) WHERE email = ?";
            String sql1 = "UPDATE savingsacc SET balance = (savingsacc.balance + ?) WHERE email = ?";
            try{
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setDouble(1, amount);
                ps.setString(2, yourEmail);
                ps.executeUpdate();
                PreparedStatement ps1 = conn.prepareStatement(sql1);
                ps1.setDouble(1, amount);
                ps1.setString(2, recipientEmail);
                ps1.executeUpdate();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }


        }

        @Override
        public void transfer_savings_to_outside_checking(double amount, String senders_email, String receivers_email) {
            String sql = "UPDATE savingsacc SET balance = (savingsacc.balance - ?) WHERE email = ?";
            String sql1 = "UPDATE checkacc SET balance = (checkacc.balance + ?) WHERE email = ?";
            try{
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setDouble(1, amount);
                ps.setString(2, senders_email);
                ps.executeUpdate();
                PreparedStatement ps1 = conn.prepareStatement(sql1);
                ps1.setDouble(1, amount);
                ps1.setString(2, receivers_email);
                ps1.executeUpdate();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }


        }

        @Override
        public void transfer_savings_to_outside_savings(double amount, String senders_email, String receivers_email) {
            String sql = "UPDATE savingsacc SET balance = (savingsacc.balance - ?) WHERE email = ?";
            String sql1 = "UPDATE savingsacc SET balance = (savingsacc.balance + ?) WHERE email = ?";
            try{
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setDouble(1, amount);
                ps.setString(2, senders_email);
                ps.executeUpdate();
                PreparedStatement ps1 = conn.prepareStatement(sql1);
                ps1.setDouble(1, amount);
                ps1.setString(2, receivers_email);
                ps1.executeUpdate();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }

        @Override
        public void transfer_to_check_acc(double transfer, String email1) {
            String sql = "UPDATE checkacc SET balance = (checkacc.balance + ?) WHERE email = ?";
            try{
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setDouble(1, transfer);
                ps.setString(2, email1);
                ps.executeUpdate();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }


        }

        @Override
        public void transfer_to_sav_acc(double transfer, String email1) {
            String sql = "UPDATE savingsacc SET balance = (savingsacc.balance + ?) WHERE email = ?";
            try{
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setDouble(1, transfer);
                ps.setString(2, email1);
                ps.executeUpdate();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }


        }

        @Override
        public void transfer(String email2, double transfer) {
            String sql = "UPDATE checkacc SET balance = (checkacc.balance - ?) WHERE email = ?";
            try{
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setDouble(1, transfer);
                ps.setString(2, email2);
                ps.executeUpdate();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }


        }
    }
