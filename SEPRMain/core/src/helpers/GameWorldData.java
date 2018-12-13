package helpers;

import java.util.List;

import sprites.Enemy;
import sprites.items.HealthConsumable;
import sprites.items.MissionItem;
import sprites.items.SpeedConsumable;
import sprites.items.Weapon;



public class GameWorldData {
	
	
	private Enemy[] enemies;
	private Weapon[] weapons;
	private HealthConsumable[] healthItems;
	private SpeedConsumable[] speedItems;
	private MissionItem[] missionItems;
	
	

	
	public void GameWOrldData(int maxEnemies, int maxHealthItems, int maxSpeedItems, int maxWeapons, int maxMissionItems, 
							  int maxCollisions) {
		
		
		this.enemies = new Enemy[maxEnemies];
		this.weapons = new Weapon[maxWeapons];
		this.healthItems = new HealthConsumable[maxHealthItems];
		this.speedItems = new SpeedConsumable[maxSpeedItems];
		this.missionItems = new MissionItem[maxMissionItems];
		
		
	}
	
	public Enemy[] getEnemiesList() {
		return this.enemies;
	}
	
	public Weapon[] getWeaponsList() {
		return this.weapons;
	}
	
	public HealthConsumable[] getHealthItemList() {
		return this.healthItems;
	}
	
	public SpeedConsumable[] getSpeedItemList() {
		return this.speedItems;
	}
	
	public MissionItem[] getMissionItemList() {
		return this.missionItems;
	}
	
}