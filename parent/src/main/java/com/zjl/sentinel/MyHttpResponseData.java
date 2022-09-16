package com.zjl.sentinel;

public class MyHttpResponseData<T> {

    T body;

    Class<?> clazz;

    public MyHttpResponseData() {
    }

    public MyHttpResponseData(T body, Class<?> clazz) {
        this.body = body;
        this.clazz = clazz;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }

    public Class<?> getClazz() {
        return clazz;
    }

    public void setClazz(Class<?> clazz) {
        this.clazz = clazz;
    }
}
