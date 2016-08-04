package moa;

import java.util.ArrayList;

/**
 *
 * @author guest-NyVQjg
 */
class Moa {

   
    public static void main(String[] args) {
        Moa moa = new Moa();
        // Conjunto entrada 
        Conjunto conjunto = new Conjunto();

        // Conjunto Incial Permutado 
        Conjunto conjuntoPermutado  = new Conjunto();
        conjunto.getPermutacaoCnjCidade(conjunto.getConjuntoCidade());
        ArrayList<Conjunto> populacao = new ArrayList<>();
        populacao.add(conjunto);
        populacao.add(conjuntoPermutado);
        //moa.caixeiroViajante(populacao);

    }

}
