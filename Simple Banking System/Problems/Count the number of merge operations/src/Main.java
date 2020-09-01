import java.util.*;

public class Main {
    public static void main(String[] args) {
      Scanner scanner = new Scanner(System.in);
      int n = scanner.nextInt();
      int[] mas = new int[n];
      for(int i = 0;i<n;i++){
          mas[i]=scanner.nextInt();
      }
        System.out.println(n-1);
    }
}