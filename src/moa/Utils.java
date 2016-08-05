/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moa;

import java.util.ArrayList;

/**
 *
 * @author rafaellb
 */
public class Utils {

    public static String[] getEntrada() {
        String ret = "1 6734 1453\n"
                + "2 2233 10\n"
                + "3 5530 1424\n"
                + "4 401 841\n"
                + "5 3082 1644\n"
                + "6 7608 4458\n"
                + "7 7573 3716\n"
                + "8 7265 1268\n"
                + "9 6898 1885\n"
                + "10 1112 2049\n"
                + "11 5468 2606\n"
                + "12 5989 2873\n";
        return ret.split("\n");
    }

    public static Rota gerarSeed() {
        ArrayList<Cidade> seed = new ArrayList<Cidade>();
        Cidade cidade0 = new Cidade();
        cidade0.idCidade = 11;
        cidade0.x = 5468;
        cidade0.y = 2606;
        seed.add(cidade0);
        Cidade cidade1 = new Cidade();
        cidade1.idCidade = 6;
        cidade1.x = 7608;
        cidade1.y = 4458;
        seed.add(cidade1);
        Cidade cidade2 = new Cidade();
        cidade2.idCidade = 3;
        cidade2.x = 5530;
        cidade2.y = 1424;
        seed.add(cidade2);
        Cidade cidade3 = new Cidade();
        cidade3.idCidade = 5;
        cidade3.x = 3082;
        cidade3.y = 1644;
        seed.add(cidade3);
        Cidade cidade4 = new Cidade();
        cidade4.idCidade = 9;
        cidade4.x = 6898;
        cidade4.y = 1885;
        seed.add(cidade4);
        Cidade cidade5 = new Cidade();
        cidade5.idCidade = 4;
        cidade5.x = 401;
        cidade5.y = 841;
        seed.add(cidade5);
        Cidade cidade6 = new Cidade();
        cidade6.idCidade = 7;
        cidade6.x = 7573;
        cidade6.y = 3716;
        seed.add(cidade6);
        Cidade cidade7 = new Cidade();
        cidade7.idCidade = 10;
        cidade7.x = 1112;
        cidade7.y = 2049;
        seed.add(cidade7);
        Cidade cidade8 = new Cidade();
        cidade8.idCidade = 2;
        cidade8.x = 2233;
        cidade8.y = 10;
        seed.add(cidade8);
        Cidade cidade9 = new Cidade();
        cidade9.idCidade = 8;
        cidade9.x = 7265;
        cidade9.y = 1268;
        seed.add(cidade9);
        Cidade cidade10 = new Cidade();
        cidade10.idCidade = 1;
        cidade10.x = 6734;
        cidade10.y = 1453;
        seed.add(cidade10);
        Cidade cidade11 = new Cidade();
        cidade11.idCidade = 12;
        cidade11.x = 6034;
        cidade11.y = 1053;
        seed.add(cidade11);
        Rota rota = new Rota();
        rota.setRota(seed);
        return rota;
    }

    static void genArrayListCidadeCode(Rota rotaY) {
        int f = 0;
        for (Cidade c : rotaY.getRota()) {

            System.out.print(
                    "Cidade cidade" + f + " = new Cidade();\n"
                    + "cidade" + f + ".idCidade = " + c.idCidade + "; "
                    + "cidade" + f + ".x = " + c.x + "; "
                    + "cidade" + f + ".y = " + c.y + "; "
                    + "seed.add(cidade" + f + ");\n"
            );
            f++;
        }

    }

}
