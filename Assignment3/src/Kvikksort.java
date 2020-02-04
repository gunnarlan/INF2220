import java.util.Arrays;
public class Kvikksort{
    double kvikkSort(int[] a){
        long tt = System.nanoTime();
        Arrays.sort(a);
        double tid = (System.nanoTime() -tt)/1000000.0;
        testSort(a);
        return tid;
    }
    void testSort(int [] a){
      for (int i = 0; i< a.length-1;i++) {
        if (a[i] > a[i+1]){
          System.out.println("SorteringsFEIL på: "+
          i +" a["+i+"]:"+a[i]+" > a["+(i+1)+"]:"+a[i+1]);
          return;
        } }
    }

}
