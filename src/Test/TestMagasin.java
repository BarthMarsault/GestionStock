package Test;

import Model.Magasin;
import Model.Utilisateur;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TestMagasin {

    Magasin magasin = new Magasin(new ArrayList<>(), new ArrayList<>());
    Utilisateur user = new Utilisateur(10,"Dupont","Jean","123");



    @Test
    public void should_getUtilisateurById_ReturnNull_when_noExistantUser(){
        assertNull(magasin.getUtilisateurById(1));
    }

    @Test
    public void should_getUtilisateurById_ReturnUser_when_idValid(){
        magasin.addUtilisateur(user);
        assertNotNull(magasin.getUtilisateurById(10));
        assertEquals(user, magasin.getUtilisateurById(10));
    }

    @Test
    public void should_getUtilisateurById_ReturnNull_when_searchedUserDoesntExist(){
        magasin.addUtilisateur(user);
        assertNull(magasin.getUtilisateurById(888));
        assertNotEquals(new Utilisateur("","","",null), magasin.getUtilisateurById(10));
    }





}