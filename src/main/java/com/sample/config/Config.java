package com.sample.config;

import io.dropwizard.Configuration;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class Config extends Configuration {

    @NotNull
    private FileConfig fileConfig;
}
