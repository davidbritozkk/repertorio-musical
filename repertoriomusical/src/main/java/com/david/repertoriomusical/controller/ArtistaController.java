package com.david.repertoriomusical.controller;

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
        var artistaId = artistaService.createArtista(createArtistaDto);
        return ResponseEntity.created(URI.create("/v1/artistas/" + artistaId.toString())).build();
    }

    @GetMapping("/{artistaId}")
    public ResponseEntity<Artista> getArtistaById(@PathVariable("artistaId") String artistaId) {
        var artista = artistaService.getArtistaById(artistaId);
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

    @PutMapping("/{artistaId}")
    public ResponseEntity<Void> updateArtistaById(@PathVariable("artistaId") String artistaId,
                                                  @RequestBody UpdateArtistaDto updateArtistaDto) {
        artistaService.updateArtistaById(artistaId, updateArtistaDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{artistaId}")
    public ResponseEntity<Void> deleteById(@PathVariable("artistaId") String artistaId) {
        artistaService.deleteById(artistaId);
        return ResponseEntity.noContent().build();
    }

}
