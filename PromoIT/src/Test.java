import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;

public class Test {
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static void main(String[] args) {
		System.out.println("Введите команду: #read/#write/#date/#exit");
		Scanner scan = new Scanner(System.in);

		while (true) {
			String command = scan.nextLine();
			switch (command) {
			case ("#read"):
				read();
				break;
			case ("#write"):
				write();
				break;
			case ("#date"):
				date();
				break;
			case ("#exit"):
				System.out.println("Завершение работы программы.");
				return;
			}
		}
	}

	public static void write() {
		System.out.print("Введите заметку:");

		try (BufferedWriter bw = new BufferedWriter(new FileWriter("save2.txt", true))) {
			Scanner scan = new Scanner(System.in);
			String user = scan.nextLine();
			String date = sdf.format(new Date(System.currentTimeMillis()));
			bw.write(date + ": " + user);
			bw.newLine();
			System.out.println("\nУспешно! Для продолжения введите команду:");
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		}

	}

	public static void read() {
		try (BufferedReader br = new BufferedReader(new FileReader("save2.txt"))) {
			String s;
			while ((s = br.readLine()) != null) {
				System.out.println(s);
			}
			System.out.println("\nВсе записи выведены! Для продолжения введите команду:");
			
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		}
	}

	public static void date() {
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		try (BufferedReader br = new BufferedReader(new FileReader("save2.txt"))) {
			Scanner scan = new Scanner(System.in);
			System.out.print("Введите год:");
			int year = scan.nextInt();
			System.out.print("Введите месяц:");
			int mother = scan.nextInt();
			System.out.print("Введите день:");
			int day = scan.nextInt();
			LocalDate date = LocalDate.of(year, mother, day);
			String s;
			System.out.println("\nНайденные записи в книжке:");
			while ((s = br.readLine()) != null) {
				String[] parts = s.split(" ", 2);
				LocalDate Date = LocalDate.parse(parts[0], format);
				if (Date.equals(date)) {
					System.out.println(s);
				}
			}
			System.out.println("\nДля продолжения введите команду:");
			
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		}

	}

}
