package name.chengchao.hellospring.controller;

import java.io.File;
import java.net.URL;
import java.util.Date;
import java.util.Properties;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
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
            URL url = ClassLoader.getSystemResource("hellospring.properties");
            sb.append(FileUtils.readFileToString(new File(url.getFile()), "UTF-8"));
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
            sb.append("kubectl test");
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
