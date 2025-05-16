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
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
public class Artista {

    @Id
    @UuidGenerator
    @GeneratedValue
    private UUID id;

    @NotNull
    @Column(name = "nome_artista")
    private String nomeArtista;

    @Column(name = "musicas_artista")
    @OneToMany(mappedBy = "artista")
    @JsonIgnoreProperties("artista")
    private List<Musica> musicas;

    @CreationTimestamp
    private Instant creationTimestamp;

    @UpdateTimestamp
    private Instant updateTimestamp;

    public Artista() {
    }

    public Artista(@NotNull String nomeArtista) {
        this.nomeArtista = nomeArtista;
    }

}
