/*
Cristian Andrés Gallardo Rojas
GNU GPL V3
Programa que cumpla con los requisitos de: 
 *Ingresar la cantidad de notas.
 *Ingresar las notas.
 *Calcular la nota definitiva.
 *Calcular nota mayor.
 *Calcular nota menor.
 *Calcular la nota que más se repite.
 *Ordenar las notas con el método burbuja.
 *Clasificar las notas en A, B, C y F.
 *Mostrar un panel con un resumen de los metodos realizados

 **/


import javax.swing.JOptionPane;
public class Arreglos{
    public static void main(String[] args) {
        int numNotas = ingresarNumNotas(
                "Ingrese el numero de notas: ",
                "Error: Ingrese un numero entero valido."
        );

        double[] notas = ingresarNotas("Ingrese la nota", numNotas);

        double notaDefinitiva = calcularNotaDefinitiva(notas);
        double notaMayor = calcularNotaMayor(notas);
        double notaMenor = calcularNotaMenor(notas);
        double notaRepetida = calcularNotaRepetida(notas);
        double[] notasOrdenadas = ordenarBurbuja(notas);
        String clasificacion = clasificarNota(notas);
        String resumen = resumirResultados(notaDefinitiva, notaMayor, notaMenor, notaRepetida, notasOrdenadas, clasificacion);
    }

    //Metodo para ingresar el numero de notas
    public static int ingresarNumNotas(String mensaje, String errorMensaje) {
        int numNotas = 0;
        boolean valido = false;

        while (!valido) {
            try {
                int n= Integer.valueOf(JOptionPane.showInputDialog(null, mensaje));
                if (n > 0) {
                    numNotas = n;
                    valido = true;
                } else {
                    JOptionPane.showMessageDialog(null, "Error: El numero debe ser mayor que cero.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, errorMensaje);
            }
        }
        return numNotas;
    }
    //Metodo para ingresar las notas
    public static double [] ingresarNotas(String mensaje, int numNotas) {
        double[] notas = new double[numNotas];
        for (int i = 0; i < numNotas; i++) {
            boolean valido = false;
            while (!valido) {
                try {
                    double nota = Double.valueOf(JOptionPane.showInputDialog(null, mensaje + " " + (i + 1) + ":"));
                    if (nota >= 0 && nota <= 100) {
                        notas[i] = nota;
                        valido = true;
                    } else {
                        JOptionPane.showMessageDialog(null, "Error: La nota debe estar entre 0 y 5.0.");
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Error: Ingrese un numero valido.");
                }
            }
        }
        return notas;
    }

    //Metodo que calcula la nota definitiva
    public static double calcularNotaDefinitiva(double[]notas){
        double notaDefinitiva = 0;
        double suma = 0;
        for(int i = 0; i<notas.length; i++){
            suma+= notas[i];
        }
        notaDefinitiva = suma/notas.length;
        return notaDefinitiva;

    }

    //Metodo que calcula la nota mayor
    public static double calcularNotaMayor(double[]notas){
        double notaMayor = 0;
        for(int i = 0; i<notas.length; i++){
            if(notas[i]>notaMayor){
                notaMayor = notas[i];
            }

        }
        return  notaMayor;
    }

    //Metodo que calcula la nota menor
    public static double calcularNotaMenor(double[]notas){
        double notaMenor= notas[0];
        for(int i= 0; i<notas.length; i++){
            if(notas[i]<notaMenor){
                notaMenor = notas[i];
            }
        }
        return notaMenor;
    }

    //Metodo que calcula la nota que mas se repite
    public static double calcularNotaRepetida(double[] notas) {
        double notaRepetida = -1;
        int maxRepeticiones = 0;

        for (int i = 0; i < notas.length; i++) {
            int contador = 0;

            for (int j = 0; j < notas.length; j++) {
                if (notas[i] == notas[j]) {
                    contador++;
                }
            }

            if (contador > maxRepeticiones) {
                maxRepeticiones = contador;
                notaRepetida = notas[i];
            }
        }
        return notaRepetida;
    }
    //Metodo que ordena las notas con el metodo burbuja
    public static  double[] ordenarBurbuja(double[] notas) {
        double[] notasOrdenadas = notas.clone();
        for (int i = 0; i < notasOrdenadas.length - 1; i++) {
            for (int j = 0; j < notasOrdenadas.length - 1 - i; j++) {
                if (notasOrdenadas[j] > notasOrdenadas[j + 1]) {
                    double temp = notasOrdenadas[j];
                    notasOrdenadas[j] = notasOrdenadas[j + 1];
                    notasOrdenadas[j + 1] = temp;
                }
            }
        }
        return notasOrdenadas;

    }
    //Metodo que clasifica las notas en S, A, F+ y F-
    public static String clasificarNota(double[]notas){
        String resultado = "";
        for (double nota : notas) {
            if (nota >= 4.5) resultado += nota + " (S)\n";
            else if (nota >= 4.0) resultado += nota + " (A)\n";
            else if (nota >= 3.0) resultado += nota + " (F+)\n";
            else resultado += nota + " (F-)\n";
        }
        return resultado;
    }
    //Metodo que resume los resultados
    public static String resumirResultados(double notaDefinitiva, double notaMayor, double notaMenor, double notaRepetida, double[] notasOrdenadas, String clasificacion) {
        String resumen = "";
        JOptionPane.showMessageDialog(null, "La nota definitiva es " + notaDefinitiva +
                " \nla nota mayor es " +notaMayor+
                "\n la nota menor es " +notaMenor+
                "\n la nota que mas se repite es " +notaRepetida+
                "\n las notas ordenadas son: " + java.util.Arrays.toString(notasOrdenadas) +
                "\n la clasificacion de las notas es: \n" + clasificacion
        );
        return resumen;
    }
}