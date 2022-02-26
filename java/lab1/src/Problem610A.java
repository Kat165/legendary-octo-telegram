import java.util.Scanner;

public class Problem610A {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int m = n/2;
        int wynik;
        if (n<=5)
        {
            System.out.println("Nie da się tak podzielić tego patyka");
            System.exit(0);
        }
        if (n%2 != 0)
        {
            System.out.println("Nie da się tak podzielić tego patyka");
            System.exit(0);
        }
        if (m%2==0)
        {
            wynik = m/2 - 1;
        }
        else
        {
            wynik = m/2;
        }
        System.out.println(wynik);
    }
}
