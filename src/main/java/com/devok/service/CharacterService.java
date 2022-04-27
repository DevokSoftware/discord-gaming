package com.devok.service;

import com.devok.models.Character;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Random;

public class CharacterService {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void createCharacter() {
        Character character = new Character("User"+ new Random().nextInt(), "Channel"+ new Random().nextInt());
        character.setExp(2);
        entityManager.persist(character);
      }
}
