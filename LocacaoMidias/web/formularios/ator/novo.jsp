<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="cp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>

<html>
    <head>

        <title>Novo Ator</title>

        <meta charset="UTF-8">
        <meta name="viewport"
              content="width=device-width, initial-scale=1.0">

        <link rel="stylesheet"
              href="${cp}/css/estilos.css"/>

        <script src="${cp}/js/libs/jquery/jquery.min.js"></script>
        <script src="${cp}/js/libs/decimal.js/decimal.min.js"></script>
        <script src="${cp}/js/formularios/locacao/novo.js"></script>

    </head>

    <body>
        <header>
            <h1>Novo Ator</h1>
        </header>

        <div class="conteudo">
            <div class="div-centralizada">
                <form id="formNovoAtor" method="post" action="${cp}/processaAtores">

                    <input name="acao" type="hidden" value="inserir"/>
                    <table>
                        <tr>
                          <td class="alinharDireita">Nome:</td>
                          <td>
                            <input name="nome"
                                   type="text"
                                   size="20"
                                   maxlength="30"
                                   required/>
                          </td>
                        </tr>
                        <tr>
                          <td class="alinharDireita">Sobrenome:</td>
                          <td>
                            <input name="sobrenome"
                                   type="text"
                                   size="20"
                                   maxlength="30"
                                   required/>
                          </td>
                        </tr>
                        <tr>
                          <td class="alinharDireita">Data de Estreia:</td>
                          <td>
                            <input name="dataEstreia"
                                   type="date"
                                   size="8"
                                   placeholder="dd/mm/aaaa"
                                   required/>
                          </td>
                        </tr>
                        
                    
                      
                    <a href="${cp}/formularios/ator/listagem.jsp"><img src="${cp}/images/voltar.png" alt="Voltar" width="32px" height="32px" title="Voltar"/></a>
                    <tr>
                        <td class="alinharDireita">
                          <input type="submit" value="Salvar"/>
                        </td>
                   </tr>
                </form>
            </div>
        </div>

    </body>

</html>
