/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ponr;

/**
 *
 * @author abim-
 */
public class G01_CEC2006 extends PONR {

    public G01_CEC2006() {
        this.setNombre("G01_CEC2006");
        this.setMejorConocido(-15.000000);
        this.setNumVariables(13);

        this.setOrdenVariables(new String[]{
            "x1", "x2", "x3", "X4", "X5", "X6", "X7", "X8", "X9", "X10", "X11", "X12", "X13"
        }
        );

        this.setVariableDiscreta(new boolean[]{
            false, false, false, false, false, false, false, false, false, false, false, false, false
        }
        );

        this.setRangos(new double[][]{
            {0.0, 1.0},
            {0.0, 1.0},
            {0.0, 1.0},
            {0.0, 1.0},
            {0.0, 1.0},
            {0.0, 1.0},
            {0.0, 1.0},
            {0.0, 1.0},
            {0.0, 1.0},
            {0.0, 100.0},
            {0.0, 100.0},
            {0.0, 100.0},
            {0.0, 1.0}// indice de pertenencia                         
        }
        );

        this.setResDesigualdad(new double[9]);
        this.setComparacionRestriccion(new double[]{
            0, 0, 0, 0, 0, 0, 0, 0, 0
        }
        );
    }

    @Override
    public double[][] evaluarFO(double[][] x) {
//        for (int t = 0; t < x.length; t++) {
//            if (x[t][0] <= 0.5) {
//                x[t][0] = 0;
//            } else {
//                x[t][0] = 1;
//            }
//            if (x[t][1] <= 0.5) {
//                x[t][1] = 0;
//            } else {
//                x[t][1] = 1;
//            }
//            if (x[t][2] <= 0.5) {
//                x[t][2] = 0;
//            } else {
//                x[t][2] = 1;
//            }
//            if (x[t][3] <= 0.5) {
//                x[t][3] = 0;
//            } else {
//                x[t][3] = 1;
//            }
//            if (x[t][4] <= 0.5) {
//                x[t][4] = 0;
//            } else {
//                x[t][4] = 1;
//            }
//            if (x[t][5] <= 0.5) {
//                x[t][5] = 0;
//            } else {
//                x[t][5] = 1;
//            }
//            if (x[t][6] <= 0.5) {
//                x[t][6] = 0;
//            } else {
//                x[t][6] = 1;
//            }
//            if (x[t][7] <= 0.5) {
//                x[t][7] = 0;
//            } else {
//                x[t][7] = 1;
//            }
//            if (x[t][8] <= 0.5) {
//                x[t][8] = 0;
//            } else {
//                x[t][8] = 1;
//            }
//            if (x[t][12] <= 0.5) {
//                x[t][12] = 0;
//            } else {
//                x[t][12] = 1;
//            }
//        }

        for (double[] ind1 : x) {
            //Evaluando la función objetivo

            ind1[this.getNumVariables()] = (5 * (ind1[0] + ind1[1] + ind1[2] + ind1[3]))
                    - (5 * (Math.pow(ind1[0], 2) + Math.pow(ind1[1], 2)
                    + Math.pow(ind1[2], 2) + Math.pow(ind1[3], 2)))
                    - (ind1[4] + ind1[5] + ind1[6] + ind1[7] + ind1[8]
                    + ind1[9] + ind1[10] + ind1[11] + ind1[12]);

            ind1[this.getNumVariables() + 1] = this.evaluarRestriccionesD(ind1);

        }
        return x;

    }

    @Override
    protected double evaluarRestriccionesD(double[] x) {
//        for (int t = 0; t < x.length; t++) {
//            if (x[0] <= 0.5) {
//                x[0] = 0;
//            } else {
//                x[0] = 1;
//            }
//            if (x[1] <= 0.5) {
//                x[1] = 0;
//            } else {
//                x[1] = 1;
//            }
//            if (x[2] <= 0.5) {
//                x[2] = 0;
//            } else {
//                x[2] = 1;
//            }
//            if (x[3] <= 0.5) {
//                x[3] = 0;
//            } else {
//                x[3] = 1;
//            }
//            if (x[4] <= 0.5) {
//                x[4] = 0;
//            } else {
//                x[4] = 1;
//            }
//            if (x[5] <= 0.5) {
//                x[5] = 0;
//            } else {
//                x[5] = 1;
//            }
//            if (x[6] <= 0.5) {
//                x[6] = 0;
//            } else {
//                x[6] = 1;
//            }
//            if (x[7] <= 0.5) {
//                x[7] = 0;
//            } else {
//                x[7] = 1;
//            }
//            if (x[8] <= 0.5) {
//                x[8] = 0;
//            } else {
//                x[8] = 1;
//            }
//            if (x[12] <= 0.5) {
//                x[12] = 0;
//            } else {
//                x[12] = 1;
//            }
//        }

        this.setSvr(0);

        this.getResDesigualdad()[0] = (2 * x[0]) + (2 * x[1]) + x[9] + x[10] - 10;
        this.getResDesigualdad()[1] = (2 * x[0]) + (2 * x[2]) + x[9] + x[11] - 10;
        this.getResDesigualdad()[2] = (2 * x[1]) + (2 * x[2]) + x[10] + x[11] - 10;
        this.getResDesigualdad()[3] = -8 * x[0] + x[9];
        this.getResDesigualdad()[4] = -8 * x[1] + x[10];
        this.getResDesigualdad()[5] = -8 * x[2] + x[11];
        this.getResDesigualdad()[6] = (-2 * x[3]) - x[4] + x[9];
        this.getResDesigualdad()[7] = (-2 * x[5]) - x[6] + x[10];
        this.getResDesigualdad()[8] = (-2 * x[7]) - x[8] + x[11];
        //suma de violacion de restricciones
        for (int i = 0; i < this.getResDesigualdad().length; i++) {

            this.setSvr(this.getSvr() + Math.max(0,
                    (this.getResDesigualdad()[i] - this.getCompRestriccion()[i])
            )
            );
        }

        return this.getSvr();
    }
}
