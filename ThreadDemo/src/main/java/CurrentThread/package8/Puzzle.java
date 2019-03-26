package CurrentThread.package8;

import java.util.Set;

/**
 * @Author:sgyt
 * @Description:箱子
 * @Date:2019/3/25 10:04
 */
public interface Puzzle<P,M> {
    /**
     * 初始化
     * @return
     */
    P initialPosition();

    /**
     * 是否达到目标
     * @param position
     * @return
     */
    boolean isGoal(P position);

    Set<M> legalMoves(P position);

    /**
     * 移动的位置
     * @param position
     * @param move
     * @return
     */
    P move(P position, M move);
}
