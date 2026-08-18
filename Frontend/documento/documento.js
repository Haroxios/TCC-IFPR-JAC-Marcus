function init() {
    montarDocumento()
    compartilhamento()
    voltarPagina()

}

async function montarDocumento() {
    const titulo = document.getElementById('h1_tituloDocumento')
    const corpo = document.getElementById('p_textoDocumento')

    //pegar o que está no banco e jogar ali abaixo

    titulo.innerHTML = ':D'
    corpo.innerHTML = 'D:'
}

function compartilhamento() {
    const btnCompartilhar = document.getElementById('btn_compartilhar')

    btnCompartilhar.addEventListener('click', () => {
        //popCompartilhar()
    })
}

function voltarPagina() {
    const voltarSalvar = document.getElementById('a_voltar')

    //salvar antes de voltar ou deixar salvando automatico?
}



//verificar se esse é o melhor caminho para o popup
function popCompartilhar() {
    document.getElementByIb('dial_compartilhamento').showModal()
}



init()