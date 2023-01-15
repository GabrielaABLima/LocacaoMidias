package vendaprodutos.servicos;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import vendaprodutos.dao.UnidadeMedidaDAO;
import vendaprodutos.entidades.UnidadeMedida;

public class UnidadeMedidaServices {
    public List<UnidadeMedida> getTodos() {

        List<UnidadeMedida> lista = new ArrayList<>();

        try ( UnidadeMedidaDAO dao = new UnidadeMedidaDAO() ) {
            lista = dao.listarTodos();
        } catch ( SQLException exc ) {
            exc.printStackTrace();
        }

        return lista;

    }

}
