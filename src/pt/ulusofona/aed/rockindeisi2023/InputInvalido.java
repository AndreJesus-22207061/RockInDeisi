package pt.ulusofona.aed.rockindeisi2023;

public class InputInvalido {
    String nomeFicheiro;
    int linhasOK;

    int linhasNOK;

    int primeiraLinhaNOK;

    public InputInvalido(String nomeFicheiro, int linhasOK, int linhasNOK, int primeiraLinhaNOK) {
        this.nomeFicheiro = nomeFicheiro;
        this.linhasOK = linhasOK;
        this.linhasNOK = linhasNOK;
        this.primeiraLinhaNOK = primeiraLinhaNOK;
    }

    public InputInvalido(String nomeFicheiro) {
        this.nomeFicheiro = nomeFicheiro;
    }

    public InputInvalido() {

    }


    @Override
    public String toString() {
        return nomeFicheiro + " | " + linhasOK + " | " + linhasNOK + " | " + primeiraLinhaNOK;
    }
}