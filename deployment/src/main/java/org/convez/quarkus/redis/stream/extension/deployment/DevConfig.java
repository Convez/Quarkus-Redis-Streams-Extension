package org.convez.quarkus.redis.stream.extension.deployment;

import io.quarkus.runtime.annotations.ConfigGroup;
import io.quarkus.runtime.annotations.ConfigItem;
import io.quarkus.runtime.annotations.ConfigRoot;

import java.util.Objects;
import java.util.Optional;

@ConfigRoot
public class DevConfig {
  
  @ConfigItem(defaultValue = "true")
  public boolean enabled;
  
  @ConfigItem
  public Optional<String> image;
  
  @ConfigItem
  public Optional<Integer> port;
  
  // Label used for container service discovery
  @ConfigItem(defaultValue = "redis")
  public String containerLabel;
  
  // When true, it creates a new container.
  // Otherwise it reuses an existing running container, if found.
  @ConfigItem(defaultValue = "false")
  public boolean createNew;
  

  @Override public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    DevConfig devConfig = (DevConfig)o;
    return enabled == devConfig.enabled && createNew == devConfig.createNew && image.equals(devConfig.image)
        && port.equals(devConfig.port) && containerLabel.equals(devConfig.containerLabel);
  }

  @Override public int hashCode() {
    return Objects.hash(enabled, image, port, createNew, containerLabel);
  }
}
