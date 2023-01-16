package com.github.zeros.engine.function;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.googlecode.aviator.runtime.function.AbstractFunction;

public abstract class JsonFunction extends AbstractFunction {

    enum JsonType {
        JSON_SIMPLE,
        JSON_OBJECT,
        JSON_ARRAY,
        JSON_EMPTY
    }

    public String convertJsonValue(JsonNode node, ObjectMapper objectMapper) {
        if (node.isContainerNode()) {
            return node.toString();
        } else {
            return objectMapper.convertValue(node, String.class);
        }
    }

    public static JsonType checkJsonType (String jsonStr) {
        if (jsonStr == null || jsonStr.length() == 0) {
            return JsonType.JSON_EMPTY;
        }
        int len = jsonStr.length();
        if (len < 2) {
            return JsonType.JSON_SIMPLE;
        }
        if (jsonStr.charAt(0) == '{' && jsonStr.charAt(len-1) == '}') {
            return JsonType.JSON_OBJECT;
        }
        if (jsonStr.charAt(0) == '[' && jsonStr.charAt(len-1) == ']') {
            return JsonType.JSON_ARRAY;
        }
        return JsonType.JSON_SIMPLE;
    }

    //TODO：补全基本类型
    public static boolean isSimpleValue (Object value) {
        Class<?> valueClass = value.getClass();
        return valueClass == Integer.class || valueClass == Long.class || valueClass == Float.class
                || valueClass == Double.class;
    }
}
