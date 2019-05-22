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
            //File archivo = new File("Python/PL.py");
            //System.out.println(archivo.getAbsolutePath());
            String ruta = "C:\\Users\\Tapia\\Desktop\\Python\\PL.py";
            String parametros = "M";
            Process p = Runtime.getRuntime().exec("python " + ruta + " " + parametros);

            BufferedReader stdInput = new BufferedReader(new
                    InputStreamReader(p.getInputStream()));

            BufferedReader stdError = new BufferedReader(new
                    InputStreamReader(p.getErrorStream()));

            System.out.println("Salida:\n");
            while ((s = stdInput.readLine()) != null) {
                System.out.println(s);
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
