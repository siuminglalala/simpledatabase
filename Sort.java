package simpledatabase;
import java.util.ArrayList;

public class Sort extends Operator{
	
	private ArrayList<Attribute> newAttributeList;
	private String orderPredicate;
	ArrayList<Tuple> tuplesResult;
	boolean sorted = false;
	
	public Sort(Operator child, String orderPredicate){
		this.child = child;
		this.orderPredicate = orderPredicate;
		newAttributeList = new ArrayList<Attribute>();
		tuplesResult = new ArrayList<Tuple>();
		
	}
	
	
	/**
     * The function is used to return the sorted tuple
     * @return tuple
     */
	@Override
	public Tuple next(){
		//Delete the lines below and add your code here
		Tuple temp;
		Tuple t = child.next();
		int s = -1;
		String s1;
		String s2;
		if(sorted==false){
			if(t!=null){
				for(s=0; s<t.attributeList.size(); s++){
					if(t.attributeList.get(s).attributeName.equals(orderPredicate)){
						break;
					}
				}
			}
			while(t!=null){
				tuplesResult.add(t);
				t = child.next();
			}
			for(int i=0; i<tuplesResult.size(); i++){
				s1 = tuplesResult.get(i).attributeList.get(s).attributeValue.toString();
				for(int j=i+1; j<tuplesResult.size(); j++){
					s2 = tuplesResult.get(j).attributeList.get(s).attributeValue.toString();
					if(s1.compareTo(s2) > 0){
						temp=tuplesResult.get(i);
						tuplesResult.set(i, tuplesResult.get(j));
						tuplesResult.set(j, temp);
					}
				}
			}
			sorted=true;
		}
		if(tuplesResult.size()>0){
			return tuplesResult.remove(0);
		}
		else{
			return null;
		}
	}
	
	/**
     * The function is used to get the attribute list of the tuple
     * @return attribute list
     */
	public ArrayList<Attribute> getAttributeList(){
		return child.getAttributeList();
	}

}