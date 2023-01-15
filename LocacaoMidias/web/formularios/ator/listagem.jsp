<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="cp" value="${pageContext.request.contextPath}"/>
<c:set var="prefixo" value="processaAtores?acao=preparar"/>
<!DOCTYPE html>

<html>
    <head>

        <title>Atores Cadastrados</title>

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
            <h1>Atores Cadastrados</h1>
        </header>

        <div class="conteudo">
            <p>
                <a href="${cp}/formularios/ator/novo.jsp">
                    <img src="${cp}/images/novo.png" alt="Novo ator" width="32px" height="32px" title="Novo ator"/>  
                </a>
            </p>
            <table>
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>Nome</th>
                        <th>Sobrenome</th>
                        <th>Data de estreia</th>
                    </tr>
                </thead>
                <tbody>

                    <jsp:useBean 
                        id="servicos"
                        scope="page"
                        class="locacaomidias.servicos.AtorServices"/>

                    <c:forEach items="${servicos.todos}" var="ator">
                        <tr>
                            <td>${ator.id}</td>
                            <td>${ator.nome}</td>
                            <td>${ator.sobrenome}</td>
                            <td>
                                <fmt:formatDate 
                                    pattern="dd/MM/yyyy"
                                    value="${ator.dataEstreia}"/>
                            </td>
                            <td>
                                <a href="${cp}/${prefixo}Alteracao&id=${ator.id}">
                                  Alterar
                                </a>
                            </td>
                            <td>
                                <a href="${cp}/${prefixo}Exclusao&id=${ator.id}">
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
