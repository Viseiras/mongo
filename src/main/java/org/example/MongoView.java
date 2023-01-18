package org.example;

import com.mongodb.*;
import com.mongodb.MongoClient;
import com.mongodb.client.*;
import org.bson.codecs.configuration.*;
import org.bson.codecs.pojo.*;
import org.bson.*;
import java.util.*;
import org.bson.types.ObjectId;
import org.bson.codecs.pojo.annotations.*;
import com.mongodb.client.FindIterable;

public class MongoView {
    public static void main(String args[])
    {
        MongoClient mongoClient = new MongoClient();
        MongoDatabase db = mongoClient.getDatabase("biblioteca");

/*        CodecRegistry pojoCodecRegistry = CodecRegistries.fromRegistries(MongoClient.getDefaultCodecRegistry(),    CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build()));
        MongoClient cliente = new MongoClient("localhost", MongoClientOptions.builder().codecRegistry(pojoCodecRegistry).build());
        MongoDatabase db = cliente.getDatabase("biblioteca");*/
//MongoCollection mco = db.getCollection(...)

        // buscamos los libros de Pepe Gotera como autor
        Document documento = new Document("autor", "Pepe Gotera");
        // nos quedamos con uno, el primero
        FindIterable findIterable = db.getCollection("libros").find(documento).limit(1);
        Libro libro = null;
        // Más adelante, cuando hablemos de búsquedas, podremos hacer esto de manera directa, sin iteraciones
        MongoCursor iter = findIterable.iterator();
        if (iter.hasNext()) {
            // guardamos su id
            documento = (Document)iter.next();
            libro = new Libro();
            libro.setId(documento.getLong("_id"));
        }

        Libro libro2 = new Libro("A sangre fría","Historia de un asesinato múltiple","Truman Capote",(new GregorianCalendar(102 + 1900, 5, 2)).getTime(),true);
        // damos valores nuevos a ese id con el método replaceOne
        // REPLACEONE() TAMBIÉN TIENE 2 MÉTODOS, EL DE FILTRADO Y EL DE ACTUALIZACIÓN
        db.getCollection("libros").replaceOne(new Document("_id", libro.getId()), new Document()
                .append("titulo", libro2.getTitulo())
                .append("descripcion", libro2.getDescripcion())
                .append("autor", libro2.getAutor())
                .append("fecha", libro2.getFecha())
                .append("disponible", libro2.getDisponible()));
        mongoClient.close();
    }
}
