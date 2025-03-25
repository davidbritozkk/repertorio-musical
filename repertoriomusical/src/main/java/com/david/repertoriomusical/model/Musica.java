package com.david.repertoriomusical.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;
import org.jetbrains.annotations.NotNull;

import java.time.Instant;
import java.util.UUID;


@Entity
@Table(name = "tb_musicas")
public class Musica {

    @Id
    @UuidGenerator
    @GeneratedValue
    private UUID musicaId;

    @NotNull
    @Column(name = "nome_musica")
    private String nomeMusica;

    @Column(name = "link_youtube")
    private String linkYoutube;

    @ManyToOne
    @JoinColumn(name = "artista_id")
    @JsonIgnoreProperties("musicas")
    private Artista artista;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    @JsonIgnoreProperties("musicas")
    private Categoria categoria;

    @CreationTimestamp
    private Instant creationTimestamp;

    @UpdateTimestamp
    private Instant updateTimestamp;

    public Musica() {
    }

    public Musica(@NotNull String nomeMusica, String linkYoutube, Artista artista, Categoria categoria) {
        this.nomeMusica = nomeMusica;
        this.linkYoutube = linkYoutube;
        this.artista = artista;
        this.categoria = categoria;
    }

    public UUID getMusicaId() {
        return musicaId;
    }

    public void setMusicaId(UUID musicaId) {
        this.musicaId = musicaId;
    }

    public String getNomeMusica() {
        return nomeMusica;
    }

    public void setNomeMusica(String nomeMusica) {
        this.nomeMusica = nomeMusica;
    }

    public String getLinkYoutube() {
        return linkYoutube;
    }

    public void setLinkYoutube(String linkYoutube) {
        this.linkYoutube = linkYoutube;
    }

    public Artista getArtista() {
        return artista;
    }

    public void setArtista(Artista artista) {
        this.artista = artista;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Instant getCreationTimestamp() {
        return creationTimestamp;
    }

    public void setCreationTimestamp(Instant creationTimestamp) {
        this.creationTimestamp = creationTimestamp;
    }

    public Instant getUpdateTimestamp() {
        return updateTimestamp;
    }

    public void setUpdateTimestamp(Instant updateTimestamp) {
        this.updateTimestamp = updateTimestamp;
    }
}

