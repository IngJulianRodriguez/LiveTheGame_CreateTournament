package com.livethegame.CreateTournament.repository;

import com.livethegame.CreateTournament.entities.Categories;
import com.livethegame.CreateTournament.entities.Params;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ParamsRepository extends JpaRepository<Params, Long> {
    Optional<Params> findByName(String name);
}
