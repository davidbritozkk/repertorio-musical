package com.david.repertoriomusical.dto;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public record GetCategoriasDto(UUID id, String nomeCategoria, List<MusicaNomeDto> musicas, Instant creationTimeStamp, Instant updateTimeStamp) {
}
