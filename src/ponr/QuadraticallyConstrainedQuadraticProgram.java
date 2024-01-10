/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ponr;

/**
 *
 * @author abim-
 */
public class QuadraticallyConstrainedQuadraticProgram extends PONR {

    public QuadraticallyConstrainedQuadraticProgram() {
        this.setNombre("Quadratically Constrained Quadratic Programg");
        this.setMejorConocido(-118.7048);
        this.setNumVariables(2);

        this.setOrdenVariables(new String[]{
            "x1", "x2"
        }
        );

        this.setVariableDiscreta(new boolean[]{
            false, false
        }
        );

        this.setRangos(new double[][]{
            {-8.0, 10.0},
            {0.05, 10.0} // indice de pertenencia                         
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
            //x1^4 - 14 * x1^2 + 24 * x1 - x2^2 
            ind1[this.getNumVariables()] = (Math.pow(ind1[0], 4))-14* (Math.pow(ind1[0], 2))+24*ind1[0]-(Math.pow(ind1[1], 2));

            ind1[this.getNumVariables() + 1] = this.evaluarRestriccionesD(ind1);

        }
        return x;

    }

    @Override
    protected double evaluarRestriccionesD(double[] x) {
        this.setSvr(0);
            //{-x1 + x2 - 8 <= 0}
            //{x2 - x1^2 - 2 * x1 + 2 <= 0}

        this.getResDesigualdad()[0] = (-x[0]+x[1])-8;

        this.getResDesigualdad()[1] = x[1]-(Math.pow(x[0],2))-(2*x[0])+2;



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
