package com.example.websocket.websockettest.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;

/**
 * @author xw
 * @date 2019/6/3 15:05
 */
public class NettyServer {
    public static void main(String[] args) {
        // 新建引导类
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        // 接受新连接线程
        NioEventLoopGroup boos = new NioEventLoopGroup();
        // 负责读取连接数据的线程 主要用于读取数据以及业务逻辑处理
        NioEventLoopGroup worker = new NioEventLoopGroup();

        serverBootstrap.group(boos, worker) // 引导类配置两大线程创建 线程模型
                .channel(NioServerSocketChannel.class) // 指定 I/O模型 为 NIO，若要配置为BIO则修改为 OioServerSocketChannel.class 但不建议
                .childHandler(new ChannelInitializer<NioSocketChannel>() { // 定义后续每条连接的 数据读写、业务处理逻辑
                    @Override
                    protected void initChannel(NioSocketChannel ch){
                        ch.pipeline().addLast(new StringDecoder());
                        ch.pipeline().addLast(new SimpleChannelInboundHandler<String>() {
                            @Override
                            protected void channelRead0(ChannelHandlerContext channelHandlerContext, String s) throws Exception {
                                System.out.println(s);
                            }
                        });
                    }
                })
        .bind(8000);
    }
}
