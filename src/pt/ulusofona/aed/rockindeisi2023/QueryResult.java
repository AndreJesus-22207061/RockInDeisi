package pt.ulusofona.aed.rockindeisi2023;

public class QueryResult {
    String result;

    long time;


    public QueryResult(String result, Long time) {
        this.result = result;
        this.time = time;
    }

    @Override
    public String toString() {
        return result + "took " + time + " ms";
    }
}