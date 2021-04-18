package net.b0at.torsion.parser.file;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.file.Path;

public class JSONFileParser<T> extends JacksonFileParser<T> {
    private static boolean serializeNulls;

    public JSONFileParser(Path file) {
        super(file, "JSON");
    }

    @Override
    protected ObjectMapper createObjectMapper() {
        return JacksonFileParser.constructObjectMapper(new JsonFactory(), objectMapper -> {
            if (JSONFileParser.serializeNulls) {
                objectMapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);
            } else {
                objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            }
        });
    }

    public static void setSerializeNulls(boolean serializeNulls) {
        JSONFileParser.serializeNulls = serializeNulls;
    }
}
