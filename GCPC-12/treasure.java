import java.util.*;

public class treasure {


    static final long INF = Long.MAX_VALUE/2;
    static ArrayList<ArrayList<Entry>> caves;
    static long[][] dist;
    static ArrayList<Integer> id;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        for (int i = 0; i < t; i++){
            int n = sc.nextInt();
            int m = sc.nextInt();
            caves = new ArrayList<>();
            for (int j = 0; j < n; j++){
                caves.add(new ArrayList<>());
            }
            for (int j = 0; j < m; j++){
                int a = sc.nextInt();
                int b = sc.nextInt();
                int cost = sc.nextInt();
                caves.get(a).add(new Entry(cost,b));
                caves.get(b).add(new Entry(cost,a));
            }

            int idols = sc.nextInt();
            dist = new long[idols + 1][];
            id = new ArrayList<>();
            id.add(0);
            dist[0] = dijkstra(n,0);
            for (int j = 1; j < idols + 1; j++){
                id.add(sc.nextInt());
                dist[j] = dijkstra(n,id.get(id.size()-1));
            }

            long air = sc.nextLong();
            long[][] result = new TSP(id.size()).solve();
            int maxi = 0;
            for (int j = 0; j < (1<<id.size()); j++)
            {
                long value= result[j][0];
                if (value <= air){
                    //System.err.println(value);
                    int cs = 0;
                    for (int k = 1; k < id.size(); k++){
                        if ((j & (1 << k)) > 0){
                            cs += 1;
                        }
                    }
                    //System.out.println("J = " + Integer.toBinaryString(j) );
                    maxi = Math.max(maxi, cs);
                }
            }
            System.out.println(maxi);
        }


    }

    /*
     * Graph contains vertices 0...N-1
     * 'start' and 'goal' are in [0, N-1]
     */
    static long[] dijkstra(int N, int start) {
        final long[] dist = new long[N];
        Arrays.fill(dist, INF);
        dist[start] = 0;

        int[] prev = new int[N];
        Queue<Entry> frontier = new PriorityQueue<Entry>(dist.length);
        frontier.offer(new Entry(0, start));

        while (!frontier.isEmpty()) {
            Entry e = frontier.poll();
            int u = e.index;


            // discard if a shorter distance is already known
            if (dist[u] < e.distance)
                continue;

            for (Entry vv : neighborsOf(u)) {
                int v = vv.index;
                long uv = vv.distance;
                if (dist[u] + uv < dist[v]) {
                    prev[v] = u;
                    dist[v] = dist[u] + uv;
                    frontier.offer(new Entry(dist[v], v));
                }
            }
        }
        return  dist;
    }

    private static ArrayList<Entry> neighborsOf(int u) {
        return  caves.get(u);
    }

    static class Entry implements Comparable {
        public int index;
        public long distance;

        public Entry(long d, int i) {
            distance = d;
            index = i;
        }

        public int compareTo(Object o) {
            Entry e = (Entry) o;
            if (distance != e.distance) {
                return (int) (distance - e.distance);
            }
            return index - e.index;
        }
    }

    static class TSP {
        int n;

        TSP(int n) {
            this.n = n;
        }

        /* return array B[i] of lengths of optimal tour
         * ending in node i, including any initial cost
         * to reach the starting point for optimal tour. */
        long[][] solve() {
            // precompute/cache dist to avoid calls in inner loop
            final long[][] D = new long[n][n];
            for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++) D[j][i] = dist(j, i);
            long[][] dp = new long[1 << n][n];
            for (long[] row : dp)
                Arrays.fill(row, INF);
            for (int i = 0; i < n; i++)
                dp[1 << i][i] = initialdist(i);
            for (int mask = 0; mask < 1 << n; mask++)
                for (int i = 0; i < n; i++)
                    if ((mask & 1 << i) > 0)
                        for (int j = 0; j < n; j++)
                            if (i != j && (mask & 1 << j) > 0)
                                dp[mask][i] = Math.min(dp[mask][i],
                                        dp[mask ^ (1 << i)][j] + D[j][i]);
            return dp;
        }

        private long initialdist(int i) {
            return treasure.dist[0][id.get(i)];
        }

        private long dist(int j, int i) {
            if (j == i) {
                return 0;
            }
            return treasure.dist[j][id.get(i)];
        }
    }



}
