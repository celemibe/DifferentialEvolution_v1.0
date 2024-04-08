package ponr;

/**
 *
 * @author abim-
 */
public class G18_CEC2006 extends PONR {

    public G18_CEC2006() {
        this.setNombre("G18_CEC2006");
        this.setMejorConocido(-0.866025403784439);
        this.setNumVariables(9);

        this.setOrdenVariables(new String[]{
            "x1", "x2", "x3", "x4", "x5", "x6", "x7", "x8", "x9"
        }
        );

        this.setVariableDiscreta(new boolean[]{
            false, false, false, false, false, false, false, false, false
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
            {0.0, 20.0}
        // indice de pertenencia                         
        }
        );

        this.setResDesigualdad(new double[13]);
        this.setComparacionRestriccion(new double[]{
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
        }
        );
    }

    @Override
    public double[][] evaluarFO(double[][] x) {
//       

        for (double[] ind1 : x) {
            //Evaluando la función objetivo

            ind1[this.getNumVariables()] = -0.5 * ((ind1[0] * ind1[3]) - (ind1[1] * ind1[2])
                    + (ind1[2] * ind1[8]) - (ind1[4] * ind1[8])
                    + (ind1[4] * ind1[7]) - (ind1[5] * ind1[6]));

            ind1[this.getNumVariables() + 1] = this.evaluarRestriccionesD(ind1);

        }
        return x;

    }

    @Override
    protected double evaluarRestriccionesD(double[] x) {

        this.setSvr(0);

        this.getResDesigualdad()[0] = Math.pow(x[2], 2) + Math.pow(x[3], 2) - 1;
        this.getResDesigualdad()[1] = Math.pow(x[8], 2) - 1;
        this.getResDesigualdad()[2] = Math.pow(x[4], 2) + Math.pow(x[5], 2) - 1;
        this.getResDesigualdad()[3] = Math.pow(x[0], 2) + Math.pow((x[1] - x[8]), 2) - 1;
        this.getResDesigualdad()[4] = Math.pow((x[0] - x[4]), 2) + Math.pow((x[1] - x[5]), 2) - 1;
        this.getResDesigualdad()[5] = Math.pow((x[0] - x[6]), 2) + Math.pow((x[1] - x[7]), 2) - 1;
        this.getResDesigualdad()[6] = Math.pow((x[2] - x[4]), 2) + Math.pow((x[3] - x[5]), 2) - 1;
        this.getResDesigualdad()[7] = Math.pow((x[2] - x[6]), 2) + Math.pow((x[3] - x[7]), 2) - 1;
        this.getResDesigualdad()[8] = Math.pow(x[6], 2) + Math.pow((x[7] - x[8]), 2) - 1;
        this.getResDesigualdad()[9] = x[1] * x[2] - x[0] * x[3];
        this.getResDesigualdad()[10] = -x[2] * x[8];
        this.getResDesigualdad()[11] = x[4] * x[8];
        this.getResDesigualdad()[12] = x[5] * x[6] - x[4] * x[7];

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
