package com.sample.service.impl;

import com.sample.bean.ConversionResponse;
import com.sample.service.IConversionService;
import lombok.extern.slf4j.Slf4j;

import javax.ws.rs.NotFoundException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

@Slf4j
public class ConversionService implements IConversionService{

    private final HashMap<Integer,String> conversionValue;

    public ConversionService(String filePath) throws IOException {
        this.conversionValue=loadFile(filePath);
    }

    private HashMap<Integer, String> loadFile(String filePath) throws IOException {
        HashMap<Integer,String> conversionValue = new HashMap<Integer, String>();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        String[] readKeyValue;
        boolean isHeader=true;
        int keyIndex=0,valueIndex=1;
        while ((line=reader.readLine())!=null) {
            readKeyValue = line.split(",");
            if(isHeader){
                if(!readKeyValue[0].equalsIgnoreCase("value")) {
                    keyIndex = 1;
                    valueIndex=0;
                }
                isHeader=false;
            }
            else
                conversionValue.put(Integer.parseInt(readKeyValue[keyIndex]),readKeyValue[valueIndex]);
        }
        return conversionValue;
    }

    @Override
    public ConversionResponse getNumberToWords(Integer value) throws NotFoundException {
        ConversionResponse response=new ConversionResponse();
        if(conversionValue.containsKey(value)){
            response.setKey(conversionValue.get(value));
            response.setValue(value);
            return response;
        }
        log.error("key not found with value: {}",value);
        throw new NotFoundException("key not found with value:"+value);
    }
}
