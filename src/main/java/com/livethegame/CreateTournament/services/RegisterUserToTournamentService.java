package com.livethegame.CreateTournament.services;

import com.livethegame.CreateTournament.Utils.Mapper;
import com.livethegame.CreateTournament.common.TournamentResponseMapper;
import com.livethegame.CreateTournament.dto.TournamentRequest;
import com.livethegame.CreateTournament.dto.TournamentResponse;
import com.livethegame.CreateTournament.dto.TournamentUserRequest;
import com.livethegame.CreateTournament.entities.*;
import com.livethegame.CreateTournament.exception.CategoryNotFoundException;
import com.livethegame.CreateTournament.exception.MaximumNumberOfFreeTournamentsReachedException;
import com.livethegame.CreateTournament.exception.ParamsNotFoundException;
import com.livethegame.CreateTournament.exception.TournamentTypeNotFoundException;
import com.livethegame.CreateTournament.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Base64;
import java.util.Optional;


@Service
public class RegisterUserToTournamentService {

    @Value("${server.RegisterUserToTournament.url}")
    private String registerUserToTournamentUrlServer;

    public void registerUserToTournament(Long tournamentId, Long userId) {

        TournamentUserRequest tournamentUserRequest = new TournamentUserRequest();
        tournamentUserRequest.setTournament_id(tournamentId);
        tournamentUserRequest.setRole_id(1L);
        tournamentUserRequest.setUser_id(userId);

        WebClient webClient = WebClient.builder()
                .baseUrl(registerUserToTournamentUrlServer)
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Basic " + encodeCredentials("admin", "admin"))
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        webClient.method(HttpMethod.POST)
                .uri("/user-to-tournament/register")
                .body(BodyInserters.fromValue(tournamentUserRequest))
                .retrieve()
                .bodyToMono(String.class)
                .subscribe(response -> {
                    System.out.println("Respuesta del servidor: " + response);
                });


    }

    private String encodeCredentials(String username, String password) {
        return Base64.getEncoder().encodeToString((username + ":" + password).getBytes());
    }

}
