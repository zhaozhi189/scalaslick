package s.com.routes

import akka.http.scaladsl.server.Directives._
import s.com.services.student.Protocols.StudentReq
import s.com.services.student.StudentService
import s.com.utils.JsonUtil._


class StudentServiceRoute {
  val service = new StudentService
  val route = pathPrefix("slick") {
    path("student") {
      get {
        complete(service.getStudents())
      } ~
        post {
          entity(as[StudentReq]) {
            req =>
              complete(service.createStudent(req))
          }
        }
    }~
    path("student" / LongNumber){
      sid =>
        put{
          entity(as[StudentReq]) {
            req =>
              complete(service.updateStudent(req,sid))
          }
        }~
        delete{
          complete(service.deleteStudent(sid))
        }
    }
  }
}
