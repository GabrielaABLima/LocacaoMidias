package locacaomidias.controladores;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import locacaomidias.dao.AtorDAO;
import locacaomidias.dao.CidadeDAO;
import locacaomidias.dao.EstadoDAO;
import locacaomidias.entidades.Ator;
import locacaomidias.entidades.Cidade;
import locacaomidias.entidades.Estado;
import locacaomidias.utils.Utils;

@WebServlet( name = "AtoresServlet", 
             urlPatterns = { "/processaAtores" } )
public class AtoresServlet extends HttpServlet {

    protected void processRequest( 
            HttpServletRequest request, 
            HttpServletResponse response )
            throws ServletException, IOException {
        
        String acao = request.getParameter( "acao" );
        RequestDispatcher disp = null;

        try ( AtorDAO daoAtor = new AtorDAO() ){

            if ( acao.equals( "inserir" ) ) {

                String nome = request.getParameter( "nome" );
                String sobrenome = request.getParameter( "sobrenome" );
                String dataEstreia = request.getParameter( "dataEstreia" );
                Long idAtor = Utils.getLong( request, "idAtor" );

                Ator a = new Ator();
                a.setNome( nome );
                a.setSobrenome(sobrenome);
                a.setDataEstreia(Utils.getDate(dataEstreia));
                

                Utils.validar( a, "id" );
                daoAtor.salvar( a );
                disp = request.getRequestDispatcher(
                        "/formularios/ator/listagem.jsp" );

            } else if ( acao.equals( "alterar" ) ) {

                Long id = Utils.getLong( request, "id" );
                String nome = request.getParameter( "nome" );
                String sobrenome = request.getParameter( "sobrenome" );
                String dataEstreia = request.getParameter( "dataEstreia" );
                Long idAtor = Utils.getLong( request, "idAtor" );

                Ator a = daoAtor.obterPorId( id );
                a.setNome( nome );
                a.setSobrenome(sobrenome);
                a.setDataEstreia(Utils.getDate(dataEstreia));

                Utils.validar( a );
                daoAtor.atualizar( a );
                disp = request.getRequestDispatcher(
                        "/formularios/ator/listagem.jsp" );

            } else if ( acao.equals( "excluir" ) ) {

                Long id = Utils.getLong( request, "id" );
                Ator a = daoAtor.obterPorId( id );

                daoAtor.excluir( a );
                disp = request.getRequestDispatcher(
                        "/formularios/ator/listagem.jsp" );

            } else {
                
                Long id = Utils.getLong( request, "id" );
                Ator a = daoAtor.obterPorId( id );
                request.setAttribute( "ator", a );
                
                if ( acao.equals( "prepararAlteracao" ) ) {
                    disp = request.getRequestDispatcher( 
                            "/formularios/ator/alterar.jsp" );
                } else if ( acao.equals( "prepararExclusao" ) ) {
                    disp = request.getRequestDispatcher( 
                            "/formularios/ator/excluir.jsp" );
                }
                
            }

        } catch ( SQLException exc ) {
            disp = Utils.prepararDespachoErro( request, exc.getMessage() );
        }

        if ( disp != null ) {
            disp.forward( request, response );
        }
        
    }

    @Override
    protected void doGet( 
            HttpServletRequest request, 
            HttpServletResponse response )
            throws ServletException, IOException {
        processRequest( request, response );
    }

    @Override
    protected void doPost( 
            HttpServletRequest request, 
            HttpServletResponse response )
            throws ServletException, IOException {
        processRequest( request, response );
    }

    @Override
    public String getServletInfo() {
        return "CidadesServlet";
    }

}
