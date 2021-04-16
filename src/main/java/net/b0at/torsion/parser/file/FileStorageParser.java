package net.b0at.torsion.parser.file;

import net.b0at.torsion.parser.StorageParser;

import java.io.File;
import java.nio.file.Path;

public abstract class FileStorageParser<T> implements StorageParser<T> {
    protected Path file;

    public FileStorageParser(Path file) {
        this.file = file;
    }

    public Path getFile() {
        return this.file;
    }
}
