package ejercicio;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.MongoNamespace;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;


public class MongoMain {

	public static void main(String[] args) throws URISyntaxException {
		URI uri = new URI("mongodb://localhost:2701");

		MongoClient mongoClient = MongoClients.create();
		MongoDatabase dbAdmin = mongoClient.getDatabase("admin");
		
	
		
		
		MongoDatabase dbMonguer = mongoClient.getDatabase("hola");
		MongoCollection<Document> collectionEmpleados = dbMonguer.getCollection("Empleados");
		


		mongoClient.close();
	}
	
	
	
	public void recorrerElementos(MongoClient mongoClient,MongoDatabase dbMonguer,MongoCollection<Document> collectionEmpleados) {
		
		  for (String dbName : mongoClient.listDatabaseNames()) {
		  System.out.println(dbName); }
		  
		 for (Document documentos : dbMonguer.listCollections()) {
		  System.out.println(documentos.toJson());
		  
		  }
		  
		  for (Document name : collectionEmpleados.find()) {
		  System.out.println(name.toJson()); }
		 
	}

	public void renombrarDocumento(MongoDatabase dbMonguer) {
		MongoNamespace oldName = new MongoNamespace(dbMonguer.getName(), "Pedrito");
		MongoNamespace newName = new MongoNamespace(dbMonguer.getName(), "Empleados");

		dbMonguer.getCollection("Pedrito").renameCollection(newName);

	}

}
