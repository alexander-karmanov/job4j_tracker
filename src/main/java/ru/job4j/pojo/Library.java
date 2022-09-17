package ru.job4j.pojo;

public class Library {
    public static void main(String[] args) {
        Book book = new Book("Brave New World", 350);
        Book book1 = new Book("The Master and Margarita", 300);
        Book book2 = new Book("Fathers and Sons", 150);
        Book book3 = new Book("Clean code", 500);
        Book[] books = new Book[4];
        books[0] = book;
        books[1] = book1;
        books[2] = book2;
        books[3] = book3;

        for (int index = 0; index < books.length; index++) {
            Book bk = books[index];
            System.out.println(bk.getTitle() + " - " + bk.getPages());
        }
        books[0] = book3;
        books[3] = book;

        for (int index = 0; index < books.length; index++) {
            Book bk = books[index];
            System.out.println(bk.getTitle() + " - " + bk.getPages());
        }

        for (int index = 0; index < books.length; index++) {
            Book bk = books[index];
            if (bk.getTitle() == "Clean code") {
                System.out.println(bk.getTitle() + " - " + bk.getPages());
            }
        }
    }
}
