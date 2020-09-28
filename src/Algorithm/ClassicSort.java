package Algorithm;

public class ClassicSort {
}

/**
 * 快排，适合先比较混乱的数组，所以排序前先打乱了就是防止输入的数组有序而导致效率低下
 * 最坏情况：数组有序，N方
 * 最好情况：每次选的数字恰好在中间，NlogN
 * 平均情况NlogN
 */
class QuickSort {

    public static void quickSort(int[] arr,int low,int high){
        if(low<high){
            int j = partition(arr, low, high);
            quickSort(arr,low,j);
            quickSort(arr,j+1,high);
        }
    }
    public static int partition(int[] arr, int low, int high) {
        int left=low,right=high+1;
        int v = arr[low];
        while(true){
            while(arr[++left]<v) if(left==high)break;
            while(arr[--right]>v) if(right == low)break;
            if(left>right)break;
            swap(arr,left,right);
        }
        swap(arr,low,right);
        return right;
    }
    public static void swap(int[] arr,int x,int y){
        int temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
    }
}

//二分
class BinarySearch {

    public static int binarySearch(int[] arr,int target){
        int left = 0;
        int right = arr.length-1;
        int mid;
        while(left<=right){
            mid = (left + right)/2;
            if(arr[mid]>target){
                right = mid-1;
            }else if(arr[mid]<target){
                left = mid + 1;
            }else{
                return mid;
            }
        }
        //未找到
        return -1;
    }

}

/**
 * 归并排序
 * NlogN 稳定的
 */
class MergeSort{
    public static void mergeSort(int[] arr,int[] temp,int left,int right){
        //当left==right，排序终止
        if(left<right){
            //[分]
            int center = (left+right)/2;
            //[治]
            mergeSort(arr,temp,left,center);
            //[治]
            mergeSort(arr,temp,center+1,right);
            //[合]
            merge(arr,temp,left,center,right);
        }
    }
    public static void merge(int[] arr,int[] temp,int left,int center,int right){
        int i=left,j=center+1;
        //先存储到辅助数组
        for(int k=left;k<=right;k++){
            if(i>center)            temp[k] = arr[j++];
            else if(j>right)        temp[k] = arr[i++];
            else if(arr[i]<arr[j])  temp[k] = arr[i++];
            else                    temp[k] = arr[j++];
        }
        //将辅助数组中的数据复制到arr中
        for(int k=left;k<=right;k++){
            arr[k] = temp[k];
        }
    }
}