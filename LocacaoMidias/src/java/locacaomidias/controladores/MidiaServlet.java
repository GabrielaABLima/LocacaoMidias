/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package locacaomidias.controladores;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import locacaomidias.dao.AtorDAO;
import locacaomidias.dao.CidadeDAO;
import locacaomidias.dao.ClassificacaoEtariaDAO;
import locacaomidias.dao.ClassificacaoInternaDAO;
import locacaomidias.dao.EstadoDAO;
import locacaomidias.dao.GeneroDAO;
import locacaomidias.dao.MidiaDAO;
import locacaomidias.dao.TipoDAO;
import locacaomidias.entidades.Ator;
import locacaomidias.entidades.Cidade;
import locacaomidias.entidades.ClassificacaoEtaria;
import locacaomidias.entidades.ClassificacaoInterna;
import locacaomidias.entidades.Estado;
import locacaomidias.entidades.Genero;
import locacaomidias.entidades.Midia;
import locacaomidias.entidades.Tipo;
import locacaomidias.utils.Utils;

/**
 *
 * @author Gabi
 */
@WebServlet(name = "MidiaServlet", urlPatterns = {"/processaMidias"})
public class MidiaServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String acao = request.getParameter( "acao" );
        RequestDispatcher disp = null;

        try ( AtorDAO daoAtor = new AtorDAO();
              GeneroDAO daoGenero = new GeneroDAO();
              ClassificacaoEtariaDAO daoCe = new ClassificacaoEtariaDAO();
              ClassificacaoInternaDAO daoCi = new ClassificacaoInternaDAO();
              TipoDAO daoTipo = new TipoDAO();
              MidiaDAO daoMidia = new MidiaDAO()){

            if ( acao.equals( "inserir" ) ) {

                String titulo = request.getParameter( "titulo" );
                String anoLancamento = request.getParameter("anoLancamento");
                String codigoBarras = request.getParameter( "codigoBarras" );
                int duracaoEmMinutos = Utils.getInt( request, "duracaoEmMinutos" );
                
                
                Long idAtorPrincipal = Utils.getLong( request, "idAtorPrincipal" );
                Long idAtorCoadjuvante = Utils.getLong( request, "idAtorCoadjuvante" );
                Long idGenero = Utils.getLong( request, "idGenero" );
                Long idClassificacaoEtaria = Utils.getLong( request, "idClassificacaoEtaria" );
                Long idTipo = Utils.getLong( request, "idTipo" );
                Long idClassificacaoInterna = Utils.getLong( request, "idClassificacaoInterna" );
                
                Ator atorPrincipal = daoAtor.obterPorId(idAtorPrincipal);
                Ator atorCoadjuvante = daoAtor.obterPorId(idAtorCoadjuvante);
                Genero g = daoGenero.obterPorId(idGenero);
                ClassificacaoEtaria ce = daoCe.obterPorId(idClassificacaoEtaria);
                ClassificacaoInterna ci = daoCi.obterPorId(idClassificacaoInterna);
                Tipo t = daoTipo.obterPorId(idTipo);
                
                Midia m = new Midia();
                m.setTitulo(titulo);
                m.setAnoLancamento(anoLancamento);
                m.setCodigoBarras(codigoBarras);
                m.setDuracaoEmMinutos(duracaoEmMinutos);
                m.setAtorPrincipal(atorPrincipal);
                m.setAtorCoadjuvante(atorCoadjuvante);
                m.setGenero(g);
                m.setClassificacaoEtaria(ce);
                m.setTipo(t);
                m.setClassificacaoInterna(ci);


                Utils.validar( m, "id" );
                daoMidia.salvar( m );
                disp = request.getRequestDispatcher(
                        "/formularios/midia/listagem.jsp" );

            } else if ( acao.equals( "alterar" ) ) {

                Long id = Utils.getLong( request, "id" );
                String titulo = request.getParameter( "titulo" );
                String anoLancamento = request.getParameter("anoLancamento");
                String codigoBarras = request.getParameter( "codigoBarras" );
                int duracaoEmMinutos = Utils.getInt( request, "duracaoEmMinutos" );
                
                
                Long idAtorPrincipal = Utils.getLong( request, "idAtorPrincipal" );
                Long idAtorCoadjuvante = Utils.getLong( request, "idAtorCoadjuvante" );
                Long idGenero = Utils.getLong( request, "idGenero" );
                Long idClassificacaoEtaria = Utils.getLong( request, "idClassificacaoEtaria" );
                Long idTipo = Utils.getLong( request, "idTipo" );
                Long idClassificacaoInterna = Utils.getLong( request, "idClassificacaoInterna" );
                
                Ator atorPrincipal = daoAtor.obterPorId(idAtorPrincipal);
                Ator atorCoadjuvante = daoAtor.obterPorId(idAtorCoadjuvante);
                Genero g = daoGenero.obterPorId(idGenero);
                ClassificacaoEtaria ce = daoCe.obterPorId(idClassificacaoEtaria);
                ClassificacaoInterna ci = daoCi.obterPorId(idClassificacaoInterna);
                Tipo t = daoTipo.obterPorId(idTipo);
                
                Midia m = daoMidia.obterPorId(id);
                m.setTitulo(titulo);
                m.setAnoLancamento(anoLancamento);
                m.setCodigoBarras(codigoBarras);
                m.setDuracaoEmMinutos(duracaoEmMinutos);
                m.setAtorPrincipal(atorPrincipal);
                m.setAtorCoadjuvante(atorCoadjuvante);
                m.setGenero(g);
                m.setClassificacaoEtaria(ce);
                m.setTipo(t);
                m.setClassificacaoInterna(ci);

                Utils.validar( m );
                daoMidia.atualizar( m );
                disp = request.getRequestDispatcher(
                        "/formularios/midia/listagem.jsp" );

            } else if ( acao.equals( "excluir" ) ) {

                Long id = Utils.getLong( request, "id" );
                Midia m = daoMidia.obterPorId( id );

                daoMidia.excluir( m );
                disp = request.getRequestDispatcher(
                        "/formularios/midia/listagem.jsp" );

            } else {
                
                Long id = Utils.getLong( request, "id" );
                Midia m = daoMidia.obterPorId( id );
                request.setAttribute( "midia", m );
                
                if ( acao.equals( "prepararAlteracao" ) ) {
                    disp = request.getRequestDispatcher( 
                            "/formularios/midia/alterar.jsp" );
                } else if ( acao.equals( "prepararExclusao" ) ) {
                    disp = request.getRequestDispatcher( 
                            "/formularios/midia/excluir.jsp" );
                }
                
            }

        } catch ( SQLException exc ) {
            disp = Utils.prepararDespachoErro( request, exc.getMessage() );
        }

        if ( disp != null ) {
            disp.forward( request, response );
        }
        
    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
