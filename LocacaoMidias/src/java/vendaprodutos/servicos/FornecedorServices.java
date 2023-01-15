package vendaprodutos.servicos;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import vendaprodutos.dao.FornecedorDAO;
import vendaprodutos.entidades.Fornecedor;

public class FornecedorServices {
    public List<Fornecedor> getTodos() {

        List<Fornecedor> lista = new ArrayList<>();

        try ( FornecedorDAO dao = new FornecedorDAO() ) {
            lista = dao.listarTodos();
        } catch ( SQLException exc ) {
            exc.printStackTrace();
        }

        return lista;

    }

}
