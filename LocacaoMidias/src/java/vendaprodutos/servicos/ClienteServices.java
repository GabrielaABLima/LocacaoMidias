package vendaprodutos.servicos;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import vendaprodutos.dao.ClienteDAO;
import vendaprodutos.entidades.Cliente;

public class ClienteServices {
    public List<Cliente> getTodos() {

        List<Cliente> lista = new ArrayList<>();

        try ( ClienteDAO dao = new ClienteDAO() ) {
            lista = dao.listarTodos();
        } catch ( SQLException exc ) {
            exc.printStackTrace();
        }

        return lista;

    }

}
