package com.sample;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sample.config.Config;
import com.sample.dao.impl.ConversionDAO;
import com.sample.resource.ConversionResource;
import com.sample.service.impl.ConversionService;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class App extends Application<Config> {

    @Override
    public void initialize(Bootstrap<Config> bootstrap){
        super.initialize(bootstrap);
    }

    public void run(Config config, Environment environment) throws Exception {
        environment.getObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        environment.getObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL);


        final ConversionDAO conversionDAO=new ConversionDAO(config.getFileConfig().getFilePath());
        final ConversionService service=new ConversionService(conversionDAO);
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
