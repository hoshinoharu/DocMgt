package com.rehoshi.docmgt.json;


import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.DateDeserializers;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SpDateDeSerializer extends DateDeserializers.DateDeserializer {
    @Override
    public Date deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String text = p.getText();
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(text) ;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null ;
    }
}
