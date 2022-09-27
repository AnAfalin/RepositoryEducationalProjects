import org.eclipse.collections.api.map.primitive.IntIntMap;
import org.eclipse.collections.api.set.primitive.MutableIntSet;
import org.eclipse.collections.impl.list.mutable.primitive.IntArrayList;
import org.eclipse.collections.impl.map.mutable.primitive.IntIntHashMap;
import org.eclipse.collections.impl.set.mutable.primitive.IntHashSet;

public class Main {
    public static void main(String[] args) {

        IntArrayList intArrayList = new IntArrayList(); //ArrayList<int>
        MutableIntSet intSet = new IntHashSet(); //TreeSet<int>
        IntIntMap intIntMap = new IntIntHashMap();  //Map<int, int>


        MutableIntSet setA = new IntHashSet();
        setA.add(1);
        setA.add(2);
        setA.add(3);

        MutableIntSet setB = new IntHashSet();
        setB.add(2);
        setB.add(3);
        setB.add(4);

        MutableIntSet mutableIntSet = setA.symmetricDifference(setB);
        System.out.println(mutableIntSet);

    }
}
