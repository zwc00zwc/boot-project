/**
 * Created by alan.zheng on 2017/12/7.
 */
public class Sort {
    public static void main(String[] args){
        int[] array = new int[]{1,3,4,2,8,5,6,7};
        //bubblingSort(array);
        quickSort(array,0,array.length-1);
        //quickMiddleSort(array,0,array.length-1);
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

//    private static void quickSort(int[] array, int start, int end){
//        int key = 0;
//        int s = start;
//        int e = end;
//        if (s<=e){
//            key = array[start];
//            while (s<e){
//                while (array[e] > key && s < e){
//                    e--;
//                }
//                array[s] = array[e];
//                while (array[s] < key && s < e){
//                    s++;
//                }
//                array[e]=array[s];
//            }
//            //s必然和e相等
//            array[s] = key;
//            quickSort(array,start,s-1);
//            quickSort(array,s+1,end);
//        }
//    }

    public static void quickSort(int arr[],int _left,int _right){
        int left = _left;
        int right = _right;
        int temp = 0;
        if(left <= right){   //待排序的元素至少有两个的情况
            temp = arr[left];  //待排序的第一个元素作为基准元素
            //从左右两边交替扫描，直到left = right
            while(left != right){
                //从右往左扫描，找到第一个比基准元素小的元素
                while(right > left && arr[right] >= temp){
                    right --;
                }
                arr[left] = arr[right];  //找到这种元素arr[right]后与arr[left]交换
                //从左往右扫描，找到第一个比基准元素大的元素
                while(left < right && arr[left] <= temp){
                    left ++;
                }
                arr[right] = arr[left];  //找到这种元素arr[left]后，与arr[right]交换
            }
            arr[right] = temp;    //基准元素归位
            quickSort(arr,_left,left-1);  //对基准元素左边的元素进行递归排序
            quickSort(arr, right+1,_right);  //对基准元素右边的进行递归排序
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

    public static void q(int[] array, int start, int end){
        //基准
        int key = 0;
        int s = start;
        int e = end;
        int temp;
        //移动到中间的时候
        if (s<e){
            key = array[start];
            while (array[s]<key){
                s++;
            }
            array[s] = array[e];
            while (array[e]>key){
                e--;
            }
            if (s<=e){
                temp = array[s];
                array[s] = array[e];
                array[e] = temp;
            }
        }
    }
}
