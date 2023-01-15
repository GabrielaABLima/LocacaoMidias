<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="cp" value="${pageContext.request.contextPath}"/>
<c:set var="prefixo" value="processaCidades?acao=preparar"/>
<!DOCTYPE html>

<html>
    <head>

        <title>Cidades Cadastradas</title>

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
            <h1>Cidades Cadastradas</h1>
        </header>

        <div class="conteudo">
            <p>
                <a href="${cp}/formularios/cidade/novo.jsp">
                    <img src="${cp}/images/novo.png" alt="Nova cidade" width="32px" height="32px" title="Nova cidade"/>  
                </a>
            </p>
            <table>
                <thead>
                  <tr>
                    <th>Id</th>
                    <th>Nome</th>
                    <th>Estado</th>
                    <th>Alterar</th>
                    <th>Excluir</th>
                  </tr>
                </thead>
                <tbody>

                  <jsp:useBean
                      id="servicos"
                      scope="page"
                      class="locacaomidias.servicos.CidadeServices"/>

                  <c:forEach items="${servicos.todos}" var="cidade">
                    <tr>
                      <td>${cidade.id}</td>
                      <td>${cidade.nome}</td>
                      <td>${cidade.estado.sigla}</td>
                      <td>
                        <a href="${cp}/${prefixo}Alteracao&id=${cidade.id}">
                          Alterar
                        </a>
                      </td>
                      <td>
                        <a href="${cp}/${prefixo}Exclusao&id=${cidade.id}">
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
