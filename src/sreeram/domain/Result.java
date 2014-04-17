package sreeram.domain;

public class Result extends WordObject{
	
	private int position;
	private int gameID;
	private int personID;
	private String result;
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	public int getGameID() {
		return gameID;
	}
	public void setGameID(int gameID) {
		this.gameID = gameID;
	}
	public int getPersonID() {
		return personID;
	}
	public void setPersonID(int personID) {
		this.personID = personID;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	
	public Result(int personID ,String result , int gameID, int position)
	{
		this.result =result;
		this.gameID =gameID;
		this.position = position;
		this.personID = personID;
	}
	@Override
	public ObjType getType() {
		// TODO Auto-generated method stub
		return ObjType.RESULT;
	}
	

}
