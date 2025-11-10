package br.mack.labirinto.util;

public class Sorts {
    public interface Comparator<T,K> {
        int compare(T obj,K key);
    }
    public static <T> void insertionSort(T[]arr,int n , Comparator<T,T> comp){
        for (int i = 1; i < n; i++) {
            T key = arr[i];
            int j = i - 1;
            while (j >= 0 && comp.compare(arr[j],key) > 0){
                arr[j+1] = arr[j];
                j--;
            }
        }
    }
    public static <T> void quickSort(T[]arr,int low,int high,Comparator<T,T> comp) {
        if (low < high) {
            int p = partition (arr,low,high,comp);
            quickSort(arr,low,p-1,comp);
            quickSort(arr,p+1,high,comp);
        }
    }
    public static <T,K> int binarySearchByName(T[] arr,int left,int right,K key,Comparator<T,K> cc) {
        while(left <= right){
            int mid = (left + right / 2 );
            int cmp = cc.compare(arr[mid],key);
            if(cmp == 0){
                return mid;
            }else if(cmp < 0){
                left = mid + 1;

            }else{
                right = mid - 1;
            }
        }
        return -1;
    }

    public static <T> int partition(T[] arr,int low,int high,Comparator<T,T> comp) {
        T pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if(comp.compare(arr[j],pivot) <= 0){
                i++;
                T tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
            }
        }
        T tmp = arr[i+1];
        arr[i+1] = arr[high];
        arr[high] = tmp;
        return i+1;

    }
}
