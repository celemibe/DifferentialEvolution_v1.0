/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ponr;

/**
 *
 * @author abim-
 */
public class G07_CEC2006 extends PONR {

    public G07_CEC2006() {
        this.setNombre("G07_CEC2006");
        this.setMejorConocido(24.30620906818);
        this.setNumVariables(10);

        this.setOrdenVariables(new String[]{
            "x1", "x2", "x3", "X4", "X5", "X6", "X7", "X8", "X9", "X10"
        }
        );

        this.setVariableDiscreta(new boolean[]{
            false, false, false, false, false, false, false, false, false, false
        }
        );

        this.setRangos(new double[][]{
            {-10.0, 10.0},
            {-10.0, 10.0},
            {-10.0, 10.0},
            {-10.0, 10.0},
            {-10.0, 10.0},
            {-10.0, 10.0},
            {-10.0, 10.0},
            {-10.0, 10.0},
            {-10.0, 10.0},
            {-10.0, 10.0}
        // indice de pertenencia                         
        }
        );

        this.setResDesigualdad(new double[8]);
        this.setComparacionRestriccion(new double[]{
            0, 0, 0, 0, 0, 0, 0, 0
        }
        );
    }

    @Override
    public double[][] evaluarFO(double[][] x) {
//       

        for (double[] ind1 : x) {
            //Evaluando la función objetivo

            ind1[this.getNumVariables()] = Math.pow(ind1[0], 2) + Math.pow(ind1[1], 2) + (ind1[0] * ind1[1]) - (14 * ind1[0]) - (16 * ind1[1]) + Math.pow((ind1[2] - 10), 2)
                    + (4 * Math.pow((ind1[3] - 5), 2)) + Math.pow((ind1[4] - 3), 2)
                    + (2 * Math.pow((ind1[5] - 1), 2)) + (5 * Math.pow(ind1[6], 2)) + (7 * Math.pow((ind1[7] - 11), 2))
                    + (2 * Math.pow((ind1[8] - 10), 2)) + Math.pow((ind1[9] - 10), 2) + 45;

            ind1[this.getNumVariables() + 1] = this.evaluarRestriccionesD(ind1);

        }
        return x;

    }

    @Override
    protected double evaluarRestriccionesD(double[] x) {

        this.setSvr(0);

        this.getResDesigualdad()[0] = -105 + 4 * x[0] + 5 * x[1] - 3 * x[6] + 9 * x[7];
        this.getResDesigualdad()[1] = 10 * x[0] - 8 * x[1] - 17 * x[6] + 2 * x[7];
        this.getResDesigualdad()[2] = -8 * x[0] + 2 * x[1] + 5 * x[8] - 2 * x[9] - 12;
        this.getResDesigualdad()[3] = 3 * Math.pow((x[0] - 2), 2) + 4 * Math.pow((x[1] - 3), 2) + 2 * Math.pow(x[2], 2) - 7 * x[3] - 120;
        this.getResDesigualdad()[4] = 5 * Math.pow(x[0], 2) + 8 * x[1] + Math.pow((x[2] - 6), 2) - 2 * x[3] - 40;
        this.getResDesigualdad()[5] = Math.pow(x[0], 2) + 2 * Math.pow((x[1] - 2), 2) - 2 * x[0] * x[1] + 14 * x[4] - 6 * x[5];
        this.getResDesigualdad()[6] = 0.5 * Math.pow((x[0] - 8), 2) + 2 * Math.pow((x[1] - 4), 2) + 3 * Math.pow(x[4], 2) - x[5] - 30;
        this.getResDesigualdad()[7] = -3 * x[0] + 6 * x[1] + 12 * Math.pow((x[8] - 8), 2) - 7 * x[9];

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
