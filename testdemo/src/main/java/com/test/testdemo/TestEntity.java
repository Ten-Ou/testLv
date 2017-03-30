package com.test.testdemo;

/**
 * Created by Oshx on 2017/3/30.
 */

public class TestEntity {
    private String content;
    private String action;
    private String functionFlag;

    public TestEntity(String content, String action) {
        this.content = content;
        this.action = action;
    }

    public TestEntity(String content, String action, String functionFlag) {
        this.content = content;
        this.action = action;
        this.functionFlag = functionFlag;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getFunctionFlag() {
        return functionFlag;
    }

    public void setFunctionFlag(String functionFlag) {
        this.functionFlag = functionFlag;
    }

    @Override
    public String toString() {
        return "TestEntity{" +
                "content='" + content + '\'' +
                ", action='" + action + '\'' +
                ", functionFlag='" + functionFlag + '\'' +
                '}';
    }
}


