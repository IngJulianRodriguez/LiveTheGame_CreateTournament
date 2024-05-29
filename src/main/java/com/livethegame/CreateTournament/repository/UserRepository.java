package com.livethegame.CreateTournament.repository;

import com.livethegame.CreateTournament.entities.Params;
import com.livethegame.CreateTournament.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Long> {
}
