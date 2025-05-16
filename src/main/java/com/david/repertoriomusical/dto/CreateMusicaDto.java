package com.david.repertoriomusical.dto;

import java.util.UUID;

public record CreateMusicaDto(String nomeMusica, String linkYoutube, UUID artistaId, UUID categoriaId) {
}
