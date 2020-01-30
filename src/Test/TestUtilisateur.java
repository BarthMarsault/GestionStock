package Test;

import Model.Utilisateur;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestUtilisateur {

    Utilisateur user1 = new Utilisateur(1,"Dupont","Jean","123", null);

    @Test
    void should_setMdp_returnFalse_when_wrongPassword() {
        assertFalse(user1.validationMdp("a"));
        assertFalse(user1.validationMdp("A"));
        assertFalse(user1.validationMdp("1"));
        assertFalse(user1.validationMdp("a1aaaaa"));
        assertFalse(user1.validationMdp("aAaaaaa"));
        assertFalse(user1.validationMdp("aA1aa"));
    }

    @Test
    void should_setMdp_returnTrue_when_correctPassword(){
        assertTrue(user1.validationMdp("aA1aaa"));
    }

}