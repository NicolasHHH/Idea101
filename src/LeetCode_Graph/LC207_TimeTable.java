package LeetCode_Graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

/*
你这个学期必须选修 numCourses 门课程，记为0到numCourses - 1 。
在选修某些课程之前需要一些先修课程。 先修课程按数组prerequisites 给出，
其中prerequisites[i] = [ai, bi] ，表示如果要学习课程ai 则 必须先学习课程 bi 。

例如，先修课程对[0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1 。
请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false 。

*/

// Directed Acyclic Graph DAG
public class LC207_TimeTable {
    boolean cycle = false;
    HashMap<Integer, LinkedList<Integer>> adj = new HashMap<>();
    HashSet<Integer> visited;
    void addVertex(int u){
        this.adj.put(u,new LinkedList<>());
    }
    void addEdge(int u,int v){
        this.adj.get(u).add(v);
    }
    boolean hasEdge(Integer u, Integer v) {
        return this.adj.get(u).contains(v);
    }
    void removeEdge(Integer u, Integer v) {
        this.adj.get(u).remove(v);
    }
    LC207_TimeTable(int numCours, int[][] prerequis){
        for (int i = 0; i<numCours;i++){
            addVertex(i);
        }
        for (int[] arr: prerequis){
            addEdge(arr[1],arr[0]);
        }

    }
    boolean detectCycle(){
        for(int i : this.adj.keySet()){
            this.visited= new HashSet<>();
            //System.out.println("cas "+i);
            dfs(i,i);
            if (this.cycle) return false;
            //System.out.println("\n"+i+" est bon");
        }
        return true;
    }
    void dfs(int v,int target){
        //System.out.print(v+":");
        if (this.visited.contains(v)) return;
        this.visited.add(v);
        for (int w : this.adj.get(v)){
            //System.out.print("(w:"+w+"target:"+target+")");
            if (w == target) {
                cycle = true;
                break;
            }
            dfs(w,target);
        }
    }

    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        LC207_TimeTable tt = new LC207_TimeTable(numCourses,prerequisites);
        return tt.detectCycle();
    }


    public static void main(String[] args){
        int[][] pr= new int[][]{{0,1},{3,1},{2,0},{3,0},{2,1}};
        int[][] pr2= new int[][]{{0,1},{1,0}};
        System.out.println("\n\n"+canFinish(2,pr2));
    }
}
