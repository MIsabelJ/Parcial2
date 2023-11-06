import java.util.InputMismatchException;

public class MutantDetector {
    public static void main(String[] args) {
        /* Definimos la matriz de ADN como un arreglo de Strings. A modo de prueba, se
         muestran 6 secuencias de ADN de 6 caracteres. Para probar cada una se debe
         descomentar la línea correspondiente (una sola por vez).*/

        /*String[] dna = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};*/
        String[] dna = {"ATGCAA", "CAGTGC", "TTATAT", "AGAACG", "GTCCCC", "TCACTG"};
        /*String[] dna = {"ATGCAA","CAGTGC","TTATAT","AGAACG","GTCCCC","TCACTG"};*/
        /*String[] dna = {"ATGCTA","CAGTGC","TTATAC","AGTACG","GTACCC","TCACTG"};*/
        /*String[] dna = {"ATGCAA","CAGTGC","TTATTT","AGATCG","GACCCC","TCACTG"};*/
        /*String[] dna = {"ATGCAT", "CAGTGC", "TTAGTC", "AGAACT", "GTCTCC", "TCACTG"};*/

        // Llamamos al método para imprimir la matriz con un mensaje
        printMatrix("Secuencia a analizar: ", dna);

        // Variable para almacenar el resultado de la detección de mutantes
        boolean resultado = false;

        try {
            // Intentamos detectar si el humano es mutante
            resultado = isMutant(dna);
        } catch (InputMismatchException e) {
            // Capturamos una excepción si se ingresan caracteres no válidos
            System.out.println("Error: Se ingresaron caracteres no válidos en la matriz de ADN.");
            return; // Salimos del programa si hay un error en la entrada.
        }

        if (resultado) {
            System.out.println("El humano es un mutante.");
        } else {
            System.out.println("El humano no es un mutante.");
        }
    }

    // Método para imprimir la matriz con un mensaje
    public static void printMatrix(String message, String[] dna) {
        System.out.println(message);
        for (String row : dna) {
            System.out.println(row);
        }
    }

    // Método para detectar si el humano es mutante
    public static boolean isMutant(String[] dna) {
        int contadorSecuencias = 0; // Inicializamos el contador de secuencias encontradas
        int longitudMatriz = dna.length; // Obtenemos la longitud de la matriz

        // Verificar la longitud de cada fila
        for (String row : dna) {
            if (row.length() != 6) {
                throw new InputMismatchException("Las filas de la matriz de ADN deben tener una longitud de 6 " +
                        "caracteres.");
            }
            for (int j = 0; j < longitudMatriz; j++) {
                if (!isValidDNACharacter(row.charAt(j))) {
                    throw new InputMismatchException("Caracter no válido en la matriz de ADN.");
                }
            }
        }

        // Verificar secuencias horizontales
        for (int i = 0; i < longitudMatriz; i++) {
            for (int j = 0; j <= longitudMatriz - 4; j++) {
                if (dna[i].charAt(j) == dna[i].charAt(j + 1) &&
                        dna[i].charAt(j + 1) == dna[i].charAt(j + 2) &&
                        dna[i].charAt(j + 2) == dna[i].charAt(j + 3)) {
                    contadorSecuencias++;
                }
            }
        }

        // Verificar secuencias verticales
        for (int i = 0; i <= longitudMatriz - 4; i++) {
            for (int j = 0; j < longitudMatriz; j++) {
                if (dna[i].charAt(j) == dna[i + 1].charAt(j) &&
                        dna[i + 1].charAt(j) == dna[i + 2].charAt(j) &&
                        dna[i + 2].charAt(j) == dna[i + 3].charAt(j)) {
                    contadorSecuencias++;
                }
            }
        }

        // Verificar secuencias diagonales
        for (int i = 0; i <= longitudMatriz - 4; i++) {
            for (int j = 0; j <= longitudMatriz - 4; j++) {
                if (dna[i].charAt(j) == dna[i + 1].charAt(j + 1) &&
                        dna[i + 1].charAt(j + 1) == dna[i + 2].charAt(j + 2) &&
                        dna[i + 2].charAt(j + 2) == dna[i + 3].charAt(j + 3)) {
                    contadorSecuencias++;
                }
            }
        }

        // Verificar secuencias diagonales invertidas
        for (int i = 0; i <= longitudMatriz - 4; i++) {
            for (int j = 3; j < longitudMatriz; j++) {
                if (dna[i].charAt(j) == dna[i + 1].charAt(j - 1) &&
                        dna[i + 1].charAt(j - 1) == dna[i + 2].charAt(j - 2) &&
                        dna[i + 2].charAt(j - 2) == dna[i + 3].charAt(j - 3)) {
                    contadorSecuencias++;
                }
            }
        }

        return contadorSecuencias >= 2;
    }

    // Método para verificar si un carácter es válido en el ADN
    private static boolean isValidDNACharacter(char character) {
        return character == 'A' || character == 'T' || character == 'C' || character == 'G';
    }
}
