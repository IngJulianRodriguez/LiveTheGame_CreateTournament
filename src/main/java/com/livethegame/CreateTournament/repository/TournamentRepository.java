package com.livethegame.CreateTournament.repository;

import com.livethegame.CreateTournament.entities.Tournaments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TournamentRepository extends JpaRepository<Tournaments, Long> {
}
