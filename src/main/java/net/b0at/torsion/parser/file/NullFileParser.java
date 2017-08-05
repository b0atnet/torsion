package net.b0at.torsion.parser.file;

import java.io.File;

/**
 * Created by Jordin on 8/4/2017.
 * Jordin is still best hacker.
 */
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
