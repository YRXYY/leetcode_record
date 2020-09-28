package SwordToOffer;

import java.util.ArrayList;
import java.util.List;

/**
 * 明明是dfs，却应用失败，看的答案
 * 说白了还是要构造决策树，注意决策树的顺序
 * dfs模型就是
 *      先判断终止条件，之后就将其加入到答案中，再继续做决策
 *      特殊处理例外最后再考虑
 */
public class LexicalOrder {

    List<Integer> res = new ArrayList<>();;
    public List<Integer> lexicalOrder(int n) {
        dfs(n,0,0);
        return res;
    }
    public void dfs(int n,int num,int place){
        if(num>n)return;
        if(num>0)
            res.add(num);
        for(int i=place==0?1:0;i<=9;i++){
            dfs(n,num*10+i,place+1);
        }
    }

}
