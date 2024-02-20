package ejercicio;

import java.net.URI;
import java.net.URISyntaxException;

import org.bson.Document;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoMain {

	public static void main(String[] args) throws URISyntaxException {
		URI uri = new URI("mongodb://localhost:2701");

		MongoClient mongoClient = MongoClients.create();

		for (String dbName : mongoClient.listDatabaseNames()) {
			System.out.println(dbName);
		}
		MongoDatabase dbMonguer = mongoClient.getDatabase("hola");
		MongoCollection<Document> collectionMonguers = dbMonguer.getCollection("Pedrito");

		for (Document documentos : dbMonguer.listCollections()) {
			System.out.println(documentos.toJson());

		}

		for (Document name : collectionMonguers.find()) {
			System.out.println(name.toJson());
		}

	}

}
