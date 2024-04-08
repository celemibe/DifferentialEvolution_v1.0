
package ponr;

/**
 *
 * @author abim-
 */
public class G11_CEC2006 extends PONR {
        public G11_CEC2006() {
        this.setNombre("G11_CEC2006");
        this.setMejorConocido(0.7499);
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
            {-1.0, 1.0},
            {-1.0, 1.0}
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

            ind1[this.getNumVariables()] = Math.pow(ind1[0],2)+Math.pow((ind1[1]-1),2);

            ind1[this.getNumVariables() + 1] = this.evaluarRestriccionesD(ind1);

        }
        return x;

    }

    @Override
    protected double evaluarRestriccionesD(double[] x) {

        this.setSvr(0);

        this.getResDesigualdad()[0] = Math.pow(x[0],2)-x[1]+1;

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
