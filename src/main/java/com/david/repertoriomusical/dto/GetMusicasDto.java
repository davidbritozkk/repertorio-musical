package com.david.repertoriomusical.dto;

import java.time.Instant;
import java.util.UUID;

public record GetMusicasDto(UUID id, String nomeMusica, String nomeArtista, String nomeCategoria, Instant creationTimestamp, Instant updateTimestamp) {
}
