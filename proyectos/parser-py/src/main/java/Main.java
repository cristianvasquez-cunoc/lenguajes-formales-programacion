class Main {

    public static void main(String[] args) {

        // "mi_id_\r\n" + //
        // "#comentario \r\n" + //
        // "while\r\n" + //
        // "for\r\n" + //
        // ">=\r\n" + //
        // "<\r\n" + //
        // "'cadena en comillas simples'\r\n" + //
        // "\r\n" + //
        // "#un error se puede presentar como la siguiente linea\r\n" + //
        // "\r\n" + //
        // "\" mi cadena que no cierra";

        Analizer analizer = new Analizer();

        String stringToAnalize = "def\nreturn\tas.for";

        analizer.start(stringToAnalize);

    }

}