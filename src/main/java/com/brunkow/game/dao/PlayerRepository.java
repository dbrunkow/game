package com.brunkow.game.dao;

import com.brunkow.game.vo.Player;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface PlayerRepository extends CrudRepository<Player, Long> {
    Set<Player> findByTeamId(Long teamId);

    Player findByTeamIdAndPosition(Long teamId, String position);
}

