package com.david.repertoriomusical.controller;

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
        var categoriaId = categoriaService.createCategoria(createCategoriaDto);
        return ResponseEntity.created(URI.create("/v1/categorias/" + categoriaId.toString())).build();
    }

    @GetMapping("/{categoriaId}")
    public ResponseEntity<Categoria> getCategoriaById(@PathVariable("categoriaId") String categoriaId) {
        var categoria = categoriaService.getCategoriaById(categoriaId);
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

    @PutMapping("/{categoriaId}")
    public ResponseEntity<Void> updateCategoriaById(@PathVariable("categoriaId") String categoriaId,
                                                    @RequestBody UpdateCategoriaDto updateCategoriaDto) {
        categoriaService.updateCategoriaById(categoriaId, updateCategoriaDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{categoriaId}")
    public ResponseEntity<Void> deleteById(@PathVariable("categoriaId") String categoriaId) {
        categoriaService.deleteById(categoriaId);
        return ResponseEntity.noContent().build();
    }

}
