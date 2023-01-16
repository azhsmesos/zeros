package com.github.zeros.engine.function.helperFunctionInterface;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.googlecode.aviator.runtime.type.AviatorObject;

import java.util.Map;

@FunctionalInterface
public interface IAnalyzeJsonSubNodeFunction {
    Object analyzeSubNode(Object value) throws JsonProcessingException;
}
