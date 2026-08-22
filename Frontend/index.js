/*

function testarConexao() {

    const id = 25

    const usr = {
    "email": "FrontAtualizar@email.com",
    "nome": "teste",
    "senha": "teste"
}

    //listarUsuario()
    //listarIdUsuario(id)
    
    //criarUsuario(usr)
    
    //atualizarUsuario(id, usr)
    //deletarUsuario(id)
    
}


async function deletarUsuario(id) {
    const url = `http://localhost:8080/api/usuario/${id}`

    try { 
        const res = await fetch(url, {
            method: 'DELETE',
            headers: {'Content-Type': 'application/json'},
        })

        if (!res.ok) {
            throw new Error(`Erro HTTP: ${res.status}`)
        }

        const texto = await res.text()
        console.log(texto)
    }
    catch (e) {
        console.error('Falha: ', e)
    }

}


async function atualizarUsuario(id, usr) {
    const url = `http://localhost:8080/api/usuario/${id}`

    try {
        const res = await fetch (url, {
            method: 'PUT',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(usr)
        })

        if (!res.ok) {
            throw new Error (`Erro HTTP: ${res.status}`)
        }

        const texto = await res.text()
        console.log(texto)
    }
    catch (erro) {
        console.error('Falha: ', erro)
    }
}

async function criarUsuario(usr) {
    const url = 'http://localhost:8080/api/usuario'

    try {
        const response = await fetch(url, {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(usr)
        }) 

       if (!response.ok) {
            throw new Error (`Erro HTTP: ${response.status}`)
        }

        const texto =  await response.text()
        console.log(texto)

    }
    catch (erro) {
        console.error('Falha: ', erro)
    }
}

async function listarIdUsuario(id) {
    const url = `http://localhost:8080/api/usuario/${id}`

    try {
        const response = await fetch(url)
        if (!response.ok) {
            throw new Error (`Erro HTTP: ${response.status}`)
        }

        const dados = await response.json()
        console.log('Deu certo!', dados)
    }
    catch (erro) {
        console.error('Falha: ', erro)
    }
}

async function listarUsuario() {
    const url = 'http://localhost:8080/api/usuario'
    
    try {
        const response = await fetch(url)
        
        if (!response.ok) {
            throw new Error(`Erro HTTP: ${response.status}`)
        }
        
        const dados = await response.json()
        console.log('Deu certo!', dados)
    }
    catch (erro) {
        console.error('Falha: ', erro)
    }
}
testarConexao()

*/



function init() {
    logar()
}

function logar() {
    const btnLogin = document.getElementById('btn_entrar')
    const inpEmail = document.getElementById('inp_email')
    const inpSenha = document.getElementById('inp_senha')

    btnLogin.addEventListener('click', async () => {
        const login = await verificarUsuario(inpEmail.value, inpSenha.value)

        if (login.id != null) {
            window.location.href = `./menu/menu.html?id=${login.id}`
        }
    })
}

async function verificarUsuario(email, senha) {
    const url = 'http://localhost:8080/api/usuario/login'
    const usr = {
        email: email,
        senha: senha
    }

    try {
        const res = await fetch(url, {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(usr),
        })

        if (!res.ok) {
            throw new Error(`Erro HTTP: ${res.status}`)
        }

        const texto = JSON.parse(await res.text())
        return texto
    }
    catch (e) {
        console.log('Erro: ', e.message)
    }

}

init()
