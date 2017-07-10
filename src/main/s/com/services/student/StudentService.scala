package s.com.services.student

import org.slf4j.LoggerFactory
import s.com.common.{MsgCode, ServiceCommon}
import s.com.dbentity.EntityTable
import s.com.dbentity.Protocols.StudentEntity
import s.com.services.student.Protocols.StudentReq
import s.com.utils.{DatabaseService, ExecutorService, Snowflake}

import scala.util.{Failure, Success}


class StudentService extends EntityTable with ServiceCommon {

  implicit val ec = ExecutorService.testServiceExecutionContext
  val databaseService = DatabaseService.databaseService

  import databaseService._
  import databaseService.driver.api._

  val currentLoginId = 1L

  val logger = LoggerFactory.getLogger(this.getClass)

  def createStudent(req: StudentReq) = {
    val rightNow = System.currentTimeMillis()
    val stu = StudentEntity(
      sid = Snowflake.nextId(),
      sname = req.sname,
      sage = req.sage,
      ssex = req.ssex,
      address = req.address,
      created = rightNow,
      createdBy = currentLoginId,
      updated = rightNow,
      updatedBy = currentLoginId)
    val query = for {
      a <- students += stu
    } yield stu


    db.run(query.asTry).map {
      case Success(s) =>
        respSuccess(MsgCode.success, "创建成功", s)
      case Failure(ex) =>
        logger.error("[DB Error]", ex)
        respFail(MsgCode.addError, s"数据库操作失败,${ex.getMessage}")
    }
  }

  def getStudents() = {
    db.run(students.result)
  }

  def updateStudent(req: StudentReq, sid: Long) = {
    val query = for {
      u <- students.filter(_.sid === sid)
        .map(s => (s.sage, s.ssex))
        .update((req.sage, req.ssex))
      s <- students.filter(_.sid === sid).result.headOption
    }yield s
    db.run(query.asTry).map {
      case Success(s) =>
        respSuccess(MsgCode.success, "创建成功", s)
      case Failure(ex) =>
        logger.error("[DB Error]", ex)
        respFail(MsgCode.updateError, s"数据库操作失败,${ex.getMessage}")
    }
  }

  def deleteStudent(sid:Long)={
    db.run(students.filter(_.sid === sid).delete.asTry).map{
      case Success(s) =>
        respSuccess(MsgCode.success, "创建成功", None)
      case Failure(ex) =>
        logger.error("[DB Error]", ex)
        respFail(MsgCode.delError, s"数据库操作失败,${ex.getMessage}")
    }
  }
}
