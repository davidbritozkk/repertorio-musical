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
public class Categoria {

    @Id
    @UuidGenerator
    @GeneratedValue
    private UUID id;

    @NotNull
    @Column(name = "nome_categoria")
    private String nomeCategoria;

    @Column(name = "musicas_categoria")
    @OneToMany(mappedBy = "categoria")
    @JsonIgnoreProperties("categoria")
    private List<Musica> musicas;

    @CreationTimestamp
    private Instant creationTimestamp;

    @UpdateTimestamp
    private Instant updateTimestamp;

    public Categoria() {
    }

    public Categoria(@NotNull String nomeCategoria) {
        this.nomeCategoria = nomeCategoria;
    }

}
