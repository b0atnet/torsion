package net.b0at.torsion.parser.file;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import java.io.*;
import java.util.Optional;

public class JSONFileParser<T> extends FileStorageParser<T> {
    private static boolean serializeNulls;
    private static boolean lenient;

    public JSONFileParser(File file) {
        super(file);
    }

    @Override
    public Optional<T> load(Class<T> clazz) {
        try {
            Gson gson = constructGsonBuilder();
            JsonReader reader = new JsonReader(new FileReader(this.file));
            reader.setLenient(true);

            return Optional.ofNullable(gson.fromJson(reader, clazz));
        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public void save(T object) {
        try {
            Writer writer = new FileWriter(this.file);
            Gson gson = constructGsonBuilder();
            gson.toJson(object, writer);
            writer.close();
        } catch (IOException exception) {
            exception.printStackTrace();
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
