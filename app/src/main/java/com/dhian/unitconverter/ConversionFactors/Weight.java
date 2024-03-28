package com.dhian.unitconverter.ConversionFactors;

public class Weight {
    private static final double GRAM_TO_KILOGRAM = 0.001;
    private static final double KILOGRAM_TO_GRAM = 1000;
    private static final double GRAM_TO_POUND = 0.00220462;
    private static final double POUND_TO_GRAM = 453.592;
    private static final double GRAM_TO_OUNCE = 0.035274;
    private static final double OUNCE_TO_GRAM = 28.3495;
    private static final double GRAM_TO_TON = 0.000001;
    private static final double TON_TO_GRAM = 1000000;

    public static double convert( double inputValue, String sourceUnit, String destinationUnit) {
        double result = -1; // Default result indicating unsupported units

        switch (sourceUnit) {
            case "g":
                result = convertFromGram(destinationUnit, inputValue);
                break;
            case "kg":
                result = convertFromKilogram(destinationUnit, inputValue);
                break;
            case "pound":
                result = convertFromPound(destinationUnit, inputValue);
                break;
            case "ounce":
                result = convertFromOunce(destinationUnit, inputValue);
                break;
            case "ton":
                result = convertFromTon(destinationUnit, inputValue);
                break;
        }

        result = round(result, 2);

        return result;
    }

    private static double convertFromGram(String destinationUnit, double inputValue) {
        switch (destinationUnit) {
            case "g":
                return inputValue;
            case "kg":
                return inputValue * GRAM_TO_KILOGRAM;
            case "pound":
                return inputValue * GRAM_TO_POUND;
            case "ounce":
                return inputValue * GRAM_TO_OUNCE;
            case "ton":
                return inputValue * GRAM_TO_TON;
            default:
                return -1;
        }
    }

    private static double convertFromKilogram(String destinationUnit, double inputValue) {
        switch (destinationUnit) {
            case "g":
                return inputValue * KILOGRAM_TO_GRAM;
            case "kg":
                return inputValue;
            case "pound":
                return inputValue * KILOGRAM_TO_GRAM * GRAM_TO_POUND;
            case "ounce":
                return inputValue * KILOGRAM_TO_GRAM * GRAM_TO_OUNCE;
            case "ton":
                return inputValue * KILOGRAM_TO_GRAM * GRAM_TO_TON;
            default:
                return -1;
        }
    }

    private static double convertFromPound(String destinationUnit, double inputValue) {
        switch (destinationUnit) {
            case "g":
                return inputValue * POUND_TO_GRAM;
            case "kg":
                return inputValue * POUND_TO_GRAM * GRAM_TO_KILOGRAM;
            case "pound":
                return inputValue;
            case "ounce":
                return inputValue * POUND_TO_GRAM * GRAM_TO_OUNCE;
            case "ton":
                return inputValue * POUND_TO_GRAM * GRAM_TO_TON;
            default:
                return -1;
        }
    }

    private static double convertFromOunce(String destinationUnit, double inputValue) {
        switch (destinationUnit) {
            case "g":
                return inputValue * OUNCE_TO_GRAM;
            case "kg":
                return inputValue * OUNCE_TO_GRAM * GRAM_TO_KILOGRAM;
            case "pound":
                return inputValue * OUNCE_TO_GRAM * GRAM_TO_POUND;
            case "ounce":
                return inputValue;
            case "ton":
                return inputValue * OUNCE_TO_GRAM * GRAM_TO_TON;
            default:
                return -1;
        }
    }

    private static double convertFromTon(String destinationUnit, double inputValue) {
        switch (destinationUnit) {
            case "g":
                return inputValue * TON_TO_GRAM;
            case "kg":
                return inputValue * TON_TO_GRAM * GRAM_TO_KILOGRAM;
            case "pound":
                return inputValue * TON_TO_GRAM * GRAM_TO_POUND;
            case "ounce":
                return inputValue * TON_TO_GRAM * GRAM_TO_OUNCE;
            case "ton":
                return inputValue;
            default:
                return -1;
        }
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long temp = Math.round(value);
        return (double) temp / factor;
    }
}
