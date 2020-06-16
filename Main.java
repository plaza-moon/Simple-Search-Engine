package search;

import java.io.*;
import java.util.LinkedList;

public class Main {
    static String importFile = "./";

    public static void main(String[] args) throws IOException {
        if (args[0].equals("--data")) {
            importFile += args[1];
        }
        File file = new File(importFile);

        BufferedReader scanner = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader scanner2 = new BufferedReader(new FileReader(file));

        LinkedList<String> list = new LinkedList<>();
        LinkedList<String> list2 = new LinkedList<>();
        LinkedList<String> list3 = new LinkedList<>();
        String st;
        int input1 = 0;
        while ((st = scanner2.readLine()) != null) {
            list2.add(st);
            list3.add(st);
            input1++;
        }
        scanner2.close();

        String[] input2 = new String[list2.size()];
        for (int i =0; i < list2.size(); i++) {
            input2[i] = list2.get(i);
        }

        for (;;) {
            System.out.println("=== Menu ===");
            System.out.println("1. Find a person");
            System.out.println("2. Print all people");
            System.out.println("0. Exit");
            String menu = scanner.readLine();
            System.out.println();

            if (menu.equals("0")) {
                System.out.println("Bye!");
                System.exit(0);
            } else if (menu.equals("2")) {
                System.out.println("=== List of people ===");
                list2.forEach(System.out::println);
                System.out.println();
            } else if (menu.equals("1")) {
                System.out.println("Select a matching strategy: ALL, ANY, NONE");
                String strategy = scanner.readLine();
                System.out.println();

                System.out.println("Enter a name or email to search all suitable people.");
                String input4 = scanner.readLine().toLowerCase();
                String[] input5 = input4.split(" ");

                int count = 0;
                boolean flag = false;
                switch (strategy) {
                    case "ALL":
                        for (int j = 0; j < input1; j++) {
                            String string = input2[j].toLowerCase();
                            String[] string1 = string.split(" ");
                                if (string1.length == 3 && input5.length == 3) {
                                    if ((input5[0].equals(string1[0]) || input5[0].equals(string1[1]) ||
                                        input5[0].equals(string1[2])) && (input5[1].equals(string1[0]) || input5[1].equals(string1[1]) ||
                                        input5[1].equals(string1[2])) && (input5[2].equals(string1[0]) || input5[2].equals(string1[1]) ||
                                            input5[2].equals(string1[2]))) {
                                        list.add(input2[j]);
                                        count++;
                                    }
                                } else if (string1.length == 3 && input5.length == 2) {
                                    if ((input5[0].equals(string1[0]) || input5[0].equals(string1[1]) ||
                                            input5[0].equals(string1[2])) && (input5[1].equals(string1[0]) || input5[1].equals(string1[1]) ||
                                            input5[1].equals(string1[2]))) {
                                        list.add(input2[j]);
                                        count++;
                                    }
                                } else if (string1.length == 3 && input5.length == 1) {
                                    if (input5[0].equals(string1[0]) || input5[0].equals(string1[1]) ||
                                        input5[0].equals(string1[2])) {
                                    list.add(input2[j]);
                                    count++;
                                    }
                                } else if (string1.length == 2 && input5.length == 3) {
                                if ((input5[0].equals(string1[0]) || input5[0].equals(string1[1])) && (input5[1].equals(string1[0]) || input5[1].equals(string1[1])) &&
                                        (input5[2].equals(string1[0]) || input5[2].equals(string1[1]))) {
                                    list.add(input2[j]);
                                    count++;
                                }
                                } else if (string1.length == 2 && input5.length == 2) {
                                    if ((input5[0].equals(string1[0]) || input5[0].equals(string1[1])) && (input5[1].equals(string1[0]) || input5[1].equals(string1[1]))) {
                                    list.add(input2[j]);
                                    count++;
                                }
                                } else if (string1.length == 2 && input5.length == 1) {
                                    if (input5[0].equals(string1[0]) || input5[0].equals(string1[1])) {
                                    list.add(input2[j]);
                                    count++;
                                }
                            }
                        }
                        break;
                    case "ANY":
                        for (int j = 0; j < input1; j++) {
                            String string = input2[j].toLowerCase();
                            String[] string1 = string.split(" ");
                            for (int m = 0; m < input5.length; m++) {
                                for (int k = 0; k < string1.length; k++) {
                                    if (input5[m].equals(string1[k])) {
                                        list.add(input2[j]);
                                        count++;
                                    }
                                }
                            }
                        }
                        break;
                    case "NONE":
                        for (int j = 0; j < input1; j++) {
                            String string = input2[j].toLowerCase();
                            String[] string1 = string.split(" ");
                            for (int m = 0; m < input5.length; m++) {
                                if (string1.length == 3) {
                                    if (input5[m].equals(string1[0]) || input5[m].equals(string1[1]) ||
                                            input5[m].equals(string1[2])) {
                                        list3.remove(input2[j]);
                                        count++;
                                        flag = true;
                                        break;
                                    }
                                } else if (string1.length == 2) {
                                    if (input5[m].equals(string1[0]) || input5[m].equals(string1[1])) {
                                        list3.remove(input2[j]);
                                        count++;
                                        flag = true;
                                        break;
                                    }
                                }
                            }
                        }
                        break;
                }
                if (!list.isEmpty()) {
                    System.out.println();
                    System.out.println(count + " persons found:");
                    list.forEach(System.out::println);
                    list.clear();
                    count = 0;
                } else if (flag = true) {
                        System.out.println();
                        System.out.println((list2.size() - count) + " persons found:");
                        list3.forEach(System.out::println);
                        list3.clear();
                        count = 0;
                } else {
                    System.out.println("No matching people found.");
                }
                System.out.println();
            } else {
                System.out.println("Incorrect option! Try again.");
                System.out.println();
            }
        }
    }
}
