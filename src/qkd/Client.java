package qkd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Client {
	ArrayList<Integer> clientValue=new ArrayList<>();
	ArrayList<Basis> clientBasis=new ArrayList<>();
	
	
	public ArrayList<Basis> choosenBasisByClient(int length){
		
		for(int i=0;i<length;i++){
			clientBasis.add(Basis.random());
		}
		return clientBasis;
	}
	public ArrayList<Integer> choosenValueByClient(int length){
		Random random=new Random();
		for(int i=0;i<length;i++){
			clientValue.add(random.nextInt(2));
		}
		//System.out.println(clientBasis);
		//System.out.println(clientValue);
		return clientValue;
		
	}
	public ArrayList<Basis> generateAnotherBasis(ArrayList<Basis> basis){
		ArrayList<Basis> newBasis=new ArrayList<>();
		for (Basis basis2 : basis) {
			if(basis2==Basis.ORTHOGONAL)
				newBasis.add(Basis.DIAGONAL);
			else {
				newBasis.add(Basis.ORTHOGONAL);
			}
		}
		//System.out.println(newBasis);
		return newBasis;
	}
	public ArrayList<Integer> generateAnotherValue(ArrayList<Integer> value){
		ArrayList<Integer> newValue=new ArrayList<>();
		for (Integer integer : value) {
			if(integer==0)
				newValue.add(1);
			else {
				newValue.add(0);
			}
		}
		
		return newValue;
	}
	public Map<String, ArrayList> ensuredByClient(Map<String, ArrayList> serverFinalCheck,
			ArrayList<Basis>clientBasis,ArrayList<Integer>clientValue ){
		ArrayList<Basis> serverFinalBasis=new ArrayList<>();
		ArrayList<Integer> serverFinalValue=new ArrayList<>();
		ArrayList<Integer> serverNotMatched=new ArrayList<>();
		ArrayList<Basis> clientFinalBasis=new ArrayList<>();
		ArrayList<Integer> clientFinalValue=new ArrayList<>();
		Map<String, ArrayList> clientFinalCheck=new HashMap<>();
		int count=0;
		serverFinalValue=serverFinalCheck.get("finalServerValue");
		serverFinalBasis=serverFinalCheck.get("finalServerBasis");
		serverNotMatched=serverFinalCheck.get("notMatched");
		int j=0;
		for(int i=0;i<clientBasis.size();i++){
			if(serverNotMatched.contains(i))
				continue;
			else {
				if(clientBasis.get(i)==serverFinalBasis.get(j))
					clientFinalBasis.add(clientBasis.get(i));
				else
					count++;
				
			}
			j++;
		}
		j=0;
		/*if((count/clientFinalBasis.size())<.75){
			System.out.println("Eve Exist.... Discard Connection");
			System.exit(0);
		}*/
		count=0;
		
		for(int i=0;i<clientValue.size();i++){
			if(serverNotMatched.contains(i))
				continue;
			else {
				if(clientValue.get(i)==serverFinalValue.get(j))
					clientFinalValue.add(clientValue.get(i));
				else
					count++;
				
			}
			j++;
		}
		/*if((count/clientFinalValue.size())<.75){
			System.out.println("Eve Exist.... Discard Connection");
			System.exit(0);
		}*/
		
		clientFinalCheck.put("finalClientValue", clientFinalValue);
		clientFinalCheck.put("finalClientBasis", clientFinalBasis);
		
		return clientFinalCheck;
		
		
		
	}
	

}
