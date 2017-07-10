package s.com.utils

import java.util.concurrent.Executors

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer

import scala.concurrent.ExecutionContext


object ExecutorService {
  implicit val system = ActorSystem()
  implicit val mat    = ActorMaterializer()

  val testServiceThreadPool = Executors.newFixedThreadPool(10)
  val testServiceExecutionContext = ExecutionContext.fromExecutorService(testServiceThreadPool)
}
