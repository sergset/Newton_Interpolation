public class PolynomProduct {

    public String printerWynik(float[] values, float[] diffs) {

        float[] result = new float[values.length];
        for (int i = 0; i < values.length; ++i) {
            result[i] = 0;
        }

        for (int i = 0; i < values.length; ++i)
        {
            float[] poly = getPoly(values, i - 1);

            for (int j = 0; j < poly.length; ++j) {
                result[j] += poly[j] * diffs[i];
            }
        }

        for (int i = 0; i < result.length; ++i) {
            System.out.println("Współczynnik dla x^" + i + " wynosi: " + result[i]);
        }
        String wynik="y=";

        for (int i = result.length-1; i >= 0; --i)
           {
               int a=Math.round(result[i]);
            if (i==0) {
                if(a<0){
                    wynik += " "+a  ;
                } else
                    wynik += "+"+a  ;
            } else if (i==1) {
                if(a<0){
                    wynik += " "+a+"x " ;
                }
                else
                    wynik += " +"+a+"x " ;
            } else {
                if(a<0){
                    wynik += " "+a+"x^"+i;
                } else {
                    if (i == result.length-1) wynik += " " + a + "x^" + i;
                        else
                            wynik += " +" + a + "x^" + i;
                }
            }

        }
        System.out.println(wynik);
        return wynik;
    }

    public static float[] getPoly(float[] values, float i) {
        float[] coefs = {1};
        for (int j = 0; j <= i; ++j) {
            float[] coefsLocal = {-values[j], 1};
            coefs = getPolyProduct(coefs, coefsLocal);
        }
        return coefs;
    }

    public static float[] getPolyProduct(float[] coefs1, float[] coefs2) {
        int s1 = coefs1.length - 1;
        int s2 = coefs2.length - 1;
        int degree = s1 + s2;

        float[] coefsProduct = new float[degree + 1];
        for (int k = 0; k <= degree; ++k) {
            coefsProduct[k] = 0;
        }
        for (int i = 0; i <= s1; ++i) {
            for (int j = 0; j <= s2; ++j) {
                coefsProduct[i + j] += coefs1[i] * coefs2[j];
            }
        }
        return coefsProduct;
    }
}