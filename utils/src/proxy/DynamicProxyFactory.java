package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DynamicProxyFactory {

    private Object target;// 维护一个目标对象

    public DynamicProxyFactory(Object target) {
        this.target = target;
    }

    // 为目标对象生产代理对象
    public Object getProxyInstence() {
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                        System.out.println("save begin");
                        // 执行目标对象方法
                        Object targetValue = method.invoke(target, args);
                        System.out.println("save end");
                        return null;
                    }
                });
    }


}
