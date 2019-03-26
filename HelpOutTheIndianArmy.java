/*
HELP OUT THE INDIAN ARMY
Protection of the Indian border and safe transport of items from one point to another along the border are the paramount jobs for the Indian army. However they need some information about the protection status along the length of the border. The border can be viewed as the real x-axis. Along the axis, Indian army has N checkpoints for lookout. 
We know that each checkpoint is located at an integer location xi. Each checkpoint must have a fleet of armed men which are responsible for guarding the neighboring areas of the checkpoint and provide military assistance of all kinds. The size of the fleet is based on the location of the checkpoint and how active the region is for terrorist activities. 
Given the number of armed men assigned at the ith checkpoint, as pi, this information is available for all checkpoints. 
With the skills of the armed men, it is known that if for the ith checkpoint, the length on the x axis that they can defend is a closed interval [xi-pi, xi+pi].
Now, your task is to transport some military items from position S to the end position E on the x-axis. 

Input:
First line of the input contains 3 integers N, S and E. N is the number of checkpoints that the Indian Army has on the border.
Then N lines follow. ith line contains 2 integers, xi and pi.

Output:
Print the total distance of the x-axisfrom S to E, that is not protected by the armed forces.

Constraints:
1 = N = 105
1 = xi, pi = 1018
xi + pi = 1018
1 = S = E = 1018

SAMPLE INPUT 
5 229 8419
1795 95
4873 720
3149 81
6101 2325
3674 629
SAMPLE OUTPUT 
2626

*/
import java.util.*;
class TestClass{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int numCheckPoints =sc.nextInt();
		long startingPoint = sc.nextLong();
		long endingPoint = sc.nextLong();
		Fleet[] farr = new Fleet[numCheckPoints];
		for(int i=0;i<numCheckPoints;i++)
		{
			long x = sc.nextLong();
			long p = sc.nextLong();
			farr[i] = new Fleet(x-p,x+p);
		}
		Arrays.sort(farr);
		ArrayList<Fleet> b = new ArrayList<Fleet>();
		long c = farr[0].left;
		long d = farr[0].right;
		for(int i=1;i<numCheckPoints;i++)
		{
			if(farr[i].left<=d)
			{
				d = Math.max(d, farr[i].right);
			}
			else
			{
				b.add(new Fleet(c,d));
				c = farr[i].left;
				d = farr[i].right;
			}
		}
		b.add(new Fleet(c,d));
		int i;
		long ans = 0;
		for(i=0;i<b.size();i++)
		{
			if(startingPoint>=endingPoint)
			{
				startingPoint = endingPoint;
				break;
			}
			if(b.get(i).left<=startingPoint && startingPoint<=b.get(i).right)
				startingPoint = b.get(i).right;
			else if(startingPoint<=b.get(i).left && endingPoint>=b.get(i).right)
			{
				ans+=b.get(i).left-startingPoint;
				startingPoint = b.get(i).right;	
			}
			else if(startingPoint<=b.get(i).left && endingPoint>=b.get(i).left &&  endingPoint<=b.get(i).right)
			{
				ans+=b.get(i).left-startingPoint;
				startingPoint = endingPoint;
			}
			else if(startingPoint<=b.get(i).left && endingPoint<=b.get(i).left)
			{
				ans+=endingPoint-startingPoint;
				startingPoint = endingPoint;
			}
		}
		if(startingPoint<endingPoint)
			ans+=endingPoint-startingPoint;
		System.out.println(ans);
	}
}
 class Fleet implements Comparable<Fleet>{
    long left;
    long right;
    Fleet(long i,long s){
       left=i;
       right=s;
    }
    public int compareTo(Fleet o){
        if(left>o.left)
        return 1;
        else if(left==o.left)
        return 0;
        else
        return -1;
    }
}