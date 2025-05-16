package com.david.repertoriomusical.repository;

import com.david.repertoriomusical.model.Musica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MusicaRepository extends JpaRepository<Musica, UUID> {
}
