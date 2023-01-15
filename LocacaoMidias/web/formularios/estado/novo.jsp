<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="cp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>

<html>
    <head>

        <title>Novo Estado</title>

        <meta charset="UTF-8">
        <meta name="viewport"
              content="width=device-width, initial-scale=1.0">

        <link rel="stylesheet"
              href="${cp}/css/estilos.css"/>

        <script src="${cp}/js/libs/jquery/jquery.min.js"></script>
        <script src="${cp}/js/libs/decimal.js/decimal.min.js"></script>
        <script src="${cp}/js/formularios/estafo/novo.js"></script>

    </head>

    <body>
        <header>
            <h1>Novo Estado</h1>
        </header>

        <div class="conteudo">
            <div class="div-centralizada">
                <form id="formNovoEstado" method="post" action="${cp}/processaEstados">

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
                          <td class="alinharDireita">Sigla:</td>
                          <td>
                            <input name="sigla"
                                   type="text"
                                   size="3"
                                   maxlength="2"
                                   required/>
                          </td>
                        </tr>
                        <tr>
                          <a href="${cp}/formularios/estado/listagem.jsp"><img src="${cp}/images/voltar.png" alt="Voltar" width="32px" height="32px" title="Voltar"/></a>
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
