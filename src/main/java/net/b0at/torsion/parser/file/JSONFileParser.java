package net.b0at.torsion.parser.file;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import net.b0at.torsion.TorsionException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

public class JSONFileParser<T> extends FileStorageParser<T> {
    private static boolean serializeNulls;
    private static boolean lenient;

    public JSONFileParser(Path file) {
        super(file);
    }

    @Override
    public Optional<T> load(Class<? extends T> clazz) {
        try {
            if (!Files.exists(this.file) || (Files.size(this.file) == 0)) {
                return Optional.empty();
            }

            ObjectMapper objectMapper = constructObjectMapper();

            return Optional.ofNullable(objectMapper.readValue(Files.newBufferedReader(this.file), clazz));
        } catch (IOException exception) {
            throw new TorsionException("Failed to load JSON file!", exception);
        }
    }

    @Override
    public void save(T object) {
        try (Writer writer = Files.newBufferedWriter(this.file)) {
            constructObjectMapper().writeValue(writer, object);
        } catch (IOException exception) {
            throw new TorsionException("Failed to save JSON file!", exception);
        }
    }

    private static ObjectMapper constructObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();

        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        if (JSONFileParser.serializeNulls) {
            objectMapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);
        } else {
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        }

        return objectMapper;
    }

    public static void setSerializeNulls(boolean serializeNulls) {
        JSONFileParser.serializeNulls = serializeNulls;
    }
}
