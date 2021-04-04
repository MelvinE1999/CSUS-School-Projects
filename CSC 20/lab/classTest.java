class A { int x = 1; }
class B extends A { }
class C extends B { int x = 2;}
public class classTest {
public static void main(String[] args) {
A w = new A(); System.out.println(w.x);
B u = new B(); System.out.println(u.x);
C v = new C(); System.out.println(v.x);
A [] a = { new A(), new B(), new C()};
for (int i=0; i<3; ++i)
System.out.println(a[i].x);
}
}
