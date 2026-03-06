package com.example.backend_taller.service;

import java.util.List;
import java.util.Optional;

public interface BaseService<T,ID> {

    T crear(T entity);

    List<T> listar();

    Optional<T> buscarPorId(ID id);

    void eliminar(ID id);

}
