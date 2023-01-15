package vendaprodutos.servicos;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import vendaprodutos.dao.CidadeDAO;
import vendaprodutos.entidades.Cidade;

public class CidadeServices {
    public List<Cidade> getTodos() {

        List<Cidade> lista = new ArrayList<>();

        try ( CidadeDAO dao = new CidadeDAO() ) {
            lista = dao.listarTodos();
        } catch ( SQLException exc ) {
            exc.printStackTrace();
        }

        return lista;

    }

}
