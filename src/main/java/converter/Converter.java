package converter;

public class Converter {
    public static double simple(String s) {
        String str = s.substring(0, s.length() - 1);
        double value = Double.parseDouble(str);
        switch (s.charAt(s.length() - 1)) {
            case ('$'):
                value *= 60;
                return value;
            case ('€'):
                value *= 70;
                return value;
        }
        return value;
    }
}
