package org.example;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

public class CodecoRegistry {
    public static void main(String args[])
    {
        //MongoClient mongoClient = new MongoClient();

        CodecRegistry pojoCodecRegistry = CodecRegistries.fromRegistries(MongoClient.getDefaultCodecRegistry(), CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build()));
        MongoClient cliente = new MongoClient("localhost", MongoClientOptions.builder().codecRegistry(pojoCodecRegistry).build());
        /*MongoDatabase db = mongoClient.getDatabase("basededatos");
        mongoClient.close();*/
        MongoDatabase db = cliente.getDatabase("basededatos");
        cliente.close();
    }
}
