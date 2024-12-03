//> using scala 2

import scala.concurrent._
import scala.concurrent.duration._

trait TaskMonitor {
  def isCancelled: Boolean
}

class CompService() {
  def compute(
      i: Int
  )(implicit ec: ExecutionContext, taskMonitor: TaskMonitor): Future[Int] =
    if (!taskMonitor.isCancelled) Future(i).map(_ + 1)
    else Future.failed(new RuntimeException("cancelled"))
}

object s3ContextFunctionDemo extends App {
  // val c1 = new CompService().compute(1) //compilation error
  // val c2 = new CompService().compute(1) //compilation error
  implicit val ec: ExecutionContext = ExecutionContext.global
  // val o: TaskMonitor ?=> Future[Int] = CompService().computeTwice(1)
  implicit val tm: TaskMonitor = new TaskMonitor {
    def isCancelled: Boolean = false
  }
  val c1 = new CompService().compute(1)
  val c2 = new CompService().compute(1)
  val r: Future[Int] = Future.sequence(Seq(c1, c2)).map(_.sum)
  println(Await.result(r, 1.seconds))
}
