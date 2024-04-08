/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ponr;

/**
 *
 * @author abim-
 */
public class TensionCompressionSpring extends PONR {

    public TensionCompressionSpring() {
        this.setNombre("Tension Compression Spring");
        this.setMejorConocido(0.012665232788);
        this.setNumVariables(3);

        this.setOrdenVariables(new String[]{
            "x1", "x2", "x3"
        }
        );

        this.setVariableDiscreta(new boolean[]{
            false, false, false
        }
        );

        this.setRangos(new double[][]{
            {0.05, 2.0},
             {0.25, 1.3},
             {2.0, 15.0} // indice de pertenencia                         
        }
        );

        this.setResDesigualdad(new double[4]);
        this.setComparacionRestriccion(new double[]{
            0, 0, 0, 0
        }
        );

    }

    @Override
    public double[][] evaluarFO(double[][] x) {

        for (double[] ind1 : x) {
            //Evaluando la función objetivo
            //(x3 + 2)x2*x1^2
            ind1[this.getNumVariables()] = (ind1[2] + 2) * ind1[1] * Math.pow(ind1[0], 2);

            ind1[this.getNumVariables() + 1] = this.evaluarRestriccionesD(ind1);

        }
        return x;

    }

    @Override
    protected double evaluarRestriccionesD(double[] x) {
        this.setSvr(0);

        this.getResDesigualdad()[0] = 1.0 - ((Math.pow(x[1], 3)) * x[2]) / (71785.0 * (Math.pow(x[0], 4)));

        this.getResDesigualdad()[1] = (((4.0 * Math.pow(x[1], 2)) - (x[0] * x[1])) / (12566.0 * ((x[1] * Math.pow(x[0], 3)) - Math.pow(x[0], 4)))) + (1.0 / (5108.0 * Math.pow(x[0], 2))) - 1.0;

        this.getResDesigualdad()[2] = 1.0 - ((140.45 * x[0]) / ((x[1] * x[1]) * x[2]));

        this.getResDesigualdad()[3] = ((x[1] + x[0]) / 1.5) - 1;

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
