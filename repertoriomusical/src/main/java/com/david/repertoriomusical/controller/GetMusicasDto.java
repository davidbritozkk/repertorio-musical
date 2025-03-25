package com.david.repertoriomusical.controller;

import java.time.Instant;
import java.util.UUID;

public record GetMusicasDto(UUID musicaId, String nomeMusica, String nomeArtista, String nomeCategoria, Instant creationTimestamp, Instant updateTimestamp) {
}
