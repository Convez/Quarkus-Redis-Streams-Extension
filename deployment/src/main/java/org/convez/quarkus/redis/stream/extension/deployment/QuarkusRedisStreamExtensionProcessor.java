package org.convez.quarkus.redis.stream.extension.deployment;

import io.quarkus.deployment.annotations.BuildStep;
import io.quarkus.deployment.builditem.FeatureBuildItem;

class QuarkusRedisStreamExtensionProcessor {

    private static final String FEATURE = "quarkus-redis-stream-extension";

    @BuildStep
    FeatureBuildItem feature() {
        return new FeatureBuildItem(FEATURE);
    }

}
