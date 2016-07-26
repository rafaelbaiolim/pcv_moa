/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moa;

import java.util.ArrayList;

/**
 *
 * @author guest-NyVQjg
 */
class Moa {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        // Conjunto entrada 
        Conjunto conjunto = new Conjunto();

        // Conjunto Incial Permutado 
        conjunto.cnjPermutado = conjunto.getPermutacaoCnjCidade(conjunto.getConjuntoCidade());
        ArrayList<Conjunto> populacao = new ArrayList<>();
        populacao.add(conjunto);

    }

}
