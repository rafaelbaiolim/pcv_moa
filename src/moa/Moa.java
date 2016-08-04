package moa;

import java.util.ArrayList;

/**
 *
 * @author guest-NyVQjg
 */
class Moa {

    public double calcularDistancia2Pontos(Cidade cidadeA, Cidade cidadeB) {
        return Math.sqrt(Math.pow((cidadeA.x - cidadeB.x), 2)
                + Math.pow((cidadeA.y - cidadeB.y), 2));
    }

    public Conjunto gerarDistancias(Conjunto populacao) {
        ArrayList<Cidade> cnjInicial = populacao.getConjuntoCidade();
        //ArrayList<Cidade> cnjPermutado = populacao.cnjPermutado;
        for (int i = 0; i < cnjInicial.size() - 1; i++) {
            populacao.distancia
                    += calcularDistancia2Pontos(cnjInicial.get(i), cnjInicial.get(i + 1));
        }
        System.out.println(populacao.distancia);
        return populacao;

    }

    public void avaliacao(ArrayList<Conjunto> populacao) {
        for (int i = 0; i < populacao.size(); i++) {
            this.gerarDistancias(populacao.get(i));
            System.out.println("life " + populacao.get(1).distancia);
        }

    }

    public void selecao() {
    }

    public void cruzamento() {
    }

    public static void main(String[] args) {
        Moa moa = new Moa();
        // Conjunto entrada 
        Conjunto conjunto = new Conjunto();

        // Conjunto Incial Permutado 
        ArrayList<Cidade> conjuntoPermutado = new ArrayList<>();
        System.out.println(conjunto.getConjuntoCidade().get(0).nome);
            
        conjuntoPermutado = conjunto.getPermutacaoCnjCidade(conjunto.getConjuntoCidade());
        System.out.println(conjuntoPermutado.get(0).nome);
        ArrayList<Conjunto> populacao = new ArrayList<>();

        populacao.add(conjunto);
        //populacao.add(conjuntoPermutado);
        //moa.caixeiroViajante(populacao);
        moa.avaliacao(populacao);

    }

}
