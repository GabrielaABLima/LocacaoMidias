<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="cp" value="${pageContext.request.contextPath}"/>
<c:set var="prefixo" value="processaClassificacaoEtaria?acao=preparar"/>
<!DOCTYPE html>

<html>
    <head>

        <title>Classificações Etárias Cadastradas</title>

        <meta charset="UTF-8">
        <meta name="viewport"
              content="width=device-width, initial-scale=1.0">

        <link rel="stylesheet"
              href="${cp}/css/estilos.css"/>

        <script src="${cp}/js/libs/jquery/jquery.min.js"></script>

    </head>

    <body>
        <header>
            <h1>Classificações Etárias Cadastradas</h1>
        </header>

        <div class="conteudo">
            <p>
                <a href="${cp}/formularios/classificacaoEtaria/novo.jsp">
                    <img src="${cp}/images/novo.png" alt="Nova Classificação Etária" width="32px" height="32px" title="Nova Classificação Etária"/>  
                </a>
            </p>
            <table class="tabelaListagem">
                <thead>
                  <tr>
                    <th>Id</th>
                    <th>Descrição</th>
                  </tr>
                </thead>
                <tbody>

                  <jsp:useBean 
                      id="servicos"
                      scope="page"
                      class="locacaomidias.servicos.ClassificacaoEtariaServices"/>

                  <c:forEach items="${servicos.todos}" var="ce">
                    <tr>
                      <td>${ce.id}</td>
                      <td>${ce.descricao}</td>
                      <td>
                        <a href="${cp}/${prefixo}Alteracao&id=${ce.id}">
                          Alterar
                        </a>
                      </td>
                      <td>
                        <a href="${cp}/${prefixo}Exclusao&id=${ce.id}">
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
