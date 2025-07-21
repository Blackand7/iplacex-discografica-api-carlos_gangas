package com.iplacex.discografica_api.discos;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Document(collection = "discos")
public class Disco {
    
    @Id
    public String _id;
    
    @NotBlank(message = "El ID del artista es obligatorio")
    public String artistaId;
    
    @NotBlank(message = "El nombre del disco es obligatorio")
    public String nombre;
    
    @NotNull(message = "El año de lanzamiento es obligatorio")
    public Integer anioLanzamiento;
    
    public List<String> canciones;
    
  
    public Disco() {}
    
    public Disco(String artistaId, String nombre, Integer anioLanzamiento, List<String> canciones) {
        this.artistaId = artistaId;
        this.nombre = nombre;
        this.anioLanzamiento = anioLanzamiento;
        this.canciones = canciones;
    }
}
