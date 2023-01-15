<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="cp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>

<html>
  <head>
    <title>Excluir Cidade</title>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet"
          href="${cp}/css/estilos.css"/>
  </head>

  <body>

    <header>
            <h1>Excluir Cidade</h1>
    </header>
    <div class="conteudo">
        <form method="post" action="${cp}/processaCidades">

          <input name="acao" type="hidden" value="excluir"/>
          <input name="id" type="hidden" value="${requestScope.cidade.id}"/>

          <table>
            <tr>
              <td class="alinharDireita">Nome:</td>
              <td>${requestScope.cidade.nome}</td>
            </tr>
            <tr>
              <td class="alinharDireita">Estado:</td>
              <td>${requestScope.cidade.estado.nome} - ${requestScope.cidade.estado.sigla}</td>
            </tr>
            <tr>
             
              <td class="alinharDireita">
                <input type="submit" value="Excluir"/>
              </td>
            </tr>
          </table>
            <a href="${cp}/formularios/cidade/listagem.jsp"><img src="${cp}/images/voltar.png" alt="Voltar" width="32px" height="32px" title="Voltar"/></a>
        </form>
    </div>
  </body>

</html>
