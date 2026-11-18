function init() {
    cadastrar()
}

function cadastrar() {
    const inpUsuario = document.getElementById('inp_usuario')
    const inpEmail = document.getElementById('inp_email')
    const inpSenha = document.getElementById('inp_senha')
    const inpConfirmarSenha = document.getElementById('inp_confirmarSenha')
    const btnCadastrar = document.getElementById('btn_cadastro')

    btnCadastrar.addEventListener('click', async () => {
        const usuario = inpUsuario.value.trim()
        const email = inpEmail.value.trim()
        const senha = inpSenha.value
        const confirmar = inpConfirmarSenha.value

        // campos obrigatórios
        if (!usuario || !email || !senha || !confirmar) {
            mensagem(2)
            return
        }

        // senhas devem ser iguais
        if (senha !== confirmar) {
            mensagem(1)
            return
        }

        // e-mail já cadastrado?
        const emailExiste = await verificarEmail(email)
        if (emailExiste) {
            mensagem(0)
            return
        }

        const criado = await cadastrarUsuarioBack(usuario, email, senha)

        if (criado) {
            mensagem(3)
            setTimeout(() => {
                window.location.href = '../index.html'
            }, 1200)
        } else {
            mensagem(4)
        }
    })
}

async function verificarEmail(email) {
    const url = 'http://localhost:8080/api/usuario/verificarEmail'

    try {
        const res = await fetch(url, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ email: email })
        })

        if (!res.ok) {
            throw new Error(`Erro HTTP: ${res.status}`)
        }

        // o backend retorna um boolean
        return await res.json()
    }
    catch (e) {
        console.log('Erro: ', e.message)
        return false
    }
}

async function cadastrarUsuarioBack(usuario, email, senha) {
    const url = 'http://localhost:8080/api/usuario'

    try {
        const res = await fetch(url, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ usuario: usuario, email: email, senha: senha })
        })

        if (!res.ok) {
            throw new Error(`Erro HTTP: ${res.status}`)
        }

        // o backend retorna texto puro ("Usuario criado API.")
        await res.text()
        return true
    }
    catch (e) {
        console.log('Erro: ', e.message)
        return false
    }
}

function mensagem(usrEstado) {
    const aviso = document.getElementById('p_aviso')

    const mensagens = {
        0: 'Este e-mail já está cadastrado.',
        1: 'As senhas não conferem.',
        2: 'Preencha todos os campos.',
        3: 'Conta criada com sucesso! Redirecionando...',
        4: 'Não foi possível cadastrar. Tente novamente.'
    }

    aviso.textContent = mensagens[usrEstado] || ''
}

init()
