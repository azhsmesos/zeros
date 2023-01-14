package com.github.zeros.engine.function;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.googlecode.aviator.runtime.function.FunctionUtils;
import com.googlecode.aviator.runtime.type.AviatorObject;
import com.googlecode.aviator.runtime.type.AviatorRuntimeJavaElementType;

import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;

// 解析json，但是嵌套字段被保留为String，即结果只有一层
public class ShallowJsonToMapFunction extends JsonFunction {
    @Override
    public AviatorObject call(Map<String, Object> env, AviatorObject arg1) {
        String jsonStr = FunctionUtils.getStringValue(arg1, env);
        ObjectMapper objectMapper = new ObjectMapper();
        Map<Object, String> valueMap;
        try {
            Map<String, JsonNode> jsonNodeMap = objectMapper.readValue(jsonStr, new TypeReference<>() {});
            valueMap = jsonNodeMap.entrySet().stream().collect(
                    Collectors.toMap(
                            Map.Entry::getKey,
                            e -> convertJsonValue(e.getValue(), objectMapper)
                    )
            );

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        System.out.println("call JsonToMapFunction, jsonStr: " + jsonStr + ", valueMap:" + valueMap);
        return new AviatorRuntimeJavaElementType(AviatorRuntimeJavaElementType.ContainerType.Array, new Map[]{valueMap}, 0,
               ()-> valueMap);
    }

    public String getName() {
        return "jsonStrToMap";
    }
}
