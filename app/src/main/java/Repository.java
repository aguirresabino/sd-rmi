import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Repository {

    private Connection con;
    public Repository() {
        try {
            this.con = ConnectionFactory.getConnectionPostgres();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void inserir(Entidade entidade) {
        try {
            String sql = "INSERT INTO entidade (id, editado, excluido) VALUES (?,?,?)";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, entidade.getId());
            statement.setBoolean(2, false);
            statement.setBoolean(3, false);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizar(int id) {
        try {
            String sql = "UPDATE entidade SET editado = ? WHERE id = ?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setBoolean(1, true);
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluir(int id) {
        try {
            String sql = "UPDATE entidade SET excluido = ? WHERE id = ?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setBoolean(1, true);
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
