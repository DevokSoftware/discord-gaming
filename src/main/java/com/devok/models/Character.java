package com.devok.models;

import javax.persistence.*;

@Entity
@Table(name = "CHARACTER")
public class Character {

    @EmbeddedId

    private CharacterKeys characterKeys;
    @Column(name = "EXP")
    private int exp;

    public Character(String userId,  String channelId) {
        this.characterKeys = new CharacterKeys(userId, channelId);
    }

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
}
