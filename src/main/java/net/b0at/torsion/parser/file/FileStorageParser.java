package net.b0at.torsion.parser.file;

import net.b0at.torsion.parser.StorageParser;

import java.io.File;

/**
 * Created by Jordin on 8/5/2017.
 * Jordin is still best hacker.
 */
public abstract class FileStorageParser<T> implements StorageParser<T> {
    private File file;

    public FileStorageParser(File file) {
        this.file = file;
    }

    public File getFile() {
        return this.file;
    }
}
