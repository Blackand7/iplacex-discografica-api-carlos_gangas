package com.iplacex.discografica_api.artistas;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IArtistaRepository extends MongoRepository<Artista, String> {
    // Métodos personalizados si los necesitas
}