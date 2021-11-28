package com.clint.marvel.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
@UtilityClass
public class JsonUtil {

    private static ObjectMapper objectMapper = new ObjectMapper();
    private static final String ERROR_GET_OBJECT_FROM_JSON = "GetObjectFromJsonError";

    static {
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }

    public static ObjectMapper getObjectMapper(){
        return objectMapper;
    }

    public static String toJsonString(Object obj){
        try {
            return objectMapper.writeValueAsString(obj);
        } catch(JsonProcessingException ex){
            return "";
        }
    }

    public static String toJsonStringPretty(Object obj) {
        try {
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        } catch(JsonProcessingException ex) {
            log.error("toJsonStringPretty: error parsing to json", ex);
            return "";
        }
    }

    public static <T> T parseObject(String json, Class<T> clazz){
        try {
            return objectMapper.readValue(json, clazz);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static JsonNode getJsonNode(String json, String nodeName) throws JsonProcessingException{
        JsonNode rootNode = objectMapper.readTree(json);

        return rootNode.findValue(nodeName);
    }
}
