package org.example;

import com.mongodb.MongoClient;
import com.mongodb.client.*;
import org.bson.*;
import java.util.*;
import org.bson.types.ObjectId;
import org.bson.codecs.pojo.annotations.*;
import com.mongodb.client.FindIterable;

public class InsertaOtro {
    public static void main(String args[])
    {
        MongoClient mongoClient = new MongoClient();
        MongoDatabase db = mongoClient.getDatabase("biblioteca");

/*        CodecRegistry pojoCodecRegistry = CodecRegistries.fromRegistries(MongoClient.getDefaultCodecRegistry(),    CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build()));
        MongoClient cliente = new MongoClient("localhost", MongoClientOptions.builder().codecRegistry(pojoCodecRegistry).build());
        MongoDatabase db = cliente.getDatabase("biblioteca");*/
        // DEFINIMOS EL DOCUMENTO DE FILTRADO
        Document documento = new Document("autor", "Robert Louis Stevensonia");
        // UNA CONSULTA RETORNA UN OBJETO FINDITERABLE
        FindIterable findIterable = db.getCollection("libros").find(documento)/*.limit(10)*/;
        List<Libro> libros = new ArrayList<Libro>();
        Libro libro = null;
        // PARA PODER ITERAR CON ÉL USAMOS EL MÉTODO ITERATOR() QUE RETORNA UN CURSOR MONGOCURSOR
        MongoCursor iter = findIterable.iterator();
        while (iter.hasNext()) {
            // SOBRE EL MONGOCURSOR NEXT() RETORNA EL SIGUIENTE DOCUMENT
            documento = (Document)iter.next();
            libro = new Libro();
            libro.setId(documento.getObjectId("_id"));
            libro.setTitulo(documento.getString("titulo"));
            libro.setDescripcion(documento.getString("descripcion"));
            libro.setAutor(documento.getString("autor"));
            libro.setFecha(documento.getDate("fecha"));
            libro.setDisponible(documento.getBoolean("disponible", false));
            libros.add(libro);
        }
        // LOS MUESTRO CON EL MÉTODO FOREACH() CONVIRTIENDO A STREAM
        // TAMBIÉN PODÍA HABERLO HECHO CON UN BUCLE FOR (FOREACH) Y LOS MÉTODOS HASNEXT() Y NEXT()
        libros.stream().forEach(System.out::println);
        mongoClient.close();
    }
}
