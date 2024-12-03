//> using scala 3
import scala.concurrent.*
import scala.concurrent.duration.given

trait TaskMonitor:
  def isCancelled: Boolean

type Executable[T] = ExecutionContext ?=> Future[T]
type Monitoring[M] = TaskMonitor ?=> M
type Computation[C] = Monitoring[Executable[C]]

class CompService():
  def compute(i: Int): Computation[Int] =
    if !summon[TaskMonitor].isCancelled then Future(i).map(_ + 1)
    else Future.failed(new RuntimeException("cancelled"))

@main
def s3ContextFunctionDemo: Unit =
  val c1: Computation[Int] = CompService().compute(1)
  val c2: Computation[Int] = CompService().compute(1)

  given ExecutionContext = ExecutionContext.global
  val o: TaskMonitor ?=> Future[Int] = c1 // partial context application

  given TaskMonitor = new TaskMonitor { def isCancelled: Boolean = false }
  val r: Future[Int] = Future.sequence(Seq(c1, c2)).map(_.sum)
  println(Await.result(r, 1.seconds))
