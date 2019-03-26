package CurrentThread.package8;

import java.util.LinkedList;
import java.util.List;

/**
 * @Author:sgyt
 * @Description:
 * @Date:2019/3/25 10:08
 */
public class Node<P,M> {
    final P pos;

    final M move;

    final Node<P,M> prep;

    public Node(P pos, M move, Node<P, M> prep) {
        this.pos = pos;
        this.move = move;
        this.prep = prep;
    }

    List<M> asMoveList(){
        List<M> solution = new LinkedList<M>();

        for(Node<P,M> n = this; n.move != null;n=n.prep){
            solution.add(0,n.move);
        }

        return solution;
    }
}
