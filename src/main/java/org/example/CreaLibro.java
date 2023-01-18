package org.example;

import com.mongodb.MongoClient;
import com.mongodb.client.*;
import org.bson.*;
import java.util.*;
import org.bson.types.ObjectId;
import org.bson.codecs.pojo.annotations.*;

public class CreaLibro
{
    public static void main(String args[])
    {
        MongoClient mongoClient = new MongoClient();
        MongoDatabase db = mongoClient.getDatabase("biblioteca");

/*        CodecRegistry pojoCodecRegistry = CodecRegistries.fromRegistries(MongoClient.getDefaultCodecRegistry(),    CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build()));
        MongoClient cliente = new MongoClient("localhost", MongoClientOptions.builder().codecRegistry(pojoCodecRegistry).build());
        MongoDatabase db = cliente.getDatabase("biblioteca");*/

        Libro libro = new Libro("Secuestrado","Las aventuras de David Balfour","Robert Louis Stevenson",(new GregorianCalendar(102 + 1900, 5, 2)).getTime(),true);

        db.getCollection("libros").insertOne(new Document()
//.append("id", libro.getId())
                .append("titulo", libro.getTitulo())
                .append("descripcion", libro.getDescripcion())
                .append("autor", libro.getAutor())
                .append("fecha", libro.getFecha())
                .append("disponible", libro.getDisponible()));

        mongoClient.close();
    }
}

class Libro {

    public Long id;
    private String titulo;
    private String descripcion;
    private String autor;
    private Date fecha;
    private boolean disponible;
//    private Editorial editorial;

    public Libro(String t, String d, String a, Date f, boolean disp) {
        this.id=id;
        titulo=t; descripcion=d; autor=a; fecha=f; disponible=disp;
    }

    public Libro() {

    }
    public void setId(Long id) { this.id = id; }

    @BsonId // anotación necesaria por ser el atributo id private
    public Long getId() { return id; }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public boolean getDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

/*    public Editorial getEditorial() {
        return editorial;
    }

    public void setEditorial(Editorial editorial) {
        this.editorial = editorial;
    }*/
}
