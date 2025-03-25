package com.david.repertoriomusical.controller;

import java.util.UUID;

public record CreateMusicaDto(String nomeMusica, String linkYoutube, UUID artistaId, UUID categoriaId) {
}
