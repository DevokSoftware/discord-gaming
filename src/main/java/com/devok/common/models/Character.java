package com.devok.common.models;

import javax.persistence.*;

@Entity
@Table(name = "CHARACTER")
@NamedQuery(name = "Character.findCharacterByUserAndServer", query = "select c from Character c where c.characterKeys.serverId = :serverId and c.characterKeys.userId = :userId")
public class Character {

    @EmbeddedId
    private CharacterKeys characterKeys;

    @Column(name = "EXP")
    private int exp;

    @Column(name = "GOLD")
    private int gold;

    public Character(String userId,  String serverId) {
        this.characterKeys = new CharacterKeys(userId, serverId);
    }

    public Character() {}

    public CharacterKeys getCharacterKeys() {
        return characterKeys;
    }

    public void setCharacterKeys(CharacterKeys characterKeys) {
        this.characterKeys = characterKeys;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }
}
