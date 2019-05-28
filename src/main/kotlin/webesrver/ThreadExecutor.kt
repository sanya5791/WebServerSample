package webesrver

import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

private const val THREADS_COUNT = 5;
class ThreadExecutor {

    private val executor: ExecutorService by lazy { Executors.newFixedThreadPool(THREADS_COUNT) }

    fun execute(function: () -> Unit) {
        executor.execute(function)
    }

    fun shutdown() {
        executor.shutdown()
    }

}