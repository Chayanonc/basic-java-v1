import javax.sound.sampled.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.time.LocalTime;
import java.util.Scanner;

public class AlarmClock implements Runnable {
    private final LocalTime alarmTime;
    private final String filePath;
    private final Scanner scanner;

    AlarmClock(LocalTime alarmTime, String filePath, Scanner scanner) {
        this.alarmTime = alarmTime;
        this.filePath = filePath;
        this.scanner = scanner;
    }

    @Override
    public void run() {
        while (LocalTime.now().isBefore(alarmTime)) {
            try {
                Thread.sleep(1000);
                LocalTime now = LocalTime.now();

                int hours = now.getHour();
                int minutes = now.getMinute();
                int seconds = now.getSecond();

                System.out.printf("\r%02d:%02d:%02d", hours, minutes, seconds);
            } catch (InterruptedException e) {
                System.out.println("Thread was interrupted");
            }
        }

        System.out.println();
        System.out.println("Alarm Notice");
//        Toolkit.getDefaultToolkit().beep();
        playSound(filePath);
    }

    private void playSound(String filePath) {

        try {
            File audioFile = new File(filePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();

            System.out.println("Press *Enter* to stop the alarm: ");
            scanner.nextLine();

            clip.stop();

            scanner.close();


        } catch (UnsupportedAudioFileException e) {
            System.out.println("Audio file format is not supported");
        } catch (LineUnavailableException e) {
            System.out.println("Audio is unavailable!");
        } catch (IOException e) {
            System.out.println("Error read file!");
        }
    }
}
