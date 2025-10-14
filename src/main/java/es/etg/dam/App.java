package es.etg.dam;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class App {

    public static final String TEXTO = """
            Me gusta PSP y java
            PSP se programa en java
            es un modulo de DAM
            y se programa de forma concurrente en PSP
            PSP es programacion.
            """;

    public static final String MSG_ERROR = "Se ha producido un error al ejecutar el comando";
    public static final int EXIT_BIEN = 0;
    public static final int EXIT_MAL = 1;
    public static final String COMANDO = "grep PSP";
    public static final String N = "\n";
    public static final String DECORACION = "----------------------------";
    public static final String TEXTO_SALIDA = "Líneas que tienen PSP: " + N + DECORACION;

    public static void main(String[] args) throws Exception {

        String linea;

        Process p = Runtime.getRuntime().exec(COMANDO);
        OutputStream out = p.getOutputStream();

        PrintWriter pw = new PrintWriter(new OutputStreamWriter(out));
        pw.println(TEXTO);
        pw.close();

		StringBuilder output = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));

        while ((linea = br.readLine()) != null) {
            output.append(linea).append(N);
        }

        br.close();

        int exitVal = p.waitFor();

        if (exitVal == 0) {
            System.out.println(TEXTO_SALIDA + N + output + DECORACION);
            System.exit(EXIT_BIEN);
        } else {
            System.out.println(MSG_ERROR);
            System.exit(EXIT_MAL);
        }
    }
}
