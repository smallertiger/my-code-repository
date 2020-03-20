package proxy;

class RealObject implements Interface {

    @Override
    public void doSomething() {
        System.out.println("doSomething");
    }

    @Override
    public void somethingElse(String args) {
        System.out.println("somethingElse" + args);
    }
}

class SimpleProxy implements Interface {
    private Interface anInterface;

    public SimpleProxy(Interface anInterface) {
        this.anInterface = anInterface;
    }

    @Override
    public void doSomething() {
        System.out.println("simpleProxy doSomething");
        anInterface.doSomething();
    }

    @Override
    public void somethingElse(String args) {
        System.out.println("simpleProxy" + args);
        anInterface.somethingElse(args);
    }

}

public class SimpleProxyDemo {

    public static void comsumer(Interface inter) {
        inter.doSomething();
        inter.somethingElse("haha");
    }

    public static void main(String[] args) {
        comsumer(new RealObject());
        System.out.println("----------");
        comsumer(new SimpleProxy(new RealObject()));
    }

}
