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

public class MongoUpdate {
    public static void main(String args[])
    {
        MongoClient mongoClient = new MongoClient();
        MongoDatabase db = mongoClient.getDatabase("biblioteca");

/*        CodecRegistry pojoCodecRegistry = CodecRegistries.fromRegistries(MongoClient.getDefaultCodecRegistry(),    CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build()));
        MongoClient cliente = new MongoClient("localhost", MongoClientOptions.builder().codecRegistry(pojoCodecRegistry).build());
        MongoDatabase db = cliente.getDatabase("biblioteca");*/

    /* LOS MÉTODOS DE ACTUALIZACIÓN, COMO UPDATEONE, REQUIEREN 2 PARÁMETROS:
        1. EL DOCUMENTO DE FILTRADO
        2. EL DOCUMENTO DE ACTUALIZACIÓN */

        db.getCollection("libros").updateOne(new Document("titulo", "Secuestrado"), new Document("$set", new Document("autor", "Pepe Gotera")));
        mongoClient.close();
    }
}
