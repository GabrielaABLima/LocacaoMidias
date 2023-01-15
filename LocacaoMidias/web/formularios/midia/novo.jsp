<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="cp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>

<html>
    <head>

        <title>Nova Mídia</title>

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
            <h1>Nova Mídia</h1>
        </header>

        <div class="conteudo">
            <div class="div-centralizada">
                <form id="formNovoGenero" method="post" action="${cp}/processaMidias">

                    <input name="acao" type="hidden" value="inserir"/>
                    <table>
                        <tr>
                          <td class="alinharDireita">Título:</td>
                          <td>
                            <input name="titulo"
                                   type="text"
                                   size="20"
                                   maxlength="30"
                                   required/>
                          </td>
                          
                        </tr>
                        <tr>
                          <td class="alinharDireita">Ano de Lançamento:</td>
                          <td>
                            <input name="anoLancamento"
                                   type="text"
                                   size="20"
                                   maxlength="4"
                                   required/>
                          </td>
                          
                        </tr>
                        <tr>
                          <td class="alinharDireita">Código de Barras:</td>
                          <td>
                            <input name="codigoBarras"
                                   type="text"
                                   size="20"
                                   maxlength="13"
                                   required/>
                          </td>
                          
                        </tr>
                        <tr>
                          <td class="alinharDireita">Duração em Minutos:</td>
                          <td>
                            <input name="duracaoEmMinutos"
                                   type="number"
                                    size="8"
                                    step="1"
                                    min="0"
                                    required/>
                          </td>
                          
                        </tr>
                        <tr>
                          <td class="alinharDireita">Ator Principal:</td>
                          <td>

                            <jsp:useBean 
                                id="servicos" 
                                scope="page" 
                                class="locacaomidias.servicos.AtorServices"/>

                            <select name="idAtorPrincipal" required>
                              <c:forEach items="${servicos.todos}" var="atorP">
                                <option value="${atorP.id}">
                                  ${atorP.nome} ${atorP.sobrenome}
                                </option>
                              </c:forEach>
                            </select>

                          </td>
                        </tr>
                        <tr>
                          <td class="alinharDireita">Ator Coadjuvante:</td>
                          <td>

                            <select name="idAtorCoadjuvante" required>
                              <c:forEach items="${servicos.todos}" var="atorC">
                                <option value="${atorC.id}">
                                  ${atorC.nome} ${atorC.sobrenome}
                                </option>
                              </c:forEach>
                            </select>

                          </td>
                        </tr>
                        <tr>
                          <td class="alinharDireita">Gênero:</td>
                          <td>

                            <jsp:useBean 
                                id="generoService" 
                                scope="page" 
                                class="locacaomidias.servicos.GeneroServices"/>

                            <select name="idGenero" required>
                              <c:forEach items="${generoService.todos}" var="genero">
                                <option value="${genero.id}">
                                  ${genero.descricao}
                                </option>
                              </c:forEach>
                            </select>

                          </td>
                        </tr>
                       <tr>
                          <td class="alinharDireita">Classificação Etária:</td>
                          <td>

                            <jsp:useBean 
                                id="ceService" 
                                scope="page" 
                                class="locacaomidias.servicos.ClassificacaoEtariaServices"/>

                            <select name="idClassificacaoEtaria" required>
                              <c:forEach items="${ceService.todos}" var="ce">
                                <option value="${ce.id}">
                                  ${ce.descricao}
                                </option>
                              </c:forEach>
                            </select>

                          </td>
                        </tr>
                        <tr>
                          <td class="alinharDireita">Tipo:</td>
                          <td>

                            <jsp:useBean 
                                id="tipoService" 
                                scope="page" 
                                class="locacaomidias.servicos.TipoServices"/>

                            <select name="idTipo" required>
                              <c:forEach items="${tipoService.todos}" var="tipo">
                                <option value="${tipo.id}">
                                  ${tipo.descricao}
                                </option>
                              </c:forEach>
                            </select>

                          </td>
                        </tr>
                        <tr>
                          <td class="alinharDireita">Classificação Interna:</td>
                          <td>

                            <jsp:useBean 
                                id="ciService" 
                                scope="page" 
                                class="locacaomidias.servicos.ClassificacaoInternaServices"/>

                            <select name="idClassificacaoInterna" required>
                              <c:forEach items="${ciService.todos}" var="ci">
                                <option value="${ci.id}">
                                  ${ci.descricao} - R$${ci.valorAluguel}
                                </option>
                              </c:forEach>
                            </select>

                          </td>
                        </tr>
                       
                        <tr>
                          <a href="${cp}/formularios/midia/listagem.jsp"><img src="${cp}/images/voltar.png" alt="Voltar" width="32px" height="32px" title="Voltar"/></a>
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
