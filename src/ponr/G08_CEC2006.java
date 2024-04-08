package ponr;

/**
 *
 * @author abim-
 */
public class G08_CEC2006 extends PONR {

    public G08_CEC2006() {
        this.setNombre("G08_CEC2006");
        this.setMejorConocido(-0.0958250414180359);
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
            {0.0, 10.0},
            {0.0, 10.0}
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

            ind1[this.getNumVariables()] = -(Math.pow(Math.sin(2 * Math.PI * ind1[0]), 3) * Math.sin(2 * Math.PI * ind1[1])) / (Math.pow(ind1[0], 3) * (ind1[0] + ind1[1]));

            ind1[this.getNumVariables() + 1] = this.evaluarRestriccionesD(ind1);

        }
        return x;

    }

    @Override
    protected double evaluarRestriccionesD(double[] x) {

        this.setSvr(0);

        this.getResDesigualdad()[0] = Math.pow(x[0],2)-x[1]+1;
        this.getResDesigualdad()[1] = 1-x[0]+ Math.pow((x[1]-4),2);

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
