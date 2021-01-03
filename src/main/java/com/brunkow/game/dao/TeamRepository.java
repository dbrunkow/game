package com.brunkow.game.dao;

import com.brunkow.game.vo.Team;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends CrudRepository<Team, Long> {
    Team findByName(String name);
}

