package net.b0at.torsion;

import net.b0at.torsion.parser.StorageParser;
import net.b0at.torsion.parser.file.FileParserFactory;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class FileStorage<T> implements Storage<T> {
    private static File baseDirectory = new File(".");
    private final File file;
    private final Class<T> clazz;

    private StorageParser<T> fileParser;

    private FileStorage(Class<T> clazz, File file) throws IOException {
        this.file = file;
        this.clazz = clazz;

        this.file.getParentFile().mkdirs();

        if (!this.file.exists()) {
            this.file.createNewFile();
        }

        try {
            this.fileParser = FileParserFactory.<T>of(this.file);
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> FileStorage<T> of(Class<T> clazz, File file) throws IOException {
        return new FileStorage<>(clazz, file);
    }

    public static <T> FileStorage<T> of(Class<T> clazz, String fileLocation) throws IOException {
        return new FileStorage<>(clazz, new File(baseDirectory, fileLocation));
    }

    public static File getBaseDirectory() {
        return FileStorage.baseDirectory;
    }

    public static void setBaseDirectory(File baseDirectory) {
        FileStorage.baseDirectory = baseDirectory;
    }

    @Override
    public void save(T object) {
        this.fileParser.save(object);
    }

    @Override
    public T load() {
        return this.fileParser.load(this.clazz);
    }
}
