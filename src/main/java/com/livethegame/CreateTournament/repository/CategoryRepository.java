package com.livethegame.CreateTournament.repository;

import com.livethegame.CreateTournament.entities.Categories;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Categories, Long> {
}
