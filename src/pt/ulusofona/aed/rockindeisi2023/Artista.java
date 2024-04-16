package pt.ulusofona.aed.rockindeisi2023;

import java.util.ArrayList;

public class Artista {

    String nome;

    ArrayList<Musica> musicas;

    String idMusica;

    ArrayList<String> tags;

    public Artista(String nome, String idMusica) {
        this.nome = nome;
        this.idMusica = idMusica;
        this.musicas = new ArrayList<>();
    }

    public Artista(ArrayList<String> tags) {
        this.tags = new ArrayList<>();
    }

    public Artista() {

    }


    @Override
    public String toString() {

        if (nome.charAt(0) == 'A' || nome.charAt(0) == 'B' || nome.charAt(0) == 'C' || nome.charAt(0) == 'D') {
            return "Artista: [" + nome + "]";
        } else {
            return "Artista: [" + nome + "] | " + musicas.size();
        }

    }

}




