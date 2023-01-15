<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="cp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>

<html>
  <head>
    <title>Excluir Ator</title>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet"
          href="${cp}/css/estilos.css"/>
  </head>

  <body>

    <header>
            <h1>Excluir Ator</h1>
    </header>
    <div class="conteudo">


    <form method="post" action="${cp}/processaAtores">

      <input name="acao" type="hidden" value="excluir"/>
      <input name="id" type="hidden" value="${requestScope.ator.id}"/>

      <table>
        <tr>
          <td>Nome:</td>
          <td>${requestScope.ator.nome}</td>
        </tr>
        <tr>
          <td>Sobrenome:</td>
          <td>${requestScope.ator.sobrenome}</td>
        </tr>
        <tr>
          <td>Data de Estreia:</td>
          <td>
            <fmt:formatDate 
                pattern="dd/MM/yyyy"
                value="${requestScope.ator.dataEstreia}"/>
          </td>
        </tr>
        <tr>
          <td>
            <input type="submit" value="Excluir"/>
          </td>
        </tr>
      </table>
      <a href="${cp}/formularios/ator/listagem.jsp"><img src="${cp}/images/voltar.png" alt="Voltar" width="32px" height="32px" title="Voltar"/></a>
    </form>
    </div>
  </body>

</html>
