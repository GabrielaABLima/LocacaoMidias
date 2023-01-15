<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="cp" value="${pageContext.request.contextPath}"/>
<c:set var="prefixo" value="processaEstados?acao=preparar"/>
<!DOCTYPE html>

<html>
    <head>

        <title>Estados Cadastrados</title>

        <meta charset="UTF-8">
        <meta name="viewport"
              content="width=device-width, initial-scale=1.0">

        <link rel="stylesheet"
              href="${cp}/css/estilos.css"/>

        <script src="${cp}/js/libs/jquery/jquery.min.js"></script>
        <script src="${cp}/js/formularios/locacao/listagem.js"></script>

    </head>

    <body>
        <header>
            <h1>Estados Cadastrados</h1>
        </header>

        <div class="conteudo">
            <p>
                <a href="${cp}/formularios/estado/novo.jsp">
                    <img src="${cp}/images/novo.png" alt="Novo estado" width="32px" height="32px" title="Novo estado"/>  
                </a>
            </p>
            <table class="tabelaListagem">
      <thead>
        <tr>
          <th>Id</th>
          <th>Nome</th>
          <th>Sigla</th>
          <th>Alterar</th>
          <th>Excluir</th>
        </tr>
      </thead>
      <tbody>

        <jsp:useBean 
            id="servicos"
            scope="page"
            class="locacaomidias.servicos.EstadoServices"/>

        <c:forEach items="${servicos.todos}" var="estado">
          <tr>
            <td>${estado.id}</td>
            <td>${estado.nome}</td>
            <td>${estado.sigla}</td>
            <td>
              <a href="${cp}/${prefixo}Alteracao&id=${estado.id}">
                Alterar
              </a>
            </td>
            <td>
              <a href="${cp}/${prefixo}Exclusao&id=${estado.id}">
                Excluir
              </a>
            </td>
          </tr>
        </c:forEach>
      </tbody>
      
    </table>

            <a href="${cp}/index.jsp"><img src="${cp}/images/voltar.png" alt="Voltar" width="32px" height="32px" title="Voltar"/></a>
        </div>

    </body>

</html>
