package pt.ulusofona.aed.rockindeisi2023;

import java.util.*;

public class FuncaoQuery {


    static String count_Songs_Year(Query command) {
        String ano = command.args[0];
        int numMusicas = 0;

        for (Musica musica : Main.listaDeMusica) {
            if (Objects.equals(musica.anoDeLancamento, ano)) {
                numMusicas++;
            }
        }
        return Integer.toString(numMusicas);
    }

    static String count_Duplicate_Songs_Year(Query command) { // conta musicas com ids difrentes mas nomes iguais
        String ano = command.args[0];
        ArrayList<Musica> listaMusicasAno = new ArrayList<>(); //lista de musicas só do ano escolhido
        ArrayList<String> listaMusicasAnoSemDuplicados = new ArrayList<>(); // lista de musicas só do ano mas com um nome de cada
        int numMusicasRepetidas = 0;                                        // criada para utilizar no duplo for
        for (Musica musica : Main.listaDeMusica) {
            if (Objects.equals(musica.anoDeLancamento, ano)) {
                listaMusicasAno.add(musica);
            }
        }

        for (Musica musica : listaMusicasAno) {
            if (!(listaMusicasAnoSemDuplicados.contains(musica.nomeMusica))) {
                listaMusicasAnoSemDuplicados.add(musica.nomeMusica);
            }
        }


        for (String musicaAComparar : listaMusicasAnoSemDuplicados) {  //Para cada nome vamos ver quantas musicas tem esse nome
            int numMusicas = 0;
            for (Musica musica : listaMusicasAno) {
                if (Objects.equals(musica.nomeMusica, musicaAComparar)) {
                    numMusicas++;
                }
            }
            if (numMusicas >= 2) {
                numMusicasRepetidas++;   // se esse nome aparecer mais do que uma vez quer dizer que é duplicada
            }
        }
        return Integer.toString(numMusicasRepetidas);
    }


    static String get_Songs_By_Artist(Query command) {     // retorna o numeroXdeMusicas de certo artista
        int numeroMusicas = Integer.parseInt(command.args[0]); //X
        String nomeArtista = command.args[1]; //nome artista

        StringBuilder songsByArtist = new StringBuilder();
        int i;
        int j = 0;


        if (!Main.mapaDeNomesArtista.containsKey(nomeArtista)) { //verifica se tem musicas
            return "No songs";
        } else if (Main.mapaDeNomesArtista.containsKey(nomeArtista) && Main.mapaDeNomesArtista.get(nomeArtista).musicas == null) { // se existe mas nao tem musicas
            return "No songs";
        } else {
            if (Main.mapaDeNomesArtista.get(nomeArtista).musicas.size() < numeroMusicas) { // se o artista tiver menos musicas que as passadas por paramentro
                numeroMusicas = Main.mapaDeNomesArtista.get(nomeArtista).musicas.size();  // o comando só vai retornar o numero de musicas que tem
            }
            for (i = 0; i < numeroMusicas; i++) {
                if (i == numeroMusicas - 1) {
                    songsByArtist.append(Main.mapaDeNomesArtista.get(nomeArtista).musicas.get(j).nomeMusica + " : "
                            + Main.mapaDeNomesArtista.get(nomeArtista).musicas.get(j).anoDeLancamento);
                } else {
                    songsByArtist.append(Main.mapaDeNomesArtista.get(nomeArtista).musicas.get(j).nomeMusica + " : "
                            + Main.mapaDeNomesArtista.get(nomeArtista).musicas.get(j).anoDeLancamento + "\n");
                }
                j++; // contador para ir percorrendo o arraydeMusicas do artista
            }
            return songsByArtist.toString();
        }
    }


    static String get_MostDanceable_Music(Query command) { //Retorna uma lista de x musicas, mais dancaveis entre os anos passadps como paramentro
        int anoIncio = Integer.parseInt(command.args[0]);
        int anoFim = Integer.parseInt(command.args[1]);
        int numeroMusicas = Integer.parseInt(command.args[2]);
        int i, j;
        int h = 0;
        StringBuilder stringResultado = new StringBuilder();

        HashSet<String> conjuntoAnos = new HashSet<>();

        ArrayList<Musica> musicasEntreAnos = new ArrayList<>();

        for (i = anoIncio; i <= anoFim; i++) {  //colocar num conjunto todos is anos entre os passados
            String ano = Integer.toString(i);
            conjuntoAnos.add(ano);
        }

        for (Musica musica : Main.listaDeMusica) {
            if (conjuntoAnos.contains(musica.anoDeLancamento)) { // ver se a musica corresponde com algum ano do conjunto
                musicasEntreAnos.add(musica);
            }
        }

        Collections.sort(musicasEntreAnos, Comparator.comparingDouble((Musica musica) -> musica.detalhes.dancabilidade).reversed());

        if (musicasEntreAnos.size() < numeroMusicas) {  //se o numero de musicas for menor que o passado por paramentro
            numeroMusicas = musicasEntreAnos.size();   // vai só retornar o numero de musicas que existirem
        }

        for (j = 0; j < numeroMusicas; j++) {
            if (j == numeroMusicas - 1) {
                stringResultado.append(musicasEntreAnos.get(h).nomeMusica + " : "
                        + musicasEntreAnos.get(h).anoDeLancamento + " : " + musicasEntreAnos.get(h).detalhes.dancabilidade);
            } else {
                stringResultado.append(musicasEntreAnos.get(h).nomeMusica + " : "
                        + musicasEntreAnos.get(h).anoDeLancamento + " : " + musicasEntreAnos.get(h).detalhes.dancabilidade + "\n");
            }
            h++;
        }
        return stringResultado.toString();
    }


    static String addTags(Query command) {

        if (command.args.length == 0) {
            return null;
        }

        String[] partes = command.args;
        String nomeArtista = partes[0];
        ArrayList<String> arrayDeTags = new ArrayList<>();
        int posicao, j;
        int h = 0;

        StringBuilder stringResultado = new StringBuilder();

        if (!Main.mapaDeNomesArtista.containsKey(nomeArtista)) { //se o artista não existir
            return "Inexistent artist";
        } else {

            for (posicao = 1; posicao <= partes.length - 1; posicao++) {  //Não colocar tags repetidas dentro do array
                if (!arrayDeTags.contains(partes[posicao])) {             //Exp: andre;punh;bonito;punh
                    arrayDeTags.add(partes[posicao].toUpperCase());       // so coloca punh,bonito
                }
            }


            //  arrayDeTags;
            for (String tag : arrayDeTags) {
                if (Main.mapaDeNomesArtista.get(nomeArtista).tags == null) {  //se o artista nao tiver tags ainda
                    Main.mapaDeNomesArtista.get(nomeArtista).tags = new ArrayList<>(); //cria o arrayde tags
                    Main.mapaDeNomesArtista.get(nomeArtista).tags.add(tag);


                    if (Main.mapaDeTags.get(tag) == null) {      //Exatamento o mesmo de cima mas para o mapo de tags
                        ArrayList arraydeNomes = new ArrayList<>();
                        arraydeNomes.add(nomeArtista);
                        Main.mapaDeTags.put(tag, arraydeNomes);  //lista tem a tag e todos os artistas com a tag
                    } else {
                        Main.mapaDeTags.get(tag).add(nomeArtista);
                    }

                } else { //se já tiver tags não cria de novo é so adicionar
                    if (!(Main.mapaDeNomesArtista.get(nomeArtista).tags.contains(tag))) {
                        Main.mapaDeNomesArtista.get(nomeArtista).tags.add(tag);

                        ArrayList arraydeNomes = new ArrayList<>();
                        arraydeNomes.add(nomeArtista);
                        Main.mapaDeTags.put(tag, arraydeNomes);
                    }
                }
            }


            for (j = 0; j < Main.mapaDeNomesArtista.get(nomeArtista).tags.size(); j++) {

                if (j == 0) {
                    stringResultado.append(nomeArtista + " | " + Main.mapaDeNomesArtista.get(nomeArtista).tags.get(h));
                } else {
                    stringResultado.append("," + Main.mapaDeNomesArtista.get(nomeArtista).tags.get(h));
                }
                h++;
            }

            return stringResultado.toString();
        }

    }


    static String removeTags(Query command) {

        if (command.args.length == 0) {
            return null;
        }

        String[] partes = command.args;
        String nomeArtista = partes[0];
        ArrayList<String> arrayDeTags = new ArrayList<>();
        int posicao, j;
        int h = 0;

        StringBuilder stringResultado = new StringBuilder();

        if (!Main.mapaDeNomesArtista.containsKey(nomeArtista)) { //se o artista existe
            return "Inexistent artist";
        } else {

            for (posicao = 1; posicao <= partes.length - 1; posicao++) {   //Não colocar tags repetidas dentro do array
                if (!arrayDeTags.contains(partes[posicao])) {              //Exp: andre;punh;bonito;punh
                    arrayDeTags.add(partes[posicao].toUpperCase());        // so coloca punh,bonito
                }
            }
            //  arrayDeTags;
            for (String tag : arrayDeTags) {
                if (Main.mapaDeNomesArtista.get(nomeArtista).tags.contains(tag)) {
                    Main.mapaDeNomesArtista.get(nomeArtista).tags.remove(tag);

                    Main.mapaDeTags.get(tag).remove(nomeArtista);
                    if (Main.mapaDeTags.get(tag).size() == 0) {
                        Main.mapaDeTags.remove(tag);
                    }


                }
            }

            if (Main.mapaDeNomesArtista.get(nomeArtista).tags.size() == 0) {   //se o artista ficar sem tags retorna assim
                return nomeArtista + " | No tags";
            }


            for (j = 0; j < Main.mapaDeNomesArtista.get(nomeArtista).tags.size(); j++) {

                if (j == 0) {
                    stringResultado.append(nomeArtista + " | " + Main.mapaDeNomesArtista.get(nomeArtista).tags.get(h));
                } else {
                    stringResultado.append("," + Main.mapaDeNomesArtista.get(nomeArtista).tags.get(h));
                }
                h++;
            }

            return stringResultado.toString();
        }

    }

    static String get_ArtistsFor_Tag(Query command) {   //retorna uma string com os artistas que tem esta tag
        String tag = command.args[0].toUpperCase();
        if (Main.mapaDeTags.get(tag) == null || !(Main.mapaDeTags.containsKey(tag))) { //se nao existir tag ou nao tiver ninguem associado
            return "No results";
        }

        int j;
        ArrayList<String> arrayDeNomes = Main.mapaDeTags.get(tag);
        StringBuilder stringResultado = new StringBuilder();

        Collections.sort(arrayDeNomes);


        for (j = 0; j < arrayDeNomes.size(); j++) {

            if (j == arrayDeNomes.size() - 1) {
                stringResultado.append(arrayDeNomes.get(j));
            } else {
                stringResultado.append(arrayDeNomes.get(j) + ";");
            }
        }
        return stringResultado.toString();
    }


    static String get_Unique_Tag(Query command) {
        if (Main.mapaDeTags == null) {
            return "No results";
        }

        int j;

        ArrayList<Tag> listaDeTags = new ArrayList<>();

        for (Map.Entry<String, ArrayList<String>> entry : Main.mapaDeTags.entrySet()) {
            String key = entry.getKey();
            ArrayList<String> value = entry.getValue();
            Tag tag = new Tag(key, value);
            listaDeTags.add(tag);
        }


        StringBuilder stringResultado = new StringBuilder();

        Collections.sort(listaDeTags, Comparator.comparingInt((Tag tag) -> tag.arrayDeNomes.size()));


        for (j = 0; j < listaDeTags.size(); j++) {

            if (j == listaDeTags.size() - 1) {
                stringResultado.append(listaDeTags.get(j).tag + " " + listaDeTags.get(j).arrayDeNomes.size());
            } else {
                stringResultado.append(listaDeTags.get(j).tag + " " + listaDeTags.get(j).arrayDeNomes.size() + "\n");
            }
        }
        return stringResultado.toString();
    }

    static String get_Artist_OneSong(Query command) {
        int anoIncio = Integer.parseInt(command.args[0]);
        int anoFim = Integer.parseInt(command.args[1]);
        int i, j;
        int h = 0;

        if (anoIncio >= anoFim) {
            return "Invalid period";
        }
        StringBuilder stringResultado = new StringBuilder();

        HashSet<String> conjuntoAnos = new HashSet<>();

        ArrayList<Artista> listaArtistasComUmaMusica = new ArrayList<>();

        for (i = anoIncio; i <= anoFim; i++) {
            String ano = Integer.toString(i);
            conjuntoAnos.add(ano);
        }

        for (Artista artista : Main.listaDeArtistas) {
            int numMusicasDessesAno = 0;
            Artista artistaUmaMusica = new Artista();
            for (Musica musica : artista.musicas) {
                if (conjuntoAnos.contains(musica.anoDeLancamento)) {
                    numMusicasDessesAno++;
                    if (numMusicasDessesAno == 1) {
                        artistaUmaMusica.nome = artista.nome;
                        ArrayList<Musica> musicas = new ArrayList();
                        musicas.add(musica);
                        artistaUmaMusica.musicas = musicas;
                    }
                }
            }
            if (numMusicasDessesAno == 1) {
                listaArtistasComUmaMusica.add(artistaUmaMusica);
            }
        }

        Collections.sort(listaArtistasComUmaMusica, Comparator.comparing((Artista artista) -> artista.nome));


        for (j = 0; j < listaArtistasComUmaMusica.size(); j++) {
            if (j == listaArtistasComUmaMusica.size() - 1) {
                stringResultado.append(listaArtistasComUmaMusica.get(j).nome + " | "
                        + listaArtistasComUmaMusica.get(j).musicas.get(0).nomeMusica + " | " + listaArtistasComUmaMusica.get(j).musicas.get(0).anoDeLancamento);
            } else {
                stringResultado.append(listaArtistasComUmaMusica.get(j).nome + " | "
                        + listaArtistasComUmaMusica.get(j).musicas.get(0).nomeMusica + " | " + listaArtistasComUmaMusica.get(j).musicas.get(0).anoDeLancamento + "\n");
            }
        }
        return stringResultado.toString();
    }


    static String get_Top3_Most_Popular_Songs_By_Year(Query command) {
        int ano = Integer.parseInt(command.args[0]);
        int top3 = 3;
        int i = 0;
        int j;
        StringBuilder stringResultado = new StringBuilder();

        ArrayList<Musica> conjuntoMusicas = new ArrayList<>();

        for (Musica musica : Main.listaDeMusica) {
            if (Integer.parseInt(musica.anoDeLancamento) == ano) {
                conjuntoMusicas.add(musica);
            }
        }

        Collections.sort(conjuntoMusicas, Comparator.comparingInt((Musica musica) -> Integer.parseInt(musica.detalhes.polularidade)).reversed());

        if (conjuntoMusicas.size() == 0) {
            return "Não houveram músicas no ano" + ano;
        }

        if (conjuntoMusicas.size() < top3) {
            top3 = conjuntoMusicas.size();
        }


        for (j = 0; j < top3; j++) {
            if (j == 0) {
                stringResultado.append("TOP1" + ": " + conjuntoMusicas.get(i).nomeMusica + " - " + conjuntoMusicas.get(i).detalhes.polularidade + "\n");
            } else if (j == 1) {
                stringResultado.append("TOP2" + ": " + conjuntoMusicas.get(i).nomeMusica + " - " + conjuntoMusicas.get(i).detalhes.polularidade + "\n");
            } else {
                stringResultado.append("TOP3" + ": " + conjuntoMusicas.get(i).nomeMusica + " - " + conjuntoMusicas.get(i).detalhes.polularidade);
            }
            i++;
        }
        return stringResultado.toString();
    }


}









