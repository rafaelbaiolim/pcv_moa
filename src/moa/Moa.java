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

    public void cruzamento(Rota rotaX, Rota rotaY) {

//        get Indices para o ponto de corte
        int meio = Math.round(rotaX.getRota().size() / 2) - 1;
        int pontoCorte = Math.round(rotaX.getRota().size() / 8);
        HashMap<Integer, Integer> novaRotaA = new HashMap<>();
        HashMap<Integer, Integer> indicesA = new HashMap<>();

        HashMap<Integer, Integer> novaRotaB = new HashMap<>();
        HashMap<Integer, Integer> indicesB = new HashMap<>();

        for (int i = 0; i < rotaX.getRota().size(); i++) {
            novaRotaA.put(i, -1);
            novaRotaB.put(i, -1);

        }

        //Preenche os Valores fixos 
        //Vertice meio e corte OK indicess: 4, 5, 6.
        for (int i = meio - pontoCorte; i <= meio + pontoCorte; i++) {
            novaRotaA.put(i, rotaX.getRota().get(i).idCidade);
            indicesA.put(rotaX.getRota().get(i).idCidade, i);
            novaRotaB.put(i, rotaY.getRota().get(i).idCidade);
            indicesB.put(rotaY.getRota().get(i).idCidade, i);
        }
        System.out.println(novaRotaA.containsValue(4));
        //Cria a hash de filhos 
        for (int i = 0; i < rotaX.getRota().size(); i++) {
            if (novaRotaA.get(i) == -1) {
                for (int j = i; j < rotaX.getRota().size(); j++) {
                    if (indicesA.get(rotaY.getRota().get(j).idCidade) == null) {
                        novaRotaA.put(j, rotaY.getRota().get(j).idCidade);
                        break;
                    }
                }
            }
        }
        /*  for (int j = 0; j < rotaX.getRota().size(); j++) {
            if ((novaRotaB.get(j) == -1)
                    && rotaX.getRota().get(j).idCidade != novaRotaB.get(i)) {
                novaRotaB.put(j, rotaX.getRota().get(j).idCidade);
                break;
            }
        }*/

        List<Integer> listRetA = new ArrayList<>(novaRotaA.values());
        List<Integer> listRetB = new ArrayList<>(novaRotaB.values());

        for (Integer c : listRetA) {
            System.out.print(c + " ");
        }

        System.out.println(
                "");
        for (Integer c : listRetB) {
            System.out.print(c + " ");

        }

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
