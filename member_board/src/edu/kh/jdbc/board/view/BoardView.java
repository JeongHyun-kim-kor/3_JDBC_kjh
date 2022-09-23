package edu.kh.jdbc.board.view;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import edu.kh.jdbc.board.model.service.BoardService;
import edu.kh.jdbc.board.model.service.CommentService;
import edu.kh.jdbc.board.model.vo.Board;
import edu.kh.jdbc.board.model.vo.Comment;
import edu.kh.jdbc.main.view.MainView;

public class BoardView {

	private Scanner sc = new Scanner(System.in);
	
	private BoardService bService = new BoardService();
	
	private CommentService cService = new CommentService();
	
	/**
	 *  게시판 기능 메뉴 화면
	 */
	public void boardMenu() {
		
		int input = -1;
		
		do {
			
			try {
				System.out.println("\n===== 게시판 기능 =====\n");
				System.out.println("1. 게시글 목록 조회");
				System.out.println("2. 게시글 상세 조회( + 댓글 기능)");
				System.out.println("3. 게시글 작성");
				System.out.println("4. 게시글 검색");
				System.out.println("0. 로그인 메뉴로 이동");
				
				System.out.print("\n 메뉴 선택 : ");
				input = sc.nextInt();
				sc.nextLine();
				
				System.out.println();
				
				switch(input) {
				case 1: selectAllBoard(); break; // 게시글 목록 조회
				case 2: selectBoard();		break; // 게시글 상세 조회
				case 3: 		break;
				case 4: 		break;
				case 0: System.out.println("[로그인 메뉴로 이동합니다.]")  ;		break;
				default : System.out.println("메뉴에 작성된 번호만 입력해주세요.");
				}
				
				System.out.println();
				
			}catch (InputMismatchException e) {
				System.out.println("\n<입력 형식이 올바르지 않습니다.>");
				sc.nextLine(); // 입력버퍼에 남아있는 잘못된 문자열 제거
				
				
			}
			
			
			
			
			
			
		} while(input !=0);
		
		
		
	}

	

	/**
	 * 게시글 목록 조회
	 */
	private void selectAllBoard() {
		System.out.println("\n[게시글 목록 조회]\n");
		
		try {
			
			List<Board> boardList = bService.selectAllBoard();
			// -> DAO에서 new ArrayList<>(); 구문으로 인해 반환되는 조회결과는
			// null이 될 수 없다.!!
		
			if(boardList.isEmpty()) { // 조회 결과가 없을 경우
				System.out.println("조회된 게시글이 없습니다.");
				
			} else {
				for(Board b : boardList) {
					
					// 3 | 샘플 제목3[4] | 유저삼 | 3시간전 | 10
					System.out.printf("%d | %s[%d] | %s | %s | %d\n",
						b.getBoardNo(), b.getBoardTitle(), b.getCommentCount(),
						b.getMemberName(),b.getCreateDate(),b.getReadCount());
				}
			}
			
			
		}catch (Exception e) {
			System.out.println("\n<게시글 목록 조회 중 예외 발생>\n");
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 게시글 상세 조회
	 */
	private void selectBoard() {
		// TODO Auto-generated method stub
		System.out.println("\n[게시글 상세 조회]\n");
		
		try {
			System.out.print("게시글 번호 입력 : ");
			int boardNo = sc.nextInt();
			sc.nextLine();
			
			//게시글 상세 조회 서비스 호출 후 결과 반환 받기
			Board board = bService.selectBoard(boardNo, MainView.loginMember.getMemberNo());
											//게시글 번호ㅡ              로그인한 회원의 회원번호
											//                     -> 자신의 글은 글 조회수 증가 X
	         if (board != null) {
	             System.out.println("--------------------------------------------------------");
	             System.out.printf("글번호 : %d \n제목 : %s\n", board.getBoardNo(), board.getBoardTitle());
	             System.out.printf("작성자 : %s | 작성일 : %s  \n조회수 : %d\n", 
	                   board.getMemberName(), board.getCreateDate(), board.getReadCount());
	             System.out.println("--------------------------------------------------------\n");
	             System.out.println(board.getBoardContent());
	             System.out.println("\n--------------------------------------------------------");

	          
	             // 댓글 목록
	             if(!board.getCommentList().isEmpty()) {
	                for(Comment c : board.getCommentList()) {
	                   System.out.printf("댓글번호: %d   작성자: %s  작성일: %s\n%s\n",
	                         c.getCommentNo(), c.getMemberName(), c.getCreateDate(), c.getCommentContent());
	                   System.out.println(" --------------------------------------------------------");
	                }
	             }
	             // 0923 1교시 1. 
	             // 댓글 등록, 수정, 삭제
	             // 게시글 수정/삭제 메뉴
	             subBoardMenu(board);
	             
	             
	          } else {
	             System.out.println("[\n해당 번호의 게시글이 존재하지 않습니다.]\n");
	          }
			
		}catch (Exception e) {
			System.out.println("\n[게시글 상세 조회 중 예외 발생]\n");
		}
		
	}
	// 0923 1교시 1. 
	/** 게시글 상세 조회시 출력되는 서브메뉴
	 * @param board(상세조회된 게시글 + 작성자 번호 + 댓글 목록)
	 */
	private void subBoardMenu(Board board) {
		
		
		try { 
			System.out.println("1) 댓글 등록");
			System.out.println("2) 댓글 수정"); 	//0923 2교시 4. 

			System.out.println("3) 댓글 삭제");	// 0923 3교시 5?
			System.out.println("0) 게시판 메뉴로 돌아가기");
			
			System.out.print("\n 서브 메뉴 선택 : ");
			int input = sc.nextInt();
			sc.nextLine();
			
			// 0923 1교시 2(2).
			// 로그인한 회원의 회원 번호 미리 기입
			int memberNo = MainView.loginMember.getMemberNo();
			
			switch(input) {
			case 1: insertComment(board.getBoardNo(), memberNo ); break; // 0923 1교시 2(1).
			case 2: updateComment(board.getCommentList(), memberNo); break; //0923 2교시 4. 
			case 3: deleteComment( board.getCommentList(), memberNo); break; // 0923 3교시  5?
			case 0: System.out.println("\n[게시판 메뉴로 돌아갑니다...]\n"); break;
			
			default : System.out.println("\n[메뉴에 작성된 번호만 입력해주세요.]\n");
			}
			
			//0923 2교시 3. 재귀호출
			
			// 댓글 등록, 수정 , 삭제 선택 시
			// 각각의 서비스 메서드 종료 후 다시 서브메뉴 메서드 호출
	if(input >0) {
		
		try {
            board = bService.selectBoard(board.getBoardNo(), 						MainView.loginMember.getMemberNo());
   
               			System.out.println("--------------------------------------------------------");
               System.out.printf("글번호 : %d | 제목 : %s\n", board.getBoardNo(), 						board.getBoardTitle());
               System.out.printf("작성자ID : %s | 작성일 : %s | 조회수 : %d\n", 
                     board.getMemberName(), board.getCreateDate().toString(), 						board.getReadCount());
               			System.out.println("--------------------------------------------------------");
               System.out.println(board.getBoardContent());
               			System.out.println("--------------------------------------------------------");
   
            
               // 댓글 목록
               if(!board.getCommentList().isEmpty()) {
                  for(Comment c : board.getCommentList()) {
                     System.out.printf("댓글번호: %d   작성자: %s  작성일: %s\n%s\n",
                       c.getCommentNo(), c.getMemberName(), c.getCreateDate(), 							c.getCommentContent());
                     			System.out.println("--------------------------------------------------------");
                  }
               }
            }catch (Exception e) {
               e.printStackTrace();
            }
			
			subBoardMenu(board);
		}
			

			
			
			
		} catch (InputMismatchException e) {
			System.out.println("\n<입력 형식이 올바르지 않습니다.>");
			sc.nextLine(); // 입력버퍼에 남아있는 잘못된 문자열 제거
			
		}
		
		
	} // subBoardMenu 종료


	// 0923 1교시 2(3).

	



	/** 댓글 등록
	 * @param boardNo
	 * @param memberNo
	 */								// 변수명 달라도 된다. 자료형이 중요
	private void insertComment(int bNo, int mNo) {
		
		try {
			System.out.println("\n[댓글 등록]\n");
			
			//0923 2교시 2.
			// 내용 입력 받기
			String content = inputContent();
			
			//0923 2교시 2. 삭제@@
//			String content = ""; // 빈 문자열
//			
//			String input = null; // 참조하는 객체가 없음
//			
//			System.out.println("입력 종료 시 ($exit) 입력");
//			
//			while(true) {
//				input = sc.nextLine();
//				
//				if(input.equals("$exit")) {
//					break;
//				} else {
//				// 입력된 내용을 content에 누적
//				content += input + "\n";
//					
//				}
				
			
			
			// DB INSERT시 필요한 값을 하나의 Comment 객체에 저장
			Comment comment = new Comment();
			comment.setCommentContent(content);
			comment.setBoardNo(bNo);
			comment.setMemberNo(mNo);
			
			// 댓글 삽입 서비스 호출 추 결과 반환 받기

			// 0923 1교시 2(4).
			int result = cService.insertComment(comment);
			
			if(result > 0) {
				System.out.println("\n[댓글 등록 성공]\n");
			} else {
				System.out.println("\n[댓글 등록 실패]\n");

			}
			
		} catch(Exception e) {
			System.out.println("\n[댓글 등록 중 예외 발생]\n");
			e.printStackTrace();
		}
		
	
}

	
	
	//0923 2교시 2.
	/** 댓글?? 내용 입력
	 * @return content
	 */
	private String inputContent() {
		String content = ""; // 빈 문자열
		
		String input = null; // 참조하는 객체가 없음
		
		System.out.println("입력 종료 시 ($exit) 입력");
		
		while(true) {
			input = sc.nextLine();
			
			if(input.equals("$exit")) {
				break;
			} else {
			// 입력된 내용을 content에 누적
			content += input + "\n";
				
			}
		}
		return content;	
	}
	
	
	
	//0923 2교시 4. 
	/** 댓글 수정
	 * @param commentList
	 * @param memberNo
	 */
	private void updateComment(List<Comment> commentList, int memberNo) {

		// 댓글 번호를 입력 받아
		// 1) 해당 댓글이 commentList에 있는지 검사
		// 2) 있다면 해당 댓글이 로그인한 회원이 작성한 글인지 검사
		// 내가 쓴 글이면서 (memberNO0, 리스트에 있는가?(commentList)
		
		try {
			System.out.println("\n[댓글 수정]\n");
			
			System.out.print("수정할 댓글 번호 입력 : ");
			int commentNo = sc.nextInt();
			sc.nextLine();
			
			boolean flag = true;
			
			for(Comment c : commentList) {
				
				if(c.getCommentNo() == commentNo) { // 댓글 번호 일치
					flag = false;
					
					if(c.getMemberNo() == memberNo) { // 회원 번호 일치
						
						//0923 3교시 1. 
						// 수정할 댓글 내용 입력 받기
						String content = inputContent();
						
						// 댓글 수정 서비스 호출
						//0923 3교시 2. 
						int result = cService.updateComment(commentNo, content); 						

						
						if(result > 0) {
							System.out.println("\n[댓글 수정 성공]\n");
						} else {
							System.out.println("\n[댓글 수정 실패...]\n");

						}
						
					} else {
						System.out.println("\n[자신의 댓글만 수정할 수 있습니다.]\n");
					}
					
					break; // 효율 증가( 더 이상의 검사는 불필요)
				}
				
			} // for end
			
			if(flag ) {
				System.out.println("\n[번호가 일치하는 댓글이 없습니다.]\n");
			}
			
			
		} catch(Exception e) {
			System.out.println("\n<<댓글 수정 중 예외 발생>>\n");
		}
		
	}
	
	
	
	/** 댓글 삭제
	 * @param commentList
	 * @param memberNo
	 */
	private void deleteComment(List<Comment> commentList, int memberNo) {
		
		// 댓글 번호를 입력 받아
		// 1) 해당 댓글이 commentList에 있는지 검사
		// 2) 있다면 해당 댓글이 로그인한 회원이 작성한 글인지 검사
		// 내가 쓴 글이면서 (memberNO0, 리스트에 있는가?(commentList)
		
		try {
			System.out.println("\n[댓글 삭제]\n");
			
			System.out.print("삭제할 댓글 번호 입력 : ");
			int commentNo = sc.nextInt();
			sc.nextLine();
			
			boolean flag = true;
			
			for(Comment c : commentList) {
				
				if(c.getCommentNo() == commentNo) { // 댓글 번호 일치
					flag = false;
					
					if(c.getMemberNo() == memberNo) { // 회원 번호 일치
						
						// 정말 삭제?  < if문 >
						// y인 경우 댓글 삭제 서비스 호출
						
						
						
						// 삭제할 댓글 내용 입력 받기
						String content = inputContent();
						
						
						
					} else {
						System.out.println("\n[자신의 댓글만 삭제할 수 있습니다.]\n");
					}
					
					break; // 효율 증가( 더 이상의 검사는 불필요)
				}
				
			} // for end
			
			if(flag ) {
				System.out.println("\n[번호가 일치하는 댓글이 없습니다.]\n");
			}
			
			
		} catch(Exception e) {
			System.out.println("\n<<댓글 삭제 중 예외 발생>>\n");
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
