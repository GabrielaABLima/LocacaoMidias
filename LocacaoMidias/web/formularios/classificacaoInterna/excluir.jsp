<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="cp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>

<html>
  <head>
    <title>Excluir Classificação Interna</title>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet"
          href="${cp}/css/estilos.css"/>
  </head>

  <body>

    <header>
            <h1>Excluir Classificação Interna</h1>
    </header>
    <div class="conteudo">
        <form method="post" action="${cp}/processaClassificacaoInterna">

          <input name="acao" type="hidden" value="excluir"/>
          <input name="id" type="hidden" value="${requestScope.classificacaoInterna.id}"/>

          <table>
            <tr>
              <td class="alinharDireita">Descrição:</td>
              <td>${requestScope.classificacaoInterna.descricao}</td>
            </tr>
            <tr>
              <td class="alinharDireita">Valor Aluguel:</td>
              <td>${requestScope.classificacaoInterna.valorAluguel}</td>
            </tr>
        
            <tr>
              <td class="alinharDireita">
                <input type="submit" value="Excluir"/>
              </td>
            </tr>
          </table>
            <a href="${cp}/formularios/classificacaoInterna/listagem.jsp"><img src="${cp}/images/voltar.png" alt="Voltar" width="32px" height="32px" title="Voltar"/></a>
        </form>
    </div>
  </body>

</html>
