package com.save_alpaca.view;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.net.URL;

import javax.swing.*;

import com.save_alpaca.controller.MainController;
import com.save_alpaca.model.vo.Alpaca;

public class SaveAlpaca extends JFrame {
	
	
	StatusLabel status = new StatusLabel();
	MainButtons mainButtons = new MainButtons();
	MainController mainController = new MainController();
	
	JFrame main = new JFrame("Save Alpaca");

    JLabel alpaca;
    JPanel alpacaPanel;
    JLabel backfiled;
    JPanel cleenPoop;
    JPanel backfiledPanel;
    JLabel[] poop = new JLabel[10];
    JPanel poopPanel;
    JLabel scoreL;
	JPanel scoreP;
	
	
	JLabel hygiene;
	JPanel hygieneP;
	private JLabel hygieneIcon; 
	private JPanel hygieneIconP; //똥이미지 변경을 위한 라벨 및 패널
	
    int poopCount = 0;


    public void setPoopCount(int poopCount) {  //똥의 갯수를 체크하기위해 set으로 받아준다.
        this.poopCount = poopCount;
    }

    int r = 1; //알파카 이미지 변경을 위한 변수 선언
    
    int playTime = 0;

    Timer timer = new Timer(200, actionEvent -> animation()); //알파카가 움직이는 타이머
    Timer poopTimer = new Timer(15000, actionEvent -> setPoopLocation()); //똥이 자동으로 생성되는 타이머
    Timer closeTimer = new Timer(10, actionEvent -> close()); //게임 종료 타이머
    Timer playTimer = new Timer(1000, actionEvent -> playTimer()); // 플레이시간 타이머

    public SaveAlpaca() {
    	playTimer.start();
        timer.start();
        poopTimer.start();
        closeTimer.start();
        
        
    	setLocationRelativeTo(null);
    	
    
        main.setLayout(null);
        main.setBounds(340, 120, 1200, 800);
        main.setResizable(false);
        
        URL backfiledURL = getClass().getClassLoader().getResource("main/background.png");
        backfiled = new JLabel(new ImageIcon(backfiledURL));
        backfiled.setLocation(0, 0);
        backfiled.setSize(1200, 800);
       
        URL alpacaURL = getClass().getClassLoader().getResource("main/alpaca1.png");
        alpaca = new JLabel(new ImageIcon(alpacaURL));
        alpaca.setLocation(400, 200);
        alpaca.setSize(200, 400);


        JPanel backfiledPanel = new JPanel();
        backfiledPanel.setLocation(0, 0);
        backfiledPanel.setSize(1200, 800);

        alpacaPanel = new JPanel();
        alpacaPanel.setBackground(new Color(0, 0, 0, 0));
        alpacaPanel.setLocation(100,440); // 알파카 위치 조정 (y축 +50)
        alpacaPanel.setSize(230, 400);


        poopPanel = new JPanel();
        poopPanel.setOpaque(false);
        poopPanel.setLocation(0, 680); // 똥 위치 조정 (y축 +100)
        poopPanel.setSize(1200, 200);
        poopPanel.setVisible(true);
        poopPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

       
        for (int i = 0; i < poop.length; i++) { //똥의 갯수를 배열만큼 만들어준다.
            poop[i] = new JLabel();
            URL icon2URL = getClass().getClassLoader().getResource("main/poop.png");
            ImageIcon icon2 = new ImageIcon(icon2URL); //현재 이미지 불러오기
            Image img2 = icon2.getImage() ;   //바꿀이미지에 현재 이미지를 넣기
            icon2 = new ImageIcon(img2.getScaledInstance(90,90, java.awt.Image.SCALE_SMOOTH)); //이미지 크기 맞춰주는 소스
            poop[i].setIcon(icon2);
            poop[i].setSize(150	,150);
            poop[i].setVisible(false);
            poopPanel.add(poop[i]);


        }

        
        URL icon3URL = getClass().getClassLoader().getResource("main/poopclean.png");
        ImageIcon icon3 = new ImageIcon(icon3URL); //현재 이미지 불러오기
        Image img3 = icon3.getImage() ;   //바꿀이미지에 현재 이미지를 넣기
        icon3 = new ImageIcon(img3.getScaledInstance(120, 90,  java.awt.Image.SCALE_SMOOTH ));
        JButton btn3 = new JButton(icon3);
        btn3.setLocation(940,210);
        btn3.setSize(120,100);
        btn3.setBorderPainted(false);
        btn3.setContentAreaFilled(false);
        btn3.setFocusPainted(false); //포커스표시설정 해제
        btn3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                removePoop();

            }
        });

        cleenPoop = new JPanel();
        cleenPoop.setBackground(new Color(255,0,0,0));
        cleenPoop.setLocation(1050,210);
        cleenPoop.setSize(120,100);
        cleenPoop.setOpaque(false);
        
        alpacaPanel.add(alpaca);
        backfiledPanel.add(backfiled);
        cleenPoop.add(btn3); //똥 치우기 버튼
       
        
        poopPanel.repaint();
        alpacaPanel.repaint();
        
        URL icon2URL = getClass().getClassLoader().getResource("statusimages/hygiene.png");
        ImageIcon icon2 = new ImageIcon(icon2URL); //현재 이미지 불러오기
        Image img2 = icon2.getImage() ;   //바꿀이미지에 현재 이미지를 넣기
        icon2 = new ImageIcon(img2.getScaledInstance(70,55, java.awt.Image.SCALE_SMOOTH));
        hygiene = new JLabel(icon2);
        
        hygieneP = new JPanel();
		hygieneP.add(hygiene);
		hygieneP.setBackground(new Color(0,0,0,0));
		
		hygieneIcon = new JLabel(new ImageIcon());
		
		hygieneIconP = new JPanel();
		hygieneIconP.add(hygieneIcon);
		hygieneIconP.setOpaque(false);
		hygieneIconP.setBackground(new Color(0,0,0,0));
		hygieneIconP.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		hygieneIconP.setSize(500,50);
		hygieneIconP.setLocation(105,251);
		
		//메인화면에 각종 패널들 추가
		main.add(hygieneIconP);
        main.getContentPane().add(alpacaPanel);
        main.getContentPane().add(poopPanel);
        main.add(mainButtons.getGamePanel1());
        main.add(mainButtons.getGamePanel2());
        main.add(status.getLabelPane());
        main.add(cleenPoop);
        main.add(backfiled);
        main.add(backfiledPanel);
        

        main.setVisible(true);
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
       
    }

    /**
     * 
    	@Method setPoopLocation
    	@작성자 : 천주원,윤석훈
    	@작성일 : 2019. 11. 18.
    	@변경이력 :
    	@메소드 설명 :똥이 나오는 위치조정 및 상단 똥 이미지 변경
     */
    public void setPoopLocation() {

    	if (poopCount<5) {
        	poop[poopCount].setSize(75,15);
        	Point point = poop[poopCount].getLocation();
        	poop[poopCount].setLocation((point.x) + (int)((Math.random() * 150)+10), (point.y) + (int)(Math.random()*-20)-1);

            poop[poopCount].setVisible(true);
            
        	 //poopCount에 따라 이미지가 변경되도록 로직 구현
            if(poopCount==0) {
            	hygieneIcon.setOpaque(false);	
                URL hygieneIconURL = getClass().getClassLoader().getResource("statusimages/hygieneicon10.png");
    			hygieneIcon.setIcon(new ImageIcon(hygieneIconURL));
    		}else if(poopCount==1) {
    			 hygieneIcon.setOpaque(false);	
    			 URL hygieneIconURL = getClass().getClassLoader().getResource("sstatusimages/hygieneicon20.png");
    			 hygieneIcon.setIcon(new ImageIcon(hygieneIconURL));
    		}else if(poopCount==2) {
    			 URL hygieneIconURL = getClass().getClassLoader().getResource("sstatusimages/hygieneicon30.png");
    			hygieneIcon.setIcon(new ImageIcon(hygieneIconURL));
    			hygieneIcon.setOpaque(false);	
    		}else if(poopCount==3) {
    			URL hygieneIconURL = getClass().getClassLoader().getResource("sstatusimages/hygieneicon40.png");
    			hygieneIcon.setIcon(new ImageIcon(hygieneIconURL));
    			hygieneIcon.setOpaque(false);	
    		}else if(poopCount==4) {
    			URL hygieneIconURL = getClass().getClassLoader().getResource("sstatusimages/hygieneicon50.png");
    			hygieneIcon.setIcon(new ImageIcon(hygieneIconURL));
    			hygieneIcon.setOpaque(false);	
    		}
        	
        	
            poopCount++;
        
          
           
        } else {
            for (int i = 0; i < poop.length; i++) {
                poop[i].setVisible(false);
       			hygieneIcon.setIcon(new ImageIcon()); //상단 아이콘이 안보이도록 수정
    			hygieneIcon.setOpaque(false);
            }
            
            mainController.PoopDamage(-10); //똥이 5개이상 쌓였을시에 건강을 하나 지운다.
            poopCount = 0; //똥이 5개이상 생성시에 다시 0개부터 생성되도록 초기화
        }
    }
    

    /**
     * 
    	@Method removePoop
    	@작성자 : 천주원,윤석훈
    	@작성일 : 2019. 11. 18.
    	@변경이력 :
    	@메소드 설명 : 똥치우기 버튼 클릭시 불러오는 메소드(똥을 지운다)
     */
    public void removePoop() {
        poopTimer.stop();
        setPoopCount(0);
        for (int i = 0; i < poop.length; i++) {
        	
            poop[i].setVisible(false);
            
   			hygieneIcon.setIcon(new ImageIcon()); //상단 아이콘이 안보이도록 수정
			hygieneIcon.setOpaque(false);
            
        }
        poopTimer.start();
  
    }


    
    /**
     * 
    	@Method animation
    	@작성자 : 천주원, 윤석훈
    	@작성일 : 2019. 11. 18.
    	@변경이력 :
    	@메소드 설명 :알파카가 좌우로 움직이도록 구현하는 메소드
     */
    public void animation() {
        Point point = alpacaPanel.getLocation();
        if (point.x > 900 || point.x < 100) {
            r *= -1;
            timer.restart();
            URL alpacaURL = getClass().getClassLoader().getResource("main/alpaca" + r + ".png");
            alpaca.setIcon(new ImageIcon(alpacaURL)); //알파카 위치조정 좌표
        }
        alpacaPanel.setLocation((point.x + (10 * r)), (point.y));
    }
  
    /**
     * 
    	@Method Name : playTimer
    	@작성자 : 윤석훈
    	@작성일 : 2019. 11. 18.
    	@변경이력 :
    	@메소드 설명 :게임 클리어시에 게임 클리어 화면 나오도록 구현
     */
    public void playTimer() {
    	playTime++;
        
        if(playTime==500) { //500초가 지나면 게임 클리어
        	 URL backfiledURL = getClass().getClassLoader().getResource("main/GameClear.png");
        	backfiled.setIcon(new ImageIcon(backfiledURL));
    		cleenPoop.setVisible(false);
    		alpacaPanel.setVisible(false);
    		poopPanel.setVisible(false);
    		mainButtons.getGamePanel1().setVisible(false);
    		mainButtons.getGamePanel2().setVisible(false);
    		status.getLabelPane().setVisible(false);
    		hygieneIcon.setVisible(false);

    		timer.stop();
    		poopTimer.stop();       
            closeTimer.stop();
            playTimer.stop();
        }
        
    	
    }
    

    
    /**
     * 
    	@Method Name : close
    	@작성자 : 윤석훈
    	@작성일 : 2019. 11. 18.
    	@변경이력 :
    	@메소드 설명 : 게임 종료시에 게임오버 화면 나오도록 구현
     */
    public void close() {
    	if(mainController.getHealth() == 0) {
       	 	URL backfiledURL = getClass().getClassLoader().getResource("main/GameOver.png");
    		backfiled.setIcon(new ImageIcon(backfiledURL));
    		cleenPoop.setVisible(false);
    		alpacaPanel.setVisible(false);
    		poopPanel.setVisible(false);
    		mainButtons.getGamePanel1().setVisible(false);
    		mainButtons.getGamePanel2().setVisible(false);
    		status.getLabelPane().setVisible(false);
    		hygieneIcon.setVisible(false);
    		

    		timer.stop();
    		poopTimer.stop();       
            closeTimer.stop();
            playTimer.stop();
            
        
    	}
    }

	public Timer getPoopTimer() {
		return poopTimer;
	}

	public void setPoopTimer(Timer poopTimer) {
		this.poopTimer = poopTimer;
	}


    
    
    
}
