package com.david.repertoriomusical.controller;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public record GetArtistasDto(UUID artistaId, String nomeArtista, List<MusicaNomeDto> musicas, Instant creationTimeStamp, Instant updateTimeStamp) {
}
