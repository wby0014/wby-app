package com.example.wby.common.interceptor;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

public class CustomJackson2HttpMessageConverter {

    public ObjectMapper builder() {
        return (new Jackson2ObjectMapperBuilder()).serializerByType(Date.class, new CustomJackson2HttpMessageConverter.CustomDateSerialize()).deserializerByType(Timestamp.class, new CustomJackson2HttpMessageConverter.CustomDateDeserialize()).build();
    }

    private static class CustomDateDeserialize extends JsonDeserializer<Timestamp> {
        private CustomDateDeserialize() {
        }

        @Override
        public Timestamp deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
            return Timestamp.valueOf(p.getText());
        }
    }

    private static class CustomDateSerialize extends JsonSerializer<Date> {
        private CustomDateSerialize() {
        }

        @Override
        public void serialize(Date value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {
            gen.writeString("");
        }
    }

}
