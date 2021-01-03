package com.brunkow.game.dao;

import com.brunkow.game.vo.Game;
import org.springframework.data.repository.CrudRepository;

public interface GameRepository extends CrudRepository<Game, Long> {

}
