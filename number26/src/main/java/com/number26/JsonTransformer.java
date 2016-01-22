package com.number26;

import com.fasterxml.jackson.databind.ObjectMapper;
import spark.ResponseTransformer;

import java.io.StringWriter;

/**
 * Transforms entities to json
 */
public class JsonTransformer implements ResponseTransformer {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String render(Object model) throws Exception {
        StringWriter writer = new StringWriter();
        objectMapper.writeValue(writer, model);
        return writer.toString();
    }
}
