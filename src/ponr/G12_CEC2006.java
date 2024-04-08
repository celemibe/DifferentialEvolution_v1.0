package ponr;

/**
 *
 * @author abim-
 */
public class G12_CEC2006 extends PONR {

    public G12_CEC2006() {
        this.setNombre("G12_CEC2006");
        this.setMejorConocido(-1);
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
            {0.0, 10.0},
            {0.0, 10.0},
            {0.0, 10.0}
        // indice de pertenencia                         
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
//       

        for (double[] ind1 : x) {
            //Evaluando la función objetivo

            ind1[this.getNumVariables()] = -(100-(Math.pow((ind1[0]-5),2))-Math.pow((ind1[1]-5),2)-Math.pow((ind1[2]-5),2))/100;

            ind1[this.getNumVariables() + 1] = this.evaluarRestriccionesD(ind1);

        }
        return x;

    }

    @Override
    protected double evaluarRestriccionesD(double[] x) {

        this.setSvr(0);

        this.getResDesigualdad()[0] = Math.pow(x[0], 2) - x[1] + 1;

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
