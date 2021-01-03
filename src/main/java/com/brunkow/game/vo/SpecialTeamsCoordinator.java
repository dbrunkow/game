package com.brunkow.game.vo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.Entity;

@Entity
public class SpecialTeamsCoordinator extends Coach {
  private static final Logger logger = LoggerFactory.getLogger(SpecialTeamsCoordinator.class);
  private static final long serialVersionUID = 1L;
  public SpecialTeamsCoordinator() {
    super();
  }
}
