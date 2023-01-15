/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package locacaomidias.servicos;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import locacaomidias.dao.ItemLocacaoDAO;
import locacaomidias.entidades.ItemLocacao;

public class ItemLocacaoServices {
    public List<ItemLocacao> getTodos() {

        List<ItemLocacao> lista = new ArrayList<>();

        try ( ItemLocacaoDAO dao = new ItemLocacaoDAO() ) {
            lista = dao.listarTodos();
        } catch ( SQLException exc ) {
            exc.printStackTrace();
        }

        return lista;

    }
}
