package net.b0at.torsion;

import java.util.Optional;

public interface Storage<T> {
    Optional<T> load();

    void save(T object);
}
