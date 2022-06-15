package com.dn.rpc.dubbo;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author dingning
 * @date 2022/6/12 13:50
 * 服务注册、服务发现、服务调用、服务治理
 **/
public class RpcFramework {

    //
    public static void export(int port, Object service) throws Exception {
        ServerSocket server = new ServerSocket(port);
        while (true) {
            Socket socket = server.accept();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        //反序列化
                        ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
                        //这里的转换有问题，拿不到方法名
                        //todo:dingning 2022/6/15 21:42  解决参数问题
//                        String methodName = String.valueOf(input.read()); //读取方法名
                        Class<?>[] parameterTypes = (Class<?>[]) input.readObject(); //参数类型
                        Object[] arguments = (Object[]) input.readObject(); //参数
                        Method method = service.getClass().getMethod("sayHello", parameterTypes); //找到方法
                        Object result = method.invoke(service, arguments); //调用方法
                        ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
                        output.writeObject(result);
                    } catch (IOException | ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

    public static <T> T refer(Class<T> interfaceClass, String host, int port) throws Exception {
        return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class<?>[]{interfaceClass},
                (proxy, method, arguments) -> {
                    Socket socket = new Socket(host, port);  //指定 provider 的 ip 和端口
                    ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
                    output.write(Integer.parseInt(method.getName()));  //传方法名
                    output.writeObject(method.getParameterTypes());  //传参数类型
                    output.writeObject(arguments);  //传参数值
                    ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
                    Object result = input.readObject();  //读取结果
                    return result;
                });
    }

}
