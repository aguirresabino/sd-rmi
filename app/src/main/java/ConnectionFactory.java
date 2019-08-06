import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    public static Connection getConnectionPostgres() throws SQLException {
        return DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/sdquestao1",
                "postgres","secret");
    }
}
