/**
 * Created by alan.zheng on 2017/12/7.
 */
public class Sort {
    public static void main(String[] args){
        int[] array = new int[]{1,3,4,2,8,5,6,7};
        //bubblingSort(array);
        //quickSort(array,0,array.length-1);
        quickMiddleSort(array,0,array.length-1);
        for (int i = 0;i<array.length;i++){
            System.out.print(array[i]+";");
        }
    }

    private static void bubblingSort(int[] array){
        int temp;
        for (int i = 0;i < array.length - 1;i++){
            for (int m = 0;m<array.length - i - 1;m++){
                if (array[m]>=array[m+1]){
                    temp = array[m + 1];
                    array[m + 1] = array[m];
                    array[m] = temp;
                }
            }
        }
    }

    private static void quickSort(int[] array, int low, int high){
        int start = low;
        int end = high;
        int key = array[low];
        int temp;
        while (start < end && start<=high && end>=0){
            if (array[start]>key){
                temp = array[start];
                array[start] = key;
                array[low] = temp;
            }
            start++;

            if (array[end]<key){
                temp = array[end];
                array[end] = key;
                array[low] = temp;
            }
            end--;
        }
        if (start>=0&&start<=high){
            quickSort(array,low,start-1);
        }
        if (end>=0){
            quickSort(array,end+1,high);
        }
    }

    private static void quickMiddleSort(int[] numbers, int left, int right)
    {
        //左边索引小于右边，则还未排序完成 　　
        if (left < right)
        {
            //取中间的元素作为比较基准，小于他的往左边移，大于他的往右边移 　　
            int middle = numbers[(left + right) / 2];
            int i = left - 1;
            int j = right + 1;
            while (true)
            {
                while (numbers[++i] < middle && i < right) ;
                while (numbers[--j] > middle && j > 0) ;

                if (i >= j)
                    break;

                int number = numbers[i];
                numbers[i] = numbers[j];
                numbers[j] = number;

            }
            quickMiddleSort(numbers, left, i - 1);
            quickMiddleSort(numbers, j + 1, right);
        }
    }
}
