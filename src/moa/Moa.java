package moa;

import java.util.ArrayList;

/**
 *
 * @author guest-NyVQjg
 */
class Moa {

    protected ArrayList<Cidade> getRotaInicial() {
        ArrayList<Cidade> rotaInicial = new ArrayList<>();
        String[] entrada = Utils.getEntrada();
        for (int i = 0; i < 48; i++) {
            Cidade cidade = new Cidade();
            String[] arrCordenada = entrada[i].split(" ");
            cidade.nome = arrCordenada[0];
            cidade.x = Integer.parseInt(arrCordenada[1]);  // coordenada X
            cidade.y = Integer.parseInt(arrCordenada[2]); // coordenada Y
            rotaInicial.add(cidade);
        }
        return rotaInicial;
    }

    protected double calcularDistancia2Pontos(Cidade cidadeA, Cidade cidadeB) {
        return Math.sqrt(Math.pow((cidadeA.x - cidadeB.x), 2)
                + Math.pow((cidadeA.y - cidadeB.y), 2));
    }

    public Rota gerarDistancias(Rota populacao) {
        ArrayList<Cidade> cnjInicial = populacao.getRota();
        //ArrayList<Cidade> cnjPermutado = populacao.cnjPermutado;
        for (int i = 0; i < cnjInicial.size() - 1; i++) {
            populacao.distancia
                    += calcularDistancia2Pontos(cnjInicial.get(i), cnjInicial.get(i + 1));
        }
        System.out.println(populacao.distancia);
        return populacao;

    }

    public void avaliacao(ArrayList<Rota> populacao) {
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
        // Rota entrada 
        Rota conjunto = new Rota();
        // Atribui a rota inicial do conjunto 
        conjunto.setRota(moa.getRotaInicial());

        // Rota Incial Permutado 
        Rota conjuntoPermutado = new Rota();
        System.out.println(conjunto.getRota().get(0).nome);

        
        conjuntoPermutado.setRota(conjunto.getPermutacaoCnjCidade(conjunto.getRota()));
        System.out.println(conjuntoPermutado.getRota().get(0).nome);
        ArrayList<Rota> populacao = new ArrayList<>();
        populacao.add(conjuntoPermutado);
        populacao.add(conjunto);
        moa.avaliacao(populacao);

    }

}
