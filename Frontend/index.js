function init() {
    botao()
}

function botao() {
    const btnLogin = document.getElementById('btn_entrar')

    btnLogin.addEventListener('click', () => {
        window.location.href = './menu/menu.html'
    })
}

init()