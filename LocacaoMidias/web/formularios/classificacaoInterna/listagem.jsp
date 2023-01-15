<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="cp" value="${pageContext.request.contextPath}"/>
<c:set var="prefixo" value="processaClassificacaoInterna?acao=preparar"/>
<!DOCTYPE html>

<html>
    <head>

            <title>Classificações Internas Cadastradas</title>

        <meta charset="UTF-8">
        <meta name="viewport"
              content="width=device-width, initial-scale=1.0">

        <link rel="stylesheet"
              href="${cp}/css/estilos.css"/>

        <script src="${cp}/js/libs/jquery/jquery.min.js"></script>

    </head>

    <body>
        <header>
            <h1>Classificações Internas Cadastradas</h1>
        </header>

        <div class="conteudo">
            <p>
                <a href="${cp}/formularios/classificacaoInterna/novo.jsp">
                    <img src="${cp}/images/novo.png" alt="Nova Classificação Interna" width="32px" height="32px" title="Nova Classificação Interna"/>  
                </a>
            </p>
            <table class="tabelaListagem">
                <thead>
                  <tr>
                    <th>Id</th>
                    <th>Descrição</th>
                    <th>Valor Aluguel</th>
                    <th>Alterar</th><!-- comment -->
                    <th>Excluir</th>
                  </tr>
                </thead>
                <tbody>

                  <jsp:useBean 
                      id="servicos"
                      scope="page"
                      class="locacaomidias.servicos.ClassificacaoInternaServices"/>

                  <c:forEach items="${servicos.todos}" var="ci">
                    <tr>
                      <td>${ci.id}</td>
                      <td>${ci.descricao}</td>
                      <td>${ci.valorAluguel}</td>
                      <td>
                        <a href="${cp}/${prefixo}Alteracao&id=${ci.id}">
                          Alterar
                        </a>
                      </td>
                      <td>
                        <a href="${cp}/${prefixo}Exclusao&id=${ci.id}">
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
