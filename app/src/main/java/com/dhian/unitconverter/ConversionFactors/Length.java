package com.dhian.unitconverter.ConversionFactors;

import java.util.HashMap;
import java.util.Map;

public class Length {
    private static final Map<String, Double> conversionFactors = new HashMap<>();

    static {
        // Conversion factors relative to meters
        conversionFactors.put("cm", 0.01);
        conversionFactors.put("inch", 0.0254);
        conversionFactors.put("foot", 0.3048);
        conversionFactors.put("yard", 0.9144);
        conversionFactors.put("km", 1000.0);
        conversionFactors.put("mile", 1609.34);
    }

    public static double convert(double inputValue, String sourceUnit, String destinationUnit) {
        // Convert input value to meters
        double valueInMeters = inputValue * conversionFactors.get(sourceUnit);

        // Convert from meters to destination unit
        double convertedValue = valueInMeters / conversionFactors.get(destinationUnit);

        convertedValue = round(convertedValue, 3);

        return convertedValue;
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long temp = Math.round(value);
        return (double) temp / factor;
    }
}
