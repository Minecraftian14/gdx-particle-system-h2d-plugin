package com.alyrow.gdx.particle.utilities;

public class Try {

    public static void defaults(DirtyRunnable runnable) {
        try {
            runnable.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static <T> T defaults(DirtySupplier<T> supplier, T defaultValue) {
        try {
            return supplier.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return defaultValue;
    }

    public static void ignore(DirtyRunnable runnable) {
        try {
            runnable.run();
        } catch (Exception ignored) {
        }
    }

    public static <T> T ignore(DirtySupplier<T> supplier, T defaultValue) {
        try {
            return supplier.get();
        } catch (Exception ignored) {
        }
        return defaultValue;
    }

    public interface DirtyRunnable {
        void run() throws Exception;
    }

    public interface DirtySupplier<T> {
        T get() throws Exception;
    }
}
