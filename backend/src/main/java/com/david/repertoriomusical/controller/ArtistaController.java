package com.david.repertoriomusical.controller;

import com.david.repertoriomusical.dto.CreateArtistaDto;
import com.david.repertoriomusical.dto.GetArtistasDto;
import com.david.repertoriomusical.dto.UpdateArtistaDto;
import com.david.repertoriomusical.model.Artista;
import com.david.repertoriomusical.service.ArtistaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/v1/artistas")
public class ArtistaController {

    private ArtistaService artistaService;

    public ArtistaController(ArtistaService artistaService) {
        this.artistaService = artistaService;
    }

    @PostMapping
    public ResponseEntity<Artista> createArtista(@RequestBody CreateArtistaDto createArtistaDto) {
        var id = artistaService.createArtista(createArtistaDto);
        return ResponseEntity.created(URI.create("/v1/artistas/" + id.toString())).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Artista> getArtistaById(@PathVariable("id") String id) {
        var artista = artistaService.getArtistaById(id);
        if (artista.isPresent()) {
            return ResponseEntity.ok(artista.get());
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping
    public ResponseEntity<List<GetArtistasDto>> listArtista() {
        var artistas = artistaService.listArtista();
        return ResponseEntity.ok(artistas);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateArtistaById(@PathVariable("id") String id,
                                                  @RequestBody UpdateArtistaDto updateArtistaDto) {
        artistaService.updateArtistaById(id, updateArtistaDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") String id) {
        artistaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
