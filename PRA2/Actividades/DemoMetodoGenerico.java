package lab02;

class DemoMetodoGenerico {
    static <T extends Comparable<T>> boolean igualArrays(T[] x, T[] y) {
        if (x.length != y.length) return false;
        for (int i = 0; i < x.length; i++) {
            if (!x[i].equals(y[i])) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Integer[] nums1 = {1, 2, 3, 4, 5};
        Integer[] nums2 = {1, 2, 3, 4, 5};
        Integer[] nums3 = {1, 2, 7, 4, 5};
        Integer[] nums4 = {1, 2, 7, 4, 5, 6};

        if (igualArrays(nums1, nums1)) System.out.println("nums1 es igual a nums1");
        if (igualArrays(nums1, nums2)) System.out.println("nums1 es igual a nums2");
        if (igualArrays(nums1, nums3)) System.out.println("nums1 es igual a nums3");
        if (igualArrays(nums1, nums4)) System.out.println("nums1 es igual a nums4");
        
        // Crea un array de double                                //A
        //Double dvals[] = {1.1, 2.2, 3.3, 4.4, 5.5};             //B
        //if(igualArrays(nums1, dvals))                           //C
        	//System.out.println("nums1 es igual a dvals");       //D
    }
}