package proxy.cglib;

public class TestProxy {

    public static void main(String[] args) {
        UserDao userDao = new UserDao();
        ProxyFactory proxyFactory = new ProxyFactory(userDao);
        UserDao proxy = (UserDao)proxyFactory.getProxyInstance();
        System.out.println(proxy.getClass());
        proxy.save();
    }
}
