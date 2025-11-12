public interface Daniel_TempConvertable {
    double ABS_ZERO_K = 0;
    double ABS_ZERO_C = -273.15;
    double ABS_ZERO_F = -459.66999999999996;

    double convertFtoC(double fTemp);
    double convertCtoF(double cTemp);
    double convertCtoK(double cTemp);
    double convertKtoC(double kTemp);
    double convertFtoK(double fTemp);
    double convertKtoF(double kTemp);
}