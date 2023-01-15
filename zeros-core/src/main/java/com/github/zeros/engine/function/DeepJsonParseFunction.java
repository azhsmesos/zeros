package com.github.zeros.engine.function;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.zeros.engine.function.helperFunctionInterface.IAnalyzeJsonSubNodeFunction;
import com.googlecode.aviator.runtime.function.FunctionUtils;
import com.googlecode.aviator.runtime.type.AviatorObject;
import com.googlecode.aviator.runtime.type.AviatorRuntimeJavaElementType;
import com.googlecode.aviator.runtime.type.AviatorString;

import java.io.IOException;
import java.util.*;

// 懒加载解析json字符串，可以无限嵌套
public class DeepJsonParseFunction extends JsonFunction {

    @Override
    public String getName() {
        return "parseJsonDeep";
    }

    @Override
    public AviatorObject call(Map<String, Object> env, AviatorObject arg1) {
        String jsonStr = FunctionUtils.getStringValue(arg1, env);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonFunction.JsonType jsonType = checkJsonType(jsonStr);
            if (jsonType == JsonType.JSON_EMPTY) {
                return new AviatorString("");
            }

            //TODO:暂时不区分数字、布尔和字符串
            if (jsonType == JsonType.JSON_SIMPLE) {
                String val = objectMapper.readValue(jsonStr, String.class);
                return new AviatorString(val);
            }

            if (jsonType == JsonType.JSON_ARRAY) {
                List<Object> valueList = objectMapper.readValue(jsonStr, new TypeReference<>() {});
                LazyAviatorRuntimeList<Object> lazyList = newLazyAviatorRuntimeList(valueList, this::analyzeSubNode);
                return new AviatorRuntimeJavaElementType(AviatorRuntimeJavaElementType.ContainerType.Array, new LazyAviatorRuntimeList[]{lazyList}, 0,
                        () -> lazyList);
            }

            Map<String, Object> valueMap = objectMapper.readValue(jsonStr, new TypeReference<>() {});
            LazyAviatorRuntimeMap<String, Object> lazyMap = newLazyAviatorRuntimeMap(valueMap, this::analyzeSubNode);
            return new AviatorRuntimeJavaElementType(AviatorRuntimeJavaElementType.ContainerType.Array, new LazyAviatorRuntimeMap[]{lazyMap}, 0,
                    () -> lazyMap);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private Object analyzeSubNode(Object value) throws JsonProcessingException {
        // 如果是基本类型，那么不用继续解析了，直接返回
        if (JsonFunction.isSimpleValue(value)) {
            return value;
        }
        // value如果是map类的，前面肯定解析过了
        if (value instanceof Map) {
            if (value instanceof LazyAviatorRuntimeMap) {
                LazyAviatorRuntimeMap<String, Object> lazyMap = (LazyAviatorRuntimeMap<String, Object>) value;
                return lazyMap;
            }
            LazyAviatorRuntimeMap<String, Object> lazyMap = newLazyAviatorRuntimeMap((Map<String, Object>) value, this::analyzeSubNode);
            return lazyMap;
        }

        // 同样的，list类也是前面解析过了
        if (value instanceof List) {
            if (value instanceof LazyAviatorRuntimeList) {
                LazyAviatorRuntimeList lazyList = (LazyAviatorRuntimeList) value;
                return lazyList;
            }
            LazyAviatorRuntimeList<Object> lazyList = newLazyAviatorRuntimeList((List<Object>) value, this::analyzeSubNode);
            return lazyList;
        }

        String jsonStr = value.toString();
        JsonFunction.JsonType jsonType = checkJsonType(jsonStr);
        // 普通类型，不用转换
        if (jsonType == JsonType.JSON_SIMPLE) {
            return jsonStr;
        }

        ObjectMapper mapper = new ObjectMapper();

        // 列表类型，解析一层
        if (jsonType == JsonType.JSON_ARRAY) {
            List<Object> valueList = mapper.readValue(jsonStr, new TypeReference<>() {});
            return valueList;
        }
        // 需要解析下层
        Map<String, Object> valueMap = mapper.readValue(jsonStr, new TypeReference<>() {});
        return valueMap;
    }

    private LazyAviatorRuntimeMap<String, Object> newLazyAviatorRuntimeMap(Map<String, Object> valueMap, IAnalyzeJsonSubNodeFunction analyzeFunction) {
        LazyAviatorRuntimeMap<String, Object> lazyMap = new LazyAviatorRuntimeMap<>(valueMap);
        lazyMap.setAnalyzeFunction(analyzeFunction);
        return lazyMap;
    }

    private LazyAviatorRuntimeList<Object> newLazyAviatorRuntimeList(List<Object> valueList, IAnalyzeJsonSubNodeFunction analyzeFunction) {
        LazyAviatorRuntimeList<Object> lazyList = new LazyAviatorRuntimeList<>(valueList);
        lazyList.setAnalyzeFunction(analyzeFunction);
        return lazyList;
    }

    private class LazyAviatorRuntimeMap<K extends String, V extends Object> extends LinkedHashMap<String, Object> {
        LinkedHashMap<String,Object> analyzedMap = new LinkedHashMap<>();
        IAnalyzeJsonSubNodeFunction analyzeFunction;

        public LazyAviatorRuntimeMap(Map<? extends K, ? extends V> m) {
            super(m);
        }

        public void setAnalyzeFunction(IAnalyzeJsonSubNodeFunction f) {
            this.analyzeFunction = f;
        }

        @Override
        public Object get(Object key) {
            if (key.getClass() != String.class) {
                throw new IllegalArgumentException("LazyAviatorRuntimeMap::get illegal key type");
            }

            if (analyzeFunction == null) {
                return super.get(key);
            }

            String keyString = String.valueOf(key);
            Object value = analyzedMap.get(keyString);
            if (value != null) {
                return value;
            }
            try {
                Object originValue = super.get(key);
                value = analyzeFunction.analyzeSubNode(originValue);
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
            if (value != null) {
                analyzedMap.put(keyString, value);
            }
            return value;
        }
    }

    private class LazyAviatorRuntimeList<K extends Object> extends ArrayList<Object> {
        LinkedHashMap<Integer,Object> analyzedMap = new LinkedHashMap<>();
        IAnalyzeJsonSubNodeFunction analyzeFunction;

        public LazyAviatorRuntimeList(Collection<Object> c) {
            super(c);
        }

        public void setAnalyzeFunction(IAnalyzeJsonSubNodeFunction f) {
            this.analyzeFunction = f;
        }

        public Object get(Object index) {
            if (index.getClass() != Integer.class) {
                throw new IllegalArgumentException("LazyAviatorRuntimeList::get illegal key type");
            }
            return get((int) index);
        }

        @Override
        public Object get(int index) {

            if (analyzeFunction == null) {
                return super.get(index);
            }

            Object value = analyzedMap.get(index);
            if (value != null) {
                return value;
            }
            try {
                Object originValue = super.get(index);
                value = analyzeFunction.analyzeSubNode(originValue);
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
            if (value != null) {
                analyzedMap.put(index, value);
            }
            return value;
        }
    }
}
