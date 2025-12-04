package br.com.fatec.bancodedados.blogango.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;

@Configuration
public class MongoConfig extends AbstractMongoClientConfiguration {
  @Value("${mongo.db.password}")
  private String password;

  @Value("${mongo.db.user}")
  private String user;

  @Override
  protected String getDatabaseName() {
    return "Blogango";
  }

  @Override
  public MongoClient mongoClient() {

    String uriTemplate = "mongodb+srv://%s:%s@cluster0.cp3qm.mongodb.net/Blogango?retryWrites=true&w=majority&appName=Cluster0";
    String finalConnectionString = String.format(uriTemplate, user, password);

    ConnectionString connectionString = new ConnectionString(finalConnectionString);

    MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
            .applyConnectionString(connectionString)
            .build();

    return MongoClients.create(mongoClientSettings);
  }
}