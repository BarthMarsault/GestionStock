package Test;

import Model.Utilisateur;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestUtilisateur {

    Utilisateur user1 = new Utilisateur(1,"Dupont","Jean","123",null);

    @Test
    void should_validationMdp_returnFalse_when_wrongPassword() {
        assertFalse(user1.validationMdp("a"));
        assertFalse(user1.validationMdp("A"));
        assertFalse(user1.validationMdp("1"));
        assertFalse(user1.validationMdp("a1aaaaa"));
        assertFalse(user1.validationMdp("aAaaaaa"));
        assertFalse(user1.validationMdp("aA1aa"));
    }

    @Test
    void should_validationMdp_returnTrue_when_correctPassword(){
        assertTrue(user1.validationMdp("aA1aaa"));
    }


    @Test
    void should_validationNomPrenom_returnFalse_when_invalidName(){
        assertFalse(user1.validationNomPrenom("Jean50"));
        assertFalse(user1.validationNomPrenom("85"));
        assertFalse(user1.validationNomPrenom("aze//"));
        assertFalse(user1.validationNomPrenom("test test"));
    }

    @Test
    void should_validationNomPrenom_returnTrue_when_validName(){
        assertTrue(user1.validationNomPrenom("Jean"));
        assertTrue(user1.validationNomPrenom("Jean-Baptiste"));
    }

}