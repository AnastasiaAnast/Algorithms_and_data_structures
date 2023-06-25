// Необходимо реализовать метод разворота связного списка (двухсвязного или односвязного на выбор).

import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
        SingleLinkList<Contact> contactList = new SingleLinkList<>();

        contactList.addToEnd(new Contact(275, "Иванов Александр Иванович", "+7999111111"));
        contactList.addToEnd(new Contact(354, "Петров Иван Иванович", "+79992222222"));
        contactList.addToEnd(new Contact(687, "Васин Иван Михайлович", "+79993333333"));
        contactList.addToEnd(new Contact(745, "Глебов Глеб Сергеевич", "+79994444444"));
        contactList.addToEnd(new Contact(834, "Демин Арсений Григорьевич", "+79995555555"));

        for (Object contact : contactList) {
            System.out.println(contact);
        }
        contactList.reverse();

        System.out.println("_-#-_-#-_-#-_-#-_-#-_-#-_-#-_-#-_-#-_-#-_-#-_");

        for (Object contact : contactList) {
            System.out.println(contact);
        }
    }

    static class Contact {
        int id;
        String name;
        String phone;

        public Contact(int id, String name, String phone) {
            this.id = id;
            this.name = name;
            this.phone = phone;
        }

        @Override
        public String toString() {
            return "Contact{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", phone='" + phone + '\'' +
                    '}';
        }
    }

    // Класс списка

    public static class SingleLinkList<T> implements Iterable {
        ListItem<T> head;
        ListItem<T> tail;

        @Override
        public Iterator iterator() {
            return new Iterator<T>() {
                ListItem<T> current = head;

                @Override
                public boolean hasNext() {
                    return current != null;
                }

                @Override
                public T next() {
                    T data = current.data;
                    current = current.next;
                    return data;
                }
            };
        }

        // Класс отдельного элемента

        private static class ListItem<T> {
            T data;
            ListItem<T> next;
        }

        // Проверка - пустое начало?
        public boolean isEmpty() {
            return head == null;
        }

        // Заполнение списка
        public void addToEnd(T item) {

            // Выделение памяти для списка
            ListItem<T> newItem = new ListItem<>();
            newItem.data = item;

            // Если начало и конец пусты - присваеваем newItem
            if (isEmpty()) {
                head = newItem;
                tail = newItem;

            // Если начало не пусто - передаём элементу адрес и ставим его в конец
            } else {
                tail.next = newItem;
                tail = newItem;
            }
        }

        // Метод разворота списка
        public void reverse() {
            if (!isEmpty() && head.next != null) { // Если не пусто и начало не равно нулю
                tail = head;
                ListItem<T> current = head.next;
                head.next = null;
                while (current != null) {
                    ListItem<T> next = current.next;
                    current.next = head;
                    head = current;
                    current = next;
                }
            }
        }
    }
}