package edu.kh.jdbc.member.view;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import edu.kh.jdbc.member.model.service.MemberService;
import edu.kh.jdbc.member.vo.Member;

/** 회원 메뉴 입/출력 클래스
 * @author user1
 *
 */
public class MemberView {

	
	// 3. 
		private Scanner sc = new Scanner(System.in);
		
	// 회원 관련 서비스를 제공하는 객체 생성
	private MemberService service = new MemberService();
	
    //	0920 1교시 5..
	// 로그인 회원 정보 저장용 변수
	private Member loginMember =null;
	
	/**
	 * 회원 기능 메뉴
	 */
	/**
	 * @param loginMember(로그인된 회원 정보)
	 */
	public void memberMenu(Member loginMember) {
										//		0920 1교시 5..
		int input = -1;					// 위에 loginMember에다가 필드로 넘겨서(복사) 
										// 전체에서 쓸수잇게!!
									// 전달 받은 로그인 회원 정보를 필드에 저장
										this.loginMember = loginMember;
		do {
			try {
				System.out.println("\n***** 회원 기능 *****");
				System.out.println();
				System.out.println("1. 내 정보 조회"); 
				System.out.println("2. 회원 목록 조회(아이디, 이름, 성별)");
				System.out.println("3. 내 정보 수정(이름, 성별)");
			    System.out.println("4. 비밀번호 변경(현재 비밀번호, 새 비밀번호, 새 비밀번호 확인)");
			    System.out.println("5. 회원 탈퇴");
			    System.out.println("0. 메인메뉴로 이동");
			    
			    System.out.print("메뉴 선택 : ");
			    input = sc.nextInt();
			    sc.nextLine();
			    
			    System.out.println();
			    switch(input) {
			    //	0920 1교시 5..

			    case 1 : selectMyInfo(); break;
//				0920 2교시 1...
			    case 2 : selectAll(); break;
			    case 3 : break;
			    case 4 : break;
			    case 5 : break;
			    case 0 : System.out.println("[메인 메뉴로 이동합니다.]"); break;
			    default:  System.out.println("메뉴에 작성된 번호만 입력해주세요.");
			    }
			    System.out.println();
			    

				
			} catch(InputMismatchException e) {
				System.out.println("\n<<입력 형식이 올바르지 않습니다.>>");
				sc.nextLine(); // 입력 버퍼에 남아있는 버퍼제거
			}
		}while(input != 0);
	}

//	0920 1교시 6..
	
	/**
	 * 내 정보 조회
	 */
	private void selectMyInfo() {
		System.out.println("\n[내 정보 조회]\n");
		
		System.out.println("회원 정보 : " + loginMember.getMemberNo());
		System.out.println("아디디 : " + loginMember.getMemberId());
		System.out.println("이름 : " + loginMember.getMemberName());
		System.out.print("성별 : " );
		if(loginMember.getMemberGender().equals("M")) {
			System.out.println("남");
		} else {
			System.out.println("여");
		}
		System.out.println("가입일 : " + loginMember.getEnrollDate());
	}
	//	0920 2교시 1...
	/**
	 * 회원 목록 조회
	 */
	private void selectAll() {
		System.out.println("\n[회원 목록 조회]\n");
		
		//DB에서 회원 목록 조회(탈퇴 회원 미포함)
		// + 가입일 내림차순
		
		try {
//			0920 2교시 2... dbeaver에서 SQL문 작성
			// 3..
			// 회원목록 조회 서비스 호출 후 결과 반환 받기
			List<Member> memberList = service.selectAll();
			
			// 조회 결과가 있으면 모두 출력
			// 없으면 "조회 결과가 없습니다." 출력
			if(memberList.isEmpty()) {
				System.out.println("조회 결과가 없습니다.");
			}else {
				for(Member mem : memberList) {
					System.out.printf("아이디 : %s 유저 이름 : %s 성별 : %s",
					mem.getMemberId(),mem.getMemberName(),mem.getMemberGender());
					System.out.println();
//					System.out.println( mem.getMemberId());
//					System.out.println( mem.getMemberName());
//					System.out.println(  mem.getMemberGender());
				}
			}
			
		} catch(Exception e) {
			System.out.println("\n<<회원 목록 조회 중 예외 발생>>\n");
			e.printStackTrace();
		}
		
		
	}

		
}
