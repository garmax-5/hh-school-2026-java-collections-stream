package tasks;

import common.Person;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;

/*
Задача 3
Отсортировать коллекцию сначала по фамилии, по имени (при равной фамилии), и по дате создания (при равных фамилии и имени)
 */
public class Task3 {

  /*
  Возможна ситуация, когда фамилия или имя у персоны являются null (из-за чего при сортировке возникает исключение),
  в связи с этим было принято решение добавить компаратор, обрабатывающий null, помещая их в конец списка
  */
  public static List<Person> sort(Collection<Person> persons) {
    return persons.stream()
        .sorted(Comparator
            .comparing(Person::secondName, Comparator.nullsLast(String::compareTo))
            .thenComparing(Person::firstName, Comparator.nullsLast(String::compareTo))
            .thenComparing(Person::createdAt)
        )
        .toList();
  }
}
