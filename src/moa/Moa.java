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
        int j = 0, sizeRotas = rotaX.getRota().size();

        HashMap<Integer, Integer> novaRotaA = new HashMap<>();
        HashMap<Integer, Integer> novaRotaB = new HashMap<>();
        inicializarNovasRotas(novaRotaA, novaRotaB, sizeRotas);
        for (int i = meio - pontoCorte; i <= meio + pontoCorte; i++) {
            novaRotaA.put(i, rotaX.getRota().get(i).idCidade);
            novaRotaB.put(i, rotaY.getRota().get(i).idCidade);
        }

        boolean inHash = false;
        for (int i = 0; i < sizeRotas; i++) {
            if (novaRotaA.get(i) == -1) {
                inHash = false;
                while (!inHash) {
                    if (!novaRotaA.containsValue(rotaY.getRota().get(j).idCidade)) {
                        novaRotaA.put(i, rotaY.getRota().get(j).idCidade);
                        inHash = true;
                    }
                    j++;
                }
            }
        }
        j = 0;
        for (int i = 0; i < sizeRotas; i++) {
            if (novaRotaB.get(i) == -1) {
                inHash = false;
                while (!inHash) {
                    if (!novaRotaB.containsValue(rotaX.getRota().get(j).idCidade)) {
                        novaRotaB.put(i, rotaX.getRota().get(j).idCidade);
                        inHash = true;
                    }
                    j++;
                }
            }
        }

        List<Integer> listRetA = new ArrayList<>(novaRotaA.values());
        List<Integer> listRetB = new ArrayList<>(novaRotaB.values());
        /*
        for (Integer c : listRetA) {
            System.out.print(c + " ");
        }

        System.out.println(
                "");
        for (Integer c : listRetB) {
            System.out.print(c + " ");

        }*/

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
        populacao.add(Utils.gerarSeed());
        populacao.add(conjunto);
        moa.avaliacao(populacao);
        moa.getSelecao(populacao);
    }

}
