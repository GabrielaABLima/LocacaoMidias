<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="cp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>

<html>
    <head>
        <title>Locação de Mídias</title>
        <meta charset="UTF-8">
        <meta name="viewport"
              content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet"
              href="${cp}/css/estilos.css"/>
    </head>

    <body>
        <header>
            <h1>Locação de Mídias</h1>
        </header>

        <div class="conteudo">
            <div class="flex-container">
                <div class="flex-item">
                    <a href="${cp}/formularios/locacao/listagem.jsp">
                        <img class="flex" src="images/dvd.png" width="64px" height="64px"/>
                    </a>
                    <p>
                        <a href="${cp}/formularios/locacao/listagem.jsp">
                            Locação de Mídias
                        </a>
                    </p>
                </div>
            </div>
        </div>

    </body>

</html>
