package com.livethegame.CreateTournament.controller;

import com.livethegame.CreateTournament.dto.TournamentRequest;
import com.livethegame.CreateTournament.dto.TournamentResponse;
import com.livethegame.CreateTournament.exception.*;
import com.livethegame.CreateTournament.services.MonitoringService;
import com.livethegame.CreateTournament.services.TournamentService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(tags = "Api Create Tournament")
@RestController
@RequestMapping("/tournaments")
public class CreateTournamentRestController {

    @Autowired
    TournamentService tournamentService;
    @Autowired
    MonitoringService monitoringService;

    @PostMapping("/create")
    public ResponseEntity<?> createTournament(@RequestBody TournamentRequest input) {
        try {
            TournamentResponse TournamentResponse = tournamentService.createTournament(input);
            monitoringService.registerSuccessLog(String.valueOf(input.getUser_id()),"/create "+input.toString()+" "+TournamentResponse);
            return ResponseEntity.ok(TournamentResponse);
        } catch (CategoryNotFoundException
                 | TournamentTypeNotFoundException
                 | ParamsNotFoundException
                 | UserNotFoundException e) {
            monitoringService.registerControlledExceptionLog("","/create "+input.toString()+" "+e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (MaximumNumberOfFreeTournamentsReachedException e) {
            monitoringService.registerControlledExceptionLog("","/create "+input.toString()+" "+e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (Exception e) {
            monitoringService.registerNotControlledExceptionLog("","/create "+input.toString()+" "+e.getMessage());
            return ResponseEntity.status(500).body("Error interno del servidor");
        }
    }

}
