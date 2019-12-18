//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.company;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Main {
	public Main() {
	}

	public static ArrayList<Request> done = new ArrayList();
	public static Login log = new Login();
	public static MainWindow mw = new MainWindow();
	public static Editor ed = new Editor();
	public static TradeSystem ts = new TradeSystem();

	static void menu() {
		System.out.println("Меню программы:");
		System.out.println("1.  Регистрация");
		System.out.println("2.  Войти");
		System.out.println("3.  Добавить заявку");
		System.out.println("4.  Вывести список пользователей");
		System.out.println("5.  Вывести список заявок на продажу");
		System.out.println("6.  Вывести список заявок на покупку");
		System.out.println("7.  Вывести список исполненных заявок");
		System.out.println("8.  Провести торги");
		System.out.println("9.  Ввести тестовые данные");
		System.out.println("111. Повторный вывод меню");
		System.out.println("0. Завершение программы");
	}

	public static void main(String[] args) {

//		mw.setVisible(true);
		log.setVisible(true);
//		new Admin();

		ts.addUser("admin", "admin", "admin");
//		ts.setCurrentUser(new Client("admin", "admin", "admin"));
		menu();

		int command;
		label70:
		do {
			Scanner in = new Scanner(System.in);
			System.out.print("\nВведите команду ");
			command = in.nextInt();
			Iterator var6;
			Request rq;
			String pt;
			String l;
			String p;
			switch(command) {
				case 0:
					System.out.print("program ended");
					break;
				case 1:
					in.nextLine();
					System.out.println("Регистрация в системе");
					System.out.println("Введите новое имя");
					pt = in.nextLine();
					System.out.println("Введите новый логин");
					l = in.nextLine();
					System.out.println("Введите новый пароль");
					p = in.nextLine();
					ts.addUser(pt, l, p);
					break;
				case 2:
					in.nextLine();
					System.out.println("Вход в систему");
					System.out.println("Введите логин");
					pt = in.nextLine();
					System.out.println("Введите пароль");
					p = in.nextLine();
					if (ts.findUser(pt, p)) {
						System.out.println("Вы успешно вошли под именем " + ts.getCurrentUser().getName());
					} else {
						System.out.println("Неверный логин иили пароль");
					}
					break;
				case 3:
					if (ts.getCurrentUser().getName() == "admin") {
						System.out.println("Администратор не может участвовать в торгах");
					} else {
						in.nextLine();
						System.out.println("Создание заявки");
						System.out.println("Введите название товара");
						pt = in.nextLine();
						System.out.println("Введите цену");
						int pe = in.nextInt();
						System.out.println("Введите количество");
						int c = in.nextInt();
						System.out.println("Введите тип предложения (0 - покупка; 1 - продажа)");
						int t = in.nextInt();
						if (t == 0) {
							ts.buyerRequests.add(new Request(ts.getCurrentUser(), pt, pe, c, t));
						} else {
							ts.sellerRequests.add(new Request(ts.getCurrentUser(), pt, pe, c, t));
						}
						System.out.println("Заявка успешно добавлена");
					}
					break;
				case 4:
					System.out.println("Список пользователей");
					var6 = ts.users.iterator();

					while(true) {
						if (!var6.hasNext()) {
							continue label70;
						}

						Client cl = (Client)var6.next();
						System.out.println(cl.getName());
					}
				case 5:
					System.out.println("Список заявок на продажу");
					var6 = ts.sellerRequests.iterator();

					while(true) {
						if (!var6.hasNext()) {
							continue label70;
						}

						rq = (Request)var6.next();
						System.out.println("\tтовар:\t" + rq.getProduct() + "\tцена:\t" + rq.getPrice() + "\tкол-во:\t" + rq.getCount() + "\tпродавец:\t" + rq.getRequester().getName());
					}
				case 6:
					System.out.println("Список заявок на покупку");
					var6 = ts.buyerRequests.iterator();

					while(true) {
						if (!var6.hasNext()) {
							continue label70;
						}

						rq = (Request)var6.next();
						System.out.println("\tтовар:\t" + rq.getProduct() + "\tцена:\t" + rq.getPrice() + "\tкол-во:\t" + rq.getCount() + "\tпокупатель:\t" + rq.getRequester().getName());
					}
				case 7:
					System.out.println("Список исполненных заявок");
					var6 = done.iterator();

					while(true) {
						if (!var6.hasNext()) {
							continue label70;
						}

						rq = (Request)var6.next();
						if (rq.getType() == 0) {
							System.out.print("Покупка ");
						} else {
							System.out.print("Продажа ");
						}

						System.out.println("\tтовар:\t" + rq.getProduct() + " цена:\t" + rq.getPrice() + " исполнитель:\t" + rq.getRequester().getName());
					}
				case 8:
					System.out.println("Результаты торгов");
					if (ts.getCurrentUser().getName().equals("admin")) {
						done.addAll(ts.processRequests());
					} else {
						System.out.println("Торги может провести только администратор");
					}
					break;
				case 9:
					ts.addUser("u1", "1", "1");
					ts.findUser("1", "1");
					ts.sellerRequests.add(new Request(ts.getCurrentUser(), "p2", 10, 1, 1));
					ts.sellerRequests.add(new Request(ts.getCurrentUser(), "p3", 10, 1, 1));
					ts.sellerRequests.add(new Request(ts.getCurrentUser(), "p4", 10, 1, 1));
					ts.sellerRequests.add(new Request(ts.getCurrentUser(), "p5", 10, 1, 1));
					ts.sellerRequests.add(new Request(ts.getCurrentUser(), "p6", 10, 1, 1));
					ts.addUser("u2", "2", "2");
					ts.findUser("2", "2");
					ts.buyerRequests.add(new Request(ts.getCurrentUser(), "p2", 8, 1, 0));
					ts.buyerRequests.add(new Request(ts.getCurrentUser(), "p3", 11, 1, 0));
					ts.buyerRequests.add(new Request(ts.getCurrentUser(), "p4", 10, 1, 0));
					ts.addUser("u3", "3", "3");
					ts.findUser("3", "3");
					ts.buyerRequests.add(new Request(ts.getCurrentUser(), "p2", 9, 1, 0));
					ts.buyerRequests.add(new Request(ts.getCurrentUser(), "p3", 13, 1, 0));
					ts.buyerRequests.add(new Request(ts.getCurrentUser(), "p4", 10, 1, 0));
					break;
				case 111:
					menu();
					break;
				default:
					System.out.print("wrong command number");
			}
		} while(command != 0);

	}
}
