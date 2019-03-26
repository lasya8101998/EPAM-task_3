/*
You have been given an array A of size N and an integer K. This array consists of N integers ranging from 1 to . Each element in this array is said to have a Special Weight. The special weight of an element  is .

You now need to sort this array in Non-Increasing order of the weight of each element, i.e the element with the highest weight should appear first, then the element with the second highest weight and so on. In case two elements have the same weight, the one with the lower value should appear in the output first.

Input Format:

The first line consists of two space separated integers N and K. The next line consists of N space separated integers denoting the elements of array A.

Output Format:

Print N space separated integers denoting the elements of the array in the order in which they are required.

Constraints:




Note:

You need to print the value of each element and not their weight.

SAMPLE INPUT 
5 2
1 2 3 4 5
SAMPLE OUTPUT 
1 3 5 2 4
*/

import java.util.*;
class TestClass{
	public static void main(String args[]) throws Exception {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int k = sc.nextInt();
		int array[] = new int[n];
		for (int i = 0; i < n; i++)
			array[i] = sc.nextInt();
		int result[] = new int[n];
		bucketsort(array, n, k, result);
		for (int i = 0; i < n; i++)
			System.out.print(result[i]+" ");
		sc.close();
	}
	static void bucketsort(int array[], int n, int k, int result[]) {
		ArrayList<ArrayList<Integer>> weight = new ArrayList(k);
		for (int i = 0; i < k; i++)
			weight.add(new ArrayList<Integer>());
		for (int i = 0; i < n; i++)
			weight.get(array[i]%k).add(array[i]);
		int ind = 0;
		for (int j = k - 1; j >= 0; j--) {
			Collections.sort(weight.get(j));
              for (int i = 0; i < weight.get(j).size(); i++)
				result[ind++] = weight.get(j).get(i);
		}
	}
}