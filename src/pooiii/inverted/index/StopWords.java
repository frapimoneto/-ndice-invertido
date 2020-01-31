package pooiii.inverted.index;

import java.util.ArrayList;

/**
 *
 * @author francisco
 */

//pavras que não fazem diferença na busca
public class StopWords extends ArrayList<String> {

    public StopWords() {
        super();
        this.add("ao");
        this.add("as");
        this.add("com");
        this.add("como");
        this.add("da");
        this.add("das");
        this.add("de");
        this.add("do");
        this.add("dos");
        this.add("em");
        this.add("lhe");
        this.add("na");
        this.add("nao");
        this.add("no");
        this.add("para");
        this.add("pra");
        this.add("os");
        this.add("por");
        this.add("que");
        this.add("se");
        this.add("sem");
        this.add("um");
        this.add("uma");
    }
}
