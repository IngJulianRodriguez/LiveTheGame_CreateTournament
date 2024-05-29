package com.livethegame.CreateTournament.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel()
public class TournamentUserRequest {

    @ApiModelProperty(name = "id del usuario", required = true,example = "", value = "")
    private long user_id;
    @ApiModelProperty(name = "id del rol", required = true,example = "", value = "")
    private long role_id;
    @ApiModelProperty(name = "id del torneo", required = true,example = "", value = "")
    private long tournament_id;

    public TournamentUserRequest(){
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public long getRole_id() {
        return role_id;
    }

    public void setRole_id(long role_id) {
        this.role_id = role_id;
    }

    public long getTournament_id() {
        return tournament_id;
    }

    public void setTournament_id(long tournament_id) {
        this.tournament_id = tournament_id;
    }
}
