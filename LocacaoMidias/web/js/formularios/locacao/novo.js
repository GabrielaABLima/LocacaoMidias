$( () => {
    
    let itensLocacao = [];

    let fmtMoeda = new Intl.NumberFormat( 
        "pt-BR", {
            style: "currency",
            currency: "BRL"
        }
    );
    
    let fmtNumero = new Intl.NumberFormat( 
        "pt-BR", {
            minimumFractionDigits: 2,
            maximumFractionDigits: 2
        }
    );
    
    $( "#btnInserir" ).on( "click", event => {
        
        let $selectProduto = $( "#idExemplar" );
        let idProduto = $selectProduto.val();
        console.log(idProduto);
        let valorVenda = "";
        if (idProduto == null) {
                alert( "Selecione uma mídia e um exemplar" );
        } else {
            $.ajax( "../../processaExemplar", {
            data: {
                    acao: "obter",
                    codigo_interno: idProduto
                },
                datatype: "text"
            }).done( ( data ) =>{
                console.log(data);
                $.ajax( "../../processaExemplar", {
                data: {
                        acao: "mudarDisponivel",
                        codigo_interno: idProduto
                    },
                    datatype: "text"
                });
                mostrarExemplar(event);
                valorVenda = data.midia.classificacaoInterna.valorAluguel;
                console.log(idProduto);
                
                   itensLocacao.push({
                        codigo_interno: idProduto,
                        valorVenda: valorVenda,
                        titulo: data.midia.titulo
                    });
                    console.log(itensLocacao);
                    
                    atualizarGUI();


            }).fail( ( jqXHR, textStatus, errorThrown ) => {
                alert( "Erro: " + errorThrown + "\n" +
                "Status: " + textStatus );
             });
     }
        
        
    });
    
    $( "#btnRemover" ).on( "click", event => {

        let selecao = $( "#selectItensLocacao" ).val();
        console.log(selecao);

        if ( selecao.length === 0 ) {
            alert( "Selecione um item da venda para remover!" );

        } else if ( confirm( "Deseja remover o(s) item(ns) da venda selecionado(s)?" ) ) {
            
            for ( let i = 0; i < selecao.length; i++ ) {
                
                for ( let j = 0; j < itensLocacao.length; j++ ) {
                    
                    let item = itensLocacao[j];
                    
                    if ( selecao[i] == item.codigo_interno ) {
                        
                        itensLocacao.splice( j, 1 );
                        $.ajax( "../../processaExemplar", {
                        data: {
                                acao: "mudarDisponivel",
                                codigo_interno: item.codigo_interno
                            },
                            datatype: "text"
                        });
                        
                        mostrarExemplar();
                        console.log(itensLocacao);
                        break;
                        
                    }
                    
                }
                
            }
            
            atualizarGUI();
            
        }
        
    });
    
    $( "#btnLimpar" ).on( "click", event => {
        if ( confirm( "Deseja remover todos os itens da venda?" ) ) {
            for ( let j = 0; j < itensLocacao.length; j++ ) {
                    
                    let item = itensLocacao[j];
                    $.ajax( "../../processaExemplar", {
                        data: {
                                acao: "mudarDisponivel",
                                codigo_interno: item.codigo_interno
                            },
                            datatype: "text"
                        });
                        
                        
                    
            }
            itensLocacao = [];
            atualizarGUI();
        }
    });
    
    $( "#formNovaLocacao" ).on( "submit", event => {
        
        if ( $( "#selectItensLocacao > option" ).length === 0 ) {
            alert( "Uma venda precisa conter pelo menos um item!" );
            return false;
        }
        
        return true;
        
    });

    let atualizarGUI = () => {
        mostrarExemplar();
        let $select = $( "#selectItensLocacao" );
        let total = new Decimal( 0 );
        
        $select.html( "" );
        
        itensLocacao.forEach( item => {
            
            let valorItem = new Decimal( item.valorVenda );
            
            $opt = $( "<option></option>" ).
                    html( `${item.titulo} - ` + 
                    `${fmtMoeda.format( item.valorVenda )}` ).
                    val( `${item.codigo_interno}` );
            
            $select.append( $opt );
            total = total.plus( valorItem );
            
        });
        
        $( "#divTotal" ).html( "Total: " + fmtMoeda.format( total ) );
        $( "#hiddenItensLocacao" ).val( JSON.stringify( itensLocacao ) );
        
    };
    
});

async function mostrarExemplar(){
  let selecionarExemplar = document.getElementById("idExemplar");  
  selecionarExemplar.innerHTML = "";
  let midiaId = document.getElementById("idMidia").value; 
  let listar = "listar";
  console.log(midiaId);
  
  $.ajax( "../../processaExemplar", {
        data: {
            acao: listar
        },
        datatype: "text"
    }).done( ( data ) =>{
        
        console.log(data);
        data.forEach(exemplar => {
            if(exemplar.midia.id == midiaId){
                if(exemplar.disponivel){
                    let opcaoE = document.createElement("option");
                    opcaoE.setAttribute('value', exemplar.codigo_interno);
                    let texto = document.createTextNode(exemplar.codigo_interno);
                    opcaoE.appendChild(texto);
                    selecionarExemplar.appendChild(opcaoE);

                }
                
            }

        });
    }).fail( ( jqXHR, textStatus, errorThrown ) => {
        alert( "Erro: " + errorThrown + "\n" +
        "Status: " + textStatus );
     });
};