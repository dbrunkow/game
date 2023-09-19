package com.brunkow.game.controller;

import com.brunkow.game.dao.TeamRepository;
import com.brunkow.game.vo.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.net.URISyntaxException;

@RestController
@RequestMapping(path = "/teams", produces = MediaType.APPLICATION_JSON_VALUE)
public class TeamController {
    @Autowired
    TeamRepository teamRepository;

    @GetMapping("/")
    public ResponseEntity<Iterable<Team>> getAll() {
        return new ResponseEntity<Iterable<Team>>(teamRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Team> getTeam(@PathVariable("id") Long id) {
        Team team = teamRepository.findById(id).orElseThrow(()
                -> new EntityNotFoundException(id.toString()));
        return ResponseEntity.notFound().build();
    }
}
