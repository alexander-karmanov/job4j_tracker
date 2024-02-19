package ru.job4j.cache.menu;

import ru.job4j.cache.DirFileCache;
import java.util.Scanner;

public class Emulator {

   public static void main(String[] args) {
        final String MENU = """
                Введите 1 для работы с кэшем.
                Введите 2 для выхода.
            """;
        System.out.println(MENU);
        boolean run = true;
        Scanner scanner = new Scanner(System.in);
        while (run) {
            int userChoice = Integer.parseInt(scanner.nextLine());
            if (userChoice == 1) {
                System.out.println("Укажите директорию");
                String cacheDir = scanner.nextLine();
                System.out.println("Укажите файл");
                String file = scanner.nextLine();
                /* "Names.txt"; */
                /* "src/main/java/ru/job4j/cache/files/"; */
                DirFileCache dirFileCache = new DirFileCache(cacheDir);
                dirFileCache.get(file);
            } else if (userChoice == 2) {
                run = false;
                System.out.println("Выход");
            }
        }
    }
}
