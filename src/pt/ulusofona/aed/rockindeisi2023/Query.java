package pt.ulusofona.aed.rockindeisi2023;

import java.util.Arrays;

public class Query {

    String name;

    String[] args;

    public Query(String name, String[] args) {
        this.name = name;
        this.args = args;
    }

    public Query(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "" + Arrays.toString(args) + "";
    }
}
