import java.io.BufferedInputStream
import java.io.BufferedReader
import java.io.File
import java.io.FileInputStream
import java.io.FileReader
import java.io.PrintWriter
import java.nio.charset.CodingErrorAction
import scala.io.Codec
import scala.io.Source

object UrlCategorizationP {

  import com.guavus.categorization.InputRecord
  import com.guavus.categorization.OutputRecord
  import com.guavus.main.TrafficCategorization
  import java.io._
  import scala.io._
  import java.nio.charset.CodingErrorAction

  def main(args:Array[String]) {
    val fname=args(0)
    processFile(fname)
  }

  val trafficCategorization = new TrafficCategorization()

  def processFile(fileName: String) {
    //Inititalize the parameters :
    val writer = new PrintWriter(new File("Categorized_In_Scala.txt" ))
    val strpath = "/data/prasad/scala/package_drop/"
    val ibReader = new BufferedReader(new FileReader(strpath+"IBStore.tab"))
    val symmetricFileName=new BufferedInputStream(new FileInputStream(strpath+"symmetricKey.txt"))
    val keyFileName=new BufferedInputStream(new FileInputStream(strpath+"privateKey.txt"))
    val ivFileName=new BufferedInputStream(new FileInputStream(strpath+"ivFile.txt"))
    trafficCategorization.init(ibReader,symmetricFileName,keyFileName,ivFileName)

    var count = 0
    implicit val codec = Codec("UTF-8")
    codec.onMalformedInput(CodingErrorAction.REPLACE)
    codec.onUnmappableCharacter(CodingErrorAction.REPLACE)

    val chunkSize = 100000
    val groupedIterator = Source.fromFile(fileName).getLines().grouped(chunkSize)
    
    

    groupedIterator.foreach { lines =>
      lines.par.foreach { line =>          // parallel processing
        count += 1
        if(line.split("\t").length > 24) {
          val tokens = line.split("\t")
          val (userAgent, mdn) = (tokens(24), tokens(2))
          val resultsList = getCategory(userAgent)
          writer.write(mdn + "\t" + userAgent + "\t" + resultsList.mkString("\t") + "\n")
        }
        if(count%1000000 == 0) println(count + " lines processed")
        // println(count+"\t"+line.split("\t").length+"\t"+line)
      }
    }
    writer.close()
  }

  def getCategory(UA: String) : List[String] = {
    val url="Facebook.com"
    val input = new InputRecord()
    input.setHostName(url)
    input.setRefererHostName(url)
    input.setUrl(url)
    input.setRefererUrl(url)
    input.setUA(UA)
    input.setIMEINumber("0")
    val output = new OutputRecord()
    trafficCategorization.process(input, output)
    if(output.getUrlCategory != null)
      List(
        output.getMobileBrowserName,
        output.getMobileAppName,
        output.getMobiledeviceManufacturer,
        output.getMobileDeviceModel,
        output.getMobileOS,
        output.getMobileOSVersion)
    else
      List.empty[String]
  }
}
