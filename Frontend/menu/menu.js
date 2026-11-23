// caches usados pelas funções de filtro/ordenação
let documentosCache = []
let cadernosCache = []

async function init() {
    const id = sessionStorage.getItem('usuarioLogado')

    if (!id) {
        window.location.href = '../index.html'
        return
    }

    await buscarUsuario(id)

    let cadernos = await buscarCadernos(id)
    if (!cadernos) {
        return
    }

    if (cadernos.length === 0) {
        const criado = await criarCadernoPadrao(id)
        if (!criado) {
            return
        }

        cadernos = await buscarCadernos(id)
        if (!cadernos) {
            return
        }
    }
    cadernosCache = cadernos

    const idCadernos = cadernos.map(c => c.idCaderno)

    const documentos = await buscarDocumentos(idCadernos)

    if (!documentos) {
        return
    }

    documentos.sort((a, b) => (a.titulo || '').localeCompare(b.titulo || ''))
    documentosCache = documentos

    renderizar(documentos)
    funcionalidades()
}

function renderizar(documentos) {
    rendCards(documentos)

    const docListar = documentos.map(d => ({
        idDocumento: d.idDocumento,
        idCaderno: d.caderno ? d.caderno.idCaderno : null,
        titulo: d.titulo,
        caderno: nomeCaderno(d.caderno ? d.caderno.idCaderno : null),
        dataProximaRevisao: d.dataProximaRevisao,
        statusRevisao: d.statusRevisao
    }))

    rendProximasRev(docListar)
    rendCadernos(cadernosCache)
}

function nomeCaderno(idCaderno) {
    const caderno = cadernosCache.find(c => c.idCaderno === idCaderno)
    return caderno ? caderno.caderno : ''
}

function funcionalidades() {
    criarDocumento()
    pesquisar()
    ordenar()
    filtrarStatus()
    sair()
}

/*
-----------------------------------------------------------
--------------     Procura os dados     -------------------
-----------------------------------------------------------
*/

async function buscarUsuario(id) {
    const usr = document.getElementById('h1_usuario')
    const url = `http://localhost:8080/api/usuario/${id}`

    try {
        const res = await fetch(url)

        if (!res.ok) {
            throw new Error(`Erro HTTP: ${res.status}`)
        }

        const usuario = await res.json()
        usr.textContent = usuario.usuario
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
            headers: { 'Content-Type': 'application/json' }
        })

        if (!res.ok) {
            throw new Error(`Erro HTTP: ${res.status}`)
        }

        return await res.json()
    }
    catch (e) {
        console.log('Erro: ', e.message)
        return null
    }
}

async function buscarDocumentos(idCadernos) {
    const url = 'http://localhost:8080/api/documento/DocsUsr'

    try {
        const res = await fetch(url, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(idCadernos)
        })

        if (!res.ok) {
            throw new Error(`Erro HTTP: ${res.status}`)
        }

        return await res.json()
    }
    catch (e) {
        console.log('Erro: ', e.message)
        return null
    }
}

async function criarCadernoPadrao(id) {
    try {
        const res = await fetch('http://localhost:8080/api/caderno', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({
                caderno: 'Geral',
                usuario: { idUsuario: Number(id) }
            })
        })

        if (!res.ok) {
            throw new Error(`Erro HTTP: ${res.status}`)
        }

        await res.text()
        return true
    }
    catch (e) {
        console.log('Erro: ', e.message)
        return false
    }
}

/*
-----------------------------------------------------------
---------------     Renderizações          ----------------
-----------------------------------------------------------
*/

function limparCards() {
    document.querySelectorAll('#cont_documentos .card_doc').forEach(el => el.remove())
}

function rendCards(documentos) {
    const contDocumentos = document.getElementById('cont_documentos')
    const fragmento = document.createDocumentFragment()

    if (!documentos || documentos.length === 0) {
        const vazio = document.createElement('p')
        vazio.className = 'msg_vazio'
        vazio.textContent = 'Nenhum documento encontrado.'
        fragmento.appendChild(vazio)
    }

    documentos.forEach(item => {
        const card = document.createElement('a')
        card.className = 'card_doc'
        card.href = `../documento/documento.html?id=${item.idDocumento}`

        const contDocumento = document.createElement('div')
        contDocumento.className = 'cont_documento'

        const contStatus = document.createElement('div')
        contStatus.className = 'cont_status'
        const statusAtual = document.createElement('div')
        statusAtual.className = 'cont_statusAtual'
        statusAtual.classList.add(`status_${(item.statusRevisao || '').toLowerCase()}`)
        contStatus.appendChild(statusAtual)

        const contTitulo = document.createElement('div')
        contTitulo.className = 'cont_titulo'
        const titulo = document.createElement('h1')
        titulo.className = 'tituloDoc'
        titulo.textContent = item.titulo || '(sem título)'
        contTitulo.appendChild(titulo)

        const contTrecho = document.createElement('div')
        contTrecho.className = 'cont_trecho'
        const previa = document.createElement('p')
        previa.className = 'trechoDoc'
        previa.textContent = item.conteudo || item.previa || ''
        contTrecho.appendChild(previa)

        contDocumento.appendChild(contStatus)
        contDocumento.appendChild(contTitulo)
        contDocumento.appendChild(contTrecho)

        card.appendChild(contDocumento)
        fragmento.appendChild(card)
    })

    contDocumentos.appendChild(fragmento)
}

function rendProximasRev(documentos) {
    const proximasRevisoes = document.getElementById('cont_proximasRevisoes')
    const fragmento = document.createDocumentFragment()

    const proximas = documentos
        .filter(d => d.statusRevisao !== 'CONCLUIDO' && d.statusRevisao !== 'DESATIVADO')
        .sort((a, b) => (a.dataProximaRevisao || '').localeCompare(b.dataProximaRevisao || ''))
        .slice(0, 10)

    proximas.forEach(e => {
        const revisaoDoc = document.createElement('a')
        revisaoDoc.className = 'link_menuEsquerdo'
        revisaoDoc.href = `../documento/documento.html?id=${e.idDocumento}`
        revisaoDoc.textContent = e.titulo
        fragmento.appendChild(revisaoDoc)
    })

    proximasRevisoes.appendChild(fragmento)
}

function rendCadernos(cadernos) {
    const listaCadernos = document.getElementById('cont_listaCadernos')
    const fragmento = document.createDocumentFragment()

    cadernos.forEach(c => {
        const caderno = document.createElement('a')
        caderno.className = 'link_menuEsquerdo'
        caderno.href = '#'
        caderno.textContent = c.caderno

        caderno.addEventListener('click', (e) => {
            e.preventDefault()
            limparCards()
            rendCards(documentosCache.filter(d => d.caderno && d.caderno.idCaderno === c.idCaderno))
        })

        fragmento.appendChild(caderno)
    })

    listaCadernos.appendChild(fragmento)
}

/*
-----------------------------------------------------------
---------------            Botões          ----------------
-----------------------------------------------------------
*/

async function criarDocumento() {
    const criarDoc = document.getElementById('btn_criar')

    criarDoc.addEventListener('click', async () => {
        if (!cadernosCache.length) {
            alert('Você precisa de um caderno para criar um documento.')
            return
        }

        const url = 'http://localhost:8080/api/documento'
        const novoDoc = {
            titulo: 'Novo documento',
            conteudo: '',
            caderno: { idCaderno: cadernosCache[0].idCaderno }
        }

        try {
            const response = await fetch(url, {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(novoDoc)
            })

            if (!response.ok) {
                throw new Error(`Erro HTTP: ${response.status}`)
            }

            await response.text()
            window.location.reload()
        }
        catch (e) {
            console.log('Erro: ' + e.message)
            alert('Não foi possível criar o documento.')
        }
    })
}

function pesquisar() {
    const inpProcurar = document.getElementById('inp_procurar')
    const btnProcurar = document.getElementById('btn_procurar')

    const aoPesquisar = () => {
        const termo = inpProcurar.value.trim().toLowerCase()
        const filtrados = documentosCache.filter(d =>
            (d.titulo || '').toLowerCase().includes(termo)
        )
        limparCards()
        rendCards(filtrados)
    }

    btnProcurar.addEventListener('click', aoPesquisar)
    inpProcurar.addEventListener('keyup', (e) => {
        if (e.key === 'Enter') {
            aoPesquisar()
        }
    })
}

function ordenar() {
    const btnOrdenar = document.getElementById('btn_ordenar')
    let crescente = true

    btnOrdenar.addEventListener('click', () => {
        const ordenados = [...documentosCache].sort((a, b) => {
            const da = a.dataProximaRevisao || ''
            const db = b.dataProximaRevisao || ''
            return crescente ? da.localeCompare(db) : db.localeCompare(da)
        })

        crescente = !crescente
        limparCards()
        rendCards(ordenados)
    })
}

function filtrarStatus() {
    const btnFiltrar = document.getElementById('btn_filtrar')
    const statuses = ['TODOS', 'ANDAMENTO', 'PENDENTE', 'CONCLUIDO', 'DESATIVADO']
    let indice = 0

    btnFiltrar.addEventListener('click', () => {
        indice = (indice + 1) % statuses.length
        const status = statuses[indice]

        btnFiltrar.textContent = status === 'TODOS' ? 'Filtrar' : `Filtro: ${status}`

        const filtrados = status === 'TODOS'
            ? documentosCache
            : documentosCache.filter(d => d.statusRevisao === status)

        limparCards()
        rendCards(filtrados)
    })
}

function sair() {
    const btnSair = document.getElementById('btn_sair')

    btnSair.addEventListener('click', (e) => {
        e.preventDefault()
        sessionStorage.clear()
        window.location.href = '../index.html'
    })
}

init()
