async function init() {

    
    const id = sessionStorage.getItem('ursLogado')
    //const id = 1

    if(!id) {
        window.location.href = '../index.html'
    }

    buscarUsuario(id)
    const cadernos = await buscarCadernos(id) 
    const documentos = await buscarDocumentos(cadernos.map(c => c.idCaderno))
    
    //buscar Tags

    renderizar(documentos)
    funcionalidades()

}

function dados() {
    
}

function renderizar(documentos) {

    //console.log(documentos)
    rendCards(documentos)
    
    const docListar = documentos.map(d => ({
        idDocumento: d.idDocumento,
        idCaderno: d.caderno.idCaderno,
        titulo: d.titulo,
        caderno: d.caderno.caderno,
        dataUltimaEdicao: d.dataUltimaEdicao,
    }))

    //console.log(docListar)
    rendProximasRev(docListar)
    rendCadernos(docListar)

}


function funcionalidades() {

    botoes()
/*
    pesquisa()
    sair()*/

}



/*
-----------------------------------------------------------
--------------     Procura os dados     -------------------
---------------------- mudar dps-  ------------------------
*/

//vai ser trocado para pegar na sessão do navegador
async function buscarUsuario(id) {
    const usr = document.getElementById('h1_usuario')
    const url = `http://localhost:8080/api/usuario/${id}`

    try {
        const res = await fetch(url)

        if (!res.ok) {
            throw new Error(`Erro HTTP: ${res.status}`)
        }

        const usuario = await res.json()    
        usr.innerHTML = usuario.usuario

    }
    catch (e) {
        console.log('Erro: ', e.message)
    }
}

async function buscarCadernos(id) {
    const url = `http://localhost:8080/api/caderno/usuario/${id}`

    try {
        const res = await fetch(url, {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
        })

        if (!res.ok) {
            throw new Error(`Erro HTTP: ${res.status}`)
        }

        const cadernosUsuario = await res.json()
        return cadernosUsuario

    }
    catch (e) {
        console.log('Erro: ', e.message)  
    }
}

async function buscarDocumentos(idCadernos) {
    const url = `http://localhost:8080/api/documento/DocsUsr`

    try {
        const res = await fetch(url, {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(idCadernos)
        })

        if(!res.ok) {
            throw new Error(`Erro HTTP: ${res.status}`)
        }

        const documentosUsuario = await res.json()
        return documentosUsuario
    }
    catch (e) {
        console.log('Erro: ', e.message)
    }
}




/*
-----------------------------------------------------------
---------------     Renderizações          ----------------
-----------------------------------------------------------
*/

function rendCards(documentos) {
    const contDocumentos = document.getElementById('cont_documentos')
    const fragmento = document.createDocumentFragment()

    documentos.forEach (item => {
        const card = document.createElement('a')
        card.id = 'a_cards'
        card.href = `/documento/documento.html?id=${item.idDocumento}`

        const contDocumento = document.createElement('div')
        contDocumento.id = 'cont_documento' 

        const contStatus = document.createElement('div')
        const statusAtual = document.createElement('div')
        contStatus.id = 'cont_status'
        statusAtual.id = 'cont_statusAtual'

        const contTitulo = document.createElement('div')
        const titulo = document.createElement('h1')
        contTitulo.id = 'cont_titulo'
        titulo.id = 'tituloDoc'

        const contTrecho = document.createElement('div')
        const previa = document.createElement('p')
        contTrecho.id = 'cont_trecho'
        previa.id = 'trechoDoc'

        const contTags = document.createElement('div')
        const contTag = document.createElement('div')
        const tag = document.createElement('p')
        contTags.id = 'cont_tags'
        contTag.id = 'cont_tag'
        tag.id = 'tagDoc'

        titulo.textContent = item.titulo
        previa.textContent = item.conteudo

        contTitulo.appendChild(titulo)
        contTrecho.appendChild(previa)

        contDocumento.appendChild(contTitulo)
        contDocumento.appendChild(contTrecho)

        card.appendChild(contDocumento)

        fragmento.appendChild(card)
    })
    
    contDocumentos.appendChild(fragmento)
}


function rendProximasRev(docListar) {
    const proximasRevisoes = document.getElementById('cont_proximasRevisoes')
    const fragmento = document.createDocumentFragment()

    docListar.forEach (e => {
        const revisaoDoc = document.createElement('a')
        revisaoDoc.className = 'link_menuEsquerdo'
        revisaoDoc.href = `/documento/documento.html?id=${e.idDocumento}`
        revisaoDoc.innerHTML = e.titulo

        fragmento.appendChild(revisaoDoc)
    })

    proximasRevisoes.appendChild(fragmento)

}


function rendCadernos(docListar) {

    // DEFINIR MELHOR DEPOIS O QUE QUERO COM ISTO
    
    /*const listarCadernos = document.getElementById('cont_listaCadernos')
    const fragmento = document.createDocumentFragment()

    listarCadernos.forEach (e => {
        const caderno = document.createElement('a')
        caderno.className = 'link_menuEsquerdo'
        caderno.



    })*/

}

/*
-----------------------------------------------------------
---------------            Botões          ----------------
-----------------------------------------------------------
*/

function botoes() {

    criarDocumento()
}

async function criarDocumento() {

    const criarDoc = document.getElementById('btn_criar')

    criarDoc.addEventListener('click', async ()  => {

        const url = 'http://localhost:8080/api/documento'

        try {
            const response = await fetch(url)

            if (!response.ok) {
                throw new Error(`Erro HTTP: ${res.status}`)
            }



        }
        catch (e) {
            console.log('Erro: ' +e.message)
        }

        window.location.href = `../documento/documento.html?id=${id}`
    })
}



init()