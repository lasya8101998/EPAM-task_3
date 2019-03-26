/*
Data science is very popular now. A lot of universities have courses about it. A lot of companies have open data science positions. So it's quite important to know how to work with statistics. And this task can help you to make first step into statistics.

Lets define S as all strings of the length n consisting of letters from the some alphabet of the size a. For each string s lets define  - maximum among all z-function values of the string s. Your task is calculate modulo . 

You can read more about z-function on https://e-maxx-eng.appspot.com/string/z-function.html. Also in this problem we define .

Input format

First line of input contains three space separated integers n, a and  (, , ).

Note that  may be composite.

Output format

Print the single integer  modulo .

SAMPLE INPUT 
3 2 1000
SAMPLE OUTPUT 
8
Explanation

*/
import java.io.*;
import java.util.*;
 
class TestClass {
	static int power(int a, int b, int mod) {
		if (b == 0)
			return 1;
		int p = power(a, b / 2, mod);
		p = (int) ((long) p * p % mod);
		if (b % 2 == 1)
			p = (int) ((long) p * a % mod);
		return p;
	}
	static int[] dsu;
	static int find(int i) {
		return dsu[i] < 0 ? i : (dsu[i] = find(dsu[i]));
	}
	static boolean join(int i, int j) {
		i = find(i);
		j = find(j);
		if (i == j)
			return false;
		if (dsu[i] > dsu[j])
			dsu[i] = j;
		else {
			if (dsu[i] == dsu[j])
				dsu[i]--;
			dsu[j] = i;
		}
		return true;
	}
	static int solve_(int k, int v, int n, int a, int mod) {
		Arrays.fill(dsu, -1);
		int bcnt = 0, cnt = n;
		for (int i = 1; i < n; i++)
			if ((v & 1 << i) > 0) {
				for (int j = i; j < i + k; j++)
					if (join(j, j - i))
						cnt--;
				bcnt++;
			}
		int sum = power(a, cnt, mod);
		return bcnt % 2 == 1 ? sum : (mod - sum) % mod;
	}
	static int solve(int n, int a, int mod) {
		dsu = new int[n];
		int sum = 0;
		for (int k = 1; k < n; k++)
			for (int v = 2; v < 1 << n - k + 1; v += 2)
				sum = (sum + solve_(k, v, n, a, mod)) % mod;
		return sum;
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int a = Integer.parseInt(st.nextToken());
		int mod = Integer.parseInt(st.nextToken());
		System.out.println(solve(n, a, mod));
	}
}