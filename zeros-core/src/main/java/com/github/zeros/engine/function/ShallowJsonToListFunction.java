package com.github.zeros.engine.function;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.googlecode.aviator.runtime.function.FunctionUtils;
import com.googlecode.aviator.runtime.type.AviatorObject;
import com.googlecode.aviator.runtime.type.AviatorRuntimeJavaElementType;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ShallowJsonToListFunction extends JsonFunction {
    @Override
    public AviatorObject call(Map<String, Object> env, AviatorObject arg1) {
        String jsonStr = FunctionUtils.getStringValue(arg1, env);
        ObjectMapper objectMapper = new ObjectMapper();
        List<Object> values;
        try {
            //默认情况下，jackson解析两层嵌套，所以需要手动把被解析的第二层转回来
            List<JsonNode> jsonNodes = objectMapper.readValue(jsonStr, new TypeReference<List<JsonNode>>() {});
            values = jsonNodes.stream().map(node -> convertJsonValue(node, objectMapper)).collect(
                    Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        System.out.println("call JsonToListFunction, jsonStr: " + jsonStr + ", valueList:" + values);
        return new AviatorRuntimeJavaElementType(AviatorRuntimeJavaElementType.ContainerType.Array, new List[]{values}, 0,
                ()-> values);
    }

    public String getName() {
        return "jsonStrToList";
    }
}
