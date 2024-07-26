package member;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.TreeMap;

public class Server {

	private static final int PORT = 12345; // 서버 포트 번호
    private static final String USERS_FILE = "users.txt"; // 사용자 정보 파일    
    private static TreeMap<String, String> users;

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		users = new TreeMap<String, String>();
//				System.out.println(users);	//test용			
		Collections.synchronizedMap(users);
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("서버가 시작되었습니다. 포트 번호: " + PORT);           
            try (ObjectInputStream objectIn = new ObjectInputStream(new FileInputStream(USERS_FILE))) {
				users = (TreeMap<String, String>)objectIn.readObject(); //파일에 있는 회원들 정보를 불러오기
			} catch(FileNotFoundException e) {
//				saveUsers();
			}
            UserSearch search = new UserSearch();
			search.start();
			while (true) {
	            Socket clientSocket = serverSocket.accept();
	            System.out.println("클라이언트가 접속했습니다: " + clientSocket);

	            // 클라이언트 요청을 처리하는 쓰레드 시작
	            ClientHandler clientHandler = new ClientHandler(clientSocket);
	            clientHandler.start();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // 클라이언트 요청 처리를 담당하는 쓰레드
    private static class ClientHandler extends Thread {
        private final Socket clientSocket;
        private BufferedReader in;
        private PrintWriter out;

        public ClientHandler(Socket socket) {
            this.clientSocket = socket;
        }

		@Override
        public void run() {
            try {
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                out = new PrintWriter(clientSocket.getOutputStream(), true);
                String request;
                while ((request = in.readLine()) != null) {
                    if (request.startsWith("REGISTER")) {
                        handleRegister(request);
                    } else if (request.startsWith("LOGIN")) {
                        handleLogin(request);
                    } else if (request.equals("EXIT")) {
                        break;
                    }
                }

                System.out.println("클라이언트 연결 종료: " + clientSocket);
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // 회원가입 요청 처리
        private void handleRegister(String request) throws IOException {
            String[] parts = request.split(":");
            String username = parts[1];
            String password = parts[2];

            if(users.containsKey(username)) {
            	out.println("ALREADY_EXISTS");
            } else {
            	users.put(username, password);
            	saveUsers();
            	out.println("REGISTER_SUCCESS");
            	System.out.println(users);
            }
        }


		// 로그인 요청 처리
        private void handleLogin(String request) throws IOException {
            String[] parts = request.split(":");
            String username = parts[1];
            String password = parts[2];

           
                boolean found = false;
               
            	if (users.isEmpty()) {
            		out.println("USERS_EMPTY");
            	} else {
            		if(users.containsKey(username)) {
            			if(users.get(username).equals(password)) {
            				found = true;            				
            			}
            		}
            		
            		if (found) {
            			out.println("LOGIN_SUCCESS");
            		} else {
            			out.println("LOGIN_FAIL");
            		}            		
            	}
        }
        
    }
    // 회원정보 검색을 담당하는 쓰레드
	private static class UserSearch extends Thread{
		BufferedReader consoleInput;
    	
		@Override
		public void run() {	
			try {
				consoleInput = new BufferedReader(new InputStreamReader(System.in));
				while(true) {
					System.out.println("1. 회원 검색");
					System.out.println("2. 전체 회원 보기");
					System.out.println("3. 종료");
					System.out.print("선택: ");
					String choice = consoleInput.readLine();

	                if (choice.equals("1")) {
	                	if(users.isEmpty()) {
	                		System.out.println("등록된 회원정보가 없습니다.");	                		
	                	} else {
	                		System.out.print("아이디: ");
	                		int count = 0;
	                		String username = consoleInput.readLine();
	                		for(String key: users.keySet()) {
	                			if(key.contains(username)) {
	                				System.out.println("아이디: " + key + "  /  비밀번호: " + users.get(key));
	                				count++;
	                			} 
	                		}
	                		if(count == 0) {
	                			System.out.println("등록되지 않은 아이디입니다.");
	                		}
	                	}

	                } else if (choice.equals("2")) {
	                	if(users.isEmpty()) {
	                		System.out.println("등록된 회원정보가 없습니다.");	                		
	                	} else {
	                		for(String key: users.keySet()) {
	                			System.out.println("아이디: " + key + "  /  비밀번호: " + users.get(key));
	                		}
	                	}
	                } else if (choice.equals("3")) {
	                    break;
	                } else {
	                    System.out.println("잘못된 선택입니다.");
	                }
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
    }
    
    // 회원정보가 담긴 트리맵을 파일로 저장
    private static void saveUsers() {
    	try(ObjectOutputStream objectOut = new ObjectOutputStream(new FileOutputStream(USERS_FILE))) {
    		objectOut.writeObject(users);
			objectOut.flush();
		} catch (IOException e) {
		}
    }
}

