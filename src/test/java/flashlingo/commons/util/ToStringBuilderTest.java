package flashlingo.commons.util;

/**
 * Builds a string representation of an object that is suitable as the return value of {@link Object#toString()}.
 */
public class ToStringBuilderTest {
    private static final String OBJECT_PREFIX = "{";
    private static final String OBJECT_SUFFIX = "}";
    private static final String FIELD_SEPARATOR = ", ";
    private static final String FIELD_NAME_VALUE_SEPARATOR = "=";

    private final StringBuilder stringBuilder = new StringBuilder();
    private boolean hasField;

    /**
     * Constructs a {@code ToStringBuilderTest} whose formatted output will be prefixed with {@code objectName}.
     */
    public ToStringBuilderTest(String objectName) {
        stringBuilder.append(objectName).append(OBJECT_PREFIX);
    }

    /**
     * Constructs a {@code ToStringBuilderTest} whose formatted output will be prefixed with the
     * canonical class name of {@code object}.
     */
    public ToStringBuilderTest(Object object) {
        this(object.getClass().getCanonicalName());
    }

    /**
     * Adds a field name/value pair to the output string.
     *
     * @param fieldName The name of the field.
     * @param fieldValue The value of the field.
     * @return A reference to this {@code ToStringBuilderTest} object, allowing method calls to be chained.
     */
    public ToStringBuilderTest add(String fieldName, Object fieldValue) {
        if (hasField) {
            stringBuilder.append(FIELD_SEPARATOR);
        }
        stringBuilder.append(fieldName).append(FIELD_NAME_VALUE_SEPARATOR).append(fieldValue);
        hasField = true;
        return this;
    }

    /**
     * Returns the built formatted string representation.
     */
    @Override
    public String toString() {
        return stringBuilder.toString() + OBJECT_SUFFIX;
    }
}
