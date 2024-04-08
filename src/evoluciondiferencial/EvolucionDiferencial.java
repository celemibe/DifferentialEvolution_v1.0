package evoluciondiferencial;

import estadisticas.Estadisticas;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import ponr.PONR;

public class EvolucionDiferencial {

    //Números de individuos en la población [10 - 150]
    private int poblacion = 50;

    //Cruza - Crossover [0 - 1]
    private double CR = 0.65;

    //Mutación [0 - 1]
    private double F = 0.65;

    //Número de evaluaciones [100 - 1000]
    private int gmax = 300;
    private double [][] mejorIter;
            
    //Población
    private double[][] individuos;

    private double[][] guardados;

    private PONR ponr;

    private Estadisticas estd;

    private String conv;

    private int itera;

    private int eval;
    //Mejores valores encontrados
    private double[][] mejoresValores = new double[0][0];

    //Procesos de evolución diferencial
    private ProcesoED procesoEd;

    private NumberRandom rnd;

    private List<Double> convergencia;

    //parametros para la busqueda local
    private double PRO_BUS = 0.1;
    private double MAX_ITER = 10;

    public EvolucionDiferencial() {
        //objeto para numeros aletorios
        this.rnd = new NumberRandom();

        //Intancia de la clase ProcesoED
        this.procesoEd = new ProcesoED();

        this.convergencia = new ArrayList();
    }

    //RESORTE
//    double vConocido = 0.012681;
//    int var = 3;  //variables del problema x1, x2 y x3
//    int numRestricD = 4;  //restricciones de desigualdad
//    double[][] rango = {{0.05, 2}, {0.25, 1.3}, {2, 15}};  // RANGO DE VARIABLES
    
    
    public void iniciar(PONR ponr, int iteracion, String nombre, String conv) {
        this.conv = conv;
        this.itera = iteracion;
        //evaluamos que el numero de iteraciones sea correcto        
        iteracion = ((iteracion < 1) || (iteracion > 30)) ? 1/*SI*/ : iteracion/*NO*/;

        //en caso de ser mañor a 1 ejecución independiente
        int medianaIter = 1;
        if (iteracion > 1) {
            medianaIter = (int) iteracion / 2;
        }
        
        
        //Se crea el archivo que guardara los datos
//        if (notExists) {
        try {
            Workbook libro = new XSSFWorkbook();
            final String name = nombre;
            for (int i = 0; i < iteracion; i++) {
                Sheet hoja = libro.createSheet("Iteracion " + (i + 1));
            }
            File direccionActual = new File(".");
            String ubicacion = direccionActual.getAbsolutePath();
            String ubicacionSalida = ubicacion.substring(0, ubicacion.length() - 1) + name;
            FileOutputStream outputstream;
            outputstream = new FileOutputStream(ubicacionSalida);
            libro.write(outputstream);
            outputstream.close();
//            System.out.println("Guardado");
        } catch (FileNotFoundException ex) {
            ex.printStackTrace(System.out);
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
//        }

        //Se crea el archivo para convergencia
        try {
            Workbook libro = new XSSFWorkbook();
            final String name = conv;
            for (int i = 0; i < iteracion; i++) {
                Sheet hoja = libro.createSheet("Iteracion " + (i + 1));
            }
            File direccionActual = new File(".");
            String ubicacion = direccionActual.getAbsolutePath();
            String ubicacionSalida = ubicacion.substring(0, ubicacion.length() - 1) + name;
            FileOutputStream outputstream;
            outputstream = new FileOutputStream(ubicacionSalida);
            libro.write(outputstream);
            outputstream.close();
//            System.out.println("Guardado");
        } catch (FileNotFoundException ex) {
            ex.printStackTrace(System.out);
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }

        double[][] mutantes = new double[this.getPoblacion()][ponr.getNumVariables() + 2];

        //Se establece el tamaño de la matriz individuos donde se guardara
        //los valores de las variables, la función objetivo y 
        //la suma de violación de restricciones
        this.individuos = new double[this.getPoblacion()][ponr.getNumVariables() + 2];

        //cuenta las evaluaciones realizadas
        int contadorEvaluaciones;

        //inicializamos la matriz para mejores valores
        if (iteracion == 1) {
            this.mejoresValores = this.individuos;
        } else {
            this.mejoresValores = new double[iteracion][ponr.getNumVariables() + 2];
        }

        //Inicia las iteraciones del algoritmo [1 - 30]
        for (int iter = 0; iter < iteracion; iter++) {

            //variables para calcular el performance
            contadorEvaluaciones = 0;

            //Crear la problación inicial de acuerdo a los rangos de variables
            //Se establece el tamaño de la matriz mejores valores
            this.individuos = NuevaPoblacion.poblacionInicial(this.getPoblacion(),
                    ponr
            );

//            for (int i = 0; i < this.individuos.length; i++) {
//                System.out.println(java.util.Arrays.toString(individuos[i]));
//            }
            //evaluar la FO y restricciones con la poblacion inicial
            this.individuos = ponr.evaluarFO(individuos);

            //contar evaluaciones de los individuos en la FO
            contadorEvaluaciones += this.getPoblacion();

//            System.out.println("Población inicial evaluada.");
            //inicia las generaciones del algoritmo
            for (int g = 0; g < this.getGmax(); g++) {

//                System.out.println("Inicia la generación " + (g + 1));
                //inicia el proceso de evolucion diferencial
                for (int i = 0; i < this.getPoblacion(); i++) {

                    //seleccion de 3 individuos aleatorios
                    int[] ind = this.procesoEd.posicionesAleatorias(this.getPoblacion(), i/*numero a descartar*/);

                    //instaciamos el vector hijo
                    double[] hijo = new double[ponr.getNumVariables() + 2];

                    //generamos un valor aleatorio uniforme
                    //creamos un aleatorio uniforme
                    double aleatorio;
                    int jrand = this.rnd.getRandomRankUnif(0, ponr.getNumVariables());
//
//                    //iteramos cada individuo segun el numero de variables
                    for (int j = 0; j < ponr.getNumVariables(); j++) {
                        //generamos un número aleatorio
                        aleatorio = this.rnd.getRandomUnif();

                        if (aleatorio <= getCR() || j == jrand) {//cruza controla la recombinación                                                    

                            //se mutan los tres individuos
                            //se pregunta si existe pertenencia en los rangos de variables
                            if (ponr.isVariableDiscreta()[j]) {
                                hijo[j] = (int) this.procesoEd.mutacion(this.individuos[ind[0]][j], this.individuos[ind[1]][j], this.individuos[ind[2]][j], this.getF());
                            } else {
                                hijo[j] = this.procesoEd.mutacion(this.individuos[ind[0]][j], this.individuos[ind[1]][j], this.individuos[ind[2]][j], this.getF());
                            }
                            //  System.out.println("hijo generado " + hijo[j]);

                            //verificamos los rangos de cada variable de acuerdo a la mutación
                            if ((hijo[j] < ponr.getRangos()[j][0])
                                    || (hijo[j] > ponr.getRangos()[j][1])) {
                                // System.out.println("fuera de rango:  " + hijo[j]);
                                hijo[j] = this.individuos[i][j];
                                // System.out.println("hijo dentro de rango:  " + hijo[j]);

                                if (ponr.isVariableDiscreta()[j]) {
                                    hijo[j] = (int) this.individuos[i][j];
                                } else {
                                    hijo[j] = this.individuos[i][j];
                                }

                            }//termina IF
                        } else { //no se mutan
                            hijo[j] = this.individuos[i][j];
                        }

                    }//cierra for

                    //   mutantes[i] = hijo;
////                    // Búsqueda local
////                    if (this.rnd.getRandomUnif() <= this.getPRO_BUS()) {
//////                        System.out.println("entre");
////                        double[][] hijoAux = new double[1][];
////                        hijoAux[0] = hijo;
////                        //evaluar hijo
////                        hijo = ponr.evaluarFO(hijoAux)[0];
////
////                        int iteracionBL = 0;
////                        double[] puntoInicial = hijo;
////                        double[] optimoLocal = puntoInicial;
////                        double fitnessInicial = hijo[ponr.getNumVariables()];
////
////                        while (iteracionBL < this.getMAX_ITER()) {
////                            double[] puntoCandidato = optimoLocal;
////
////                            for (int j = 0; j < ponr.getNumVariables(); j++) {
////                                puntoCandidato[j] += this.rnd.getRandomGaussian();
////
//////                                puntoCandidato[j] = puntoCandidato[j] + (Math.random() - 1) * 0.1;
//////                                System.out.println(this.rnd.getRandomGaussian());
////                                if (ponr.isVariableDiscreta()[j]) {
////                                    puntoCandidato[j] = (int) Math.max(Math.min(puntoCandidato[j],
////                                             ponr.getRangos()[j][1]),
////                                             ponr.getRangos()[j][0]
////                                    );
////                                }else puntoCandidato[j] = Math.max(Math.min(puntoCandidato[j],
////                                             ponr.getRangos()[j][1]),
////                                             ponr.getRangos()[j][0]
////                                    );
////
////                            }
////                            hijoAux[0] = puntoCandidato;
////                            double fitnessCandidato = ponr.evaluarFO(hijoAux)[0][ponr.getNumVariables()];
////
////                            if (fitnessCandidato < fitnessInicial) {
////                                optimoLocal = hijoAux[0];
////                                fitnessInicial = fitnessCandidato;
//////                                System.out.println("cdscs");
////                            }
////
////                            iteracionBL++;
////                        } // cierra while
////
////                        hijoAux[0] = optimoLocal;
////
////                        double fitnessOL = ponr.evaluarFO(hijoAux)[0][ponr.getNumVariables()];
////
////                        if (fitnessOL < hijo[ponr.getNumVariables()]) {
////                            hijo = optimoLocal;
////                            System.out.println("VBSDFVBSDFVBKSDFVSDFVSDFVBDFBDFBDF");
////                        }
////
////                    }// cierra if busqueda local
                    //para evaluar hijo, lo convertimos en array
                    double[][] hijoAux = new double[1][];
                    hijoAux[0] = hijo;

                    //evaluar hijo
                    hijoAux = ponr.evaluarFO(hijoAux);
                    hijo = hijoAux[0];

                    //incrementamos contador de evaluaciones
                    contadorEvaluaciones++;

                    //comparamos la calidad del hijo con el padre
                    if ((hijo[ponr.getNumVariables() + 1] == 0) && (this.individuos[i][ponr.getNumVariables() + 1] == 0)) {

                        if (hijo[ponr.getNumVariables()] < this.individuos[i][ponr.getNumVariables()]) {
                            this.individuos[i] = hijo; //copiamos todo el hijo por ser mejor
                        }

                    }//cierra if

                    if ((hijo[ponr.getNumVariables() + 1] > 0) && (this.individuos[i][ponr.getNumVariables() + 1] > 0)) {
                        if (hijo[ponr.getNumVariables()] < this.individuos[i][ponr.getNumVariables()]) {
                            this.individuos[i] = hijo; //copiamos todo el hijo por ser mejor
                        }
                    }//cierra if

                    if ((hijo[ponr.getNumVariables() + 1] == 0) && (this.individuos[i][ponr.getNumVariables() + 1] > 0)) {
                        this.individuos[i] = hijo; //copiamos todo el hijo por ser mejor
                    }//cierra if

                }//cierra proceso evolución diferencial

                //ordenamos los individuos
                this.individuos = this.procesoEd.ordenarP(this.individuos, ponr.getNumVariables());

//                if ((iter + 1) == medianaIter) {
                this.convergencia.add(this.individuos[0][ponr.getNumVariables()]);
//                }

            }//cierra for de generaciones

//            for (int j = 0; j < this.individuos.length; j++) {
//                System.out.println(java.util.Arrays.toString(this.individuos[j]));
//            }
            if (iteracion > 1) {
                this.mejoresValores[iter] = this.individuos[0];
            } else {
                this.mejoresValores = this.individuos;
            }

            //****mandar a guardar la lista de convergencia en el excel
            //Se cargan los datos de convergencia
            String filePath1 = conv;
            Path path1 = Paths.get(filePath1);
            boolean exists1 = Files.exists(path1);
//            if (iteracion == 1) {
            try {
                File fil = new File(filePath1);
                FileInputStream file = new FileInputStream(fil);
                Workbook wb = new XSSFWorkbook(file);

                Sheet hoja = wb.getSheetAt(iter);
                int indiceFila = 0;
                Row fila = hoja.createRow(indiceFila);
                String contenido = "";
                int f = this.convergencia.size();

                double[][] conver = new double[2][f];

                for (int i = 0; i < f; i++) {
                    conver[0][i] = i + 1;
                    conver[1][i] = this.convergencia.get(i);
                }
                for (int j = 0; j < 2; j++) {
                    fila = hoja.createRow(indiceFila);
                    for (int c = 0; c < f; c++) {
                        contenido = String.valueOf(conver[j][c]);
                        fila.createCell(c).setCellValue(Double.parseDouble(contenido));
                    }
                    indiceFila++;
                }

                File direccionActual = new File(".");
                String ubicacion = direccionActual.getAbsolutePath();
                String ubicacionSalida = ubicacion.substring(0, ubicacion.length() - 1) + conv;
                FileOutputStream outputstream;
                outputstream = new FileOutputStream(ubicacionSalida);
                wb.write(outputstream);
                outputstream.close();
//                System.out.println("Save");
            } catch (FileNotFoundException ex) {
                ex.printStackTrace(System.out);
            } catch (IOException ex) {
                ex.printStackTrace(System.out);
            }
//            } else if (iteracion > 1) {
//                try {
//                    File fil = new File(filePath1);
//                    FileInputStream file = new FileInputStream(fil);
//                    Workbook wb = new XSSFWorkbook(file);
//
//                    Sheet hoja = wb.getSheetAt(iter);
//                    int indiceFila = 0;
//                    Row fila = hoja.createRow(indiceFila);
//                    String contenido = "";
//                    int f = this.convergencia.size();
//                    double[][] conver = new double[2][f];
//
//                    for (int i = 0; i < f; i++) {
//                        conver[0][i] = i + 1;
//                        conver[1][i] = this.convergencia.get(i);
//                    }
//
//                    if (conver[0].length > this.getGmax()) {
//                        int ini = conver[0].length / iteracion;
//                        System.out.println("" + ini);
//                        for (int j = 0; j < 2; j++) {
//                            fila = hoja.createRow(indiceFila);
//                            for (int c = ini - 1; c < f; c++) {
//                                contenido = String.valueOf(conver[j][c]);
//                                fila.createCell(c).setCellValue(Double.parseDouble(contenido));
//                            }
//                            indiceFila++;
//                        }
//                    }
//
//                    File direccionActual = new File(".");
//                    String ubicacion = direccionActual.getAbsolutePath();
//                    String ubicacionSalida = ubicacion.substring(0, ubicacion.length() - 1) + conv;
//                    FileOutputStream outputstream;
//                    outputstream = new FileOutputStream(ubicacionSalida);
//                    wb.write(outputstream);
//                    outputstream.close();
////                System.out.println("Save");
//                } catch (FileNotFoundException ex) {
//                    ex.printStackTrace(System.out);
//                } catch (IOException ex) {
//                    ex.printStackTrace(System.out);
//                }
//            }

            //****mandar a guardar la matriz de individuos en el excel
            for (int i = 0; i < this.individuos.length; i++) {
                this.guardados = this.procesoEd.ordenarP(this.individuos, ponr.getNumVariables());
//                System.out.println(java.util.Arrays.toString(this.guardados[i]));
            }

            String filePath = nombre;
            Path path = Paths.get(filePath);
            boolean exists = Files.exists(path);
            boolean notExists = Files.notExists(path);
            boolean isDir = Files.isDirectory(path);

            //Se comprueba y se llena el archivo de poblacion
            if (exists) {
                try {
                    File fil = new File(filePath);
                    FileInputStream file = new FileInputStream(fil);
                    Workbook wb = new XSSFWorkbook(file);
                    int Hojas = wb.getNumberOfSheets();

                    Sheet sheet = wb.getSheetAt(iter);
                    int indiceFila = 0;
                    Row fila = sheet.createRow(indiceFila);
                    String encabezado = "";
                    String contenido = "";

                    int nColum = ponr.getOrdenVariables().length;
                    String[] cabecera = new String[nColum + 2];

                    for (int i = 0; i < nColum; i++) {
                        encabezado = cabecera[i] = ponr.getOrdenVariables()[i];
                        Cell celda = fila.createCell(i);
                        celda.setCellValue(encabezado);
                    }

                    cabecera[nColum] = "FO";
                    encabezado = cabecera[nColum];
                    Cell celda = fila.createCell(nColum);
                    celda.setCellValue(encabezado);
                    cabecera[nColum + 1] = "SVR";
                    encabezado = cabecera[nColum + 1];
                    celda = fila.createCell(nColum + 1);
                    celda.setCellValue(encabezado);

                    int filas = this.individuos.length;
                    int columnas = nColum + 2;
                    double[][] objectAux = new double[filas][columnas];
                    indiceFila++;
                    for (int i = 0; i < filas; i++) {
                        fila = sheet.createRow(indiceFila);
                        for (int j = 0; j < columnas; j++) {
                            objectAux[i][j] = this.individuos[i][j];
                            contenido = String.valueOf(objectAux[i][j]);
                            fila.createCell(j).setCellValue(Double.parseDouble(contenido));
                        }
                        indiceFila++;
                    }

                    File direccionActual = new File(".");
                    String ubicacion = direccionActual.getAbsolutePath();
                    String ubicacionSalida = ubicacion.substring(0, ubicacion.length() - 1) + nombre;
                    FileOutputStream outputstream;
                    outputstream = new FileOutputStream(ubicacionSalida);
                    wb.write(outputstream);
                    outputstream.close();
//                    System.out.println("ya");

                } catch (FileNotFoundException ex) {
                    ex.printStackTrace(System.out);
                } catch (IOException ex) {
                    ex.printStackTrace(System.out);
                }
            }


            this.eval = this.getGmax();

        }//termina for del número de iteraciones

        //ordenamos las mejores soluciones para mostrar en la tabla final
        ////this.mejoresValores = this.procesoEd.ordenarP(this.mejoresValores, ponr.getNumVariables());
     this.mejorIter=this.procesoEd.ordenarP(this.mejoresValores, ponr.getNumVariables());
    //este mejorIter es para mostrar en la tabla final remarcado al mejor
    }

    /**
     * @return the poblacion
     */
    public int getPoblacion() {
        return poblacion;
    }

    /**
     * @param poblacion the poblacion to set
     */
    public void setPoblacion(int poblacion) {
        this.poblacion = poblacion;
    }

    /**
     * @return the CR
     */
    public double getCR() {
        return CR;
    }

    /**
     * @param CR the CR to set
     */
    public void setCR(double CR) {
        this.CR = CR;
    }

    /**
     * @return the F
     */
    public double getF() {
        return F;
    }

    /**
     * @param F the F to set
     */
    public void setF(double F) {
        this.F = F;
    }

    /**
     * @return the gmax
     */
    public int getGmax() {
        return gmax;
    }

    /**
     * @param gmax the gmax to set
     */
    public void setGmax(int gmax) {
        this.gmax = (gmax/this.poblacion)-1;
    }

    /**
     * @return the mejoresValores
     */
    public double[][] getMejoresValores() {
        return mejoresValores;
    }

    /**
     * @return the individuos
     */
    public double[][] getIndividuos() {
        return individuos;
    }

    /**
     * @return the guardados
     */
    public double[][] getGuardados() {
        return guardados;
    }

    /**
     * @return the guardados
     */
    public int getEval() {
        return eval;
    }

    /**
     * @return the PRO_BUS
     */
    public double getPRO_BUS() {
        return PRO_BUS;
    }

    /**
     * @param PRO_BUS the PRO_BUS to set
     */
    public void setPRO_BUS(double PRO_BUS) {
        this.PRO_BUS = PRO_BUS;
    }

    /**
     * @return the MAX_ITER
     */
    public double getMAX_ITER() {
        return MAX_ITER;
    }

    /**
     * @param MAX_ITER the MAX_ITER to set
     */
    public void setMAX_ITER(double MAX_ITER) {
        this.MAX_ITER = MAX_ITER;
    }

    /**
     * @return the convergencia
     */
    public double[][] getConvergencia() {

        int f = this.gmax;

        double[][] conver = new double[2][f];

//        for (int i = 0; i < f; i++) {
//            conver[0][i] = i + 1;
//            conver[1][i] = this.convergencia.get(i);
//        }
        String filePath1 = this.conv;
        Path path1 = Paths.get(filePath1);
        try {
            File fil = new File(filePath1);
            FileInputStream file = new FileInputStream(fil);
            Workbook wb = new XSSFWorkbook(file);

            for (int i = 0; i < this.itera; i++) {
                Sheet hoja = wb.getSheetAt(i);
                if (i == 0) {
                    int numFilas = hoja.getLastRowNum();

                    for (int a = 0; a <= numFilas; a++) {
                        Row fila = hoja.getRow(a);
                        int numCols = fila.getLastCellNum();
                        for (int b = 0; b < numCols; b++) {
                            Cell celda = fila.getCell(b);
                            conver[a][b] = celda.getNumericCellValue();
                        }
                    }
                }
            }
            File direccionActual = new File(".");
            String ubicacion = direccionActual.getAbsolutePath();
            String ubicacionSalida = ubicacion.substring(0, ubicacion.length() - 1) + conv;
            FileOutputStream outputstream;
            outputstream = new FileOutputStream(ubicacionSalida);
            wb.write(outputstream);
            outputstream.close();
//                System.out.println("Save");
        } catch (FileNotFoundException ex) {
            ex.printStackTrace(System.out);
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }

        return conver;
    }

}
