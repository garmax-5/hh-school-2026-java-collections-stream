package tasks;

import common.Person;
import common.PersonService;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/*
Задача 1
Метод на входе принимает List<Integer> id людей, ходит за ними в сервис
(он выдает несортированный Set<Person>, внутренняя работа сервиса неизвестна)
нужно их отсортировать в том же порядке, что и переданные id.
Оценить асимптотику работы
 */
public class Task1 {

  private final PersonService personService;

  public Task1(PersonService personService) {
    this.personService = personService;
  }

  /* Асимптотическая сложность
   1. Преобразование Set в Map: O(n + 1) = O(n)
        Проход по каждому элементу O(n)
        Вставка в Map 0(1)
   2. Проход по списку ID: O(m + 1) = O(m)
        Проход по каждому элементу O(m)
        Поиск в Map по по ключу 0(1)
   В результате асимптотическая сложность: O(n + m),
   если в списке personIds нет дубликатов и сервис выдал все Person по id,
   то n = m, тогда сложность = O(n)
  */
  public List<Person> findOrderedPersons(List<Integer> personIds) {
    Set<Person> persons = personService.findPersons(personIds);
    Map<Integer, Person> personsMap = persons.stream()
        .collect(Collectors.toMap(
                Person::id,
                Function.identity()
        ));
    return personIds.stream()
        .map(personsMap::get)
        .toList();
  }
}
