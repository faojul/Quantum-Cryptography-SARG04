package qkd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Server {
	
	ArrayList<Basis> serverBasis=new ArrayList<>();
	ArrayList<Integer> serverValue=new ArrayList<>();
	Map<String,ArrayList> finalCheck =new HashMap();
	
	
	public ArrayList<Basis> choosenBasisByServer(int length){
		
		for(int i=0;i<length;i++){
			serverBasis.add(Basis.random());
		}
		return serverBasis;
	}
	public ArrayList<Integer> measuredValueByServer(ArrayList<Basis> clientBasis,
			ArrayList<Basis> serverBasis,ArrayList<Integer> clientValue){
			Random random=new Random();
		for(int i=0;i<clientBasis.size();i++){
			if(clientBasis.get(i)==serverBasis.get(i)){
				serverValue.add(clientValue.get(i));
			}
			else{
				serverValue.add(random.nextInt(2));
			}
		}
		return serverValue;
		
	}
	public Map<String, ArrayList> finalValueCheckedByServer(
			ArrayList<Basis> clientBasis,ArrayList<Integer> clientValue,
			ArrayList<Basis> clientAnotherBasis,ArrayList<Integer> clientAnotherValue,
			ArrayList<Basis> serverBasis,ArrayList<Integer> serverValue){
		
		ArrayList<Integer> finalServerValue=new ArrayList<>();
		ArrayList<Basis> finalServerBasis=new ArrayList<>();
		ArrayList<Integer> notMatched=new ArrayList<>();
		for(int i=0;i<clientBasis.size();i++){
			if(clientBasis.get(i)==serverBasis.get(i)){
				if(clientValue.get(i)==serverValue.get(i)){
					//System.out.println(i+":  Discard");
					notMatched.add(i);
				}
				else {
					finalServerBasis.add(Basis.flip(clientBasis.get(i)));
					finalServerValue.add(clientAnotherValue.get(i));
				}
			}
			else {
				if(clientAnotherValue.get(i)==serverValue.get(i)){
					//System.out.println(i+":  Discard");
					notMatched.add(i);
				}
				else {
					finalServerBasis.add(Basis.flip(clientAnotherBasis.get(i)));
					finalServerValue.add(clientValue.get(i));
				}
			}
			
		}
		finalCheck.put("finalServerValue", finalServerValue);
		finalCheck.put("finalServerBasis", finalServerBasis);
		finalCheck.put("notMatched",notMatched);
		
		return finalCheck;
		
	}

}
