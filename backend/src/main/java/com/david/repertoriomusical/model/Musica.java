package com.david.repertoriomusical.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;
import org.jetbrains.annotations.NotNull;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@Entity
public class Musica {

    @Id
    @UuidGenerator
    @GeneratedValue
    private UUID id;

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

}

