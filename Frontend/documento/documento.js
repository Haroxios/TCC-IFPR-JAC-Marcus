let idDocumento = null
let cadernoId = null
let statusAtual = null

async function init() {
    const params = new URLSearchParams(window.location.search)
    const id = params.get('id')

    if (!id) {
        window.location.href = '../menu/menu.html'
        return
    }

    idDocumento = Number(id)
    const documento = await buscarDocumento(idDocumento)

    if (!documento) {
        window.location.href = '../menu/menu.html'
        return
    }

    montarDocumento(documento)
    funcionalidades()
}

async function buscarDocumento(id) {
    const url = `http://localhost:8080/api/documento/${id}`

    try {
        const response = await fetch(url)

        if (!response.ok) {
            throw new Error(`Erro HTTP: ${response.status}`)
        }

        return await response.json()
    }
    catch (e) {
        console.log('Erro: ', e.message)
        return null
    }
}

function montarDocumento(documento) {
    const titulo = document.getElementById('h1_tituloDocumento')
    const corpo = document.getElementById('p_textoDocumento')
    const slctEstado = document.getElementById('slct_estado')

    titulo.textContent = documento.titulo || ''
    corpo.textContent = documento.conteudo || ''

    cadernoId = documento.caderno ? documento.caderno.idCaderno : null
    statusAtual = documento.statusRevisao || null

    if (statusAtual) {
        slctEstado.value = statusAtual
    }

    renderizarTags(idDocumento)
}

function funcionalidades() {
    salvar()
    alterarStatus()
    finalizarRevisao()
    deletarDocumento()
    compartilhar()
    adicionarTag()
}

/*
-----------------------------------------------------------
---------------            Salvar          ----------------
-----------------------------------------------------------
*/

function salvar() {
    const btnSalvar = document.getElementById('btn_salvar')

    btnSalvar.addEventListener('click', async () => {
        const titulo = document.getElementById('h1_tituloDocumento').textContent
        const conteudo = document.getElementById('p_textoDocumento').innerText
        const statusRevisao = document.getElementById('slct_estado').value

        const docAtual = {
            idDocumento: idDocumento,
            titulo: titulo,
            conteudo: conteudo,
            statusRevisao: statusRevisao,
            caderno: { idCaderno: cadernoId }
        }

        try {
            const response = await fetch(`http://localhost:8080/api/documento/${idDocumento}`, {
                method: 'PUT',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(docAtual)
            })

            if (!response.ok) {
                throw new Error(`Erro HTTP: ${response.status}`)
            }

            await response.text()
            console.log('Documento salvo.')
        }
        catch (e) {
            console.log('Erro: ' + e.message)
        }
    })
}

/*
-----------------------------------------------------------
---------------      Status do documento    ----------------
-----------------------------------------------------------
*/

function alterarStatus() {
    const slctEstado = document.getElementById('slct_estado')

    slctEstado.addEventListener('change', async () => {
        const novoStatus = slctEstado.value

        if (novoStatus === statusAtual) {
            return
        }

        const rotas = {
            CONCLUIDO: 'concluir',
            ANDAMENTO: 'reativar',
            DESATIVADO: 'desativar'
        }

        const rota = rotas[novoStatus]

        if (!rota) {
            slctEstado.value = statusAtual || 'ANDAMENTO'
            return
        }

        const ok = await chamarStatus(rota)
        if (ok) {
            statusAtual = novoStatus
        } else {
            slctEstado.value = statusAtual || 'ANDAMENTO'
        }
    })
}

async function chamarStatus(rota) {
    try {
        const res = await fetch(`http://localhost:8080/api/documento/${rota}/${idDocumento}`, {
            method: 'PUT'
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

function finalizarRevisao() {
    const btnFinalizar = document.getElementById('btn_revisaoFinalizado')

    btnFinalizar.addEventListener('click', async () => {
        const ok = await chamarStatus('concluir')

        if (ok) {
            statusAtual = 'CONCLUIDO'
            document.getElementById('slct_estado').value = 'CONCLUIDO'
        }
    })
}

function deletarDocumento() {
    const btnDeletar = document.getElementById('btn_deletar')

    btnDeletar.addEventListener('click', async () => {
        if (!confirm('Tem certeza que deseja excluir este documento?')) {
            return
        }

        try {
            const res = await fetch(`http://localhost:8080/api/documento/${idDocumento}`, {
                method: 'DELETE'
            })

            if (!res.ok) {
                throw new Error(`Erro HTTP: ${res.status}`)
            }

            await res.text()
            window.location.href = '../menu/menu.html'
        }
        catch (e) {
            console.log('Erro: ', e.message)
        }
    })
}

/*
-----------------------------------------------------------
---------------         Compartilhar      ----------------
-----------------------------------------------------------
*/

function compartilhar() {
    const dialog = document.getElementById('dial_compartilhamento')
    const btnCompartilhar = document.getElementById('btn_compartilhar')
    const btnCancelar = document.getElementById('dial_btnCancelar')
    const btnConfirmar = document.getElementById('dial_btnCompartilhar')

    btnCompartilhar.addEventListener('click', () => dialog.showModal())
    btnCancelar.addEventListener('click', () => dialog.close())

    btnConfirmar.addEventListener('click', () => {
        // ainda não existe endpoint de compartilhamento no backend
        dialog.close()
    })
}

/*
-----------------------------------------------------------
---------------             Tags          ----------------
-----------------------------------------------------------
*/

async function buscarTags() {
    try {
        const res = await fetch('http://localhost:8080/api/tag')

        if (!res.ok) {
            throw new Error(`Erro HTTP: ${res.status}`)
        }

        return await res.json()
    }
    catch (e) {
        console.log('Erro: ', e.message)
        return []
    }
}

async function buscarTagDocs() {
    try {
        const res = await fetch('http://localhost:8080/api/tagDoc')

        if (!res.ok) {
            throw new Error(`Erro HTTP: ${res.status}`)
        }

        return await res.json()
    }
    catch (e) {
        console.log('Erro: ', e.message)
        return []
    }
}

async function renderizarTags() {
    const [tags, tagDocs] = await Promise.all([buscarTags(), buscarTagDocs()])
    const contTags = document.getElementById('cont_tags')
    contTags.innerHTML = ''

    const nomes = tagDocs
        .filter(td => td.documento && td.documento.idDocumento === idDocumento)
        .map(td => {
            const tag = tags.find(t => t.idTag === (td.tag ? td.tag.idTag : null))
            return tag ? tag.tag : null
        })
        .filter(nome => nome)

    if (nomes.length === 0) {
        const vazio = document.createElement('p')
        vazio.className = 'tag_vazia'
        vazio.textContent = 'Nenhuma tag'
        contTags.appendChild(vazio)
        return
    }

    nomes.forEach(nome => {
        const tag = document.createElement('span')
        tag.className = 'tag'
        tag.textContent = `# ${nome}`
        contTags.appendChild(tag)
    })
}

function adicionarTag() {
    const btnAdicionarTag = document.getElementById('btn_adicionarTag')

    btnAdicionarTag.addEventListener('click', async () => {
        const nome = prompt('Nome da nova tag:')
        if (!nome || !nome.trim()) {
            return
        }

        try {
            const resTag = await fetch('http://localhost:8080/api/tag', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ tag: nome.trim() })
            })

            if (!resTag.ok) {
                throw new Error(`Erro HTTP: ${resTag.status}`)
            }

            await resTag.text()

            const tags = await buscarTags()
            const tagCriada = tags.find(t => t.tag.toLowerCase() === nome.trim().toLowerCase())
            if (!tagCriada) {
                return
            }

            const resTagDoc = await fetch('http://localhost:8080/api/tagDoc', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({
                    documento: { idDocumento: idDocumento },
                    tag: { idTag: tagCriada.idTag }
                })
            })

            if (!resTagDoc.ok) {
                throw new Error(`Erro HTTP: ${resTagDoc.status}`)
            }

            await resTagDoc.text()
            renderizarTags()
        }
        catch (e) {
            console.log('Erro: ', e.message)
        }
    })
}

init()
