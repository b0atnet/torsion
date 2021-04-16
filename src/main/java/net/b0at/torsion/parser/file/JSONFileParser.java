package net.b0at.torsion.parser.file;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
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
    public Optional<T> load(Class<T> clazz) {
        try {
            if (!Files.exists(this.file) || (Files.size(this.file) == 0)) {
                return Optional.empty();
            }

            Gson gson = constructGsonBuilder();

            JsonReader reader = new JsonReader(Files.newBufferedReader(this.file));
            reader.setLenient(true);

            return Optional.ofNullable(gson.fromJson(reader, clazz));
        } catch (IOException exception) {
            throw new TorsionException("Failed to load JSON file!", exception);
        }
    }

    @Override
    public void save(T object) {
        try (Writer writer = Files.newBufferedWriter(this.file)) {
            constructGsonBuilder().toJson(object, writer);
        } catch (IOException exception) {
            throw new TorsionException("Failed to save JSON file!", exception);
        }
    }

    private static Gson constructGsonBuilder() {
        GsonBuilder builder = new GsonBuilder();

        builder.setPrettyPrinting();

        if (JSONFileParser.serializeNulls) {
            builder.serializeNulls();
        }

        if (JSONFileParser.lenient) {
            builder.setLenient();
        }

        return builder.create();
    }

    public static void setSerializeNulls(boolean serializeNulls) {
        JSONFileParser.serializeNulls = serializeNulls;
    }

    public static void setLenient(boolean lenient) {
        JSONFileParser.lenient = lenient;
    }
}
