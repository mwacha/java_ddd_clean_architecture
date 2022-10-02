package tk.mwacha.helper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ParseJsonHelper {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    static {
        MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        MAPPER.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
        MAPPER.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        MAPPER.registerModule(new JavaTimeModule());
        MAPPER.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    public static <T> T toObject(String content, Class<T> valueType) throws IOException {
        try {
            return MAPPER.readValue(content, valueType);
        } catch (JsonProcessingException e) {
            log.error(
                    "PARSE ERROR TO OBJECT, CLASS {}, content {} : msg {}",
                    valueType,
                    content,
                    e.getMessage());
            throw new IOException(e.getMessage());
        }
    }

    public static String toJson(Object content) throws IOException {
        try {
            return MAPPER.writeValueAsString(content);
        } catch (JsonProcessingException e) {
            log.error(
                    "PARSE ERROR TO JSON, CLASS {}, content {} : msg {}", content.getClass(), e.getMessage());
            throw new IOException(e.getMessage());
        }
    }
}