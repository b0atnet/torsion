package net.b0at.torsion.parser.file;

import java.nio.file.Path;
import java.util.Optional;

public class NullFileParser<T> extends FileStorageParser<T> {
    public NullFileParser(Path file) {
        super(file);
    }

    @Override
    public Optional<T> load(Class<? extends T> clazz) {
        return Optional.empty();
    }

    @Override
    public void save(T object) {
    }
}
