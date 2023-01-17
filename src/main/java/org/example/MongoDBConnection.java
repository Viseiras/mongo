package org.example;
import com.mongodb.MongoClient;
import com.mongodb.client.*;

public class MongoDBConnection {
    public static void main(String[] args) {
        MongoClient mongoClient = new MongoClient();
        MongoDatabase db = mongoClient.getDatabase("biblioteca");
        mongoClient.close();
    }
}
