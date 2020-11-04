package io.netty;

public class NettyServerApplication {

    public static void main(String[] args) {
        try {
            new HttpServer(8801).run();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
