package s.com.services.teacher

import org.slf4j.LoggerFactory
import s.com.common.{MsgCode, ServiceCommon}
import s.com.dbentity.EntityTable
import s.com.dbentity.Protocols.TeacherEntity
import s.com.services.teacher.Protocols.TeacherReq
import s.com.utils.{DatabaseService, ExecutorService, Snowflake}

import scala.util.{Failure, Success}


class TeacherService extends EntityTable with ServiceCommon {

  implicit val ec = ExecutorService.testServiceExecutionContext
  val databaseService = DatabaseService.databaseService

  import databaseService._
  import databaseService.driver.api._

  val currentLoginId = 1L
  val logger = LoggerFactory.getLogger(this.getClass)

  def createTeacher(req:TeacherReq)={
    val rightNow = System.currentTimeMillis()

    val teacher = TeacherEntity(
      tid = Snowflake.nextId(),
      tname = req.tname,
      created = rightNow,
      createdBy = currentLoginId,
      updated = rightNow,
      updatedBy = currentLoginId)
    val query = for {
      a <- teachers += teacher
    }yield  teacher

    db.run(query.asTry).map {
      case Success(t) =>
        respSuccess(MsgCode.success, "创建成功", t)
      case Failure(ex) =>
        logger.error("[DB Error]", ex)
        respFail(MsgCode.addError, s"数据库操作失败,${ex.getMessage}")
    }
  }

  def getTeachers() = {
    db.run(teachers.result)
  }

  def updateTeacher(req: TeacherReq, tid: Long) = {
    val query = for {
      u <- teachers.filter(_.tid === tid)
        .map(s => (s.tname))
        .update((req.tname))
      t <- teachers.filter(_.tid === tid).result.headOption
    }yield t
    db.run(query.asTry).map {
      case Success(s) =>
        respSuccess(MsgCode.success, "修改成功", s)
      case Failure(ex) =>
        logger.error("[DB Error]", ex)
        respFail(MsgCode.updateError, s"数据库操作失败,${ex.getMessage}")
    }
  }

  def deleteTeacher(tid:Long)={
    db.run(teachers.filter(_.tid === tid).delete.asTry).map{
      case Success(s) =>
        respSuccess(MsgCode.success, "创建成功", None)
      case Failure(ex) =>
        logger.error("[DB Error]", ex)
        respFail(MsgCode.delError, s"数据库操作失败,${ex.getMessage}")
    }
  }
}
