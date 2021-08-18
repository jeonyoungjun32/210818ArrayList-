/*
 * ==============================================08/12 과제======배열을 ArrayList로 변경============================
 */
package 여썜이만든거;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/* [추상클래스]=[인터페이스] 공통된 특징
* 1. '미완성된 추상 메서드'가 존재하므로 '객체 생성 불가'
*    미완성된 추상 메서드는 반드시 자식에서 재정의(안하면 오류)
*    오류 해결방법 :1. 자식에서 재정의하거나
*               2. 자식도 추상 클래스나 인터페이스로 만들면 됨
*               
* 2. 부모 역할 가능함
*    자식객체를 "부모타입으로 자동형변환"하여 받아들일 수 있다.
*     (예1) method(Product p){//Product의 자식객체 (Tv, Computer, Audio)->자동으로 Product로 형변환되어 대입 
*    
*           }
*    
*     (예2) Product p=new Tv();  Product p=new Computer();  Product p=new Audio();
* ============================================================================================================
* [추상 클래스만의 특징]
* 1. 여러 메서드 중 단 1개라도 미완성된 추상 메서드가 있으면 이 클래스는 추상 클래스가 된다.
* ------------------------------------------------------------------------------------------------------------
* [인터페이스만의 특징]
* 1. 멤버변수(=필드)가 모두 '상수'
*    int A = 1; //public static final 생략 가능
* 2. 메서드가 모두 다 '미완성된 추상 메서드'
*    void a(); //public abstract 생략
*    
*    ★예외1 : static 메서드-자체로 완성된 메서드
*             =>자식 클래스에서 재정의 못함
*             
*    ★예외2 : default 메서드(정석401p)-자체로 완성된 메서드
*             =>재정의를 원하는 자식 클래스에서만 재정의하면 됨
*/
interface ProductInter{
	int TV=1, COMPUTER=2, AUDIO=3, REFUND=4;//배열을 ArrayList로 변경
	
	static void buyerEnter(){
		System.out.println("구매자가 전자마트에 입장하셨습니다.");
	}
	
	default void defaultmethod(){}
	//그외 메서드는 모두 추상 메서드
	void menu();
	
}

abstract class Product{//제품-부모 : "자식의 공통된 특성"
	//부모의 필드 : 자식의 공통된 속성
	final String makeCountry="Korea";	//제조국가. const(자바스크립트)
	int price;//제품가격
	int bonusPoint;//제품의 포인트
	
	//기본생성자  Product(){super();}
	public Product(int price) {//가격이 있는 제품 생성
		super();
		this.price = price;
		bonusPoint = (int) (price/10.0);
	}
	
	//미완성된 메서드 : 필드의 값을 출력하기위해서 만듬
	abstract void show();//반드시 자식이 재정의해서 사용
	
	
}

class Tv extends Product{
	//부모로부터 상속받은 필드+추가 필드
	String makeCompany = "삼성";
	
	//기본생성자Tv(){super();}
	public Tv() {
		super(200);
		bonusPoint = (int) (price/5.0);
	}

	@Override
	void show() {
		System.out.println("TV제조국 :"+makeCountry+", TV제조사 :"+makeCompany+", TV 가격 :"+price+", TV 보너스포인트 :"+bonusPoint);
	}

	@Override
	public String toString() {
		return "TV";
	}
	
	
}

class Computer extends Product{
	//부모로부터 상속받은 필드+추가 필드
	final String brand = "LG 그램";
	
	public Computer() {
		super(100);
	}

	@Override
	void show() {
		System.out.println("Computer 제조국 :"+makeCountry+", Computer 브랜드 :"+brand+", Computer 가격 :"+price+", Computer 보너스포인트 :"+bonusPoint);		
	}
	@Override
	public String toString() {
		return "com";
	}
	
}

class Audio extends Product{
	//부모로부터 상속받은 필드+추가 필드
	public Audio() {
		super(50);
	}
	@Override
	void show() {
		System.out.println("TV제조국 :"+makeCountry+", TV 가격 :"+price+", TV 보너스포인트 :"+bonusPoint);
	}
	@Override
	public String toString() {
		return "Audio";
	}
	
}
//구매자
class Buyer{ 
	//1.멤버변수=필드 : 속성
	int money;//돈
	int bonusPoint=0;//구매자의 보너스 점수
	
	//구매자의 제품목록(배열)
	//배열 : 반드시 같은 타입만 저장, 크기가 고정되므로 최대크기로 선언. 추가와 삭제에 대한 코드를 직접 작성해줘야 하는 번거로움
//	Product[] item = new Product[10];//최대 10개까지의 제품 구매가능함.[null,null,null,...]기본값으로 채워짐
	/* Product타입만 저장하는 ArrayList
	 * 크기 자동으로 커짐. 추가와 삭제에 대한 메서드가 있음
	 * 
	 * List 인터페이스 : 부모 역할 가능 - index -> 순서가있음,중복허용
	 * -ArrayList 클래스 : 끝에 추가하거나 삭제에 삭제에 사용하면 빠름,중간에 추가나 삭제를하면 처리시간이 오래걸림, 싱글쓰레드에서만 사용
	 * -LinkeList 클래스 : 중간에 추가나 삭제가 용이하고 처리시간이 빠름
	 * -Vector 클래스 : 멀티쓰레드에서 사용, 특징은 ArrayList와 같음
	 */
	List<Product> item = new ArrayList<Product>();//ArrayList<> Product생략가능
	
	//2.생성자 : 돈을 가진 구매자
	public Buyer(int money) {//1000
		super();
		this.money = money;
	}

	//3.메서드 : 기능
	//제품에 대해 물어보는 기능
	void askInfo(Product p){//Product의 자식 객체(TV,Computer,Audio) 중 하나를 Product타입으로 자동형변환되어서 받아들임
		System.out.println(p+"제품에 대한 정보 부탁드립니다.");//클래스명@16진수해쉬코드
		//->재정의된toString()이 호출됨
		p.show();
	}
	
	//제품 구매기능
//	int i=0;//멤버변수 : 제품을 item배열에 추가할 때마다 1씩 증가
	void buy(Product p){//Product의 자식 객체(TV,Computer,Audio) 중 하나를 Product타입으로 자동형변환되어서 받아들임
		if(this.money < p.price ) {
			System.out.println("잔액이 부족이 부족하여"+p+"제품을 구매할 수 없습니다.");
			return;//해당메서드(buy메서드)종료. System.exit(0);//프로그램을 종료시킴
		}
		this.money-=p.price;//구매자의 돈은 제품의 가격만큼 감소
		this.bonusPoint += p.bonusPoint;//구매자의 보너스포인트는 증가
		
		//제품목록(배열)에 제품을 추가 후 i값을 1증가
//		item[i++]=p;
		item.add(p);
		System.out.println(p+"를 구입하셨습니다.");
	}
	//구매한 물품 환불
	void refund(Product p) {
		//해당 제품을 목록에서 찾아서 있으면 제거 후 true, 찾았는데 없으면 false
		if(item.remove(p)) {
			this.money += p.price;//환불받은 돈을 구매자에게 다시 추가
			this.bonusPoint -= p.bonusPoint;//
			System.out.println(p+"를(을) 반품하셨습니다.");
		}else System.out.println("구입하신 제품은 저희 제품 목록 중에 없습니다.");
	}
	
	//구매한 정보 요약
	void summary() {
		int sum = 0;//구매한 제품 가격 합계
		String itemlist="";//구매한 제품목록들 (예)""+"Tv"+","+"Computer"=>"Tv,Computer"
		
		int cntTv =0, cntCom=0, cntAud=0;//각 제품을 몇 대 샀는지 카운팅하는 변수
		
		
		//반복문을 이요해서 구입한 제품을 '총가격'과 '목록'을 만들어 출력
		/*
		for(i=0;i<item.length;i++) {//i : index + 총갯수
			if(item[i] == null) break;
			
			sum+=item[i].price;
			
			//과제-1 : ,출력 마지막 ,는 출력안함(Tv,Computer) 
//			if(item[i+1] != null)itemlist += item[i].toString()+",";
//			else itemlist += item[i].toString();

			//방법 : 조건연산자 (조건문)?참:거짓
			itemlist += ((i==0)?""+item[i] : ","+item[i]);
			
			switch(item[i].toString().toUpperCase()) {
			case "TV"       : cntTv++; break; 
			case "COMPUTER" : cntCom++; break; 
			case "AUDIO"    : cntAud++; break;
			}
			
		}
	*/
		 int totalCnt=0;
		for(int i=0;i<item.size();i++) {
			Product p = item.get(i);
			sum += p.price;
			//방법-1
//			itemlist += ((i==0)?""+p.toString() : ","+p.toString());
//			itemlist += ((i==0)?""+p : ","+p);
			
			switch(p.toString().toUpperCase()) { //toUpperCase = 대문자인식
			case "TV"       : cntTv++; totalCnt++; break; 
			case "COM" : cntCom++; totalCnt++; break; 
			case "AUDIO"    : cntAud++; totalCnt++; break;
			}
		}
		
		
		//출력
		if(item.size() != 0) {//제품을 구매하면 
			System.out.println("*******구입한 제품목록과 총금액*******");
//			System.out.println("구입하신 제품은 ["+itemlist+"]입니다.");
			//방법-2 : 문자열 사이에 구분자(,)를 두어 문자열 결합
			//string.join (구분  , 결합할 문자)
			itemlist = String.join(",", item.toString());//"TV" "Computer" -> "TV,Computer" 
			System.out.println("구입하신 제품은 "+itemlist+"입니다.");
			System.out.println("구입하신 제품의 총 금액은"+sum+"만원입니다.");
			//과제-2 : TV 1대, Computer2대 총 3대입니다.
			String itemCnt="";
			if(cntTv > 0) itemCnt += "TV :" + cntTv +"대,";
			if(cntCom > 0) itemCnt += "Computer :" + cntCom +"대,";
			if(cntAud > 0) itemCnt += "Audio :" + cntAud +"대,";
			
			itemCnt = itemCnt.substring(0, itemCnt.length()-1);
			System.out.println(itemCnt+"이므로");
			System.out.println("총"+totalCnt+"대입니다.");
			
		}else {
			System.out.println("구매자는 제품을 구매하지 않았습니다.");
		}
		
	}
	
	
	
}



//1개의 파일에 여러 개의 클래스가 있다면 public을 붙일 수 있는 클래스는 단 1개뿐
//main() 실행용 클래스 = 파일명
public class ProductInterfaceArrayList_after  implements ProductInter{
	
	//필드
	
	//기본 생성자 존재
	
	/*
	 * @Override 재정의 조건
	 * 1. 반드시 부모의 리턴타입 메서드명(매개변수)이 같아야 함.
	 *    단, JDK1.5~이후 수정된 사항 : (예)부모의 리턴타입(Object)을 자식클래스의 타입(ProductInter
	 * 2. 접근제한자는 부모와 같너나 넓은 범위로 
	 * 3. static 있는 메서드 - static 없는 메서드(x)
	 *    static 없는 메서드 - static 있는 메서드(x)
	 * 4. 예외는 조상클래의 메서드보다 많이 선언할 수 없다.
	 */
	//메서드
	@Override
	public void menu() {//static을 붙이면 오류
		System.out.println();
		System.out.println("***************가전제품목록***************");
		System.out.println("1. TV               2.Computer        3.Audio      4.REFUND");
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		//1000만원 가진 구매자 생성
		Buyer b = new Buyer(1000);
		
		//구매자가 전자마트에 입장
		ProductInter.buyerEnter();
		
		//Tv,Computer 객체생성
		Tv tv =new Tv();
		Computer com = new Computer();
		Audio audio = new Audio();
		
		String tmp = null;
		
		ProductInterfaceArrayList_after pi = new ProductInterfaceArrayList_after();
		while(b.money != 0) {
			pi.menu();
			
			System.out.println("원하는 가전제품 번호를 입력하세요. 종료는 stop>");
			tmp = sc.next();//
			
			if(tmp.equalsIgnoreCase("stop")) break;
			
			int num = 0;
			try {
			    num = Integer.parseInt(tmp);
			}catch(NumberFormatException e) {
				System.out.println("잘못된 값을 입력하셨습니다.");
				System.out.println(e.getMessage());
				continue;
			}
			if(!(1<=num && num<=4)) {
				System.out.println("잘못된 번호입니다. 다시 입력해 주세야");
				continue;
			}
			
			switch(num) {
			case ProductInter.TV : b.askInfo(tv); 
										b.buy(tv);
										break;
			case ProductInter.COMPUTER : b.askInfo(com); 
										b.buy(com);
									    break; 
			case ProductInter.AUDIO :b.askInfo(audio); 
			                         b.buy(audio);
			                         break;
			case ProductInter.REFUND : System.out.println("환불할 제품 입력(Tv, Coputer, Audio) > ");
										String refund  = sc.next();
									switch (refund.toLowerCase()) {
									case "tv": b.refund(tv);break;
									case "com": b.refund(com);break;
									case "audio": b.refund(audio);break;
									} break;
					}//switch				
			
		}//while

		System.out.println("\n***************가전제품 판매를 종료합니다.");
		
		b.summary();
		
		
		
	}//main


}











