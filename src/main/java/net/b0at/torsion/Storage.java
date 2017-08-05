package net.b0at.torsion;

/**
 * Created by Jordin on 8/4/2017.
 * Jordin is still best hacker.
 */
public interface Storage<T> {
    T load();

    void save(T object);
}
