function init() {

    const usrStatus = cadastrar()
    mensagem(usrStatus)

    if(usrStatus == 3) {
        //window.location.href = '../index.html'   }

    }
}

function cadastrar() {
    const inpUsuario = document.getElementById('inp_usuario')
    const inpEmail = document.getElementById('inp_email')
    const inpSenha = document.getElementById('inp_senha')
    const inpConfirmarSenha = document.getElementById('inp_confirmarSenha')
    const btnCadastrar = document.getElementById('btn_cadastro')
    
    btnCadastrar.addEventListener('click', async() => {
        verificarEmail(inpEmail.value)
        
        confirmarSenha(
            inpSenha.value, 
            inpConfirmarSenha.value)
        
        cadastrarUsuarioBack(
            inpUsuario.value,
            inpEmail.value,
            inpSenha.value,
        )

        return usuarioCadastrado
    })
}

async function verificarEmail(email) {
    const url = 'http://localhost:8080/api/usuario/verificarEmail'
    const usr = {
        email: email
    }

    try {
        const res = await fetch(url, {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(usr)
        })

        if (!res.ok) {
            throw new Error(`Erro HTTP: ${res.status}`)
        }

        const texto = await res.text()
        console.log(texto)
        console.log(typeof(texto))
    }
    catch(e) {
        console.log('Erro: ', e.message) 
    }
}

function confirmarSenha(senha, confirmar) {
    if (senha !== confirmar) {
        console.log('senhas distinta')
        throw new Error
            // ^^^^  tratar deste erro aqui
    }
}

async function cadastrarUsuarioBack(usuario, email, senha) {
    const url = 'http://localhost:8080/api/usuario'
    const usr = {
        usuario: usuario,
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
    catch(e) {
        console.log('Erro: ', e.message) 
    }
}

function mensagem(usrEstado) {
    const aviso = document.getElementById('p_aviso')

    // email já existe
    if (usrEstado == 0) {

    }

    //senha não batem
    if (usrEstado == 1) {

    }

    //usr criado
    if (usrEstado == 3) {

    }
}

init()