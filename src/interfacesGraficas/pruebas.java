
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
        double[] x = {0.199804, 0.799811, 1.907857, 1.0, 1.0, 0.0, 1.0};

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

    public void funcionG01() {

        /*Tension-compression Spring*/
        double[] x = {1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 3.0, 3.0, 3.0, 1.0};

        double ffo = (5 * (x[0] + x[1] + x[2] + x[3])) - (5 * (Math.pow(x[0], 2) + Math.pow(x[1], 2) + Math.pow(x[2], 2) + Math.pow(x[3], 2))) - (x[4] + x[5] + x[6] + x[7] + x[8] + x[9] + x[10] + x[11] + x[12]);
        System.out.println("fobj " + ffo);

        double g1 = (2 * x[0]) + (2 * x[1]) + x[9] + x[10] - 10;
        System.out.println("g1 " + g1);
        double g2 = (2 * x[0]) + (2 * x[2]) + x[9] + x[11] - 10;
        System.out.println("g2 " + g2);
        double g3 = (2 * x[1]) + (2 * x[2]) + x[10] + x[11] - 10;
        System.out.println("g3 " + g3);
        double g4 = -8 * x[0] + x[9];
        System.out.println("g4 " + g4);
        double g5 = -8 * x[1] + x[10];
        System.out.println("g5 " + g5);
        double g6 = -8 * x[2] + x[11];
        System.out.println("g6 " + g6);
        double g7 = (-2 * x[3]) - x[4] + x[9];
        System.out.println("g7 " + g7);
        double g8 = (-2 * x[5]) - x[6] + x[10];
        System.out.println("g8 " + g8);
        double g9 = (-2 * x[7]) - x[8] + x[11];
        System.out.println("g9 " + g9);

    }

    public void funcionG02() {

        /*Tension-compression Spring*/
        double[] x = {3.16246061572185, 3.12833142812967, 3.09479212988791, 3.06145059523469, 3.02792915885555, 2.99382606701730, 2.95866871765285, 2.92184227312450, 0.49482511456933, 0.48835711005490, 0.48231642711865, 0.47664475092742, 0.47129550835493, 0.46623099264167, 0.46142004984199, 0.45683664767217, 0.45245876903267, 0.44826762241853, 0.44424700958760, 0.44038285956317};

        double ffo = -Math.abs((Math.pow(Math.cos(x[0]), 4) + Math.pow(Math.cos(x[1]), 4) + Math.pow(Math.cos(x[2]), 4) + Math.pow(Math.cos(x[3]), 4) + Math.pow(Math.cos(x[4]), 4)
                + Math.pow(Math.cos(x[5]), 4) + Math.pow(Math.cos(x[6]), 4) + Math.pow(Math.cos(x[7]), 4) + Math.pow(Math.cos(x[8]), 4) + Math.pow(Math.cos(x[9]), 4)
                + Math.pow(Math.cos(x[10]), 4) + Math.pow(Math.cos(x[11]), 4) + Math.pow(Math.cos(x[12]), 4) + Math.pow(Math.cos(x[13]), 4) + Math.pow(Math.cos(x[14]), 4)
                + Math.pow(Math.cos(x[15]), 4) + Math.pow(Math.cos(x[16]), 4) + Math.pow(Math.cos(x[17]), 4) + Math.pow(Math.cos(x[18]), 4) + Math.pow(Math.cos(x[19]), 4)
                - 2 * (Math.pow(Math.cos(x[0]), 2) * Math.pow(Math.cos(x[1]), 2) * Math.pow(Math.cos(x[2]), 2) * Math.pow(Math.cos(x[3]), 2) * Math.pow(Math.cos(x[4]), 2)
                * Math.pow(Math.cos(x[5]), 2) * Math.pow(Math.cos(x[6]), 2) * Math.pow(Math.cos(x[7]), 2) * Math.pow(Math.cos(x[8]), 2) * Math.pow(Math.cos(x[9]), 2)
                * Math.pow(Math.cos(x[10]), 2) * Math.pow(Math.cos(x[11]), 2) * Math.pow(Math.cos(x[12]), 2) * Math.pow(Math.cos(x[13]), 2) * Math.pow(Math.cos(x[14]), 2)
                * Math.pow(Math.cos(x[15]), 2) * Math.pow(Math.cos(x[16]), 2) * Math.pow(Math.cos(x[17]), 2) * Math.pow(Math.cos(x[18]), 2) * Math.pow(Math.cos(x[19]), 2)))
                / Math.sqrt((x[0] * Math.pow(x[0], 2)) + (x[1] * Math.pow(x[1], 2)) + (x[2] * Math.pow(x[2], 2)) + (x[3] * Math.pow(x[3], 2)) + (x[4] * Math.pow(x[4], 2)) + (x[5] * Math.pow(x[5], 2))
                        + (x[6] * Math.pow(x[6], 2)) + (x[7] * Math.pow(x[7], 2)) + (x[8] * Math.pow(x[8], 2)) + (x[9] * Math.pow(x[9], 2)) + (x[10] * Math.pow(x[10], 2)) + (x[11] * Math.pow(x[11], 2))
                        + (x[12] * Math.pow(x[12], 2)) + (x[13] * Math.pow(x[13], 2)) + (x[14] * Math.pow(x[14], 2)) + (x[15] * Math.pow(x[15], 2)) + (x[16] * Math.pow(x[16], 2)) + (x[17] * Math.pow(x[17], 2))
                        + (x[18] * Math.pow(x[18], 2)) + (x[19] * Math.pow(x[19], 2))));
        System.out.println("fobj " + ffo);

        double g1 = 0.75 - (x[0] * x[1] * x[2] * x[3] * x[4] * x[5] * x[6] * x[7] * x[8] * x[9] * x[10] * x[11] * x[12] * x[13] * x[14] * x[15] * x[16] * x[17] * x[18] * x[19]);
        System.out.println("g1 " + g1);
        double g2 = (x[0] + x[1] + x[2] + x[3] + x[4] + x[5] + x[6] + x[7] + x[8] + x[9] + x[10] + x[11] + x[12] + x[13] + x[14] + x[15] + x[16] + x[17] + x[18] + x[19]) - (7.5 * 20);

    }

    public void funcionG04() {

        /*Tension-compression Spring*/
        double[] x = {78.0, 33.0, 29.9952560256815985, 45.0, 36.7758129057882073};

        double ffo = 5.3578547 * Math.pow(x[2], 2) + 0.8356891 * x[0] * x[4] + 37.293239 * x[0] - 40792.141;
        System.out.println("fobj " + ffo);

        double g1 = 85.334407 + 0.0056858 * x[1] * x[4] + 0.0006262 * x[0] * x[3] - 0.0022053 * x[2] * x[4] - 92;
        System.out.println("g1 " + g1);
        double g2 = -85.334407 - (0.0056858 * (x[1] * x[4])) - (0.0006262 * (x[0] * x[3])) + (0.0022053 * (x[2] * x[4]));
        System.out.println("g2 " + g2);
        double g3 = 80.51249 + (0.0071317 * (x[1] * x[4])) + (0.0029955 *( x[0] * x[1])) + (0.0021813 * (Math.pow(x[2], 2))) - 110;
        System.out.println("g3 " + g3);
        double g4 = -80.51249 - 0.0071317 * x[1] * x[4] - 0.0029955 * x[0] * x[1] - 0.0021813 * (Math.pow(x[2], 2)) + 90;
        System.out.println("g4 " + g4);
        double g5 = 9.300961 + (0.0047026 * (x[2] * x[4])) + (0.0012547 * (x[0] * x[2])) + (0.0019085 *( x[2] * x[3])) - 25;
        System.out.println("g5 " + g5);
        double g6 = -9.300961 - (0.0047026 * (x[2] * x[4])) - (0.0012547 * (x[0] * x[2])) - (0.0019085 * (x[2] * x[3])) + 20;
        System.out.println("g6 " + g6);

    }
        public void funcionG24() {

        
        double[] x = {2.32952019747762,3.17849307411774};

        double ffo = -x[0]-x[1];
        System.out.println("fobj " + ffo);

        double g1 = -2 * Math.pow(x[0], 4) + 8 * Math.pow(x[0], 3) + x[1] - 2;
        System.out.println("g1 " + g1);
        double g2 = -4 * Math.pow(x[0], 4) + 32 * Math.pow(x[0], 3) - 88 * Math.pow(x[0], 2) + 96 * x[0] + x[1] - 36;
        System.out.println("g2 " + g2);


    }
}
