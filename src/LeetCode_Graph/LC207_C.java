package LeetCode_Graph;
/*
本题是一道经典的「拓扑排序」问题。

给定一个包含 n 个节点的有向图 G，我们给出它的节点编号的一种排列，如果满足：

    对于图 G 中的任意一条有向边(u,v)，u 在排列中都出现在 v 的前面。
    那么称该排列是图 G 的「拓扑排序」。

根据上述的定义，我们可以得出两个结论：

    如果图 G 中存在环（即图 G 不是「有向无环图DAG」），那么图 G 不存在拓扑排序。
        这是因为假设图中存在环 x1, x2, ..., xn, x1
        x1 在排列中必须出现在 xn 的前面，
        但 xn 同时也必须出现在 x1 的前面，
        因此不存在一个满足要求的排列，也就不存在拓扑排序；

    如果图 G 是有向无环图，那么它的拓扑排序可能不止一种。
        举一个最极端的例子，如果图 G 值包含 n 个节点却没有任何边，那么任意一种编号的排列都可以作为拓扑排序。

*/
/*
由于求出一种拓扑排序方法的最优时间复杂度为 O(n+m)，
其中 n 和 m 分别是有向图 G 的节点数和边数。而判断图 G 是否存在拓扑排序，至少也要对其进行一次完整的遍历，时间复杂度也为 O(n+m)。
因此不可能存在一种仅判断图是否存在拓扑排序的方法，它的时间复杂度在渐进意义上严格优于 O(n+m)。
 */


import java.util.ArrayList;
import java.util.List;

public class LC207_C {
    List<List<Integer>> edges;
    int[] visited;
    boolean valid = true;

    public boolean canFinishC(int numCourses, int[][] prerequisites) {
        edges = new ArrayList<>();
        for (int i = 0; i < numCourses; ++i) {
            edges.add(new ArrayList<>());
        }
        visited = new int[numCourses];
        for (int[] info : prerequisites) {
            edges.get(info[1]).add(info[0]);
        }
        for (int i = 0; i < numCourses && valid; ++i) {
            if (visited[i] == 0) {
                dfs(i);
            }
        }
        return valid;
    }
    void dfs(int u){
        visited[u] = 1;
        for(int v : edges.get(u)){
            if (visited[v] == 1) {
                valid = false;
                return ;
            }
            else if(visited[v] == 0) {
                dfs(v);
                if (!valid) {
                    return;
                }
            }
        }
        visited[u] = 2;
    }
    public static void main(String[] args){
        int[][] pr= new int[][]{{0,1},{3,1},{2,0},{3,0},{2,1}};
        int[][] pr2= new int[][]{{0,1},{1,0}};
        LC207_C cc = new LC207_C();
        System.out.println(cc.canFinishC(5,pr));
    }
}
