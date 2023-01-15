package vendaprodutos.servicos;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import vendaprodutos.dao.EstadoDAO;
import vendaprodutos.entidades.Estado;

public class EstadoServices {
    public List<Estado> getTodos() {

        List<Estado> lista = new ArrayList<>();

        try ( EstadoDAO dao = new EstadoDAO() ) {
            lista = dao.listarTodos();
        } catch ( SQLException exc ) {
            exc.printStackTrace();
        }

        return lista;

    }

}
