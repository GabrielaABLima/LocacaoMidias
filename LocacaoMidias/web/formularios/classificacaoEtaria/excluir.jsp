<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="cp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>

<html>
  <head>
    <title>Excluir Classificação Etária</title>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet"
          href="${cp}/css/estilos.css"/>
  </head>

  <body>

    <header>
            <h1>Excluir Classificação Etária</h1>
    </header>
    <div class="conteudo">
        <form method="post" action="${cp}/processaClassificacaoEtaria">

          <input name="acao" type="hidden" value="excluir"/>
          <input name="id" type="hidden" value="${requestScope.classificacaoEtaria.id}"/>

          <table>
            <tr>
              <td class="alinharDireita">Descrição:</td>
              <td>${requestScope.classificacaoEtaria.descricao}</td>
            </tr>
        
            <tr>
              <td class="alinharDireita">
                <input type="submit" value="Excluir"/>
              </td>
            </tr>
          </table>
            <a href="${cp}/formularios/classificacaoEtaria/listagem.jsp"><img src="${cp}/images/voltar.png" alt="Voltar" width="32px" height="32px" title="Voltar"/></a>
        </form>
    </div>
  </body>

</html>
