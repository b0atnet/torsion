package net.b0at.torsion.parser.file;

import java.io.File;

public class NullFileParser<T> extends FileStorageParser<T> {
    public NullFileParser(File file) {
        super(file);
    }

    @Override
    public T load(Class<T> clazz) {
        return null;
    }

    @Override
    public void save(T object) {

    }
}
