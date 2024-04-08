/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ponr;

/**
 *
 * @author abim-
 */
public class G06_CEC2006 extends PONR {

        public G06_CEC2006() {
            this.setNombre("G06_CEC2006");
            this.setMejorConocido(-6961.81387558015);
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
                {13.0, 100.0},
                {0.0, 100.0}
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

                ind1[this.getNumVariables()] = Math.pow((ind1[0]-10),3)+Math.pow((ind1[1]-20),3);

                ind1[this.getNumVariables() + 1] = this.evaluarRestriccionesD(ind1);

            }
            return x;

        }

        @Override
        protected double evaluarRestriccionesD(double[] x) {

            this.setSvr(0);

            this.getResDesigualdad()[0] = -1*(Math.pow((x[0]-5),2))-Math.pow((x[1]-5),2)+100;
            this.getResDesigualdad()[1] = Math.pow((x[0]-6),2)+Math.pow((x[1]-5), 2)-82.81;


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
