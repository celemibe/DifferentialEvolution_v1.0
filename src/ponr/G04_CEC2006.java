/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ponr;

/**
 *
 * @author abim-
 */
public class G04_CEC2006 extends PONR {

    public G04_CEC2006() {
        this.setNombre("G04_CEC2006");
        this.setMejorConocido(-30665.53867178332);
        this.setNumVariables(5);

        this.setOrdenVariables(new String[]{
            "x1", "x2", "x3", "X4", "X5"
        }
        );

        this.setVariableDiscreta(new boolean[]{
            false, false, false, false, false
        }
        );

        this.setRangos(new double[][]{
            {78.0, 102.0},
            {33.0, 45.0},
            {27.0, 45.0},
            {27.0, 45.0},
            {27.0, 45.0}
            // indice de pertenencia                         
        }
        );

        this.setResDesigualdad(new double[6]);
        this.setComparacionRestriccion(new double[]{
            0, 0, 0, 0, 0, 0
        }
        );
    }

    @Override
    public double[][] evaluarFO(double[][] x) {
//       

        for (double[] ind1 : x) {
            //Evaluando la función objetivo

            ind1[this.getNumVariables()] = 5.3578547 * Math.pow(ind1[2], 2) + 0.8356891 * ind1[0] * ind1[4] + 37.293239 * ind1[0] - 40792.141;

            ind1[this.getNumVariables() + 1] = this.evaluarRestriccionesD(ind1);

        }
        return x;

    }

    @Override
    protected double evaluarRestriccionesD(double[] x) {


        this.setSvr(0);

        this.getResDesigualdad()[0] = 85.334407 + 0.0056858 * x[1] * x[4] + 0.0006262 * x[0] * x[3] - 0.0022053 * x[2] * x[4] - 92;
        this.getResDesigualdad()[1] = -85.334407 - (0.0056858 * (x[1] * x[4])) - (0.0006262 * (x[0] * x[3])) + (0.0022053 * (x[2] * x[4]));
        this.getResDesigualdad()[2] = 80.51249 + (0.0071317 * (x[1] * x[4])) + (0.0029955 *( x[0] * x[1])) + (0.0021813 * (Math.pow(x[2], 2))) - 110;
        this.getResDesigualdad()[3] = -80.51249 - 0.0071317 * x[1] * x[4] - 0.0029955 * x[0] * x[1] - 0.0021813 * (Math.pow(x[2], 2)) + 90;
        this.getResDesigualdad()[4] = 9.300961 + (0.0047026 * (x[2] * x[4])) + (0.0012547 * (x[0] * x[2])) + (0.0019085 *( x[2] * x[3])) - 25;
        this.getResDesigualdad()[5] = -9.300961 - (0.0047026 * (x[2] * x[4])) - (0.0012547 * (x[0] * x[2])) - (0.0019085 * (x[2] * x[3])) + 20;
        
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
