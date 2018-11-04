package lesson_5_Multithreading;

public class Temp {
    public static void main(String[] args) {
        int size = 36;
        float[] arr = new float[size];
        byte count = 4;

//        segmentationArray(arr, count);
    }

//    public static void segmentationArray(float[] arr, int count){
//
//        int startIndex = 0;
//        int numberOfElementsInArray = arr.length / count;
//        int residueOfElements = arr.length % count;
//        float[][] array = new float[count][];
//
//        for (int i = 0; i < count - 1; i++) {
//            array[i] = new float[numberOfElementsInArray];
//            System.arraycopy(arr, startIndex, array[i], 0, numberOfElementsInArray);
//            startIndex += numberOfElementsInArray;
//        }
//        array[count-1] = new float[numberOfElementsInArray + residueOfElements];
//        System.arraycopy(arr, startIndex, array[count-1], 0, array[count-1].length);
//
//        for (int i = 0; i < array.length ; i++) {
//            for (int j = 0; j < array[i].length; j++) {
//                System.out.print(array[i][j] + " ");
//            }
//            System.out.println();
//        }
//    }
}
