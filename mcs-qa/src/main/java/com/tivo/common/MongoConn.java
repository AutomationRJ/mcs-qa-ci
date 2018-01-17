package com.tivo.common;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.BSON;
import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.Document;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.joda.time.DateTime;

import java.util.Date;

/**
 * Created by rjaiswal on 5/2/2017.
 */
public class MongoConn {

    public MongoDatabase db(String host, String database){
        CodecRegistry codecRegistry = CodecRegistries.fromRegistries(CodecRegistries.fromCodecs(new DateTimeCodec()),MongoClient.getDefaultCodecRegistry());
        MongoClientOptions options = MongoClientOptions.builder().codecRegistry(codecRegistry).build();
        MongoClient mongo = new MongoClient(host, options);
        MongoDatabase db = mongo.getDatabase(database);
        return db;
    }

    public MongoCollection<Document> mongoJDBC(String host, String database, String collection){
        CodecRegistry codecRegistry = CodecRegistries.fromRegistries(CodecRegistries.fromCodecs(new DateTimeCodec()),MongoClient.getDefaultCodecRegistry());
        MongoClientOptions options = MongoClientOptions.builder().codecRegistry(codecRegistry).build();
        MongoClient mongo = new MongoClient(host, options);
        MongoDatabase db = mongo.getDatabase(database);
        MongoCollection<Document> coll = db.getCollection(collection);
        return coll;
        //Document findQuery = coll.find(exists("d_movieType",true)).first();
    }
}
