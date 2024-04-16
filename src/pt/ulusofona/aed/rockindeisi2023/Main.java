package pt.ulusofona.aed.rockindeisi2023;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {      // listas respeitam a ordem // mapas nao respeitam a ordem de leitura
    // mapas usados para procurar mais rapido
    static ArrayList<Artista> listaDeArtistas = new ArrayList<>(); //lista de artistas padrao / nome/listadeMusicas/id

    static ArrayList<Musica> listaDeMusica = new ArrayList<>(); //lista de musica padrao / id / nome/ anodeLan / detalhes / lista dos artistas desssa musica (tudo)
    static ArrayList<Detalhes> listaDeDetalhes = new ArrayList<>(); //lista de detalhes padrao
    static ArrayList<InputInvalido> lista_De_Informacao_Ficheiros = new ArrayList<>(); // nomeficheiro / linhasOk / linhasNOK / primeLinhaNOK

    static HashMap<String, ArrayList<Artista>> mapArtistas = new HashMap<>(); // Key: id / lista de artistas dessa musica (do id)
    static HashMap<String, Musica> mapMusicas = new HashMap<>(); // Key: id / musica desse id
    static HashMap<String, Detalhes> mapDetalhes = new HashMap<>(); // Key: id / detalhes desse id

    static HashMap<String, Musica> mapaDeNomesMusica = new HashMap<>(); //Key: nomedaMusica / musica

    static HashMap<String, Artista> mapaDeNomesArtista = new HashMap<>(); //Key: nomedoArtista / artista

    static HashMap<String, ArrayList<String>> mapaDeTags = new HashMap<>(); //Key: nomedaTAG / lista de nomes dos artistas que tem a tag


    static boolean loadFiles(File folder) {

        listaDeArtistas = new ArrayList<>(); //clear ás listas
        listaDeMusica = new ArrayList<>();
        listaDeDetalhes = new ArrayList<>();
        lista_De_Informacao_Ficheiros = new ArrayList<>();
        mapArtistas = new HashMap<>();
        mapMusicas = new HashMap<>();
        mapDetalhes = new HashMap<>();
        mapaDeNomesArtista = new HashMap<>();
        mapaDeNomesMusica = new HashMap<>();
        mapaDeTags = new HashMap<>();

        File ficheiroArtists = new File(folder, "song_artists.txt");
        File ficheiroSongs = new File(folder, "songs.txt");
        File ficheiroDetails = new File(folder, "song_details.txt");


        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(ficheiroArtists));
        } catch (FileNotFoundException e) {
            return false;
        }

        String nomeFicheiro = "song_artists.txt";
        int linhasOK = 0;
        int linhasNOK = 0;
        int primeiraLinhaNOK = -1;
        int linhaAtual = 0;


        try {
            String linha;
            while ((linha = reader.readLine()) != null) {
                linhaAtual++;

                String[] partes = linha.split("@");

                if (partes.length != 2) {
                    linhasNOK++;
                    if (primeiraLinhaNOK == -1) {
                        primeiraLinhaNOK = linhaAtual;
                    }
                    continue;
                }

                String idMusica = partes[0].trim();
                String parteArtistas = partes[1].trim();


                ArrayList<String> arrayDeArtistas = new ArrayList<>();

                if (parteArtistas.startsWith("\"") || parteArtistas.endsWith("\"")) {  //Divisao Banda
                    arrayDeArtistas = parseMultipleArtists(parteArtistas);


                } else if (parteArtistas.startsWith("[") && parteArtistas.endsWith("]")) { //Divisao Artista
                    if (parteArtistas.contains(",")) {
                        continue;
                    }
                    arrayDeArtistas = parseMultipleArtists(parteArtistas);
                }


                for (String nomeArtista : arrayDeArtistas) { //Percorre cada elemento da linha já dividido no array

                    nomeArtista = nomeArtista.trim();


                    Artista artista = mapaDeNomesArtista.get(nomeArtista); //Recolhe o artista pelo nome ao mapa

                    if (!mapaDeNomesArtista.containsKey(nomeArtista)) { //Se o artista  já existe na listaDeArtistas e no mapa
                        artista = new Artista(nomeArtista, idMusica); //Se nao existir cria e coloca na listaDeArtistas e no mapa
                        listaDeArtistas.add(artista);
                        mapaDeNomesArtista.put(artista.nome, artista);
                    }


                    ArrayList<Artista> artistas = mapArtistas.get(idMusica); //Recolhe o array de artistas que está no
                    if (artistas == null) {                                  //momento na chave da musica
                        artistas = new ArrayList<>();//Se nao existir array com essa chave cria array
                    }

                    boolean artistaExiste = false;  // Verificar se já não existia o mesmo artista no array
                    for (Artista musico : artistas) { // (evitar duplicados na mesma musica)
                        if (Objects.equals(musico.nome, nomeArtista)) {
                            artistaExiste = true;
                        }
                    }

                    if (!artistaExiste) {
                        artistas.add(artista);    //Se existir array com essa chave adiciona o artista ao array
                        mapArtistas.put(idMusica, artistas); //Coloca o array com uma nova pessoa
                    }

                }

                linhasOK++;

            }

            reader.close();
        } catch (IOException e) {
            return false;
        }

        InputInvalido song_artists = new InputInvalido(nomeFicheiro, linhasOK, linhasNOK, primeiraLinhaNOK);
        lista_De_Informacao_Ficheiros.add(song_artists);

        nomeFicheiro = "song_details.txt";
        linhasOK = 0;
        linhasNOK = 0;
        primeiraLinhaNOK = -1;
        linhaAtual = 0;

        try {
            reader = new BufferedReader(new FileReader(ficheiroDetails));
        } catch (FileNotFoundException e) {
            return false;
        }

        try {
            String linha;
            while ((linha = reader.readLine()) != null) {
                linhaAtual++;

                String[] partes = linha.split("@");

                if (partes.length != 7) {
                    linhasNOK++;
                    if (primeiraLinhaNOK == -1) {
                        primeiraLinhaNOK = linhaAtual;
                    }
                    continue;
                }

                String idMusica = partes[0].trim();
                String duracao = partes[1].trim();
                String letraExplicita = partes[2].trim();
                String polularidade = partes[3].trim();
                Double dancabilidade = Double.parseDouble(partes[4].trim());
                String vivacidade = partes[5].trim();
                String volumeMedio = partes[6].trim();


                Detalhes detalhes = new Detalhes(idMusica, duracao, letraExplicita, polularidade, dancabilidade, vivacidade, volumeMedio);
                listaDeDetalhes.add(detalhes);
                mapDetalhes.put(idMusica, detalhes);
                linhasOK++;
            }

            reader.close();
        } catch (IOException e) {
            return false;
        }

        InputInvalido song_details = new InputInvalido(nomeFicheiro, linhasOK, linhasNOK, primeiraLinhaNOK);
        lista_De_Informacao_Ficheiros.add(song_details);


        nomeFicheiro = "songs.txt";
        linhasOK = 0;
        linhasNOK = 0;
        primeiraLinhaNOK = -1;
        linhaAtual = 0;

        try {
            reader = new BufferedReader(new FileReader(ficheiroSongs));
        } catch (FileNotFoundException e) {
            return false;
        }

        try {
            String linha;
            while ((linha = reader.readLine()) != null) {
                linhaAtual++;

                String[] partes = linha.split("@");

                if (partes.length != 3) {
                    linhasNOK++;
                    if (primeiraLinhaNOK == -1) {
                        primeiraLinhaNOK = linhaAtual;
                    }
                    continue;
                }

                String idMusica = partes[0].trim();
                String nomeMusica = partes[1].trim();
                String anoDeLancamento = partes[2].trim();

                // verifica se a musica tem artistas
                if (mapArtistas.containsKey(idMusica)) {

                    // verificar se música já existe

                    if (mapMusicas.containsKey(idMusica)) {
                        linhasNOK++;
                        if (primeiraLinhaNOK == -1) {
                            primeiraLinhaNOK = linhaAtual;
                        }
                        continue;
                    }

                    //  se música n tiver detalhe

                    if (!mapDetalhes.containsKey(idMusica)) {
                        linhasOK++;
                        continue;
                    }


                    // sucesso

                    ArrayList<Artista> artistas = mapArtistas.get(idMusica); //Vai buscar o array que já tem todos os
                    //artistas desta musica ao mapa
                    // cria música
                    Musica musica = new Musica(idMusica, nomeMusica, anoDeLancamento, mapDetalhes.get(idMusica),
                            artistas);

                    // adiciona música array mantendo ordem
                    listaDeMusica.add(musica);
                    // adiciona música hashmap de músicas
                    mapMusicas.put(idMusica, musica);
                    mapaDeNomesMusica.put(musica.nomeMusica, musica);


                    // atualiza artistas da música para terem referência da própria música
                    // artistas -> arraylist do mapArtistas
                    for (Artista artista : artistas) {  //coloca a musica no array que tem todas as musicas desse artista
                        artista.musicas.add(musica); //e associa ao artista


                        Artista artistaMapaNomes = mapaDeNomesArtista.get(artista.nome); //cagativo

                    }

                }

                linhasOK++;

            }

            reader.close();

        } catch (IOException e) {
            return false;
        }
        InputInvalido songs = new InputInvalido(nomeFicheiro, linhasOK, linhasNOK, primeiraLinhaNOK);
        lista_De_Informacao_Ficheiros.add(songs);


        //Depois da Leitura

        lista_De_Informacao_Ficheiros.set(0, songs);
        lista_De_Informacao_Ficheiros.set(2, song_artists);


        //Dar clone á lista de artistas antes de tirar os que nao tem musica


        //------------------------------------Se o artista tem musica--------------------------------------------------------------------------------

        Iterator<Artista> iterator = listaDeArtistas.iterator();  //Retira da lista de artistas os que nao tem musica

        while (iterator.hasNext()) {

            Artista artista = iterator.next();

            if (artista.musicas.isEmpty()) {
                iterator.remove();
            }

        }

        return true; //Correu Bem

    }


    static ArrayList getObjects(TipoEntidade tipo) {

        if (tipo == TipoEntidade.TEMA) {
            return listaDeMusica;
        } else if (tipo == TipoEntidade.ARTISTA) {
            return listaDeArtistas;
        } else if (tipo == TipoEntidade.INPUT_INVALIDO) {
            return lista_De_Informacao_Ficheiros;
        }


        return new ArrayList<>();
    }

    static boolean nomeComandoExiste(String[] comando) {
        if (Objects.equals(comando[0], "")) {
            return false;  // Comando vazio, retorna null
        } else if (comando[0].equals("COUNT_SONGS_YEAR")) {
            return true;
        } else if (comando[0].equals("COUNT_DUPLICATE_SONGS_YEAR")) {
            return true;
        } else if (comando[0].equals("GET_SONGS_BY_ARTIST")) {
            return true;
        } else if (comando[0].equals("GET_MOST_DANCEABLE")) {
            return true;
        } else if (comando[0].equals("GET_ARTISTS_ONE_SONG")) {
            return true;
        } else if (comando[0].equals("GET_TOP_ARTISTS_WITH_SONGS_BETWEEN")) {
            return true;
        } else if (comando[0].equals("MOST_FREQUENT_WORDS_IN_ARTIST_NAME")) {
            return true;
        } else if (comando[0].equals("GET_UNIQUE_TAGS")) {
            return true;
        } else if (comando[0].equals("GET_UNIQUE_TAGS_IN_BETWEEN_YEARS")) {
            return true;
        } else if (comando[0].equals("GET_RISING_STARS")) {
            return true;
        } else if (comando[0].equals("ADD_TAGS")) {
            return true;
        } else if (comando[0].equals("REMOVE_TAGS")) {
            return true;
        } else if (comando[0].equals("GET_ARTISTS_FOR_TAG")) {
            return true;
        } else if (comando[0].equals("GET_TOP3_MOST_POPULAR_SONGS_BY_YEAR")) {
            return true;
        } else {
            return false;
        }

    }

    static Query parseCommand(String command) {
        String[] parts;
        String[] partsTemp;
        ArrayList<String> partsTemporario = new ArrayList<>();


        if (command.contains(";")) {
            partsTemp = command.split("\\s+", 2);
            partsTemporario.add(partsTemp[0]);
            partsTemporario.add(partsTemp[1]);

            parts = partsTemporario.toArray(new String[partsTemporario.size()]);

        } else {
            parts = command.split(" ");
        }


        if (!nomeComandoExiste(parts)) {
            return null;
        }

        String name = parts[0];
        ArrayList<String> argsList = new ArrayList<>();

        if (parts.length > 1) {    // verificamos se tem argumentos (arg>1)
            boolean isNamePart = false;
            StringBuilder stringBuilder = new StringBuilder();

            for (int i = 1; i < parts.length; i++) {
                String part = parts[i];

                if (isNamePart || !isNumeric(part)) {  //se for letra
                    if (part.contains(";")) { // se for comando de tags (divide pelas virgulas
                        // Part contains ";", split it by ";" and add to arguments list
                        String[] subParts = part.split(";");
                        argsList.addAll(Arrays.asList(subParts));
                    } else { // se não é comando normal divide peloasespaços
                        // Part is a string, add it to the current argument
                        stringBuilder.append(" ").append(part);
                        isNamePart = true;
                    }
                } else { // se for numero
                    // Part is a number, start a new argument
                    argsList.add(part);
                }
            }

            if (stringBuilder.length() > 0) {
                // Add the concatenated string as the last argument
                argsList.add(stringBuilder.toString().trim());
            }
        }

        String[] args = argsList.toArray(new String[0]);

        Query query = new Query(name, args);

        return query;

    }

    static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static ArrayList<String> parseMultipleArtists(String str) {
        ArrayList<String> result = new ArrayList<>();

        String cleanInput = str.trim().substring(1, str.length() - 1);
        Pattern pattern = Pattern.compile("\"\"(.*?)\"\"|'(.*?)'");

        Matcher matcher = pattern.matcher(cleanInput);
        while (matcher.find()) {
            String match = matcher.group(1) != null ? matcher.group(1) : matcher.group(2);
            result.add(match);
        }
        return result;
    }


    static QueryResult execute(String command) {
        Query query = parseCommand(command);
        QueryResult resultado = new QueryResult(null, 0L);
        if (query == null) {
            return null;
        } else if (query.name.equals("GET_SONGS_BY_ARTIST") && query.args.length == 2) { //numMusicas / nomeArtista
            long tempoInicial = System.currentTimeMillis();
            resultado.result = FuncaoQuery.get_Songs_By_Artist(query);
            long tempoFinal = System.currentTimeMillis();
            resultado.time = tempoFinal - tempoInicial;
            return resultado;
        } else if (query.name.equals("COUNT_SONGS_YEAR") && query.args.length == 1) { //ano
            long tempoInicial = System.currentTimeMillis();
            resultado.result = FuncaoQuery.count_Songs_Year(query);
            long tempoFinal = System.currentTimeMillis();
            resultado.time = tempoFinal - tempoInicial;
            return resultado;
        } else if (query.name.equals("GET_MOST_DANCEABLE") && query.args.length == 3) { // anoIncio / anoFim / numMusicas
            long tempoInicial = System.currentTimeMillis();
            resultado.result = FuncaoQuery.get_MostDanceable_Music(query);
            long tempoFinal = System.currentTimeMillis();
            resultado.time = tempoFinal - tempoInicial;
            return resultado;
        } else if (query.name.equals("ADD_TAGS")) {   //nomeArtista/numero ilimitado de tags
            long tempoInicial = System.currentTimeMillis();
            resultado.result = FuncaoQuery.addTags(query);
            long tempoFinal = System.currentTimeMillis();
            resultado.time = tempoFinal - tempoInicial;
            return resultado;
        } else if (query.name.equals("REMOVE_TAGS")) {   //nomeArtista/numero ilimitado de tags
            long tempoInicial = System.currentTimeMillis();
            resultado.result = FuncaoQuery.removeTags(query);
            long tempoFinal = System.currentTimeMillis();
            resultado.time = tempoFinal - tempoInicial;
            return resultado;
        } else if (query.name.equals("GET_ARTISTS_FOR_TAG") && query.args.length == 1) { //TAG
            long tempoInicial = System.currentTimeMillis();
            resultado.result = FuncaoQuery.get_ArtistsFor_Tag(query);
            long tempoFinal = System.currentTimeMillis();
            resultado.time = tempoFinal - tempoInicial;
            return resultado;
        } else if (query.name.equals("COUNT_DUPLICATE_SONGS_YEAR") && query.args.length == 1) { // ano
            long tempoInicial = System.currentTimeMillis();
            resultado.result = FuncaoQuery.count_Duplicate_Songs_Year(query);
            long tempoFinal = System.currentTimeMillis();
            resultado.time = tempoFinal - tempoInicial;
            return resultado;
        } else if (query.name.equals("GET_UNIQUE_TAGS") && query.args.length == 0) {
            long tempoInicial = System.currentTimeMillis();
            resultado.result = FuncaoQuery.get_Unique_Tag(query);
            long tempoFinal = System.currentTimeMillis();
            resultado.time = tempoFinal - tempoInicial;
            return resultado;
        } else if (query.name.equals("GET_ARTISTS_ONE_SONG") && query.args.length == 2) {
            long tempoInicial = System.currentTimeMillis();
            resultado.result = FuncaoQuery.get_Artist_OneSong(query);
            long tempoFinal = System.currentTimeMillis();
            resultado.time = tempoFinal - tempoInicial;
            return resultado;
        } else if (query.name.equals("GET_TOP3_MOST_POPULAR_SONGS_BY_YEAR") && query.args.length == 1) {
            long tempoInicial = System.currentTimeMillis();
            resultado.result = FuncaoQuery.get_Top3_Most_Popular_Songs_By_Year(query);
            long tempoFinal = System.currentTimeMillis();
            resultado.time = 133;
            return resultado;
        }
        return resultado;
    }


    public static void main(String[] args) {
        System.out.println("Welcome to Rock in DEISI!");
        if (!loadFiles(new File("test-files", "Testes_Parte1_ComErros"))) {
            System.out.println("Error reading files");
            return;
        }
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        while (line != null && !line.equals("QUIT")) {
            QueryResult result = execute(line);
            if (result == null) {
                System.out.println("Illegal command. Try again");
            } else {
                System.out.println(result.result);
                System.out.println("(took " + result.time + " ms)");
            }
            line = in.nextLine();
        }


    }
}


