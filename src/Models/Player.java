package Models;

import rx.Observer;
import rx.functions.Action1;
import rx.subjects.PublishSubject;

public class Player {

	private Integer scoreValue;
	private Integer livesValue;
	public PublishSubject<String> name;
	private PublishSubject<Integer> score;
	private PublishSubject<Integer> lives;

	public Player(String name) {
		this.name = PublishSubject.create();
		this.score = PublishSubject.create();
		this.lives = PublishSubject.create();
		setName(name);
		setScore(0);
		scoreValue = 0;
		livesValue = 3;
	}
	
	public void subscribeToName(Observer<? super String> observer) {
		this.name.subscribe(observer);
	}
	
	public void subscribeToScore(Action1<Integer> scoreObserver) {
		this.score.subscribe(scoreObserver);
	}
	
	public void subscribeToLives(Action1<Integer> livesObserver) {
		this.lives.subscribe(livesObserver);
	}
	
	public void setName(String name) {
		this.name.onNext(name);
	}
	
	public Integer getScore() {
		return scoreValue;
	}
	
	public void setScore(Integer score) {
		if (score >= 0)  {
			this.score.onNext(score);
			this.scoreValue = score;
		}
	}
	
	public Integer getLives() {
		return livesValue;
	}
	
	public void decrementLives() {
		if (livesValue > 0) {
			livesValue--;
			lives.onNext(livesValue);
			
		}
	}
	
}
