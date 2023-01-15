<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="cp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>

<html>
  <head>
    <title>Excluir Exemplar</title>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet"
          href="${cp}/css/estilos.css"/>
  </head>

  <body>

    <header>
            <h1>Excluir Exemplar</h1>
    </header>
    <div class="conteudo">
        <form method="post" action="${cp}/processaExemplar">

          <input name="acao" type="hidden" value="excluir"/>
          <input name="id" type="hidden" value="${requestScope.exemplar.codigo_interno}"/>

          <table>
            <tr>
              <td class="alinharDireita">Código interno:</td>
              <td>${requestScope.exemplar.codigo_interno}</td>
            </tr>
            <tr>
              <td class="alinharDireita">Disponível:</td>
              <c:choose>
                <c:when test="${requestScope.exemplar.disponivel}">
                    <td>Disponível</td>
                </c:when>    
                <c:otherwise>
                    <td>Indisponível</td>
                </c:otherwise>
            </c:choose>
            </tr>
            <tr>
              <td class="alinharDireita">Mídia:</td>
              <td>${requestScope.exemplar.midia.titulo}</td>
            </tr>
            <tr>
              <td class="alinharDireita">
                <input type="submit" value="Excluir"/>
              </td>
            </tr>
          </table>
            <a href="${cp}/formularios/exemplar/listagem.jsp"><img src="${cp}/images/voltar.png" alt="Voltar" width="32px" height="32px" title="Voltar"/></a>
        </form>
    </div>
  </body>

</html>
