/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfacesGraficas;

/**
 *
 * @author abim-
 */
public class pruebas {

    public pruebas() {

    }

    public void funcionrestricTension() {

        /*Tension-compression Spring*/
        double[] x = {0.052073, 0.366004, 10.767653};

        double ffo = (x[2] + 2) * x[1] * Math.pow(x[0], 2);
        System.out.println("fobj " + ffo);

        double g1 = 1.0 - ((Math.pow(x[1], 3)) * x[2]) / (71785.0 * (Math.pow(x[0], 4)));
        System.out.println("g1 " + g1);
        double g2 = (((4.0 * Math.pow(x[1], 2)) - (x[0] * x[1])) / (12566.0 * ((x[1] * Math.pow(x[0], 3)) - Math.pow(x[0], 4)))) + (1.0 / (5108.0 * Math.pow(x[0], 2))) - 1.0;
        System.out.println("g2 " + g2);
        double g3 = 1.0 - ((140.45 * x[0]) / ((x[1] * x[1]) * x[2]));
        System.out.println("g3 " + g3);
        double g4 = ((x[1] + x[0]) / 1.5) - 1;
        System.out.println("g4 " + g4);

    }

    public void funcionrestricMINLP() {

        /* MINLP */
        double[] x = {0.199804,0.799811,1.907857,1.0,1.0,0.0,1.0};

        double ffo = Math.pow((x[3] - 1), 2) + Math.pow((x[4] - 2), 2) + Math.pow((x[5] - 1), 2) - Math.log((x[6] + 1)) + Math.pow((x[0] - 1), 2) + Math.pow((x[1] - 2), 2) + Math.pow((x[2] - 3), 2);
        System.out.println("fobj " + ffo);

        double g1 = x[3] + x[4] + x[5] + x[0] + x[1] + x[2] - 5;
        System.out.println("g1 " + g1);
        double g2 = Math.pow(x[5], 2) + Math.pow(x[0], 2) + Math.pow(x[1], 2) + Math.pow(x[2], 2) - 5.5;

        System.out.println("g2 " + g2);
        double g3 = x[3] + x[0] - 1.2;
        System.out.println("g3 " + g3);
        double g4 = x[4] + x[1] - 1.8;
        System.out.println("g4 " + g4);
        double g5 = x[5] + x[2] - 2.5;
        System.out.println("g5 " + g5);
        double g6 = x[5] + x[0] - 1.2;
        System.out.println("g6 " + g6);
        double g7 = Math.pow(x[4], 2) + Math.pow(x[1], 2) - 1.64;
        System.out.println("g7 " + g7);
        double g8 = Math.pow(x[5], 2) + Math.pow(x[2], 2) - 4.25;
        System.out.println("g8 " + g8);
        double g9 = Math.pow(x[4], 2) + Math.pow(x[2], 2) - 4.64;
        System.out.println("g9 " + g9);
    }

    public void functionPresion() {

        double aux1 = 0;
        double aux2 = 0;

        /*Pressure Vessel*/
        double[] x = {0.8125, 0.4375, 42.098232, 176.648012};
        aux1 = x[0];
        aux2 = x[1];
        aux1 = Math.floor(aux1) * 0.0625;
        aux2 = Math.floor(aux1) * 0.0625;

        double ffo = (0.6224 * aux1 * x[2] * x[3]) + (1.7781 * aux2 * Math.pow(x[2], 2)) + (3.1661 * Math.pow(aux1, 2) * x[3]) + (19.84 * Math.pow(aux1, 2) * x[2]);
        System.out.println("fobj " + ffo);

        double g1 = (-1 * aux1) + (0.0193 * x[2]);
        System.out.println("g1 " + g1);
        double g2 = (-1 * aux2) + (0.00954 * x[2]);
        System.out.println("g2 " + g2);
        double g3 = ((-1 * Math.PI) * (Math.pow(x[2], 2) * x[3])) - ((4.0 / 3.0) * Math.PI * Math.pow(x[2], 3)) + 1296000;
        System.out.println("g3 " + g3);
        double g4 = x[3] - 240;
        System.out.println("g4 " + g4);
    }

    public void functionQuadratically() {
        /*Quadraticalilly Constrained*/
        double[] x = {-3.170927, 1.712906};

        double ffo = (Math.pow(x[0], 4)) - 14 * (Math.pow(x[0], 2)) + 24 * x[0] - (Math.pow(x[1], 2));
        System.out.println("fobj " + ffo);

        double g1 = (-x[0] + x[1]) - 8;
        System.out.println("g1 " + g1);
        double g2 = x[1] - (Math.pow(x[0], 2)) - (2 * x[0]) + 2;
        System.out.println("g2 " + g2);

    }

    public void functionConcrete() {
        /*Desing Reinforced Concrete*/
        double[] x = {8.180741, 7.543553};

        double ffo = (29.4 * x[0]) + (18 * x[1]);
        System.out.println("fobj " + ffo);

//        double g1 = -(x[0] +((0.2458*Math.pow(x[0],2))/x[1])+6);
        double g1 = x[0] - ((0.2458 * Math.pow(x[0], 2)) / x[1]);
        System.out.println("g1 " + g1);

    }
}
