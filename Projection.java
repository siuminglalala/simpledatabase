package simpledatabase;
import java.util.ArrayList;

public class Projection extends Operator{
	
	ArrayList<Attribute> newAttributeList;
	private String attributePredicate;

	public Projection(Operator child, String attributePredicate){
		
		this.attributePredicate = attributePredicate;
		this.child = child;
		newAttributeList = new ArrayList<Attribute>();	
		
	}
	
	
	/**
     * Return the data of the selected attribute as tuple format
     * @return tuple
     */
	@Override
	public Tuple next(){
		//Delete the lines below and add your code here
		
		Tuple t = child.next();
		if(t!=null){
			for(int i=0; i<t.attributeList.size(); i++){
				
				if(t.getAttributeName(i).equals(attributePredicate)){
					
					newAttributeList.add(0, t.attributeList.get(i));
				}
			}
			t.attributeList=newAttributeList;
			return t;
		}
		return null;
		
	}
		

	
	/**
     * The function is used to get the attribute list of the tuple
     * @return attribute list
     */
	public ArrayList<Attribute> getAttributeList(){
		return child.getAttributeList();
	}

}