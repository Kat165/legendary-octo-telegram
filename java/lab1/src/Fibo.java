import java.util.Scanner;

public class Fibo {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int n1=0,n2=1,n3;
        if (n<1 || n>45)
        {
            System.exit(0);
        }
        int[] tab = new int[n];
        tab[0] = 1;
        for (int i = 1; i<n; i++)
        {
            n3=n1+n2;
            tab[i] = n3;
            n1=n2;
            n2=n3;
        }

        for (int i = 0; i<n; i++)
        {
            System.out.println(" "+tab[i]);
        }

    }
}
