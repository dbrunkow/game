package com.brunkow.game.vo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;

@Entity
public class DefensiveCoordinator extends Coach {
  private static final Logger logger = LoggerFactory.getLogger(DefensiveCoordinator.class);
  private static final long serialVersionUID = 1L;
  public DefensiveCoordinator() {
    super();
  }
}
