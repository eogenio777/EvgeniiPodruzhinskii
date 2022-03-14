package com.epam.tc.hw5.cucumber.context;

import java.util.HashMap;
import java.util.Map;

public final class TestContext {
    private static final Map<String, Object> context = new HashMap<>();

    private static TestContext instance;

    private TestContext() {

    }

    public void setObject(final String key, final Object object) {
        context.put(key, object);
    }

    public <T> T getObject(final String key, Class<T> clazz) {
        return clazz.cast(context.get(key));
    }

    public void cleanContext() {
        context.clear();
        instance = null;
    }

    public static TestContext getInstance() {
        if (instance == null) {
            instance = new TestContext();
        }
        return instance;
    }
}
