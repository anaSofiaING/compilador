/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AnalizadorLexico;

/**
 *
 * @author Dimas
 */
public class Token {

    private String lexema;
    private int numero_token;
    private int fila;
    private int columna;
    private String tipo;
    private int estado;

    public Token() {
    }

    public Token(String lexema, int fila, int columna, String tipo, int estado) {
        this.lexema = lexema;
        this.numero_token = numero_token;
        this.fila = fila;
        this.columna = columna;
        this.tipo = tipo;
        this.estado = estado;
    }

    public String getLexema() {
        return lexema;
    }

    public void setLexema(String lexema) {
        this.lexema = lexema;
    }

    public int getNumero_token() {
        return numero_token;
    }

    public void setNumero_token(int numero_token) {
        this.numero_token = numero_token;
    }

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "[" + tipo + ", " + lexema + ", " + fila + ", " + columna + "]";
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

}
