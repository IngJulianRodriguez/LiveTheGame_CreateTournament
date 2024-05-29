package com.livethegame.CreateTournament.repository;

import com.livethegame.CreateTournament.entities.TournamentTypes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TournamentTypeRepository extends JpaRepository<TournamentTypes, Long> {
}
