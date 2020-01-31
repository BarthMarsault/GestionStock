package Test;

import Model.Article;
import Model.Rayon;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class testRayon {

    Article article1 = new Article("E345","Pantalon",74,"Pantalon de bonne qualité.");
    Article article2 = new Article("H935","Pull",30,"Pull de bonne qualité.");
    Rayon rayon = new Rayon("Vêtements",new ArrayList<Article>(Arrays.asList(article1, article2)));

    @Test
    void should_addArticle_not_add_article_in_rayon_articleList_when_article_null() {
        Article article = null;
        rayon.addArticle(article);
        Assertions.assertEquals(false, rayon.getListeArticles().contains(article));
    }

    @Test
    void should_addArticle_add_article_in_rayon_articleList_when_article_is_good() {
        Article article = new Article("R545","Jean",34,"Jean de bonne qualité.");
        rayon.addArticle(article);
        Assertions.assertEquals(true, rayon.getListeArticles().contains(article));
    }

    @Test
    void should_addArticle_not_add_article_in_rayon_articleList_when_article_is_already_in() {
        rayon.addArticle(article1);
        Assertions.assertEquals(1, Collections.frequency(rayon.getListeArticles(), article1));
    }

    @Test
    void should_updateArticle_update_article_when_new_article_is_good() {
        Article article = new Article("K045","Chapeau",21,"Chapeau de bonne qualité.");
        rayon.updateArticle(article1, article);
        Assertions.assertEquals(true, rayon.getListeArticles().contains(article));
        Assertions.assertEquals(false,rayon.getListeArticles().contains(article1));
    }

    @Test
    void should_updateArticle_not_update_article_when_new_article_is_null() {
        Article article = null;
        rayon.updateArticle(article1, article);
        Assertions.assertEquals(false, rayon.getListeArticles().contains(article));
        Assertions.assertEquals(true, rayon.getListeArticles().contains(article1));
    }

    @Test
    void should_updateArticle_not_update_article_when_new_article_is_already_in() {
        Article article = article2;
        rayon.updateArticle(article1, article);

        Assertions.assertEquals(1, Collections.frequency(rayon.getListeArticles(), article2));
        Assertions.assertEquals(true, rayon.getListeArticles().contains(article1));
    }

    @Test
    void should_updateArticle_not_update_article_when_old_article_is_not_in() {
        Article article = new Article("J025","Gants",91,"Gants de bonne qualité.");
        Article unkAritcle = new Article("U305","Chaussettes",161,"Chaussettes de bonne qualité.");
        rayon.updateArticle(unkAritcle, article);

        Assertions.assertEquals(false, rayon.getListeArticles().contains(unkAritcle));
        Assertions.assertEquals(false, rayon.getListeArticles().contains(article));
    }

    @Test
    void should_deleteArticle_delete_article_in_rayon_articleList_when_article_is_good() {
        rayon.deleteArticle(article1);
        Assertions.assertEquals(false, rayon.getListeArticles().contains(article1));
        Assertions.assertEquals(true, rayon.getListeArticles().contains(article2));
    }

}
