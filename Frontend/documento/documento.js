let idDocumento = null
let cadernoId = null

async function init() {
    if (!sessionStorage.getItem('usuarioLogado')) {
        window.location.href = '../index.html'
        return
    }

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

    titulo.textContent = documento.titulo || ''
    corpo.textContent = documento.conteudo || ''

    cadernoId = documento.caderno ? documento.caderno.idCaderno : null

    atualizarInformacoes(documento)
}

function formatarData(data) {
    if (!data) {
        return ''
    }

    return data.split('-').reverse().join('/')
}

function atualizarInformacoes(documento) {
    const info = document.getElementById('p_infoRevisao')
    const status = documento.statusRevisao
    const emRevisao = status === 'ANDAMENTO' || status === 'PENDENTE'

    info.textContent = `Estado: ${status} | Nível: ${documento.nivelRevisao} | Próxima revisão: ${formatarData(documento.dataProximaRevisao)}`

    document.getElementById('btn_revisar').hidden = !emRevisao
    document.getElementById('btn_adiar').hidden = !emRevisao
    document.getElementById('btn_concluir').hidden = !emRevisao
    document.getElementById('btn_desativar').hidden = !emRevisao
    document.getElementById('btn_reativar').hidden = emRevisao
}

function funcionalidades() {
    salvar()
    acoesRevisao()
    deletarDocumento()
}

/*
-----------------------------------------------------------
---------------            Salvar          ----------------
-----------------------------------------------------------
*/

function salvar() {
    const btnSalvar = document.getElementById('btn_salvar')

    btnSalvar.addEventListener('click', async () => {
        await gravarDocumento()
    })
}

async function gravarDocumento() {
    const titulo = document.getElementById('h1_tituloDocumento').textContent.trim()
    const conteudo = document.getElementById('p_textoDocumento').innerText

    if (!titulo) {
        alert('O título é obrigatório.')
        return false
    }

    const docAtual = {
        idDocumento: idDocumento,
        titulo: titulo,
        conteudo: conteudo,
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
        return true
    }
    catch (e) {
        console.log('Erro: ' + e.message)
        alert('Não foi possível salvar o documento.')
        return false
    }
}

/*
-----------------------------------------------------------
---------------      Status do documento    ----------------
-----------------------------------------------------------
*/

function acoesRevisao() {
    const acoes = {
        btn_revisar: 'revisar',
        btn_adiar: 'adiar',
        btn_concluir: 'concluir',
        btn_desativar: 'desativar',
        btn_reativar: 'reativar'
    }

    const salvaAntes = ['btn_revisar', 'btn_adiar', 'btn_concluir']

    Object.keys(acoes).forEach(idBotao => {
        const botao = document.getElementById(idBotao)

        botao.addEventListener('click', async () => {
            if (salvaAntes.includes(idBotao)) {
                const salvo = await gravarDocumento()

                if (!salvo) {
                    return
                }
            }

            const ok = await chamarStatus(acoes[idBotao])

            if (!ok) {
                alert('Não foi possível realizar a ação.')
                return
            }

            const documento = await buscarDocumento(idDocumento)
            if (documento) {
                atualizarInformacoes(documento)
            }
        })
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

init()
