package net.b0at.torsion.parser.file;

import net.b0at.torsion.parser.StorageParser;

import java.io.File;

public abstract class FileStorageParser<T> implements StorageParser<T> {
    private File file;

    public FileStorageParser(File file) {
        this.file = file;
    }

    public File getFile() {
        return this.file;
    }
}
