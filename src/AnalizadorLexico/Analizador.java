package AnalizadorLexico;

import java.util.ArrayList;

/**
 *
 * @author Dimas
 */
public class Analizador {

    ArrayList<Token> lista_token = new ArrayList();

    int contadorComillas = 0;

    public Analizador(ArrayList<Token> lista_token) {
        this.lista_token = lista_token;
    }

    public void analizar(String Cadena) {
        String[] letras = Cadena.split("\n");

        String lexema = "";
        int estado = 0;
        String tipo = "";
        int estadoA = 0;
        boolean comillas = false;

        for (int i = 0; i < letras.length; i++) {
            for (int j = 0; j < letras[i].length(); j++) {
                int letraActual, letraSiguiente = -2;
                letraActual = letras[i].codePointAt(j);
                //Estado Inicial
                if (estado == 0) {
                    estado = estado_transcicion(letraActual);
                }

                try {
                    letraSiguiente = letras[i].codePointAt(j + 1);
                } catch (Exception e) {
                    System.out.println("Error en el Analizador Lexico: " + e.getMessage());
                }

                switch (estado) {
                    //Cuando es 1 Es una letra minusculua o mayuscula
                    case 1:
                        lexema = lexema + letras[i].charAt(j);
                        if (comprobarLetra(letraSiguiente)) {
                            estado = 1;
                        } else {
                            //Cuando es un Identificador y contiene un numero
                            if (comprobarNumero(letraSiguiente)) {
                                estado = 1;
                            } else {
                                tipo = "IDENTIFICADOR";
                                estado = 10;
                                estadoA = 1;
                            }
                        }
                        break;
                    //Cuando es 2 Es un numero
                    case 2:
                        lexema = lexema + letras[i].charAt(j);
                        if (comprobarNumero(letraSiguiente)) {
                            estado = 2;
                        } else {
                            if (Integer.parseInt(lexema) >= -2147483647 && Integer.parseInt(lexema) <= 2147483647) {
                                tipo = "INT";
                            }
                            estado = 10;
                            estadoA = 2;
                        }
                        break;
                    //Cuando es una cadena
                    case 3:
                        if (contadorComillas == 1) { //Significa que estan abiertas
                            lexema = lexema + letras[i].charAt(j);
                            estado = 3;
                            tipo = "CADENA";
                        }

                        if (letraSiguiente == 34) { //Si se cierran
                            estado = 10;
                            lexema = lexema + letras[i].charAt(j + 1);
                        }

                        if (contadorComillas == 0) { // Si ya esta cerrado 
                            estado = 0;
                        }

                        break;
                    //Cuando es 20 es =
                    case 20:
                        lexema = lexema + letras[i].charAt(j);
                        tipo = "IGUAL";
                        estadoA = 20;
                        estado = 10;
                        contadorComillas = 0;
                        break;
                }
                //Si es un estado 10 guarda el token
                if (estado == 10) {
                    lista_token.add(new Token(lexema, i + 1, j + 1, tipo, estadoA));
                    lexema = "";
                    tipo = "";
                    estado = 0;
                    estadoA = 0;
                    letraActual = 0;

                }
            }
        }
    }

    private boolean comprobarLetra(int n) {
        if ((n > 96 && n < 123)
                || (n > 64 && n < 91)) {
            return true;
        }
        return false;
    }

    private boolean comprobarNumero(int n) {
        if (n >= 48 && n <= 57) {
            return true;
        }
        return false;
    }

    public int estado_transcicion(int n) {
        if ((n >= 97 && n <= 122) || (n >= 65 && n <= 90)) { //Estado letra
            return 1;
        } else if (n >= 48 && n <= 57) { //Estado Numero
            return 2;
        } else if (n == 61) { //Estado Igual
            return 20;
        } else if (n == 34) { //Comillas
            if (contadorComillas == 1) {
                contadorComillas = 0;
            } else {
                contadorComillas = 1;
            }
            return 3;
        }
        return 0;
    }
}
