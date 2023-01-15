<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="cp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>

<html>
  <head>
    <title>Alterar Mídia</title>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet"
          href="${cp}/css/estilos.css"/>
  </head>

  <body>

    <header>
            <h1>Alterar Mídia</h1>
    </header>
    <div class="conteudo">

    <form method="post" action="${cp}/processaMidias">

      <input name="acao" type="hidden" value="alterar"/>
      <input name="id" type="hidden" value="${requestScope.midia.id}"/>

      <table>
                        <tr>
                          <td class="alinharDireita">Título:</td>
                          <td>
                            <input name="titulo"
                                   type="text"
                                   size="20"
                                   maxlength="30"
                                   required
                                   value="${requestScope.midia.titulo}"/>
                          </td>
                          
                        </tr>
                        <tr>
                          <td class="alinharDireita">Ano de Lançamento:</td>
                          <td>
                            <input name="anoLancamento"
                                   type="text"
                                   size="20"
                                   maxlength="4"
                                   required
                                   value="${requestScope.midia.anoLancamento}"/>
                          </td>
                          
                        </tr>
                        <tr>
                          <td class="alinharDireita">Código de Barras:</td>
                          <td>
                            <input name="codigoBarras"
                                   type="text"
                                   size="20"
                                   maxlength="13"
                                   required
                                   value="${requestScope.midia.codigoBarras}"/>
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
                                    required
                                    value="${requestScope.midia.duracaoEmMinutos}"/>
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
                                <c:forEach items="${servicos.todos}" var="ator">
                                  <c:choose>
                                    <c:when test="${requestScope.midia.atorPrincipal.id eq ator.id}">
                                      <option value="${ator.id}" selected>
                                        ${ator.nome} ${ator.sobrenome}
                                      </option>
                                    </c:when>
                                    <c:otherwise>
                                      <option value="${ator.id}">
                                        ${ator.nome} ${ator.sobrenome}
                                      </option>
                                    </c:otherwise>
                                  </c:choose>
                                </c:forEach>
                              </select>

                            </td>
                          </tr>
                        
                        <tr>
                            <td class="alinharDireita">Ator Coadjuvante:</td>
                            <td>

                              <select name="idAtorCoadjuvante" required>
                                <c:forEach items="${servicos.todos}" var="ator">
                                  <c:choose>
                                    <c:when test="${requestScope.midia.atorCoadjuvante.id eq ator.id}">
                                      <option value="${ator.id}" selected>
                                        ${ator.nome} ${ator.sobrenome}
                                      </option>
                                    </c:when>
                                    <c:otherwise>
                                      <option value="${ator.id}">
                                        ${ator.nome} ${ator.sobrenome}
                                      </option>
                                    </c:otherwise>
                                  </c:choose>
                                </c:forEach>
                              </select>

                            </td>
                        </tr>
                        <tr>
                            <td class="alinharDireita">Gênero:</td>
                            <td>

                              <jsp:useBean 
                                  id="generoServicos"
                                  scope="page"
                                  class="locacaomidias.servicos.GeneroServices"/>

                              <select name="idGenero" required>
                                <c:forEach items="${generoServicos.todos}" var="genero">
                                  <c:choose>
                                    <c:when test="${requestScope.midia.genero.id eq genero.id}">
                                      <option value="${genero.id}" selected>
                                        ${genero.descricao}
                                      </option>
                                    </c:when>
                                    <c:otherwise>
                                      <option value="${genero.id}">
                                        ${genero.descricao}
                                      </option>
                                    </c:otherwise>
                                  </c:choose>
                                </c:forEach>
                              </select>

                            </td>
                          </tr>
                       <tr>
                            <td class="alinharDireita">Classificação Etária:</td>
                            <td>

                              <jsp:useBean 
                                  id="ceServicos"
                                  scope="page"
                                  class="locacaomidias.servicos.ClassificacaoEtariaServices"/>

                              <select name="idClassificacaoEtaria" required>
                                <c:forEach items="${ceServicos.todos}" var="ce">
                                  <c:choose>
                                    <c:when test="${requestScope.midia.classificacaoEtaria.id eq ce.id}">
                                      <option value="${ce.id}" selected>
                                        ${ce.descricao}
                                      </option>
                                    </c:when>
                                    <c:otherwise>
                                      <option value="${ce.id}">
                                        ${ce.descricao}
                                      </option>
                                    </c:otherwise>
                                  </c:choose>
                                </c:forEach>
                              </select>

                            </td>
                          </tr>
                        <tr>
                            <td class="alinharDireita">Tipo:</td>
                            <td>

                              <jsp:useBean 
                                  id="tipoServicos"
                                  scope="page"
                                  class="locacaomidias.servicos.TipoServices"/>

                              <select name="idTipo" required>
                                <c:forEach items="${tipoServicos.todos}" var="tipo">
                                  <c:choose>
                                    <c:when test="${requestScope.midia.tipo.id eq tipo.id}">
                                      <option value="${tipo.id}" selected>
                                        ${tipo.descricao}
                                      </option>
                                    </c:when>
                                    <c:otherwise>
                                      <option value="${tipo.id}">
                                        ${tipo.descricao}
                                      </option>
                                    </c:otherwise>
                                  </c:choose>
                                </c:forEach>
                              </select>

                            </td>
                          </tr>
                        <tr>
                            <td class="alinharDireita">Classificação Interna:</td>
                            <td>

                              <jsp:useBean 
                                  id="ciServicos"
                                  scope="page"
                                  class="locacaomidias.servicos.ClassificacaoInternaServices"/>

                              <select name="idClassificacaoInterna" required>
                                <c:forEach items="${ciServicos.todos}" var="ci">
                                  <c:choose>
                                    <c:when test="${requestScope.midia.classificacaoInterna.id eq ci.id}">
                                      <option value="${ci.id}" selected>
                                        ${ci.descricao} - R$${ci.valorAluguel}
                                      </option>
                                    </c:when>
                                    <c:otherwise>
                                      <option value="${ci.id}">
                                        ${ci.descricao} - R$${ci.valorAluguel}
                                      </option>
                                    </c:otherwise>
                                  </c:choose>
                                </c:forEach>
                              </select>

                            </td>
                          </tr>
                       
                        <tr>
                          <td class="alinharDireita">
                            <input type="submit" value="Salvar"/>
                          </td>
                        </tr>
                      </table>
          <a href="${cp}/formularios/midia/listagem.jsp"><img src="${cp}/images/voltar.png" alt="Voltar" width="32px" height="32px" title="Voltar"/></a>
    </form>
    </div>
  </body>

</html>
