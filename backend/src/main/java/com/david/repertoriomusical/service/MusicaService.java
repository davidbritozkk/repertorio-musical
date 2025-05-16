package com.david.repertoriomusical.service;

import com.david.repertoriomusical.dto.CreateMusicaDto;
import com.david.repertoriomusical.dto.GetMusicasDto;
import com.david.repertoriomusical.dto.UpdateMusicaDto;
import com.david.repertoriomusical.model.Musica;
import com.david.repertoriomusical.repository.ArtistaRepository;
import com.david.repertoriomusical.repository.CategoriaRepository;
import com.david.repertoriomusical.repository.MusicaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MusicaService {

    private MusicaRepository musicaRepository;
    private ArtistaRepository artistaRepository;
    private CategoriaRepository categoriaRepository;

    public MusicaService(MusicaRepository musicaRepository, ArtistaRepository artistaRepository, CategoriaRepository categoriaRepository) {
        this.musicaRepository = musicaRepository;
        this.artistaRepository = artistaRepository;
        this.categoriaRepository = categoriaRepository;
    }

    public UUID createMusica(CreateMusicaDto createMusicaDto){
        var artista = artistaRepository.findById(createMusicaDto.artistaId()).orElseThrow(() -> new IllegalArgumentException("Artista não encontrado"));

        var categoria = categoriaRepository.findById(createMusicaDto.categoriaId()).orElseThrow(() -> new IllegalArgumentException("Categoria não encontrada"));
        var entity = new Musica(
                createMusicaDto.nomeMusica(),
                createMusicaDto.linkYoutube(),
                artista,
                categoria
        );

        var musicaSaved = musicaRepository.save(entity);
        return musicaSaved.getId();
    }

    public Optional<Musica> getMusicaById(String musicaId){
        return musicaRepository.findById(UUID.fromString(musicaId));
    }

    public List<GetMusicasDto> listMusica(){
        return musicaRepository.findAll().stream().map(musica -> new GetMusicasDto(
                musica.getId(),
                musica.getNomeMusica(),
                musica.getArtista().getNomeArtista(),
                musica.getCategoria().getNomeCategoria(),
                musica.getCreationTimestamp(),
                musica.getUpdateTimestamp()
        )).toList();
    }

    public void updateMusicaById(String musicaId, UpdateMusicaDto updateMusicaDto){
        var id = UUID.fromString(musicaId);
        var musicaEntity = musicaRepository.findById(id);

        if(musicaEntity.isPresent()){
            var musica = musicaEntity.get();

            if(updateMusicaDto.nomeMusica()!=null){
                musica.setNomeMusica(updateMusicaDto.nomeMusica());
            }
            if(updateMusicaDto.linkYoutube()!=null){
                musica.setLinkYoutube(updateMusicaDto.linkYoutube());
            }
            if(updateMusicaDto.artistaId()!=null){
                var artista = artistaRepository.findById(updateMusicaDto.artistaId()).orElseThrow(() -> new IllegalArgumentException("Artista não encontrado"));
                musica.setArtista(artista);
            }
            if(updateMusicaDto.categoriaId()!=null){
                var categoria = categoriaRepository.findById(updateMusicaDto.categoriaId()).orElseThrow(() -> new IllegalArgumentException("Categoria não encontrada"));
                musica.setCategoria(categoria);
            }
            musicaRepository.save(musica);
            }
        }


    public void deleteById(String musicaId){
        var id = UUID.fromString(musicaId);
        var musicaExists = musicaRepository.existsById(id);

        if(musicaExists){
            musicaRepository.deleteById(id);
        }
    }
}
