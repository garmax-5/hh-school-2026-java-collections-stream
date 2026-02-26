package tasks;

import common.Area;
import common.Person;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/*
Имеются
- коллекция персон Collection<Person>
- словарь Map<Integer, Set<Integer>>, сопоставляющий каждой персоне множество id регионов
- коллекция всех регионов Collection<Area>
На выходе хочется получить множество строк вида "Имя - регион". Если у персон регионов несколько, таких строк так же будет несколько
 */
public class Task6 {

  /*
  Возможна ситуация, когда отсутствует ключ в словаре (персоне не сопоставляется регион),
  в связи с чем было принято решение пропускать такие записи.
  Так же, в связи с тем, что передается коллекция всех регионов и мы не знаем о наличии в ней дубликатов,
  было принято решение при формировании словаря учитывать возможный факт появления дубликатов
  */
  public static Set<String> getPersonDescriptions(Collection<Person> persons,
                                                  Map<Integer, Set<Integer>> personAreaIds,
                                                  Collection<Area> areas) {
    Map<Integer, Area> areasMap = areas.stream()
        .collect(Collectors.toMap(
            Area::getId,
            Function.identity(),
            (a, b) -> a
        ));
    Set<String> personDescriptions = persons.stream()
        .flatMap(person -> personAreaIds.getOrDefault(person.id(), Collections.emptySet()).stream()
            .map(areasMap::get)
            .map(area -> person.firstName() + " - " + area.getName())
        )
        .collect(Collectors.toSet());
    return personDescriptions;
  }
}
