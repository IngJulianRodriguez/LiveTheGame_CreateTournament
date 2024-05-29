package com.livethegame.CreateTournament.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDate;

@ApiModel()
public class TournamentRequest {

    @ApiModelProperty(name = "id del usuario", required = true, example = "", value = "")
    private long user_id;
    @ApiModelProperty(name = "fecha inicio torneo", required = false,example = "", value = "")
    private LocalDate start_date;
    @ApiModelProperty(name = "hora inicio torneo", required = false,example = "10:00", value = "")
    private String start_time;
    @ApiModelProperty(name = "id de la categoria", required = true,example = "", value = "")
    private long category_id;
    @ApiModelProperty(name = "id del tipo de torneo", required = true,example = "", value = "")
    private long tournament_type_id;

    public TournamentRequest(){
    }

    public LocalDate getStart_date() {
        return start_date;
    }

    public void setStart_date(LocalDate start_date) {
        this.start_date = start_date;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public long getCategory_id() {
        return category_id;
    }

    public void setCategory_id(long category_id) {
        this.category_id = category_id;
    }

    public long getTournament_type_id() {
        return tournament_type_id;
    }

    public void setTournament_type_id(long tournament_type_id) {
        this.tournament_type_id = tournament_type_id;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }
}
