package name.chengchao.hellospring.controller;

import java.io.InputStream;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.BuildProperties;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import name.chengchao.hellospring.service.AliyunAPIService;
import name.chengchao.hellospring.util.LocalInfoUtils;

@RestController
public class SampleController {

    @Autowired
    private BuildProperties buildProperties;

    public static final String lineBreak = "\n";

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Object index() {
        return "Hello World! @" + new Date();
    }

    @RequestMapping(value = "/sayhello/{name}", method = RequestMethod.GET)
    public Object sayhello(@PathVariable String name) {
        return "hi," + name;
    }

    @RequestMapping(value = "/env", method = RequestMethod.GET)
    public String env() {
        try {
            StringBuilder sb = new StringBuilder();
            Map<String, String> envVariables = System.getenv();
            for (Map.Entry<String, String> entry : envVariables.entrySet()) {
                sb.append(entry.getKey() + "=" + entry.getValue());
                sb.append(lineBreak);
            }
            return sb.toString();
        } catch (Exception e) {
            return e.getMessage();
        }

    }

    @RequestMapping(value = "/getCaller", method = RequestMethod.GET)
    public String getCaller(HttpServletRequest request, HttpServletResponse response) {
        try {
            String result = AliyunAPIService.getCaller();
            return result;
        } catch (Exception e) {
            return e.getMessage();
        }

    }

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello() {
        try {
            StringBuilder sb = new StringBuilder();

            Map<String, String> envVariables = System.getenv();
            for (Map.Entry<String, String> entry : envVariables.entrySet()) {
                sb.append(entry.getKey() + "=" + entry.getValue());
                sb.append(lineBreak);
            }

            // show hellospring.properties
            // System.out.println(ClassLoader.getSystemResource("BOOT-INF"));
            System.out.println(SampleController.class.getResource("/hellospring.properties"));
            // URL url = ClassLoader.getSystemResource("hellospring.properties");
            InputStream inputStream = SampleController.class.getResourceAsStream("/hellospring.properties");
            sb.append(IOUtils.toString(inputStream, "UTF-8"));
            sb.append(lineBreak);
            sb.append("-----------------------------------------------------------------------------");
            sb.append(lineBreak);
            sb.append("getAllIpv4NoLoopbackAddresses:" + LocalInfoUtils.getAllIpv4NoLoopbackAddresses());
            sb.append(lineBreak);
            sb.append("HostName:" + LocalInfoUtils.getHostNameForLiunx());
            sb.append(lineBreak);
            sb.append(new Date());
            sb.append(lineBreak);
            sb.append("hello k8s 3333");
            sb.append(lineBreak);
            sb.append(buildProperties.getName());
            sb.append(lineBreak);
            ZoneId zoneIdShanghai = ZoneId.of("Asia/Shanghai");
            DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withZone(zoneIdShanghai);
            Instant instant = buildProperties.getTime();
            // sb.append(instant);
            // sb.append(lineBreak);
            sb.append(formatter.format(instant));
            return sb.toString();
        } catch (Exception e) {
            return e.getMessage();
        }

    }

    @RequestMapping(value = "/system", method = RequestMethod.GET)
    public String system(HttpServletRequest request, HttpServletResponse response) {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append(new Date());
            sb.append(lineBreak);
            sb.append("-----------------------------------------------------------------------------");
            sb.append(lineBreak);
            sb.append("getAllIpv4NoLoopbackAddresses:" + LocalInfoUtils.getAllIpv4NoLoopbackAddresses());
            sb.append(lineBreak);
            sb.append("HostName:" + LocalInfoUtils.getHostNameForLiunx());
            sb.append(lineBreak);
            sb.append("-----------------------------------------------------------------------------");
            sb.append(lineBreak);
            sb.append(lineBreak);

            Properties props = System.getProperties();
            Set<Object> keys = props.keySet();
            for (Object key : keys) {
                sb.append(key.toString() + " = " + props.getProperty(key.toString()));
                sb.append(lineBreak);
            }

            return sb.toString();
        } catch (Exception e) {
            return e.getMessage();
        }

    }

}
