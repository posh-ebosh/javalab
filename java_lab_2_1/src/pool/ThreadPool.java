package pool;

import java.util.Deque;
import java.util.concurrent.ConcurrentLinkedDeque;

public class ThreadPool {
    private Deque<Runnable> tasks;

    // пул потоков
    private PoolWorker threads[];

    public ThreadPool(int threadsCount) {
        this.tasks = new ConcurrentLinkedDeque<>();
        this.threads = new PoolWorker[threadsCount];

        for (int i = 0; i < this.threads.length; i++) {
            this.threads[i] = new PoolWorker();
            this.threads[i].start();
        }
    }

    public void submit(Runnable task) {
        tasks.add(task);

    }

    // класс - рабочий поток
    private class PoolWorker extends Thread {
        @Override
        public void run() {
            while (true) {
                Runnable task;
                if (!tasks.isEmpty()) {
                    synchronized (tasks) {
                        task = tasks.pollFirst();

                    }
                    if (task != null) {
                        task.run();
                    }
                }


            }

        }

    }

}
