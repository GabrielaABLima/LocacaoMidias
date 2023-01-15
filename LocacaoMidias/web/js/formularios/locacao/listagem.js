function cancelarLocacao( event, cp ) {
          
    if ( confirm( "Deseja mesmo cancelar essa locação?" ) ) {

        let id = event.target.dataset.id;

        let parametros = new URLSearchParams();
        parametros.append( "acao", "cancelar" );
        parametros.append( "id", id );

        fetch( `${cp}/processaLocacao`, {
            method: "POST",
            body: parametros
        }).then( response => {
            return response.json();
        }).then( data => {
            if ( data.status === "ok" ) {
                event.target.parentElement.innerHTML = "Cancelada";
                var canceladaT = document.querySelector(`#canceladaT[value="${id}"]`);
                var devolvidaT = document.querySelector(`#devolvidaT[value="${id}"]`);
                canceladaT.innerHTML = "";
                canceladaT.innerHTML = "Cancelada";
                devolvidaT.innerHTML = "";
                devolvidaT.innerHTML = "Devolver";
            } else {
                alert( "Ocorreu um erro na sua requisição!" );
            }
            
        }).catch( error => {
            alert( "Erro: " + error );
        });
    
    }

}

function devolverLocacao( event, cp ) {
          
    if ( confirm( "Deseja mesmo devolver essa locação?" ) ) {

        let id = event.target.dataset.id;

        let parametros = new URLSearchParams();
        parametros.append( "acao", "devolver" );
        parametros.append( "id", id );

        fetch( `${cp}/processaLocacao`, {
            method: "POST",
            body: parametros
        }).then( response => {
            console.log(response);
            return response.json();
        }).then( data => {
            console.log(data);
            if ( data.status === "ok" ) {
                event.target.parentElement.innerHTML = "Devolvida";
                //element = document.getElementById("dataFim");
                var elementValue = document.querySelector(`#dataFim[value="${id}"]`);
                elementValue.innerHTML = "";
                var dia = new Date(data.dataFim);
                dia.setDate(dia.getDate() + 1);
                elementValue.innerHTML = `${dia.toLocaleDateString()}`;
                //element.innerHTML = `${data.dataFim}`;
                var canceladaT = document.querySelector(`#canceladaT[value="${id}"]`);
                var devolvidaT = document.querySelector(`#devolvidaT[value="${id}"]`);
                canceladaT.innerHTML = "";
                canceladaT.innerHTML = "Cancelar";
                devolvidaT.innerHTML = "";
                devolvidaT.innerHTML = "Devolvida";
            } else {
                alert( "Ocorreu um erro na sua requisição!" );
            }

        }).catch( error => {
            alert( "Erro: " + error );
        });
    
    }

}