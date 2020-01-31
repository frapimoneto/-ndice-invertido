/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pooiii.inverted.index;

import java.util.HashMap;

/**
 *
 * @author francisco
 */
public class InvertedIndex extends HashMap<String, Occurrence> {

    public InvertedIndex() {
        super();
    }

    public void add(String key, String path) { //salva a palavra e o caminho dela
        Occurrence oc = get(key); //instancia o occurence o que permite salvar a palavra e o numero de ocorrencias dela
        if (oc != null) {
            try {
                int counter = oc.get(path); // instancia um contador
                oc.put(path, counter+1); // adiciona a palavra e seu numero de aparições
            } catch (Exception e) {
                oc.put(path, 1);
            }
        } else {
            oc = new Occurrence();
            oc.put(path, 1); //coloca valor um caso ela apareca so uma vez
            put(key, oc);
        }
    }
}
