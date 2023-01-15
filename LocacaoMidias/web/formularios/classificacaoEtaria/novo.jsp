<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="cp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>

<html>
    <head>

        <title>Nova Classificação Etária</title>

        <meta charset="UTF-8">
        <meta name="viewport"
              content="width=device-width, initial-scale=1.0">

        <link rel="stylesheet"
              href="${cp}/css/estilos.css"/>

        <script src="${cp}/js/libs/jquery/jquery.min.js"></script>
        <script src="${cp}/js/libs/decimal.js/decimal.min.js"></script>

    </head>

    <body>
        <header>
            <h1>Nova Classificação Etária</h1>
        </header>

        <div class="conteudo">
            <div class="div-centralizada">
                <form id="formNovaCE" method="post" action="${cp}/processaClassificacaoEtaria">

                    <input name="acao" type="hidden" value="inserir"/>
                    <table>
                        <tr>
                          <td class="alinharDireita">Descrição:</td>
                          <td>
                            <input name="descricao"
                                   type="text"
                                   size="20"
                                   maxlength="30"
                                   required/>
                          </td>
                          
                        </tr>
                        
                       
                        <tr>
                          <a href="${cp}/formularios/classificacaoEtaria/listagem.jsp"><img src="${cp}/images/voltar.png" alt="Voltar" width="32px" height="32px" title="Voltar"/></a>
                          <td class="alinharDireita">
                            <input type="submit" value="Salvar"/>
                          </td>
                        </tr>
                      </table>
                </form>
            </div>
        </div>

    </body>

</html>
