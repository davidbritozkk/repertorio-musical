package com.david.repertoriomusical.service;

import com.david.repertoriomusical.dto.CreateCategoriaDto;
import com.david.repertoriomusical.dto.GetCategoriasDto;
import com.david.repertoriomusical.dto.MusicaNomeDto;
import com.david.repertoriomusical.dto.UpdateCategoriaDto;
import com.david.repertoriomusical.model.Categoria;
import com.david.repertoriomusical.repository.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CategoriaService {

    private CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public UUID createCategoria(CreateCategoriaDto createCategoriaDto) {
        //UUID utilizado no método acima para evitar tráfego de muitos dados, por questões de segurança
        //DTO > ENTITY
        var entity = new Categoria(createCategoriaDto.nomeCategoria());

        var categoriaSaved = categoriaRepository.save(entity);
        return categoriaSaved.getId();
    }

    public Optional<Categoria> getCategoriaById(String categoriaId) {
        return categoriaRepository.findById(UUID.fromString(categoriaId));
    }

    public List<GetCategoriasDto> listCategoria() {
        return categoriaRepository.findAll().stream().map(categoria -> new GetCategoriasDto(
                categoria.getId(),
                categoria.getNomeCategoria(),
                categoria.getMusicas().stream().map(musica -> new MusicaNomeDto(musica.getNomeMusica())).toList(),
                categoria.getCreationTimestamp(),
                categoria.getUpdateTimestamp()
        )).toList();
    }

    public void updateCategoriaById(String categoriaId, UpdateCategoriaDto updateCategoriaDto) {
        var id = UUID.fromString(categoriaId);
        var categoriaEntity = categoriaRepository.findById(id);

        if (categoriaEntity.isPresent()) {
            var categoria = categoriaEntity.get();

            if (updateCategoriaDto.nomeCategoria() != null) {
                categoria.setNomeCategoria(updateCategoriaDto.nomeCategoria());
            }

            categoriaRepository.save(categoria);
        }

    }

    public void deleteById(String categoriaId) {
        var id = UUID.fromString(categoriaId);
        var categoriaExists = categoriaRepository.existsById(id);

        if (categoriaExists) {
            categoriaRepository.deleteById(id);
        }
    }

}
