package com.guoanfamily.palmsale.common.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.Date;

/**
 * Created by yangshaodong on 2017/4/19.
 * 序列化为  yyyy-MM-dd
 */
public class CustomDateSerializer extends JsonSerializer<Date> {
    /**
     * 用注解去序列化时间
     * @param date
     * @param jsonGenerator
     * @param serializerProvider
     * @throws IOException
     * @throws JsonProcessingException
     */
    @Override
    public void serialize(Date date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        jsonGenerator.writeString(DateUtils.format(date,DateUtils.YEAR_MONTH_DAY_PATTERN));
    }
}
