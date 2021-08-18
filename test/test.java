package test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

interface ProductInter {
	int TV = 1, COMPUTER = 2, AUDIO = 3, REFUND = 4;

	static void buyerEnter() {
		System.out.println("전자마트 입장합니다.");
	}
}

abstract class Product {
	final String makeCountry = "Korea";
	int price;
	int bonusPoint;

	public Product(int price) {
		super();
		this.price = price;
	}

	abstract void show();

}

class TV extends Product {
	public TV() {
		super(200);
		bonusPoint = (int) (price / 5.0);
	}

	String makeCompany = "삼성";

	@Override
	void show() {
		System.out.println("Product [ 제조국=" + makeCountry + ", 가격=" + price + ", 보너스 포인트=" + bonusPoint + "]"
				+ ", 제조회사=" + makeCompany);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "TV";
	}

}

class Computer extends Product {
	public Computer() {
		super(100);
		// TODO Auto-generated constructor stub
	}

	final String brand = "LG 그램";

	@Override
	void show() {
		System.out.println(
				"Product [ 제조국=" + makeCountry + ", 가격=" + price + ", 보너스 포인트=" + bonusPoint + "]" + ", 제품명" + brand);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Computer";
	}

}

class Audio extends Product {

	public Audio() {
		super(50);
		// TODO Auto-generated constructor stub
	}

	@Override
	void show() {
		System.out.println("Product [ 제조국=" + makeCountry + ", 가격=" + price + ", 보너스 포인트=" + bonusPoint + "]");
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Audio";
	}

}

class Buyer {// 구매자
	int money;
	int bonusPoint;

	List<Product> ArrayList = new ArrayList<Product>();

	public Buyer(int money) {
		super();
		this.money = money;
	}

	void askInfo(Product p) {
		System.out.println("구매할수 있는 제품을 보여주세요");
		p.show();
	}

	void buy(Product p) {
		if (money < p.price) {
			System.out.println("잔액이 부족합니다");
			return;
		} else {
			money -= p.price;
			bonusPoint += p.bonusPoint;
			ArrayList.add(p);// 1.돈빠지고 2.보너스포인트받고 3.물건추가
			System.out.println(p + " 제품을 구매했습니다!");
		}

	}

	// 환불
	void refund(Product p) {
		if (ArrayList.remove(p)) {
			System.out.println("환불할 제품을 입력해주세요");
			money += p.price;
			bonusPoint -= p.bonusPoint;
			System.out.println(p + " 제품을 반품했습니다");
		} else {
			System.out.println("반품 할께 없습니다");
		}
	}

	// 구매한 정보 요악
	void summary() {
		int sum = 0, cntTv = 0, cntCom = 0, cntAud = 0, totalCnt = 0;
		String itemlist;
		for (Product p : ArrayList) {
			sum += p.price;
			switch (p.toString().toLowerCase()) {
			case "tv":
				cntTv++;
				totalCnt++;
				break;
			case "computer":
				cntTv++;
				totalCnt++;
				break;
			case "audio":
				cntTv++;
				totalCnt++;
				break;
			}
		}
		if (ArrayList.size() != 0) {// 0이 아니면
			System.out.println("*******구입한 제품목록과 총금액*******");

			itemlist = String.join(",", ArrayList.toString());

			System.out.println("구입하신 제품은 " + itemlist + " 입니다!");
			System.out.println("구입하신 제품의 총 금액은 " + sum + "입니다!");
			String itemCnt = "";
			if (cntTv > 0)
				itemCnt += "TV " + cntTv + "대,";
			if (cntCom > 0)
				itemCnt += "TV " + cntCom + "대,";
			if (cntAud > 0)
				itemCnt += "TV " + cntAud + "대,";

			// 대 끝에있는 , 을 지우기위해
			itemCnt = itemCnt.substring(0, itemCnt.length() - 1);
			System.out.println(itemCnt + " 이므로");
			System.out.println("총 :" + totalCnt + " 대입니다!");
		} else {
			System.out.println("제품을 구매하지 않았습니다");
		}
	}

}

public class test {
	public void menu() {
		System.out.println();
		System.out.println("***************가전제품목록***************");
		System.out.println("1. TV               2.Computer        3.Audio      4.REFUND");

	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		Buyer b = new Buyer(1000);

		ProductInter.buyerEnter();

		TV tv = new TV();
		Computer com = new Computer();
		Audio audio = new Audio();

		test pi = new test();

		while (b.money != 0) { // 돈이 0이 아니면
			pi.menu();
			System.out.println("가전제품 번호 입력하시오");
			String tmp = sc.next();

			if (tmp.equalsIgnoreCase("stop")) {
				break;
			}
		
			int num;
			try {
				num = Integer.parseInt(tmp);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
				continue;
			}

			if (!(1 <= num && num <= 4)) {
				System.out.println("잘못된 번호입니다.");
				continue;
			}

			switch (num) {// askInfo : 구매할수 있는 제품을 보여주세요");
			// if (money < p.price) {
			// System.out.println("잔액이 부족합니다");
			case 1:
				b.askInfo(tv);
				b.buy(tv);
				break;
			case 2:
				b.askInfo(com);
				b.buy(com);
				break;
			case 3:
				b.askInfo(audio);
				b.buy(audio);
				break;
			case 4:
				System.out.println("환불할 제품");
				int refund = Integer.parseInt(sc.next());
				switch (refund) {
				case 1:
					b.refund(tv);
					break;
				case 2:
					b.refund(com);
					break;
				case 3:
					b.refund(audio);
					break;

				default:
					break;
				}

			}

		}
		System.out.println("\n 가전제품 반매종료");
		b.summary();
	}
	
}
