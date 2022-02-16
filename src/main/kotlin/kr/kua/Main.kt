import org.openqa.selenium.By
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

lateinit var campusDriver: ChromeDriver
lateinit var officialDriver: ChromeDriver
fun main() {
    Runtime.getRuntime().addShutdownHook(object : Thread() {
        override fun run(){
            campusDriver.close()
            officialDriver.close()
        }
    })

    //프로퍼티 설정하기
    val webDriverID = "webdriver.chrome.driver"
    val webDriverPath = "C:\\chromedriver.exe"
    System.setProperty(webDriverID, webDriverPath)

    //크롬 드라이버 옵션 설정하기
    val options = ChromeOptions()
    options.setBinary("")
//    options.addArguments("--start-maximized")
    options.addArguments("--disable-popup-blocking")
    options.addArguments("--disable-default-apps")
//    options.addArguments("--headless")
//    options.addArguments("--no-sandbox")

    val campusUrl = "https://www.samsungebiz.com/event/galaxycampus/smartphones/galaxy-s22-s906-cpo/SM-S906NLBWKOO/"
    val officialUrl = "https://www.samsung.com/sec/galaxy-s22/preorder/"
    campusDriver = ChromeDriver(options)
    campusDriver.get(campusUrl)
    officialDriver = ChromeDriver(options)
    officialDriver.get(officialUrl)
    Thread.sleep(5000)
    while (true) {

        try {
            print(
                "[${LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))}](Campus)   "
            )

            campusDriver.findElement(By.xpath("/html/body/div[3]/div[3]/div[3]/section[1]/div/div/div[2]/div[1]/div[3]/dl[1]/dd/div/ol/li[1]/label")).click()
            Thread.sleep(2000)
            var element =
                campusDriver.findElement(By.xpath("/html/body/div[3]/div[3]/div[3]/section[1]/div/div/div[2]/div[1]/div[6]/div[2]/p"))
            print("skyblue: " + element.text) // 상품을 준비중입니다.

            campusDriver.findElement(By.xpath("/html/body/div[3]/div[3]/div[3]/section[1]/div/div/div[2]/div[1]/div[3]/dl[1]/dd/div/ol/li[2]/label/span")).click()
            Thread.sleep(2000)
            element = campusDriver.findElement(By.xpath("/html/body/div[3]/div[3]/div[3]/section[1]/div/div/div[2]/div[1]/div[6]/div[2]/p"))
            print(", graphite: " + element.text) // 상품을 준비중입니다.

            campusDriver.findElement(By.xpath("/html/body/div[3]/div[3]/div[3]/section[1]/div/div/div[2]/div[1]/div[3]/dl[1]/dd/div/ol/li[3]/label/span")).click()
            Thread.sleep(2000)
            element = campusDriver.findElement(By.xpath("/html/body/div[3]/div[3]/div[3]/section[1]/div/div/div[2]/div[1]/div[6]/div[2]/p"))
            print(", cream: " + element.text) // 상품을 준비중입니다.

            campusDriver.findElement(By.xpath("/html/body/div[3]/div[3]/div[3]/section[1]/div/div/div[2]/div[1]/div[3]/dl[1]/dd/div/ol/li[4]/label/span")).click()
            Thread.sleep(2000)
            element = campusDriver.findElement(By.xpath("/html/body/div[3]/div[3]/div[3]/section[1]/div/div/div[2]/div[1]/div[6]/div[2]/p"))
            println(", violet: " + element.text) // 상품을 준비중입니다.

            campusDriver.navigate().refresh()
        } catch (e: Exception) {
            println("가져오기 실패(구매가능)")
            campusDriver.navigate().refresh()
            Thread.sleep(2000)
        }

        try {
            print(
                "[${LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))}](Official) "
            )
            officialDriver.findElement(By.xpath("/html/body/div[2]/div[2]/main/section[4]/div/div[1]/div/div[2]/div/a[2]")).click()
            Thread.sleep(2000)

            officialDriver.findElement(By.xpath("/html/body/div[2]/div[2]/main/section[4]/div/div[1]/div/div[4]/div[1]/div[3]/a[1]/span")).click()
            Thread.sleep(2000)
            var element = officialDriver.findElement(By.xpath("/html/body/div[2]/div[2]/main/section[4]/div/div[1]/div/div[8]/div[3]"))
            print("graphite: " + element.getAttribute("data-mod")) // soldout

            officialDriver.findElement(By.xpath("/html/body/div[2]/div[2]/main/section[4]/div/div[1]/div/div[4]/div[1]/div[3]/a[2]/span")).click()
            Thread.sleep(2000)
            element = officialDriver.findElement(By.xpath("/html/body/div[2]/div[2]/main/section[4]/div/div[1]/div/div[8]/div[3]"))
            print(", cream: " + element.getAttribute("data-mod")) // soldout

            officialDriver.findElement(By.xpath("/html/body/div[2]/div[2]/main/section[4]/div/div[1]/div/div[4]/div[1]/div[3]/a[3]/span")).click()
            Thread.sleep(2000)
            element = officialDriver.findElement(By.xpath("/html/body/div[2]/div[2]/main/section[4]/div/div[1]/div/div[8]/div[3]"))
            print(", skyblue: " + element.getAttribute("data-mod")) // soldout

            officialDriver.findElement(By.xpath("/html/body/div[2]/div[2]/main/section[4]/div/div[1]/div/div[4]/div[1]/div[3]/a[4]/span")).click()
            Thread.sleep(2000)
            element = officialDriver.findElement(By.xpath("/html/body/div[2]/div[2]/main/section[4]/div/div[1]/div/div[8]/div[3]"))
            println(", violet: " + element.getAttribute("data-mod")) // soldout

            officialDriver.navigate().refresh()
        } catch (e: Exception) {
            println("가져오기 실패(구매가능)")
            campusDriver.navigate().refresh()
            Thread.sleep(2000)
        }
        Thread.sleep(5000)
    }
}