/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ponr;

/**
 *
 * @author abim-
 */
public class ProcessSynthesisMINLP extends PONR {

    public ProcessSynthesisMINLP() {
        this.setNombre("Process synthesis MINLP");
        this.setMejorConocido(4.57958);
        this.setNumVariables(7);

        this.setOrdenVariables(new String[]{
            "x1", "x2", "x3", "y1", "y2", "y3", "y4"
        }
        );

        this.setVariableDiscreta(new boolean[]{
            false, false, false, false, false, false, false
        }
        );

        this.setRangos(new double[][]{
            {0.0, 1.2},
            {0.0, 1.8},
            {0.0, 2.5},
            {0.0, 1.0},
            {0.0, 1.0},
            {0.0, 1.0},
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
        for (int t = 0; t < x.length; t++) {
            if (x[t][3] <= 0.5) {
                x[t][3] = 0;
            } else {
                x[t][3] = 1;
            }
            if (x[t][4] <= 0.5) {
                x[t][4] = 0;
            } else {
                x[t][4] = 1;
            }
            if (x[t][5] <= 0.5) {
                x[t][5] = 0;
            } else {
                x[t][5] = 1;
            }
            if (x[t][6] <= 0.5) {
                x[t][6] = 0;
            } else {
                x[t][6] = 1;
            }
        }

        for (double[] ind1 : x) {
            //Evaluando la función objetivo
            //"(y1 - 1)^2 + (y2 - 2)^2 + (y3 - 1)^2 - log10(y4 + 1) + (x1 - 1)^2 + (x2 - 2)^2 + (x3 - 3)^2" 
            ind1[this.getNumVariables()] = Math.pow((ind1[3] - 1), 2)
                    + Math.pow((ind1[4] - 2), 2)
                    + Math.pow((ind1[5] - 1), 2)
                    - Math.log((ind1[6] + 1))
                    + Math.pow((ind1[0] - 1), 2)
                    + Math.pow((ind1[1] - 2), 2)
                    + Math.pow((ind1[2] - 3), 2);

            ind1[this.getNumVariables() + 1] = this.evaluarRestriccionesD(ind1);

        }
        return x;

    }

    @Override
    protected double evaluarRestriccionesD(double[] x) {
        for (int t = 0; t < x.length; t++) {
            if (x[3] <= 0.5) {
                x[3] = 0;
            } else {
                x[3] = 1;
            }
            if (x[4] <= 0.5) {
                x[4] = 0;
            } else {
                x[4] = 1;
            }
            if (x[5] <= 0.5) {
                x[5] = 0;
            } else {
                x[5] = 1;
            }
            if (x[6] <= 0.5) {
                x[6] = 0;
            } else {
                x[6] = 1;
            }
        }

        this.setSvr(0);

        this.getResDesigualdad()[0] = x[3] + x[4] + x[5] + x[0] + x[1] + x[2] - 5;
        this.getResDesigualdad()[1] = Math.pow(x[5], 2) + Math.pow(x[0], 2) + Math.pow(x[1], 2) + Math.pow(x[2], 2) - 5.5;
        this.getResDesigualdad()[2] = x[3] + x[0] - 1.2;
        this.getResDesigualdad()[3] = x[4] + x[1] - 1.8;
        this.getResDesigualdad()[4] = x[5] + x[2] - 2.5;
        this.getResDesigualdad()[5] = x[5] + x[0] - 1.2;
        this.getResDesigualdad()[6] = Math.pow(x[4], 2) + Math.pow(x[1], 2) - 1.64;
        this.getResDesigualdad()[7] = Math.pow(x[5], 2) + Math.pow(x[2], 2) - 4.25;
        this.getResDesigualdad()[8] = Math.pow(x[4], 2) + Math.pow(x[2], 2) - 4.64;
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
