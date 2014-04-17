package sreeram.domain;

public class Game extends WordObject{
	private String gameName;

	public String getGameName() {
		return gameName;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}
	
	public Game(String name)
	{
		this.gameName = name;
	}
	
	public Game()
	{
		
	}

	@Override
	public ObjType getType() {
		// TODO Auto-generated method stub
		return ObjType.GAME;
	}

}
