package org.convez.quarkus.redis.stream.extension;

import io.quarkus.runtime.annotations.ConfigItem;
import io.quarkus.runtime.annotations.ConfigRoot;
import io.quarkus.runtime.annotations.DefaultConverter;
import io.smallrye.common.constraint.Nullable;
import io.smallrye.config.ConfigMapping;
import io.smallrye.config.WithDefault;

import java.util.Optional;

@ConfigRoot(name = "redis")
public class RedisStreamConfig {
  /**
   * Enable redis stream connection
   */
  @ConfigItem(defaultValue = "true")
  @DefaultConverter
  public boolean enable;

  /**
   * Redis host address
   */
  @ConfigItem
  public String host;

  /**
   * Redis host port
   */
  @ConfigItem
  @DefaultConverter
  public Integer port;

  /**
   * Redis host password
   */
  @ConfigMapping
  public String password;
}
