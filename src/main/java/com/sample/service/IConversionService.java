package com.sample.service;

import com.sample.bean.ConversionResponse;

public interface IConversionService {

    ConversionResponse getNumberToWords(Integer value);
}
