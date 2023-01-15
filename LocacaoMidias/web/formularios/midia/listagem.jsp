<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="cp" value="${pageContext.request.contextPath}"/>
<c:set var="prefixo" value="processaMidias?acao=preparar"/>
<!DOCTYPE html>

<html>
    <head>

        <title>Mídias Cadastradas</title>

        <meta charset="UTF-8">
        <meta name="viewport"
              content="width=device-width, initial-scale=1.0">

        <link rel="stylesheet"
              href="${cp}/css/estilos.css"/>

        <script src="${cp}/js/libs/jquery/jquery.min.js"></script>

    </head>

    <body>
        <header>
            <h1>Mídias Cadastradas</h1>
        </header>

        <div class="conteudo">
            <p>
                <a href="${cp}/formularios/midia/novo.jsp">
                    <img src="${cp}/images/novo.png" alt="Nova Mídia" width="32px" height="32px" title="Nova Mídia"/>  
                </a>
            </p>
            <table class="tabelaListagem">
                <thead>
                  <tr>
                    <th>Id</th>
                    <th>Título</th>
                    <th>Ano Lançamento</th><!-- comment -->
                    <th>Cód. Barras</th>
                    <th>Duração em min</th>
                    <th>Ator Principal</th>
                    <th>Ator Coadjuvante</th>
                    <th>Gênero</th>
                    <th>Classificação Etária</th>
                    <th>Tipo</th>
                    <th>Classificação Interna</th>
                    <th>Alterar</th>
                    <th>Excluir</th>
                  </tr>
                </thead>
                <tbody>

                  <jsp:useBean 
                      id="servicos"
                      scope="page"
                      class="locacaomidias.servicos.MidiaServices"/>

                  <c:forEach items="${servicos.todos}" var="m">
                    <tr>
                        <td>${m.id}</td>
                        <td>${m.titulo}</td>
                        <td>${m.anoLancamento}</td>
                        <td>${m.codigoBarras}</td>
                        <td>${m.duracaoEmMinutos}</td>
                        <td>${m.atorPrincipal.nome}</td>
                        <td>${m.atorCoadjuvante.nome}</td>
                        <td>${m.genero.descricao}</td>
                        <td>${m.classificacaoEtaria.descricao}</td>
                        <td>${m.tipo.descricao}</td>
                        <td>${m.classificacaoInterna.descricao}</td>
                      <td>
                        <a href="${cp}/${prefixo}Alteracao&id=${m.id}">
                          Alterar
                        </a>
                      </td>
                      <td>
                        <a href="${cp}/${prefixo}Exclusao&id=${m.id}">
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
