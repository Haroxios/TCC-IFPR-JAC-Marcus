function init() {

    const params = new URLSearchParams(window.location.search)
    const id = params.get('id')

    menuEsquerdo()
    menuSuperior()

    documento()

    buscarUsuario(id)
    //buscarDocumento()
    //buscarCaderno()
}

async function buscarUsuario(id) {

    url = `http://localhost:8080/api/usuario/${id}`

    try {
        const req = await fetch(url)

        if (!req.ok) {
            throw new Error(`Erro HTTP: ${res.status}`)
        }

        const cadernos = JSON.parse(await req.text())
        console.log(cadernos)

    }
    catch (e) {
        console.log('Erro: ', e.message)
    }

}

function buscarDocumento() {}





function menuEsquerdo() {

    //puxar no banco o nome do usuario ou trazer na hora do login
    document.getElementById('h1_usuario').innerHTML = 'PuxarNoBanco'
        
    listaRevisões()
    listaCaderno()
    botoesE()
}

function menuSuperior() {
    botoesS()
    pesquisa()
}

function documento() {

}





function listaRevisões() {
    // com a lista de revisão ordenar em prioridade
    // depois remover o que não precisa ser revisado
    // rederizar

}

function listaCaderno() {

    // quase mesma coisa do revisar
    // mas definir quantidade a ser mostrado e
    // se vai ter outra pagina apenas para mostara os caderno

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