package de.kochAppBackend.KochAppBackend.validations;

import static java.lang.Double.parseDouble;

public class Misc {
    //TODO: Bad! An alternative must be found!
    public static boolean isNumeric(String number) {
        try {
            parseDouble(number);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}
