package simpledatabase;
import java.util.ArrayList;

public class Join extends Operator{

	private ArrayList<Attribute> newAttributeList;
	private String joinPredicate;
	ArrayList<Tuple> tuples1;

	
	//Join Constructor, join fill
	public Join(Operator leftChild, Operator rightChild, String joinPredicate){
		this.leftChild = leftChild;
		this.rightChild = rightChild;
		this.joinPredicate = joinPredicate;
		newAttributeList = new ArrayList<Attribute>();
		tuples1 = new ArrayList<Tuple>();
		
	}

	
	/**
     * It is used to return a new tuple which is already joined by the common attribute
     * @return the new joined tuple
     */
	//The record after join with two tables
	@Override
	public Tuple next(){
		//Delete the lines below and add your code here	
		Tuple l = leftChild.next();
		while(l!=null){
			tuples1.add(l);
			l = leftChild.next();
		}		
		
		Tuple r = rightChild.next();
		if(r!=null){
			for(int i=0; i<r.getAttributeList().size(); i++){
				for(int j=0; j<tuples1.size(); j++){
					for(int k=0; k<tuples1.get(j).getAttributeList().size(); k++){
						if(r.getAttributeValue(i).equals(tuples1.get(j).getAttributeValue(k))){
							newAttributeList = r.attributeList;
							newAttributeList.remove(i);
							newAttributeList.addAll(tuples1.get(j).getAttributeList());

							return new Tuple(newAttributeList);
						}
					}
				}
			}		
		}
		return null;
	}
	
	
	/**
     * The function is used to get the attribute list of the tuple
     * @return attribute list
     */
	public ArrayList<Attribute> getAttributeList(){
		if(joinPredicate.isEmpty())
			return child.getAttributeList();
		else
			return(newAttributeList);
	}

}