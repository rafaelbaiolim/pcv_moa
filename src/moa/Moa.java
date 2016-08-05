package moa;

import java.util.Collections;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

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
            cidade.idCidade = Integer.parseInt(arrCordenada[0]);
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

    protected Rota gerarDistancias(Rota populacao) {
        ArrayList<Cidade> cnjInicial = populacao.getRota();
        //ArrayList<Cidade> cnjPermutado = populacao.cnjPermutado;
        for (int i = 0; i < cnjInicial.size() - 1; i++) {
            populacao.distancia
                    += calcularDistancia2Pontos(cnjInicial.get(i), cnjInicial.get(i + 1));
        }
        //System.out.println(populacao.distancia);
        return populacao;
    }

    public void avaliacao(ArrayList<Rota> populacao) {
        for (int i = 0; i < populacao.size(); i++) {
            this.gerarDistancias(populacao.get(i));
            //System.out.println("life " + populacao.get(1).distancia);
        }
    }

    protected void getSelecao(ArrayList<Rota> populacao) {
        PriorityQueue<Rota> Q = new PriorityQueue<>(Collections.reverseOrder());
        for (int i = 0; i < populacao.size(); i++) {
            Q.add(populacao.get(i));
        }
        Rota rotaX = Q.remove();
        Rota rotaY = Q.remove();
        //Rota rotaK = Q.remove();
        //System.out.println(rotaX.distancia);
        //System.out.println(rotaY.distancia);
        // System.out.println(rotaK.distancia);

        cruzamento(rotaX, rotaY);
    }

    public void cruzamento(Rota rotaX, Rota rotaY) {

//        get Indices para o ponto de corte
        int meio = Math.round(rotaX.getRota().size() / 2);
        int pontoCorte = Math.round(rotaX.getRota().size() / 8);
        HashMap<Integer, Cidade> novaRotaA = new HashMap<>();
        HashMap<Integer, Cidade> novaRotaB = new HashMap<>();
        //Preenche os Valores fixos 
        for (int i = meio - pontoCorte; i < meio + pontoCorte; i++) {
            novaRotaA.put(i, rotaX.getRota().get(i));
            novaRotaB.put(i, rotaY.getRota().get(i));
        }

        //Cria a hash de filhos 
        for (int i = 0; i < rotaX.getRota().size(); i++) {
            if (novaRotaA.get(i) == null) {
                novaRotaA.put(i, rotaY.getRota().get(i));
            }

            if (novaRotaB.get(i) == null) {
                novaRotaB.put(i, rotaX.getRota().get(i));
            }
        }

        List<Cidade> listRetA = new ArrayList<>(novaRotaA.values());
        List<Cidade> listRetB = new ArrayList<>(novaRotaB.values());

    }

    public static void main(String[] args) {
        Moa moa = new Moa();
        // Rota entrada 
        Rota conjunto = new Rota();
        // Atribui a rota inicial do conjunto 
        conjunto.setRota(moa.getRotaInicial());

        // Rota Incial Permutado 
        Rota conjuntoPermutado = new Rota();
        conjuntoPermutado.setRota(conjunto.getPermutacaoCnjCidade(conjunto.getRota()));

        ArrayList<Rota> populacao = new ArrayList<>();
        populacao.add(conjuntoPermutado);
        populacao.add(conjunto);
        moa.avaliacao(populacao);
        moa.getSelecao(populacao);
    }

}
