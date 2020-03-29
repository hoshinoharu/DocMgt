package com.rehoshi.docmgt.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SpDateSerializer extends DateSerializer {
    @Override
    public void serialize(Date value, JsonGenerator g, SerializerProvider provider) throws IOException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        g.writeString(dateFormat.format(value));
    }
}
