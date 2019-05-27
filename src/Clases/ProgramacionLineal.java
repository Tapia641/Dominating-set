package Clases;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

public class ProgramacionLineal {
    public ProgramacionLineal() {
    }

    public void EjecutarPython() {

        String s = null;

        try {
            File out = new File(new File("src/Python/PL.py").getAbsolutePath());
            System.out.println("python " + out.getAbsolutePath());
            Process p = Runtime.getRuntime().exec("python " + out.getAbsolutePath());

            BufferedReader stdInput = new BufferedReader(new
                    InputStreamReader(p.getInputStream()));

            BufferedReader stdError = new BufferedReader(new
                    InputStreamReader(p.getErrorStream()));

            System.out.println("Salida:\n");
            String total = "";
            while ((s = stdInput.readLine()) != null) {
                System.out.println(s);
                total += s;
            }

            if (total.equals("NO")){
                System.err.println("Simplex perdió factibilidad");
            }

            System.out.println("Errores:\n");
            while ((s = stdError.readLine()) != null) {
                System.out.println(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
