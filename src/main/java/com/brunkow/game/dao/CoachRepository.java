package com.brunkow.game.dao;

import com.brunkow.game.vo.Coach;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoachRepository extends CrudRepository<Coach, Long> {

}

