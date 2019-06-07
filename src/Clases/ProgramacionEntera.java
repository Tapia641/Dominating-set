/*AUTOR: HERNÁNDEZ TAPIA LUIS ENRIQUE ;D */


package Clases;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.io.*;

public class ProgramacionEntera {
    private String Cadena = "";

    public ProgramacionEntera() {
    }

    public void EjecutarPython() {

        String s = null;

        try {
            File out = new File(new File("src/Python/PE.py").getAbsolutePath());
            System.out.println("python " + out.getAbsolutePath());
            Process p = Runtime.getRuntime().exec("python " + out.getAbsolutePath());

            BufferedReader stdInput = new BufferedReader(new
                    InputStreamReader(p.getInputStream()));

            BufferedReader stdError = new BufferedReader(new
                    InputStreamReader(p.getErrorStream()));

            System.out.println("Salida:\n");
            String total = "";
            while ((s = stdInput.readLine()) != null) {
                //System.out.println(s);
                total += s;
            }

            if (total.equals("NO")){
                System.err.println("Simplex perdió factibilidad");
            }

            Cadena = total;

            System.out.println("Errores:\n");
            while ((s = stdError.readLine()) != null) {
                System.out.println(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getCadena() {
        return Cadena;
    }
}
