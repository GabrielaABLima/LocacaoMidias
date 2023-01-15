$( () => {
    
    let itensVenda = [];

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
        
        let $selectProduto = $( "#selectProduto" );
        let $txtQuantidade = $( "#txtQuantidade" );
        
        let idProduto = $selectProduto.val();
        let valorVenda = $selectProduto.find( ":selected" ).data( "valor" ).toString();
        let descricao = $selectProduto.find( ":selected" ).data( "descricao" );
        let quantidade = null;
        
        if ( valorVenda.includes( "," ) ) {
            valorVenda = valorVenda.replace( ",", "." );
        }
        
        try {
            quantidade = new Decimal( $txtQuantidade.val() );
        } catch ( e ) {
        }
        
        if (quantidade !== null && quantidade.greaterThan(0)) {
            itensVenda.push({
                idProduto: idProduto,
                valorVenda: valorVenda,
                descricao: descricao,
                quantidade: quantidade
            });

            atualizarGUI();
            $txtQuantidade.val("");

        } else {
            alert( "Forneça uma quantidade maior que zero!" );
        }
        
    });
    
    $( "#btnRemover" ).on( "click", event => {

        let selecao = $( "#selectItensVenda" ).val();

        if ( selecao.length === 0 ) {
            alert( "Selecione um item da venda para remover!" );

        } else if ( confirm( "Deseja remover o(s) item(ns) da venda selecionado(s)?" ) ) {
            
            for ( let i = 0; i < selecao.length; i++ ) {
                
                for ( let j = 0; j < itensVenda.length; j++ ) {
                    
                    let item = itensVenda[j];
                    
                    if ( selecao[i] === item.idProduto ) {
                        
                        itensVenda.splice( j, 1 );
                        break;
                        
                    }
                    
                }
                
            }
            
            atualizarGUI();
            
        }
        
    });
    
    $( "#btnLimpar" ).on( "click", event => {
        if ( confirm( "Deseja remover todos os itens da venda?" ) ) {
            itensVenda = [];
            atualizarGUI();
        }
    });
    
    $( "#formNovaVenda" ).on( "submit", event => {
        
        if ( $( "#selectItensVenda > option" ).length === 0 ) {
            alert( "Uma venda precisa conter pelo menos um item!" );
            return false;
        }
        
        return true;
        
    });

    $( "#txtQuantidade" ).on( "keydown", event => {
        if ( event.keyCode === 13 ) {
            event.preventDefault();
        }
    });

    let atualizarGUI = () => {
        
        let $select = $( "#selectItensVenda" );
        let total = new Decimal( 0 );
        
        $select.html( "" );
        
        itensVenda.forEach( item => {
            
            let valorItem = new Decimal( item.valorVenda )
                                .times( item.quantidade );
            
            $opt = $( "<option></option>" ).
                    html( `${item.descricao} - ` + 
                    `${fmtMoeda.format( item.valorVenda )} x ` + 
                    `${fmtNumero.format( item.quantidade )} = ` + 
                    `${fmtMoeda.format( valorItem )}` ).
                    val( `${item.idProduto}` );
            
            $select.append( $opt );
            total = total.plus( valorItem );
            
        });
        
        $( "#divTotal" ).html( "Total: " + fmtMoeda.format( total ) );
        $( "#hiddenItensVenda" ).val( JSON.stringify( itensVenda ) );
        
    };
    
});
