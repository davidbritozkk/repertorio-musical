package com.david.repertoriomusical.controller;

import com.david.repertoriomusical.dto.CreateMusicaDto;
import com.david.repertoriomusical.dto.GetMusicasDto;
import com.david.repertoriomusical.dto.UpdateMusicaDto;
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
        var id = musicaService.createMusica(createMusicaDto);
        return ResponseEntity.created(URI.create("/v1/musicas/" + id.toString())).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Musica> getMusicaById(@PathVariable("id") String id){
        var musica = musicaService.getMusicaById(id);
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

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateMusicaById(@PathVariable("id") String id,
                                                 @RequestBody UpdateMusicaDto updateMusicaDto){
        musicaService.updateMusicaById(id, updateMusicaDto);
        return ResponseEntity.noContent().build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") String id){
        musicaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
