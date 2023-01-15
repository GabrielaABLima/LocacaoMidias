<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="cp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>

<html>
    <head>

        <title>Nova Cidade</title>

        <meta charset="UTF-8">
        <meta name="viewport"
              content="width=device-width, initial-scale=1.0">

        <link rel="stylesheet"
              href="${cp}/css/estilos.css"/>

        <script src="${cp}/js/libs/jquery/jquery.min.js"></script>
        <script src="${cp}/js/libs/decimal.js/decimal.min.js"></script>
        <script src="${cp}/js/formularios/cidade/novo.js"></script>

    </head>

    <body>
        <header>
            <h1>Nova Cidade</h1>
        </header>

        <div class="conteudo">
            <div class="div-centralizada">
                <form id="formNovaCidade" method="post" action="${cp}/processaCidades">

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
                          <td class="alinharDireita">Estado:</td>
                          <td>

                            <jsp:useBean 
                                id="servicos"
                                scope="page"
                                class="locacaomidias.servicos.EstadoServices"/>

                            <select name="idEstado" required>
                              <c:forEach items="${servicos.todos}" var="estado">
                                <option value="${estado.id}">
                                  ${estado.nome} - ${estado.sigla}
                                </option>
                              </c:forEach>
                            </select>

                          </td>
                        </tr>
                        
                      
                        
                    
                      
                    <a href="${cp}/formularios/cidade/listagem.jsp"><img src="${cp}/images/voltar.png" alt="Voltar" width="32px" height="32px" title="Voltar"/></a>
                    <tr>
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
