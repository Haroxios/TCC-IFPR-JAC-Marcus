function init() {
    logar()
}

function logar() {
    const btnLogin = document.getElementById('btn_entrar')
    const inpEmail = document.getElementById('inp_email')
    const inpSenha = document.getElementById('inp_senha')

    btnLogin.addEventListener('click', async () => {
        const login = await verificarUsuario(inpEmail.value, inpSenha.value)

        if (login.idUsuario != null) {
            sessionStorage.setItem('ursLogado', login.idUsuario)
            window.location.href = './menu/menu.html'
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
        
        const texto = await res.json()
        return texto
    }
    catch (e) {
        console.log('Erro: ', e.message)
    }

}

init()
