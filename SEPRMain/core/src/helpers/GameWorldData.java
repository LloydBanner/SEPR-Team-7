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
	private String missionItemID;
	private String missionItemTexture;
	private ShieldConsumable[] shieldItems;
	private Door[] doors; 
	private String map;
	private int xPlayerPosition;
	private int yPlayerPosition;
	private int[][] zombieCoordinates;
	private int[][] healthItemCoordinates;
	private int[][] speedItemCoordinates;
	private int[][] missionItemCoordinates;
	private int[][] doorCoordinates;
	private int nextLevel;
	

	public GameWorldData(int[][] missionItemCoordinates, int[][] zombieCoordinates, int[][] healthItemCoordinates, int[][] speedItemCoordinates, 
						 int[][] sheildItemCooridinates, int[][] doorCoordinates, int xPlayerPosition, int yPlayerPosition, String map, int maxEnemies,
						 int maxHealthItems, int maxSpeedItems, int maxWeapons, int maxMissionItems, 
						 int maxCollisions, int maxDoors, int maxShieldItems, String missionItemTexture, String missionItemID, int nextLevel) {
		
		this.missionItemCoordinates= missionItemCoordinates;
		this.missionItemID = missionItemID;
		this.missionItemTexture = missionItemTexture;
		this.zombieCoordinates= zombieCoordinates;
		this.healthItemCoordinates = healthItemCoordinates;
		this.speedItemCoordinates = speedItemCoordinates;
		this.doorCoordinates = doorCoordinates;
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
		this.nextLevel = nextLevel;
		
	}
	
	public int getPlayerXPosition() {
		return this.xPlayerPosition;
	}
	
	public int getPlayerYPosition() {
		return this.yPlayerPosition;
	}
	
	public Enemy[] getEnemiesList() {
		return this.enemies;
	}
	
	public int[][] getZombieCoordinates(){
		return this.zombieCoordinates;
	}
	
	public int[][] getDoorCoordinates(){
		return this.doorCoordinates;
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
	
	public String getMissionItemID(){
		return this.missionItemID;
	}
	
	public String getMissionItemTexture() {
		return this.missionItemTexture;
	}
	
	public Door[] getDoorItemList() {
		return this.doors;
	}
	
	public String getMap() {
		return this.map;
	}
	
	public int getNextLevel() {
		return this.nextLevel;
	}
	
}