package com.david.repertoriomusical.service;

import com.david.repertoriomusical.dto.CreateArtistaDto;
import com.david.repertoriomusical.dto.GetArtistasDto;
import com.david.repertoriomusical.dto.MusicaNomeDto;
import com.david.repertoriomusical.dto.UpdateArtistaDto;
import com.david.repertoriomusical.model.Artista;
import com.david.repertoriomusical.repository.ArtistaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ArtistaService {

    private ArtistaRepository artistaRepository;

    public ArtistaService(ArtistaRepository artistaRepository) {
        this.artistaRepository = artistaRepository;
    }

    public UUID createArtista (CreateArtistaDto createArtistaDto){
        //UUID utilizado no método acima para evitar tráfego de muitos dados, por questões de segurança
        //DTO > ENTITY
        var entity = new Artista(createArtistaDto.nomeArtista());

        var artistaSaved =  artistaRepository.save(entity);
        return artistaSaved.getId();

    }

    public Optional<Artista> getArtistaById(String artistaId){
       return artistaRepository.findById(UUID.fromString(artistaId));

    }

    public List<GetArtistasDto> listArtista(){
        return artistaRepository.findAll().stream().map(artista -> new GetArtistasDto(
                artista.getId(),
                artista.getNomeArtista(),
                artista.getMusicas().stream().map(musica -> new MusicaNomeDto(musica.getNomeMusica())).toList(),
                artista.getCreationTimestamp(),
                artista.getUpdateTimestamp()

        )).toList();
    }

    public void updateArtistaById(String artistaId, UpdateArtistaDto updateArtistaDto){
        var id = UUID.fromString(artistaId);
        var artistaEntity = artistaRepository.findById(id);

        if(artistaEntity.isPresent()){
            var artista = artistaEntity.get();

            if(updateArtistaDto.nomeArtista() != null) {
                artista.setNomeArtista(updateArtistaDto.nomeArtista());
            }

            artistaRepository.save(artista);
        }

    }

    public void deleteById(String artistaId){
        var id = UUID.fromString(artistaId);
        var artistaExists = artistaRepository.existsById(id);

        if(artistaExists){
            artistaRepository.deleteById(id);
        }
    }

}
