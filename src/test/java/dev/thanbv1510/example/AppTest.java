package dev.thanbv1510.example;

import dev.thanbv1510.littlehelper.Init;
import dev.thanbv1510.littlehelper.LittleHelper;
import dev.thanbv1510.littlehelper.LittleHelperApplication;
import dev.thanbv1510.littlehelper.ShutdownHook;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@LittleHelper
public class AppTest {
//    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public static void main(String[] args) {
        LittleHelperApplication.run(AppTest.class, args);
    }

    @Init
    public void init() throws Exception {
        log.info("init method...");

        Thread.sleep(10000);
    }

    @ShutdownHook
    public void preShutdown() throws Exception {
        log.info("do something before shutdown in hook");
    }
}
