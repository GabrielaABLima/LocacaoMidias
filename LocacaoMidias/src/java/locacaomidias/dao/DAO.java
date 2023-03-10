package locacaomidias.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import locacaomidias.jdbc.ConnectionFactory;




public abstract class DAO<Tipo> implements AutoCloseable {

    private Connection conexao;

    public DAO() throws SQLException {

        conexao = ConnectionFactory.getConnection();

    }

    public Connection getConnection() {
        return conexao;
    }

    @Override
    public void close() throws SQLException {
        conexao.close();
    }

    public abstract void salvar( Tipo obj ) throws SQLException;

    public abstract void atualizar( Tipo obj ) throws SQLException;

    public abstract void excluir( Tipo obj ) throws SQLException;

    public abstract List<Tipo> listarTodos() throws SQLException;

    public abstract Tipo obterPorId( Long id ) throws SQLException;

}