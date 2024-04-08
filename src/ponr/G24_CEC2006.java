package ponr;

/**
 *
 * @author abim-
 */
public class G24_CEC2006 extends PONR {

    public G24_CEC2006() {
        this.setNombre("G24_CEC2006");
        this.setMejorConocido(-5.50801327159536);
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
            {0.0, 3.0},
            {0.0, 4.0}
        // indice de pertenencia                         
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
//       

        for (double[] ind1 : x) {
            //Evaluando la función objetivo

            ind1[this.getNumVariables()] = -ind1[0] - ind1[1];

            ind1[this.getNumVariables() + 1] = this.evaluarRestriccionesD(ind1);

        }
        return x;

    }

    @Override
    protected double evaluarRestriccionesD(double[] x) {

        this.setSvr(0);

        this.getResDesigualdad()[0] = -2 * Math.pow(x[0], 4) + 8 * Math.pow(x[0], 3) + x[1] - 2;
        this.getResDesigualdad()[1] = -4 * Math.pow(x[0], 4) + 32 * Math.pow(x[0], 3) - 88 * Math.pow(x[0], 2) + 96 * x[0] + x[1] - 36;

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
