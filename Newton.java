import java.io.*;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Scanner;

public class Newton extends PolynomProduct{
    static float proterm(int i, float value, float x[]) {
        float pro = 1;
        for (int j = 0; j < i; j++) {
            pro = pro * (value - x[j]);
        }
        return pro;
    }

    static void dividedDiffTable(float x[], float y[][], int n) {
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < n - i; j++) {
                y[j][i] = (y[j][i - 1] - y[j + 1]
                        [i - 1]) / (x[j] - x[i + j]);
            }
        }
    }

    static float applyFormula(float value, float x[], float y[][], int n) {
        float sum = y[0][0];
        for (int i = 1; i < n; i++) {
            sum = sum + (proterm(i, value, x) * y[0][i]);
        }
        return sum;
    }
//metoda do wypisania tablicy trójkątnej
    static void printDiffTable(float y[][], int n) {
        DecimalFormat df = new DecimalFormat("#.####");
        df.setRoundingMode(RoundingMode.HALF_UP);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - i; j++) {
                String str1 = df.format(y[i][j]);

                System.out.print(str1 + "\t ");
            }
            System.out.println("");
        }
    }

    public static void main(String[] args) throws IOException {
        File file = new File("MO - Interpolacja - dane kontrolne - 3.txt");
        Scanner sc = new Scanner(file);

        BufferedReader buffer = new BufferedReader(
                new InputStreamReader(System.in));
        System.out.print("Podaj wartość x: ");
        float value = Float.parseFloat(buffer.readLine());

        int n = 6;
        int tmp = 0;
        int tmp2 = 0;
        float x[] = new float[n];
        float y[][] = new float[n][n];

        while (sc.hasNextFloat()) {
            if (n > tmp) {
                x[tmp] = sc.nextFloat();
                tmp++;
            }
            else
                break;
        }

        while (sc.hasNextFloat()) {
            if (n > tmp2) {
                y[tmp2][0] = sc.nextFloat();
                tmp2++;
            }
        }
        sc.close();

        dividedDiffTable(x, y, n);
        //printDiffTable(y, n);

        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.HALF_UP);

        System.out.println("Wartość w " + df.format(value) + " wynosi " +
                df.format(applyFormula(value, x, y, n)));

        PolynomProduct polynomProduct = new PolynomProduct();
        String result2 = polynomProduct.printerWynik(x,y[0]);

        PrintWriter zapis = new PrintWriter("Newton Wynik.txt");
        zapis.println("Wartość w " + df.format(value) + " wynosi " + df.format(applyFormula(value, x, y, n)));
        zapis.println(result2);
        zapis.close();
    }
}