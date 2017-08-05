package net.b0at.torsion.parser;

/**
 * Created by Jordin on 8/4/2017.
 * Jordin is still best hacker.
 */
public interface StorageParser<T> {
    T load(Class<T> clazz);

    void save(T object);
}
