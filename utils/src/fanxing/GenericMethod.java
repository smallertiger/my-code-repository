package fanxing;

public class GenericMethod {
    public <T> void f(T x) {
        System.out.println(x.getClass().getSimpleName() + "  " + x.getClass().getName());
    }

    public static void main(String[] args) {
        GenericMethod genericMethod = new GenericMethod();
        genericMethod.f('c');
        genericMethod.f("aa");
        genericMethod.f(1);
        genericMethod.f(1.1f);
        genericMethod.f(1.0);
        genericMethod.f(genericMethod);
    }
}
