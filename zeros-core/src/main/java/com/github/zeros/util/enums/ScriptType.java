package com.github.zeros.util.enums;

/**
 * @author zhaozhenhang <zhaozhenhang@kuaishou.com>
 * Created on 2023-01-10
 */
public enum ScriptType {

    GROOVY(1, "groovy"),
    AVIATOR(2, "aviator");

    private int id;
    private String type;

    ScriptType(int id, String type) {
        this.type = type;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }
}
