import java.util.*;
import java.io.*;

public class Main {
    static class Edge implements Comparable<Edge> {
        int u, v, weight;

        public Edge(int u, int v, int weight) {
            this.u = u;
            this.v = v;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge other) {
            return Integer.compare(this.weight, other.weight);
        }
    }

    static int find(int[] parent, int x) {
        if (parent[x] != x) {
            parent[x] = find(parent, parent[x]); 
        }
        return parent[x];
    }

    static void union(int[] parent, int[] rank, int x, int y) {
        int rootX = find(parent, x);
        int rootY = find(parent, y);
        if (rootX != rootY) {
            if (rank[rootX] > rank[rootY]) {
                parent[rootY] = rootX;
            } else if (rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY;
            } else {
                parent[rootY] = rootX;
                rank[rootX]++;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder output = new StringBuilder();

        while (true) {
            String[] line = br.readLine().split(" ");
            int m = Integer.parseInt(line[0]);
            int n = Integer.parseInt(line[1]);

            if (m == 0 && n == 0) break;

            List<Edge> edges = new ArrayList<>();
            long totalWeight = 0;

            for (int i = 0; i < n; i++) {
                line = br.readLine().split(" ");
                int u = Integer.parseInt(line[0]);
                int v = Integer.parseInt(line[1]);
                int weight = Integer.parseInt(line[2]);
                edges.add(new Edge(u, v, weight));
                totalWeight += weight;
            }

            Collections.sort(edges);

            int[] parent = new int[m];
            int[] rank = new int[m];
            for (int i = 0; i < m; i++) {
                parent[i] = i;
                rank[i] = 0;
            }

            long mstWeight = 0;
            for (Edge edge : edges) {
                if (find(parent, edge.u) != find(parent, edge.v)) {
                    union(parent, rank, edge.u, edge.v);
                    mstWeight += edge.weight;
                }
            }

            output.append(totalWeight - mstWeight).append("\n");
        }

        System.out.print(output);
    }
}
