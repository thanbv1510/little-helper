package dev.thanbv1510.littlehelper;

public abstract class AbsLittleHelperApplication {
    protected void addShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {

            System.out.println("shutdown hook");

            System.exit(9);
        }));
    }
}
