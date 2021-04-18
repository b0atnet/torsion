package net.b0at.torsion.parser;

import java.util.Optional;

public interface StorageParser<T> {
    Optional<T> load(Class<? extends T> clazz);

    void save(T object);
}
