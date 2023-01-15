package locacaomidias.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import locacaomidias.entidades.Cidade;
import locacaomidias.entidades.Cliente;
import locacaomidias.entidades.Estado;
import locacaomidias.entidades.Exemplar;
import locacaomidias.entidades.ItemLocacao;
import locacaomidias.entidades.Locacao;
import locacaomidias.entidades.Midia;
import locacaomidias.utils.Utils;

public class ItemLocacaoDAO extends DAO<ItemLocacao> {
    public ItemLocacaoDAO() throws SQLException{      
    }
    
    @Override
    public void salvar(ItemLocacao obj) throws SQLException {
        PreparedStatement stmt = getConnection().prepareStatement(
                "INSERT INTO " + 
                "item_locacao( locacao_id, exemplar_codigo_interno, valor ) " + 
                "VALUES( ?, ?, ? );"
                /*,
                new String[]{ "insert_id" }*/ );

        stmt.setLong(1, obj.getLocacao().getId());
        stmt.setLong(2, obj.getExemplar().getCodigo_interno());
        stmt.setBigDecimal(3, obj.getValor());

        stmt.executeUpdate();
        //obj.setId( Utils.getChavePrimariaAposInsercao( stmt, "insert_id" ) );
        stmt.close();
    }

    @Override
    public void atualizar(ItemLocacao obj) throws SQLException {
        // não faz sentido nessa implementação,
        // pois não é possível atualizar um item
        // de locação já armazenado
    }

    @Override
    public void excluir(ItemLocacao obj) throws SQLException {
        // não faz sentido nessa implementação,
        // pois não é possível excluir um item
        // de locação já armazenado
    }

    @Override
    public List<ItemLocacao> listarTodos() throws SQLException {
        // nesse caso, não há sentido haver uma listagem por todos
        // os itens de locação, visto que essa entidade é uma entidade
        // de ligação e não faremos atualização/exclusão em locações 
        // já realizadas, a não ser o cancelamento.
        return null;
    }

    @Override
    public ItemLocacao obterPorId(Long id) throws SQLException {
        ItemLocacao il = null;

        PreparedStatement stmt = getConnection().prepareStatement(
                "SELECT" + 
                "    il.valor valor, " +
                "    l.id idLocacao, " + 
                "    e.codigo_interno codigoInternoExemplar, " +
                "FROM" +
                "    item_locacao il, " +
                "    locacao l, " + 
                "    exemplar e " +
                "WHERE il.locacao_id = l.id AND l.exemplar_codigo_interno = e.codigo_interno;" );

        ResultSet rs = stmt.executeQuery();

        if ( rs.next() ) {

            il = new ItemLocacao();
            Locacao l = new Locacao();
            Exemplar e = new Exemplar();
            
            il.setValor( rs.getBigDecimal( "valor" ) );
            
            l.setId( rs.getLong( "idLocacao" ) );
            il.setLocacao(l);
            
            e.setCodigo_interno(rs.getLong("codigoInternoExemplar"));
            il.setExemplar(e);
            
            
            


        }
        rs.close();
        stmt.close();

        return il;
    }
    
    public List<ItemLocacao> obterPorIdLocacao( Long idLocacao ) throws SQLException {

        List<ItemLocacao> itensLocacao = new ArrayList<>();

        PreparedStatement stmt = getConnection().prepareStatement(
                "SELECT" + 
                "    il.valor valor, " +
                "    l.id idLocacao, " + 
                "    e.codigo_interno codigoInternoExemplar, " +
                "    e.midia_id midia_id, " +
                "    e.disponivel disponivel " +
                "FROM" +
                "    item_locacao il, " +
                "    locacao l, " + 
                "    exemplar e " +
                "WHERE il.locacao_id = l.id AND il.exemplar_codigo_interno = e.codigo_interno AND il.locacao_id = ?;" );


        stmt.setLong( 1, idLocacao );

        ResultSet rs = stmt.executeQuery();

        while ( rs.next() ) {

            ItemLocacao il = new ItemLocacao();
            il.setValor(rs.getBigDecimal("valor"));
            
            Locacao l = new Locacao();
            l.setId(idLocacao);
            il.setLocacao(l);
            
            Exemplar p = new Exemplar();
            
            p.setCodigo_interno(rs.getLong( "codigoInternoExemplar" ) );
            p.setDisponivel(rs.getBoolean("disponivel" ) );
            Midia m = new Midia();
            m.setId(rs.getLong("midia_id"));
            p.setMidia( m );
            il.setExemplar(p);
            
            itensLocacao.add( il );

        }

        rs.close();
        stmt.close();

        return itensLocacao;

    }
}
