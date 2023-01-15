<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="cp" value="${pageContext.request.contextPath}"/>
<jsp:useBean 
id="servicosE" 
scope="page" 
class="locacaomidias.servicos.ExemplarServices"/>
<!DOCTYPE html>

<html>
    <head>

        <title>Nova Locação de Mídias</title>

        <meta charset="UTF-8">
        <meta name="viewport"
              content="width=device-width, initial-scale=1.0">

        <link rel="stylesheet"
              href="${cp}/css/estilos.css"/>

        <script src="${cp}/js/libs/jquery/jquery.min.js"></script>
        <script src="${cp}/js/libs/decimal.js/decimal.min.js"></script>
        <script src="${cp}/js/formularios/locacao/novo.js"></script>

    </head>

    <body onload="mostrarExemplar()">
        <header>
            <h1>Nova Locação de Mídias</h1>
        </header>

        <div class="conteudo">
            <div class="div-centralizada">
                <form id="formNovaLocacao" method="post" action="${cp}/processaLocacao">

                    <input name="acao" type="hidden" value="inserir"/>
                    <input id="hiddenItensLocacao" name="itensLocacao" type="hidden"/>

                    <div id="divCliente">
                        <jsp:useBean 
                            id="servicosC" 
                            scope="page" 
                            class="locacaomidias.servicos.ClienteServices"/>

                        Cliente:
                        <br>
                        <select id="selectCliente" name="idCliente" required>
                            <c:forEach items="${servicosC.todos}" var="cliente">
                                <option value="${cliente.id}">
                                    ${cliente.nome} ${cliente.sobrenome}
                                </option>
                            </c:forEach>
                        </select>
                    </div>

                    <div id="divItensVenda">
                        <table>
                            <tr>
                                <td>

                                    <jsp:useBean 
                                        id="servicosM" 
                                        scope="page" 
                                        class="locacaomidias.servicos.MidiaServices"/>

                                    <p>
                                        Mídia:
                                        <br>
                                        <select id="idMidia" onchange="mostrarExemplar()">
                                            
                                            <c:forEach items="${servicosM.todos}" var="midia">
                                                <option value="${midia.id}">
                                                    ${midia.titulo} - R$${midia.classificacaoInterna.valorAluguel}
                                                </option>
                                            </c:forEach>
                                        </select>
                                    </p>  

                                    <p>
                                        Exemplar:
                                        <br>
                                        <select id="idExemplar">
                                            
                                        </select>
                                    </p>

                                    

                                </td>
                                
                                <td class="btnsItensVenda">
                                    <p><input id="btnInserir" type="button" value="&#x2795;"/></p>
                                    <p><input id="btnRemover" type="button" value="&#x2796;"/></p>
                                    <p><input id="btnLimpar" type="button" value="&#x274C;"/></p>
                                </td>
                                <td>
                                    Itens da Locação:
                                    <br>
                                    <select id="selectItensLocacao" size="10" multiple>
                                    </select>
                                    <br>
                                    <div>
                                        <div id="divTotal">Total: R$ 0,00</div>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                </td>
                                <td></td>
                                <td class="alinharDireita">
                                    <input id="btnSalvar" type="submit" value="Salvar"/>
                                </td>
                            </tr>
                        </table>
                    </div>
                      
                    <a href="${cp}/formularios/locacao/listagem.jsp"><img src="${cp}/images/voltar.png" alt="Voltar" width="32px" height="32px" title="Voltar"/></a>

                </form>
            </div>
        </div>

    </body>

</html>
