import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {
            int n = scanner.nextInt();
            if (n == 0) break;

            int[] array = new int[n];

            for (int i = 0; i < n; i++) {
                array[i] = scanner.nextInt();
            }

            int flips = countFlips(array);
            System.out.println("Minimum exchange operations : " + flips);
        }
        scanner.close();
    }

    private static int countFlips(int[] array) {
        int count = 0;
        int n = array.length;

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    count++;
                }
            }
        }
        return count;
    }
}
