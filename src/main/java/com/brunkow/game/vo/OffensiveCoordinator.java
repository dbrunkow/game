package com.brunkow.game.vo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;

@Entity
public class OffensiveCoordinator extends Coach {
  private static final Logger logger = LoggerFactory.getLogger(DefensiveCoordinator.class);
  private static final long serialVersionUID = 1L;
  public OffensiveCoordinator() {
    super();
  }
}