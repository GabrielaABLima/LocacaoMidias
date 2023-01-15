<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="cp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>

<html>
    <head>

        <title>Locações Cadastradas</title>

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
            <h1>Locações Cadastradas</h1>
        </header>

        <div class="conteudo">
            <p>
                <a href="${cp}/formularios/locacao/novo.jsp">
                    <img src="${cp}/images/novo.png" alt="Nova Locação" width="32px" height="32px" title="Nova Locação"/>  
                </a>
            </p>
            <table>
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>Data de início</th>
                        <th>Data de entrega</th>
                        <th>Cliente</th>
                        <th>Cancelar</th>
                        <th>Devolver</th>
                    </tr>
                </thead>
                <tbody>

                    <jsp:useBean 
                        id="servicos"
                        scope="page"
                        class="locacaomidias.servicos.LocacaoServices"/>

                    <c:forEach items="${servicos.todos}" var="loc">
                        <tr>
                            <td>${loc.id}</td>
                            <td>
                                <fmt:formatDate 
                                    pattern="dd/MM/yyyy"
                                    value="${loc.dataInicio}"/>
                            </td>
                            <td id="dataFim" value="${loc.id}">
                                <c:choose>
                                    <c:when test="${loc.dataFim == '1800-01-01'}">
                                        -
                                    </c:when>    
                                    <c:otherwise>
                                        <fmt:formatDate 
                                        pattern="dd/MM/yyyy"
                                        value="${loc.dataFim}"/>
                                    </c:otherwise>
                                </c:choose>
                                
                            </td>

                            <td>${loc.cliente.nome} ${loc.cliente.sobrenome}</td>
                            <td id="canceladaT" value="${loc.id}">
                                <c:choose>
                                    <c:when test="${loc.cancelada}">
                                        Cancelada
                                    </c:when>
                                    <c:otherwise>
                                        <c:choose>
                                        <c:when test="${loc.dataFim == '1800-01-01'}">
                                            <a href="#" data-id="${loc.id}" onclick="cancelarLocacao(event, '${cp}')">
                                                Cancelar
                                            </a>
                                        </c:when>
                                        <c:otherwise>
                                            Cancelar
                                        </c:otherwise>
                                        </c:choose>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td id="devolvidaT" value="${loc.id}">
                                <c:choose>
                                    <c:when test="${loc.dataFim == '1800-01-01'}">
                                        <c:choose>
                                            <c:when test="${loc.cancelada}">
                                                Devolver
                                            </c:when>
                                            <c:otherwise>
                                                <a href="#" data-id="${loc.id}" onclick="devolverLocacao(event, '${cp}')">
                                                    Devolver
                                                </a>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:when>
                                    <c:otherwise>
                                        Devolvido
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            
                        </tr>
                    </c:forEach>
                </tbody>

            </table>

            <a href="${cp}/index.jsp"><img src="${cp}/images/voltar.png" alt="Voltar" width="32px" height="32px" title="Voltar"/></a>
        </div>

    </body>

</html>
