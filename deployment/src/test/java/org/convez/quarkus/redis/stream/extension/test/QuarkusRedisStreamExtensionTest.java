package org.convez.quarkus.redis.stream.extension.test;

import ai.grakn.redismock.RedisServer;
import io.lettuce.core.XReadArgs;
import io.lettuce.core.api.sync.RedisCommands;
import org.convez.quarkus.redis.stream.extension.RedisStreamConfig;
import org.convez.quarkus.redis.stream.extension.RedisStreamProducer;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.RegisterExtension;

import io.quarkus.test.QuarkusUnitTest;

import javax.inject.Inject;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Logger;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class QuarkusRedisStreamExtensionTest {

  @RegisterExtension
  static final QuarkusUnitTest testConfig = new QuarkusUnitTest()
      .setArchiveProducer(()->ShrinkWrap.create(JavaArchive.class)
          .addClasses(RedisStreamProducer.class, RedisStreamConfig.class)
          .addAsResource("application.properties")
      );

  @Inject RedisStreamConfig redisConfig;

  @Inject RedisStreamProducer producer;
  RedisServer server;
  @BeforeAll
  void setupServer() throws IOException {
    server = RedisServer.newRedisServer(redisConfig.port);
    server.start();
  }
  @AfterAll
  void cleanupServer(){
    server.stop();
  }

  @Test
  public void testConnection(){
    RedisCommands<String,String> syncClient = producer.produceClient();
    Assertions.assertNotNull(syncClient);
  }

}
