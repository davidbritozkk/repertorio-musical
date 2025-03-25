package com.david.repertoriomusical.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;
import org.jetbrains.annotations.NotNull;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_artistas")
public class Artista {

    @Id
    @UuidGenerator
    @GeneratedValue
    private UUID artistaId;

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

    public UUID getArtistaId() {
        return artistaId;
    }

    public void setArtistaId(UUID artistaId) {
        this.artistaId = artistaId;
    }

    public String getNomeArtista() {
        return nomeArtista;
    }

    public void setNomeArtista(String nomeArtista) {
        this.nomeArtista = nomeArtista;
    }

    public List<Musica> getMusicas() {
        return musicas;
    }

    public void setMusicas(List<Musica> musicas) {
        this.musicas = musicas;
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
