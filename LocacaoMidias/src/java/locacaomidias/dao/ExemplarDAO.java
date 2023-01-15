package locacaomidias.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import locacaomidias.entidades.Ator;
import locacaomidias.entidades.ClassificacaoEtaria;
import locacaomidias.entidades.ClassificacaoInterna;
import locacaomidias.entidades.Exemplar;
import locacaomidias.entidades.Genero;
import locacaomidias.entidades.Midia;
import locacaomidias.entidades.Tipo;
import locacaomidias.utils.Utils;

public class ExemplarDAO extends DAO<Exemplar>{
    public ExemplarDAO() throws SQLException{  
    }
    
    @Override
    public void salvar( Exemplar obj ) throws SQLException {
        PreparedStatement stmt = getConnection().prepareStatement(
                "INSERT INTO " + 
                "exemplar( disponivel, midia_id ) " + 
                "VALUES( ?, ? );",
                new String[]{ "insert_id" } );

        stmt.setBoolean(1, obj.isDisponivel() );
        stmt.setLong(2, obj.getMidia().getId());

        stmt.executeUpdate();
        obj.setCodigo_interno(Utils.getChavePrimariaAposInsercao( stmt, "insert_id" ) );
        stmt.close();

    }

    @Override
    public void atualizar( Exemplar obj ) throws SQLException {

        PreparedStatement stmt = getConnection().prepareStatement(
                "UPDATE exemplar " + 
                "SET" + 
                "    disponivel = ?," + 
                "    midia_id = ? " + 
                "WHERE" + 
                "    codigo_interno = ?;" );

        stmt.setBoolean(1, obj.isDisponivel() );
        stmt.setLong( 2, obj.getMidia().getId() );
        stmt.setLong( 3, obj.getCodigo_interno());

        stmt.executeUpdate();
        stmt.close();

    }

    @Override
    public void excluir( Exemplar obj ) throws SQLException {

        PreparedStatement stmt = getConnection().prepareStatement(
                "DELETE FROM exemplar " + 
                "WHERE" + 
                "    codigo_interno = ?;" );

        stmt.setLong( 1, obj.getCodigo_interno());

        stmt.executeUpdate();
        stmt.close();

    }

    @Override
    public List<Exemplar> listarTodos() throws SQLException {

        List<Exemplar> lista = new ArrayList<>();

        PreparedStatement stmt = getConnection().prepareStatement(
                "SELECT" + 
                "    e.codigo_interno codigo_interno, " +
                "    e.disponivel disponivel, " + 
                "    m.id idMidia, " + 
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
                "FROM" + 
                "    exemplar e, " + 
                "    midia m, " + 
                "    ator_atriz atr1, " +
                "    ator_atriz atr2, " +
                "    genero g, " +
                "    classificacao_etaria ce, " + 
                "    tipo t, " +
                "    classificacao_interna ci " +
                "WHERE" + 
                "    e.midia_id = m.id AND " +
                "    m.ator_atriz_principal = atr1.id AND " +
                "    m.ator_atriz_coadjuvante = atr2.id AND " +
                "    m.genero_id = g.id AND " +
                "    m.classificacao_etaria_id = ce.id AND " +
                "    m.tipo_id = t.id AND " +
                "    m.classificacao_interna_id = ci.id;");

        ResultSet rs = stmt.executeQuery();

        while ( rs.next() ) {

            Exemplar e = new Exemplar();
            Midia m = new Midia();
            Ator atorPrincipal = new Ator();
            Ator atorCoadjuvante = new Ator();
            Genero g = new Genero();
            ClassificacaoEtaria ce = new ClassificacaoEtaria();
            Tipo t = new Tipo();
            ClassificacaoInterna ci = new ClassificacaoInterna();
            
            e.setCodigo_interno(rs.getLong("codigo_interno"));
            e.setDisponivel(rs.getBoolean("disponivel"));
            
            m.setId(rs.getLong("idMidia"));
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
            
            e.setMidia(m);
            
            lista.add(e);

        }

        rs.close();
        stmt.close();

        return lista;

    }

    @Override
    public Exemplar obterPorId( Long id ) throws SQLException {

        Exemplar e = null;
        Midia m = null;
        Ator atorPrincipal = null;
        Ator atorCoadjuvante = null;
        Genero g = null;
        ClassificacaoEtaria ce = null;
        Tipo t = null;
        ClassificacaoInterna ci = null;

        PreparedStatement stmt = getConnection().prepareStatement(
                "SELECT" + 
                "    e.disponivel disponivel, " + 
                "    m.id idMidia, " + 
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
                "FROM" + 
                "    exemplar e, " + 
                "    midia m, " + 
                "    ator_atriz atr1, " +
                "    ator_atriz atr2, " +
                "    genero g, " +
                "    classificacao_etaria ce, " + 
                "    tipo t, " +
                "    classificacao_interna ci " +
                "WHERE" + 
                "    e.codigo_interno = ? AND " +
                "    e.midia_id = m.id AND " +
                "    m.ator_atriz_principal = atr1.id AND " +
                "    m.ator_atriz_coadjuvante = atr2.id AND " +
                "    m.genero_id = g.id AND " +
                "    m.classificacao_etaria_id = ce.id AND " +
                "    m.tipo_id = t.id AND " +
                "    m.classificacao_interna_id = ci.id;");

        stmt.setLong( 1, id );
        
        ResultSet rs = stmt.executeQuery();

        while ( rs.next() ) {

            e = new Exemplar();
            m = new Midia();
            atorPrincipal = new Ator();
            atorCoadjuvante = new Ator();
            g = new Genero();
            ce = new ClassificacaoEtaria();
            t = new Tipo();
            ci = new ClassificacaoInterna();
            
            e.setCodigo_interno(id);
            e.setDisponivel(rs.getBoolean("disponivel"));
            
            m.setId(rs.getLong("idMidia"));
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
            
            e.setMidia(m);

        }

        rs.close();
        stmt.close();

        return e;

    }
}
