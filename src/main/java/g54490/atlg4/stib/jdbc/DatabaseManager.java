package g54490.atlg4.stib.jdbc;

import g54490.atlg4.stib.config.ConfigManager;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author 54490@etu.he2b.be
 */
public class DatabaseManager {
    
    private Connection connection;

    /**
     * private manufacturer by default.
     */
    private DatabaseManager() {
    }

    public Connection getConnection() throws IOException {
        ConfigManager.getInstance().load();
        String DBurl = "jdbc:sqlite:" + ConfigManager.getInstance().getProperties("db.url");
        if (connection == null ) {
            try {
                connection = DriverManager.getConnection(DBurl);
            } catch (SQLException ex) {
                System.out.println("erruer DB "+ex.getMessage());
            }
        }
        return connection;
    }

    void startTransaction() throws IOException {
        try {
            getConnection().setAutoCommit(false);
        } catch (SQLException ex) {
            System.out.println("erruer DB "+ex.getMessage());
        }
    }

    void startTransaction(int isolationLevel) throws IOException{
        try {
            getConnection().setAutoCommit(false);

            int isol = 0;
            switch (isolationLevel) {
                case 0:
                    isol = java.sql.Connection.TRANSACTION_READ_UNCOMMITTED;
                    break;
                case 1:
                    isol = java.sql.Connection.TRANSACTION_READ_COMMITTED;
                    break;
                case 2:
                    isol = java.sql.Connection.TRANSACTION_REPEATABLE_READ;
                    break;
                case 3:
                    isol = java.sql.Connection.TRANSACTION_SERIALIZABLE;
                    break;
                default:
                    throw new IOException("Degré d'isolation inexistant!");
            }
            getConnection().setTransactionIsolation(isol);
        } catch (SQLException ex) {
            throw new IOException("Impossible de démarrer une transaction: " + ex.getMessage());
        }
    }

    void validateTransaction() throws IOException{
        try {
            getConnection().commit();
            getConnection().setAutoCommit(true);
        } catch (SQLException ex) {
            throw new IOException("Impossible de valider la transaction: " + ex.getMessage());
        }
    }

    void cancelTransaction() throws IOException{
        try {
            getConnection().rollback();
            getConnection().setAutoCommit(true);
        } catch (SQLException ex) {
            throw new IOException("Impossible d'annuler la transaction: " + ex.getMessage());
        }
    }

    public static DatabaseManager getInstance() {
        return DBManagerHolder.INSTANCE;
    }

    private static class DBManagerHolder {

        private static final DatabaseManager INSTANCE = new DatabaseManager();
    }
}
