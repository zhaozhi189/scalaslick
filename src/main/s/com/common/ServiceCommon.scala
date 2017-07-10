package s.com.common


trait ServiceCommon {
  def respSuccess(retCode: String, retMessage: String, entity: Any, totalCount: Int = 1) = Map("retCode" -> retCode,
    "retMsg" -> retMessage, "entity" -> entity, "totalCount" -> totalCount)

  def respFail(retCode: String, retMessage: String) = Map("retCode" -> retCode,
    "retMsg" -> retMessage)
}

class ItoaHttpException(message: String = "", cause: Throwable = null)
  extends RuntimeException(message, cause)

case class HttpNullException(message: String = "", cause: Throwable = null)
  extends ItoaHttpException(message, cause)

