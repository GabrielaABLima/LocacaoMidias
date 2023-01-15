/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package locacaomidias.controladores;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import locacaomidias.dao.ClassificacaoInternaDAO;
import locacaomidias.dao.ClienteDAO;
import locacaomidias.dao.ExemplarDAO;
import locacaomidias.dao.ItemLocacaoDAO;
import locacaomidias.dao.LocacaoDAO;
import locacaomidias.entidades.ClassificacaoInterna;
import locacaomidias.entidades.Cliente;
import locacaomidias.entidades.Exemplar;
import locacaomidias.entidades.ItemLocacao;
import locacaomidias.entidades.Locacao;
import locacaomidias.utils.Utils;

/**
 *
 * @author Gabi
 */
@WebServlet(name = "LocacaoServlet", urlPatterns = {"/processaLocacao"})
public class LocacaoServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String acao = request.getParameter( "acao" );
        RequestDispatcher disp = null;

        try ( LocacaoDAO dao = new LocacaoDAO();
              ClienteDAO daoCliente = new ClienteDAO();
              ExemplarDAO daoExemplar = new ExemplarDAO();
              ItemLocacaoDAO daoItemLocacao = new ItemLocacaoDAO()){

            if ( acao.equals( "inserir" ) ) {

                Long idCliente = Utils.getLong( request, "idCliente" );
                String itensLocacao = request.getParameter( "itensLocacao" );

                JsonReader jsr = Json.createReader( 
                        new StringReader( itensLocacao ) );

                JsonArray jsaItensLocacao = jsr.readArray();
                
                Cliente c = daoCliente.obterPorId( idCliente );
                
                Locacao l = new Locacao();
                l.setDataInicio(Date.valueOf( LocalDate.now() ) );
                l.setCancelada( false );
                l.setDataFim(Date.valueOf("1800-01-01"));
                l.setCliente( c );
                
                Utils.validar( l, "id" );
                dao.salvar( l );
                System.out.println(l.getId());

                for ( JsonValue jsv : jsaItensLocacao ) {

                    JsonObject jso = jsv.asJsonObject();

                    Long idExemplar = Utils.getLong( 
                            jso.getString( "codigo_interno" ) );
                    
                    Exemplar e = daoExemplar.obterPorId( idExemplar );
                    e.setDisponivel( false );

                    ItemLocacao il = new ItemLocacao();
                    il.setLocacao( l );
                    il.setExemplar( e );
                    il.setValor( e.getMidia().getClassificacaoInterna().getValorAluguel() );

                    daoExemplar.atualizar( e );
                    daoItemLocacao.salvar( il );
                    
                }
                
                disp = request.getRequestDispatcher(
                        "/formularios/locacao/listagem.jsp" );

            } else if ( acao.equals( "alterar" ) ) {

                Long id = Utils.getLong( request, "id" );
                String dataInicio = request.getParameter( "dataInicio" );
                String dataFim = request.getParameter( "dataFim" );
                Boolean cancelada = Utils.getBoolean( request, "cancelada" );
                
                Long idCliente = Utils.getLong( request, "idCliente" );

                Cliente c = daoCliente.obterPorId( idCliente );
                
                Locacao loc = dao.obterPorId(id);
                loc.setDataInicio(Utils.getDate(dataInicio));
                loc.setDataFim(Utils.getDate(dataFim));
                loc.setCancelada(cancelada);
                loc.setCliente(c);

                Utils.validar( loc );
                dao.atualizar( loc );
                disp = request.getRequestDispatcher(
                        "/formularios/estados/listagem.jsp" );
                
            } else if ( acao.equals( "cancelar" ) ) {

                Long id = Utils.getLong( request, "id" );
                Locacao l = dao.obterPorId( id );
                System.out.println(id);
                System.out.println(l.getId());
                System.out.println(l.isCancelada() + "antes");
                l.setCancelada( true );
                dao.atualizar( l );
                
                System.out.println(l.isCancelada() + "depois");
                
                for ( ItemLocacao il : daoItemLocacao.obterPorIdLocacao( id ) ) {
                     Exemplar e = daoExemplar.obterPorId( il.getExemplar().getCodigo_interno() );
                     e.setDisponivel(true);
                     daoExemplar.atualizar( e );
                }

                response.setContentType( "application/json;charset=UTF-8" );
                
                JsonObject jo = Json.createObjectBuilder()
                        .add( "status", "ok" )
                        .build();
                
                System.out.println(jo.toString());
                try ( PrintWriter out = response.getWriter() ) {
                    out.print( jo );
                }

            } else if ( acao.equals( "devolver" ) ) {

                Long id = Utils.getLong( request, "id" );
                Locacao l = dao.obterPorId( id );
                l.setCancelada( false );
                l.setDataFim(Date.valueOf(LocalDate.now()));
                dao.atualizar( l );
                
                for ( ItemLocacao il : daoItemLocacao.obterPorIdLocacao( id ) ) {
                    Exemplar p = il.getExemplar();
                    p.setDisponivel(true);
                    daoExemplar.atualizar( p );
                }

                response.setContentType( "application/json;charset=UTF-8" );
                System.out.println("teste");
                JsonObject jo = Json.createObjectBuilder()
                        .add( "status", "ok" )
                        .add("dataFim", l.getDataFim().toString())
                        .build();
                
                System.out.println(jo.toString());
                try ( PrintWriter out = response.getWriter() ) {
                    out.print( jo );
                }

            
            } else if ( acao.equals( "excluir" ) ) {

                Long id = Utils.getLong( request, "id" );
                Locacao loc = dao.obterPorId( id );

                dao.excluir( loc );
                disp = request.getRequestDispatcher(
                        "/formularios/estados/listagem.jsp" );

            } else {
                
                Long id = Utils.getLong( request, "id" );
                Locacao loc = dao.obterPorId( id );
                request.setAttribute( "locacao", loc );
                
                if ( acao.equals( "prepararAlteracao" ) ) {
                    disp = request.getRequestDispatcher( 
                            "/formularios/estados/alterar.jsp" );
                } else if ( acao.equals( "prepararExclusao" ) ) {
                    disp = request.getRequestDispatcher( 
                            "/formularios/estados/excluir.jsp" );
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
