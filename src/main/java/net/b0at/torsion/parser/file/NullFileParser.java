package net.b0at.torsion.parser.file;

import java.io.File;
import java.util.Optional;

public class NullFileParser<T> extends FileStorageParser<T> {
    public NullFileParser(File file) {
        super(file);
    }

    @Override
    public Optional<T> load(Class<T> clazz) {
        return Optional.empty();
    }

    @Override
    public void save(T object) {

    }
}
