function init() {
    logar()
}

function logar() {
    const btnLogin = document.getElementById('btn_entrar')
    const inpEmail = document.getElementById('inp_email')
    const inpSenha = document.getElementById('inp_senha')

    inpSenha.addEventListener('keydown', (e) => {
        if (e.key === 'Enter') {
            btnLogin.click()
        }
    })

    btnLogin.addEventListener('click', async () => {
        const email = inpEmail.value.trim()
        const senha = inpSenha.value

        if (!email || !senha) {
            mostrarAviso('Preencha todos os campos.')
            return
        }

        const login = await verificarUsuario(email, senha)

        if (login && login.idUsuario != null) {
            sessionStorage.setItem('usuarioLogado', login.idUsuario)
            window.location.href = './menu/menu.html'
        } else {
            mostrarAviso('Usuário ou senha inválidos.')
        }
    })
}

async function verificarUsuario(email, senha) {
    const url = 'http://localhost:8080/api/usuario/login'

    try {
        const res = await fetch(url, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ email: email, senha: senha })
        })

        if (!res.ok) {
            return null
        }

        return await res.json()
    }
    catch (e) {
        console.log('Erro: ', e.message)
        return null
    }
}

function mostrarAviso(texto) {
    const aviso = document.getElementById('p_aviso')
    aviso.textContent = texto
}

init()
