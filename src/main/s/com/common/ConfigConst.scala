package s.com.common

object MsgCode {
  //处理成功返回码
  val success = "0000"

  //有重复记录返回码,{*}记录重复
  val notUnique = "0010"

  //不存在记录的返回码,{*}记录不存在
  val notExist = "0020"

  //不允许为空的返回码,
  val notNull = "0030"

  //类型不匹配的返回码或者数据输入不一致
  val notMatch = "0040"

  //超过最大长度的返回码
  val overLength = "0050"

  //超时
  val timeOut = "0060"

  //数据库插入错误情况返回码  01**
  val addError = "0100"

  //数据库删除错误情况返回码  02**
  val delError = "0200"
  val delInUsed = "0210"

  //数据库修改错误情况返回码  03**
  val updateError = "0300"
  val needComfirm = "0320"
  val queryError = "0400"

  val unAuthorized = "5000"
  val noRights = "6000"
}
