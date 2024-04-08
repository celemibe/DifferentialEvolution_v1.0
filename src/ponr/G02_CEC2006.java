/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ponr;

/**
 *
 * @author abim-
 */
public class G02_CEC2006 extends PONR {

    public G02_CEC2006() {
        this.setNombre("G02_CEC2006");
        this.setMejorConocido(-0.80361910412559);
        this.setNumVariables(20);

        this.setOrdenVariables(new String[]{
            "x1", "x2", "x3", "X4", "X5", "X6", "X7", "X8", "X9", "X10", "X11", "X12", "X13", "X14", "X15", "X16", "X17", "X18", "X19", "X20"
        }
        );

        this.setVariableDiscreta(new boolean[]{
            false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
        }
        );

        this.setRangos(new double[][]{
            {0.0, 10.0},
            {0.0, 10.0},
            {0.0, 10.0},
            {0.0, 10.0},
            {0.0, 10.0},
            {0.0, 10.0},
            {0.0, 10.0},
            {0.0, 10.0},
            {0.0, 10.0},
            {0.0, 10.0},
            {0.0, 10.0},
            {0.0, 10.0},
            {0.0, 10.0},
            {0.0, 10.0},
            {0.0, 10.0},
            {0.0, 10.0},
            {0.0, 10.0},
            {0.0, 10.0},
            {0.0, 10.0},
            {0.0, 10.0}// indice de pertenencia                         
        }
        );

        this.setResDesigualdad(new double[2]);
        this.setComparacionRestriccion(new double[]{
            0, 0
        }
        );
    }

    @Override
    public double[][] evaluarFO(double[][] x) {

        for (double[] ind1 : x) {
            //Evaluando la función objetivo

            ind1[this.getNumVariables()] = -Math.abs(((Math.pow(Math.cos(ind1[0]), 4)
                    + Math.pow(Math.cos(ind1[1]), 4)
                    + Math.pow(Math.cos(ind1[2]), 4)
                    + Math.pow(Math.cos(ind1[3]), 4)
                    + Math.pow(Math.cos(ind1[4]), 4)
                    + Math.pow(Math.cos(ind1[5]), 4)
                    + Math.pow(Math.cos(ind1[6]), 4)
                    + Math.pow(Math.cos(ind1[7]), 4)
                    + Math.pow(Math.cos(ind1[8]), 4)
                    + Math.pow(Math.cos(ind1[9]), 4)
                    + Math.pow(Math.cos(ind1[10]), 4)
                    + Math.pow(Math.cos(ind1[11]), 4)
                    + Math.pow(Math.cos(ind1[12]), 4)
                    + Math.pow(Math.cos(ind1[13]), 4)
                    + Math.pow(Math.cos(ind1[14]), 4)
                    + Math.pow(Math.cos(ind1[15]), 4)
                    + Math.pow(Math.cos(ind1[16]), 4)
                    + Math.pow(Math.cos(ind1[17]), 4)
                    + Math.pow(Math.cos(ind1[18]), 4)
                    + Math.pow(Math.cos(ind1[19]), 4))
                    - 2 * (Math.pow(Math.cos(ind1[0]), 2)
                    * Math.pow(Math.cos(ind1[1]), 2)
                    * Math.pow(Math.cos(ind1[2]), 2)
                    * Math.pow(Math.cos(ind1[3]), 2)
                    * Math.pow(Math.cos(ind1[4]), 2)
                    * Math.pow(Math.cos(ind1[5]), 2)
                    * Math.pow(Math.cos(ind1[6]), 2)
                    * Math.pow(Math.cos(ind1[7]), 2)
                    * Math.pow(Math.cos(ind1[8]), 2)
                    * Math.pow(Math.cos(ind1[9]), 2)
                    * Math.pow(Math.cos(ind1[10]), 2)
                    * Math.pow(Math.cos(ind1[11]), 2)
                    * Math.pow(Math.cos(ind1[12]), 2)
                    * Math.pow(Math.cos(ind1[13]), 2)
                    * Math.pow(Math.cos(ind1[14]), 2)
                    * Math.pow(Math.cos(ind1[15]), 2)
                    * Math.pow(Math.cos(ind1[16]), 2)
                    * Math.pow(Math.cos(ind1[17]), 2)
                    * Math.pow(Math.cos(ind1[18]), 2)
                    * Math.pow(Math.cos(ind1[19]), 2)))
                    / Math.sqrt((1 * Math.pow(ind1[0], 2)
                            + 2 * Math.pow(ind1[1], 2)
                            + 3 * Math.pow(ind1[2], 2)
                            + 4 * Math.pow(ind1[3], 2)
                            + 5 * Math.pow(ind1[4], 2)
                            + 6 * Math.pow(ind1[5], 2)
                            + 7 * Math.pow(ind1[6], 2)
                            + 8 * Math.pow(ind1[7], 2)
                            + 9 * Math.pow(ind1[8], 2)
                            + 10 * Math.pow(ind1[9], 2)
                            + 11 * Math.pow(ind1[10], 2)
                            + 12 * Math.pow(ind1[11], 2)
                            + 13 * Math.pow(ind1[12], 2)
                            + 14 * Math.pow(ind1[13], 2)
                            + 15 * Math.pow(ind1[14], 2)
                            + 16 * Math.pow(ind1[15], 2)
                            + 17 * Math.pow(ind1[16], 2)
                            + 18 * Math.pow(ind1[17], 2)
                            + 19 * Math.pow(ind1[18], 2)
                            + 20 * Math.pow(ind1[19], 2))));

            ind1[this.getNumVariables() + 1] = this.evaluarRestriccionesD(ind1);
            
        }
        return x;

    }

    @Override
    protected double evaluarRestriccionesD(double[] x) {

        this.setSvr(0);

        this.getResDesigualdad()[0] = 0.75 - (x[0] * x[1] * x[2] * x[3] * x[4] * x[5] * x[6]
                * x[7] * x[8] * x[9] * x[10] * x[11] * x[12] * x[13] * x[14] * x[15]
                * x[16] * x[17] * x[18] * x[19]);
        this.getResDesigualdad()[1] = (x[0] + x[1] + x[2] + x[3] + x[4] + x[5] + x[6]
                + x[7] + x[8] + x[9] + x[10] + x[11] + x[12] + x[13] + x[14] + x[15]
                + x[16] + x[17] + x[18] + x[19]) - 7.5 * 20;

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
