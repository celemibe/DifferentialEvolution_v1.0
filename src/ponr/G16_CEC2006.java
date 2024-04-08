package ponr;

/**
 *
 * @author abim-
 */
public class G16_CEC2006 extends PONR {

    public G16_CEC2006() {
        this.setNombre("G16_CEC2006");
        this.setMejorConocido(-1.90515525853479);
        this.setNumVariables(5);

        this.setOrdenVariables(new String[]{
            "x1", "x2", "x3", "x4", "x5"
        }
        );

        this.setVariableDiscreta(new boolean[]{
            false, false, false, false, false
        }
        );

        this.setRangos(new double[][]{
            {704.4148, 906.3855},
            {68.6, 288.88},
            {0.0, 134.75},
            {193, 287.0966},
            {25.0, 84.1988}
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
