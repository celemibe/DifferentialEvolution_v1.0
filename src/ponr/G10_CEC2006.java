package ponr;

/**
 *
 * @author abim-
 */
public class G10_CEC2006 extends PONR {

    public G10_CEC2006() {
        this.setNombre("G10_CEC2006");
        this.setMejorConocido(7049.24802052867);
        this.setNumVariables(8);

        this.setOrdenVariables(new String[]{
            "x1", "x2", "x3", "X4", "X5", "X6", "X7", "X8"
        }
        );

        this.setVariableDiscreta(new boolean[]{
            false, false, false, false, false, false, false, false
        }
        );

        this.setRangos(new double[][]{
            {100.0, 10000.0},
            {1000.0, 10000.0},
            {1000.0, 10000.0},
            {10.0, 1000.0},
            {10.0, 1000.0},
            {10.0, 1000.0},
            {10.0, 1000.0},
            {10.0, 1000.0}
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

            ind1[this.getNumVariables()] = ind1[0] + ind1[1] + ind1[2];

            ind1[this.getNumVariables() + 1] = this.evaluarRestriccionesD(ind1);

        }
        return x;

    }

    @Override
    protected double evaluarRestriccionesD(double[] x) {

        this.setSvr(0);

        this.getResDesigualdad()[0] = -1 + 0.0025 * (x[3] + x[5]);
        this.getResDesigualdad()[1] = -1 + 0.0025 * (x[4] + x[6] - x[3]);
        this.getResDesigualdad()[2] = -1 + 0.01 * (x[7] - x[4]);
        this.getResDesigualdad()[3] = -x[0] * x[5] + 833.33252 * x[3] + 100 * x[0] - 83333.333;
        this.getResDesigualdad()[4] = -x[1] * x[6] + 1250 * x[4] + x[1] * x[3] - 1250 * x[3];
        this.getResDesigualdad()[5] = -x[2] * x[7] + 1250000 + x[2] * x[4] - 2500 * x[4];

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
