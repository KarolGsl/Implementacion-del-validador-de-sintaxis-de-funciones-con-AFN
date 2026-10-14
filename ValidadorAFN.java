import java.util.ArrayList;
import java.util.Scanner;

public class ValidadorAFN {

    public static boolean esLetra(char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || c == '_';
    }

    public static boolean esAlfanumerico(char c) {
        return esLetra(c) || (c >= '0' && c <= '9');
    }

    public static boolean validarFuncion(String cadena) {
        ArrayList<Integer> estados = new ArrayList<>();
        estados.add(0);

        for (int i = 0; i < cadena.length(); i++) {
            char c = cadena.charAt(i);
            ArrayList<Integer> siguientes = new ArrayList<>();

            for (int j = 0; j < estados.size(); j++) {
                int estado = estados.get(j);

                switch (estado) {
                    case 0:
                        if (esLetra(c)) {
                            if (!siguientes.contains(1)) siguientes.add(1);
                        } else if (c == ' ' || c == '\t') {
                            if (!siguientes.contains(0)) siguientes.add(0);
                        }
                        break;

                    case 1:
                        if (esAlfanumerico(c)) {
                            if (!siguientes.contains(1)) siguientes.add(1);
                        }
                        if (c == ' ' || c == '\t') {
                            if (!siguientes.contains(2)) siguientes.add(2);
                        }
                        break;

                    case 2:
                        if (c == ' ' || c == '\t') {
                            if (!siguientes.contains(2)) siguientes.add(2);
                        } else if (esLetra(c)) {
                            if (!siguientes.contains(3)) siguientes.add(3);
                        }
                        break;

                    case 3:
                        if (esAlfanumerico(c)) {
                            if (!siguientes.contains(3)) siguientes.add(3);
                        }
                        if (c == '(') {
                            if (!siguientes.contains(4)) siguientes.add(4);
                        } else if (c == ' ' || c == '\t') {
                            if (!siguientes.contains(3)) siguientes.add(3);
                        }
                        break;

                    case 4:
                        if (c == ' ' || c == '\t') {
                            if (!siguientes.contains(4)) siguientes.add(4);
                        } else if (c == ')') {
                            if (!siguientes.contains(9)) siguientes.add(9);
                        } else if (esLetra(c)) {
                            if (!siguientes.contains(5)) siguientes.add(5);
                        }
                        break;

                    case 5:
                        if (esAlfanumerico(c)) {
                            if (!siguientes.contains(5)) siguientes.add(5);
                        }
                        if (c == ' ' || c == '\t') {
                            if (!siguientes.contains(6)) siguientes.add(6);
                        }
                        break;

                    case 6:
                        if (c == ' ' || c == '\t') {
                            if (!siguientes.contains(6)) siguientes.add(6);
                        } else if (esLetra(c)) {
                            if (!siguientes.contains(7)) siguientes.add(7);
                        }
                        break;

                    case 7:
                        if (esAlfanumerico(c)) {
                            if (!siguientes.contains(7)) siguientes.add(7);
                        }
                        if (c == ',') {
                            if (!siguientes.contains(8)) siguientes.add(8);
                        } else if (c == ')') {
                            if (!siguientes.contains(9)) siguientes.add(9);
                        } else if (c == ' ' || c == '\t') {
                            if (!siguientes.contains(7)) siguientes.add(7);
                        }
                        break;

                    case 8:
                        if (c == ' ' || c == '\t') {
                            if (!siguientes.contains(8)) siguientes.add(8);
                        } else if (esLetra(c)) {
                            if (!siguientes.contains(5)) siguientes.add(5);
                        }
                        break;

                    case 9:
                    case 10:
                        if (c == ' ' || c == '\t') {
                            if (!siguientes.contains(10)) siguientes.add(10);
                        } else if (c == ';') {
                            if (!siguientes.contains(11)) siguientes.add(11);
                        } else if (c == '{') {
                            if (!siguientes.contains(12)) siguientes.add(12);
                        }
                        break;

                    case 12:
                    case 13:
                        if (c == '}') {
                            if (!siguientes.contains(14)) siguientes.add(14);
                        } else {
                            if (!siguientes.contains(13)) siguientes.add(13);
                        }
                        break;

                    case 14:
                        if (c == ' ' || c == '\t') {
                            if (!siguientes.contains(14)) siguientes.add(14);
                        }
                        break;
                }
            }

            estados = siguientes;
            if (estados.isEmpty()) {
                return false;
            }
        }

        return estados.contains(11) || estados.contains(14);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese la funcion a validar: ");
        String linea = sc.nextLine();

        if (validarFuncion(linea)) {
            System.out.println("La cadena es valida.");
        } else {
            System.out.println("La cadena NO es valida.");
        }

        sc.close();
    }
}