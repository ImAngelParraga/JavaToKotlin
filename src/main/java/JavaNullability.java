import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class JavaNullability {
    // Si a es null, tenemos NullPointerException
    int stringLength(String a) {
        return a.length();
    }

    int stringLengthSafe(String a) {
        if (a != null) {
            return a.length();
        } else {
            return 0;
        }
    }

    int stringLengthOptional(String a) {
        return Optional.ofNullable(a).map(String::length).orElse(0);
    }

    record Trabajador(Departamento departamento, String nombre) {}
    record Departamento(Trabajador jefe) {}
    String workerBossName(Trabajador trabajador) {
        return Optional.ofNullable(trabajador)
                .map(Trabajador::departamento)
                .map(Departamento::jefe)
                .map(Trabajador::nombre)
                .orElse(null);
    }

    void getOrNull(final List<Integer> numbers, final Integer i) {
        // return numbers.get(i); would throw exception if i is greater than numbers length

        final Integer iValue;
        if (numbers != null && i < numbers.size()) {
            iValue = numbers.get(i);
        } else {
            iValue = null;
        }

        assert numbers != null;
        final var max = numbers.stream().max(Comparator.naturalOrder()).orElse(null);
    }

    void filterNotNull() {
        List<Integer> nullableList = List.of(1, 2, null, 4);

        List<Integer> intList = nullableList.stream()
                .filter(Objects::nonNull)
                .toList();

        System.out.println(intList);
    }
}
