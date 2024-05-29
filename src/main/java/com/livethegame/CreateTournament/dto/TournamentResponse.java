package com.livethegame.CreateTournament.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel()
public class TournamentResponse {

    @ApiModelProperty(name = "Id", required = true,example = "", value = "")
    private Long id;

    public TournamentResponse(){
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}
