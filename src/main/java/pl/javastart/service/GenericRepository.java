package pl.javastart.service;

public interface GenericRepository<K, T> {
    T get(K id) throws IllegalAccessException;
    void add(T obj) throws IllegalAccessException;
}
