package com.david.repertoriomusical.controller;

import com.david.repertoriomusical.dto.CreateCategoriaDto;
import com.david.repertoriomusical.dto.GetCategoriasDto;
import com.david.repertoriomusical.dto.UpdateCategoriaDto;
import com.david.repertoriomusical.model.Categoria;
import com.david.repertoriomusical.service.CategoriaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/v1/categorias")
public class CategoriaController {

    private CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @PostMapping
    public ResponseEntity<Categoria> createCategoria(@RequestBody CreateCategoriaDto createCategoriaDto) {
        var id = categoriaService.createCategoria(createCategoriaDto);
        return ResponseEntity.created(URI.create("/v1/categorias/" + id.toString())).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> getCategoriaById(@PathVariable("id") String id) {
        var categoria = categoriaService.getCategoriaById(id);
        if (categoria.isPresent()) {
            return ResponseEntity.ok(categoria.get());
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping
    public ResponseEntity<List<GetCategoriasDto>> listCategoria() {
        var categorias = categoriaService.listCategoria();
        return ResponseEntity.ok(categorias);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateCategoriaById(@PathVariable("id") String id,
                                                    @RequestBody UpdateCategoriaDto updateCategoriaDto) {
        categoriaService.updateCategoriaById(id, updateCategoriaDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") String id) {
        categoriaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
