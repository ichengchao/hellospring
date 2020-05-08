package name.chengchao.hellospring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan("name.chengchao.helloweb")
public class MyApplication {


    public static final String Aliyun_AccessKeyId = "bRrm85CCuCBy6d3a";

    public static final String Aliyun_AccessKeySecret = "6AB07oXYXwO3b7hddzUnr8q53yd0DX";

    public static void main(String[] args) {
        SpringApplication.run(MyApplication.class, args);
    }

}
