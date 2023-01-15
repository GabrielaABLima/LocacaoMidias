package vendaprodutos.servicos;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import vendaprodutos.dao.ProdutoDAO;
import vendaprodutos.entidades.Produto;

public class ProdutoServices {
    public List<Produto> getTodos() {

        List<Produto> lista = new ArrayList<>();

        try ( ProdutoDAO dao = new ProdutoDAO() ) {
            lista = dao.listarTodos();
        } catch ( SQLException exc ) {
            exc.printStackTrace();
        }

        return lista;

    }

}
