package qkd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.swing.WindowConstants;
import javax.swing.text.StyledEditorKit.ForegroundAction;

import org.omg.CORBA.PUBLIC_MEMBER;

import com.sun.xml.internal.ws.api.streaming.XMLStreamReaderFactory.Default;

public class MAIN {
	
	

	public static void main(String[] args) {
		Scanner scanner=new Scanner(System.in);
		System.out.println("1: Generate Quantum Key of Specific length\n"
							+"2: Analysis input bit size vs output key length\n"
							+"3: Analysis Desired key length vs Number of Round");
		int process=scanner.nextInt();
		
		switch(process) {
		case 1:
			generateQKD();
			break;
		case 2:
			inputBitSizeVsOutputKeyLength();
			break;
		case 3:
			desiredKeyLengthVsNumberOfRounds();
			break;
		default:
			System.out.println("Wrong selection");
			System.exit(0);
		}
		
		
		
		
		
		
	}
	
	public static void generateQKD(){
		Scanner scanner=new Scanner(System.in);
		ArrayList<Integer> numberOfGeneratedKey=new ArrayList<>();
		ArrayList<Integer> RoundNeeded=new ArrayList<>();
		int clientFv=0;
		System.out.println("Enter the key length: ");
		int length=scanner.nextInt();
		
		System.out.println("Start Quantum Key Generation");
		System.out.println("Desired key length :"+length);
		
		
		while(clientFv!=length){
		//Server
		Server server=new Server();
		ArrayList<Basis> serverBasis=new ArrayList<>();
		ArrayList<Integer> serverValue=new ArrayList<>();
		ArrayList<Integer> serverFinalValue=new ArrayList<>();
		ArrayList<Basis> serverFinalBasis=new ArrayList<>();
		ArrayList<Integer> serverFinalNotMatched=new ArrayList<>();
		Map<String, ArrayList> serverFinalCheck=new HashMap();
		
		
		//client
		Client client=new Client();
		ArrayList<Basis> clientBasis=new ArrayList<>();
		ArrayList<Integer> clientValue=new ArrayList<>();
		ArrayList<Basis> clientAnotherBasis=new ArrayList<>();
		ArrayList<Integer> clientAnotherValue=new ArrayList<>();
		ArrayList<Integer> clientFinalValue=new ArrayList<>();
		ArrayList<Basis> clientFinalBasis=new ArrayList<>();
		Map<String, ArrayList> clientFinalCheck=new HashMap();
		
		
		
		
		//step one: Random value & polarized photon taken by Server (Alice) 
		System.out.println("\nStep-one: Random value & polarized photon taken by Server (Alice): ");
		clientBasis=client.choosenBasisByClient(4*length);
		clientValue=client.choosenValueByClient(4*length);
		System.out.println(clientBasis);
		System.out.println(clientValue);
		
		//step two: taken Random basis by Client(Bob) & measure the corresponding value
		System.out.println("\nStep two: taken Random basis by Client(Bob) & measure the corresponding value:");
		serverBasis=server.choosenBasisByServer(4*length);
		serverValue=server.measuredValueByServer(clientBasis, serverBasis, clientValue);
		System.out.println(serverBasis);
		System.out.println(serverValue);
		
		//step three: Announce by Client that he has got all the qubits;
		System.out.println("\nStep three: Announce by Client that he has got all the qubits");
		
		//step four: Generate new value & basis by Server & send his previous qubit & new qubit both to the Server 
		System.out.println("\nStep four: Generate new value & basis by Server & send his previous qubit & new qubit both to the Client");
		clientAnotherBasis=client.generateAnotherBasis(clientBasis);
		clientAnotherValue=client.generateAnotherValue(clientValue);
		
		
		
		
		System.out.println(clientAnotherBasis);
		System.out.println(clientAnotherValue);
		
		//step five: Discard the value & accept the value by comparison by the Client
		System.out.println("\nStep five: Discard the value & accept the value by comparison by the Client");
		serverFinalCheck=server.finalValueCheckedByServer(clientBasis, clientValue, clientAnotherBasis, clientAnotherValue, serverBasis, serverValue);
		serverFinalValue=serverFinalCheck.get("finalServerValue");
		serverFinalBasis=serverFinalCheck.get("finalServerBasis");
		serverFinalNotMatched=serverFinalCheck.get("notMatched");
		System.out.println(serverFinalBasis);
		System.out.println(serverFinalValue);
		System.out.println(serverFinalNotMatched);
		
		//step six: share the value & basis with Client to see all bits are right. if accuracy >75% accept otherwise discard
		System.out.println("\nStep six:After comparison of Client & Server");
		clientFinalCheck=client.ensuredByClient(serverFinalCheck, clientBasis, clientValue);
		clientFinalValue=clientFinalCheck.get("finalClientValue");
		clientFinalBasis=clientFinalCheck.get("finalClientBasis");
		//numberOfGeneratedKey.add(clientFinalValue.size());
		
		System.out.println(clientFinalBasis);
		System.out.println(clientFinalValue);
		System.out.println("output key length :"+clientFinalValue.size());
		clientFv=clientFinalValue.size();
		}
		
		//String s=scanner.next();
		//String 
		
		
		
	}
	public static void inputBitSizeVsOutputKeyLength(){
		
		
		ArrayList<Integer> numberOfGeneratedKey=new ArrayList<>();
		ArrayList<Integer> RoundNeeded=new ArrayList<>();
	
		for(int i=8;i<1000;i=i+8){
	
		
		//Server
		Server server=new Server();
		ArrayList<Basis> serverBasis=new ArrayList<>();
		ArrayList<Integer> serverValue=new ArrayList<>();
		ArrayList<Integer> serverFinalValue=new ArrayList<>();
		ArrayList<Basis> serverFinalBasis=new ArrayList<>();
		ArrayList<Integer> serverFinalNotMatched=new ArrayList<>();
		Map<String, ArrayList> serverFinalCheck=new HashMap();
		
		
		//client
		Client client=new Client();
		ArrayList<Basis> clientBasis=new ArrayList<>();
		ArrayList<Integer> clientValue=new ArrayList<>();
		ArrayList<Basis> clientAnotherBasis=new ArrayList<>();
		ArrayList<Integer> clientAnotherValue=new ArrayList<>();
		ArrayList<Integer> clientFinalValue=new ArrayList<>();
		ArrayList<Basis> clientFinalBasis=new ArrayList<>();
		Map<String, ArrayList> clientFinalCheck=new HashMap();
		
		
		
		
		//step one: Random value & polarized photon taken by Client (Alice) 
		
		clientBasis=client.choosenBasisByClient(i);
		clientValue=client.choosenValueByClient(i);
		
		
		//step two: taken Random basis by Server(Bob) & measure the corresponding value
		
		serverBasis=server.choosenBasisByServer(i);
		serverValue=server.measuredValueByServer(clientBasis, serverBasis, clientValue);
		
		//step three: Announce by Server that he has got all the qubits;
		
		
		//step four: Generate new value & basis by Client & send his previous qubit & new qubit both to the Server 
		
		clientAnotherBasis=client.generateAnotherBasis(clientBasis);
		clientAnotherValue=client.generateAnotherValue(clientValue);
		
		
		//step five: Discard the value & accept the value by comparison by the Server
		
		serverFinalCheck=server.finalValueCheckedByServer(clientBasis, clientValue, clientAnotherBasis, clientAnotherValue, serverBasis, serverValue);
		serverFinalValue=serverFinalCheck.get("finalServerValue");
		serverFinalBasis=serverFinalCheck.get("finalServerBasis");
		serverFinalNotMatched=serverFinalCheck.get("notMatched");
		
		
		//step six: share the value & basis with Client to see all bits are right. if accuracy >75% accept otherwise discard
		
		clientFinalCheck=client.ensuredByClient(serverFinalCheck, clientBasis, clientValue);
		clientFinalValue=clientFinalCheck.get("finalClientValue");
		clientFinalBasis=clientFinalCheck.get("finalClientBasis");
		numberOfGeneratedKey.add(clientFinalValue.size());
		
		
		
		}
		System.out.println("[ Length of generated key for value, i=i+8; initially i=8; ]");
		System.out.println(numberOfGeneratedKey);
		
		XYLineChartExample example = new XYLineChartExample("XY Chart Example ",numberOfGeneratedKey);
		//example.createDataset(RoundNeeded);
	      example.setSize(800, 400);
	      example.setLocationRelativeTo(null);
	      example.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	      example.setVisible(true);
		
		
	}
	public static void desiredKeyLengthVsNumberOfRounds(){
		Scanner scanner=new Scanner(System.in);
		ArrayList<Integer> numberOfGeneratedKey=new ArrayList<>();
		ArrayList<Integer> RoundNeeded=new ArrayList<>();
		// TODO Auto-generated method stub
		for(int i=8;i<1000;i=i+8){
		int count=0;
		int clientFv=0;
		//System.out.println("Enter the raw length: ");
		//int length=scanner.nextInt();
		
		
		while(clientFv!=i/4){
		//Server
		Server server=new Server();
		ArrayList<Basis> serverBasis=new ArrayList<>();
		ArrayList<Integer> serverValue=new ArrayList<>();
		ArrayList<Integer> serverFinalValue=new ArrayList<>();
		ArrayList<Basis> serverFinalBasis=new ArrayList<>();
		ArrayList<Integer> serverFinalNotMatched=new ArrayList<>();
		Map<String, ArrayList> serverFinalCheck=new HashMap();
		
		
		//client
		Client client=new Client();
		ArrayList<Basis> clientBasis=new ArrayList<>();
		ArrayList<Integer> clientValue=new ArrayList<>();
		ArrayList<Basis> clientAnotherBasis=new ArrayList<>();
		ArrayList<Integer> clientAnotherValue=new ArrayList<>();
		ArrayList<Integer> clientFinalValue=new ArrayList<>();
		ArrayList<Basis> clientFinalBasis=new ArrayList<>();
		Map<String, ArrayList> clientFinalCheck=new HashMap();
		
		
		
		
		//step one: Random value & polarized photon taken by Client (Alice) 
		clientBasis=client.choosenBasisByClient(i);
		clientValue=client.choosenValueByClient(i);
		
		
		//step two: taken Random basis by Server(Bob) & measure the corresponding value
		serverBasis=server.choosenBasisByServer(i);
		serverValue=server.measuredValueByServer(clientBasis, serverBasis, clientValue);
		
		
		//step three: Announce by Server that he has got all the qubits;
		//System.out.println("\nStep three: Announce by Client that he has got all the qubits");
		
		//step four: Generate new value & basis by Client & send his previous qubit & new qubit both to the Server 
		clientAnotherBasis=client.generateAnotherBasis(clientBasis);
		clientAnotherValue=client.generateAnotherValue(clientValue);
		
		
		//step five: Discard the value & accept the value by comparison by the Server
		serverFinalCheck=server.finalValueCheckedByServer(clientBasis, clientValue, clientAnotherBasis, clientAnotherValue, serverBasis, serverValue);
		serverFinalValue=serverFinalCheck.get("finalServerValue");
		serverFinalBasis=serverFinalCheck.get("finalServerBasis");
		serverFinalNotMatched=serverFinalCheck.get("notMatched");
		
		
		//step six: share the value & basis with Client to see all bits are right. if accuracy >75% accept otherwise discard
		clientFinalCheck=client.ensuredByClient(serverFinalCheck, clientBasis, clientValue);
		clientFinalValue=clientFinalCheck.get("finalClientValue");
		clientFinalBasis=clientFinalCheck.get("finalClientBasis");
		
		numberOfGeneratedKey.add(clientFinalValue.size());
		
		
		count++;
		clientFv=clientFinalValue.size();
		}
		//System.out.println(numberOfGeneratedKey);
		RoundNeeded.add(count);
		
			   
	
			
			//System.out.println(count);
	}
		System.out.println(RoundNeeded);
		
		XYLineChartExample example = new XYLineChartExample("XY Chart Example ",RoundNeeded);
		//example.createDataset(RoundNeeded);
	      example.setSize(800, 400);
	      example.setLocationRelativeTo(null);
	      example.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	      example.setVisible(true);
	}
	

}
