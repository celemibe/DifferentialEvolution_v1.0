/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ponr;

/**
 *
 * @author abim-
 */
public class DesignReinforcedConcreteBeam extends PONR {

    public DesignReinforcedConcreteBeam() {
        this.setNombre("Design of a Reinforced Concrete Beam");
        this.setMejorConocido(376.2919);
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
            {0.0, 115.8},
            {0.00001, 30.0}// indice de pertenencia                         
        }
        );

        this.setResDesigualdad(new double[1]);
        this.setComparacionRestriccion(new double[]{
            0
        }
        );
    }

    @Override
    public double[][] evaluarFO(double[][] x) {

        for (double[] ind1 : x) {
            //Evaluando la función objetivo
            //29.4*x1 + 18*x 
            ind1[this.getNumVariables()] = (29.4 * ind1[0]) + (18.0 * ind1[1]);

            ind1[this.getNumVariables() + 1] = this.evaluarRestriccionesD(ind1);

        }
        return x;

    }

    @Override
    protected double evaluarRestriccionesD(double[] x) {
        this.setSvr(0);

        this.getResDesigualdad()[0] = -(x[0]) + ((0.2458 * Math.pow(x[0], 2)) / x[1]) + 6;
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
