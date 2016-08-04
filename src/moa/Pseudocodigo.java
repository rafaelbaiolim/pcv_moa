package moa;

import java.util.ArrayList;

/**
 *
 * @author guest-NyVQjg
 */
class Before {
/*
    public void avaliacao() {
    }

    public void selecao() {
    }

    public void cruzamento() {
    }

    public double calcularDistancia2Pontos(Cidade cidadeA, Cidade cidadeB) {
        return Math.sqrt(Math.pow((cidadeA.x - cidadeB.x), 2)
                + Math.pow((cidadeA.y - cidadeB.y), 2));
    }

    public ArrayList<Conjunto> gerarDistancias(Conjunto populacao) {
        ArrayList<Cidade> cnjInicial = populacao.getConjuntoCidade();
        //ArrayList<Cidade> cnjPermutado = populacao.cnjPermutado;
        for (int i = 0; i < cnjInicial.size() - 1; i++) {
             populacao.distancia +=
                     calcularDistancia2Pontos(cnjInicial.get(i),cnjInicial.get(i + 1));
             System.out.println(populacao.distancia);
        }
        return null;
        
    }

    //Método de avaliação
    public ArrayList<Conjunto> avaliar(ArrayList<Conjunto> populacao) {
       for(int i = 0; i < populacao.size(); i++) {   
             gerarDistancias(populacao.get(i));
            // System.out.println(populacao.get(i).distancia);
        }
       
        return populacao;
    }

    public void caixeiroViajante(ArrayList<Conjunto> populacaoInicial) {
        int criterioParada = 123;
        //Conjunto populacao = populacaoInicial.get(0).cnjPermutado;
        for(int i = 0; i < populacaoInicial.size(); i++) {   
            //System.out.print(populacaoInicial.size());
        }
        while (criterioParada != 0) {

           // avaliar(populacao.get(0));
            //selecao()
            //cruzamento()
            //mutacao
            //atualizacaoPopulacao(populacao)
            criterioParada--;
        }
    }
    /*
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
        moa.caixeiroViajante(populacao);

    }*/

}
