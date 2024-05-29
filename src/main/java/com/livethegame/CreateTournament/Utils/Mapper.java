package com.livethegame.CreateTournament.Utils;

import com.livethegame.CreateTournament.dto.TournamentRequest;
import com.livethegame.CreateTournament.entities.Categories;
import com.livethegame.CreateTournament.entities.Tournaments;
import com.livethegame.CreateTournament.entities.TournamentTypes;

public  class Mapper {
    public static Tournaments TournamentRequestToTournament(TournamentRequest source, Categories categories, TournamentTypes tournamentType){
        Tournaments tournament = new Tournaments();
        tournament.setStart_date(source.getStart_date());
        tournament.setStart_time(source.getStart_time());
        tournament.setCategory(categories);
        tournament.setTournament_type(tournamentType);
        return tournament;
    };
}
