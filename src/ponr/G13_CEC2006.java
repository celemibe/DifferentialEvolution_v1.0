package ponr;

/**
 *
 * @author abim-
 */
public class G13_CEC2006 extends PONR {

    public G13_CEC2006() {
        this.setNombre("G13_CEC2006");
        this.setMejorConocido(0.053941514041898);
        this.setNumVariables(5);

        this.setOrdenVariables(new String[]{
            "x1", "x2", "x3","x4","x5"
        }
        );

        this.setVariableDiscreta(new boolean[]{
            false, false, false,false,false
        }
        );

        this.setRangos(new double[][]{
            {-2.3, 2.3},
            {-2.3, 2.3},
            {-3.2, 3.2},
            {-3.2, 3.2},
            {-3.2, 3.2}
        // indice de pertenencia                         
        }
        );

        this.setResDesigualdad(new double[3]);
        this.setComparacionRestriccion(new double[]{
            0,0,0
        }
        );
    }

    @Override
    public double[][] evaluarFO(double[][] x) {
//       

        for (double[] ind1 : x) {
            //Evaluando la función objetivo

            ind1[this.getNumVariables()] = Math.exp(ind1[0]*ind1[1]*ind1[2]*ind1[3]*ind1[4]);

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
