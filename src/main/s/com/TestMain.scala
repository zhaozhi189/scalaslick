package s.com

import akka.event.{Logging, LoggingAdapter}
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.HttpResponse
import akka.http.scaladsl.model.StatusCodes._
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.ExceptionHandler
import s.com.common.HttpNullException
import s.com.routes.{StudentServiceRoute, TeacherServiceRoute}
import s.com.utils.ExecutorService


object TestMain {

  import ExecutorService.{mat, system}

  implicit val log: LoggingAdapter = Logging(system, getClass)

  def main(args: Array[String]) {
    Http().bindAndHandle(route, "0.0.0.0", 8086, log = log)
  }

  def route = {
    val studentRoute = new StudentServiceRoute()
    val teacherRoute = new TeacherServiceRoute()
    studentRoute.route ~ teacherRoute.route
  }

  implicit def myExceptionHandler: ExceptionHandler =
    ExceptionHandler {
      case ex: ArithmeticException =>
        extractUri { uri =>
          log.error(s"uri:[$uri] ,error msg:${ex.getMessage}", ex)
          complete(HttpResponse(InternalServerError, entity = ex.getMessage))
        }
      case ex: HttpNullException =>
        complete(HttpResponse(NotFound, entity = ex.getMessage))
      case ex: Exception =>
        log.error(s"error msg:${ex.getMessage}", ex)
        complete(HttpResponse(InternalServerError, entity = ex.getMessage))
    }
}
