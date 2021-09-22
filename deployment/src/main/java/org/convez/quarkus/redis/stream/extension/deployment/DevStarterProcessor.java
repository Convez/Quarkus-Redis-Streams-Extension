package org.convez.quarkus.redis.stream.extension.deployment;

import io.quarkus.deployment.IsDockerWorking;
import io.quarkus.deployment.IsNormal;
import io.quarkus.deployment.annotations.BuildStep;
import io.quarkus.deployment.dev.devservices.GlobalDevServicesConfig;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.utility.DockerImageName;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

public class DevStarterProcessor {
  private static final Logger LOGGER = Logger.getLogger(DevStarterProcessor.class.getName());
  private static int REDIS_DEFAULT_PORT = 6379;
  private static String REDIS_DEFAULT_IMAGE = "redis:6.2-alpine";
  private List<RedisContainer> startedContainers = new LinkedList<>();
  
  private class RedisContainer {
    String connectionString;
    GenericContainer container;
    public RedisContainer(String connectionString, GenericContainer container) {
      this.connectionString = connectionString;
      this.container = container;
    }
  }
  
  @BuildStep(onlyIfNot = IsNormal.class, onlyIf = { GlobalDevServicesConfig.Enabled.class })
  public void startContainer(DevConfig config){
    if(!config.enabled) {
      return;
    }
    Boolean dockerRunning = new IsDockerWorking.IsDockerRunningSilent().getAsBoolean();
    if(!dockerRunning){
      return;
    }
    DockerImageName dockerImageName = DockerImageName
        .parse(config.image.orElse(REDIS_DEFAULT_IMAGE))
        .asCompatibleSubstituteFor(REDIS_DEFAULT_IMAGE);
  }
  
  
}

