package by.chitatel.api.utils;

import java.util.HashMap;
import java.util.Map;

public abstract class FormParameters {
    protected Map<String, Object> formParams = new HashMap<>();

    public Map<String, Object> build() {
        return formParams;
    }
}
