import javax.sound.sampled.*;
import java.io.*;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class Main {
    static String getHangmanArt(int wrongGuesses) {
        return switch (wrongGuesses) {
            case 0 -> """
                    
                    
                    
                    """;
            case 1 -> """
                      0
                    
                    
                    """;
            case 2 -> """
                      0
                      |
                    
                    """;
            case 3 -> """
                      0
                     /|
                    
                    """;
            case 4 -> """
                      0
                     /|\\
                    
                    """;
            case 5 -> """
                      0
                     /|\\
                     /
                    """;
            case 6 -> """
                      0
                     /|\\
                     / \\
                    """;
            default -> "";
        };

    }

    public static void main(String[] args) {
//Chapter Final [JAVA ALARM CLOCK]

        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalTime alarmTime = null;
        String filePath = "sound/s1.mp3";

        System.out.println(LocalTime.now());
        while (alarmTime == null) {
            try {
                System.out.print("Enter an alarm time (HH:MM:SS): ");
                String inputTime = scanner.nextLine();

                alarmTime = LocalTime.parse(inputTime, formatter);
                System.out.println("Alarm set for " + alarmTime);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid format HH:MM:SS");
            }
        }

        AlarmClock alarmClock = new AlarmClock(alarmTime, filePath, scanner);
        Thread alarmThread = new Thread(alarmClock);

        alarmThread.start();


/*Chapter 43 [Multithreading]

        Thread thread1 = new Thread(new MyRunnable("Ping"));
        Thread thread2 = new Thread(new MyRunnable("Pong"));

        System.out.println("Game start!");

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            System.out.println("Main thread was interrupted");
        }

        System.out.println("Game over!");


/*Chapter 42 [Threading]
        Scanner scanner = new Scanner(System.in);

        MyRunnable myRunnable = new MyRunnable();
        Thread thread = new Thread(myRunnable);

        thread.setDaemon(true);
        thread.start();

        System.out.println("You have 5 sec to enter your name");
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        System.out.println("Hello " + name);

        scanner.close();



Chapter 41 [Enums]
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a day of the week: ");
        String input = scanner.nextLine().toUpperCase();

        try {
            Day day = Day.valueOf(input);

            System.out.println(day);
            System.out.println(day.getDayNumber());

            switch (day) {
                case MONDAY, TUESDAY, WENESDAY, THURSDAY, FRIDAY -> System.out.println("Is is a weekday");
                case SATURDAY, SUNDAY -> System.out.println("Weekend");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error Input");
        }

Chapter 40 [Hashmap]
        HashMap<String, Double> map = new HashMap<>();
        map.put("apple", 100.99);
        map.put("orange", 78.99);
        map.put("banana", 34.99);

        System.out.println(map.containsKey("banana"));
        System.out.println(map.get("apple"));

        for (String key : map.keySet()) {
            System.out.println(key+ " : "+ map.get(key));
        }

Chapter 39 [Generics]

        Box<Integer> box = new Box<>();

        box.setItem(2);
        System.out.println(box.getItem());

        Product<String, Double> product1 = new Product<>("Banana", 20.2);
        Product<String, Integer> product2 = new Product<>("Banana", 2);

        System.out.println(product1.getPrice());
        System.out.println(product2.getPrice());


Chapter 38 [JAVA COUNTDOWN TIMER PROGRAM]

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number of seconds to countdown from: ");
        int res = scanner.nextInt();

        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            int count = res;

            @Override
            public void run() {
                System.out.println(count);
                count--;
                if (count < 0) {
                    System.out.println("Yeah!");
                    timer.cancel();
                }
            }
        };

        timer.scheduleAtFixedRate(task, 0, 1000);


Chapter 37 [Timer, TimerTask]
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            int count = 3;

            @Override
            public void run() {
                System.out.println("Hello");
                count--;
                if(count <= 0) {
                    System.out.println("Task completed!");
                    timer.cancel();
                }
            }
        };

        timer.schedule(task,0, 1000);


Chapter 36 [Anonymous class]
        Dog dog1 = new Dog();
        Dog dog2 = new Dog() {
            @Override
            void speak() {
                System.out.println("*Hongggg*");
            }
        };

        dog1.speak();
        dog2.speak();

Chapter 35 [Date/Time]
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        LocalDateTime dateTime = LocalDateTime.now();

        Instant instant = Instant.now();

        System.out.println(instant);
        System.out.println(date);
        System.out.println(time);
        System.out.println(dateTime);

//        Custom Format

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String newDateTime = dateTime.format(formatter);

        System.out.println(newDateTime);

        LocalDate fixDate = LocalDate.of(2024, 12, 25);
        System.out.println(fixDate);
        LocalDateTime fixDateTime = LocalDateTime.of(2025, 1, 3, 23, 2, 1);
        System.out.println(fixDateTime);

        LocalDateTime date1 = LocalDateTime.of(2025, 1, 20, 0, 2, 1);
        LocalDateTime date2 = LocalDateTime.of(2025, 4, 3, 23, 2, 1);

        System.out.println(date1);
        System.out.println(date2);

        if (date1.isBefore(date2)) {
            System.out.println(date1 + " is earlier than " + date2);
        }

Chapter 34 [JAVA HANGMAN GAME]

        String filePath = "test.txt";
        ArrayList<String> words = new ArrayList<String>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = reader.readLine()) != null) {
                words.add(line.toLowerCase());
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Reader error");
        }

        System.out.println(words);
        Random random = new Random();
        String word = words.get(random.nextInt(words.size()));


        Scanner scanner = new Scanner(System.in);
        ArrayList<Character> wordState = new ArrayList<Character>();
        int wrongGuesses = 0;

        for (int i = 0; i < word.length(); i++) {
            wordState.add('_');
        }

        boolean isCorrect = false;

        while (wrongGuesses < 6) {
            System.out.println("***** Welcome to JAVA HANGMAN *****");
            System.out.println(getHangmanArt(wrongGuesses));
            System.out.print("Word: ");

            for (char c : wordState) {
                System.out.print(c + " ");
            }
            System.out.println();
            System.out.print("Guess a letter: ");
            char guess = scanner.next().toLowerCase().charAt(0);

            if (word.indexOf(guess) >= 0) {
                for (int i = 0; i < word.length(); i++) {
                    if (word.charAt(i) == guess) {
                        wordState.set(i, guess);
                    }
                }
                System.out.println("Is correct");

                System.out.println(wordState.toString());

                if (!wordState.contains('_')) {
                    System.out.println("Very good");
                    System.out.println("The word is: " + word);
                    break;
                }
            } else {
                wrongGuesses++;
            }
        }

        if (wrongGuesses >= 6) {
            System.out.println(getHangmanArt(wrongGuesses));
            System.out.println("GAME OVER!");
            System.out.println("The word was: " + word);
        }

        scanner.close();

Chapter 33 [Audio]
        String filePath = "sound/s1.wav";

        File file = new File(filePath);

        try (Scanner scanner = new Scanner(System.in);) {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);

            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);

            String response = "";

            while (!response.equals("Q")) {
                System.out.println("P = Play");
                System.out.println("S = Stop");
                System.out.println("R = Reset");
                System.out.println("Q = Quit");

                System.out.println("Enter your choice");
                response = scanner.nextLine().toUpperCase();

                switch (response) {
                    case "P" -> clip.start();
                    case "S" -> clip.stop();
                    case "R" -> clip.setMicrosecondPosition(0);
                    case "Q" -> clip.close();
                    default -> System.out.println("Invalid choice");
                }
            }

            System.out.println("No problem");
        } catch (FileNotFoundException e) {
            System.out.println("Could not locate file");
        } catch (LineUnavailableException e) {
            System.out.println("Unable to access audio resource");
        } catch (UnsupportedAudioFileException e) {
            System.out.println("Audio file is not supported");
        } catch (IOException e) {
            System.out.println("Something went wrong");
        } finally {
            System.out.println("Bye!");
        }

Chapter 32 [File Read]
        String filePath = "test.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

        } catch (FileNotFoundException e) {
            System.out.println("Could not locate file");
        } catch (IOException e) {
            System.out.println("Something went wrong");
        }

Chapter 31 [File Write]
        try (FileWriter writer = new FileWriter("test.txt")) {
            writer.write("I Link pizza!");
            System.out.println("File has been written");

        } catch (FileNotFoundException e) {
            System.out.println("Could not locate file location");
        } catch (IOException e) {
            System.out.println("Could not write file");

        }

Chapter 30 [Exception]
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter a number: ");
            int number = scanner.nextInt();
            System.out.println(number);

        } catch (InputMismatchException e) {
            System.out.println("That was not a number!");

        } catch (ArithmeticException e) {
            System.out.println("You can not divide by zero");

        } catch (Exception e) {
            System.out.println("Something went wrong!");
        } finally {
            System.out.println("This always execute");
        }
Chapter 29 [ArrayList]
        ArrayList<Integer> list = new ArrayList<>();

        list.add(3);
        list.add(2);
        list.add(1);

        System.out.println(list);

        Collections.sort(list);

        System.out.println(list);
Chapter 28 [Aggregation]
        Book book1 = new Book("The Hell", 1000000);
        Book book2 = new Book("The 2", 1000);
        Book book3 = new Book("The King", 1322);

        Book[] books = {book1, book2, book3};

        Library libraries = new Library("NYC", 1897, books);

        libraries.displayInfo();
Chapter 27 [GETTERS, SETTERS]
        Car car = new Car("Ford", "Blue", 1000);

        car.setPrice(3000);
        System.out.println(car.getPrice());
*/


    }
}
