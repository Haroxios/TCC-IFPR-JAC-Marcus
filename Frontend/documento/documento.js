async function init() {
//tenho que deixar duas rota aqui uma para quando abrir o documento outra para quando criar ele 

    const params = new URLSearchParams(window.location.search)
    //const id = params.get('id')
    const id = 1
    
    const textoDocumento = await documento(id)
    console.log(textoDocumento)
    montarDocumento(textoDocumento)

    salvar(id)
}

async function documento(id) {
    const url = `http://localhost:8080/api/documento/${id}`

    try {
        const response = await fetch(url)

        if (!response.ok) {
            throw new Error (`Erro HTTP: ${response.status}`)
        }

        const txtDoc = await response.json()
        return txtDoc
    }
    catch (e) {
        console.log('Erro: ', e.message)
    }
}

async function montarDocumento(textoDocumento, documento) {
    const titulo = document.getElementById('h1_tituloDocumento')
    const corpo = document.getElementById('p_textoDocumento')

    titulo.innerHTML = textoDocumento.titulo
    corpo.innerHTML = textoDocumento.conteudo
}


function salvar(id) {
    const salvar = document.getElementById('btn_salvar')

    salvar.addEventListener('click', async () => {
        const titulo = document.getElementById('h1_tituloDocumento').textContent
        const conteudo = document.getElementById('p_textoDocumento').textContent
        const statusRevisao = document.getElementById('slct_estado')

        const url = `http://localhost:8080/api/documento/${id}`
        const docAtual = {conteudo: conteudo,
            //dataProximaRevisao: "2026-10-15T03:00:00.000Z",
            //dataUltimaEdicao: "2026-10-08T03:00:00.000Z",
            idDocumento: 1,
            nivelRevisao: 1,
            statusRevisao: statusRevisao,
            titulo: titulo
        }

        try {
            const response =  await fetch(url, {
                method: 'PUT',
                headers: {'Content-Type': 'application/json'},
                body: JSON.stringify(docAtual)
            })

            if(!response.ok) {
                throw new Error (`Erro HTTP: ${response}`)
            }

            //const doc = await response.json()
            console.log(doc)
        }
        catch (e) {
            console.log('Erro: ' +e.message)
        }
    })
}

init()