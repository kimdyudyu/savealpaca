package com.save_alpaca.view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import com.save_alpaca.controller.MainController;
import com.save_alpaca.model.vo.Alpaca;

public class StatusLabel extends JFrame{

	private static final long serialVersionUID = 1L;
	
	
    MainController mainController = new MainController(); //메인컨트롤에 값을 보내주기위해 선언
    
    Alpaca ap = new Alpaca();
 
	static int hungercount= 50; //배고픔이 변경되는것을 임의로 해주기위해 선언

	private JPanel LabelPane;
	private JLabel health;
	private JLabel hunger;
	private JLabel lifetime;
	private JLabel hygiene;
	private JLabel user;
	private JLabel alpacaname;
	private JLabel playTimeL;
	private JLabel score;  
	private JLabel hungerIcon;
	private JLabel healthIcon;
	private JLabel hygieneIcon;
	
	
	private JPanel healthP;
	private JPanel hungerP;
	private JPanel lifetimeP;
	private JPanel hygieneP;
	private JPanel userP;
	private JPanel alpacanameP;
	private JPanel hungerIconP;
	private JPanel healthIconP;
	private JPanel playTimeP;

	
	private int playTime = 0;

	Timer healthtimer = new Timer(20, actionEvent -> health()); //건강을 수시로 체크해주는 타이머
	Timer hungertimer = new Timer(12000,actionEvent -> hungercount()); //배고픔이 시간에 따라 변경됨
	Timer hungerIconTimer = new Timer(20,actionEvent -> hunger()); // 배고픔 수시로 체크해주는 타이머
	public Timer playTimer = new Timer(1000, actionEvent -> playTimer()); //플레이시간 기록하는 타이머

	
	
	
	public StatusLabel() {
		
		healthtimer.start();
		hungertimer.start();
        hungerIconTimer.start();
        playTimer.start();
		
	//기본이 될 스테이터스 패널 값 선언
		LabelPane = new JPanel();
		LabelPane.setBounds(0, 0, 700, 500);
		LabelPane.setBackground(new Color(0,0,0,0));
		LabelPane.setLayout(null);
		LabelPane.setOpaque(false);	
		
	//유저 이름과 알파카 이름 패널의 배경이미지 삽입.
        URL userIURL = getClass().getClassLoader().getResource("statusimages/smalluser.png");
		ImageIcon userI = new ImageIcon(userIURL);
        URL alpacaNameIURL = getClass().getClassLoader().getResource("statusimages/user.png");
		ImageIcon alpacaNameI = new ImageIcon(alpacaNameIURL);		

	//필요한 요소요소에 이미지 넣기.
		user = new JLabel(mainController.getUserName(),userI,JLabel.CENTER);
		user.setVerticalTextPosition(0);
		user.setHorizontalTextPosition(0);
		Font font = new Font("필기",Font.BOLD,21);
		user.setFont(font);
		alpacaname = new JLabel(mainController.getAlpacaName(),alpacaNameI,JLabel.CENTER);
		alpacaname.setVerticalTextPosition(0);
		alpacaname.setHorizontalTextPosition(0);
		alpacaname.setFont(font);

		 URL lifetimeURL = getClass().getClassLoader().getResource("statusimages/timer.png");
		lifetime = new JLabel(new ImageIcon(lifetimeURL));

		
	//게임 플레이 타임 구현 - 19.11.18 수정 
        playTimeP = new JPanel();
        playTimeP.setBackground(new Color(0,0,0,0));
        playTimeP.setSize(200,100);
        playTimeP.setLocation(540, 0);
        playTimeP.setOpaque(false);
        
		 URL playTimeLURL = getClass().getClassLoader().getResource("statusimages/smalluser.png");
        playTimeL = new JLabel(new ImageIcon(playTimeLURL));
        playTimeL.setBounds(0, 0, 200, 100);
        
        URL scoreURL = getClass().getClassLoader().getResource("statusimages/score.png");
		score = new JLabel("  " + String.valueOf(playTime),new ImageIcon(scoreURL), JLabel.CENTER);
		score.setVerticalTextPosition(0);
		score.setHorizontalTextPosition(0);
		Font font2 = new Font("필기", Font.BOLD, 31);
		score.setFont(font2);
		score.setBounds(10, 11, 100, 70);

		
	//이미지가 사이즈에 맞도록 리사이즈
		
		URL iconURL = getClass().getClassLoader().getResource("statusimages/health.png");
        ImageIcon icon = new ImageIcon(iconURL); //현재 이미지 불러오기
        Image img = icon.getImage() ;   //바꿀이미지에 현재 이미지를 넣기
        icon = new ImageIcon(img.getScaledInstance(70, 55, java.awt.Image.SCALE_SMOOTH));
        health = new JLabel(icon);
        
        URL icon1URL = getClass().getClassLoader().getResource("statusimages/hunger.png");
        ImageIcon icon1 = new ImageIcon(icon1URL); //현재 이미지 불러오기
        Image img1 = icon1.getImage() ;   //바꿀이미지에 현재 이미지를 넣기
        icon1 = new ImageIcon(img1.getScaledInstance(70, 50, java.awt.Image.SCALE_SMOOTH));
        hunger = new JLabel(icon1);
        
        URL icon2URL = getClass().getClassLoader().getResource("statusimages/hygiene.png");
        ImageIcon icon2 = new ImageIcon(icon2URL); //현재 이미지 불러오기
        Image img2 = icon2.getImage() ;   //바꿀이미지에 현재 이미지를 넣기
        icon2 = new ImageIcon(img2.getScaledInstance(70,55, java.awt.Image.SCALE_SMOOTH));
        hygiene = new JLabel(icon2);

	//-------------------라벨을 패널에 넣기----------------------------------------
		userP = new JPanel();
		userP.setBackground(new Color(0,0,0,0));
		alpacanameP = new JPanel();
		alpacanameP.setBackground(new Color(0,0,0,0));

		
		healthP = new JPanel();
		healthP.setBackground(new Color(0,0,0,0));
		hungerP = new JPanel();
		hungerP.setBackground(new Color(0,0,0,0));
		lifetimeP = new JPanel();
		lifetimeP.setBackground(new Color(0,0,0,0));
		hygieneP = new JPanel();
		hygieneP.setBackground(new Color(0,0,0,0));
		
		
	//초기 상단에 뜨는 배고픔 및 건강 아이콘 초기값 설정	
		URL healthIconURL = getClass().getClassLoader().getResource("statusimages/healthIcon50.png");
		URL hungerIconURL = getClass().getClassLoader().getResource("statusimages/hungerIcon50.png");
		healthIcon = new JLabel(new ImageIcon(healthIconURL));
		hungerIcon = new JLabel(new ImageIcon(hungerIconURL));
	


	// 세부패널들 세팅.(상단에 뜨는 배고픔 / 건강 아이콘)
		healthIconP = new JPanel();
		healthIconP.setOpaque(false);
		healthIconP.setBackground(new Color(0,0,0,0));
		healthIconP.setLayout(new FlowLayout(FlowLayout.LEFT));
		hungerIconP = new JPanel();
		hungerIconP.setOpaque(false);
		hungerIconP.setBackground(new Color(0,0,0,0));
		hungerIconP.setLayout(new FlowLayout(FlowLayout.LEFT));
		userP.setLayout(new GridLayout(1,5));
		alpacanameP.setLayout(new GridLayout(1,5));

	//패널들 사이즈, 로케이션 설정.
		userP.setSize(120,100);
		userP.setLocation(10,0);
		alpacanameP.setSize(200,100);
		alpacanameP.setLocation(150,0);
		lifetimeP.setSize(120,100);
		lifetimeP.setLocation(470, 0);
		healthP.setSize(70,70);
		healthP.setLocation(25,112);
		hungerP.setSize(100,200);
		hungerP.setLocation(10,180);
		hygieneP.setSize(100,200);
		hygieneP.setLocation(10, 240);	
		healthIconP.setSize(500,50);
		healthIconP.setLocation(105,125);
		hungerIconP.setSize(500,50);
		hungerIconP.setLocation(105,187);				

		
		// 클래스 배경 패널에 세부패널들 넣기.
		lifetimeP.add(lifetime);
		hungerP.add(hunger);
		healthP.add(health);
		alpacanameP.add(alpacaname);
		userP.add(user);
		playTimeL.add(score);	
		playTimeP.add(playTimeL);
		hygieneP.add(hygiene);
		hungerIconP.add(hungerIcon);
		healthIconP.add(healthIcon);
		
		//메인화면에 추가
		LabelPane.add(userP);
		LabelPane.add(alpacanameP);
		LabelPane.add(lifetimeP);
		LabelPane.add(playTimeP);
		LabelPane.add(healthP);
		LabelPane.add(hungerP);
		LabelPane.add(hygieneP);
		LabelPane.add(healthIconP);
		LabelPane.add(hungerIconP);
	}

	
	/**
	 * 
		@Method health
		@작성자 : 윤석훈
		@작성일 : 2019. 11. 18.
		@변경이력 :
		@메소드 설명 :건강값을 가져와서 이미지가 변경되도록 구현
	 */
	public void health() {
		
		 if(mainController.getHealth()==50) {
			 hygieneP.setOpaque(false);
				URL healthIconURL = getClass().getClassLoader().getResource("statusimages/healthIcon50.png");
			healthIcon.setIcon(new ImageIcon(healthIconURL));
		 }else if(mainController.getHealth()==40) {
			 hygieneP.setOpaque(false);
				URL healthIconURL = getClass().getClassLoader().getResource("statusimages/healthIcon40.png");
			healthIcon.setIcon(new ImageIcon(healthIconURL));
		}else if(mainController.getHealth()==30) {
			hygieneP.setOpaque(false);
			URL healthIconURL = getClass().getClassLoader().getResource("sstatusimages/healthIcon30.png");
			healthIcon.setIcon(new ImageIcon(healthIconURL));
		}else if(mainController.getHealth()==20) {
			hygieneP.setOpaque(false);
			URL healthIconURL = getClass().getClassLoader().getResource("statusimages/healthIcon20.png");
			healthIcon.setIcon(new ImageIcon(healthIconURL));
		}else if(mainController.getHealth()==10) {
			hygieneP.setOpaque(false);
			URL healthIconURL = getClass().getClassLoader().getResource("statusimages/healthIcon10.png");
			healthIcon.setIcon(new ImageIcon(healthIconURL));
			
		}
		
	}
	/**
	 * 
		@Method hungercount
		@작성자 : 윤석훈
		@작성일 : 2019. 11. 18.
		@변경이력 :
		@메소드 설명 : 배고픔이 시간에 따라 감소되도록 로직 구현
	 */
	public void hungercount() {
		hungercount -=10;
		ap.setHunger(hungercount);
		
		if(mainController.getHunger()==0) {
			mainController.gameHealthResult(-10); //배고픔이 0이되면 건강 -10후에 초기화
		}
	}
	
	/**
	 * 
		@Method hunger
		@작성자 : 윤석훈
		@작성일 : 2019. 11. 18.
		@변경이력 :
		@메소드 설명 :배고픔 값에 따라 이미지 수시로 변경되도록 구현
	 */
	public void hunger() {
		
		if(mainController.getHunger()==50) {
			hungerIcon.setOpaque(false);	
			URL hungerIconURL = getClass().getClassLoader().getResource("statusimages/hungerIcon50.png");
			hungerIcon.setIcon(new ImageIcon(hungerIconURL));
		}else if(mainController.getHunger()==40) {
			hungerIcon.setOpaque(false);	
			URL hungerIconURL = getClass().getClassLoader().getResource("statusimages/hungerIcon40.png");
			hungerIcon.setIcon(new ImageIcon(hungerIconURL));
		}else if(mainController.getHunger()==30) {
			hungerIcon.setOpaque(false);	
			URL hungerIconURL = getClass().getClassLoader().getResource("statusimages/hungerIcon30.png");
			hungerIcon.setIcon(new ImageIcon(hungerIconURL));
		}else if(mainController.getHunger()==20) {
			hungerIcon.setOpaque(false);	
			URL hungerIconURL = getClass().getClassLoader().getResource("statusimages/hungerIcon20.png");
			hungerIcon.setIcon(new ImageIcon(hungerIconURL));
		}else if(mainController.getHunger()==10) {
			hungerIcon.setOpaque(false);	
			URL hungerIconURL = getClass().getClassLoader().getResource("statusimages/hungerIcon10.png");
			hungerIcon.setIcon(new ImageIcon(hungerIconURL));
		}else if(mainController.getHunger()==0) {
			hungerIcon.setOpaque(false);	
			hungerIcon.setIcon(new ImageIcon());
			hungercount = 60; //배고픔이 -10씩 감소하기떄문에 50을 보여주기위해 60으로 초기화
			
		
		}
		
	}
	

	/**
	@Method :playTimer
	@작성자 : 박주완 
	@작성일 : 2019. 11. 18.
	@변경이력 :
	@메소드 설명 : 게임 실행후 1초마다 늘어나는 플레이 타임을 화면상단 중앙부에 표시하는 메소드.
 */
public void playTimer() {
	playTime++;
	
	
	score.setLocation(1600, 900);
	
	URL scoreURL = getClass().getClassLoader().getResource("statusimages/score.png");
	score = new JLabel(String.valueOf(playTime)+"s",new ImageIcon(scoreURL), JLabel.CENTER);
	score.setVerticalTextPosition(0);
	score.setHorizontalTextPosition(0);
	score.setOpaque(false);
	Font font2 = new Font("필기", Font.BOLD, 25);
	score.setFont(font2);
	score.setBounds(10, 11, 100, 70);
	playTimeL.add(score);
	
	
	if(playTime==300 || mainController.getHealth()==0) {
		playTimer.stop();
	}

}


 // 이후는 전부 getter , setter
 	public JPanel getLabelPane() {
 		return LabelPane;
 	}

 	public void setLabelPane(JPanel labelPane) {
 		LabelPane = labelPane;
 	}

 	public JLabel getHungerIcon() {
 		return hungerIcon;
 	}

 	public void setHungerIcon(JLabel hungerIcon) {
 		this.hungerIcon = hungerIcon;
 	}

 	public JLabel getHealthIcon() {
 		return healthIcon;
 	}

 	public void setHealthIcon(JLabel healthIcon) {
 		this.healthIcon = healthIcon;
 	}

 	public JLabel getHygieneIcon() {
 		return hygieneIcon;
 	}

 	public void setHygieneIcon(JLabel hygieneIcon) {
 		this.hygieneIcon = hygieneIcon;
 	}

 	public JLabel getUser() {
 		return user;
 	}

 	public void setUser(JLabel user) {
 		this.user = user;
 	}
 	
 	public JLabel getAlpacaname() {
 		return alpacaname;
 	}

 	public void setAlpacaname(JLabel alpacaname) {
 		this.alpacaname = alpacaname;
 	}

 	public JLabel getHealth() {
 		return health;
 	}

 	public void setHealth(JLabel health) {
 		this.health = health;
 	}

 	public JLabel getHunger() {
 		return hunger;
 	}

 	public void setHunger(JLabel hunger) {
 		this.hunger = hunger;
 	}

 	public JLabel getLifetime() {
 		return lifetime;
 	}

 	public void setLifetime(JLabel lifetime) {
 		this.lifetime = lifetime;
 	}

 	public JLabel getHygiene() {
 		return hygiene;
 	}

 	public void setHygiene(JLabel hygiene) {
 		this.hygiene = hygiene;
 	}


	public Timer getHungertimer() {
		return hungertimer;
	}


	public void setHungertimer(Timer hungertimer) {
		this.hungertimer = hungertimer;
	}


	public Timer getHungerIconTimer() {
		return hungerIconTimer;
	}


	public void setHungerIconTimer(Timer hungerIconTimer) {
		this.hungerIconTimer = hungerIconTimer;
	}


	public Timer getPlayTimer() {
		return playTimer;
	}


	public void setPlayTimer(Timer playTimer) {
		this.playTimer = playTimer;
	}


	public JLabel getPlayTimeL() {
		return playTimeL;
	}


	public void setPlayTimeL(JLabel playTimeL) {
		this.playTimeL = playTimeL;
	}


	public JPanel getPlayTimeP() {
		return playTimeP;
	}


	public void setPlayTimeP(JPanel playTimeP) {
		this.playTimeP = playTimeP;
	}


	public int getPlayTime() {
		return playTime;
	}


	public void setPlayTime(int playTime) {
		this.playTime = playTime;
	}
 	
 	
 	
 	
 	
}
