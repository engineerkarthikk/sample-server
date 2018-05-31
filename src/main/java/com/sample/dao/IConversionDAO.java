package com.sample.dao;

import com.sample.bean.ConversionResponse;

public interface IConversionDAO {

    ConversionResponse getKey(Integer value);
}
