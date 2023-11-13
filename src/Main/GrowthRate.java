package Main;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.util.HashMap;
import java.util.Map;

import static java.lang.Math.ceil;

public class GrowthRate {

     static String thicknessEquation = "firstLayerThickness * ( 1";
     static double firstLayerThickness;
     static double totalThickness ;
     static int numberOfLayers;

    public GrowthRate(double firstLayThick, double totalThick, int numOfLay) {

        firstLayerThickness = firstLayThick;
        totalThickness = totalThick;
        numberOfLayers = numOfLay;
    }


    public static double[] getGrowthRateAndFinalThickness() {


        double finalLayerThickness;
        double growthRate = 0;
        double finalLayerThickAndGrowthRate[] = new double[2];

        // 1. Build the expression -- example: firstLayerThickness * ( 1 + growthRate^2 + growthRate^3) - totalThickness
        if (numberOfLayers == 1) {

            finalLayerThickness = firstLayerThickness;

            finalLayerThickAndGrowthRate[0] = finalLayerThickness;
            finalLayerThickAndGrowthRate[1] = 0;

            return finalLayerThickAndGrowthRate;
        }
        else {

            thicknessEquation = "firstLayerThickness * ( 1";

            for (int i = 1; i < numberOfLayers; i++) {

                thicknessEquation = thicknessEquation + " + growthRate^" + i;
            }
            thicknessEquation = thicknessEquation + ") - totalThickness";
            System.out.println(thicknessEquation);
        }

        // 2. Evaluate the built expression using exp4j and solve the polynomial equation using Brents method

        ///////////////////////////////////////Brents method///////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
        double a = -100;  //Lower bound
        double b = 100;   //Upper bound
        double c;       //Previous bound
        double d = 0;
        double L;       //Temporary value
        double maxError = 0.0001;
        int iteration = 0;
        double theResult = 0;
        double theResults[] = new double[50];    //Array of results from all iterations
        double dd[] = new double[50];            //Array of results2 from all iterations
        double ii[] = new double[50];            //Array of all iterations

        //Testing if root is inside the two bounds
        if (f(a) * f(b) >= 0) {

            System.out.println("Root out of bounds!");
        }
        //Swapping bounds
        if (Math.abs(f(a)) < Math.abs(f(b))) {

            L = a;
            a = b;
            b = L;
        }

        c = a;

        boolean wasBisection = true;

        while (Math.abs(b - a) >= maxError) {

            iteration++;

            if (f(a) != f(c) && f(b) != f(c)) {

                theResult = a*f(b)*f(c) / (f(a)-f(b))*(f(a)-f(c))+    ////Inverse Quadratic Interpolation
                        b*f(a)*f(c) / ((f(b)*f(a))*(f(b)-f(c)))+
                        c*f(a)*f(b) / ((f(c)-f(a))*(f(c)-f(b)));
            }
            else {
                theResult = b-f(b)*(b-a) / (f(b)-f(a));  ////Secant method
            }
            if (theResult <= (3*a+b)/4 || theResult >= b ||
                    (wasBisection && Math.abs(theResult-b) >= Math.abs(b-c)/2) ||
                    (!wasBisection && Math.abs(theResult-b) >= Math.abs(c-d)/2) ||
                    (wasBisection && Math.abs(b-c) < Math.abs(maxError)) ||
                    (!wasBisection && Math.abs(c-d) >= Math.abs(maxError))) {

                theResult = (a+b)/2;
                wasBisection = true;
            }
            else {
                wasBisection = false;
            }
            //Calculate f of the result
            d = c;
            c = b;

            if (f(a) * f(theResult) < 0) {

                b = theResult;
            }
            else {

                a = theResult;
            }
            //Swapping a and b contents

            if (Math.abs(f(a)) < Math.abs(f(b))){

                if (Math.abs(f(a)) < Math.abs(f(b))) {

                    L = a;
                    a = b;
                    b = L;
                }
                theResults[iteration] = theResult;
                dd[iteration] = d;
                ii[iteration] = iteration;

                growthRate = theResult;

                System.out.println(theResult);
            }
        }
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        if (firstLayerThickness < 1) {

            int orderOfMagnitude = Math.abs((int) ceil(Math.log10(firstLayerThickness)));

            finalLayerThickAndGrowthRate[0] = Math.pow(growthRate, numberOfLayers - 1) / Math.pow(10, orderOfMagnitude);
        }
        else {

            finalLayerThickAndGrowthRate[0] = Math.pow(growthRate, numberOfLayers - 1);
        }
        finalLayerThickAndGrowthRate[1] = growthRate;

        return finalLayerThickAndGrowthRate;
    }
    static double f(double g) {

        double fg;
        //double firstLayerThickness = 1;
        String fgString = thicknessEquation;

        Map<String, Double> vars = new HashMap<>();
        vars.put("growthRate", g);
        vars.put("firstLayerThickness", firstLayerThickness);
        vars.put("totalThickness", totalThickness);

        ExpressionBuilder eb = new ExpressionBuilder(fgString);
        eb.variables("growthRate", "firstLayerThickness", "totalThickness");

        Expression ex = eb.build();
        ex.setVariables(vars);

        fg = ex.evaluate();

        return fg;
    }
}








