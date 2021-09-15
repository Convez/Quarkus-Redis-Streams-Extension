package org.convez.quarkus.redis.stream.extension.deployment;

import io.quarkus.arc.deployment.AdditionalBeanBuildItem;
import io.quarkus.arc.deployment.BeanContainerBuildItem;
import io.quarkus.deployment.annotations.BuildStep;
import io.quarkus.deployment.builditem.FeatureBuildItem;
import org.convez.quarkus.redis.stream.extension.RedisStreamProducer;

class QuarkusRedisStreamExtensionProcessor {

    private static final String FEATURE = "quarkus-redis-stream-extension";

    @BuildStep
    FeatureBuildItem feature() {
        return new FeatureBuildItem(FEATURE);
    }
    
    @BuildStep
    AdditionalBeanBuildItem createRedisProducer() {
        return new AdditionalBeanBuildItem(RedisStreamProducer.class);
    }
}
