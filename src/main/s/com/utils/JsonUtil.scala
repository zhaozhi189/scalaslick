package s.com.utils

import com.fasterxml.jackson.databind.{DeserializationFeature, ObjectMapper}
import com.fasterxml.jackson.module.scala.experimental.ScalaObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import akka.http.javadsl.marshallers.jackson.Jackson
import akka.http.scaladsl.marshalling._
import akka.http.scaladsl.model.MediaTypes.`application/json`
import akka.http.scaladsl.unmarshalling.{FromEntityUnmarshaller, Unmarshaller}
import akka.util.ByteString
import scala.reflect.ClassTag


object JsonUtil extends JacksonSupport {
  val defaultObjectMapper = new ObjectMapper() with ScalaObjectMapper
  defaultObjectMapper.registerModule(DefaultScalaModule)
  defaultObjectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

  def toJson(value: Any): String = {
    defaultObjectMapper.writeValueAsString(value)
  }

  def fromJson[T](json: String)(implicit m: Manifest[T]): T = {
    defaultObjectMapper.readValue[T](json)
  }
}


trait JacksonSupport {

  import JsonUtil._

  private val jsonStringUnmarshaller =
    Unmarshaller.byteStringUnmarshaller
      .forContentTypes(`application/json`)
      .mapWithCharset {
        case (ByteString.empty, _) => throw Unmarshaller.NoContentException
        case (data, charset) => data.decodeString(charset.nioCharset.name)
      }

  /**
    * HTTP entity => `A`
    */
  implicit def jacksonUnmarshaller[A](
                                       implicit ct: ClassTag[A],
                                       objectMapper: ScalaObjectMapper = defaultObjectMapper,
                                       m: Manifest[A]
                                     ): FromEntityUnmarshaller[A] = {
    jsonStringUnmarshaller.map(
      //data => objectMapper.readValue(data, ct.runtimeClass).asInstanceOf[A]
      data => objectMapper.readValue(data)
    )
  }

  /**
    * `A` => HTTP entity
    */
  implicit def jacksonToEntityMarshaller[Object](
                                                  implicit objectMapper: ObjectMapper = defaultObjectMapper
                                                ): ToEntityMarshaller[Object] = {
    Jackson.marshaller[Object](objectMapper)
  }
}
