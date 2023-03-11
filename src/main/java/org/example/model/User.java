package org.example.model;

import javax.persistence.*;
import java.util.Objects;

@Entity // аннотация, которая указывает гиберу, что этот класс связан с таблицами и нужно за ним следить
@Table(name = "user") // аннотация которая указывает гиберу с какой таблицей связан класс
public class User {
    @Id //указывает что эта часть является первичным ключом
    @GeneratedValue(strategy = GenerationType.IDENTITY) // указываем, что поле id будет увеличиваться если "Identity",
    //// то увеличивается на 1,
    // если Sequence, то сами настраиваем как генерировать значение для поля id
    // если Table, то id берется на стороне таблицы
    @Column(name = "id") // указываем имена в таблице для столбцов
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "age")
    private int age;

    // Создаем геттеры и сеттеры для всех полей, это методы для получения данных из всех полей
    // через методы(инкапсуляция), для защиты данных
    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }
// создаем пустой конструктор для юзера, нужен для того чтобы фрэймворк мог создать объект класса пустым
// и потом заполнить его
    public User() {
    }
// создаем конструктор для всех полей таблицы, нужен для объединения значений, получаемых с интерфейса и загрузки их
// в таблицу базы данных
    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }
// создаем тостринг для того чтобы при печати с объекта получать данные не в ссылочном типе, а получать значения
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
// создаем эквалс и хэшкод для id, для того чтобы при сравнении объектов он сравнивал их только по полю id
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
