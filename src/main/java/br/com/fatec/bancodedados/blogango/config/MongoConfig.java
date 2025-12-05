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
  @Value("${spring.data.mongodb.uri}")
  private String connectionString;

  @Override
  protected String getDatabaseName() {
    return "Blogango";
  }

  @Override
  public MongoClient mongoClient() {

    ConnectionString connString = new ConnectionString(connectionString);

    MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
            .applyConnectionString(connString)
            .build();

    return MongoClients.create(mongoClientSettings);
  }
}