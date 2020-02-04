import java.util.Arrays;
public class VRadix{
  final static int NUM_BIT =8;
  final static int MIN_NUM = 47;
  int maksBit=0;
  int maksDybde=0;
  double VRadixMulti(int [] a) {
    long tt = System.nanoTime();
    int [] b = new int [a.length];
    int max = 0;
    for(int i=0;i<a.length;i++){
      if(a[i]>max){
        max=a[i];
      }
    }
    maksBit=32-Integer.numberOfLeadingZeros(max); //Fungerer selvom tallet er MAX_INT
    if(maksBit%NUM_BIT==0){
        maksDybde=maksBit/NUM_BIT;
    }
    else{
        maksDybde=(maksBit/NUM_BIT)+1; //Ett ekstra kall med en annen lengde på siffer
    }
    if(maksBit<NUM_BIT){
        VenstreRadix(a,b,0,a.length-1,0,NUM_BIT,0);
    }
    else{
        VenstreRadix(a,b,0,a.length-1,maksBit-NUM_BIT,NUM_BIT,0);
    }
    double tid = (System.nanoTime() -tt)/1000000.0;
    testSort(a);
  return tid;
  }

  void VenstreRadix ( int [] a, int [] b, int left, int right, int leftSortBit, int maskLen, int rekursjonsDybde){

    int mask = (1<<maskLen) -1;
    int [] count = new int [mask+1];
    int kumulativ=0;
    rekursjonsDybde++;

  for(int i=left; i<=right; i++){
    count[(a[i]>>leftSortBit)&mask]++;
  }
  int j=0;
  for(int i =0;i<=mask;i++){
    j=count[i];
    count[i]=kumulativ;
    kumulativ=kumulativ+j;
  }

  for(int i=left;i<=right;i++){
    b[left+count[(a[i]>>leftSortBit) & mask]++] = a[i];
  }

  if(rekursjonsDybde==maksDybde){
    if (rekursjonsDybde % 2==1){ //Kopier tilbake dersom det trengs
      for(int i=left;i<=right;i++)
        a[i]=b[i];
    }
  }
  else{
    if(count[0]==1){ //Tar count[0] seperat, pga. for-loopen lenger nede
      if (rekursjonsDybde % 2==1){
        a[left]=b[left];
      }
  }
    else if(count[0]<MIN_NUM){
      innstikkSorter(b,a,left,left+count[0]-1,rekursjonsDybde);
    }

    else if(count[0]>1){
        if(rekursjonsDybde==maksDybde-1){
            VenstreRadix(b,a,left, left+count[0]-1,0, maskLen,rekursjonsDybde); //Siste kall - 0 leftSortBit
        }
        else{
            VenstreRadix(b,a,left, left+count[0]-1,maksBit-(rekursjonsDybde+1)*NUM_BIT, maskLen,rekursjonsDybde);
        }
    }
    for(int i=1;i<count.length;i++){
        if(count[i]-count[i-1]==1){
          if (rekursjonsDybde % 2==1){
              a[left+count[i-1]]=b[left+count[i-1]];
          }
        }
        else if((count[i]-count[i-1])<MIN_NUM){
          innstikkSorter(b,a,left+count[i-1],left+count[i]-1,rekursjonsDybde);
        }
        else if(count[i]-count[i-1]>1){
          if(rekursjonsDybde==maksDybde-1){
              VenstreRadix(b,a, left+count[i-1],left+count[i]-1, 0,maskLen,rekursjonsDybde);
          }
          else{
          VenstreRadix(b,a, left+count[i-1],left+count[i]-1, maksBit-(rekursjonsDybde+1)*NUM_BIT,maskLen,rekursjonsDybde);
          }
        }
    }
  }

  }

  void innstikkSorter(int[] a,int[] b,int left, int right, int rekursjonsDybde){
    int [] sortertArray=new int[right-left+1];
    rekursjonsDybde++;
    for(int i=left; i<=right;i++){
      sortertArray[i-left]=a[i];
    }
    Arrays.sort(sortertArray);
    if (rekursjonsDybde % 2==1){
      for(int i=left;i<=right;i++){
        a[i]=sortertArray[i-left];
      }
    }
    else{
      for(int i=left;i<=right;i++){
        b[i]=sortertArray[i-left];
      }
    }

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
