package com.brunkow.game;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Set;

@Entity
public class Team implements Serializable {
    private static final Logger logger = LoggerFactory.getLogger(Team.class);
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String location;
    private int power;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "oc_id", referencedColumnName = "id")
    private OffensiveCoordinator offensiveCoordinator;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "dc_id", referencedColumnName = "id")
    private DefensiveCoordinator defensiveCoordinator;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "stc_id", referencedColumnName = "id")
    private SpecialTeamsCoordinator specialTeamsCoordinator;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "hc_id", referencedColumnName = "id")
    private HeadCoach headCoach;

    @OneToMany(mappedBy = "team", cascade = CascadeType.PERSIST)
    Set<Player> players;

    @OneToMany(mappedBy = "team", cascade = CascadeType.PERSIST)
    Set<DepthChart> depthCharts;

    public Team() {

    }

    public Team(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public OffensiveCoordinator getOffensiveCoordinator() {
        return offensiveCoordinator;
    }

    public void setOffensiveCoordinator(OffensiveCoordinator offensiveCoordinator) {
        this.offensiveCoordinator = offensiveCoordinator;
    }

    public Set<Player> getPlayers() {
        return players;
    }

    public void setPlayers(Set<Player> players) {
        this.players = players;
    }

    public Set<DepthChart> getDepthCharts() {
        return depthCharts;
    }

    public void setDepthCharts(Set<DepthChart> depthCharts) {
        this.depthCharts = depthCharts;
    }

    public DefensiveCoordinator getDefensiveCoordinator() {
        return defensiveCoordinator;
    }

    public void setDefensiveCoordinator(DefensiveCoordinator defensiveCoordinator) {
        this.defensiveCoordinator = defensiveCoordinator;
    }


    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public SpecialTeamsCoordinator getSpecialTeamsCoordinator() {
        return specialTeamsCoordinator;
    }

    public void setSpecialTeamsCoordinator(SpecialTeamsCoordinator specialTeamsCoordinator) {
        this.specialTeamsCoordinator = specialTeamsCoordinator;
    }

    public HeadCoach getHeadCoach() {
        return headCoach;
    }

    public void setHeadCoach(HeadCoach headCoach) {
        this.headCoach = headCoach;
    }
}
