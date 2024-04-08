package ponr;

/**
 *
 * @author abim-
 */
public class G05_CEC2006 extends PONR {

    public G05_CEC2006() {
        this.setNombre("G05_CEC2006");
        this.setMejorConocido(5126.4967140071);
        this.setNumVariables(4);

        this.setOrdenVariables(new String[]{
            "x1", "x2", "x3", "X4"
        }
        );

        this.setVariableDiscreta(new boolean[]{
            false, false, false, false
        }
        );

        this.setRangos(new double[][]{
            {0.0, 1200.0},
            {0.0, 1200.0},
            {-0.55, 0.55},
            {-0.55, 0.55}
        // indice de pertenencia                         
        }
        );

        this.setResDesigualdad(new double[5]);
        this.setComparacionRestriccion(new double[]{
            0, 0, 0, 0, 0
        }
        );
    }

    @Override
    public double[][] evaluarFO(double[][] x) {
//       

        for (double[] ind1 : x) {
            //Evaluando la función objetivo

            ind1[this.getNumVariables()] = 3 * ind1[0] + 0.000001 * Math.pow(ind1[0], 3)
                    + 2 * ind1[1] + (0.000002 / 3) * Math.pow(ind1[1], 3);

            ind1[this.getNumVariables() + 1] = this.evaluarRestriccionesD(ind1);

        }
        return x;

    }

    @Override
    protected double evaluarRestriccionesD(double[] x) {

        this.setSvr(0);

        this.getResDesigualdad()[0] = -x[3] + x[2] - 0.55;
        this.getResDesigualdad()[1] = -x[2] + x[3] - 0.55;
//        this.getResDesigualdad()[2] = ;
//        this.getResDesigualdad()[3] = ;
//        this.getResDesigualdad()[4] = ;

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
