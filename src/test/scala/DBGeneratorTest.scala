
import org.junit.Test

class DBGeneratorTest {

  @Test
  def testSourceCodeGenerator = {
    val as = Array[String](//"jdbc:mysql://192.168.31.46:3306/itoaManagement?user=root&password=User@123",
      "slick.jdbc.MySQLProfile", //slick.driver.MySQLDriver,JdbcProfile
      "com.mysql.jdbc.Driver", //com.mysql.jdbc.Driver,MysqlDataSource,com.mysql.cj.jdbc.Driver
      "jdbc:mysql://localhost:3306/slicktest?characterEncoding=utf8&useSSL=false",
      "g:src/",
      "eoi.sp.dao", "test1", "test1")

    slick.codegen.SourceCodeGenerator.main(as)
  }
}
