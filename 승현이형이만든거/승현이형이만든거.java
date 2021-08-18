package 승현이형이만든거;

import java.util.*;
import java.util.Scanner;

/* [추상클래스]=[인터페이스] 공통된 특징
* 1. '미완성된 추상 메서드'가 존재하므로 '객체 생성 불가'
*    미완성된 추상 메서드는 반드시 자식에서 재정의(안하면 오류)
*    오류 해결방법 :1. 자식에서 재정의하거나
*              2. 자식도 추상 클래스로나 인터페이스로 만들면 됨
*               
* 2. 부모 역할 가능함
*    자식객체를 "부모타입으로 자동형변환"하여 받아들일 수 있다.
*     (예1) method(Product p){//Product의 자식객체 (Tv, Computer, Audio)->자동으로 Product로 형변환되어 대입 
*    
*           }
*    
*     (예2) Product p=new Tv();  Product p=new Computer();  Product p=new Audio();
*     
* [추상 클래스만의 특징]
* 1. 여러 메서드 중 단 1개라도 미완성된 추상 메서드가 있으면 이 클래스는 추상 클래스가 된다.
* 
* [인터페이스만의 특징]
* 1. 멤버변수(=필드)가 모두 '상수'
*    int A = 1; //public static final 생략 가능
*    
* 2. 메서드가 모두 다 '미완성된 추상 메서드'
*    void a(); //public abstract 생략
*    
*    ★예외1 : static 메서드-자체로 완성된 메서드
*             =>자식 클래스에서 재정의 못함
*             =>static 메서드는 추상 메서드가 될 수 없음
*             
*    ★예외2 : default 메서드-자체로 완성된 메서드
*             =>재정의를 원하는 자식 클래스에서만 재정의하면 됨
*/

interface ProductInter {
	int TV = 1, Computer = 2, Audio = 3;
	int Buy = 1, Refund = 2;
	
	static void buyerEnter() {
		System.out.println("구매자가 전자마트에 입장하셨습니다!");
	} // 완성된 메서드 자식클래스에서 재정의못함
	
	default void defaultmethod() {} // 완성된 메서드 자식클래스에서 재정의가능
	
	// 그외 메서드는 모두 추상메서드
	void menu(); // 추상메서드
	//Object menu(); // 아래에서 재정의 조건을 설명을 위해 추가한 코드
	void bigMenu();
	
}

abstract class Product { // 제품 - 부모 : "자식의 공통된 특성"
	
	// 부모의 필드 : 자식의 공통된 속성
	final String makeCountry = "KOREA";// 제조국가 // 자바스크립트의 const와 같다.
	int price; // 제품가격
	int bonusPoint; // 제품의 포인트
	
	// 기본생성자 Product(){super();}
	
	public Product(int price) { // 가격이 있는 제품 생성
		super();
		this.price = price;
		bonusPoint = (int)(price/10.0); // 200.0/10.0 => 20.0
	}
	
	abstract void show();
	abstract void refundShow();
	
}

class TV extends Product {
	
	// 부모로부터 상속받은 필드 + 추가 필드
	String makeCompany = "SAMSUNG";// 제품 제작 회사 
	
	// 기본생성자 TV(){super();}
	
	public TV() {
		super(200);
		bonusPoint = (int)(price/20.0); // 200.0/5.0 => 10.0
		// TODO Auto-generated constructor stub
	}

	@Override
	void show() {
		System.out.println("==========제품 정보==========");
		System.out.println("제조국 : " + makeCountry + ", 제조사 : " + makeCompany + ", 가격 : " + price + "만원, 보너스포인트 : " + bonusPoint + "\n");
	}
	
	@Override
	void refundShow() {
		System.out.println("==========제품 환불 정보==========");
		System.out.println(+ price + "만원 환불되오며, 보너스포인트는 " + bonusPoint + "점 차감됩니다!\n");
	}

	@Override
	public String toString() {
		return "TV";
	}
	
}

class Computer extends Product {
	
	// 부모로부터 상속받은 필드 + 추가 필드
	final String brand = "LG 그램";// 제품 브랜드
	
	public Computer() {
		super(100);
		// TODO Auto-generated constructor stub
	}

	@Override
	void show() {
		System.out.println("==========제품 정보==========");
		System.out.println("제조국 : " + makeCountry + ", 브랜드 : " + brand + ", 가격 : " + price + "만원, 보너스포인트 : " + bonusPoint + "\n");
	}
	
	@Override
	void refundShow() {
		System.out.println("==========제품 환불 정보==========");
		System.out.println(+ price + "만원 환불되오며, 보너스포인트는 " + bonusPoint + "점 차감됩니다!\n");
	}
	
	@Override
	public String toString() {
		return "Computer";
	}
	
}

class Audio extends Product {

	// 부모로부터 상속받은 필드 + 추가 필드 없음
	
	public Audio() {
		super(50);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	void show() {
		System.out.println("==========제품 정보==========");
		System.out.println("제조국 : " + makeCountry +  ", 가격 : " + price + "만원, 보너스포인트 : " + bonusPoint + "\n");
	}
	
	@Override
	void refundShow() {
		System.out.println("==========제품 환불 정보==========");
		System.out.println(+ price + "만원 환불되오며, 보너스포인트는 " + bonusPoint + "점 차감됩니다!\n");
	}

	@Override
	public String toString() {
		return "Audio";
	}
	
}

// 구매자
class Buyer {
	// 1. 멤버변수 = 필드 : 속성
	int money; // 돈
	int bonusPoint = 0; // 구매자의 보너스포인트
	int cntTv = 0, cntCom = 0, cntAud = 0; // ★각 제품을 몇대 보유중인지 카운팅변수
	
	// 구매자의 제품 목록(배열) , 배열은 반드시 같은 타입만(자식은 가능) 저장. 크기 고정되므로 최대크기로 선언. 추가와 삭제에 대한 코드를 직접 작성해줘야하는 번거러움이 있음. => 이 번거로움때문에 컬렉션프레임워크가 만들어짐
	//Product[] item = new Product[10]; // 최대 10개까지 제품 구매가능함. [null, null, null, ....] 기본값으로 채워짐
	List<Product> item = new ArrayList<Product>();
	// 2. 생성자 : 돈을 가진 구매자
	Buyer(int money) {
		super();
		this.money = money;
	}
	
	// 3. 메서드 : 기능
	// 제품에 대해 물어본다.
	void askBuyInfo(Product p) { // Product의 자식객체 (TV, Computer, Audio) 중 하나를 Product타입으로 자동으로 형변환되어 받아들임
		System.out.print("구매자 : " + p + "제품에 대한 정보 부탁드립니다!\n\n"); // toString 재정의 안할 시 클래스명@16진수해시코드 => 각 클래스에 toString 재정의 후 사용
		p.show();
	}
	
	void askRefundInfo(Product p) { // Product의 자식객체 (TV, Computer, Audio) 중 하나를 Product타입으로 자동으로 형변환되어 받아들임
		System.out.print("구매자 : " + p + "제품 환불대한 정보 부탁드립니다!\n\n"); // toString 재정의 안할 시 클래스명@16진수해시코드 => 각 클래스에 toString 재정의 후 사용
		p.refundShow();
	}
	
	// 제품을 산다.
	void buy(Product p) { // Product의 자식객체 (TV, Computer, Audio) 중 하나를 Product타입으로 자동으로 형변환되어 받아들임
		if (this.money < p.price) {
			System.out.println("잔액이 부족하여 " + p + "제품을 구매하실 수 없습니다!");
			return; // 해당 메서드(buy메서드)종료. != System.exit(0); // 프로그램종료
		}
		this.money -= p.price;
		this.bonusPoint += p.bonusPoint;
		switch (p.toString().toUpperCase()) {
			case "TV" : {
				cntTv++;
				break;
			}
			case "COMPUTER" : {
				cntCom++;
				break;
			}
			case "AUDIO" : { 
				cntAud++;
				break;
			}
		} // switch문 종료
		// 제품 목록(배열)에 제품을 추가
		item.add(p);
		
		System.out.println("==========구매 완료==========");
		System.out.println(p + "제품을 " + p.price + "만원에 구입하였습니다!");
		System.out.println("구매 후 잔액 : " + this.money + "만원 입니다!");
		System.out.println("제품 구매 후 보너스 포인트 : " + this.bonusPoint + "점 입니다!\n");
		
	}
	
	//환불
	void refund(Product p) {
		this.money += p.price;
		this.bonusPoint -= p.bonusPoint;
		if (p.toString().equalsIgnoreCase("tv")) cntTv--;
		if (p.toString().equalsIgnoreCase("computer")) cntCom--; 
		if (p.toString().equalsIgnoreCase("audio")) cntAud--; 
		item.remove(p);
		
		System.out.println("==========환불 완료==========");
		System.out.println(p + "제품을 환불하여 " + p.price + "만원 받고, 보너스포인트는 " + p.bonusPoint + "점 차감되었습니다!");
		System.out.println("환불 후 잔액 : " + this.money + "만원 입니다!");
		System.out.println("환불 후 보너스 포인트 : " + this.bonusPoint + "점 입니다!\n");
	}
	// 구매한 정보 요약
	void summary() {
		int sum = 0; // 구매한 제품 가격 합계
		String itemList1 = "";
		
		// 반복문을 이용해 구입한 제품의 총 가격과 목록을 만들어 출력
		int i = 0;
		if(!item.isEmpty()) {
			for (Product itemList : item) {
				
				sum += itemList.price;
				// 과제1 : "Tv,Computer" 컴마찍기 
				itemList1 = itemList1 + ((i==0) ? "" + item.get(i) : ", "  + item.get(i));
				i++;
				
			} // for문 종료
			System.out.println("==========현황==========");
			System.out.println("총 구매 가격 : " + sum + "만원 입니다!");
			System.out.println("총 구매 제품 목록 : [" + itemList1 + "] 입니다!");
			
			String itemCnt = "";
			if (cntTv > 0) itemCnt += "TV : " + cntTv + "대, ";
			if (cntCom > 0) itemCnt += "Computer : " + cntCom + "대, ";
			if (cntAud > 0) itemCnt += "Audio : " + cntAud + "대, ";
			
			// 과제2 : "Tv 1대, Computer 2대 총 3대 입니다" 같은 형식으로 출력			
			itemCnt = itemCnt.substring(0, itemCnt.length()-2); // ,뒤에 빈칸이 들어있으므로 -2 ,만 있다면 -1
			System.out.println(itemCnt + " 이므로 ");
			System.out.println("총 " + i + " 대 입니다!");
		}
		else System.out.println("구매자는 제품을 구매하지 않았습니다!");
		}
		
	// 환불전 정보 요약
	void refundList() {
		if(!item.isEmpty()) {
		String itemList1 = "";
		
		int i = 0;
		for (Product itemList : item) {
			itemList1 = itemList1 + ((i==0) ? "" + item.get(i) : ", "  + item.get(i));
			i++;
		} // for문 종료
		String itemCnt = "";
		if (cntTv > 0) itemCnt += "TV : " + cntTv + "대, ";
		if (cntCom > 0) itemCnt += "Computer : " + cntCom + "대, ";
		if (cntAud > 0) itemCnt += "Audio : " + cntAud + "대, ";
		System.out.println("**********보유 제품 목록**********");
		System.out.println("[" + itemList1 + "]");
		System.out.println(itemCnt + "총 " + i + " 대 입니다!");
		System.out.println("******************************");
		}
		else System.out.println("구매자는 제품을 구매하지 않았습니다!");
	}
}
	
public class 승현이형이만든거 implements ProductInter{
	// 필드
	
	// 기본생성자 존재 - public ProductInterfaceArray(){super();}
	
	/*
	 * 재정의 조건
	 * 1. 반드시 부모의 "리턴타입 메서드명(매개변수)" 같아야 한다.
	 * 단, JDK1.5~수정된 사항 : (예) 부모의 리턴타입(Object)을 자식클래스의 타입(Product)으로 변경할 수 있도록 허용
	 * 2. 접근제한자는 부모와 같거나 넓은 범위로
	 * 3. static 있는 메서드 -> static 없는 메서드(x)
	 *    static 없는 메서드 -> static 있는 메서드(x)
	 */
	
	//메서드
	/*
	@Override
	public ProductInterfaceArray menu() { // static 붙이면 오류
		ProductInterfaceArray obj = null;
		return obj;
	}
	*/

	@Override
	public void menu() {
		System.out.println("********************가전 제품 목록********************");
		System.out.println("1. TV             2. Computer             3.Audio");
		System.out.println("**********현재 현황을 보시려면 0번을 눌러주세요!!**********\n");
	}
	

	@Override
	public void bigMenu() {
		System.out.println();
		System.out.println("******************무엇을  도와드릴까요?******************");
		System.out.println("**********1. 구매                    2. 환불**********");
		System.out.println("***************************************************\n");
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		//ProductInterfaceArray p = new ProductInterfaceArray();
		//ProductInterfaceArray p2 = p.menu();
		
		// 1000만원을 가진 구매자 생성
		Buyer A = new Buyer(1000);
		
		// 구매자가 전자마트에 입장
		ProductInter.buyerEnter();
		
		//TV, Computer, Audio 객체 생성
		TV tv = new TV();
		Computer computer = new Computer();
		Audio audio = new Audio();
		
		String tmp = null;
		
		승현이형이만든거 pi = new 승현이형이만든거();
		while (A.money != 0) {
			pi.bigMenu();
			System.out.print("방문하신 목적을 선택해주세요. 종료는 stop : ");
			tmp = sc.next();
			
			if (tmp.equalsIgnoreCase("stop")) break;
			int num = 0;
			try {
				num = Integer.parseInt(tmp);
			} catch (NumberFormatException e) {
				// TODO 문자 입력시 재주문
				System.out.println("1 또는 2만 입력해 주세요!");
				System.out.println("입력창으로 돌아갑니다!");
				continue;
			}
			if (!(0 < num && num < 3)) {
				System.out.println("1 또는 2만 입력해 주세요!");
				System.out.println("입력창으로 돌아갑니다!");
				continue;
			}
			main : switch (num) {
				case ProductInter.Buy : 
					for (;;) {
						System.out.println();
						System.out.println("**********구 매**********");
						pi.menu();
						
						System.out.print("원하시는 가전제품 번호를 입력하세요. 종료는 Stop, 메인화면은 back : ");
						tmp = sc.next();
						
						if (tmp.equalsIgnoreCase("stop")) {
							A.summary();
							System.exit(0);
						}
						if (tmp.equalsIgnoreCase("back")) break main;
						num = 0;
						try {
							num = Integer.parseInt(tmp);
						} catch (NumberFormatException e) {
							// TODO 문자 입력시 재주문
							System.out.println("0 에서 3만 입력해 주세요!");
							System.out.println("입력창으로 돌아갑니다!");
							continue;
						}
						if (!(0 <= num && num <= 3)) {
							System.out.println("0 에서 3만 입력해 주세요!");
							System.out.println("입력창으로 돌아갑니다!");
							continue;
						}
						
						switch (num) {
							case ProductInter.TV : A.askBuyInfo(tv);
								System.out.print("구매 하시겠습니까?(Y,N)");
								tmp = sc.next();
								
								if (tmp.equalsIgnoreCase("y")) {
									if (A.money >= tv.price) A.buy(tv); 
									else System.out.println("잔액이 부족합니다!");
									continue;
								}
								
								if (tmp.equalsIgnoreCase("n")) {
									System.out.println("구매화면으로 돌아갑니다!");
									continue;
								}
								
								if (!(tmp.equalsIgnoreCase("y") && tmp.equalsIgnoreCase("n"))) {
									System.out.println("잘못 입력하셨습니다!");
									System.out.println("구매화면으로 돌아갑니다!");
									continue;
								}
								
							case ProductInter.Computer : A.askBuyInfo(computer); 
								System.out.print("구매 하시겠습니까?(Y,N)");
								tmp = sc.next();
								
								if (tmp.equalsIgnoreCase("y")) {
									if (A.money >= computer.price) A.buy(computer); 
									else System.out.println("잔액이 부족합니다!");
									continue;
								}
								
								if (tmp.equalsIgnoreCase("n")) {
									System.out.println("구매화면으로 돌아갑니다!");
									continue;
								}
								
								if (!(tmp.equalsIgnoreCase("y") && tmp.equalsIgnoreCase("n"))) {
									System.out.println("잘못 입력하셨습니다!"); 
									System.out.println("구매화면으로 돌아갑니다!");
									continue;
								}
								
							case ProductInter.Audio : A.askBuyInfo(audio);
								System.out.print("구매 하시겠습니까?(Y,N)");
								tmp = sc.next();
								
								if (tmp.equalsIgnoreCase("y")) {
									if (A.money >= audio.price) A.buy(audio); 
									else System.out.println("잔액이 부족합니다!");
									continue;
								}
								
								if (tmp.equalsIgnoreCase("n")) {
									System.out.println("구매화면으로 돌아갑니다!");
									continue;
								}
								
								if (!(tmp.equalsIgnoreCase("y") && tmp.equalsIgnoreCase("n"))) {
									System.out.println("잘못 입력하셨습니다!"); 
									System.out.println("구매화면으로 돌아갑니다!");
									continue;
								}
								
							case 0 : 
								A.summary(); 
								continue;
						} // 구매menu switch문 종료
					} // for문 종료
				case ProductInter.Refund : 
					for (;;) {
						System.out.println();
						System.out.println("**********환 불**********");
						pi.menu();
						
						System.out.print("환불하실 제품을 선택해 주세요. 종료는 stop, 메인화면은 back : ");
						tmp = sc.next();
						
						if (tmp.equalsIgnoreCase("stop")) {
							A.summary();
							System.exit(0);
						}
						if (tmp.equalsIgnoreCase("back")) break main;
						num = 0;
						try {
							num = Integer.parseInt(tmp);
						} catch (NumberFormatException e) {
							// TODO 문자 입력시 재주문
							System.out.println("0 에서 3만 입력해 주세요!");
							System.out.println("입력창으로 돌아갑니다!");
							continue;
						}
						if (!(0 <= num && num <= 3)) {
							System.out.println("0 에서 3만 입력해 주세요!");
							System.out.println("입력창으로 돌아갑니다!");
							continue;
						}
						switch (num) {
						case ProductInter.TV : A.askRefundInfo(tv);
							System.out.print("환불 하시겠습니까?(Y,N)");
							tmp = sc.next();
							
							if (tmp.equalsIgnoreCase("y")) {
								if (A.cntTv > 0) A.refund(tv); 
								else System.out.println("TV제품을 가지고 계시지 않습니다!");
								continue;
							}
							
							if (tmp.equalsIgnoreCase("n")) {
								System.out.println("환불화면으로 돌아갑니다!");
								continue;
							}
							
							if (!(tmp.equalsIgnoreCase("y") && tmp.equalsIgnoreCase("n"))) {
								System.out.println("잘못 입력하셨습니다!"); 
								System.out.println("환불화면으로 돌아갑니다!");
								continue;
							}
						case ProductInter.Computer : A.askRefundInfo(computer);
							System.out.print("환불 하시겠습니까?(Y,N)");
							tmp = sc.next();
							
							if (tmp.equalsIgnoreCase("y")) {
								if (A.cntCom > 0) A.refund(computer); 
								else System.out.println("Computer제품을 가지고 계시지 않습니다!");
								continue;
							}
							
							if (tmp.equalsIgnoreCase("n")) {
								System.out.println("환불화면으로 돌아갑니다!");
								continue;
							}
							
							if (!(tmp.equalsIgnoreCase("y") && tmp.equalsIgnoreCase("n"))) {
								System.out.println("잘못 입력하셨습니다!"); 
								System.out.println("환불화면으로 돌아갑니다!");
								continue;
							}
						case ProductInter.Audio : A.askRefundInfo(audio);
							System.out.print("환불 하시겠습니까?(Y,N)");
							tmp = sc.next();
							
							if (tmp.equalsIgnoreCase("y")) {
								if (A.cntAud > 0) A.refund(audio); 
								else System.out.println("Audio제품을 가지고 계시지 않습니다!");
								continue;
							}
							
							if (tmp.equalsIgnoreCase("n")) {
								System.out.println("환불화면으로 돌아갑니다!");
								continue;
							}
							
							if (!(tmp.equalsIgnoreCase("y") && tmp.equalsIgnoreCase("n"))) {
								System.out.println("잘못 입력하셨습니다!"); 
								System.out.println("환불화면으로 돌아갑니다!");
								continue;
							}
						case 0 : 
							A.refundList(); 
							continue;
						}
					} // 환불menu switch문 종료
			} // bigMenu switch문 종료
		} // while문 종료
		A.summary();
		
	} // 메인문
	
} // 클래스문
/*******************************************************************************************************/