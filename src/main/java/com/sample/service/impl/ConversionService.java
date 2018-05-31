package com.sample.service.impl;

import com.sample.bean.ConversionResponse;
import com.sample.dao.IConversionDAO;
import com.sample.service.IConversionService;
import lombok.extern.slf4j.Slf4j;

import javax.ws.rs.NotFoundException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

@Slf4j
public class ConversionService implements IConversionService{


    private IConversionDAO conversionDAO;

    public ConversionService(IConversionDAO conversionDAO) throws IOException {
        this.conversionDAO=conversionDAO;
    }

    @Override
    public ConversionResponse getNumberToWords(Integer value) throws NotFoundException {
        return conversionDAO.getKey(value);
    }
}
