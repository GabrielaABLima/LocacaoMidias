/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package locacaomidias.servicos;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import locacaomidias.dao.AtorDAO;
import locacaomidias.entidades.Ator;

/**
 *
 * @author Gabi
 */
public class AtorServices {
    public List<Ator> getTodos() {

        List<Ator> lista = new ArrayList<>();

        try ( AtorDAO dao = new AtorDAO() ) {
            lista = dao.listarTodos();
        } catch ( SQLException exc ) {
            exc.printStackTrace();
        }

        return lista;

    }
}
