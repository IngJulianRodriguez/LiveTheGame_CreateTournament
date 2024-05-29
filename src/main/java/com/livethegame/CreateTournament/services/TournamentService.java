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
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
public class TournamentService {

    @Autowired
    TournamentRepository tournamentRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    ParamsRepository paramsRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    TournamentTypeRepository tournamentTypeRepository;
    @Autowired
    RegisterUserToTournamentService registerUserToTournamentService;
    @Autowired
    TournamentResponseMapper tournamentResponseMapper;

    public TournamentResponse createTournament(TournamentRequest tournamentRequest){
        Optional<Categories> optionalCategory= categoryRepository.findById(tournamentRequest.getCategory_id());
        if(optionalCategory.isEmpty()) {
            throw new CategoryNotFoundException("Categoria no encontrada con ID: " + tournamentRequest.getCategory_id());
        }
        Optional<TournamentTypes> optionalTournamentType = tournamentTypeRepository.findById(tournamentRequest.getTournament_type_id());
        if(optionalTournamentType.isEmpty()) {
            throw new TournamentTypeNotFoundException("Tipo de torneo no encontrado con ID: " + tournamentRequest.getTournament_type_id());
        }
        Optional<Params> optionalParamsTournamentTypesFreeId = paramsRepository.findByName("tournament.types.free.id");
        if (optionalParamsTournamentTypesFreeId.isEmpty()) {
            throw new ParamsNotFoundException("Parametro tournament.types.free.id no encontrado");
        }

        if(optionalParamsTournamentTypesFreeId.get().getValueAsLong()
                == tournamentRequest.getTournament_type_id()){
            Optional<Users> optionalUser = userRepository.findById(tournamentRequest.getUser_id());
            if (optionalUser.isEmpty()) {
                throw new ParamsNotFoundException("Usuario no encontrado con ID: "+tournamentRequest.getUser_id());
            }
            Optional<Params> optionalParamsTournamentFreeMax = paramsRepository.findByName("tournament.free.max");
            if (optionalParamsTournamentFreeMax.isEmpty()) {
                throw new ParamsNotFoundException("Parametro tournament.free.max no encontrado");
            }
            Users user = optionalUser.get();
            if(user.getFree_tournaments_created() == optionalParamsTournamentFreeMax.get().getValueAsInt()){
                throw new MaximumNumberOfFreeTournamentsReachedException("Numero maximo de torneos gratis alcanzado");
            }else{
                user.incrementFree_tournaments_created();
                userRepository.save(user);
            }
        }
        Tournaments tournamentRequestToTournament = Mapper.TournamentRequestToTournament(tournamentRequest,optionalCategory.get(),optionalTournamentType.get());
        Tournaments save = tournamentRepository.save(tournamentRequestToTournament);
        TournamentResponse tournamentToTournamentResponse = tournamentResponseMapper.TournamentToTournamentResponse(save);


        registerUserToTournamentService.registerUserToTournament(tournamentToTournamentResponse.getId(),tournamentRequest.getUser_id());

        return tournamentToTournamentResponse;
    }

}
