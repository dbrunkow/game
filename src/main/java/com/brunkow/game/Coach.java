package com.brunkow.game;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "COACH")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Coach implements Serializable {
        private static final Logger logger = LoggerFactory.getLogger(Coach.class);
        private static final long serialVersionUID = 1L;

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String firstName;
        private String lastName;
        private int power;

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public String getFirstName() {
                return firstName;
        }

        public void setFirstName(String firstName) {
                this.firstName = firstName;
        }

        public String getLastName() {
                return lastName;
        }

        public void setLastName(String lastName) {
                this.lastName = lastName;
        }

        public int getPower() {
                return power;
        }

        public void setPower(int power) {
                this.power = power;
        }
}
