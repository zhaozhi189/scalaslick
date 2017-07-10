package s.com.routes

import akka.http.scaladsl.server.Directives._
import s.com.services.teacher.Protocols.TeacherReq
import s.com.services.teacher.TeacherService
import s.com.utils.JsonUtil._

class TeacherServiceRoute {
  val service = new TeacherService()

  val route = pathPrefix("slick") {
    path("teacher") {
      get {
        complete(service.getTeachers())
      }
    } ~
      path("teacher" / LongNumber) {
        tid =>
          put {
            entity(as[TeacherReq]) {
              req =>
                complete(service.updateTeacher(req, tid))
            }
          } ~
            put {
              complete(service.deleteTeacher(tid))
            }
      }
  }
}
