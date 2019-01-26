package net.b0at.torsion;

public interface Storage<T> {
    T load();

    void save(T object);
}
