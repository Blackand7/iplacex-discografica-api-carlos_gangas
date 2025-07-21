package com.iplacex.discografica_api.artistas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ArtistaController {
    
    @Autowired
    private IArtistaRepository artistaRepository;
    
    @PostMapping(value = "/artista", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> crearArtista(@Valid @RequestBody Artista artista) {
        try {
            Artista savedArtista = artistaRepository.save(artista);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedArtista);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear artista");
        }
    }
    
    @GetMapping(value = "/artistas", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Artista>> obtenerArtistas() {
        try {
            List<Artista> artistas = artistaRepository.findAll();
            return ResponseEntity.ok(artistas);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    
    @GetMapping(value = "/artista/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> obtenerArtista(@PathVariable String id) {
        try {
            Optional<Artista> artista = artistaRepository.findById(id);
            if (artista.isPresent()) {
                return ResponseEntity.ok(artista.get());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Artista no encontrado");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al buscar artista");
        }
    }
    
    @PutMapping(value = "/artista/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> actualizarArtista(@PathVariable String id, @Valid @RequestBody Artista artista) {
        try {
            if (artistaRepository.existsById(id)) {
                artista._id = id;
                Artista updatedArtista = artistaRepository.save(artista);
                return ResponseEntity.ok(updatedArtista);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Artista no encontrado");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar artista");
        }
    }
    
    @DeleteMapping(value = "/artista/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> eliminarArtista(@PathVariable String id) {
        try {
            if (artistaRepository.existsById(id)) {
                artistaRepository.deleteById(id);
                return ResponseEntity.ok("Artista eliminado correctamente");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Artista no encontrado");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar artista");
        }
    }
}