package com.iplacex.discografica_api.artistas;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Document(collection = "artistas")
public class Artista {
    
    @Id
    public String _id;
    
    @NotBlank(message = "El nombre es obligatorio")
    public String nombre;
    
    public List<String> estilos;
    
    @NotNull(message = "El año de fundación es obligatorio")
    public Integer anioFundacion;
    
    @NotNull(message = "El estado activo es obligatorio")
    public Boolean estaActivo;
    
 
    public Artista() {}
    

    public Artista(String nombre, List<String> estilos, Integer anioFundacion, Boolean estaActivo) {
        this.nombre = nombre;
        this.estilos = estilos;
        this.anioFundacion = anioFundacion;
        this.estaActivo = estaActivo;
    }
}
