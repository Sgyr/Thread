package CurrentThread.package5;

import Demo.Return;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

public interface Computable<A,V> {
    V computer(A arg) throws InterruptedException, ExecutionException;

    public class ExpensiveFunction implements Computable<String, BigInteger> {
        @Override
        public BigInteger computer(String arg) {
//        在经过常时间的计算后
            return new BigInteger(arg);
        }
    }

    public class Memorizer1<A,V> implements Computable<A,V>{
        private final Map<A,V> cache = new HashMap<A,V>();



        private final Computable<A,V> c;

        public Memorizer1(Computable<A, V> c) {
            this.c = c;
        }

        @Override
        public synchronized V computer(A arg) throws InterruptedException, ExecutionException {
            V result =  cache.get(arg);
            if(result == null){
                result = c.computer(arg);
                cache.put(arg,result);
            }
            return result;
        }
    }

//    升级过后的用安全con
    public class Memoizer2<A,V> implements  Computable<A,V>{
        private final Map<A,V> cache = new ConcurrentHashMap<A,V>();
        private final Computable<A,V> c;

        public Memoizer2(Computable<A, V> c) {
            this.c = c;
        }

        @Override
        public V computer(A arg) throws InterruptedException, ExecutionException {
            V result = cache.get(arg);
            if(result == null){
                result = c.computer(arg);
                cache.put(arg,result);
            }
            return result;
        }
    }

//     再次升级(当比较表大的时候，会进入多次计算（针对的修改）)
        public class Memoizer3<A,V> implements Computable<A,V>{
            private final Map<A,Future<V>> cache = new ConcurrentHashMap<A, Future<V>>();
            private final Computable<A,V> c;

            public Memoizer3(Computable<A, V> c) {
                this.c = c;
            }

            @Override
            public V computer(final A arg) throws InterruptedException, ExecutionException {
                Future<V> f = cache.get(arg);
                if (f == null) {
                    Callable<V> eval = new Callable<V>() {
                        @Override
                        public V call() throws Exception {
                            return c.computer(arg);
                        }
                    };
                    FutureTask<V> ft = new FutureTask<V>(eval);
                    f = ft;
                    cache.put(arg, ft);
                    ft.run();
                }
                return f.get();
            }

        }

        //        再次升级，防止原子性的问题
        public class Memoizer4<A,V> implements Computable<A,V>{
            private Map<A,Future<V>> cache = new ConcurrentHashMap<>();
            private final Computable<A,V> c;

            public Memoizer4(Computable<A, V> c) {
                this.c = c;
            }

            @Override
            public V computer(final A arg) throws InterruptedException, ExecutionException {
                while(true){
                    Future<V> f = cache.get(arg);
                    if(f == null){
                        Callable<V> eval = new Callable<V>() {
                            @Override
                            public V call() throws Exception {
                                return c.computer(arg);
                            }
                        };
                        FutureTask<V> ft = new FutureTask<V>(eval);
                        //使用原子性的操作
                        f = cache.putIfAbsent(arg,ft);
                        if(f == null){
                            f= ft;
                            ft.run();
                        }
                    };

                }

            }
        }


//        考虑计算失败，计算取消的因素,把futuer中的缓存去除
}