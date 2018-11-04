package lesson_5_Multithreading;

public class Temp {
    public static void main(String[] args) {
        int size = 12;
        float[] arr = new float[size];
        byte count = 3;
        for(float f : arr){
            f = 1;
        }
        segmentationArray(arr, count);

    }

    public static void segmentationArray(float[] arr, int count){
        int startIndex = 0;
        int numberOfElements = arr.length / count;
        float[][] array = new float[count][];
        for (int i = 0; i <= count - 2; i++) {
            array[i] = new float[numberOfElements];
            System.arraycopy(arr, startIndex, array[i], 0, numberOfElements);
            startIndex += numberOfElements;
            System.out.println(i);
            System.out.println(startIndex);
            System.out.println(numberOfElements);
        }
        array[count-1] = new float[arr.length - startIndex];
        System.out.println("Hfpybwf" + (arr.length - startIndex));
        for (int i = 0; i < arr.length - startIndex - 1; i++) {
            array[count-1][i] = 1;
        }
        for (int i = 0; i < array.length ; i++) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
//        for (int i = 0; i < array[1].length ; i++) {
//            System.out.print(array[i] + " ");
//        }
//        array[count - 1] = new float[((arr.length - startIndex) + numberOfElements)];
//        System.arraycopy(arr, startIndex, array[count - 1], 0, ((arr.length - startIndex) + numberOfElements - 1));
//        printArr(array);
    }

//    static void printArr(float[][] arr){
//        for (int i = 0; i < arr.length ; i++) {
//            for (int j = 0; j < arr[i].length; j++) {
//                System.out.print(arr[i][j]);
//            }
//            System.out.println();
//        }
//    }

}
