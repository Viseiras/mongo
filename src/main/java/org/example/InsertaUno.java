package org.example;

import com.mongodb.*;
import com.mongodb.MongoClient;
import com.mongodb.client.*;
import org.bson.codecs.configuration.*;
import org.bson.codecs.pojo.*;

import java.util.*;
import org.bson.types.ObjectId;
import org.bson.codecs.pojo.annotations.*;

public class InsertaUno {
    public static void main(String args[])
    {
/*        MongoClient mongoClient = new MongoClient();
        MongoDatabase db = mongoClient.getDatabase("biblioteca");*/

        CodecRegistry pojoCodecRegistry = CodecRegistries.fromRegistries(MongoClient.getDefaultCodecRegistry(),    CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build()));
        MongoClient cliente = new MongoClient("localhost", MongoClientOptions.builder().codecRegistry(pojoCodecRegistry).build());
        MongoDatabase db = cliente.getDatabase("biblioteca");

        MongoCollection<Libro> coleccionLibros = db.getCollection("libros", Libro.class);

        Libro libro = new Libro("A sangre fría","Historia de un asesinato múltiple","Truman Capote",(new GregorianCalendar(102 + 1900, 5, 2)).getTime(),true);
 /*       Document documento = new Document()
.append("titulo", libro.getTitulo())
.append("descripcion", libro.getDescripcion())
.append("autor", libro.getAutor())
.append("fecha", libro.getFecha())
.append("disponible", libro.getDisponible());*/
        //db.getCollection("libros").insertOne(libro);
        coleccionLibros.insertOne(libro);

        cliente.close();
    }
}

