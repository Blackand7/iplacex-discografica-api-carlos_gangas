package com.iplacex.discografica_api.discos;

import com.iplacex.discografica_api.artistas.IArtistaRepository;
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
public class DiscoController {
    
    @Autowired
    private IDiscoRepository discoRepository;
    
    @Autowired
    private IArtistaRepository artistaRepository;
    
    @PostMapping(value = "/disco", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> crearDisco(@Valid @RequestBody Disco disco) {
        try {
            // Verificar que el artista existe
            if (!artistaRepository.existsById(disco.artistaId)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El artista especificado no existe");
            }
            
            Disco savedDisco = discoRepository.save(disco);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedDisco);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear disco");
        }
    }
    
    @GetMapping(value = "/discos", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Disco>> obtenerDiscos() {
        try {
            List<Disco> discos = discoRepository.findAll();
            return ResponseEntity.ok(discos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    
    @GetMapping(value = "/disco/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> obtenerDisco(@PathVariable String id) {
        try {
            Optional<Disco> disco = discoRepository.findById(id);
            if (disco.isPresent()) {
                return ResponseEntity.ok(disco.get());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Disco no encontrado");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al buscar disco");
        }
    }
    
    @GetMapping(value = "/artista/{artistaId}/discos", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> obtenerDiscosPorArtista(@PathVariable String artistaId) {
        try {
            if (!artistaRepository.existsById(artistaId)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Artista no encontrado");
            }
            
            List<Disco> discos = discoRepository.findByArtistaId(artistaId);
            return ResponseEntity.ok(discos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al buscar discos del artista");
        }
    }
    
    @PutMapping(value = "/disco/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> actualizarDisco(@PathVariable String id, @Valid @RequestBody Disco disco) {
        try {
            if (!discoRepository.existsById(id)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Disco no encontrado");
            }
            
            if (!artistaRepository.existsById(disco.artistaId)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El artista especificado no existe");
            }
            
            disco._id = id;
            Disco updatedDisco = discoRepository.save(disco);
            return ResponseEntity.ok(updatedDisco);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar disco");
        }
    }
    
    @DeleteMapping(value = "/disco/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> eliminarDisco(@PathVariable String id) {
        try {
            if (discoRepository.existsById(id)) {
                discoRepository.deleteById(id);
                return ResponseEntity.ok("Disco eliminado correctamente");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Disco no encontrado");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar disco");
        }
    }
}