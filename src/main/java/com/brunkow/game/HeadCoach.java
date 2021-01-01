package com.brunkow.game;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.Entity;

@Entity
public class HeadCoach extends Coach {
  private static final Logger logger = LoggerFactory.getLogger(HeadCoach.class);
  private static final long serialVersionUID = 1L;
  public HeadCoach() {
    super();
  }
}
