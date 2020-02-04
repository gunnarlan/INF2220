import java.util.Random;
import java.util.Arrays;

public class Main{

  public static void main(String[] args){
    int gjentagelser=5;
    int totaltAntallN=6;
    int n=0;
    double [] tidRadix = new double[totaltAntallN];
    double [] tidKvikkSort = new double[totaltAntallN];
    double [] speedUp = new double[totaltAntallN];
    double [] medianTider = new double[gjentagelser];
    double gjennomsnittSpeedUp;
    Random tilfeldig = new Random();
    VRadix radix = new VRadix();
    Kvikksort kvikksort = new Kvikksort();
    int [] nVerdier= new int[totaltAntallN];
    for(int i=0;i<nVerdier.length;i++){
      nVerdier[i]=(int) Math.pow(10,i+2);
    }
    for(int i=0;i<totaltAntallN;i++){
        n = nVerdier[i];
        for(int g=0;g<gjentagelser;g++){
            int [] a = new int[n];
            for(int j=0;j<n; j++){
                a[j]=tilfeldig.nextInt(n);
            }
            medianTider[g]=radix.VRadixMulti(a);
        }
        Arrays.sort(medianTider);
        tidRadix[i]=medianTider[(gjentagelser+1)/2];

        for(int g=0;g<gjentagelser;g++){
            int [] a = new int[n];
            for(int j=0;j<n; j++){
                a[j]=tilfeldig.nextInt(n);
            }
            medianTider[g]=kvikksort.kvikkSort(a);
        }
        Arrays.sort(medianTider);
        tidKvikkSort[i]=medianTider[(gjentagelser+1)/2];
        speedUp[i]=tidKvikkSort[i]/tidRadix[i];
    }
    gjennomsnittSpeedUp=mean(speedUp);
    System.out.println("N         | Radix (ms)     | QuickSort (ms) | SpeedUp");
    for(int k=0;k<totaltAntallN;k++){
        System.out.format("%-9d | %-14f | %-14f | %-10f\n",nVerdier[k],tidRadix[k],tidKvikkSort[k],speedUp[k]);
    }
    System.out.println("");
    System.out.println("Average SpeedUp: "+Double.toString(gjennomsnittSpeedUp));
  }

  public static double mean(double[] a){
      double sum=0;
      for(int i=0;i<a.length;i++){
          sum+=a[i];
      }
      return sum/a.length;
  }
}
