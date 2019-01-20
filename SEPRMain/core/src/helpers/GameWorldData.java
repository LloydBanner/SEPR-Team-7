package helpers;

import sprites.Enemy;
import sprites.items.Door;
import sprites.items.HealthConsumable;
import sprites.items.MissionItem;
import sprites.items.ShieldConsumable;
import sprites.items.SpeedConsumable;
import sprites.items.Weapon;



public class GameWorldData {
	
	
	private Enemy[] enemies;
	private Weapon[] weapons;
	private HealthConsumable[] healthItems;
	private SpeedConsumable[] speedItems;
	private MissionItem[] missionItems;
	private ShieldConsumable[] shieldItems;
	private Door[] doors; 
	private String map;
	private int xPlayerPosition;
	private int yPlayerPosition;
	private int[][] zombieCoordinates;
	private int[][] healthItemCoordinates;
	private int[][] speedItemCoordinates;
	private int[][] missionItemCoordinates;
	

	public GameWorldData(int[][] missionItemCoordinates, int[][] zombieCoordinates, int[][] healthItemCoordinates, int[][] speedItemCoordinates, 
						 int[][] sheildItemCooridinates, int xPlayerPosition, int yPlayerPosition, String map, int maxEnemies,
						 int maxHealthItems, int maxSpeedItems, int maxWeapons, int maxMissionItems, 
						 int maxCollisions, int maxDoors, int maxShieldItems) {
		
		this.missionItemCoordinates= missionItemCoordinates;
		this.zombieCoordinates= zombieCoordinates;
		this.healthItemCoordinates = healthItemCoordinates;
		this.speedItemCoordinates = speedItemCoordinates;
		this.enemies = new Enemy[maxEnemies];
		this.weapons = new Weapon[maxWeapons];
		this.healthItems = new HealthConsumable[maxHealthItems];
		this.speedItems = new SpeedConsumable[maxSpeedItems];
		this.missionItems = new MissionItem[maxMissionItems];
		this.doors = new Door[maxDoors];
		this.shieldItems = new ShieldConsumable[maxShieldItems];
		this.map = map;
		this.xPlayerPosition = xPlayerPosition;
		this.yPlayerPosition = yPlayerPosition;
		
	}
	
	public int getXPosition() {
		return this.xPlayerPosition;
	}
	
	public int getYPosition() {
		return this.yPlayerPosition;
	}
	
	public Enemy[] getEnemiesList() {
		return this.enemies;
	}
	
	public int[][] getZombieCoordinates(){
		return this.zombieCoordinates;
	}
	
	public Weapon[] getWeaponsList() {
		return this.weapons;
	}
	
	public ShieldConsumable[] getShieldItemList() {
		return this.shieldItems;
	}
	
	public HealthConsumable[] getHealthItemList() {
		return this.healthItems;
	}
	
	public int[][] getHeathConsumableCoordinates(){
		return this.healthItemCoordinates;
	}
	
	public SpeedConsumable[] getSpeedItemList() {
		return this.speedItems;
	}
	
	public int[][] getSpeedConsumableCoordinates(){
		return this.speedItemCoordinates;
	}
	
	
	public MissionItem[] getMissionItemList() {
		return this.missionItems;
	}
	
	public int[][] getMissionItemCoordinates(){
		return this.missionItemCoordinates;
	}
	
	public Door[] getDoorItemList() {
		return this.doors;
	}
	
	public String getMap() {
		return this.map;
	}
	
}