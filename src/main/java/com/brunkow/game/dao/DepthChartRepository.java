package com.brunkow.game.dao;

import com.brunkow.game.vo.DepthChart;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface DepthChartRepository extends CrudRepository<DepthChart, Long> {
    Set<DepthChart> findByTeamId(Long teamId);
    DepthChart findByTeamIdAndPositionAndDepth(Long teamId, String position, int depth);
}

