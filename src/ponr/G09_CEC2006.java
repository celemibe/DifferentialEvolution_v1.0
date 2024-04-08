package ponr;

/**
 *
 * @author abim-
 */
public class G09_CEC2006 extends PONR {

    public G09_CEC2006() {
        this.setNombre("G09_CEC2006");
        this.setMejorConocido(680.630057374402);
        this.setNumVariables(7);

        this.setOrdenVariables(new String[]{
            "x1", "x2", "x3", "X4", "X5", "X6", "X7"
        }
        );

        this.setVariableDiscreta(new boolean[]{
            false, false, false, false, false, false, false
        }
        );

        this.setRangos(new double[][]{
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

        this.setResDesigualdad(new double[4]);
        this.setComparacionRestriccion(new double[]{
            0, 0, 0, 0
        }
        );
    }

    @Override
    public double[][] evaluarFO(double[][] x) {
//       

        for (double[] ind1 : x) {
            //Evaluando la función objetivo

            ind1[this.getNumVariables()] = Math.pow((ind1[0] - 10), 2) + 5 * Math.pow((ind1[1] - 12), 2)
                    + Math.pow(ind1[2], 4) + 3 * Math.pow((ind1[3] - 11), 2) + 10 * Math.pow(ind1[4], 6)
                    + 7 * Math.pow(ind1[5], 2) + Math.pow(ind1[6], 4) - 4 * ind1[5] * ind1[6] - 10 * ind1[5] - 8 * ind1[6];

            ind1[this.getNumVariables() + 1] = this.evaluarRestriccionesD(ind1);

        }
        return x;

    }

    @Override
    protected double evaluarRestriccionesD(double[] x) {

        this.setSvr(0);

        this.getResDesigualdad()[0] = -127 + 2 * Math.pow(x[0], 2) + 3 * Math.pow(x[1], 4) + x[2] + 4 * Math.pow(x[3], 2) + 5 * x[4];
        this.getResDesigualdad()[1] = -282 + 7 * x[0] + 3 * x[1] + 10 * Math.pow(x[2], 2) + x[3] - x[4];
        this.getResDesigualdad()[2] = -196 + 23 * x[0] + Math.pow(x[1], 2) + 6 * Math.pow(x[5], 2) - 8 * x[6];
        this.getResDesigualdad()[3] = 4 * Math.pow(x[0], 2) + Math.pow(x[1], 2) - 3 * x[0] * x[1] + 2 * Math.pow(x[2], 2) + 5 * x[5] - 11 * x[6];

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
