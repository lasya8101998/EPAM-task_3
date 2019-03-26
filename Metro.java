/*
A city subway line has become huge and it is hard to take the shortest path through them. You have to find the shortest path in subway lines. In the second , you are in the station  and you want to go to the station .
The city has  stations. The subway has  lines. Each subway line goes to some stations.
The  subway goes to stations (in order) and this train takes  seconds to travel from  to (for  and ).
Trains are ready for the passengers to get in, but the last train goes in the second (and you are allowed to board it in between the path).

Input 
The first line contains (in order).
Next  lines describe subway lines.
The first line contains  and next line contains and the next line contains .
The last line containts .
It is guaranteed  and no subway line intersects itself.

Output 
Print the shortest path in subway lines from the station  to .
If there is no way from  to , print -1. 

SAMPLE INPUT 
4 2
2 0
1 2
1
3 0
3 2 4
1 1
1 4
SAMPLE OUTPUT 
2
Explanation
In second  get on first subway line and in next second we arrive to station  and we can get on second line and arrive to  in second 
*/

    import java.util.Arrays;
    import java.util.PriorityQueue;
    import java.util.ArrayList;
    import java.util.AbstractCollection;
    import java.util.Comparator;
    import java.util.Scanner;
    class TestClass {
	static Scanner in=new Scanner(System.in);
        public static void main(String[] args)  {
            Metro solver = new Metro();
            solver.solve();
        }
        static class Metro {
            public void solve() {
                int n = in.nextInt();
                int m = in.nextInt();
                ArrayList<pair> arrayList[] = new ArrayList[n + 1];
                for (int i = 0; i <= n; i++) arrayList[i] = new ArrayList();
                for (int i = 0; i < m; i++) {
                    int s = in.nextInt();
                    long t = in.nextLong();
                    int arr[] = new int[s];
                    for (int j = 0; j < s; j++) arr[j] = in.nextInt();
                    for (int j = 0; j < s - 1; j++) {
                        int we = in.nextInt();
                        arrayList[arr[j]].add(new pair(arr[j + 1], we,t));
                        t += we;
                    }
                }
                int st = in.nextInt();
                int end = in.nextInt();
                long dis[] = new long[n + 1];
                Arrays.fill(dis, Long.MAX_VALUE / 2);
                dis[st] = 0;
                PriorityQueue<pair2> pq = new PriorityQueue<>(new Comparator<pair2>() {
                    public int compare(pair2 o1, pair2 o2) {
                        return Long.compare(o1.dis, o2.dis);
                    }
                });
                pq.add(new pair2(st, dis[st]));
                boolean visited[] = new boolean[n + 1];
                visited[st] = true;
                while (!pq.isEmpty()) {
                    pair2 p = pq.poll();
                    if (p.node == end) {
                        System.out.println(dis[p.node]);
                        return;
                    }
                    for (pair pp : arrayList[p.node]) {
                        if (dis[p.node] <= pp.t && dis[pp.u] > dis[p.node] + pp.w) {
                            dis[pp.u] = dis[p.node] + pp.w;
                            pq.add(new pair2(pp.u, dis[pp.u]));
                        }
                    }
                }
                System.out.println(-1);
            }
            class pair2 {
                int node;
                long dis;
                public pair2(int node, long dis) {
                    this.node = node;
                    this.dis = dis;
                }
            }
            class pair {
                int u;
                long w;
                long t;
                public pair(int u, long w, long t) {
                    this.u = u;
                    this.w = w;
                    this.t = t;
                }
            }
        }
    }