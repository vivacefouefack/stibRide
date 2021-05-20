package g54490.atl4.sortingRace.model;

/**
 *
 * @author g54490@etu.he2b.be
 */
public class Sort {
    
    private int nbOperation;

    public Sort() {
 
    }

    public int getNbOperation() {
        return nbOperation;
    }

    public void bubbleSort(int[] array) {
        this.nbOperation=0;
        int tmp = 0;
        int j = 0;
        while (j < array.length) {
            for (int i = 0; i < array.length - 1; i++) {
                nbOperation++;
                if (array[i] > array[i + 1]) {
                    tmp = array[i + 1];
                    nbOperation++;
                    array[i + 1] = array[i];
                    nbOperation++;
                    array[i] = tmp;
                    nbOperation++;
                }
            }
            nbOperation++;
            j++;
        }
        
      
    }

    private void merge(int[] a, int[] l, int[] r, int left, int right) {//tri fusion

        int i = 0;
        int j = 0;
        int k = 0;
        while (i < left && j < right) {
            nbOperation++;
            if (l[i] <= r[j]) {
                a[k++] = l[i++];
                nbOperation++;
            } else {
                a[k++] = r[j++];
                nbOperation++;
            }
        }
        while (i < left) {
            a[k++] = l[i++];
            nbOperation++;
        }
        while (j < right) {
            a[k++] = r[j++];
            nbOperation++;
        }
    }

    public int mergeSort(int[] a, int n) {
        if (n < 2) {
            return nbOperation ;
        }
        int mid = n / 2;
        int[] l = new int[mid];
        int[] r = new int[n - mid];

        for (int i = 0; i < mid; i++) {
            l[i] = a[i];
            nbOperation++;
        }
        for (int i = mid; i < n; i++) {
            r[i - mid] = a[i];
            nbOperation++;
        }
        mergeSort(l, mid);
        
        mergeSort(r, n - mid);

        merge(a, l, r, mid, n - mid);
        return nbOperation;
    }
    
    public int[] generateArray(int size){
        int[] array;
        if(size==0){
            array=new int[0];
        }else{
           array=new int[size]; 
        }
        
        for(int i=0; i<size ;i++){
            array[i]= (int)(Math.random()*99)+1;
        }
        return array;
    }

    public static void main(String[] args) {
        int[] tap = {12, 23, 10, 7, 61, 97, 23, 2, 14, 4};
        
        int[] actual = { 5, 1, 6, 2, 3, 4 };
        int[] expected = { 1, 2, 3, 4, 5, 6 };
        
        
        Sort sort = new Sort();
        sort.mergeSort(actual, actual.length);
        //int[] array=sort.generateArray(1000000000);
        //System.out.println(sort.bubbleSort(array));
        System.out.println();
        for (int i = 0; i <actual.length; i++) {
            System.out.println(actual[i]);
            //System.out.println((int)(Math.random()*7)+1);
        }

    }
}
