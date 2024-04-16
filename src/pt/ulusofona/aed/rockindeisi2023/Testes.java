package pt.ulusofona.aed.rockindeisi2023;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class Testes {

    @Test
    public void loadFiles_listaDeMusica_Anterior_1995_Correta() {

        File folder = new File("test-files", "Testes_Parte1_ComErros");
        Main.loadFiles(folder);
        Main.getObjects(TipoEntidade.TEMA);


        ArrayList<String> resultado_Esperado = new ArrayList<>();
        String musicaLinha1 = "2Sy1r4fGfq4Lslfc0gHkbk | Only For You | 1990";
        String musicaLinha2 = "1l4CEjapdCSUsazfBMjR4l | Loops & Tings - Radio Edit | 1993";
        //música linha3 está errada não deve aparecer
        resultado_Esperado.add(musicaLinha1);
        resultado_Esperado.add(musicaLinha2);


        ArrayList<Musica> ListaDeMusica_Anterior_1995 = new ArrayList<>();

        for (Musica musica : Main.listaDeMusica) {
            int ano = Integer.parseInt(musica.anoDeLancamento);
            if (ano < 1995) {
                ListaDeMusica_Anterior_1995.add(musica);
            }
        }
        assertEquals(resultado_Esperado.toString(), ListaDeMusica_Anterior_1995.toString());
    }


    @Test
    public void loadFiles_listaDeMusica_Anterior_2000_Correta() {

        File folder = new File("test-files", "Testes_Parte1_ComErros");
        Main.loadFiles(folder);
        Main.getObjects(TipoEntidade.TEMA);


        ArrayList<String> resultado_Esperado = new ArrayList<>();

        //música Linha4 não tem detalhes, não deve aparecer
        String musicaLinha5 = "78YHGVR0T59IOHJ394HG34 | Candy Shop | 1998 | 0:59 | 45";
        String musicaLinha6 = "1SlMYQmLe0yNEvBIfaPTAW | Ich will | 1999 | 3:37 | 57";

        resultado_Esperado.add(musicaLinha5);
        resultado_Esperado.add(musicaLinha6);


        ArrayList<Musica> ListaDeMusica_Anterior_2000 = new ArrayList<>();

        for (Musica musica : Main.listaDeMusica) {
            int ano = Integer.parseInt(musica.anoDeLancamento);
            if (ano >= 1995 && ano < 2000) {
                ListaDeMusica_Anterior_2000.add(musica);
            }
        }
        assertEquals(resultado_Esperado.toString(), ListaDeMusica_Anterior_2000.toString());
    }


    @Test
    public void loadFiles_listaDeMusica_Superior_2000_Correta() {

        File folder = new File("test-files", "Testes_Parte1_ComErros");
        Main.loadFiles(folder);
        Main.getObjects(TipoEntidade.TEMA);


        ArrayList<String> resultado_Esperado = new ArrayList<>();

        String musicaLinha1 = "6r8k1vznHrzlEKYxL4dZEe | Heirate mich | 2000 | 0:59 | 45 | 1";
        String musicaLinha2 = "1KLyMQwUeIPJaXn7wgdeNW | Dibil | 2000 | 6:50 | 16 | 1";
        String musicaLinha3 = "12gh2h91291090921hsjhs | Mata Bichos | 2003 | 2:05 | 19 | 1";
        String musicaLinha4 = "12354fdsht3245twsgdshj | Mata Bichos | 2003 | 6:50 | 16 | 1";
        String musicaLinha5 = "4798gbv8ufyh3fh43y6gt7 | ola | 2001 | 4:02 | 70 | 1";
        String musicaLinha6 = "uwhg8fi2rgfiwwhfuwifwe | Dark Horse | 2014 | 4:02 | 70 | 1";
        String musicaLinha7 = "9griojgriugnuhgeufyrgi | Can't Remember to Forget You | 2014 | 5:47 | 51 | 1";
        String musicaLinha8 = "ehgn8eghrew8fghwywgabv | La La La | 2014 | 0:59 | 45 | 1";
        String musicaLinha9 = "89hsuifhwsgfbysafgyuag | See You Again | 2015 | 4:02 | 75 | 1";
        String musicaLinha10 = "uwfu894y38778waf78twqf | Sugar | 2015 | 5:47 | 60 | 1";
        String musicaLinha11 = "6twfgduwgdwfgwyfgweyfg | Lean On | 2015 | 0:59 | 40 | 1";
        //música Linha9 não tem artista associado, não deve aparecer;
        //música Linha10 é a mesma música que a linha 7, não deve aparecer

        resultado_Esperado.add(musicaLinha1);
        resultado_Esperado.add(musicaLinha2);
        resultado_Esperado.add(musicaLinha3);
        resultado_Esperado.add(musicaLinha4);
        resultado_Esperado.add(musicaLinha5);
        resultado_Esperado.add(musicaLinha6);
        resultado_Esperado.add(musicaLinha7);
        resultado_Esperado.add(musicaLinha8);
        resultado_Esperado.add(musicaLinha9);
        resultado_Esperado.add(musicaLinha10);
        resultado_Esperado.add(musicaLinha11);


        ArrayList<Musica> ListaDeMusica_Superior_2000 = new ArrayList<>();

        for (Musica musica : Main.listaDeMusica) {
            int ano = Integer.parseInt(musica.anoDeLancamento);
            if (ano >= 2000) {
                ListaDeMusica_Superior_2000.add(musica);
            }
        }
        assertEquals(resultado_Esperado.toString(), ListaDeMusica_Superior_2000.toString());
    }


    @Test
    public void loadFiles_listaDeArtistas_Correta() {

        File folder = new File("test-files", "Testes_Parte1_ComErros");
        Main.loadFiles(folder);


        ArrayList<String> resultado_Esperado = new ArrayList<>();

        String artista1 = "Artista: [La Sonora Matancera] | 2";
        String artista2 = "Artista: [Carlos Argentino]";
        String artista3 = "Artista: [Girao] | 2";
        String artista4 = "Artista: [Pinto] | 1";
        String artista5 = "Artista: [Rammstein] | 2";
        String artista6 = "Artista: [Madonna] | 1";
        String artista7 = "Artista: [Antônio Carlos Jobim]";
        String artista8 = "Artista: [Vinícius de Moraes] | 1";
        String artista9 = "Artista: [JP] | 1";
        String artista10 = "Artista: [50 Cent] | 1";
        String artista11 = "Artista: [Afonso Surfista]";
        String artista12 = "Artista: [Lendia] | 1";
        String artista13 = "Artista: [Katy Perry] | 1";
        String artista14 = "Artista: [Shakira] | 2";
        String artista15 = "Artista: [Wiz Khalifa] | 1";
        String artista16 = "Artista: [Maroon 5] | 1";
        String artista17 = "Artista: [Major Lazer] | 1";


        resultado_Esperado.add(artista1);
        resultado_Esperado.add(artista2);
        resultado_Esperado.add(artista3);
        resultado_Esperado.add(artista4);
        resultado_Esperado.add(artista5);
        resultado_Esperado.add(artista6);
        resultado_Esperado.add(artista7);
        resultado_Esperado.add(artista8);
        resultado_Esperado.add(artista9);
        resultado_Esperado.add(artista10);
        resultado_Esperado.add(artista11);
        resultado_Esperado.add(artista12);
        resultado_Esperado.add(artista13);
        resultado_Esperado.add(artista14);
        resultado_Esperado.add(artista15);
        resultado_Esperado.add(artista16);
        resultado_Esperado.add(artista17);


        assertEquals(resultado_Esperado.toString(), Main.getObjects(TipoEntidade.ARTISTA).toString());
    }


    @Test
    public void loadFiles_leituraDeFicheiros_Sem_Erros() {
        File folder = new File("test-files", "Testes_Parte1_SemErros");
        Main.loadFiles(folder);


        ArrayList<String> resultado_Esperado = new ArrayList<>();
        String ficheiro1 = "songs.txt | 3 | 0 | -1";
        String ficheiro2 = "song_details.txt | 3 | 0 | -1";
        String ficheiro3 = "song_artists.txt | 3 | 0 | -1";
        resultado_Esperado.add(ficheiro1);
        resultado_Esperado.add(ficheiro2);
        resultado_Esperado.add(ficheiro3);

        assertEquals(resultado_Esperado.toString(), Main.getObjects(TipoEntidade.INPUT_INVALIDO).toString());
    }

    @Test
    public void loadFiles_leituraDeFicheiros_Com_Erros() {
        File folder = new File("test-files", "Testes_Parte1_ComErros");
        Main.loadFiles(folder);

        ArrayList<String> resultado_Esperado = new ArrayList<>();
        String ficheiro1 = "songs.txt | 17 | 2 | 3";
        String ficheiro2 = "song_details.txt | 18 | 1 | 10";
        String ficheiro3 = "song_artists.txt | 18 | 1 | 10";
        resultado_Esperado.add(ficheiro1);
        resultado_Esperado.add(ficheiro2);
        resultado_Esperado.add(ficheiro3);

        assertEquals(resultado_Esperado.toString(), Main.getObjects(TipoEntidade.INPUT_INVALIDO).toString());
    }

    @Test
    public void parseMultipleArtists() {
        assertArrayEquals(new String[]{"aaa"},
                Main.parseMultipleArtists("['aaa']").toArray());
        assertArrayEquals(new String[]{"aaa", "bbb"},
                Main.parseMultipleArtists("['aaa', 'bbb']").toArray());
        assertArrayEquals(new String[]{"a,aa", "bbb"},
                Main.parseMultipleArtists("['a,aa', 'bbb']").toArray());
        assertArrayEquals(new String[]{"aaa", "bb b"},
                Main.parseMultipleArtists("['aaa', 'bb b']").toArray());
        assertArrayEquals(new String[]{"a'aa", "bbb"},
                Main.parseMultipleArtists("[\"\"a'aa\"\", 'bbb']").toArray());
        assertArrayEquals(new String[]{"a' aa", "b, bb", "ccc"},
                Main.parseMultipleArtists("[\"\"a' aa\"\", 'b, bb', 'ccc']").toArray());
    }


    @Test
    public void getSongsByArtist() {
        Main.loadFiles(new File("test-files", "Testes_Parte1_ComErros"));
        QueryResult queryResult = Main.execute("GET_SONGS_BY_ARTIST 10 La Sonora Matancera");
        assertNotNull(queryResult);
        assertNotNull(queryResult.result);
        String[] resultParts = queryResult.result.split("\n");
        assertEquals(2, resultParts.length);
        assertArrayEquals(new String[]{
                "Only For You : 1990",
                "Ich will : 1999",
        }, resultParts);
    }

    @Test
    public void count_Songs_Year() {
        Main.loadFiles(new File("test-files", "Testes_Parte1_ComErros"));
        QueryResult queryResult = Main.execute("COUNT_SONGS_YEAR 2000");
        assertNotNull(queryResult);
        assertNotNull(queryResult.result);
        String[] resultParts = queryResult.result.split("\n");
        assertEquals(1, resultParts.length);
        assertArrayEquals(new String[]{
                "2"
        }, resultParts);
    }

    @Test
    public void count_DuplicateSongs_Year() {
        Main.loadFiles(new File("test-files", "Testes_Parte1_ComErros"));
        QueryResult queryResult = Main.execute("COUNT_DUPLICATE_SONGS_YEAR 2003");
        assertNotNull(queryResult);
        assertNotNull(queryResult.result);
        String[] resultParts = queryResult.result.split("\n");
        assertEquals(1, resultParts.length);
        assertArrayEquals(new String[]{
                "1"
        }, resultParts);
    }


    @Test
    public void get_MostDanceble() {
        Main.loadFiles(new File("test-files", "Testes_Parte1_ComErros"));
        QueryResult queryResult = Main.execute("GET_MOST_DANCEABLE 1990 2010 10");
        assertNotNull(queryResult);
        assertNotNull(queryResult.result);
        String[] resultParts = queryResult.result.split("\n");
        assertEquals(9, resultParts.length);
        assertArrayEquals(new String[]{
                "Loops & Tings - Radio Edit : 1993 : 0.725",
                "ola : 2001 : 0.725",
                "Mata Bichos : 2003 : 0.691",
                "Candy Shop : 1998 : 0.649",
                "Heirate mich : 2000 : 0.649",
                "Ich will : 1999 : 0.648",
                "Only For You : 1990 : 0.397",
                "Dibil : 2000 : 0.221",
                "Mata Bichos : 2003 : 0.221"
        }, resultParts);
    }


    @Test
    public void add_Tag() {
        Main.loadFiles(new File("test-files", "Testes_Parte1_ComErros"));
        QueryResult queryResult = Main.execute("ADD_TAGS Girao;dolba;corno");
        assertNotNull(queryResult);
        assertNotNull(queryResult.result);
        String resultParts = queryResult.result.toString();

        assertArrayEquals(new String[]{
                "Girao | DOLBA,CORNO"

        }, new String[]{resultParts});
    }

    @Test
    public void get_ArtistTag() {
        Main.loadFiles(new File("test-files", "Testes_Parte1_ComErros"));
        QueryResult queryResult = Main.execute("GET_ARTISTS_FOR_TAG dolba");
        assertNotNull(queryResult);
        assertNotNull(queryResult.result);
        String resultParts = queryResult.result.toString();

        assertArrayEquals(new String[]{
                "No results"

        }, new String[]{resultParts});
    }


    @Test
    public void get_ArtistsOneSong() {
        Main.loadFiles(new File("test-files", "Testes_Parte1_ComErros"));
        QueryResult queryResult = Main.execute("GET_ARTISTS_ONE_SONG 1990 2010");
        assertNotNull(queryResult);
        assertNotNull(queryResult.result);
        String[] resultParts = queryResult.result.split("\n");
        assertEquals(9, resultParts.length);
        assertArrayEquals(new String[]{
                "50 Cent | Candy Shop | 1998",   //ramstein nao aparece tem duas musicas entre os anos
                "Afonso Surfista | Mata Bichos | 2003",
                "Antônio Carlos Jobim | Ich will | 1999",
                "Carlos Argentino | Only For You | 1990",
                "JP | Ich will | 1999",
                "Lendia | Mata Bichos | 2003",
                "Madonna | Heirate mich | 2000",
                "Pinto | Only For You | 1990",
                "Vinícius de Moraes | Ich will | 1999",
        }, resultParts);
    }


    @Test
    public void testCreativeQuery() {
        Main.loadFiles(new File("test-files", "Testes_Parte1_ComErros"));
        QueryResult queryResult = Main.execute("GET_TOP3_MOST_POPULAR_SONGS_BY_YEAR 2014");
        assertNotNull(queryResult);
        assertNotNull(queryResult.result);
        String[] resultParts = queryResult.result.split("\n");
        assertEquals(3, resultParts.length);

        assertArrayEquals(new String[]{
                "TOP1: Dark Horse - 70",
                "TOP2: Can't Remember to Forget You - 51",
                "TOP3: La La La - 45",

        }, resultParts);

        queryResult = Main.execute("GET_TOP3_MOST_POPULAR_SONGS_BY_YEAR 2015");
        assertNotNull(queryResult);
        assertNotNull(queryResult.result);
        resultParts = queryResult.result.split("\n");
        assertEquals(3, resultParts.length);

        assertArrayEquals(new String[]{
                "TOP1: See You Again - 75",
                "TOP2: Sugar - 60",
                "TOP3: Lean On - 40",

        }, resultParts);
    }


}



