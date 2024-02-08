package ru.job4j.gc.leak;

import java.util.Random;
import java.util.Scanner;

public class Menu {
    public final Integer addPost = 1;

    public final Integer addManyPosts = 2;

    public final Integer showAllPosts = 3;

    public final Integer deletePost = 4;

    public final String select = "Выберите меню";

    public final String count = "Выберите количество создаваемых постов";

    public final String textOfPost = "Введите текст";

    public final String idForDelete = "Все посты удалены";

    public final String exit = "Конец работы";

    public final String menu = """
                Введите 1 для создание поста.
                Введите 2, чтобы создать определенное количество постов.
                Введите 3, чтобы показать все посты.
                Введите 4, чтобы удалить все посты.
                Введите любое другое число для выхода.
            """;

    public static void main(String[] args) {
        Random random = new Random();
        UserGenerator userGenerator = new UserGenerator(random);
        CommentGenerator commentGenerator = new CommentGenerator(random, userGenerator);
        Scanner scanner = new Scanner(System.in);
        PostStore postStore = new PostStore();
        Menu menu = new Menu();
        menu.start(commentGenerator, scanner, userGenerator, postStore);
    }

    private void start(CommentGenerator commentGenerator, Scanner scanner, UserGenerator userGenerator, PostStore postStore) {
        boolean run = true;
        while (run) {
            System.out.println(menu);
            System.out.println(select);
            int userChoice = Integer.parseInt(scanner.nextLine());
            System.out.println(userChoice);
            if (addPost == userChoice) {
                System.out.println(textOfPost);
                String text = scanner.nextLine();
                userGenerator.generate();
                commentGenerator.generate();
                postStore.add(new Post(text, commentGenerator.getComments()));
            } else if (addManyPosts == userChoice) {
                System.out.println(textOfPost);
                String text = scanner.nextLine();
                System.out.println(count);
                String count = scanner.nextLine();
                for (int i = 0; i < Integer.parseInt(count); i++) {
                    createPost(commentGenerator, userGenerator, postStore, text);
                }
            } else if (showAllPosts == userChoice) {
                PostStore postSt = new PostStore();
                System.out.println(postSt.getPosts());
            } else if (deletePost == userChoice) {
                System.out.println(idForDelete);
                postStore.removeAll();
            } else {
                run = false;
                System.out.println(exit);
            }
        }
    }

    private void createPost(CommentGenerator commentGenerator,
                                   UserGenerator userGenerator, PostStore postStore, String text) {
        userGenerator.generate();
        commentGenerator.generate();
        postStore.add(new Post(text, commentGenerator.getComments()));
    }
}
