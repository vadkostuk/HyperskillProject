package processor;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static Random random = new Random();
    public static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        while (true) {
        System.out.println("1. Add matrices \n2. Multiply matrix to a constant \n3. Multiply matrices \n4. Transpose matrix\n5. Calculate a determinant\n6. Inverse matrix\n0. Exit");
        System.out.println("Your choice: ");
        int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    addMatrix(createMatrix(), createMatrix());
                    break;
                case 2:
                    multiplicationMatrixByConstant(createMatrix());
                    break;
                case 3:
                    printMatrix(multiplyMatrixByMatrix(createMatrix(), createMatrix()));
                    break;
                case 4:
                    System.out.println("1. Main diagonal\n2. Side diagonal\n3. Vertical line\n4. Horizontal line\n Your choice: ");
                    int type = scanner.nextInt();
                    printMatrix(transposeMatrix(createMatrix(),type));
                    break;
                case 5:
                    System.out.println("The result is:\n"+determinantMatrix(createMatrix()));
                    break;
                case 6:
                        printMatrix(inversionMatrix(createMatrix()));
                        break;
                case 0:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Chose another one");
                    break;
            }

        }
    }
    public static double[][] createMatrix(){
        System.out.println("Enter size of matrix:");
        int n = scanner.nextInt();
        int m= scanner.nextInt();
        System.out.println("Enter matrix:");
        double[][] matrix = new double[n][m];
        for (int i = 0; i<n;i++){
            for (int j = 0; j < m; j++){
               matrix[i][j]=scanner.nextDouble();
              //matrix[i][j]=random.nextDouble();
            }
        }
        return matrix;
    }
    public static void  printMatrix(double[][] matrix){
        for (int i = 0; i<matrix.length;i++){
            for (int j = 0; j < matrix[0].length; j++){
                System.out.print(matrix[i][j]+" ");
            }
            System.out.println();
        }
    }
    public static void addMatrix(double[][] matrix1, double[][] matrix2){
        for (int i = 0; i<matrix1.length;i++){
            for (int j = 0; j < matrix1[0].length; j++){
                matrix1[i][j]+=matrix2[i][j];
                System.out.print(" "+matrix1[i][j]+" ");
            }
            System.out.println();
        }
    }
    public static void multiplicationMatrixByConstant(double[][] matrix){
        int constant = scanner.nextInt();
        for (int i = 0; i<matrix.length;i++){
            for (int j = 0; j < matrix[0].length; j++){
                matrix[i][j]*=constant;
                System.out.print(matrix[i][j]+" ");
            }
            System.out.println();
        }
    }
        public static double[][] multiplyMatrixByMatrix(double[][] firstMatrix, double[][] secondMatrix) {
            double[][] product = new double[firstMatrix.length][secondMatrix[0].length];
            for(int i = 0; i < firstMatrix.length; i++) {
                for (int j = 0; j < secondMatrix[0].length; j++) {
                    for (int k = 0; k < firstMatrix[0].length; k++) {
                        product[i][j] += firstMatrix[i][k] * secondMatrix[k][j];
                    }
                }
            }

            return product;
        }
        public static double[][] transposeMatrix(double[][] matrix,int typesOfTranspose){
            double transpose[][]=new double[matrix.length][matrix[0].length];
            switch (typesOfTranspose){
                case 1:
                    for(int i=0;i<matrix.length;i++){
                        for(int j=0;j<matrix[0].length;j++){
                            transpose[i][j]=matrix[j][i];
                        }
                    }
                    return transpose;
                case 2:
                    for (int i = 0; i < (matrix[0].length - 1); i++)
                        for (int j = 0; j < (matrix[0].length - 1) - i; j++)
                        {
                            double tmp = matrix[i][j];
                            matrix[i][j] = matrix[(matrix[0].length - 1) - j][(matrix[0].length- 1) - i];
                            matrix[(matrix[0].length - 1) - j][(matrix[0].length- 1) - i] = tmp;
                        }
                    return matrix;
                case 3:

                for(int j = 0; j < matrix.length; j++){
                    for(int i = 0; i < matrix[j].length / 2; i++) {
                        double temp = matrix[j][i];
                        matrix[j][i] = matrix[j][matrix[j].length - i - 1];
                        matrix[j][matrix[j].length - i - 1] = temp;
                    }
                }
                return matrix;
                case 4:
                    for(int col = 0;col < matrix[0].length; col++){
                        for(int row = 0; row < matrix.length/2; row++) {
                            double temp = matrix[row][col];
                            matrix[row][col] = matrix[matrix.length - row - 1][col];
                            matrix[matrix.length - row - 1][col] = temp;
                        }
                    }
                    return matrix;
            }
            return matrix;
        }

    static double determinantMatrix(double A[][]){
        int n = A.length;
        if(n == 1) return A[0][0];
        double determinant = 0;
        double B[][] = new double[n-1][n-1];
        int l = 1;
        for(int i = 0; i < n; ++i){

            int x = 0, y = 0;
            for(int j = 1; j < n; ++j){
                for(int k = 0; k < n; ++k){
                    if(i == k) continue;
                    B[x][y] = A[j][k];
                    ++y;
                    if(y == n - 1){
                        y = 0;
                        ++x;
                    }
                }
            }
            determinant += l * A[0][i] * determinantMatrix(B);
            l *= (-1);
        }
        return determinant;
    }

    public static double[][] inversionMatrix(double [][]A)
    {
        double temp;
        int N=A.length;

        double [][] E = new double [N][N];


        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
            {
                E[i][j] = 0f;

                if (i == j)
                    E[i][j] = 1f;
            }

        for (int k = 0; k < N; k++)
        {
            temp = A[k][k];

            for (int j = 0; j < N; j++)
            {
                A[k][j] /= temp;
                E[k][j] /= temp;
            }

            for (int i = k + 1; i < N; i++)
            {
                temp = A[i][k];

                for (int j = 0; j < N; j++)
                {
                    A[i][j] -= A[k][j] * temp;
                    E[i][j] -= E[k][j] * temp;
                }
            }
        }

        for (int k = N - 1; k > 0; k--)
        {
            for (int i = k - 1; i >= 0; i--)
            {
                temp = A[i][k];

                for (int j = 0; j < N; j++)
                {
                    A[i][j] -= A[k][j] * temp;
                    E[i][j] -= E[k][j] * temp;
                }
            }
        }

        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                A[i][j] = E[i][j];

            return A;
    }
    }
