package org.convez.quarkus.redis.stream.extension;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.async.RedisAsyncCommands;
import io.lettuce.core.api.reactive.RedisReactiveCommands;
import io.lettuce.core.api.sync.RedisCommands;
import io.lettuce.core.protocol.RedisCommand;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import java.util.logging.Logger;

@ApplicationScoped
public class RedisStreamProducer {
  
  RedisStreamConfig config;
  
  RedisClient client;
  StatefulRedisConnection<String,String> connection;
  
  @PostConstruct
  private void safeConnect(){
    RedisURI redisURI = RedisURI.builder()
        .withHost(config.host)
        .withPort(config.port)
        .withPassword(config.password).build();
    client = RedisClient.create(redisURI);
    connection = client.connect();
  }
  
  @PreDestroy
  private void disconnect(){
    client.shutdown();
  }
  
  @Produces
  @ApplicationScoped
  public RedisCommands<String,String> produceClient(){
    return connection.sync();
  }
  
  @Produces
  @ApplicationScoped
  public RedisAsyncCommands<String,String> produceClientAsync(){
    return connection.async();
  }
  
  @Produces
  @ApplicationScoped
  public RedisReactiveCommands<String,String> produceClientReactive(){
    return connection.reactive();
  }
  
  
}
