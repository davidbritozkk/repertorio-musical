package com.david.repertoriomusical.dto;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public record GetArtistasDto(UUID id, String nomeArtista, List<MusicaNomeDto> musicas, Instant creationTimeStamp, Instant updateTimeStamp) {
}
