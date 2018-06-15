package cn.zjj.tips.base.reactor;

import java.io.IOException;
import java.nio.channels.SocketChannel;
/**
 * @Date: 2018/6/15 10:36
 * @Description:
 */
public class Acceptor implements Runnable{

    private Reactor reactor;
    public Acceptor(Reactor reactor){
        this.reactor=reactor;
    }
    @Override
    public void run() {
        try {
            SocketChannel socketChannel=reactor.serverSocketChannel.accept();
            if(socketChannel!=null)//调用Handler来处理channel
                new SocketReadHandler(reactor.selector, socketChannel);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
