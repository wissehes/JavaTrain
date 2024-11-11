package nl.wissehes.javatrain.model.NDOV;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum NSBoolean {
    N,
    J;

    /**
     * Returns this as a normal boolean
     */
    @JsonValue
    public boolean toBoolean() {
        return this == J;
    }

    @JsonCreator
    public static NSBoolean fromString(String value) {
        return value.equals("J") ? J : N;
    }
}
