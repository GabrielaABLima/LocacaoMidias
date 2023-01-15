<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="cp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>

<html>
  <head>
    <title>Alterar Cidade</title>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet"
          href="${cp}/css/estilos.css"/>
  </head>

  <body>

    <header>
            <h1>Alterar Cidade</h1>
    </header>
    <div class="conteudo">

    <form method="post"  action="${cp}/processaCidades">

      <input name="acao" type="hidden" value="alterar"/>
      <input name="id" type="hidden" value="${requestScope.cidade.id}"/>

      <table>
        <tr>
          <td class="alinharDireita">Nome:</td>
          <td>
            <input name="nome"
                   type="text"
                   size="20"
                   maxlength="30"
                   required
                   value="${requestScope.cidade.nome}"/>
          </td>
        </tr>
        <tr>
          <td class="alinharDireita">Estado:</td>
          <td>

            <jsp:useBean 
                id="servicos"
                scope="page"
                class="locacaomidias.servicos.EstadoServices"/>

            <select name="idEstado" required>
              <c:forEach items="${servicos.todos}" var="estado">
                <c:choose>
                  <c:when test="${requestScope.cidade.estado.id eq estado.id}">
                    <option value="${estado.id}" selected>
                      ${estado.nome} - ${estado.sigla}
                    </option>
                  </c:when>
                  <c:otherwise>
                    <option value="${estado.id}">
                      ${estado.nome} - ${estado.sigla}
                    </option>
                  </c:otherwise>
                </c:choose>
              </c:forEach>
            </select>

          </td>
        </tr>
        <tr>
          <td class="alinharDireita">
            <input type="submit" value="Alterar"/>
          </td>
        </tr>
      </table>
      <a href="${cp}/formularios/cidade/listagem.jsp"><img src="${cp}/images/voltar.png" alt="Voltar" width="32px" height="32px" title="Voltar"/></a>
    </form>
    </div>

  </body>

</html>
