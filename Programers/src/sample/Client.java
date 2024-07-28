package sample;

import java.io.*;
import java.net.*;

public class Client {
    private static final String SERVER_IP = "localhost";
    private static final int SERVER_PORT = 6006;

    public static void main(String[] args) {
        try (
            Socket socket = new Socket(SERVER_IP, SERVER_PORT);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader consoleInput = new BufferedReader(new InputStreamReader(System.in))
        ) {
            System.out.println("서버에 접속되었습니다.");

            while (true) {
                System.out.println("1. 회원가입");
                System.out.println("2. 로그인");
                System.out.println("3. 종료");
                System.out.print("선택: ");
                String choice = consoleInput.readLine();

                if (choice.equals("1")) {
                    // 회원가입
                    System.out.print("사용자 이름: ");
                    String username = consoleInput.readLine();
                    System.out.print("비밀번호: ");
                    String password = consoleInput.readLine();

                    out.println("REGISTER:" + username + ":" + password);

                    String response = in.readLine();
                    if (response.equals("REGISTER_SUCCESS")) {
                        System.out.println("회원가입 성공!");
                    } else {
                        System.out.println("회원가입 실패: 이미 존재하는 사용자 이름입니다.");
                    }
                } else if (choice.equals("2")) {
                    // 로그인
                    System.out.print("사용자 이름: ");
                    String username = consoleInput.readLine();
                    System.out.print("비밀번호: ");
                    String password = consoleInput.readLine();

                    out.println("LOGIN:" + username + ":" + password);

                    String response = in.readLine();
                    if (response.equals("LOGIN_SUCCESS")) {
                        System.out.println("로그인 성공!");
                    } else if (response.equals("LOGIN_FAIL")) {
                    	System.out.println("로그인 실패: 사용자 이름 또는 비밀번호가 잘못되었습니다.");
                    }
                    else {
                    	System.out.println("등록된 회원정보가 없습니다.");
                    }
                } else if (choice.equals("3")) {
                    out.println("EXIT");
                    break;
                } else {
                    System.out.println("잘못된 선택입니다.");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

