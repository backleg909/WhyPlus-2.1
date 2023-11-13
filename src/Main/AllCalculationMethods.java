package Main;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.util.HashMap;
import java.util.Map;

public class AllCalculationMethods {

    public static double getDynViscosityWater(double waterTemperature) {

        double dynViscosityWater;

        dynViscosityWater = Math.exp(-3.7188 + (578.919/(-137.546 + waterTemperature)));    // Vogel equation

        return dynViscosityWater;
    }

    public static double getDynViscosityAir(double airTemperature) {

        double dynViscosityAir;

        dynViscosityAir = (1.458e-6 * Math.pow(airTemperature, 1.5)) / (airTemperature + 110.4); // Sutherland equation for air

        return dynViscosityAir;
    }

    public static double getKinViscosityAir(double airTemperature, double absPressure) {

        double kinViscosityAir;

        kinViscosityAir = getDynViscosityAir(airTemperature) / getDensityAir(airTemperature, absPressure);

        return kinViscosityAir;
    }

    public static double getDensityAir(double airTemperature, double absPressure) {

        final double specGasConstant = 287.058; // For dry air
        double densityAir;

        densityAir = absPressure / (airTemperature * specGasConstant);

        return densityAir;
    }

    public static double getKinViscosityWater(double dynViscosityWater, double densityWater) {

        double kinViscosityWater = dynViscosityWater/densityWater;

        return kinViscosityWater;
    }

    public static double getDensityWater(double waterTemperature) {

        double densityWater;
        double waterTemperatureC = waterTemperature - 273.15d;
        double nominalWaterDensity = 9.998407306915692e+02;
        double a1 = 0.065564447400740;
        double a2 = -0.008619171545808;
        double a3 = 7.217694748210123e-05;
        double a4 = -4.812120252252117e-07;
        double a5 = 1.413875310205396e-09;

        densityWater = nominalWaterDensity + a1*waterTemperatureC +
                a2*Math.pow(waterTemperatureC, 2) + a3*Math.pow(waterTemperatureC, 3) +
                a4*Math.pow(waterTemperatureC, 4) + a5*Math.pow(waterTemperatureC, 5);

        return densityWater;
    }

    public static double getReynoldsNumber(double velocity, double length, double kinViscosity) {

        double reynoldsNumber = (velocity * length) / (kinViscosity);

        return reynoldsNumber;
    }

    public static double getSkinFrictionCoefficient(double reynoldsNumber, boolean internalFlow) {

        double skinFrictionCoefficient;

        if(internalFlow) {

            skinFrictionCoefficient = 0.079 * Math.pow((reynoldsNumber), -0.25);
        }
        else {
            skinFrictionCoefficient = 0.058 * Math.pow((reynoldsNumber), -0.2);
        }
        return skinFrictionCoefficient;
    }

    public static double getWallShearStress(double skinFrictionCoefficient, double density, double velocity) {

        double wallShearStress = 0.5 * skinFrictionCoefficient * density * Math.pow(velocity, 2);

        return wallShearStress;
    }

    public static double getFrictionalVelocity(double wallShearStress, double density) {

        double frictionalVelocity = Math.sqrt(wallShearStress / density);

        return frictionalVelocity;
    }
    public static double getFirstLayerThickness(boolean CFXSolver,
                                         int yPlus,
                                         double dynViscosity,
                                         double density,
                                         double frictionalVelocity) {
        double firstLayerThickness;
        int CFXFactor;

        if(CFXSolver) {
            CFXFactor = 1;
        }
        else {
            CFXFactor = 2;  // Factor 2 is used for Ansys AIM, Fluent and StarCCM+
        }
        firstLayerThickness = CFXFactor * (yPlus * dynViscosity) / (density * frictionalVelocity);

        return firstLayerThickness;
    }

    public static double getBoundaryLayerThickness(double reynoldsNumber, double charLength) {

        double boundaryLayerThickness;

        if(reynoldsNumber > 5e+5) {

            boundaryLayerThickness = (0.38*charLength) / (Math.pow(reynoldsNumber, 0.2));
        }
        else {
            boundaryLayerThickness = (4.91*charLength) / Math.sqrt(reynoldsNumber);
        }
        return boundaryLayerThickness;
    }
    public static boolean getIsTurbulent(double reynoldsNumber) {

        boolean isTurbulent;

        if(reynoldsNumber > 5e+5) {

            isTurbulent = true;
        }
        else {
            isTurbulent = false;
        }
        return isTurbulent;
    }

    public static double[] getTotalAndLastLayerThickness(double growthRate, double firstLayerThickness, int numberOfLayers) {

        double totalThickness;
        double thicknessMinusFirstLayer = 0;
        double totalAndFinalLayerThickness[] = new double[2];
        double finalLayerThickness;

        if (numberOfLayers == 1) {
            totalThickness = firstLayerThickness;
        }
        else {
            for (int i = 1; i < numberOfLayers; i++) {

                thicknessMinusFirstLayer += firstLayerThickness*Math.pow(growthRate, i);
            }
        }
        totalThickness = thicknessMinusFirstLayer + firstLayerThickness;
        finalLayerThickness = firstLayerThickness * Math.pow(growthRate, numberOfLayers - 1);
        totalAndFinalLayerThickness[0] = totalThickness;
        totalAndFinalLayerThickness[1] = finalLayerThickness;

        return totalAndFinalLayerThickness;
    }

    public static double[] getFirstAndLastLayerThickness(double totalThickness, int numberOfLayers, double growthRate) {

        double[] firstAndLastLayerThickness = new double[2];
        String firstLayerThicknessEquation = "totalThickness / ( 1";

        //1. Build the expression
        if (numberOfLayers == 1) {

            firstAndLastLayerThickness[0] = totalThickness;
            firstAndLastLayerThickness[1] = totalThickness;

            return firstAndLastLayerThickness;
        }
        else {

            for (int i = 1; i < numberOfLayers; i++) {

                firstLayerThicknessEquation = firstLayerThicknessEquation + " + growthRate^" + i;
            }
            firstLayerThicknessEquation = firstLayerThicknessEquation + ")";
            System.out.println(firstLayerThicknessEquation);
        }
        // 2. Evaluate the built expression using exp4j
        Map<String, Double> vars = new HashMap<>();
        vars.put("totalThickness", totalThickness);
        vars.put("growthRate", growthRate);

        ExpressionBuilder expressionBuilder = new ExpressionBuilder(firstLayerThicknessEquation);
        expressionBuilder.variables("totalThickness", "growthRate");

        Expression expression = expressionBuilder.build();
        expression.setVariables(vars);

        firstAndLastLayerThickness[0] = expression.evaluate();
        firstAndLastLayerThickness[1] = firstAndLastLayerThickness[0] * Math.pow(growthRate, numberOfLayers - 1);

        return firstAndLastLayerThickness;
    }
}


