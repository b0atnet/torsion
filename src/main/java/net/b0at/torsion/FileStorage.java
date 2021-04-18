package net.b0at.torsion;

import net.b0at.torsion.parser.StorageParser;
import net.b0at.torsion.parser.file.FileParserFactory;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;
import java.util.Optional;

public final class FileStorage<T> implements Storage<T> {
    private static Path baseDirectory = Paths.get(".");
    private final Class<T> clazz;

    private final StorageParser<T> fileParser;

    private FileStorage(Class<T> clazz, Path file) throws IOException {
        this.clazz = clazz;

        Files.createDirectories(file.getParent());

        if (!Files.exists(file)) {
            Files.createFile(file);
        }

        try {
            //noinspection unchecked
            this.fileParser = (StorageParser<T>) FileParserFactory.of(file);
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            throw new TorsionException("Failed to create file parser!", e);
        }
    }

    public static <T> FileStorage<T> of(Class<T> clazz, Path file) throws IOException {
        return new FileStorage<>(clazz, baseDirectory.resolve(file));
    }

    public static <T> FileStorage<T> of(Class<T> clazz, String fileLocation) throws IOException {
        return new FileStorage<>(clazz, baseDirectory.resolve(fileLocation));
    }

    public static Path getBaseDirectory() {
        return FileStorage.baseDirectory;
    }

    public static void setBaseDirectory(Path baseDirectory) {
        FileStorage.baseDirectory = baseDirectory;
    }

    @Override
    public void save(T object) {
        this.fileParser.save(object);
    }

    @Override
    public Optional<T> load() {
        return this.fileParser.load(this.clazz);
    }
}
