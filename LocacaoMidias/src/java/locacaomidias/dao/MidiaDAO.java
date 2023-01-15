package locacaomidias.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import locacaomidias.entidades.Ator;
import locacaomidias.entidades.ClassificacaoEtaria;
import locacaomidias.entidades.ClassificacaoInterna;
import locacaomidias.entidades.Genero;
import locacaomidias.entidades.Midia;
import locacaomidias.entidades.Tipo;
import locacaomidias.utils.Utils;

public class MidiaDAO extends DAO<Midia>{
    
    public MidiaDAO() throws SQLException{ 
    }

    @Override
    public void salvar(Midia obj) throws SQLException {
        PreparedStatement stmt = getConnection().prepareStatement(
                "INSERT INTO " + 
                "midia(" + 
                "    titulo, " + 
                "    anoLancamento, " + 
                "    codigoBarras, " + 
                "    duracaoEmMinutos, " + 
                "    ator_atriz_principal, " + 
                "    ator_atriz_coadjuvante, " + 
                "    genero_id, " + 
                "    classificacao_etaria_id, " + 
                "    tipo_id, " + 
                "    classificacao_interna_id ) " + 
                "VALUES( ?, ?, ?, ?, ?, ?, ?, ?, ?, ? );",
                new String[]{ "insert_id" } );

        stmt.setString( 1, obj.getTitulo());
        stmt.setString(2, obj.getAnoLancamento());
        stmt.setString(3, obj.getCodigoBarras());
        stmt.setInt(4, obj.getDuracaoEmMinutos());
        stmt.setLong(5, obj.getAtorPrincipal().getId());
        stmt.setLong(6, obj.getAtorCoadjuvante().getId());
        stmt.setLong(7, obj.getGenero().getId());
        stmt.setLong(8, obj.getClassificacaoEtaria().getId());
        stmt.setLong(9, obj.getTipo().getId());
        stmt.setLong(10, obj.getClassificacaoInterna().getId());


        stmt.executeUpdate();
        obj.setId( Utils.getChavePrimariaAposInsercao( stmt, "insert_id" ) );
        stmt.close();
    }

    @Override
    public void atualizar(Midia obj) throws SQLException {
        PreparedStatement stmt = getConnection().prepareStatement(
                "UPDATE midia " + 
                "SET" + 
                "    titulo = ?, " + 
                "    anoLancamento = ?," + 
                "    codigoBarras = ?, " + 
                "    duracaoEmMinutos = ?, " + 
                "    ator_atriz_principal = ?, " + 
                "    ator_atriz_coadjuvante = ?, " + 
                "    genero_id = ?, " + 
                "    classificacao_etaria_id = ?, " + 
                "    tipo_id = ?, " + 
                "    classificacao_interna_id = ? " + 
                "WHERE" + 
                "    id = ?;" );

        stmt.setString( 1, obj.getTitulo());
        stmt.setString(2, obj.getAnoLancamento());
        stmt.setString(3, obj.getCodigoBarras());
        stmt.setInt(4, obj.getDuracaoEmMinutos());
        stmt.setLong(5, obj.getAtorPrincipal().getId());
        stmt.setLong(6, obj.getAtorCoadjuvante().getId());
        stmt.setLong(7, obj.getGenero().getId());
        stmt.setLong(8, obj.getClassificacaoEtaria().getId());
        stmt.setLong(9, obj.getTipo().getId());
        stmt.setLong(10, obj.getClassificacaoInterna().getId());
        stmt.setLong(11, obj.getId() );

        stmt.executeUpdate();
        stmt.close();
    }

    @Override
    public void excluir(Midia obj) throws SQLException {
        PreparedStatement stmt = getConnection().prepareStatement(
                "DELETE FROM midia " + 
                "WHERE" + 
                "    id = ?;" );

        stmt.setLong( 1, obj.getId() );

        stmt.executeUpdate();
        stmt.close();
    }

    @Override
    public List<Midia> listarTodos() throws SQLException {
        List<Midia> lista = new ArrayList<>();

        PreparedStatement stmt = getConnection().prepareStatement(
                "SELECT " +
                "    m.id id, " +
                "    m.titulo titulo, " +
                "    m.anoLancamento anoLancamento, " +
                "    m.codigoBarras codigoBarras, " +
                "    m.duracaoEmMinutos duracaoEmMinutos, " + 
                "    atr1.id idAtorPrincipal, " +
                "    atr1.nome nomeAtorPrincipal, " +
                "    atr1.sobrenome sobrenomeAtorPrincipal, " +
                "    atr1.dataEstreia dataEstreiaAtorPrincipal, " +
                "    atr2.id idAtorCoadjuvante, " +
                "    atr2.nome nomeAtorCoadjuvante, " +
                "    atr2.sobrenome sobrenomeAtorCoadjuvante, " +
                "    atr2.dataEstreia dataEstreiaAtorCoadjuvante, " +
                "    g.id idGenero, " +
                "    g.descricao descricaoGenero, " +
                "    ce.id idClassificacaoEtaria, " + 
                "    ce.descricao descricaoClassificacaoEtaria, " +
                "    t.id idTipo, " +
                "    t.descricao descricaoTipo, " + 
                "    ci.id idClassificacaoInterna, " +
                "    ci.descricao descricaoClassificacaoInterna, " +
                "    ci.valorAluguel valorAluguelClassificacaoInterna " +
            " FROM" +
                "    midia m, " + 
                "    ator_atriz atr1, " +
                "    ator_atriz atr2, " +
                "    genero g, " +
                "    classificacao_etaria ce, " + 
                "    tipo t, " +
                "    classificacao_interna ci " +
            "WHERE" +
                "    m.ator_atriz_principal = atr1.id AND " +
                "    m.ator_atriz_coadjuvante = atr2.id AND " +
                "    m.genero_id = g.id AND " +
                "    m.classificacao_etaria_id = ce.id AND " +
                "    m.tipo_id = t.id AND " +
                "    m.classificacao_interna_id = ci.id;");

        ResultSet rs = stmt.executeQuery();

        while ( rs.next() ) {

            Midia m = new Midia();
            Ator atorPrincipal = new Ator();
            Ator atorCoadjuvante = new Ator();
            Genero g = new Genero();
            ClassificacaoEtaria ce = new ClassificacaoEtaria();
            Tipo t = new Tipo();
            ClassificacaoInterna ci = new ClassificacaoInterna();
            
            m.setId(rs.getLong("id"));
            m.setTitulo(rs.getString("titulo"));
            m.setAnoLancamento(rs.getString("anoLancamento"));
            m.setCodigoBarras(rs.getString("codigoBarras"));
            m.setDuracaoEmMinutos(rs.getInt("duracaoEmMinutos"));
            
            atorPrincipal.setId(rs.getLong("idAtorPrincipal"));
            atorPrincipal.setNome(rs.getString("nomeAtorPrincipal"));
            atorPrincipal.setSobrenome(rs.getString("sobrenomeAtorPrincipal"));
            atorPrincipal.setDataEstreia(rs.getDate("dataEstreiaAtorPrincipal"));
            m.setAtorPrincipal(atorPrincipal);
            
            atorCoadjuvante.setId(rs.getLong("idAtorCoadjuvante"));
            atorCoadjuvante.setNome(rs.getString("nomeAtorCoadjuvante"));
            atorCoadjuvante.setSobrenome(rs.getString("sobrenomeAtorCoadjuvante"));
            atorCoadjuvante.setDataEstreia(rs.getDate("dataEstreiaAtorCoadjuvante"));
            m.setAtorCoadjuvante(atorCoadjuvante);
            
            g.setId(rs.getLong("idGenero"));
            g.setDescricao(rs.getString("descricaoGenero"));
            m.setGenero(g);
            
            ce.setId(rs.getLong("idClassificacaoEtaria"));
            ce.setDescricao(rs.getString("descricaoClassificacaoEtaria"));
            m.setClassificacaoEtaria(ce);
            
            t.setId(rs.getLong("idTipo"));
            t.setDescricao(rs.getString("descricaoTipo"));
            m.setTipo(t);
            
            ci.setId(rs.getLong("idClassificacaoInterna"));
            ci.setDescricao(rs.getString("descricaoClassificacaoInterna"));
            ci.setValorAluguel(rs.getBigDecimal("valorAluguelClassificacaoInterna"));
            m.setClassificacaoInterna(ci);
            

            lista.add( m );

        }

        rs.close();
        stmt.close();

        return lista;
    }

    @Override
    public Midia obterPorId(Long id) throws SQLException {

        Midia m = null;
        Ator atorPrincipal = null;
        Ator atorCoadjuvante = null;
        Genero g = null;
        ClassificacaoEtaria ce = null;
        Tipo t = null;
        ClassificacaoInterna ci = null;
        
        PreparedStatement stmt = getConnection().prepareStatement(
                "SELECT " +
                "    m.titulo titulo, " +
                "    m.anoLancamento anoLancamento, " +
                "    m.codigoBarras codigoBarras, " +
                "    m.duracaoEmMinutos duracaoEmMinutos, " + 
                "    atr1.id idAtorPrincipal, " +
                "    atr1.nome nomeAtorPrincipal, " +
                "    atr1.sobrenome sobrenomeAtorPrincipal, " +
                "    atr1.dataEstreia dataEstreiaAtorPrincipal, " +
                "    atr2.id idAtorCoadjuvante, " +
                "    atr2.nome nomeAtorCoadjuvante, " +
                "    atr2.sobrenome sobrenomeAtorCoadjuvante, " +
                "    atr2.dataEstreia dataEstreiaAtorCoadjuvante, " +
                "    g.id idGenero, " +
                "    g.descricao descricaoGenero, " +
                "    ce.id idClassificacaoEtaria, " + 
                "    ce.descricao descricaoClassificacaoEtaria, " +
                "    t.id idTipo, " +
                "    t.descricao descricaoTipo, " + 
                "    ci.id idClassificacaoInterna, " +
                "    ci.descricao descricaoClassificacaoInterna, " +
                "    ci.valorAluguel valorAluguelClassificacaoInterna " +
            " FROM" +
                "    midia m, " + 
                "    ator_atriz atr1, " +
                "    ator_atriz atr2, " +
                "    genero g, " +
                "    classificacao_etaria ce, " + 
                "    tipo t, " +
                "    classificacao_interna ci " +
            "WHERE" +
                "    m.id = ? AND " +
                "    m.ator_atriz_principal = atr1.id AND " +
                "    m.ator_atriz_coadjuvante = atr2.id AND " +
                "    m.genero_id = g.id AND " +
                "    m.classificacao_etaria_id = ce.id AND " +
                "    m.tipo_id = t.id AND " +
                "    m.classificacao_interna_id = ci.id;");

        
                   
        stmt.setLong( 1, id );
        
        ResultSet rs = stmt.executeQuery();

        if ( rs.next() ) {

            m = new Midia();
            atorPrincipal = new Ator();
            atorCoadjuvante = new Ator();
            g = new Genero();
            ce = new ClassificacaoEtaria();
            t = new Tipo();
            ci = new ClassificacaoInterna();
            
            m.setId(id);
            m.setTitulo(rs.getString("titulo"));
            m.setAnoLancamento(rs.getString("anoLancamento"));
            m.setCodigoBarras(rs.getString("codigoBarras"));
            m.setDuracaoEmMinutos(rs.getInt("duracaoEmMinutos"));
            
            atorPrincipal.setId(rs.getLong("idAtorPrincipal"));
            atorPrincipal.setNome(rs.getString("nomeAtorPrincipal"));
            atorPrincipal.setSobrenome(rs.getString("sobrenomeAtorPrincipal"));
            atorPrincipal.setDataEstreia(rs.getDate("dataEstreiaAtorPrincipal"));
            m.setAtorPrincipal(atorPrincipal);
            
            atorCoadjuvante.setId(rs.getLong("idAtorCoadjuvante"));
            atorCoadjuvante.setNome(rs.getString("nomeAtorCoadjuvante"));
            atorCoadjuvante.setSobrenome(rs.getString("sobrenomeAtorCoadjuvante"));
            atorCoadjuvante.setDataEstreia(rs.getDate("dataEstreiaAtorCoadjuvante"));
            m.setAtorCoadjuvante(atorCoadjuvante);
            
            g.setId(rs.getLong("idGenero"));
            g.setDescricao(rs.getString("descricaoGenero"));
            m.setGenero(g);
            
            ce.setId(rs.getLong("idClassificacaoEtaria"));
            ce.setDescricao(rs.getString("descricaoClassificacaoEtaria"));
            m.setClassificacaoEtaria(ce);
            
            t.setId(rs.getLong("idTipo"));
            t.setDescricao(rs.getString("descricaoTipo"));
            m.setTipo(t);
            
            ci.setId(rs.getLong("idClassificacaoInterna"));
            ci.setDescricao(rs.getString("descricaoClassificacaoInterna"));
            ci.setValorAluguel(rs.getBigDecimal("valorAluguelClassificacaoInterna"));
            m.setClassificacaoInterna(ci);

        }
        System.out.println(m.getId());
        rs.close();
        stmt.close();

        return m;
    }
    
}
