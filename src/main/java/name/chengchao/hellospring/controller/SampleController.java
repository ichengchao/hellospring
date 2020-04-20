package name.chengchao.hellospring.controller;

import java.io.InputStream;
import java.util.Date;
import java.util.Properties;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import name.chengchao.hellospring.util.LocalInfoUtils;

@Controller
public class SampleController {

    public static final String lineBreak = "\n";

    @RequestMapping("/")
    @ResponseBody
    String root() {
        return "Hello World! @" + new Date();
    }

    @RequestMapping("/hello")
    public void hello(HttpServletRequest request, HttpServletResponse response) {
        try {
            StringBuilder sb = new StringBuilder();

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
            response.getWriter().write(sb.toString());
            response.flushBuffer();
        } catch (Exception e) {
            System.out.println(e.getMessage());

        }

    }

    @RequestMapping("/system")
    public void system(HttpServletRequest request, HttpServletResponse response) {
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

            response.getWriter().write(sb.toString());
            response.flushBuffer();
        } catch (Exception e) {
            System.out.println(e.getMessage());

        }

    }

    @RequestMapping("/mock")
    public void mock(HttpServletRequest request, HttpServletResponse response) {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("mock 测试");
            sb.append(lineBreak);
            sb.append(Thread.currentThread());
            sb.append(lineBreak);
            sb.append(new Date());
            response.getWriter().write(sb.toString());
            response.flushBuffer();
        } catch (Exception e) {
            System.out.println(e.getMessage());

        }

    }

}
