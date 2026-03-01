package tasks;

import common.ApiPersonDto;
import common.Person;
import common.PersonConverter;

import java.util.List;
import java.util.Map;

/*
Задача 5
Расширим предыдущую задачу.
Есть список персон, и словарь сопоставляющий id каждой персоны и id региона
Необходимо выдать список персон ApiPersonDto, с правильно проставленными areaId
Конвертер одной персоны дополнен!
 */
public class Task5 {

  private final PersonConverter personConverter;

  public Task5(PersonConverter personConverter) {
    this.personConverter = personConverter;
  }

  /*
  Возможна ситуация, когда отсутствует ключ в словаре (персоне не сопоставляется регион),
  в связи с чем было принято решение в таких ситуациях использовать перегруженный метод convert(Person person)
  */
  public List<ApiPersonDto> convert(List<Person> persons, Map<Integer, Integer> personAreaIds) {
    return persons.stream()
        .map(person -> personConverter.convert(person, personAreaIds.get(person.id())))
        .toList();
  }
}
