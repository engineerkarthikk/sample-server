package com.sample.config;

import lombok.Data;
import javax.validation.constraints.NotNull;

@Data
public class FileConfig{

    @NotNull
    private String filePath;
}
