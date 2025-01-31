import java.util.Arrays;
import java.lang.Math;

public class Planet {

    private Integer[] board;
    private int turn;

    // Deve gestire la conversione da 'input' a 'board'
    // ESEMPIO: 'input' = '1001010' -> 'board' = [ 1, 0, 0, 1, 0, 1, 0 ]
    public Planet(String input){
    	setBoard(input);
    	setTurn(0);
    }

    public Integer[] getBoard() {
		return board;
	}

	public void setBoard(Integer[] board) {
		this.board = board;
	}
	
	public void setBoard(String input) {
		board=new Integer[input.length()];
		for (int i=0; i<input.length(); i++) {
			if (input.charAt(i)=='0')
				board[i]=0;
			else if (input.charAt(i)=='1')
				board[i]=1;
			else
				board[i]=(int)(Math.random() * 2);	// valore casuale tra 0 e 1
		}
		
		this.board = board;
	}

	public void setTurn(int turn) {
		this.turn = turn;
	}

	public void printBoard(){
		String s = "";
		for (int i=0; i<board.length; i++) {
			s+=board[i].toString();
		}
		System.out.println(s);
    }

    // restituisce true se c'è almeno un 1 nel board, altrimenti false
    public boolean lifeOnPlanet(){
    	for (int i=0; i<board.length; i++) {    		
    		if(board[i]==1)
    			return true;
    	}
    	return false;
    }
    
    // basandosi sul board attuale, lo aggiorna per il successivo turno sequendo le seguenti regole:
    // PER ogni elemento i del board SE:
    //  vicini sono entrambi 1 ed i è 0 -> i diventa 1 (nuova nascita)		1, 0, 1 -> 1, 1, 1
    //  vicini sono entrambi 1 ed i è 1 -> i diventa 0 (morte per sovrappopolazione)	1, 1, 1 -> 1, 0, 1
    //  vicini sono uno 0 e l'altro 1	 -> i non cambia (sopravvivenza)		1, 1, 0 -> 1, 1, 0
    //											0, 1, 1 -> 0, 1, 1
    //  vicini sono entrambi 0     -> i diventa 0 nel (morte per sottopopolazione)	0, 1, 0 -> 0, 0, 0
    //											0, 0, 0 -> 0, 0, 0
    // Il board va considerato CIRCOLARE (es: il vicino sx di i=0 è i=board.length-1), e il metodo deve anche aggiornare il count del turno
    public void oneTurn(){
    	Integer[] arrOrig = board.clone();
    	for (int i=0; i<arrOrig.length; i++) {
        	int sx = i-1;
        	int dx = i+1;
    		if (i==0)
    			sx=board.length-1;
    		if (i==board.length-1)
    			dx=0;
    		
    		if (arrOrig[sx]==1 && arrOrig[dx]==1 && arrOrig[i]==0)	// 101 => 111
    			board[i]=1;
    		if (arrOrig[sx]==1 && arrOrig[dx]==1 && arrOrig[i]==1)	// 111 => 101
    			board[i]=0;
    		if ((arrOrig[sx]==1 && arrOrig[dx]==0) || (arrOrig[sx]==0 && arrOrig[dx]==1)) // 1X0 o 0X1 => invariato
    			board[i]=board[i];
    		if (arrOrig[sx]==0 && arrOrig[dx]==0)
    			board[i]=0;
    		
    		this.turn++;
    	}
    }

    public int getTurn() {return this.turn;}

}
