package locacaomidias.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import locacaomidias.entidades.Ator;
import locacaomidias.utils.Utils;

public class AtorDAO extends DAO<Ator> {

    public AtorDAO() throws SQLException{
    }
    
    @Override
    public void salvar(Ator obj) throws SQLException {
        PreparedStatement stmt = getConnection().prepareStatement(
                "INSERT INTO " + 
                "ator_atriz( nome, sobrenome, dataEstreia ) " + 
                "VALUES( ?, ?, ? );",
                new String[]{ "insert_id" } );

        stmt.setString( 1, obj.getNome() );
        stmt.setString( 2, obj.getSobrenome() );
        stmt.setDate(3, obj.getDataEstreia());

        stmt.executeUpdate();
        obj.setId( Utils.getChavePrimariaAposInsercao( stmt, "insert_id" ) );
        stmt.close();
    }

    @Override
    public void atualizar(Ator obj) throws SQLException {
        PreparedStatement stmt = getConnection().prepareStatement(
                "UPDATE ator_atriz " + 
                "SET" + 
                "    nome = ?," + 
                "    sobrenome = ?, " + 
                "    dataEstreia = ? " +
                "WHERE" + 
                "    id = ?;" );

        stmt.setString( 1, obj.getNome() );
        stmt.setString( 2, obj.getSobrenome() );
        stmt.setDate(3, obj.getDataEstreia());
        stmt.setLong( 4, obj.getId() );

        stmt.executeUpdate();
        stmt.close();
    }

    @Override
    public void excluir(Ator obj) throws SQLException {
        PreparedStatement stmt = getConnection().prepareStatement(
                "DELETE FROM ator_atriz " + 
                "WHERE" + 
                "    id = ?;" );

        stmt.setLong( 1, obj.getId() );

        stmt.executeUpdate();
        stmt.close();
    }

    @Override
    public List<Ator> listarTodos() throws SQLException {
        List<Ator> lista = new ArrayList<>();

        PreparedStatement stmt = getConnection().prepareStatement(
                "SELECT * FROM ator_atriz " + 
                "ORDER BY nome, sobrenome;" );

        ResultSet rs = stmt.executeQuery();

        while ( rs.next() ) {

            Ator e = new Ator();

            e.setId( rs.getLong( "id" ) );
            e.setNome( rs.getString( "nome" ) );
            e.setSobrenome( rs.getString("sobrenome") );
            e.setDataEstreia(rs.getDate("dataEstreia"));

            lista.add( e );

        }

        rs.close();
        stmt.close();

        return lista;
    }

    @Override
    public Ator obterPorId(Long id) throws SQLException {
        Ator ator = null;

        PreparedStatement stmt = getConnection().prepareStatement(
                "SELECT * FROM ator_atriz " + 
                "WHERE id = ?;" );

        stmt.setLong( 1, id );

        ResultSet rs = stmt.executeQuery();

        if ( rs.next() ) {

            ator = new Ator();

            ator.setId( rs.getLong( "id" ) );
            ator.setNome( rs.getString( "nome" ) );
            ator.setSobrenome( rs.getString("sobrenome") );
            ator.setDataEstreia(rs.getDate("dataEstreia"));

        }

        rs.close();
        stmt.close();

        return ator;
    }
    
}
