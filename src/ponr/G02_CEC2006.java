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
            //"(y1 - 1)^2 + (y2 - 2)^2 + (y3 - 1)^2 - log10(y4 + 1) + (x1 - 1)^2 + (x2 - 2)^2 + (x3 - 3)^2" 
            ind1[this.getNumVariables()] = (5 * (ind1[0] + ind1[1] + ind1[2] + ind1[3]))
                    - (5 * (Math.pow(ind1[0], 2)
                    + Math.pow(ind1[1], 2) + Math.pow(ind1[2], 2) + Math.pow(ind1[3], 2)))
                    - (ind1[4] + ind1[5] + ind1[6] + ind1[7] + ind1[8] + ind1[9] + ind1[10] + ind1[11] + ind1[12]);

            ind1[this.getNumVariables() + 1] = this.evaluarRestriccionesD(ind1);

        }
        return x;

    }

    @Override
    protected double evaluarRestriccionesD(double[] x) {
       

        this.setSvr(0);

        this.getResDesigualdad()[0] = (2 * x[0]) + (2 * x[1]) + x[9] + x[10] - 10;
        this.getResDesigualdad()[1] = (2 * x[0]) + (2 * x[2]) + x[9] + x[11] - 10;

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
