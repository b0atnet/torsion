package net.b0at.torsion.parser.file;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import java.io.*;

/**
 * Created by Jordin on 8/4/2017.
 * Jordin is still best hacker.
 */
public class JSONFileParser<T> extends FileStorageParser<T> {
    public JSONFileParser(File file) {
        super(file);
    }

    @Override
    public T load(Class<T> clazz) {
        T loaded = null;
        try {
            Gson gson = constructGsonBuilder();
            JsonReader reader = new JsonReader(new FileReader(getFile()));
            reader.setLenient(true);

            loaded = gson.fromJson(reader, clazz);
        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
        }

        if (loaded == null) {
            try {
                loaded = clazz.newInstance();
            } catch (InstantiationException | IllegalAccessException exception) {
                exception.printStackTrace();
            }
        }
        return loaded;
    }

    @Override
    public void save(T object) {
        try {
            Writer writer = new FileWriter(getFile());
            Gson gson = constructGsonBuilder();
            gson.toJson(object, writer);
            writer.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    private static Gson constructGsonBuilder() {
        return new GsonBuilder().setPrettyPrinting().serializeNulls().create();
    }
}
