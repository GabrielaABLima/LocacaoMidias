package vendaprodutos.servicos;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import vendaprodutos.dao.VendaDAO;
import vendaprodutos.entidades.Venda;

public class VendaServices {
    public List<Venda> getTodos() {

        List<Venda> lista = new ArrayList<>();

        try ( VendaDAO dao = new VendaDAO() ) {
            lista = dao.listarTodos();
        } catch ( SQLException exc ) {
            exc.printStackTrace();
        }

        return lista;

    }

}
