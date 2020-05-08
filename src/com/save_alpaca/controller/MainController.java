package com.save_alpaca.controller;

import com.save_alpaca.model.vo.Alpaca;
import com.save_alpaca.view.SaveAlpaca;
import com.save_alpaca.view.game1.Start;
import com.save_alpaca.view.game2.MiniGame_02;

import java.awt.*;

public class MainController{
    private Alpaca alpaca = new Alpaca();

    public MainController() throws HeadlessException {
    }

    public void createAlpaca(String userName, String alpacaName) {
        alpaca.setUserName(userName);
		alpaca.setAlpacaName(alpacaName);
		// --> 알파카 객체 생성해서 이름들을 넘겨줌
		mainScreen(0);
	}

	public void mainScreen(int menuNum) {
			switch (menuNum) {
				case 0: new SaveAlpaca(); // 위 메소드 create Alpaca 에서 1을 받아서 savealpaca(메인화면) 으로 넘어옴
					break;
				case 1: new Start().StartMain();; //게임1 실행
					break;
				case 2: new MiniGame_02(); //게임2실행
//					break;
				default:
					break;
			}
//		}
	}

	public String getUserName() {
	    return alpaca.getUserName();
    }

    public String getAlpacaName() {
	    return alpaca.getAlpacaName();
    }

    public int getLifetime() {
        return alpaca.getLifetime();
    }

    public int getHealth() {
	    return alpaca.getHealth();
    }

    public int getHygiene() {
	    return alpaca.getHygiene();
    }

    public int getHunger() {
	    return alpaca.getHunger();
    }

	public void PoopDamage(int poopDamage) { // 똥으로부터 오는 데미지를 Alpaca 의
		// hygieneDamage 에 보내주는 메소드;
		alpaca.healthControl(poopDamage);
		// 화면 청결 하나 깎이게 거기에도 값 보내줘야됨 or 화면에 뜨는 청결칸을 alpaca 의 getHygiene 이랑 연동해서 깎이면 하나씩 없어지게!
	}

	public void gameHungerResult (int gameResult) {// 게임 결과를 Alpaca  의
		// healthControl 혹은 hungerControl 에 보내주는 메소드
		// 굳이 int gameResult 매개변수로 안받아와도 game1.textfield.getvalue 어쩌고저쩌고 이지랄해도 됨
		// if 게임 1 --> hungerControl()
		// if 게임 2 --> healthControl()
		alpaca.hungerControl(gameResult);
	}

	public void gameHealthResult(int gameResult) {
		alpaca.healthControl(gameResult);
	}
}