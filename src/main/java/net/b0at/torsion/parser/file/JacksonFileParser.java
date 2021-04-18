package net.b0at.torsion.parser.file;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import net.b0at.torsion.TorsionException;

import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.function.Consumer;

public abstract class JacksonFileParser<T> extends FileStorageParser<T> {
    private final String type;

    protected JacksonFileParser(Path file, String type) {
        super(file);
        this.type = type;
    }

    @Override
    public Optional<T> load(Class<? extends T> clazz) {
        try {
            if (!Files.exists(this.file) || (Files.size(this.file) == 0)) {
                return Optional.empty();
            }

            return Optional.ofNullable(this.createObjectMapper().readValue(Files.newBufferedReader(this.file), clazz));
        } catch (IOException exception) {
            throw new TorsionException("Failed to load " + this.type + " file!", exception);
        }
    }

    @Override
    public void save(T object) {
        try (Writer writer = Files.newBufferedWriter(this.file)) {
            this.createObjectMapper().writeValue(writer, object);
        } catch (IOException exception) {
            throw new TorsionException("Failed to save " + this.type + " file!", exception);
        }
    }

    protected abstract ObjectMapper createObjectMapper();

    protected static ObjectMapper constructObjectMapper(JsonFactory factory, Consumer<? super ObjectMapper> callback) {
        ObjectMapper objectMapper = new ObjectMapper(factory);

        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        objectMapper.registerModule(new Jdk8Module());
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.disable(MapperFeature.AUTO_DETECT_GETTERS, MapperFeature.AUTO_DETECT_IS_GETTERS);

        callback.accept(objectMapper);

        return objectMapper;
    }

    protected static ObjectMapper constructObjectMapper(JsonFactory factory) {
        return constructObjectMapper(factory, ignored -> { });
    }
}
