package pt.ulusofona.aed.rockindeisi2023;

public class Detalhes {

    String idMusica;

    String duracao;

    String letraExplicita;

    String polularidade;

    Double dancabilidade;

    String vivacidade;

    String volumeMedio;


    public Detalhes(String idMusica, String duracao, String letraExplicita, String polularidade, Double dancabilidade,
                    String vivacidade, String volumeMedio) {

        this.idMusica = idMusica;
        this.duracao = duracao;
        this.letraExplicita = letraExplicita;
        this.polularidade = polularidade;
        this.dancabilidade = dancabilidade;
        this.vivacidade = vivacidade;
        this.volumeMedio = volumeMedio;

    }

    public Detalhes() {

    }


    @Override
    public String toString() {
        return "Detalhes{" +
                "idMusica='" + idMusica +
                ", duracao=" + duracao +
                ", letraExplicita='" + letraExplicita +
                ", polularidade=" + polularidade +
                ", dancabilidade=" + dancabilidade +
                ", vivacidade=" + vivacidade +
                ", volumeMedio=" + volumeMedio +
                '}';
    }
}