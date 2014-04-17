package sreeram.domain;

public class Person extends WordObject{
	private String country;
	private String name;
	private String gender;
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public Person ( String name , String gender , String country)
	{
		this.name = name;
		this.gender = gender;
		this.country = country;
	}
	public Person()
	{	
	}
	@Override
	public ObjType getType() {
		// TODO Auto-generated method stub
		return ObjType.PERSON;
		}
}
