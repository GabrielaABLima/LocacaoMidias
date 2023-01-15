/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package locacaomidias.servicos;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import locacaomidias.dao.ClassificacaoInternaDAO;
import locacaomidias.entidades.ClassificacaoInterna;

/**
 *
 * @author Gabi
 */
public class ClassificacaoInternaServices {
     public List<ClassificacaoInterna> getTodos() {

        List<ClassificacaoInterna> lista = new ArrayList<>();

        try ( ClassificacaoInternaDAO dao = new ClassificacaoInternaDAO() ) {
            lista = dao.listarTodos();
        } catch ( SQLException exc ) {
            exc.printStackTrace();
        }

        return lista;

    }
}
