package sreeram.domain;
enum ObjType {
    GAME,PERSON,RESULT
}
public abstract class WordObject {
	protected ObjType type;
	public abstract ObjType getType();
}
