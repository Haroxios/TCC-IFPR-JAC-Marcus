package com.revisaoWeb.model;

public enum NivelRevisao {

    NIVEL_1(1, 1),
    NIVEL_2(2, 7),
    NIVEL_3(3, 30);

    public static final int NIVEL_MAXIMO = values().length;
    public static final int NIVEL_INICIAL = 1;

    private final int nivel;
    private final int dias;

    NivelRevisao(int nivel, int dias) {
        this.nivel = nivel;
        this.dias = dias;
    }

    public int getNivel() {
        return nivel;
    }

    public int getDias() {
        return dias;
    }

    public static int proximoNivel(int nivelAtual) {
        return Math.min(nivelAtual + 1, NIVEL_MAXIMO);
    }

    public static int nivelInicial() {
        return NIVEL_INICIAL;
    }

    public static int qntProximoDia(int nivel) {

        // clamp
        int nivelLimitado = Math.min(Math.max(nivel, 1), NIVEL_MAXIMO);

        /* for (NivelRevisao constanteAtual : NivelRevisao.values())
                └── tipo ──┘ └── nome ────┘   └ de onde vêm os itens ┘   */
        for (NivelRevisao n: values()){

            if (n.nivel == nivelLimitado) {
                return n.dias;
            }
        }
        throw new IllegalStateException("Nivel Invalido" +nivel);
    }
}
