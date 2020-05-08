package name.chengchao.hellospring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan("name.chengchao.helloweb")
public class MyApplication {


    public static final String Aliyun_AccessKeyId = "LTAI4G7SCbPx6PudCAxHk5ap";

    public static final String Aliyun_AccessKeySecret = "YUguDMwzIaCsbmi6SKmtcdguOMRwel";

    public static void main(String[] args) {
        SpringApplication.run(MyApplication.class, args);
    }

}
