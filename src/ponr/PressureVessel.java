/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ponr;

/**
 *
 * @author abim-
 */
public class PressureVessel extends PONR {

    public PressureVessel() {
        this.setNombre("Pressure Vessel");
        this.setMejorConocido(6059.714335048436);
        this.setNumVariables(4);

        this.setOrdenVariables(new String[]{
            "x1", "x2", "x3", "x4"
        }
        );

        this.setVariableDiscreta(new boolean[]{
            false, false, false, false
        }
        );

        this.setRangos(new double[][]{
            {1.0, 99.0},
            {1.0, 99.0},
            {10.0, 200.0},
            {10.0, 200.0}// indice de pertenencia                         
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
        double aux1 = 0;
        double aux2 = 0;
        for (double[] ind1 : x) {
            //Evaluando la función objetivo
            //0,6224x1x3x4 + 1,7781x2x3^2 + 3,1661x12x4 + 19,84x12x3 
            aux1 = ind1[0];
            aux2 = ind1[1];

            aux1 = Math.floor(aux1) * 0.0625;
            aux2 = Math.floor(aux2) * 0.0625;

            ind1[this.getNumVariables()] = (0.6224 * aux1 * ind1[2] * ind1[3]) + 
                    (1.7781 * aux2 * Math.pow(ind1[2], 2)) + 
                    (3.1661 * Math.pow(aux1, 2) * ind1[3]) + 
                    (19.84 * Math.pow(aux1, 2) * ind1[2]);

            ind1[this.getNumVariables() + 1] = this.evaluarRestriccionesD(ind1);

        }
        return x;

    }

    @Override
    protected double evaluarRestriccionesD(double[] x) {
        this.setSvr(0);
        double aux1 = 0;
        double aux2 = 0;
        aux1 = x[0];
        aux2 = x[1];
        aux1 = Math.floor(aux1) * 0.0625;
        aux2 = Math.floor(aux2) * 0.0625;
        
        this.getResDesigualdad()[0] = (-1 * aux1) + (0.0193 * x[2]);

        this.getResDesigualdad()[1] = (-1 * aux2) + (0.00954 * x[2]);

        this.getResDesigualdad()[2] = ((-1 * Math.PI) * 
                (Math.pow(x[2], 2) * x[3])) - 
                ((4.0 / 3.0) * Math.PI * Math.pow(x[2], 3)) + 1296000;

        this.getResDesigualdad()[3] = x[3] - 240;
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
