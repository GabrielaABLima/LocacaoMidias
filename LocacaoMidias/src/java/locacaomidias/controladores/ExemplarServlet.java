/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package locacaomidias.controladores;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import locacaomidias.dao.CidadeDAO;
import locacaomidias.dao.EstadoDAO;
import locacaomidias.dao.ExemplarDAO;
import locacaomidias.dao.MidiaDAO;
import locacaomidias.entidades.Cidade;
import locacaomidias.entidades.Estado;
import locacaomidias.entidades.Exemplar;
import locacaomidias.entidades.Midia;
import locacaomidias.utils.Utils;

/**
 *
 * @author Gabi
 */
@WebServlet(name = "ExemplarServlet", urlPatterns = {"/processaExemplar"})
public class ExemplarServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String acao = request.getParameter( "acao" );
        RequestDispatcher disp = null;

        try ( MidiaDAO daoMidia = new MidiaDAO();
              ExemplarDAO daoExemplar = new ExemplarDAO() ){

            if ( acao.equals( "inserir" ) ) {
                
                
                
                Long idMidia = Utils.getLong( request, "idMidia" );
                Midia m = daoMidia.obterPorId( idMidia );

                Exemplar e = new Exemplar();
                e.setCodigo_interno(Long.valueOf(0));
                e.setDisponivel(true);
                e.setMidia(m);

                Utils.validar( e, "id" );
                daoExemplar.salvar( e );
                
                disp = request.getRequestDispatcher(
                        "/formularios/exemplar/listagem.jsp" );

            } else if ( acao.equals( "alterar" ) ) {

                Long id = Utils.getLong( request, "id" );
                String nome = request.getParameter( "nome" );
                Long codigoInterno = Utils.getLong( request, "codigoInterno" );
                boolean disponivel = Utils.getBoolean(request, "disponivel");
                
                Long idMidia = Utils.getLong("idMidia");
                Midia m = daoMidia.obterPorId( idMidia );

                Exemplar e = daoExemplar.obterPorId(id);
                e.setCodigo_interno(codigoInterno);
                e.setDisponivel(disponivel);
                e.setMidia(m);

                Utils.validar( e );
                daoExemplar.atualizar( e );
                disp = request.getRequestDispatcher(
                        "/formularios/exemplar/listagem.jsp" );

            } else if ( acao.equals( "excluir" ) ) {

                Long id = Utils.getLong( request, "id" );
                Exemplar e = daoExemplar.obterPorId( id );

                daoExemplar.excluir( e );
                disp = request.getRequestDispatcher(
                        "/formularios/exemplar/listagem.jsp" );
                
            } else if ( acao.equals( "listar" ) ) {

                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");

                Jsonb jb = JsonbBuilder.create();
                List<Exemplar> exemplares = new ArrayList<>();
                for(Exemplar e : daoExemplar.listarTodos()){
                    if(e.isDisponivel()){
                        exemplares.add(e);
                    }
                    
                }
                
       
  

                try ( PrintWriter out = response.getWriter() ) {
                    out.print( jb.toJson( exemplares ) );
                }
            } else if ( acao.equals( "obter" ) ) {

                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");

                Jsonb jb = JsonbBuilder.create();
                Long id = Utils.getLong( request, "codigo_interno" );
                Exemplar e = daoExemplar.obterPorId( id );
  

                try ( PrintWriter out = response.getWriter() ) {
                    out.print( jb.toJson( e ) );
                }

            } else if ( acao.equals( "mudarDisponivel" ) ) {

                Long id = Utils.getLong( request, "codigo_interno" );
                Exemplar e = daoExemplar.obterPorId( id );
                
                e.setDisponivel(!e.isDisponivel());
                daoExemplar.atualizar(e);

            } else {
                
                Long id = Utils.getLong( request, "id" );
                Exemplar e = daoExemplar.obterPorId( id );
                request.setAttribute( "exemplar", e );
                
                if ( acao.equals( "prepararAlteracao" ) ) {
                    disp = request.getRequestDispatcher( 
                            "/formularios/exemplar/alterar.jsp" );
                } else if ( acao.equals( "prepararExclusao" ) ) {
                    disp = request.getRequestDispatcher( 
                            "/formularios/exemplar/excluir.jsp" );
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
