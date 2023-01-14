package com.github.zeros.engine.function;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.googlecode.aviator.runtime.function.AbstractFunction;

public abstract class JsonFunction extends AbstractFunction {

    public String convertJsonValue(JsonNode node, ObjectMapper objectMapper) {
        if (node.isContainerNode()) {
            return node.toString();
        } else {
            return objectMapper.convertValue(node, String.class);
        }
    }

}
