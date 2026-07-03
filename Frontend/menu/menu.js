function init() {
    menuEsquerdo()
    menuSuperior()

    documento()
}



function menuEsquerdo() {
    botoesE()
}

function menuSuperior() {
    botoesS()
    pesquisa()
}

function documento() {

}


function botoesE() {
    const btnCriar = document.getElementById('btn_criar')
    const btnSair = document.getElementById('btn_sair')

    btnCriar.addEventListener('click', () => {
        window.location.href = '../documento/documento.html'
    })

    btnSair.addEventListener('click', () => {
        window.location.href = '../index.html'
    })
}

function botoesS() {
    const btnNovaPasta = document.getElementById('btn_novaPasta')
    const btnOrdenar = document.getElementById('btn_ordenar')
    const btnFiltrar = document.getElementById('btn_filtrar')
    
}

function pesquisa() {
    const inpProcurar = document.getElementById('inp_procurar')
    const btnProcurar = document.getElementById('btn_procurar')

    btnProcurar.addEventListener('click', () => {

    })
}



init()