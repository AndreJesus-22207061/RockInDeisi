package pt.ulusofona.aed.rockindeisi2023;

import java.util.ArrayList;

public class Musica {

    String idMusica;

    String nomeMusica;

    String anoDeLancamento;

    Detalhes detalhes;

    // Artista artista;
    ArrayList<Artista> artistas;


    // int numArtistas;

    public Musica(String idMusica, String nomeMusica, String anoDeLancamento, Detalhes detalhes, ArrayList<Artista> artistas) {
        this.idMusica = idMusica;
        this.nomeMusica = nomeMusica;
        this.anoDeLancamento = anoDeLancamento;
        this.detalhes = detalhes;
        this.artistas = artistas;
    }

    @Override
    public String toString() {

        int anoDeLancamentoInt = Integer.parseInt(anoDeLancamento);

        if (anoDeLancamentoInt < 1995) {
            return idMusica + " | " + nomeMusica + " | " + anoDeLancamento;

        } else if (anoDeLancamentoInt < 2000) {
            String duracaoEmString = detalhes.duracao;
            int duracao = Integer.parseInt(duracaoEmString);
            int minutos = (duracao / 1000) / 60;
            int segundos = (duracao / 1000) % 60;
            String minutosEmString = String.format("%01d", minutos);
            String segundosEmString = String.format("%02d", segundos);


            return idMusica + " | " + nomeMusica + " | " + anoDeLancamento + " | " + minutosEmString + ":" + segundosEmString + " | " + detalhes.polularidade;
        } else {
            String duracaoEmString = detalhes.duracao;
            int duracao = Integer.parseInt(duracaoEmString);
            int minutos = (duracao / 1000) / 60;
            int segundos = (duracao / 1000) % 60;

            String minutosEmString = String.format("%01d", minutos);
            String segundosEmString = String.format("%02d", segundos);


            return idMusica + " | " + nomeMusica + " | " + anoDeLancamento + " | " + minutosEmString + ":" + segundosEmString + " | " + detalhes.polularidade + " | " + artistas.size();
        }


    }


}