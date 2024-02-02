package name.chengchao.hellospring.controller;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CpuController {

    private static Logger logger = LoggerFactory.getLogger(CpuController.class);

    public static final int CpuCoreNum = Runtime.getRuntime().availableProcessors();

    public static ExecutorService MyExecutor = Executors.newFixedThreadPool(CpuCoreNum);

    public static double tmp;

    public static final int TIME_UNIT_MS = 100;

    @RequestMapping(value = "/cpu/eat", method = RequestMethod.GET)
    public String eatCPU(@RequestParam(name = "duration", required = true) final int duration,
        @RequestParam(name = "cpuUtil", required = true) final int cpuUtil) throws Exception {
        eat(duration, cpuUtil);
        return "start! " + new Date();
    }

    public static void eat(int duration, int cpuUtil) throws Exception {
        if (cpuUtil < 5 || cpuUtil > 80) {
            throw new IllegalArgumentException("cpuUtil must be between 5 and 80");
        }

        int runTime = TIME_UNIT_MS * cpuUtil / 100;
        int sleepTime = TIME_UNIT_MS - runTime;
        long start = System.currentTimeMillis();
        logger.info("duration(s):{},cpuUtil(%):{}", duration, cpuUtil);
        logger.info("runTime(ms):" + runTime + ",sleepTime(ms):" + sleepTime + ",CpuCoreNum:" + CpuCoreNum);

        for (int i = 0; i < CpuCoreNum; i++) {
            MyExecutor.execute(() -> {
                logger.info("start,thread:{}", Thread.currentThread().getName());
                while (true) {
                    try {
                        eatCpuTime(runTime);
                        Thread.sleep(sleepTime);
                        if ((System.currentTimeMillis() - start) > (duration * 1000)) {
                            logger.info("end,thread:{}", Thread.currentThread().getName());
                            break;
                        }
                    } catch (InterruptedException e) {
                        logger.error(e.getMessage(), e);
                    }

                }
            });
        }

    }

    public static void eatCpuTime(long useTimeMs) {
        long start = System.nanoTime();
        long useTimeNano = useTimeMs * 1000000;
        while (true) {
            // eat cpu,这里的10000不宜过大,如果设置过大会导致控制的精度不够
            for (int i = 0; i < 10000; i++) {
                tmp = Math.sqrt(i) * Math.sqrt(i);
            }

            if ((System.nanoTime() - start) >= useTimeNano) {
                break;
            }

        }
    }

}
