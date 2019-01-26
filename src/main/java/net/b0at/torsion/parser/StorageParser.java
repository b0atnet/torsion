package net.b0at.torsion.parser;

public interface StorageParser<T> {
    T load(Class<T> clazz);

    void save(T object);
}
