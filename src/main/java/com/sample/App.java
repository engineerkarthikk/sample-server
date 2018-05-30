package com.sample;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sample.config.Config;
import com.sample.resource.ConversionResource;
import com.sample.service.ConversionService;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import minisu.dropwizard.interpolation.EnvironmentVariableInterpolationBundle;

public class App extends Application<Config> {

    @Override
    public void initialize(Bootstrap<Config> bootstrap){
        super.initialize(bootstrap);
        bootstrap.addBundle(new EnvironmentVariableInterpolationBundle());
    }

    public void run(Config config, Environment environment) throws Exception {
        environment.getObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        environment.getObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL);

        final ConversionService service=new ConversionService(config.getFileConfig().getFilePath());
        final ConversionResource resource=new ConversionResource(service);

        /*
        Register resources
         */
        environment.jersey().register(resource);

    }

    public static void main(String[] args) throws Exception {
        new App().run(args);
    }
}
