package com.david.repertoriomusical.controller;

import java.util.UUID;

public record UpdateMusicaDto(String nomeMusica, String linkYoutube, UUID artistaId, UUID categoriaId) {
}
