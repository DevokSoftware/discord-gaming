package com.devok.common.services;

import com.devok.common.models.Character;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Random;

public class CharacterService {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void createCharacter() {
        Character character = new Character("User" + new Random().nextInt(), "Channel" + new Random().nextInt());
        character.setExp(2);
        entityManager.persist(character);
    }

    public Character fetchCharacterByUserIdAndServer(String userId, String serverId){
        try{
            return entityManager.createNamedQuery("Character.findCharacterByUserAndServer", Character.class)
                    .setParameter("userId", userId)
                    .setParameter("serverId", serverId)
                    .getSingleResult();
        }catch (NoResultException nre){
            System.out.println("Character not found.");
            return null;
        }
    }

    public int calculateLevel(int exp) {
        return (int) (Math.sqrt(exp / 20d) - 1);
    }
}
