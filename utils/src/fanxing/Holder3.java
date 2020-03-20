package fanxing;

public class Holder3<T> {

    private T t;

    public Holder3 (T t) {
        this.t = t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public T getT() {
        return t;
    }

    public static void main(String[] args) {
        Holder3<Automobile> holder3 = new Holder3<>(new Automobile());
        holder3.getT();
    }
}

class Automobile {}
