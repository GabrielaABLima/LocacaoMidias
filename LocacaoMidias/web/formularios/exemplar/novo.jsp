<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="cp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>

<html>
    <head>

        <title>Novo Exemplar</title>

        <meta charset="UTF-8">
        <meta name="viewport"
              content="width=device-width, initial-scale=1.0">

        <link rel="stylesheet"
              href="${cp}/css/estilos.css"/>

        <script src="${cp}/js/libs/jquery/jquery.min.js"></script>
        <script src="${cp}/js/libs/decimal.js/decimal.min.js"></script>


    </head>

    <body>
        <header>
            <h1>Novo Exemplar</h1>
        </header>

        <div class="conteudo">
            <div class="div-centralizada">
                <form id="formNovoExemplo" method="post" action="${cp}/processaExemplar">

                    <input name="acao" type="hidden" value="inserir"/>
                    <input name="disponivel" type="hidden" value="true"/>
                    <table>
                       
                        <tr>
                          <td class="alinharDireita">Mídia:</td>
                          <td>

                            <jsp:useBean 
                                id="servicos"
                                scope="page"
                                class="locacaomidias.servicos.MidiaServices"/>

                            <select name="idMidia" required>
                              <c:forEach items="${servicos.todos}" var="midia">
                                <option value="${midia.id}">
                                  ${midia.titulo}
                                </option>
                              </c:forEach>
                            </select>

                          </td>
                        </tr>
                        <tr>
                          <a href="${cp}/formularios/exemplar/listagem.jsp"><img src="${cp}/images/voltar.png" alt="Voltar" width="32px" height="32px" title="Voltar"/></a>
                          <td class="alinharDireita">
                            <input type="submit" value="Salvar"/>
                          </td>
                        </tr>
                      </table>
                </form>
            </div>
        </div>

    </body>

</html>
