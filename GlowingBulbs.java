/*
GLOWING BULBS
There is an infinite series of bulbs indexed from 1. And there are 40 switches indexed from 1 to 40. Switch with index x is connected to the bulbs with indexes that are multiple of x. i.e switch 2 is connected to bulb 4, 6, 8 ....
You can easily observe that some bulbs are connected to multiple switches and some are not connected to any switch.
Chotu is playing with these switches, he wants to know the Kth glowing bulb from the start if certain switches are in ON state. If any of the switch is ON, connected to any bulb then bulb starts glowing. But chotu has special fond of prime numbers so he only puts prime indexed switches ON.

INPUT-
First line contains number of test cases (T). Each test case contains two lines- First line contains a string S of length 40 containing 0s and 1s that represents the state of bulbs. 1 is ON , 0 is OFF. Second line contains one integer k. Atleast One switch is in ON condition.

OUTPUT-
Output one integer per test case representing kth glowing bulb.

CONSTRAINTS-
1 <= T <= 500
S contains only 0s and 1s. 1s are only at prime positions.
1 <= k <= 10^15
1 is not prime

SAMPLE INPUT 
3
0110000000000000000000000000000000000000
5
0010000000000000000000000000000000000000
5
0100000000100000001000100000101000001000
16807
SAMPLE OUTPUT 
8
15
26866

*/
import java.util.*;
class TestClass
{   
    static List<Integer> switches = new ArrayList<>(12);
    public static void main( String[] args )
    {
        Scanner s = new Scanner( System.in );
        int t = s.nextInt();
        long k, current, last, m = 0;
        int i,j;
        long kbulb = 0;
        while ( t>0 ) {
            t-=1;
            switches.clear();
            String switchString = s.next();
            k = s.nextLong();
            for(i =0 ; i< switchString.length(); i++){
                if(switchString.charAt( i ) == '1')
                    switches.add( i+1 );
            }
            current=1; last= 1000000000000000L;
            while ( current <= last ){
                m = (current+last) >> 1;
                int size = switches.size();   
                long res = 0;
                for(i=1; i< (1 << size); i++ ){
                    long pr = 1;
                    int sign = -1 ;
                    for(j =0; j < size; j++ ){
                        if( (i>>j) % 2== 1){
                            pr = pr * switches.get( j );
                            sign *= -1;
                        }
                    }
                    res += sign*(m/pr);
                }
                if(res < k){
                    current = m+1;
                }
                else{
                    kbulb = m;
                    last = m-1;
                }
            }
            System.out.println(kbulb);
        }
    }
}