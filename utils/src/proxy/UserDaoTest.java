package proxy;

public class UserDaoTest {
    public static void main(String[] args) {
        DynamicProxyFactory factory = new DynamicProxyFactory(new UserDao());
        IUserDao proxy = (IUserDao) factory.getProxyInstence();
        System.out.println(proxy.getClass());  //输出代理对象信息
        proxy.save();
    }
}
