package CurrentThread.package8;

import java.util.List;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;

/**
 * @Author:sgyt
 * @Description:
 * @Date:2019/3/25 12:49
 */
public class ConcurrentPuzzleSolver<P,M> {

    private final Puzzle<P,M> puzzle;

    private final ExecutorService executorService;

    private final ConcurrentMap<P,Boolean> seen;

    final ValueLatch<Node<P,M>> solution = new ValueLatch<>();

    public ConcurrentPuzzleSolver(Puzzle<P, M> puzzle, ExecutorService executorService, ConcurrentMap<P, Boolean> seen) {
        this.puzzle = puzzle;
        this.executorService = executorService;
        this.seen = seen;
    }

    public List<M> solve() throws InterruptedException {
        P p  = puzzle.initialPosition();
        executorService.execute(newTask(p,null,null));
        Node<P,M> solnNode = null;
        try {
            solnNode = solution.getValue();
            return (solnNode == null)?null :solnNode.asMoveList();
        }finally {
            executorService.shutdownNow();
        }
    }

    protected Runnable newTask(P p,M m,Node<P,M> n){
        return new SolverTask(p,m,n);
    }

    class SolverTask extends Node<P,M> implements Runnable{

        public SolverTask(P pos, M move, Node<P, M> prep) {
            super(pos, move, prep);
        }


        @Override
        public void run() {
            if(solution.isSet() || seen.putIfAbsent(pos,true) != null){
                return;
            }
            if(puzzle.isGoal(pos)){
                solution.setValue(this);
            }else{
                for(M m:puzzle.legalMoves(pos)){
                    executorService.execute(newTask(puzzle.move(pos,m),m,this));
                }
            }
        }
    }
}
