package com.david.repertoriomusical.dto;

import java.util.UUID;

public record UpdateMusicaDto(String nomeMusica, String linkYoutube, UUID artistaId, UUID categoriaId) {
}
