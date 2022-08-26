public class DaoFactory {
    private static Dao dao;
    private DaoFactory() {
    }

    public static Dao getAccounts() {
        if (dao == null) {
            dao = new DaoImpl();
        }
        return dao;
    }
}
