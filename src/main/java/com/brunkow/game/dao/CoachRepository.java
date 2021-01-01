package com.brunkow.game.dao;

import com.brunkow.game.OffensiveCoordinator;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoachRepository extends CrudRepository<OffensiveCoordinator, Long> {

}

