function init() {
    const aviso = document.getElementById('p_aviso')
    aviso.innerHTML = ''

    cadastrar()
}

function cadastrar() {
    const inpUsuario = document.getElementById('inp_usuario')
    const inpEmail = document.getElementById('inp_email')
    const inpSenha = document.getElementById('inp_senha')
    const inpConfirmarSenha = document.getElementById('inp_confirmarSenha')
    const btnCadastrar = document.getElementById('btn_cadastro')

    
    btnCadastrar.addEventListener('click', async() => {
        /*verificarEmail(inpEmail.value)
        
        confirmarSenha(
            inpSenha.value, 
            inpConfirmarSenha.value)
        */
        cadastrarUsuarioBack(
            inpUsuario.value,
            inpEmail.value,
            inpSenha.value,
        )

        console.log('Se der tudo certo foi redirecionado')
        //window.location.href = '../index.html'
    })

}

function confirmarSenha(senha, confirmar) {
    if (senha !== confirmar) {
        console.log('senhas distinta')
        throw new Error
            // ^^^^  tratar deste erro aqui
    }
}

async function verificarEmail(email) {

    const url = 'http://localhost:8080/api/usuario/verificarEmail'
    const usr = {email: email}

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
    }
    catch(e) {
        console.log('Erro: ', e.message) 
    }
}

async function cadastrarUsuarioBack(usuario, email, senha) {

    const url = 'http://localhost:8080/api/usuario'
    const usr = {
        nome: usuario,
        email: email,
        senha: senha
    }

    console.log(usr)

    try {
        const res = await fetch(url, {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(usr),
        })

        if (!res.ok) {
            throw new Error(`Erro HTTP: ${res.status}`)
        }

        const texto = await res.text()
    }
    catch(e) {
        console.log('Erro: ', e.message) 
    }


}

init()