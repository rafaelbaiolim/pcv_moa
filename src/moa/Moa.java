package moa;

import java.util.Collections;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.PriorityQueue;

/**
 *
 * @author guest-NyVQjg
 */
class Moa {

    static ArrayList<Rota> populacao = new ArrayList<>();
    static final int TOTAL_FOR = 12;

    protected ArrayList<Cidade> getRotaInicial() {
        ArrayList<Cidade> rotaInicial = new ArrayList<>();
        String[] entrada = Utils.getEntrada();
        for (int i = 0; i < TOTAL_FOR; i++) {
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

    public void gerarMutacao(Rota perA, Rota perB) {
        Rota mutA = new Rota();
        Rota mutB = new Rota();

        mutA.setRota(perA.getPermutacaoCnjCidade(perA.getRota()));
        mutB.setRota(perB.getPermutacaoCnjCidade(perB.getRota()));

    }

    public void preencherNovaRota(
            Rota rotaX, Rota rotaY,
            HashMap<Integer, Integer> novaRotaA,
            int sizeRotas,
            HashMap<Integer, Cidade> hashFinder
    ) {
        boolean inHash = false;
        int j = 0;

        for (int i = 0; i < sizeRotas; i++) {
            if (novaRotaA.get(i) == -1) {
                inHash = false;
                while (!inHash) {

                    if (!novaRotaA.containsValue(rotaY.getRota().get(j).idCidade)) {
                        novaRotaA.put(i, rotaY.getRota().get(j).idCidade);
                        hashFinder.put(i, rotaY.getRota().get(j));
                        inHash = true;
                    }
                    j++;
                }
            }
        }

    }

    protected void inicializarNovasRotas(HashMap<Integer, Integer> novaRotaA,
            HashMap<Integer, Integer> novaRotaB, int sizeRotas) {
        for (int i = 0; i < sizeRotas; i++) {
            novaRotaA.put(i, -1);
            novaRotaB.put(i, -1);
        }
    }

    public void cruzamento(Rota rotaX, Rota rotaY) {

        int meio = Math.round(rotaX.getRota().size() / 2) - 1;
        int pontoCorte = Math.round(rotaX.getRota().size() / 8);
        int sizeRotas = rotaX.getRota().size();
        boolean inHash = false;

        HashMap<Integer, Integer> novaRotaA = new HashMap<>();
        HashMap<Integer, Integer> novaRotaB = new HashMap<>();
        HashMap<Integer, Cidade> hashFinderA = new HashMap<>();
        HashMap<Integer, Cidade> hashFinderB = new HashMap<>();
        inicializarNovasRotas(novaRotaA, novaRotaB, sizeRotas);

        for (int i = meio - pontoCorte; i <= meio + pontoCorte; i++) {
            novaRotaA.put(i, rotaX.getRota().get(i).idCidade);
            hashFinderA.put(i, rotaX.getRota().get(i));
            novaRotaB.put(i, rotaY.getRota().get(i).idCidade);
            hashFinderB.put(i, rotaY.getRota().get(i));
        }

        preencherNovaRota(rotaX, rotaY, novaRotaA, sizeRotas, hashFinderA);
        preencherNovaRota(rotaY, rotaX, novaRotaB, sizeRotas, hashFinderB);

        ArrayList<Cidade> listRetA = new ArrayList<>(hashFinderA.values());
        ArrayList<Cidade> listRetB = new ArrayList<>(hashFinderB.values());
        Rota rotaA = new Rota();
        rotaA.setRota(listRetA);
        gerarDistancias(rotaA);
        populacao.add(rotaA);

        Rota rotaB = new Rota();
        rotaB.setRota(listRetB);
        gerarDistancias(rotaB);
        populacao.add(rotaB);
        gerarMutacao(rotaA, rotaB);

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

        populacao.add(Utils.gerarSeed());
        populacao.add(conjunto);
        moa.avaliacao(populacao);
        moa.getSelecao(populacao);
    }

}
