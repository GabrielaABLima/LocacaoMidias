<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="cp" value="${pageContext.request.contextPath}"/>
<c:set var="prefixo" value="processaExemplar?acao=preparar"/>
<!DOCTYPE html>

<html>
    <head>

        <title>Exemplares Cadastrados</title>

        <meta charset="UTF-8">
        <meta name="viewport"
              content="width=device-width, initial-scale=1.0">

        <link rel="stylesheet"
              href="${cp}/css/estilos.css"/>

        <script src="${cp}/js/libs/jquery/jquery.min.js"></script>

    </head>

    <body>
        <header>
            <h1>Exemplares Cadastrados</h1>
        </header>

        <div class="conteudo">
            <p>
                <a href="${cp}/formularios/exemplar/novo.jsp">
                    <img src="${cp}/images/novo.png" alt="Novo estado" width="32px" height="32px" title="Novo estado"/>  
                </a>
            </p>
            <table class="tabelaListagem">
      <thead>
        <tr>
          <th>Código Interno</th>
          <th>Disponível</th>
          <th>Mídia</th>
          <th>Excluir</th>
        </tr>
      </thead>
      <tbody>

        <jsp:useBean 
            id="servicos"
            scope="page"
            class="locacaomidias.servicos.ExemplarServices"/>

        <c:forEach items="${servicos.todos}" var="exemplar">
          <tr>
            <td>${exemplar.codigo_interno}</td>
            
            <c:choose>
                <c:when test="${exemplar.disponivel}">
                    <td>Disponível</td>
                </c:when>    
                <c:otherwise>
                    <td>Indisponível</td>
                </c:otherwise>
            </c:choose>
            <td>${exemplar.midia.titulo}</td>
           
            <td>
              <a href="${cp}/${prefixo}Exclusao&id=${exemplar.codigo_interno}">
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
