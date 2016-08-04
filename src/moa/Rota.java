package moa;

import java.util.ArrayList;
import java.util.*;

/**
 *
 * @author guest-NyVQjg
 */
class Rota {

    double distancia;
    ArrayList<Cidade> rota;

    Rota() {
        this.distancia = 0;
        rota = new ArrayList<>();
    }

    protected void setRota(ArrayList<Cidade> rota) {
        this.rota = rota;
    }

    protected ArrayList<Cidade> getRota() {
        return this.rota;
    }

    protected ArrayList<Cidade> getPermutacaoCnjCidade(ArrayList<Cidade> cnjInicial) {
        Collections.shuffle(cnjInicial);
        return cnjInicial;
    }

}
