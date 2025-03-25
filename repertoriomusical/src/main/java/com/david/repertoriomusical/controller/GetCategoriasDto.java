package com.david.repertoriomusical.controller;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public record GetCategoriasDto(UUID categoriaId, String nomeCategoria, List<MusicaNomeDto> musicas, Instant creationTimeStamp, Instant updateTimeStamp) {
}
