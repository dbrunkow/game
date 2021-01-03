package com.brunkow.game.dao;

import com.brunkow.game.vo.DefensiveCoordinator;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DefensiveCoordinatorRepository extends CrudRepository<DefensiveCoordinator, Long> {

}

