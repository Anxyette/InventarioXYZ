package com.example.crudfirebase;
public class MainModel {
    String Titulo, Autor, Genero, Precio, Cantidad;

    public MainModel() {
    }

    public MainModel(String titulo, String autor, String genero, String precio, String cantidad) {
        Titulo = titulo;
        Autor = autor;
        Genero = genero;
        Precio = precio;
        Cantidad = cantidad;
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String titulo) {
        Titulo = titulo;
    }

    public String getAutor() {
        return Autor;
    }

    public void setAutor(String autor) {
        Autor = autor;
    }

    public String getGenero() {
        return Genero;
    }

    public void setGenero(String genero) {
        Genero = genero;
    }

    public String getPrecio() {
        return Precio;
    }

    public void setPrecio(String precio) {
        Precio = precio;
    }

    public String getCantidad() {
        return Cantidad;
    }

    public void setCantidad(String cantidad) {
        Cantidad = cantidad;
    }
}