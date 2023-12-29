package name.chengchao.hellospring.controller;

import java.io.InputStream;
import java.util.Date;
import java.util.Map;
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

    @RequestMapping("/env")
    public void env(HttpServletRequest request, HttpServletResponse response) {
        try {
            StringBuilder sb = new StringBuilder();
            Map<String, String> envVariables = System.getenv();
            for (Map.Entry<String, String> entry : envVariables.entrySet()) {
                sb.append(entry.getKey() + "=" + entry.getValue());
                sb.append(lineBreak);
            }
            response.getWriter().write(sb.toString());
            response.flushBuffer();
        } catch (Exception e) {
            System.out.println(e.getMessage());

        }

    }

    @RequestMapping("/hello")
    public void hello(HttpServletRequest request, HttpServletResponse response) {
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
            sb.append("hello k8s1111");
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
            sb.append("机器增加到3个pod");
            sb.append(lineBreak);
            sb.append("-----------------------------------------------------------------------------");
            sb.append(lineBreak);
            sb.append("HostName:" + LocalInfoUtils.getHostNameForLiunx());
            sb.append(lineBreak);
            sb.append(Thread.currentThread());
            sb.append(lineBreak);
            sb.append(new Date());
            response.setCharacterEncoding("UTF-8");
            response.setHeader("content-type", "text/plain;charset=utf-8");
            response.getWriter().write(sb.toString());
            response.flushBuffer();
        } catch (Exception e) {
            System.out.println(e.getMessage());

        }

    }

}
