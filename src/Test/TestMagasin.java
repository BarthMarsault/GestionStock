package Test;

import Model.Magasin;
import Model.Utilisateur;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TestMagasin {

    Magasin magasin = new Magasin(new ArrayList<>(), new ArrayList<>());
    Utilisateur user = new Utilisateur(10,"Dupont","Jean","123",null);



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
        assertNotEquals(new Utilisateur(1,"","","",null), magasin.getUtilisateurById(10));
    }

    @Test
    void should_getLastIdUtilisateur_return11_when_higherIdIs10(){
        magasin.addUtilisateur(user);
        assertEquals(11,magasin.getLastIdUtilisateur());
        assertNotEquals(8,magasin.getLastIdUtilisateur());
    }

    @Test
    void should_getLastIdUtilisateur_return1_when_noUserInMagasin(){
        assertNotEquals(0,magasin.getLastIdUtilisateur());
        assertEquals(1,magasin.getLastIdUtilisateur());
    }


}