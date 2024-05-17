import java.io.*;
import java.nio.file.*;
import java.util.*;

class Estudiante {
    String nombre;
    String apellido;
    String genero;
    int edad;
    double promedioNotas;

    public Estudiante(String nombre, String apellido, String genero, int edad, double promedioNotas) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.genero = genero;
        this.edad = edad;
        this.promedioNotas = promedioNotas;
    }
}

public class Ejercicio4 {
    public static void main(String[] args) {
        String archivoCSV = "lista_alumnos.csv";
        List<Estudiante> estudiantes = new ArrayList<>();

        try (BufferedReader br = Files.newBufferedReader(Paths.get(archivoCSV))) {
            String linea;
            br.readLine();
            while ((linea = br.readLine()) != null) {
                String[] valores = linea.split(",");
                if (valores.length == 5) {
                    estudiantes.add(new Estudiante(valores[0], valores[1], valores[2], Integer.parseInt(valores[3]), Double.parseDouble(valores[4])));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Imprimir el contenido del archivo CSV
        estudiantes.forEach(e -> System.out.println(e.nombre + "," + e.apellido + "," + e.genero + "," + e.edad + "," + e.promedioNotas));


        int numEstudiantes = estudiantes.size();
        System.out.println("Esta clase tiene " + numEstudiantes + " estudiantes");


        double edadPromedio = estudiantes.stream().mapToInt(e -> e.edad).average().orElse(0);
        int edadPromedioRedondeada = (int) Math.round(edadPromedio);
        System.out.println("Edad media: " + edadPromedioRedondeada);


        double promedioNotas = estudiantes.stream().mapToDouble(e -> e.promedioNotas).average().orElse(0);
        double notaMaxima = estudiantes.stream().mapToDouble(e -> e.promedioNotas).max().orElse(0);
        double notaMinima = estudiantes.stream().mapToDouble(e -> e.promedioNotas).min().orElse(0);

        System.out.println("Nota promedio: " + promedioNotas);
        System.out.println("Nota máxima: " + notaMaxima);
        System.out.println("Nota mínima: " + notaMinima);


        try (BufferedWriter bw = Files.newBufferedWriter(Paths.get(archivoCSV), StandardOpenOption.APPEND)) {
            bw.newLine();
            bw.write("Número de estudiantes: " + numEstudiantes);
            bw.newLine();
            bw.write("Edad media: " + edadPromedioRedondeada);
            bw.newLine();
            bw.write("Nota promedio: " + promedioNotas);
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }


        try (BufferedWriter bw = Files.newBufferedWriter(Paths.get("informe-clase.txt"))) {
            bw.write("Número de estudiantes: " + numEstudiantes);
            bw.newLine();
            bw.write("Edad media: " + edadPromedioRedondeada);
            bw.newLine();
            bw.write("Nota promedio: " + promedioNotas);
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
