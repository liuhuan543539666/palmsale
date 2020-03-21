package com.guoanfamily.palmsale.common.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.Date;

/**
 * Created by yangshaodong on 2017/4/19.
 * 序列化时间为 yyyy-MM-dd HH:mm
 */
public class CustomTimeToMinuteSerializer extends JsonSerializer<Date> {

    @Override
    public void serialize(Date date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        jsonGenerator.writeString(DateUtils.format(date,DateUtils.YMDHM_PATTERN));
    }
}
