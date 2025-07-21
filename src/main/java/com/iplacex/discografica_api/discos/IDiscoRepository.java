package com.iplacex.discografica_api.discos;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IDiscoRepository extends MongoRepository<Disco, String> {
    List<Disco> findByArtistaId(String artistaId);
}