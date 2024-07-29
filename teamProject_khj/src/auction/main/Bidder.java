package auction.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class Bidder {

	private String SERVER_IP = "192.168.30.209";
	private int SERVER_PORT = 6006;
	Socket socket;
	BufferedReader in;
    PrintWriter out;
    BufferedReader consoleInput;

    public Bidder() {
    	try {
			socket = new Socket(SERVER_IP, SERVER_PORT);
			System.out.println("- 서버에 연결되었습니다.");
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream(), true);
			consoleInput = new BufferedReader(new InputStreamReader(System.in));
		
		}  catch (Exception e) {
			e.printStackTrace();
		}
    }
	public void start() throws IOException {       
            while (true) {
                System.out.println("1. 로그인");
                System.out.println("2. 회원가입");
                System.out.println("3. 종료");
                System.out.print("선택: ");
                String choice = consoleInput.readLine();

                if (choice.equals("1")) {
                	logIn();
                } else if (choice.equals("2")) {
                	register();
                } else if (choice.equals("3")) {
                    out.println("EXIT");
                    break;
                } else {
                    System.out.println("잘못된 선택입니다.");
                }
            }
       
    }
	private void bidStart() {
		Thread thread = new Thread(()->{
			
		});
	}
	
	public void logIn() throws IOException {
		// 로그인
        System.out.print("아이디: ");
        String username = consoleInput.readLine();
        System.out.print("비밀번호: ");
        String password = consoleInput.readLine();

        out.println("LOGIN:" + username + ":" + password);

        String response = in.readLine();
        if (response.equals("LOGIN_SUCCESS")) {
        	bidStart();
            System.out.println("로그인 성공!");
        } else if (response.equals("LOGIN_FAIL")) {
        	System.out.println("로그인 실패: 사용자 이름 또는 비밀번호가 잘못되었습니다.");
        }
        else {
        	System.out.println("등록된 회원정보가 없습니다.");
        }
	}

	
	public void register() throws IOException {
		// 회원가입
        System.out.print("아이디: ");
        String id = consoleInput.readLine();
        System.out.print("비밀번호: ");
        String password = consoleInput.readLine();
        System.out.print("이름: ");
        String name = consoleInput.readLine();
        System.out.print("주소: ");
        String address = consoleInput.readLine();
        System.out.print("전화번호: ");
        String contact = consoleInput.readLine();

        out.println("REGISTER:" + id + ":" + password + ":" + name + ":" + address + ":" + contact);

        String response = in.readLine();
        if (response.equals("REGISTER_SUCCESS")) {
            System.out.println("회원가입 성공!");
        } else {
            System.out.println("회원가입 실패: 이미 존재하는 사용자 이름입니다.");
        }
	}
}
