package com.save_alpaca.view.game2;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import com.save_alpaca.controller.MainController;
import com.save_alpaca.view.Sound;

public class MiniGame_02 {
	// 기본 프레임 생성.
	JFrame Main = new JFrame();
	MainController mc = new MainController();

	// 게임에 사용되는 값들.
	private int count = 0;
	private int count2 = 0;
	private int score = 0;
	private int run = 0;
	private String notice1 = "알파카가 코뿔소와 부딪혀서 아파해요!!!  (라이프 포인트 -1)";
	private String notice2 = "알파카가 뱀에 물려 느려졌어요!!       (3초간 이동속도 -50%)";

	// 캐릭터,배경화면,점수 패널
	private JPanel obstacle1;
	private JPanel obstacle2;
	private JPanel obstacle3;
	private JPanel snake1;
	private JPanel snake2;
	private JPanel Alpaca;
	private JPanel Notice;

	// 캐릭터,배경화면,점수 라벨
	private JLabel obstacleL1;
	private JLabel obstacleL2;
	private JLabel obstacleL3;
	private JLabel snakeL1;
	private JLabel snakeL2;
	private JLabel AlpacaL;
	private JLabel BackFiled;
	private JLabel scoreText;
	private JLabel NoticeL;
	

	// 게임중 충돌을 감지하는 패널
	private JPanel obstacle1_mini;
	private JPanel obstacle2_mini;
	private JPanel obstacle3_mini;
	private JPanel snake1_mini;
	private JPanel snake2_mini;
	private JPanel Alpaca_mini;

	// 각종 상태창을 표시할 패널,라벨
	private JPanel scoreFiled;
	private JPanel lifeFiled;
	private JLabel lifeFiledL;
	private JLabel scoreStrL;
	private JLabel scoreL;

	// 게임 시작시 표시되는 이미지들에 사용되는 패널,라벨
	private JPanel tip;
	private JPanel go;
	private JPanel go1;
	private JPanel go2;
	private JPanel go3;
	private JLabel tipL;
	private JLabel goL;
	private JLabel go1L;
	private JLabel go2L;
	private JLabel go3L;
	private JButton startbtn;
	private JButton endbtn;
	private JPanel startP;
	private JPanel endP;

	// 코뿔소,뱀 들의 이동속도 변수.
	int x1 = 0;
	int x2 = 0;
	int x3 = 0;
	int x4 = 0;
	int x5 = 0;
	int y1 = 178; 
	int y2 = 412; 
	int y3 = 638; 
	int y4 = 368; 
	int y5 = 598; 
	
	// 방향키를 한번 눌렸을때 알파카가 움직이는 거리 기본값.
	private int FLYING_UNIT = 30;

	// 알파카의 목숨갯수.
	private int life;

	// 메인컨트롤러에 리턴해줄 게임 결과값 선언.
	private int result;

	// 코뿔소,뱀의 이동속도
	private int obstacle1Random = (int) (Math.random() * 100) + 75;
	private int obstacle2Random = (int) (Math.random() * 105) + 80;
	private int obstacle3Random = (int) (Math.random() * 110) + 85;
	private int snake1Random = (int) (Math.random() * 125) + 80;
	private int snake2Random = (int) (Math.random() * 130) + 75;

	// 코뿔소,뱀의 생성을 위한 타이머.
	Timer collisionTimer = new Timer(70, actionEvent -> checkcollisionTimer());
	Timer StartTimer = new Timer(1000, actionEvent -> StartCount());
	Timer ScoreTimer = new Timer(1000, actionEvent -> CheckScore());
	Timer AlpacaRunTimer = new Timer(200, actionEvent -> AlpacaRun());
	Timer AlpacaRetrunTimer = new Timer(3000, actionEvent -> AlpacaRetrunTimer());
	Timer SnakeSlowTimer = new Timer(4000, actionEvent -> SnakeSlowTimer());
	Timer obstacle1Timer = new Timer(obstacle1Random, actionEvent -> obstacle1Method());
	Timer obstacle2Timer = new Timer(obstacle2Random, actionEvent -> obstacle2Method());
	Timer obstacle3Timer = new Timer(obstacle3Random, actionEvent -> obstacle3Method());
	Timer obstacleYMoveTimer = new Timer(5000, actionEvent -> obstacleYMoveTimer());
	Timer snake1Timer = new Timer(snake1Random, actionEvent -> snake1Method());
	Timer snake2Timer = new Timer(snake2Random, actionEvent -> snake2Method());

	// 기본생성자.
	public MiniGame_02() {
		
		
		//미니게임2 bgm 시작!
        Sound.bgmThread.interrupt();
        URL sURL = getClass().getClassLoader().getResource("mg2bgm.wav");
        Sound s = new Sound(sURL,178000);
        Sound.bgmThread = new Thread(s);
        Sound.bgmThread.start();
        
		// 게임프레임의 사이즈,위치,레이아웃
		Main.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Main.setSize(1400, 908);
		Main.setLocation(250, 50);
		Main.setLayout(null);
		Main.setResizable(false);

		// 알파카 이동, 목숨 관련
		life = 3; // 알파카 목숨 초기값;

		// 기본 패널, 라벨 초기값 세팅.
		URL BackFiledURL = getClass().getClassLoader().getResource("MiniGame_02_images/FiledG2.png");
		BackFiled = new JLabel(new ImageIcon(BackFiledURL));
		BackFiled.setLocation(0, 0);
		BackFiled.setSize(1400, 908);

		obstacle1 = new JPanel();
		obstacle1.setSize(280, 180);
		obstacle1.setLocation(1400, 800);
		obstacle1.setBackground(new Color(0, 0, 0, 0));
		obstacle2 = new JPanel();
		obstacle2.setSize(305, 180);
		obstacle2.setLocation(1400, 800);
		obstacle2.setBackground(new Color(0, 0, 0, 0));
		obstacle3 = new JPanel();
		obstacle3.setSize(305, 180);
		obstacle3.setLocation(1400, 800);
		obstacle3.setBackground(new Color(0, 0, 0, 0));

		snake1 = new JPanel();
		snake1.setSize(170, 41);
		snake1.setLocation(1400, 800);
		snake1.setBackground(new Color(0, 0, 0, 0));
		snake2 = new JPanel();
		snake2.setSize(170, 41);
		snake2.setLocation(1400, 800);
		snake2.setBackground(new Color(0, 0, 0, 0));

		Alpaca = new JPanel();
		Alpaca.setSize(130, 250);
		Alpaca.setLocation(1100, 445);
		Alpaca.setBackground(new Color(0, 0, 0, 0));

		URL scoreIURL = getClass().getClassLoader().getResource("MiniGame_02_images/scoreFiled.png");
		ImageIcon scoreI = new ImageIcon(scoreIURL);
		scoreL = new JLabel("  " + String.valueOf(score), scoreI, JLabel.RIGHT);
		scoreL.setVerticalTextPosition(0);
		scoreL.setHorizontalTextPosition(0);
		Font font = new Font("필기", Font.BOLD, 31);
		scoreL.setFont(font);
		scoreFiled = new JPanel();
		scoreFiled.add(scoreL);
		scoreFiled.setBounds(60, 10, 204, 80);
		scoreFiled.setBackground(new Color(0, 0, 0, 0));
		
		
		URL scoreTextURL = getClass().getClassLoader().getResource("MiniGame_02_images/scoreFiled2.png");
		scoreText = new JLabel("  " + String.valueOf(score)+" M", 
				(new ImageIcon(scoreTextURL)), JLabel.RIGHT);
		scoreText.setVerticalTextPosition(0);
		scoreText.setHorizontalTextPosition(0);
		scoreText.setLayout(new FlowLayout(FlowLayout.LEFT));
		scoreText.setFont(font);
		scoreText.setBounds(0, -3, 204, 80);
		scoreText.setVisible(true);
		scoreL.add(scoreText);

		lifeFiledL = new JLabel();
		URL lifeFiledLURL = getClass().getClassLoader().getResource("MiniGame_02_images/lifeFiled3.png");
		lifeFiledL = new JLabel(new ImageIcon(lifeFiledLURL));

		lifeFiled = new JPanel();
		lifeFiled.add(lifeFiledL);
		lifeFiled.setBounds(370, 10, 286, 80);
		lifeFiled.setBackground(new Color(0, 0, 0, 0));

		URL obstacleLURL = getClass().getClassLoader().getResource("MiniGame_02_images/obstacle.png");
		obstacleL1 = new JLabel(new ImageIcon(obstacleLURL));
		obstacleL2 = new JLabel(new ImageIcon(obstacleLURL));
		obstacleL3 = new JLabel(new ImageIcon(obstacleLURL));

		URL snakeLURL = getClass().getClassLoader().getResource("MiniGame_02_images/snake.png");
		snakeL1 = new JLabel(new ImageIcon(snakeLURL));
		snakeL2 = new JLabel(new ImageIcon(snakeLURL));

		
		URL AlpacaLURL = getClass().getClassLoader().getResource("MiniGame_02_images/alpacaG2_1.png");
		AlpacaL = new JLabel(new ImageIcon(AlpacaLURL));

		URL tipLURL = getClass().getClassLoader().getResource("MiniGame_02_images/playtip.png");
		tipL = new JLabel(new ImageIcon(tipLURL));
		tip = new JPanel();
		tip.add(tipL);
		tip.setLocation(90, 120);
		tip.setSize(1200, 559);
		tip.setBackground(new Color(0, 0, 0, 0));

		URL goLURL = getClass().getClassLoader().getResource("MiniGame_02_images/go.png");
		goL = new JLabel(new ImageIcon(goLURL));
		go = new JPanel();
		go.add(goL);
		go.setLocation(530, 300);
		go.setSize(310, 320);
		go.setBackground(new Color(0, 0, 0, 0));

		URL go1LURL = getClass().getClassLoader().getResource("MiniGame_02_images/1.png");
		go1L = new JLabel(new ImageIcon(go1LURL));
		go1 = new JPanel();
		go1.add(go1L);
		go1.setLocation(530, 300);
		go1.setSize(310, 320);
		go1.setBackground(new Color(0, 0, 0, 0));

		URL go2LURL = getClass().getClassLoader().getResource("MiniGame_02_images/2.png");
		go2L = new JLabel(new ImageIcon(go2LURL));
		go2 = new JPanel();
		go2.add(go2L);
		go2.setLocation(530, 300);
		go2.setSize(310, 320);
		go2.setBackground(new Color(0, 0, 0, 0));

		URL go3LURL = getClass().getClassLoader().getResource("MiniGame_02_images/3.png");
		go3L = new JLabel(new ImageIcon(go3LURL));
		go3 = new JPanel();
		go3.add(go3L);
		go3.setLocation(530, 300);
		go3.setSize(310, 320);
		go3.setBackground(new Color(0, 0, 0, 0));
		
		startbtn = new JButton();
		startbtn.setBounds(385, 560, 580, 80);
		
		startP = new JPanel();
		startP.setBounds(380, 550, 580, 100);
		startP.add(startbtn);
		
		startbtn.setBorderPainted(false);
		startbtn.setContentAreaFilled(false);
		startbtn.setFocusPainted(false);
		
		URL endbtnURL = getClass().getClassLoader().getResource("MiniGame_02_images/returnMain.png");
		endbtn = new JButton(new ImageIcon(endbtnURL));
		endbtn.setBounds(0, 0, 125, 95);

		endP = new JPanel();
		endP.setBounds(1400, 300, 125, 95);
		endP.setBackground(new Color(0,0,0,0));
		endP.setOpaque(false);
		endP.add(endbtn);
		
		endbtn.setBackground(new Color(0,0,0,0));
		endbtn.setBorderPainted(false);
		endbtn.setContentAreaFilled(false);
		endbtn.setFocusPainted(false);


		    


		//공지사항 세팅
		NoticeL = new JLabel(notice1);
		
		
		
		
		
		
		// 게임시작시 스타트 버튼 액션
		startbtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				super.mouseReleased(e);
				tip.setLocation(1400, 800);
				startbtn.setLocation(1400, 800);
				startP.setLocation(1400, 800);
				StartTimer.start();

			}
		});
		
		// 게임 종료시 앤드 버튼 액션
		endbtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				super.mouseReleased(e);
				endP.setLocation(100, 700);
				
				Main.dispose();
				
				//미니게임2 종료 후 bgm 시작
	            Sound.bgmThread.interrupt();
	            URL mainsURL = getClass().getClassLoader().getResource("mainbgm.wav");
	            Sound mains = new Sound(mainsURL,131000);
	            Sound.bgmThread = new Thread(mains);
	            Sound.bgmThread.start();
			}
		});


		// 알파카 패널 이동시키기
		Alpaca.addKeyListener(new keyLisner());

		// 알파카 충돌 감지용 알파카 미니패널 생성,초기화
		Alpaca_mini = new JPanel();
		Alpaca_mini.setSize(100, 107);
		Alpaca_mini.setLocation(Alpaca.getLocation());

		// 패널안에 라벨 넣기.
		tip.add(startP);
		obstacle1.add(obstacleL1);
		obstacle2.add(obstacleL2);
		obstacle3.add(obstacleL3);
		snake1.add(snakeL1);
		snake2.add(snakeL2);
		Alpaca.add(AlpacaL);

		// 메인프레임에 패널들 넣기.
		Main.add(startbtn);
		Main.add(tip);
		Main.add(go3);
		Main.add(go2);
		Main.add(go1);
		Main.add(go);
		Main.add(endP);

		Main.add(lifeFiled);
		Main.add(scoreFiled);
		Main.add(Alpaca);
		Main.add(obstacle1);
		Main.add(obstacle2);
		Main.add(obstacle3);
		Main.add(snake1);
		Main.add(snake2);
		Main.add(BackFiled);

		Main.setVisible(true);
		Main.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		// 알파카 움직이는 방향키 리스너 포커스 계속해서 잡아주기.
		Alpaca.requestFocus();
		
		//미니게임2 시작화면에서 X버튼 종료시 bgm 변경
        Main.addWindowListener(new WindowAdapter() { 
           public void windowClosing(WindowEvent e) {
              Sound.bgmThread.interrupt();
              if(Sound.bgmThread.isAlive()) {
                 Sound.bgmThread.interrupt();
              }
              URL mainsURL = getClass().getClassLoader().getResource("mainbgm.wav");
              Sound mains = new Sound(mainsURL,131000);
              Sound.bgmThread = new Thread(mains);
              Sound.bgmThread.start();
           }
        });

	}

	/**==========================================================================
	 * @Method :gameTip
	 * @작성자 : 박주완
	 * @작성일 : 2019. 11. 15.
	 * @변경이력 :
	 * @메소드 설명 : 게임시작시 게임플레이 방법 팁 패널 출력하는 메소드
	 ==========================================================================*/
	public void gameTip() {
		startbtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				super.mouseReleased(e);
				
				tip.setLocation(1400, 800);
				
			}

		});

	}

	/**==========================================================================
	 * @Method :StartCount
	 * @작성자 : 박주완
	 * @작성일 : 2019. 11. 14.
	 * @변경이력 :
	 * @메소드 설명 : 게임시작시 카운트다운, 각종 타이머 실행 시켜주는 메소드.
	 ==========================================================================*/
	public void StartCount() {
		count2++;
		Alpaca.requestFocus();

		if (count2 == 1) {
			go3.setLocation(1400, 800);
		} else if (count2 == 2) {
			go2.setLocation(1400, 800);
		} else if (count2 == 3) {
			go1.setLocation(1400, 800);
		} else if (count2 == 4) {
			go.setLocation(1400, 800);
			obstacle1Timer.start();
			snake1Timer.start();
			ScoreTimer.start();
		} else if (count2 == 5) {
			collisionTimer.start();
			snake2Timer.start();
			AlpacaRunTimer.start();
			obstacleYMoveTimer.start();
			

		} else if (count2 == 6) {
			obstacle3Timer.start();
		} else if (count2 == 7) {
			obstacle2Timer.start();
		}
		
		//BGM
	      Main.addWindowListener(new WindowAdapter() { 
	         public void windowClosing(WindowEvent e) {
	            Sound.bgmThread.interrupt();
	            if(Sound.bgmThread.isAlive()) {
	               Sound.bgmThread.interrupt();
	            }
	            URL mainsURL = getClass().getClassLoader().getResource("mainbgm.wav");
	            Sound mains = new Sound(mainsURL,131000);
	            Sound.bgmThread = new Thread(mains);
	            Sound.bgmThread.start();
	         }

	      });

	      
		Alpaca.requestFocus();
	}

	/**==========================================================================
	 * @Class :keyLisner
	 * @작성자 : 박주완
	 * @작성일 : 2019. 11. 13.
	 * @변경이력 :
	 * @메소드 설명 : 위아래 방향키를 움직일 떄 마다 게임속 알파카 캐릭터가 이동하게하는 클래스
	 ==========================================================================*/
	class keyLisner implements KeyListener {

		@Override
		public void keyTyped(KeyEvent e) {

		}

		@Override
		public void keyPressed(KeyEvent e) {
			int keycode = e.getKeyCode();
			// 방향키 각 키를 누를 떄 마다 알파카가 이동할 좌표값들. (기본 30)
			switch (keycode) {
			case KeyEvent.VK_UP:
				Alpaca.setLocation(Alpaca.getX(), Alpaca.getY() - FLYING_UNIT);
				Alpaca_mini.setLocation(Alpaca.getLocation());
				break;
			case KeyEvent.VK_DOWN:
				Alpaca.setLocation(Alpaca.getX(), Alpaca.getY() + FLYING_UNIT);
				Alpaca_mini.setLocation(Alpaca.getLocation());
				break;
			case KeyEvent.VK_LEFT:
				Alpaca.setLocation(Alpaca.getX() - FLYING_UNIT, Alpaca.getY());
				Alpaca_mini.setLocation(Alpaca.getLocation());
				break;
			case KeyEvent.VK_RIGHT:
				Alpaca.setLocation(Alpaca.getX() + FLYING_UNIT, Alpaca.getY());
				Alpaca_mini.setLocation(Alpaca.getLocation());
				break;

			}
		}

		@Override
		public void keyReleased(KeyEvent e) {

		}

	}

	/**==========================================================================
	 * @Method :checkcollisionTimer
	 * @작성자 : 천주원, 박주완
	 * @작성일 : 2019. 11. 13.
	 * @변경이력 :
	 * @메소드 설명 : 알파카가 맵 밖으로 나가는지 감시. 알파카 캐릭터와 장애물의 충돌을 감지하고 라이프 포인트를 1씩 감소. 뱀에게 물렸을
	 *      시 알파카 캐릭터의 일정시간 속도 저하. 라이프 포인트가 0이 되었을 때 게임오버 출력. 게임종료후 결과값 메인컨트롤러에 리턴.
	 ==========================================================================*/
	public void checkcollisionTimer() {
		count++;
		Alpaca.requestFocus();

		Rectangle alpacaR = Alpaca_mini.getBounds();

		// 알파카가 맵밖으로 나가는지 감시하는 조건문.
		if (Alpaca.getY() <= 80) {
			Alpaca.setLocation(Alpaca.getX(),81);
		}
		if (Alpaca.getY() >= 710) {
			Alpaca.setLocation(Alpaca.getX(),708);
		}
		if (Alpaca.getX() <= 100) {
			Alpaca.setLocation(51, Alpaca.getY());
		}
		if (Alpaca.getX() >= 1280) {
			Alpaca.setLocation(1278, Alpaca.getY());
		}

		obstacle1_mini = new JPanel();
		obstacle1_mini.setSize(180, 105);
		obstacle1_mini.setLocation(obstacle1.getLocation());
		obstacle2_mini = new JPanel();
		obstacle2_mini.setSize(180, 105);
		obstacle2_mini.setLocation(obstacle2.getLocation());
		obstacle3_mini = new JPanel();
		obstacle3_mini.setSize(180, 105);
		obstacle3_mini.setLocation(obstacle3.getLocation());

		snake1_mini = new JPanel();
		snake1_mini.setSize(100, 35);
		snake1_mini.setLocation(snake1.getLocation());
		snake2_mini = new JPanel();
		snake2_mini.setSize(100, 35);
		snake2_mini.setLocation(snake2.getLocation());

		JPanel[] obstacleArr = new JPanel[3];
		{
			obstacleArr[0] = obstacle1_mini;
			obstacleArr[1] = obstacle2_mini;
			obstacleArr[2] = obstacle3_mini;
		}
		JPanel[] snakeArr = new JPanel[2];
		{
			snakeArr[0] = snake1_mini;
			snakeArr[1] = snake2_mini;
		}

		for (int i = 0; i < obstacleArr.length; i++) {
			Rectangle obstacleR = obstacleArr[i].getBounds();
			if (alpacaR.intersects(obstacleR)) {
				System.out.println("코뿔소에 부딪혀서 알파카가 아파해요!! " + "체력 : " + life);
				Alpaca_mini.setLocation(0, 0);
				AlpacaRunTimer.stop();
				URL AlpacaLURL = getClass().getClassLoader().getResource("MiniGame_02_images/alpacaG2Die.png");
				AlpacaL.setIcon(new ImageIcon(AlpacaLURL));
				AlpacaRetrunTimer.start();

	
				// 알파카 이미지와 로케이션이 변경된 후 2초 뒤에 원래 위치, 원래 이미지로 바꿔어야함.
				// Alpaca_mini.setLocation(Alpaca.getLocation());

			}

		}

		for (int i = 0; i < snakeArr.length; i++) {
			Rectangle snakeR = snakeArr[i].getBounds();
			if (alpacaR.intersects(snakeR)) {
				FLYING_UNIT = 10;
				System.out.println("독사에 물려서 느려졌어요!!!");
				Alpaca_mini.setLocation(0, 0);
				AlpacaRunTimer.stop();
				URL AlpacaLURL = getClass().getClassLoader().getResource("MiniGame_02_images/alpacaG2_1slow.png");
				AlpacaL.setIcon(new ImageIcon(AlpacaLURL));
				SnakeSlowTimer.start();
				// 알파카 이미지와 로케이션이 변경된 후 2초 뒤에 원래 위치, 원래 이미지로 바꿔어야함.
				// Alpaca_mini.setLocation(Alpaca.getLocation());

			}
		}

		// 라이프 포인트가 줄어들 때 마다 상단의 라이프포인트창에 하트가 줄어들도록 해주는 조건문.
		if (life == 3) {
			URL lifeFiledLURL = getClass().getClassLoader().getResource("MiniGame_02_images/lifeFiled3.png");
			lifeFiledL.setIcon(new ImageIcon(lifeFiledLURL));
		} else if (life == 2) {
			URL lifeFiledLURL = getClass().getClassLoader().getResource("MiniGame_02_images/lifeFiled2.png");
			lifeFiledL.setIcon(new ImageIcon(lifeFiledLURL));
		} else if (life == 1) {
			URL lifeFiledLURL = getClass().getClassLoader().getResource("MiniGame_02_images/lifeFiled1.png");
			lifeFiledL.setIcon(new ImageIcon(lifeFiledLURL));
		} else if (life == 0) {
			URL lifeFiledLURL = getClass().getClassLoader().getResource("MiniGame_02_images/lifeFiled0.png");
			lifeFiledL.setIcon(new ImageIcon(lifeFiledLURL));
		}

		// 라이프 포인트가 0이 되었을 떄 게임오버 시켜주는 조건문.
		if (life == 0) {
			System.out.println("[게임오버]");
			System.out.println("획득한 점수 : " + score + "점!");
			
			URL BackFiledURL = getClass().getClassLoader().getResource("MiniGame_02_images/gameOver.png");
			URL AlpacaLURL = getClass().getClassLoader().getResource("MiniGame_02_images/alpacaG2Die.png");
			BackFiled.setIcon(new ImageIcon(BackFiledURL));
			AlpacaL.setIcon(new ImageIcon(AlpacaLURL));
			endP.setLocation(1220, 25); //메인화면으로 돌아가기 버튼 위치지정해주기.
			collisionTimer.stop(); // 충돌감지 타이머 스탑
			StartTimer.stop(); // 게임시작 타이머 스탑
			obstacle1Timer.stop(); // 코뿔소1 움직임 타이머 스탑
			obstacle2Timer.stop(); // 코뿔소1 움직임 타이머 스탑
			obstacle3Timer.stop(); // 코뿔소1 움직임 타이머 스탑
			snake1Timer.stop(); // 뱀1 움직임 타이머 스탑
			snake2Timer.stop(); // 뱀2 움직임 타이머 스탑
			AlpacaRunTimer.stop(); // 알파카 이동 타이머 스탑
			ScoreTimer.stop(); // 점수계산 타이머 스탑
			AlpacaRetrunTimer.stop(); // 알파카 충돌후 돌아오는 타이머 스탑
			SnakeSlowTimer.stop();    // 알파카가 원래 속도로 돌아오는 타이머 스탑	
			obstacleYMoveTimer.stop(); //코뿔소 y값 변경하는 타이머 스탑
			obstacle1.setVisible(false);
			obstacle2.setVisible(false);
			obstacle3.setVisible(false);
			snake1.setVisible(false);
			snake2.setVisible(false);

			
			//미니게임2  종료 X버튼시 main bgm 전환
	         Main.addWindowListener(new WindowAdapter() { 
	            public void windowClosing(WindowEvent e) {
	               Sound.bgmThread.interrupt();
	               if(Sound.bgmThread.isAlive()) {
	                  Sound.bgmThread.interrupt();
	               }
	               URL mainsURL = getClass().getClassLoader().getResource("mainbgm.wav");
	               Sound mains = new Sound(mainsURL,131000);
	               Sound.bgmThread = new Thread(mains);
	               Sound.bgmThread.start();
	            }

	         });
	         
	         
			result = score;
		}

		// 게임 스코어가 90이 되었을때 게임클리어 표시와 동시에 result값 리턴(to 메인컨트롤러)
		if (score == 90) {
			System.out.println("[게임클리어!]");
			System.out.println("획득한 점수 : " + score + "점!");

			URL BackFiledURL = getClass().getClassLoader().getResource("MiniGame_02_images/gameClear.png");
			BackFiled.setIcon(new ImageIcon(BackFiledURL));
			
			
	      //이미지 리사이즈 해주는 작업
			URL imageURL = getClass().getClassLoader().getResource("MiniGame_02_images/alpacaG2Happy.png");
			ImageIcon image = new ImageIcon(imageURL); //현재 이미지 불러오기
	        Image imageResize = image.getImage() ;   //바꿀이미지에 현재 이미지를 넣기
	        image = new ImageIcon(imageResize.getScaledInstance(130,250,  java.awt.Image.SCALE_SMOOTH ));
	        
			AlpacaL.setIcon(image);
			endP.setLocation(1220, 25); //메인화면으로 돌아가기 버튼 위치지정해주기.
			collisionTimer.stop(); // 충돌감지 타이머 스탑
			StartTimer.stop(); // 게임시작 타이머 스탑
			obstacle1Timer.stop(); // 코뿔소1 움직임 타이머 스탑
			obstacle2Timer.stop(); // 코뿔소1 움직임 타이머 스탑
			obstacle3Timer.stop(); // 코뿔소1 움직임 타이머 스탑
			snake1Timer.stop(); // 뱀1 움직임 타이머 스탑
			snake2Timer.stop(); // 뱀2 움직임 타이머 스탑
			AlpacaRunTimer.stop(); // 알파카 이동 타이머 스탑
			ScoreTimer.stop(); // 점수계산 타이머 스탑
			AlpacaRetrunTimer.stop(); // 알파카 충돌후 돌아오는 타이머 스탑
			SnakeSlowTimer.stop();    // 알파카가 원래 속도로 돌아오는 타이머 스탑
			obstacleYMoveTimer.stop(); //코뿔소 y값 변경하는 타이머 스탑
			obstacle1.setVisible(false);
			obstacle2.setVisible(false);
			obstacle3.setVisible(false);
			snake1.setVisible(false);
			snake2.setVisible(false);

			//미니게임2  종료 X버튼시 main bgm 전환
	         Main.addWindowListener(new WindowAdapter() { 
	            public void windowClosing(WindowEvent e) {
	               Sound.bgmThread.interrupt();
	               if(Sound.bgmThread.isAlive()) {
	                  Sound.bgmThread.interrupt();
	               }
	               URL mainsURL = getClass().getClassLoader().getResource("mainbgm.wav");
	               Sound mains = new Sound(mainsURL,131000);
	               Sound.bgmThread = new Thread(mains);
	               Sound.bgmThread.start();
	            }

	         });
			result = score;
			
			mc.gameHealthResult(10);//게임 클리어시 건강 10 추가
		}

	}

	/**==========================================================================
	 * 
	 * @Method :AlpacaRetrunTimer
	 * @작성자 : 박주완
	 * @작성일 : 2019. 11. 15.
	 * @변경이력 :
	 * @메소드 설명 : 알파카가 장애물과 충돌후 충돌을 감지하는 패널이 2초뒤에 실제 알파카의 위치로 돌려주는 메소드. (알파카의 무적이
	 *      풀린다고 생각하면된다.)
	 ==========================================================================*/
	public void AlpacaRetrunTimer() {
		life--;
		Alpaca_mini.setLocation(Alpaca.getLocation());
		AlpacaRunTimer.start();
		AlpacaRetrunTimer.stop();
		
	}
	
	
	/**==========================================================================
		@Method :SnakeSlowTimer
		@작성자 : 박주완
		@작성일 : 2019. 11. 15.
		@변경이력 :
		@메소드 설명 : 알파카가 뱀에게 물렸을때 5초간 느려진 후 5초뒤에 다시 원래 속도로 돌려주는 메소드.
	 ==========================================================================*/
	public void SnakeSlowTimer() {
		FLYING_UNIT = 30;
		Alpaca_mini.setLocation(Alpaca.getLocation());
		AlpacaRunTimer.start();
		
	}

	/**==========================================================================
	 * @Method :CheckScore
	 * @작성자 : 박주완
	 * @작성일 : 2019. 11. 15.
	 * @변경이력 :
	 * @메소드 설명 : 게임시작후 1초마다 달린 거리 를 체크하는 메소드
	 ==========================================================================*/
	public void CheckScore() {
		score++;
		scoreText.setLocation(1500, 800);
		URL scoreTextURL = getClass().getClassLoader().getResource("MiniGame_02_images/scoreFiled2.png");
		scoreText = new JLabel("  " + String.valueOf(score)+" M", (new ImageIcon(scoreTextURL)), JLabel.RIGHT);
		scoreText.setVerticalTextPosition(0);
		scoreText.setHorizontalTextPosition(0);
		Font font = new Font("필기", Font.BOLD, 31);
		scoreText.setLayout(new FlowLayout(FlowLayout.LEFT));
		scoreText.setFont(font);
		scoreText.setBounds(0, -3, 204, 80);
		scoreText.setVisible(true);
		scoreL.add(scoreText);
		

		System.out.println("아빠 알파카(이)가 달린거리 : " + score + " 미터");
		/*
		 * ImageIcon scoreI = new ImageIcon("images/MiniGame_02_images/scoreFiled.png");
		 * scoreL = new JLabel("  " + String.valueOf(score), scoreI, JLabel.RIGHT);
		 * scoreL.setVerticalTextPosition(0); scoreL.setHorizontalTextPosition(0); Font
		 * font = new Font("필기", Font.BOLD, 31); scoreL.setFont(font); scoreFiled = new
		 * JPanel(); scoreFiled.add(scoreL); scoreFiled.setBounds(60, 10, 204, 80);
		 * scoreFiled.setBackground(new Color(0, 0, 0, 0));
		 * 
		 ==========================================================================*/
	}

	/**==========================================================================
	 * @Method :AlpacaRun
	 * @작성자 : 박주완
	 * @작성일 : 2019. 11. 14.
	 * @변경이력 :
	 * @메소드 설명 : 알파카가 달리는 모습처럼 보이도록 반복해서 이미지를 교차해주는 메소드.
	 ==========================================================================*/
	public void AlpacaRun() {
		run++;
		if (run % 2 == 0) {
			URL AlpacaLURL = getClass().getClassLoader().getResource("MiniGame_02_images/alpacaG2_1.png");
			AlpacaL.setIcon(new ImageIcon(AlpacaLURL));
			Alpaca.setOpaque(false);
		} else if (run % 2 != 0) {
			URL AlpacaLURL = getClass().getClassLoader().getResource("MiniGame_02_images/alpacaG2_2.png");
			AlpacaL.setIcon(new ImageIcon(AlpacaLURL));
			Alpaca.setOpaque(false);
		}
	}
	
	/**==========================================================================
		@Method :obstacleYMoveTimer
		@작성자 : 박주완
		@작성일 : 2019. 11. 15.
		@변경이력 :
		@메소드 설명 : 코뿔소 사이에 가만히 서서 데미지를 받지 않고 점수를 취득하는 편법을 방지하기 위해
					코뿔소의 Y축값이 5초마다 변경되게 하는 메소드
	 ==========================================================================*/
	public void obstacleYMoveTimer() {
		int random = (int) (Math.random() * 15) -5;
		y1 = (int) (Math.random() * 10) + 178+random;
		y2 = (int) (Math.random() * 10) + 412+random;
		y3 = (int) (Math.random() * 10) + 638+random;
		y4 = (int) (Math.random() * 5) + 368+random;
		y5 = (int) (Math.random() * 5) + 598+random;
		System.out.println("각 장애물의 y값이 변경되었습니다.");
	}

	

	/**==========================================================================
	 * @Method :obstacle1Method
	 * @작성자 : 박주완
	 * @작성일 : 2019. 11. 15.
	 * @변경이력 :
	 * @메소드 설명 : 장애물이 왼쪽에서 오른쪽으로 달리도록 하는 메소드.
	 ====================================================================================================================================================*/
	public void obstacle1Method() {

		int randomNumX = (int) (Math.random() * 40) + 10;
		x1 += randomNumX;
		obstacle1.setLocation(x1, y1);
		if (x1 >= 1500) {
			x1 = 0;
		}

	}

	public void obstacle2Method() {

		int randomNum = (int) (Math.random() * 25) + 10;
		x2 += randomNum;
		obstacle2.setLocation(x2, y2);
		if (x2 >= 1500) {
			x2 = 0;
		}
	}

	public void obstacle3Method() {

		int randomNum = (int) (Math.random() * 33) + 15;
		x3 += randomNum;
		obstacle3.setLocation(x3, y3);
		if (x3 >= 1500) {
			x3 = 0;
		}
	}

	public void snake1Method() {

		int randomNum = (int) (Math.random() * 23) + 15;
		x4 += randomNum;
		snake1.setLocation(x4, y4);
		if (x4 >= 1500) {
			x4 = 0;
		}
	}

	public void snake2Method() {

		int randomNum = (int) (Math.random() * 28) + 15;
		x5 += randomNum;
		snake2.setLocation(x5, y5);
		if (x5 >= 1500) {
			x5 = 0;
		}
	}

	/**==========================================================================
	 * @Method :getResult
	 * @작성자 : 박주완
	 * @작성일 : 2019. 11. 12.
	 * @변경이력 :
	 * @메소드 설명 : 이후 메소드 들은 각 필드값의 getter,setter
	 ==========================================================================*/
	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getCount2() {
		return count2;
	}

	public void setCount2(int count2) {
		this.count2 = count2;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public JLabel getScoreStrL() {
		return scoreStrL;
	}

	public void setScoreStrL(JLabel scoreStrL) {
		this.scoreStrL = scoreStrL;
	}

	public JLabel getScoreL() {
		return scoreL;
	}

	public void setScoreL(JLabel scoreL) {
		this.scoreL = scoreL;
	}

}
