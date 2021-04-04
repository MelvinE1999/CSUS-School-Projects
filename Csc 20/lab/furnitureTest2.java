abstract class Furniture { abstract void prnt();}
class Recliner extends Furniture {
void prnt() { System.out.println("I'm a recliner");}
}
class LaZBoy extends Recliner {
void prnt() { System.out.println("I'm a lazboy");}
}
public class furnitureTest2 {
public static void main(String[] args) {
Furniture [] A = { new Recliner(), new Recliner(), new LaZBoy()};
for (int i=0; i<3; ++i)
A[i].prnt();
}
}