package com.dhian.unitconverter.ConversionFactors;

public class Temperature {
    public static double convert(double inputValue, String sourceUnit, String destinationUnit) {
        double celsiusValue = 0;

        // Convert the input value to Celsius
        if (sourceUnit.equalsIgnoreCase("Celsius")) {
            celsiusValue = inputValue;
        } else if (sourceUnit.equalsIgnoreCase("Fahrenheit")) {
            celsiusValue = (inputValue - 32) * 5 / 9;
        } else if (sourceUnit.equalsIgnoreCase("Kelvin")) {
            celsiusValue = inputValue - 273.15;
        }

        // Convert Celsius to the destination unit
        double result = 0;
        if (destinationUnit.equalsIgnoreCase("Celsius")) {
            result = celsiusValue;
        } else if (destinationUnit.equalsIgnoreCase("Fahrenheit")) {
            result = (celsiusValue * 9 / 5) + 32;
        } else if (destinationUnit.equalsIgnoreCase("Kelvin")) {
            result = celsiusValue + 273.15;
        }

        result = round(result, 2);

        return result;
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long temp = Math.round(value);
        return (double) temp / factor;
    }

}
