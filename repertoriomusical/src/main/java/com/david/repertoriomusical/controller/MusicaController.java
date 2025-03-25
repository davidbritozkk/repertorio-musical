package com.david.repertoriomusical.controller;

import com.david.repertoriomusical.model.Musica;
import com.david.repertoriomusical.service.MusicaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/v1/musicas")
public class MusicaController {

    private MusicaService musicaService;

    public MusicaController(MusicaService musicaService) {
        this.musicaService = musicaService;
    }

    @PostMapping
    public ResponseEntity<Musica> createMusica(@RequestBody CreateMusicaDto createMusicaDto){
        var musicaId = musicaService.createMusica(createMusicaDto);
        return ResponseEntity.created(URI.create("/v1/musicas/" + musicaId.toString())).build();
    }

    @GetMapping("/{musicaId}")
    public ResponseEntity<Musica> getMusicaById(@PathVariable("musicaId") String musicaId){
        var musica = musicaService.getMusicaById(musicaId);
        if(musica.isPresent()){
            return ResponseEntity.ok(musica.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<GetMusicasDto>> listMusica(){
        var musicas = musicaService.listMusica();
        return ResponseEntity.ok(musicas);
    }

    @PutMapping("/{musicaId}")
    public ResponseEntity<Void> updateMusicaById(@PathVariable("musicaId") String musicaId,
                                                 @RequestBody UpdateMusicaDto updateMusicaDto){
        musicaService.updateMusicaById(musicaId, updateMusicaDto);
        return ResponseEntity.noContent().build();
    }


    @DeleteMapping("/{musicaId}")
    public ResponseEntity<Void> deleteById(@PathVariable("musicaId") String musicaId){
        musicaService.deleteById(musicaId);
        return ResponseEntity.noContent().build();
    }

}
