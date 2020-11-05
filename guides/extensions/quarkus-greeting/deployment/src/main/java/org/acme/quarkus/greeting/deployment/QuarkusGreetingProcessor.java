package org.acme.quarkus.greeting.deployment;

import io.quarkus.deployment.annotations.BuildStep;
import io.quarkus.deployment.builditem.FeatureBuildItem;
import io.quarkus.undertow.deployment.ServletBuildItem;
import org.acme.quarkus.greeting.GreetingServlet;

class QuarkusGreetingProcessor {

    private static final String FEATURE = "quarkus-greeting";

    @BuildStep
    FeatureBuildItem feature() {
        return new FeatureBuildItem(FEATURE);
    }

    @BuildStep
    ServletBuildItem createServlet() {
        return ServletBuildItem.builder("greeting", GreetingServlet.class.getName())
                    .addMapping("/greeting").build();
    }
}
