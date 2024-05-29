package com.livethegame.CreateTournament.common;

import com.livethegame.CreateTournament.dto.TournamentResponse;
import com.livethegame.CreateTournament.entities.Tournaments;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface TournamentResponseMapper {
    TournamentResponse TournamentToTournamentResponse(Tournaments source);
}
