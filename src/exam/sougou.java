package exam;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class sougou {
    public void sougou(){
        int[][] arr = new int[][]{{0,1},{0,2},{0,3},{1,4},{3,4},{2,6},{4,5},{5,6},{6,7},{7,8},{6,9},{9,10},{8,-1},{10,-1}};
        Interval[] conn = new Interval[arr.length] ;
        for(int i = 0; i < arr.length; i++){
            conn[i] = new Interval(arr[i][0],arr[i][1]);
            conn[i].start = arr[i][0];
            conn[i].end = arr[i][1];
        }
        trim(10,14,conn);
    }
    class Interval {
        int start;
        int end;

        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
    LinkedList<LinkedList<Integer>> allPaths = new LinkedList<>();
    LinkedList<Integer> path = new LinkedList<> ();
    List<List<Integer>> targets = new ArrayList<>();
    //targets.get(i)表示点i可以到的地方
    public Interval trim(int N, int M, Interval[] conn) {
        for (int i = 0; i <= N; i++) {
            targets.add(new ArrayList<>());
        }
        for(int i=0;i<M;i++){
            targets.get(conn[i].start).add(conn[i].end);
        }
        dfs(0);
        boolean[] nodes = new boolean[N+1];
        for(int i=0;i<allPaths.size();i++){
            for(int j=1;j<allPaths.get(i).size();j++){
                nodes[allPaths.get(i).get(j)] = true;
            }
        }
        int count = 0,sum=0;
        for(int i=1;i<N+1;i++){
            if(nodes[i]){
                count++;
                sum+=i;
            }
            if(sum>1000000){
                sum%=100000007;
            }
        }

        return new Interval(count,sum);
    }

    public void dfs(int node){
        if(node==-1){
            allPaths.add(new LinkedList<>(path));
            return;
        }
        path.add(node);
        for(int i=0;i<targets.get(node).size();i++){
            dfs(targets.get(node).get(i));
        }
        path.pollLast();
    }
}
