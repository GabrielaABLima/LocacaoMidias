<!--PROJETO LOCAÇÃO DE MÍDIAS-->
<!--Gabriela Andrade Bueno de Lima - BV3008291-->
<!--Thiago Teixeira Pereira - BV3008282-->
<!--Isabella Alegrette Juliano - BV3008347-->

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
                <div class="flex-item">
                    <a href="${cp}/formularios/midia/listagem.jsp">
                        <img class="flex" src="images/midia.png" width="64px" height="64px"/>
                    </a>
                    <p>
                        <a href="${cp}/formularios/midia/listagem.jsp">
                            Mídias
                        </a>
                    </p>
                </div>
                            
                <div class="flex-item">
                    <a href="${cp}/formularios/exemplar/listagem.jsp">
                        <img class="flex" src="images/exemplar.png" width="64px" height="64px"/>
                    </a>
                    <p>
                        <a href="${cp}/formularios/exemplar/listagem.jsp">
                            Exemplares
                        </a>
                    </p>
                </div>
                            
                <div class="flex-item">
                    <a href="${cp}/formularios/cliente/listagem.jsp">
                        <img class="flex" src="images/cliente.png" width="64px" height="64px"/>
                    </a>
                    <p>
                        <a href="${cp}/formularios/cliente/listagem.jsp">
                            Clientes
                        </a>
                    </p>
                </div>
                
                
                
                
                
                
                
            </div>
            <div class="flex-container">
                <div class="flex-item">
                    <a href="${cp}/formularios/classificacaoEtaria/listagem.jsp">
                        <img class="flex" src="images/classificacaoEtaria.png" width="50px" height="50px"/>
                    </a>
                    <p>
                        <a href="${cp}/formularios/classificacaoEtaria/listagem.jsp">
                            Classificações Etárias
                        </a>
                    </p>
                </div>
                <div class="flex-item">
                    <a href="${cp}/formularios/classificacaoInterna/listagem.jsp">
                        <img class="flex" src="images/classificacaoInterna.png" width="50px" height="50px"/>
                    </a>
                    <p>
                        <a href="${cp}/formularios/classificacaoInterna/listagem.jsp">
                            Classificações Internas
                        </a>
                    </p>
                </div>
                <div class="flex-item">
                    <a href="${cp}/formularios/genero/listagem.jsp">
                        <img class="flex" src="images/genero.png" width="64px" height="64px"/>
                    </a>
                    <p>
                        <a href="${cp}/formularios/genero/listagem.jsp">
                            Gêneros
                        </a>
                    </p>
                </div>
                <div class="flex-item">
                    <a href="${cp}/formularios/ator/listagem.jsp">
                        <img class="flex" src="images/ator.png" width="64px" height="64px"/>
                    </a>
                    <p>
                        <a href="${cp}/formularios/ator/listagem.jsp">
                            Atores
                        </a>
                    </p>
                </div>
                
                
                
            </div>
            <div class="flex-container">
                
                
                
                <div class="flex-item">
                    <a href="${cp}/formularios/tipo/listagem.jsp">
                        <img class="flex" src="images/tipo.png" width="64px" height="64px"/>
                    </a>
                    <p>
                        <a href="${cp}/formularios/tipo/listagem.jsp">
                            Tipos
                        </a>
                    </p>
                </div>
                <div class="flex-item">
                    <a href="${cp}/formularios/cidade/listagem.jsp">
                        <img class="flex" src="images/cidade.png" width="64px" height="64px"/>
                    </a>
                    <p>
                        <a href="${cp}/formularios/cidade/listagem.jsp">
                            Cidades
                        </a>
                    </p>
                </div>
                <div class="flex-item">
                    <a href="${cp}/formularios/estado/listagem.jsp">
                        <img class="flex" src="images/estado.png" width="64px" height="64px"/>
                    </a>
                    <p>
                        <a href="${cp}/formularios/estado/listagem.jsp">
                            Estados
                        </a>
                    </p>
                </div>
                
            </div>
        </div>

    </body>

</html>
