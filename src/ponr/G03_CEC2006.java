package ponr;

/**
 *
 * @author abim-
 */
public class G03_CEC2006 extends PONR {

    public G03_CEC2006() {
        this.setNombre("G03_CEC2006");
        this.setMejorConocido(-1.00050010001000);
        this.setNumVariables(10);

        this.setOrdenVariables(new String[]{
            "x1", "x2", "x3", "X4", "X5", "X6", "X7", "X8", "X9", "X10"
        }
        );

        this.setVariableDiscreta(new boolean[]{
            false, false, false, false, false, false, false, false, false, false
        }
        );

        this.setRangos(new double[][]{
            {0.0, 1.0},
            {0.0, 1.0},
            {0.0, 1.0},
            {0.0, 1.0},
            {0.0, 1.0},
            {0.0, 1.0},
            {0.0, 1.0},
            {0.0, 1.0},
            {0.0, 1.0},
            {0.0, 1.0}
        // indice de pertenencia                         
        }
        );

        this.setResDesigualdad(new double[0]);
        this.setComparacionRestriccion(new double[]{
            0
        }
        );
        this.setRestriccionesI(new double[1]);
        this.setComparacionRestriccion(new double[]{
            0
        }
        );
    }

    @Override
    public double[][] evaluarFO(double[][] x) {
        double f3 = 0;

        for (double[] ind1 : x) {
            //Evaluando la función objetivo
            f3 = Math.pow(this.getNumVariables(), (this.getNumVariables() / 2));
            double f1 = 1.0;

            for (int j = 0; j < this.getNumVariables(); j++) {
                f1 = f1 * ind1[j];

            }
            ind1[this.getNumVariables()] = -f3 * f1;
            //            ind1[this.getNumVariables()] = -(Math.pow(Math.sqrt(10), 10))
            //                    * (ind1[0] * ind1[1] * ind1[2] * ind1[3] * ind1[4] * ind1[5] * ind1[6]
            //                    * ind1[7] * ind1[8] * ind1[9]);
            ind1[this.getNumVariables() + 1] = this.evaluarRestriccionesI(ind1);
            System.out.println("Funcion objetivo: " + ind1[this.getNumVariables()]);
//            System.out.println("variables: "+ind1[j]);
        }

        return x;

    }

//    @Override
//    protected double evaluarRestriccionesD(double[] x) {
//
//        this.setSvr(0);
//
////        this.getResDesigualdad()[0] = ;
////        this.getResDesigualdad()[1] = ;
//        //suma de violacion de restricciones
//        for (int i = 0; i < this.getResDesigualdad().length; i++) {
//
//            this.setSvr(this.getSvr() + Math.max(0,
//                    (this.getResDesigualdad()[i] - this.getCompRestriccion()[i])
//            )
//            );
//        }
//
//        return this.getSvr();
//    }
    /**
     *
     * @param x
     * @return suma de violación de restricciones
     */
    @Override
    protected double evaluarRestriccionesI(double[] x) {
        double eps = 0.0001;
        this.setSvr(0);
        double f2 = 0.0;
        for (int j = 0; j < this.getNumVariables(); j++) {
            f2 = f2 + x[j] * x[j];
        }
        double h = f2 - 1.0;
        System.out.println("h1: " + h);
        h = Math.abs(h) - eps;
        System.out.println("h abs: " + h);
        this.getRestriccionesI()[0] = h;
        System.out.println("Restriccion: " + h);
        //suma de violacion de restricciones
        for (int i = 0; i < this.getRestriccionesI().length; i++) {

            this.setSvr(this.getSvr() + Math.max(0,
                    (this.getRestriccionesI()[i] - this.getCompRestriccion()[i])
            )
            );
        }

        return this.getSvr();
    }
}
