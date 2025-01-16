import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("ALL")
public class SyntaxJava {
    public void simpleTest() {
        String mutable = "mutable";
        mutable = "mutado";

        final String inmutable = "inmutable";

        record Person(String name, Integer age) {}

        final Person person = new Person("John", 30);

        String name = person.name;
        final String message = "Hello, " + name + "! You've " + (20 + 10) + " years old";
        final String messageFormat = String.format("Hello, %s! You've %s years old", name, 20 + 10);

        System.out.println(message);

        final String ifVariable;
        if (person.name.equals("John")) {
            ifVariable = "yes";
        } else {
            ifVariable = "no";
        }
    }

    public void collections() {
        /*
        Aquí dejo un enlace a un artículo en medium.com con muchas implementaciones de List indicando si son mutables
         o no: https://medium.com/javarevisited/how-do-you-know-if-a-java-collection-is-mutable-or-immutable-b397dfc5d231
         */

        // Lista mutable e inmutable son el mismo tipo List.
        final List<Integer> inmutableList = List.of(1, 2, 3, 4, 5);
        //inmutableList.remove(1); Está permitido pero da error al ejecutarlo

        // No se puede saber si una lista es mutable o no
        final List<Integer> mutableList = new ArrayList<>(List.of(1, 2, 3, 4, 5));
        mutableList.remove(1);

        final List<Integer> times2 = mutableList.stream().map( element -> element * 2).toList();
    }
}
