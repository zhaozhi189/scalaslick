package s.com.dbentity

import s.com.utils.DatabaseService



object Protocols {
  final case class CourseEntity(cid: Long, cname: String, tid: Long, created: Long, createdBy: Long, updated: Long, updatedBy: Long)
  final case class ScEntity(cid: Long, sid: Long, score: Byte, created: Long, createdBy: Long, updated: Long, updatedBy: Long)
  final case class StudentEntity(sid: Long, sname: String, sage: Byte, ssex: String, address: String, created: Long, createdBy: Long, updated: Long, updatedBy: Long)
  final case class TeacherEntity(tid: Long, tname: String, created: Long, createdBy: Long, updated: Long, updatedBy: Long)
}

trait EntityTable {
  protected val databaseService: DatabaseService

  import databaseService.driver.api._
  import Protocols._


  class Course(_tableTag: Tag) extends Table[CourseEntity](_tableTag, "course") {
    def * = (cid, cname, tid, created, createdBy, updated, updatedBy) <>(CourseEntity.tupled, CourseEntity.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(cid), Rep.Some(cname), Rep.Some(tid), Rep.Some(created), Rep.Some(createdBy), Rep.Some(updated), Rep.Some(updatedBy)).shaped.<>({r=>import r._; _1.map(_=> CourseEntity.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6.get, _7.get)))}, (_:Any) => throw new Exception("Inserting into ? projection not supported."))

    /** Database column cid SqlType(BIGINT), PrimaryKey */
    val cid: Rep[Long] = column[Long]("cid", O.PrimaryKey)
    /** Database column cname SqlType(VARCHAR), Length(255,true) */
    val cname: Rep[String] = column[String]("cname")
    /** Database column tid SqlType(BIGINT) */
    val tid: Rep[Long] = column[Long]("tid")
    /** Database column created SqlType(BIGINT) */
    val created: Rep[Long] = column[Long]("created")
    /** Database column createdBy SqlType(BIGINT) */
    val createdBy: Rep[Long] = column[Long]("createdBy")
    /** Database column updated SqlType(BIGINT) */
    val updated: Rep[Long] = column[Long]("updated")
    /** Database column updatedBy SqlType(BIGINT) */
    val updatedBy: Rep[Long] = column[Long]("updatedBy")
  }
  lazy val courses = new TableQuery(tag => new Course(tag))


  class Sc(_tableTag: Tag) extends Table[ScEntity](_tableTag, "sc") {
    def * = (cid, sid, score, created, createdBy, updated, updatedBy) <>(ScEntity.tupled, ScEntity.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(cid), Rep.Some(sid), Rep.Some(score), Rep.Some(created), Rep.Some(createdBy), Rep.Some(updated), Rep.Some(updatedBy)).shaped.<>({r=>import r._; _1.map(_=> ScEntity.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6.get, _7.get)))}, (_:Any) => throw new Exception("Inserting into ? projection not supported."))

    /** Database column cid SqlType(BIGINT) */
    val cid: Rep[Long] = column[Long]("cid")
    /** Database column sid SqlType(BIGINT) */
    val sid: Rep[Long] = column[Long]("sid")
    /** Database column score SqlType(TINYINT) */
    val score: Rep[Byte] = column[Byte]("score")
    /** Database column created SqlType(BIGINT) */
    val created: Rep[Long] = column[Long]("created")
    /** Database column createdBy SqlType(BIGINT) */
    val createdBy: Rep[Long] = column[Long]("createdBy")
    /** Database column updated SqlType(BIGINT) */
    val updated: Rep[Long] = column[Long]("updated")
    /** Database column updatedBy SqlType(BIGINT) */
    val updatedBy: Rep[Long] = column[Long]("updatedBy")

    /** Primary key of Sc (database name sc_PK) */
    val pk = primaryKey("sc_PK", (cid, sid))
  }
  lazy val scs = new TableQuery(tag => new Sc(tag))


  class Student(_tableTag: Tag) extends Table[StudentEntity](_tableTag, "student") {
    def * = (sid, sname, sage, ssex, address, created, createdBy, updated, updatedBy) <>(StudentEntity.tupled, StudentEntity.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(sid), Rep.Some(sname), Rep.Some(sage), Rep.Some(ssex), Rep.Some(address), Rep.Some(created), Rep.Some(createdBy), Rep.Some(updated), Rep.Some(updatedBy)).shaped.<>({r=>import r._; _1.map(_=> StudentEntity.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6.get, _7.get, _8.get, _9.get)))}, (_:Any) => throw new Exception("Inserting into ? projection not supported."))

    /** Database column sid SqlType(BIGINT), PrimaryKey */
    val sid: Rep[Long] = column[Long]("sid", O.PrimaryKey)
    /** Database column sname SqlType(VARCHAR), Length(255,true) */
    val sname: Rep[String] = column[String]("sname")
    /** Database column sage SqlType(TINYINT) */
    val sage: Rep[Byte] = column[Byte]("sage")
    /** Database column ssex SqlType(VARCHAR), Length(5,true) */
    val ssex: Rep[String] = column[String]("ssex")
    /** Database column address SqlType(VARCHAR), Length(1024,true) */
    val address: Rep[String] = column[String]("address")
    /** Database column created SqlType(BIGINT) */
    val created: Rep[Long] = column[Long]("created")
    /** Database column createdBy SqlType(BIGINT) */
    val createdBy: Rep[Long] = column[Long]("createdBy")
    /** Database column updated SqlType(BIGINT) */
    val updated: Rep[Long] = column[Long]("updated")
    /** Database column updatedBy SqlType(BIGINT) */
    val updatedBy: Rep[Long] = column[Long]("updatedBy")
  }
  lazy val students = new TableQuery(tag => new Student(tag))


  class Teacher(_tableTag: Tag) extends Table[TeacherEntity](_tableTag, "teacher") {
    def * = (tid, tname, created, createdBy, updated, updatedBy) <>(TeacherEntity.tupled, TeacherEntity.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(tid), Rep.Some(tname), Rep.Some(created), Rep.Some(createdBy), Rep.Some(updated), Rep.Some(updatedBy)).shaped.<>({r=>import r._; _1.map(_=> TeacherEntity.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6.get)))}, (_:Any) => throw new Exception("Inserting into ? projection not supported."))

    /** Database column tid SqlType(BIGINT), PrimaryKey */
    val tid: Rep[Long] = column[Long]("tid", O.PrimaryKey)
    /** Database column tname SqlType(VARCHAR), Length(255,true) */
    val tname: Rep[String] = column[String]("tname")
    /** Database column created SqlType(BIGINT) */
    val created: Rep[Long] = column[Long]("created")
    /** Database column createdBy SqlType(BIGINT) */
    val createdBy: Rep[Long] = column[Long]("createdBy")
    /** Database column updated SqlType(BIGINT) */
    val updated: Rep[Long] = column[Long]("updated")
    /** Database column updatedBy SqlType(BIGINT) */
    val updatedBy: Rep[Long] = column[Long]("updatedBy")
  }
  lazy val teachers = new TableQuery(tag => new Teacher(tag))
}

