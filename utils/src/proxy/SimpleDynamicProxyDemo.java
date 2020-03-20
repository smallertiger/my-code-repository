package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

class DynamicProxyHandler implements InvocationHandler {

    private Object proxied;
    public DynamicProxyHandler(Object proxied) {
        this.proxied = proxied;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("proxy:" + proxy.getClass() + ", method:" + method + ", args:" + args);
        if(args != null) {
            for(Object obj : args) {
                System.out.println("  " + obj);
            }
        }
        if(method.getName().equals("somethingElse")) {
            System.out.println("DynamicProxy: " + args);
        }

        return method.invoke(proxied, args);
    }
}

public class SimpleDynamicProxyDemo {
    public static void consumer(Interface inter) {
        inter.doSomething();
        inter.somethingElse("baba");
    }

    public static void main(String[] args) {
        RealObject real = new RealObject();
        consumer(real);

        Interface proxy = (Interface) Proxy.newProxyInstance(Interface.class.getClassLoader(),
                new Class[] {Interface.class},
                new DynamicProxyHandler(real));
        consumer(proxy);
    }
}
