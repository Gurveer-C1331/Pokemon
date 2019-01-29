package Game;

import java.util.Scanner;

/* Pokemon
 * Gurveer
 * December 18, 2017
 */
public class Pokemon{
	
	public static void main(String[] args){
		int [][] map = new int [100][100]; //array for the map
	
		int Mp; //used to stored random numbers that will be placed in the map
		//filling the map with random numbers 
		for(int r = 0; r < map.length; r++){
			for(int c = 0; c < map.length; c++){
				Mp = (int)(Math.random()*101+0);
				map[r][c] = map(Mp);	
			}
		}
		intro(); //prints out the intro	
		String direction; //variable used to contain the awsd movement key inputs		
		boolean x; //motion in the x and y direction
		boolean y;
		int dx = 0;
		int dy = 0;
		int position; //variable that takes in the element in the map array
		int stage = 1; //variable that controls the whole game loop
		int stage1 = 0; //controls the phase the game will enter
		Scanner input = new Scanner(System.in); //allows user inputs
		System.out.println("What is your name");
		String name = input.nextLine();
		//items 
		int pB = 10; //pokeballs
		int gB = 5; //greatballs
		int rZ = 10; //razzberries
		int gZ = 5; //goldenberries
		//pokemons
		int pokeTotal = 0;
		//xp & Level
		int xp = 0;
		int level = 0;
		int LlUp;
		
		//arrays for pokemon's names and all the stats
		String [] pNam = {"Pidgey","Weedle", "Squirtle", "Charmander", "Bulbasaur", "Phanpy", "Abra", "Gengar", "Wartortle", "Charmeleon", "Ivysaur", "Makuhita", "Pikachu", "Ampharos", "Raichu", "Hariyama", "Kadabra", "Donphan", "Chansey", "Snorlax", "Lapras", "Blastoise", "Charizard", "Venusaur", "Alakazam", "Tyranitar", "Mewtwo", "Moltres", "Zapdos", "Articuno"};
		double [][] stats ={/*pNum*/   {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30},
				 /*Type*/   {1,2,3,4,5,6,7,8,3,4,5,9,10,10,10,9,7,6,1,1,11,3,4,5,7,12,7,4,10,11},
				 /*HP*/ 	{40,40,44,39,45,90,25,60,59,58,60,72,35,90,60,144,40,90,250,160,130,79,78,80,55,100,106,90,90,90},
				 /*Attack*/ {45,35,48,52,49,60,20,65,63,64,62,60,55,75,90,120,35,120,5,110,85,83,84,82,50,134,110,100,90,85},
				 /*Defense*/{40,30,65,43,49,60,15,60,80,58,63,30,40,85,55,60,30,120,5,65,80,100,78,83,45,110,90,90,85,100},
				 /*Sp.ATT*/ {35,20,50,60,65,40,105,130,65,80,80,20,50,115,90,40,120,60,35,65,85,85,109,100,135,95,154,125,125,95},
				 /*Sp.Def*/ {35,20,64,50,65,40,55,75,80,65,80,30,50,90,80,60,70,60,105,110,95,105,85,100,95,100,90,85,90,125},
				 /*Speed*/  {56,50,43,65,45,40,90,110,58,80,60,25,90,55,110,50,105,50,50,30,60,78,100,80,120,61,130,90,100,85} };
		
		//Stores the pokeCode of all the pokemon caught by the user
		String [] PokeC = new String [1000];
		for(int i  = 0; i < PokeC.length; i++){
			PokeC[i] = " ";
		}
		//Stores all the information and stats of the pokemon caught by the user
		double [][] pokeC = new double [3][1000];
		for(int r = 0; r < pokeC.length; r++){
			for(int c = 0; c < pokeC.length; c++){
				pokeC[r][c] = 0.0;
			}
		}
		int pokeCaught = 0; //Used to automatically store information in both pokeC and PokeC one after another
		
		//array to store the total of each pokemon caught 
		int [] total = new int [30];

		//one big loop
		while(stage == 1){ 
			System.out.println("|"); 
			direction = input.next();
			if(direction.equalsIgnoreCase("qq")){ //ends the game
				stage = 0;
				input.close();
				System.out.println("You are about to end the game");
			}
			String Code;
			//displays instructions inorder to search the pokemon 
			if(direction.equalsIgnoreCase("search")){
				System.out.println("------- SEARCH -------");
				System.out.println("You can enter the pokeCode to bring up that pokemon's information");
				Code = input.next(); 
				search(Code, PokeC, pokeC);		
				System.out.println("----------------------");
			}
			//displays what's inside the backpack
			if(direction.equalsIgnoreCase("b")){
				backpack(pB, gB, rZ, gZ);
			}
			//displays the users xp and level and total pokemon caught 
			if(direction.equalsIgnoreCase("u")){
				user(name, level, xp, pokeTotal);
			}
			//displays users location on the map
			if(direction.equalsIgnoreCase("l")){
				location(dx, dy);
			}
			//displays the pokedex 
			if(direction.equalsIgnoreCase("p")){
				System.out.println("------- PokeDex -------");
				System.out.println("Total Pokemon Caught: " +pokeTotal);
				System.out.println("Pidgey: " +total[0]);
				System.out.println("Weedle: " +total[1]);
				System.out.println("Squirtle: " +total[2]);
				System.out.println("Charmander: " +total[3]);
				System.out.println("Bulbasaur: " +total[4]);
				System.out.println("Phanpy: " +total[5]);
				System.out.println("Abra: " +total[6]);
				System.out.println("Gengar: " +total[7]);
				System.out.println("Wartortle: " +total[8]);
				System.out.println("Charmeleon: " +total[9]);
				System.out.println("Ivysaur: " +total[10]);
				System.out.println("Makuhita: " +total[11]);
				System.out.println("Pikachu: " +total[12]);
				System.out.println("Ampharos: " +total[13]);
				System.out.println("Raichu: " +total[14]);
				System.out.println("Hariyama: " +total[15]);
				System.out.println("Kadabra: " +total[16]);
				System.out.println("Donphan: " +total[17]);
				System.out.println("Chansey: " +total[18]);
				System.out.println("Snorlax: " +total[19]);
				System.out.println("Lapras: " +total[20]);
				System.out.println("Blatoise: " +total[21]);
				System.out.println("Charizard: " +total[22]);
				System.out.println("Venusaur: " +total[23]);
				System.out.println("Alakazam: " +total[24]);
				System.out.println("Tyranitar: " +total[25]);
				System.out.println("Mewtwo: " +total[26]);
				System.out.println("Moltres: " +total[27]);
				System.out.println("Zapdos: " +total[28]);
				System.out.println("Articuno: " +total[29]);
				System.out.println("-----------------------");
			}
			//checks which direction is entered and moves the user according to the input
			if(direction.equalsIgnoreCase("a")||direction.equalsIgnoreCase("d")||direction.equalsIgnoreCase("w")||direction.equalsIgnoreCase("s")){
				x = moveX(direction); 
				y = moveY(direction); 
				if(x == true && direction.equalsIgnoreCase("a")){ 
					dx--;}
				if(x == true && direction.equalsIgnoreCase("d")){ 
					dx++;}
				if(y == true && direction.equalsIgnoreCase("w")){ 
					dy--;}
				if(y == true && direction.equalsIgnoreCase("s")){
					dy++;}
			}
			//check if the user has moved out of the map and puts them back in
			if(dx < 0){ 
				dx++;
				System.out.println("Can't go out of the map");}
			if(dy < 0){ 
				dy++;
				System.out.println("Can't go out of the map");}
			
			position = map[dy][dx]; 
			stage1 = position; 
			int OL = level;
			//trainer
			double STAB = 1;
			double Power = 0; //power of the move 
			double EFF = 1.0;

			if(level > 4 && stage1 == 1){ //battle with trainer happens when level is above 4
				int a; 
				int b; 
				String poke1 = null; 
				String poke2 = null; 
				String poke3 = null; 
				int pNum1 = 0; 
				int pNum2 = 0; 
				int pNum3 = 0; 
				//computer's pokemon to battle against user based on user's level
				if(level > 4 && level < 7){
					a = 0;
					b = 6;
					Power = 5.0;
					pNum1 = (int)(Math.random()*((b-a)+1)+a);
					for(int i = 0; i < pNam.length; i++){
						if(pNum1 == i){
							poke1 = pNam[i];}}
					pNum2 = (int)(Math.random()*((b-a)+1)+a);
					for(int i = 0; i < pNam.length; i++){
						if(pNum2 == i){
							poke2 = pNam[i];}}
					pNum3 = (int)(Math.random()*((b-a)+1)+a);
					for(int i = 0; i < pNam.length; i++){
						if(pNum3 == i){
							poke3 = pNam[i];}}
				}
				if(level > 6 && level < 14){
					a = 7;
					b = 15;
					Power = 13.0;
					pNum1 = (int)(Math.random()*((b-a)+1)+a);
					for(int i = 0; i < pNam.length; i++){
						if(pNum1 == i){
							poke1 = pNam[i];}}
					pNum2 = (int)(Math.random()*((b-a)+1)+a);
					for(int i = 0; i < pNam.length; i++){
						if(pNum2 == i){
							poke2 = pNam[i];}}
					pNum3 = (int)(Math.random()*((b-a)+1)+a);
					for(int i = 0; i < pNam.length; i++){
						if(pNum3 == i){
							poke3 = pNam[i];}}
				}
				if(level > 13){
					a = 16;
					b = 25;
					Power = 20.0;
					pNum1 = (int)(Math.random()*((b-a)+1)+a);
					for(int i = 0; i < pNam.length; i++){
						if(pNum1 == i){
							poke1 = pNam[i];}}
					pNum2 = (int)(Math.random()*((b-a)+1)+a);
					for(int i = 0; i < pNam.length; i++){
						if(pNum2 == i){
							poke2 = pNam[i];}}
					pNum3 = (int)(Math.random()*((b-a)+1)+a);
					for(int i = 0; i < pNam.length; i++){
						if(pNum3 == i){
							poke3 = pNam[i];}}
				}
				battleP1();
				int ivAp = 15; //attack ivs
				int ivDp = 15; //defense ivs
				int ivSp = 15; //stamina ivs
				//calculating all the stats for the first pokemon
				System.out.println("Pokemon 1 "+poke1);
				double att1 = 0.0; //attack
				double spATT1 = 0.0; //special attack
				double def1 = 0.0; //defense
				double spDEF1 = 0.0; //special defense
				double hp1 = 0.0; //hp
				double sp1 = 0.0; //stamina
				//retrieving stats from array
				for(int c = 0; c < stats[0].length; c++){
					if(pNum1 == c){
						hp1 = (double)stats[2][c];
						att1 = (double)stats[3][c];
						def1 = (double)stats[4][c];
						spATT1 = (double)stats[5][c];
						spDEF1 = (double)stats[6][c];
						sp1 = (double)stats[7][c];}}
				double hGA1 = 0.0;
				double lGA1 = 0.0;
				if(att1 > spATT1){
					hGA1 = att1;
					lGA1 = spATT1;}
				else{
					hGA1 = spATT1;
					lGA1 = att1;}
				double hGD1 = 0.0;
				double lGD1 = 0.0;
				if(def1 > spDEF1){
					hGD1 = def1;
					lGD1 = spDEF1;}
				else{
					hGD1 = spDEF1;
					lGD1 = def1;}		
				double scalATT1 = Math.round(2.0*((7.0/8.0)*hGA1)+((1.0/8.0)*lGA1));
				double spMD1 = 1.0+((sp1-75.0)/500.0);
				double baseATT1 = ((Math.round(scalATT1*spMD1*(13.0/15.0)))+(ivAp))*0.79030001; //attack
				double scalDEF1 = Math.round(2.0*((7.0/8.0)*hGD1)+((1.0/8.0)*lGD1));
				double baseDEF1 = ((Math.round(scalDEF1*spMD1*(13.0/15.0)))+(ivDp))*0.79030001; //defense
				double sta1 = ((hp1*2.0*(15.0/15.0))+(ivSp))*0.79030001; //stamina
				sta1 = Math.round(sta1*10.0)/10.0;
				double damage1 = Math.floor((.5*Power)*(baseATT1/baseDEF1)*STAB*EFF)+1; //damage
				damage1 = Math.round(damage1*10.0)/10.0;
				
				//calculating all the stats for the second pokemon
				System.out.println("Pokemon 2 "+poke2);
				double att2 = 0.0; 
				double spATT2 = 0.0; 
				double def2 = 0.0; 
				double spDEF2 = 0.0; 
				double hp2 = 0.0; 
				double sp2 = 0.0;
				//retrieving stats from array
				for(int c = 0; c < stats[0].length; c++){
					if(pNum2 == c){
						hp2 = (double)stats[2][c];
						att2 = (double)stats[3][c];
						def2 = (double)stats[4][c];
						spATT2 = (double)stats[5][c];
						spDEF2 = (double)stats[6][c];
						sp2 = (double)stats[7][c];}}
				double hGA2 = 0.0;
				double lGA2 = 0.0;
				if(att2 > spATT2){
					hGA2 = att2;
					lGA2 = spATT2;}
				else{
					hGA2 = spATT2;
					lGA2 = att2;}
				double hGD2 = 0.0;
				double lGD2 = 0.0;
				if(def2 > spDEF2){
					hGD2 = def2;
					lGD2 = spDEF2;}
				else{
					hGD2 = spDEF2;
					lGD2 = def2;}
				double scalATT2 = Math.round(2.0*((7.0/8.0)*hGA2)+((1.0/8.0)*lGA2));
				double spMD2 = 1.0+((sp2-75.0)/500.0);
				double baseATT2 = ((Math.round(scalATT2*spMD2*(13.0/15.0)))+(ivAp))*0.79030001; //attack
				double scalDEF2 = Math.round(2.0*((7.0/8.0)*hGD2)+((1.0/8.0)*lGD2));
				double baseDEF2 = ((Math.round(scalDEF2*spMD2*(13.0/15.0)))+(ivDp))*0.79030001; //defense
				double sta2 = ((hp2*2.0*(15.0/15.0))+(ivSp))*0.79030001; //stamina
				sta2 = Math.round(sta2*10.0)/10.0;
				double damage2 = Math.floor((.5*Power)*(baseATT2/baseDEF2)*STAB*EFF)+1; //damage
				damage2 = Math.round(damage2*10.0)/10.0;
				
				//calculating all the stats for the third pokemon
				System.out.println("Pokemon 3 "+poke3);
				double att3 = 0.0;
				double spATT3 = 0.0;
				double def3 = 0.0;
				double spDEF3 = 0.0; 
				double hp3 = 0.0; 
				double sp3 = 0.0;
				//retrieving pokemon stats from the array
				for(int c = 0; c < stats[0].length; c++){
					if(pNum1 == c){
						hp3 = (double)stats[2][c];
						att3 = (double)stats[3][c];
						def3 = (double)stats[4][c];
						spATT3 = (double)stats[5][c];
						spDEF3 = (double)stats[6][c];
						sp3 = (double)stats[7][c];}}
				double hGA3 = 0.0;
				double lGA3 = 0.0;
				if(att3 > spATT3){
					hGA3 = att3;
					lGA3 = spATT3;}
				else{
					hGA3 = spATT3;
					lGA3 = att3;}
				double hGD3 = 0.0;
				double lGD3 = 0.0;
				if(def3 > spDEF3){
					hGD3 = def3;
					lGD3 = spDEF3;}
				else{
					hGD3 = spDEF3;
					lGD3 = def3;}
				double scalATT3 = Math.round(2.0*((7.0/8.0)*hGA3)+((1.0/8.0)*lGA3));
				double spMD3 = 1.0+((sp3-75.0)/500.0);
				double baseATT3 = ((Math.round(scalATT3*spMD3*(13.0/15.0)))+(ivAp))*0.79030001; //attack
				double scalDEF3 = Math.round(2.0*((7.0/8.0)*hGD3)+((1.0/8.0)*lGD3));
				double baseDEF3 = ((Math.round(scalDEF3*spMD3*(13.0/15.0)))+(ivDp))*0.79030001; //defense
				double sta3 = ((hp3*2.0*(15.0/15.0))+(ivSp))*0.79030001; //stamina 
				sta3 = Math.round(sta3*10.0)/10.0;
				double damage3 = Math.floor((.5*Power)*(baseATT3/baseDEF3)*STAB*EFF)+1;
				damage3 = Math.round(damage3*10.0)/10.0;
				battleP2();
				
				//frist pokemon to choose for battle (name and stats)
				String pokeNam1 = null; 
				double Sta1 = 0;
				double pow1 = 0;
				//second pokemon to choose for battle (name and stats)
				String pokeNam2 = null;
				double Sta2 = 0;
				double pow2 = 0;
				//third pokemon to choose for battle (name and stats)
				String pokeNam3 = null;
				double Sta3 = 0;
				double pow3 = 0;
				int sBattle = 0;
				System.out.println("Choose your pokemons");
				
				//first pokemon to search to get the battle stats needed to battle from the file
				while(sBattle == 0){
					System.out.println("Enter your first pokemon code");
					pokeNam1 = input.next();
					if(pokeNam1.equalsIgnoreCase("r")){
						sBattle = 1;}//doesn't go into the while loop for first pokemon
					else{
						int s1 = -1;
						for(int i = 0; i < PokeC.length; i++){
							if(PokeC[i].equalsIgnoreCase(pokeNam1)){
								s1 = i;}}
						if(s1 == -1){
							System.out.println("Incorrect search");}
						else{
							 Sta1 = pokeC[0][s1];
							 pow1 = pokeC[1][s1];
							 sBattle = 1;}}
					}

				//second pokemon to search to get the battle stats needed to battle from the file
				if(pokeNam1.equalsIgnoreCase("r")){
					sBattle = 1;}
				else{
					sBattle = 0;}
				while(sBattle == 0){
					System.out.println("Enter your second pokemon code");
					pokeNam2 = input.next();
					if(pokeNam2.equalsIgnoreCase("r")){
						sBattle = 1;} //doesn't go into the while loop for first pokemon
					else{
						int s1 = -1;
						for(int i = 0; i < PokeC.length; i++){
							if(PokeC[i].equalsIgnoreCase(pokeNam2)){
								s1 = i;}}
						if(s1 == -1){
							System.out.println("Incorrect search");
							sBattle = 0;}
						else{
							 Sta1 = pokeC[0][s1];
							 pow1 = pokeC[1][s1];
							 sBattle = 1;}
						if(pokeNam2.equalsIgnoreCase(pokeNam1)){
							System.out.println("Incorrect search");
							sBattle = 0;}}
					}
				
				//third pokemon to search to get the battle stats needed to battle from the file
				if(pokeNam1.equalsIgnoreCase("r")){
					sBattle = 1;}
				else{
					sBattle = 0;}
				while(sBattle == 0){
					System.out.println("Enter your third pokemon code");
					pokeNam3 = input.next();
					if(pokeNam2.equalsIgnoreCase("r")){
						sBattle = 1;} //doesn't go into the while loop for first pokemon
					else{
						sBattle = 0;
						int s1 = -1;
						for(int i = 0; i < PokeC.length; i++){
							if(PokeC[i].equalsIgnoreCase(pokeNam3)){
								s1 = i;}}
						if(s1 == -1){	
							System.out.println("Incorrect search");}
						else{
							 Sta1 = pokeC[0][s1];
							 pow1 = pokeC[1][s1];
							 sBattle = 1;}
							if(pokeNam3.equalsIgnoreCase(pokeNam1)||pokeNam3.equalsIgnoreCase(pokeNam2)){
								System.out.println("Incorrect search");}}
					}
				if(pokeNam1.equalsIgnoreCase("r")||pokeNam2.equalsIgnoreCase("r")||pokeNam3.equalsIgnoreCase("r")){
					sBattle = 0;
					System.out.println("You have ran away");}
				
					String battle; //string to enter the moves
					int Bcount = 0; //used to count till charge move is ready
					int u = 0; //value to keep track of the while loop of the user
					int o = 0; //value to keep track of the while loop of the opponent
					//user
					String uPoke = pokeNam1; //string variable used for the name of the pokemon
					double uSta = Sta1; //value for the pokemons stamina
					double uPow = pow1; //value for the power or damage of the pokemon
					int uB = 0; //value used to see if all the pokemons are defeated
					//computer or trainer
					String oPoke = poke1;
					double oSta = sta1;
					double oPow = damage1;
					int oB = 0;
					
					//battle while loop
					int intro = 0;
					while(sBattle == 1){
						while(intro == 0){
							System.out.println(uPoke+" will battle against "+oPoke); //prints the two pokemon that will battle
							intro = 1;}
						while(u == 0){ //while loop for the user's turn
							System.out.println("Enter a move");
							battle = input.next();
							//checks if the charge move is ready to use
							if(Bcount >= 5){
								if(battle.equalsIgnoreCase("s")){
									oSta = oSta - (uPow*2);
									System.out.println("The opponents pokemon has "+oSta+" stamina");
									Bcount = 0;}	
								if(battle.equalsIgnoreCase("a")){
									oSta = oSta - uPow;
									System.out.println("The opponents pokemon has "+oSta+" stamina");
									Bcount++;}}
							else{
								if(battle.equalsIgnoreCase("s")&&Bcount < 5){
									System.out.println("Charge move is not ready yet");}
								if(battle.equalsIgnoreCase("a")){
									oSta = oSta - uPow;
									System.out.println("The opponents pokemon has "+oSta+" stamina");
									Bcount++;}}
							if(Bcount >= 5){
								System.out.println("You can use the charge move by pressing s on your next turn");}
							o = 1;
							//if the user has defeated the computer's pokemon
							if(oSta < 1){
								System.out.println("You have defeated the opponents pokemon");
								oB++;
								if(oB == 1){
									oPoke = poke2;
									oSta = sta2;
									oPow = damage2;
									System.out.println("You will play against "+oPoke);}
								if(oB == 2){
									oPoke = poke3;
									oSta = sta3;
									oPow = damage3;
									System.out.println("You will play against "+oPoke);}
								if(oB == 3){
									System.out.println("You win the battle");
									sBattle = 3;
									o = 0;}}
							u = 1;
						}
						//while loop for when the opponent's turn
						while(o == 1){
								System.out.println();
								System.out.println("Opponent will go");
								uSta = uSta - oPow; 
								System.out.println("Your pokemon has "+uSta+" stamina");
								//if computer defeats one of the user's pokemon
								if(uSta < 1){
									System.out.println("Opponent has beaten your pokemon");
									uB++;
									//based on the number of pokemons beaten the user will have change to its second or third pokemon
									//second pokemon
									if(uB == 1){
										uPoke = pokeNam2;
										uSta = Sta2;
										uPow = pow2;
										System.out.println("Your pokemon will be "+uPoke);}
									//third pokemon
									if(uB == 2){
										uPoke = pokeNam3;
										uSta = Sta3;
										uPow = pow3;
										System.out.println("Your pokemon will be "+uPoke);}
									//this is when the computer has beaten all three of the users pokemons
									if(uB == 3){
										System.out.println("You lost the battle");
										o = 0;
										u = 1;
										sBattle = 3;}}
								u = 0;
								o = 0;}}
					}
			
			//items
			if(stage1 >= 2 && stage1 < 6 ){ 
				if(stage1 == 2){ //add one to pokeballs
					pB++;
					map[dy][dx] = 0; 
					xp = xp + 10;
					System.out.println("You have recieved a pokeball");}
				if(stage1 == 3){ //add one to greatballs
					gB++;
					map[dy][dx] = 0; 
					xp = xp + 20;
					System.out.println("You have recieved a greatball");}
				if(stage1 == 4){ //add one to razzberries
					rZ++;
					map[dy][dx] = 0; 
					xp = xp + 10;
					System.out.println("You have recieved a razzberry");}
				if(stage1 == 5){ //add one to goldenberries
					gZ++;
					map[dy][dx] = 0; 
					xp = xp + 20;
					System.out.println("You have recieved a goldenberry");}
			}

			//catching
			//stats variable for pokemon
			double cpp = 0; //cp
			double stap = 0; //stamina
			double baseDEFp = 0; //attack
			double baseATTp = 0; //defense
			int ivA = 0; //attack ivs
			int ivD = 0; //defense ivs
			int ivS = 0; //stamina ivs
			String pokeCode = ""; //gives each pokemon caught a unique code
			int catchRate = 0; //catchrate of the pokemon
			String poke = ""; //first part of the pokeCode
			if(stage1 > 5 && stage1 < 36 ){ 
				//based on the pokemon a catchrate will be assigned
				if(stage1 == 6){ 
					catchRate = 40;
					poke = "Pidgey";}
				if(stage1 == 7){ 
					catchRate = 40;
					poke = "Weedle";}
				if(stage1 == 8){ 
					catchRate = 6;
					poke = "Squirtle";}
				if(stage1 == 9){ 
					catchRate = 6;
					poke = "Charmander";}
				if(stage1 == 10){ 
					catchRate = 6;
					poke = "Bulbasaur";}
				if(stage1 == 11){ 
					catchRate = 16;
					poke = "Phanpy";}
				if(stage1 == 12){ 
					catchRate = 26;
					poke = "Abra";}
				if(stage1 == 13){ 
					catchRate = 6;
					poke = "Gengar";}
				if(stage1 == 14){ 
					catchRate = 6;
					poke = "Wartortle";}
				if(stage1 == 15){ 
					catchRate = 6;
					poke = "Charmeleon";}
				if(stage1 == 16){ 
					catchRate = 6;
					poke = "Ivysaur";}
				if(stage1 == 17){ 
					catchRate = 24;
					poke = "Makuhita";}
				if(stage1 == 18){ 
					catchRate = 10;
					poke = "Pikachu";}
				if(stage1 == 19){
					catchRate = 5;
					poke = "Ampharos";}
				if(stage1 == 20){ 
					catchRate = 8;
					poke = "Raichu";}
				if(stage1 == 21){ 
					catchRate = 15;
					poke = "Hariyama";}
				if(stage1 == 22){ 
					catchRate = 13;
					poke = "Kadabra";}
				if(stage1 == 23){ 
					catchRate = 8;
					poke = "Donphan";}
				if(stage1 == 24){ 
					catchRate = 4;
					poke = "Chansey";}
				if(stage1 == 25){ 
					catchRate = 4;
					poke = "Snorlax";}
				if(stage1 == 26){ 
					catchRate = 6;
					poke = "Lapras";}
				if(stage1 == 27){ 
					catchRate = 6;
					poke = "Blastoise";}
				if(stage1 == 28){ 
					catchRate = 6;
					poke = "Charizard";}
				if(stage1 == 29){ 
					catchRate = 6;
					poke = "Venusaur";}
				if(stage1 == 30){ 
					catchRate = 7;
					poke = "Alakazam";}
				if(stage1 == 31){ 
					catchRate = 6;
					poke = "Tyranitar";}
				if(stage1 == 32){ 
					catchRate = 1;
					poke = "Mewtwo";}
				if(stage1 == 33){ 
					catchRate = 1;
					poke = "Moltres";}
				if(stage1 == 34){ 
					catchRate = 1;
					poke = "Zapdos";}
				if(stage1 == 35){ 
					catchRate = 1;
					poke = "Articuno";}
				int phase = 0; //catching phase
				String berry; //berry input
				String ball; //ball input
				int rate; //total catch rate
				int chance; //catch range
				int ChRemain; 
				double chRun; //run range
				double chB; //break range
				int controls = 0; //used to displays instructions once
				
				while (phase == 0){
					while(controls == 0){
						//catching instructions
						System.out.println(poke+" has appeared");
						System.out.println("----- Catching Controls -----");
						System.out.println("To enter a berry:");
						System.out.println("q = to not use a berry");
						System.out.println("r = razzberry");
						System.out.println("z = goldenberry");
						System.out.println("To enter a ball:");
						System.out.println("n = to run away");
						System.out.println("p = pokeball");
						System.out.println("g = greatball");
						System.out.println("-----------------------------");
						controls = 1;
					}
					rate = catchRate; 
					//berry choosing
					int b = 0;
					while(b == 0){ 
						System.out.println("Enter a berry you want to use. If not press q");
						berry = input.next();
						if(berry.equalsIgnoreCase("r")){ 
							rate = rate + 10; //razzberry adds 10 to the rate
							rZ--; 
							b = 1;
							if(rZ == -1){
								System.out.println("You are out of razzberries");
								rate = 0; 
								rZ = 0;
								b = 0;}}
						if(berry.equalsIgnoreCase("z")){ 
							rate = rate + 20; //goldenberry adds 20 to the rate
							gZ--;
							b = 1;
							if(gZ == -1){ 
								System.out.println("You are out of goldenberries"); 
								rate = 0;
								gZ = 0;
								b = 0;}}
						if(berry.equalsIgnoreCase("q")){
							b = 1;}
					}
					//ball choosing 
					int bll = 0;
					while(bll == 0){
						System.out.println("Enter a ball you want to use. If you want to run away press n");
						ball = input.next();
						if(pB == 0 && gB == 0){
							phase = 1;
							System.out.println("You can't catch the pokemon");}
						if(ball.equalsIgnoreCase("p")){ 
							pB--;
							bll = 1;
							if(pB == 0){ 
								System.out.println("You are out of pokeballs");
								pB = 0;
								bll = 0;}}
						if(ball.equalsIgnoreCase("g")){ 
							rate = rate + 20; //greatball adds 20 to the rate
							gB--;
							bll = 1;
							if(gB == 0){ 
								System.out.println("You are out of greatballs");
								rate = 0;
								gB = 0;
								bll = 0;}}
						if(ball.equalsIgnoreCase("n")){ //when user wants to run away from the pokemon
							bll = 1;
							phase = 1;
							System.out.println("You have run away.");}
					}
					if(phase == 0){
						chance = (int)(Math.random()*101+0);
						ChRemain = 100 - rate;
						chB = ChRemain/2.0;
						chRun = ChRemain/2.0;
						
						if(chance < rate+1){ //pokemon is caught
							System.out.println("You have caught "+poke );
							pokeTotal++;
							phase = 1;
							int pN = 0;
							double power = 0; //power value for the pokemons
							String pCode = ""; //makes up the total code
							int PCode = 0;
							//based on the pokemon caught the variable values will vary
							if(stage1 == 6){
								total[0]++;	
								xp = xp + 50;
								pN = 0;
								pCode = "Pidgey";
								PCode = total[0];
								power = 5.0;}
							if(stage1 == 7){ 
								total[1]++;	
								xp = xp + 50;
								pN = 1;
								pCode = "Weedle";
								PCode = total[1];
								power = 5.0;}
							if(stage1 == 8){ 
								total[2]++;	
								xp = xp + 50;
								pN = 2;
								pCode = "Squirtle";
								PCode = total[2];
								power = 7.0;}
							if(stage1 == 9){ 
								total[3]++;
								xp = xp + 50;
								pN = 3;
								pCode = "Charmander";
								PCode = total[3];
								power = 7.0;}
							if(stage1 == 10){ 
								total[4]++;	
								xp = xp + 50;
								pN = 4;
								pCode = "Bulbasaur";
								PCode = total[4];
								power = 7.0;}
							if(stage1 == 11){ 
								total[5]++;
								xp = xp + 50;
								pN = 5;
								pCode = "Phanpy";
								PCode = total[5];
								power = 5.0;}
							if(stage1 == 12){ 
								total[6]++;		
								xp = xp + 50;
								pN = 6;
								pCode = "Abra";
								PCode = total[6];
								power = 5.0;}
							if(stage1 == 13){ 
								total[7]++;		
								xp = xp + 100;
								pN = 7;
								pCode = "Gengar";
								PCode = total[7];
								power = 8.0;}
							if(stage1 == 14){ 
								total[8]++;
								xp = xp + 100;
								pN = 8;
								pCode = "Wartortle";
								PCode = total[8];
								power = 10.0;}
							if(stage1 == 15){ 
								total[9]++;
								xp = xp + 100;
								pN = 9;
								pCode = "Charmeleon";
								PCode = total[9];
								power = 10.0;}
							if(stage1 == 16){ 
								total[10]++;		
								xp = xp + 100;
								pN = 10;
								pCode = "Ivysaur";
								PCode = total[10];
								power = 10.0;}
							if(stage1 == 17){ 
								total[11]++;	
								xp = xp + 100;
								pN = 11;
								pCode = "Makuhita";
								PCode = total[11];
								power = 8.0;}
							if(stage1 == 18){ 
								total[12]++;	
								xp = xp + 100;
								pN = 12;
								pCode = "Pikachu";
								PCode = total[12];
								power = 8.0;}
							if(stage1 == 19){ 
								total[13]++;	
								xp = xp + 150;
								pN = 13;
								pCode = "Ampharos";
								PCode = total[13];
								power = 15.0;}
							if(stage1 == 20){ 
								total[14]++;	
								xp = xp + 150;
								pN = 14;
								pCode = "Raichu";
								PCode = total[14];
								power = 15.0;}
							if(stage1 == 21){ 
								total[15]++;	
								xp = xp + 150;
								pN = 15;
								pCode = "Hariyama";
								PCode = total[15];
								power = 15.0;}
							if(stage1 == 22){ 
								total[16]++;	
								xp = xp + 150;
								pN = 16;
								pCode = "Kadabra";
								PCode = total[16];
								power = 15.0;}
							if(stage1 == 23){ 
								total[17]++;
								xp = xp + 150;
								pN = 17;
								pCode = "Donphan";
								PCode = total[17];
								power = 15.0;}
							if(stage1 == 24){ 
								total[18]++;		
								xp = xp + 200;
								pN = 18;
								pCode = "Chansey";
								PCode = total[18];
								power = 17.0;}
							if(stage1 == 25){ 
								total[19]++;
								xp = xp + 200;
								pN = 19;
								pCode = "Snorlax";
								PCode = total[19];
								power = 18.0;}
							if(stage1 == 26){ 
								total[20]++;	
								xp = xp + 200;
								pN = 20;
								pCode = "Lapras";
								PCode = total[20];
								power = 19.0;}
							if(stage1 == 27){ 
								total[21]++;	
								xp = xp + 200;
								pN = 21;
								pCode = "Blastoise";
								PCode = total[21];
								power = 20.0;}
							if(stage1 == 28){ 
								total[22]++;
								xp = xp + 200;
								pN = 22;
								pCode = "Charizard";
								PCode = total[22];
								power = 20.0;}
							if(stage1 == 29){ 
								total[23]++;		
								xp = xp + 200;
								pN = 23;
								pCode = "Venusaur";
								PCode = total[23];
								power = 20.0;}
							if(stage1 == 30){ 
								total[24]++;	
								xp = xp + 200;
								pN = 24;
								pCode = "Alakazam";
								PCode = total[24];
								power = 20.0;}
							if(stage1 == 31){ 
								total[25]++;	
								xp = xp + 200;
								pN = 25;
								pCode = "Tyranitar";
								PCode = total[25];
								power = 20.0;}
							if(stage1 == 32){ 
								total[26]++;		
								xp = xp + 500;
								pN = 26;
								pCode = "Mewtwo";
								PCode = total[26];
								power = 35.0;}
							if(stage1 == 33){ 
								total[27]++;		
								xp = xp + 500;
								pN = 27;
								pCode = "Moltres";
								PCode = total[27];
								power = 35.0;}
							if(stage1 == 34){ 
								total[28]++;	
								xp = xp + 500;
								pN = 28;
								pCode = "Zapdos";
								PCode = total[28];
								power = 35.0;}
							if(stage1 == 35){ 
								total[29]++;	
								xp = xp + 500;
								pN = 29;
								pCode = "Articuno";
								PCode = total[29];
								power = 35.0;}
							//final pokeCode 
							pokeCode = pCode+PCode;

							//stats of the pokemon caught
							ivA = (int)(Math.random()*16+0); //attack iv
							ivD = (int)(Math.random()*16+0); //defense iv
							ivS = (int)(Math.random()*16+0); //stamina iv
							double attp = 0.0; //attack
							double spATTp = 0.0; //special attack 
							double defp = 0.0; //defense
							double spDEFp = 0.0; //special defense
							double hpp = 0.0; //hp
							double spp = 0.0; //stamina
							//retrieving pokemon stats from the array
							for(int c = 0; c < stats[0].length; c++){
								if(pN == c){
									hpp = (double)stats[2][c];
									attp = (double)stats[3][c];
									defp = (double)stats[4][c];
									spATTp = (double)stats[5][c];
									spDEFp = (double)stats[6][c];
									spp = (double)stats[7][c];}}
							double hGAp = 0.0;
							double lGAp = 0.0;
							if(attp > spATTp){
								hGAp = attp;
								lGAp = spATTp;}
							else{
								hGAp = spATTp;
								lGAp = attp;}	
							double hGDp = 0.0;
							double lGDp = 0.0;
							if(defp > spDEFp){
								hGDp = defp;
								lGDp = spDEFp;}
							else{
								hGDp = spDEFp;
								lGDp = defp;}		
							double scalATTp = Math.round(2.0*((7.0/8.0)*hGAp)+((1.0/8.0)*lGAp));
							double spMDp = 1.0+((spp-75.0)/500.0);
							baseATTp = ((Math.round(scalATTp*spMDp*(13.0/15.0)))+(ivA))*0.79030001; //attack
							double scalDEFp = Math.round(2.0*((7.0/8.0)*hGDp)+((1.0/8.0)*lGDp));
							baseDEFp = ((Math.round(scalDEFp*spMDp*(13.0/15.0)))+(ivD))*0.79030001; //defense
							stap = ((hpp*2.0*(15.0/15.0))+(ivS))*0.79030001; //stamina
							cpp = (baseATTp*(Math.sqrt(baseDEFp))*(Math.sqrt(stap))*((0.79030001*0.79030001)/10)); //cp
							double damagep = Math.floor((.5*power)*(baseATTp/baseDEFp)*STAB*EFF)+1; //damage
						
							System.out.println("PokeCode: "+pokeCode);
							//rounds all the 4 main stats of the pokemon to one decimal point
							baseATTp = Math.round(baseATTp*10.0)/10.0;
							baseDEFp = Math.round(baseDEFp*10.0)/10.0;
							stap = Math.round(cpp*10.0)/10.0;
							damagep = Math.round(damagep*10.0)/10.0;
							cpp = Math.round(cpp*10.0)/10.0;
							
							//inputs all information in the arrays
							PokeC[pokeCaught] = pokeCode;
							pokeC[0][pokeCaught] = stap;
							pokeC[1][pokeCaught] = damagep;
							pokeC[2][pokeCaught] = cpp;
							//adds one to the count 
							pokeCaught++;
				
							map[dy][dx] = 0; //makes the pokemon disappear
						}
						else if(chance < rate+chB){ //pokemon has not been caught
							System.out.println(poke+" has broke open, try again");
						}
						else if(chance < 101){ //pokemon has run away
							System.out.println(poke+" has ran away");
							phase = 1;
							map[dy][dx] = 0; //makes the pokemon disappear
						}	
					}
				}
			}
			//levels up the user based off the xp of the user 
			LlUp = levelUp(xp);
			level = LlUp;
			//if user levels up the user will recieve items
			if(level % 1 == 0 && level != 0 && level != OL){
				pB = pB + 10;
				gB = gB + 5;
				rZ = rZ + 10;
				gZ = gZ + 5;
				System.out.println("Good job, you have leveled up");
				System.out.println("You have recieved some items");
			}
			}
		}
	
	/*Returns integer value to create the map
	 * pre: one integer value
	 * post: return one integer parameter
	 */
	public static int map(int Mp){
		int m = 0; //stores the number that will be placed in the map
		//m will be empty space
		if(Mp < 25){ 
			m = 0;}
		//m will be a trainer/battling
		else if(Mp < 32){
			m = 1;}
		//m will be items
		else if(Mp < 47){ 
			m = 2;}
		else if(Mp < 52){
			m = 3;}
		else if(Mp < 62){ 
			m = 4;}
		else if(Mp < 65){ 
			m = 5;}
		// m will be pokemons
		else if(Mp < 80){ 
			int p = (int)(Math.random()*8-0);
			if(p == 0){
				m = 6;}
			if(p == 1){
				m = 7;}
			if(p == 2){
				m = 8;}
			if(p == 3){
				m = 9;}
			if(p == 4){
				m = 10;}
			if(p == 5){
				m = 11;}
			if(p == 6){
				m = 12;}
		}
		else if(Mp < 90){ 
			int p = (int)(Math.random()*7-0);
			if(p == 0){
				m = 13;}
			if(p == 1){
				m = 14;}
			if(p == 2){
				m = 15;}
			if(p == 3){
				m = 16;}
			if(p == 4){
				m = 17;}
			if(p == 5){
				m = 18;}
		}
		else if(Mp < 96){
			int p = (int)(Math.random()*6-0);
			if(p == 0){
				m = 19;}
			if(p == 1){
				m = 20;}
			if(p == 2){
				m = 21;}
			if(p == 3){
				m = 22;}
			if(p == 4){
				m = 23;}
		}
		else if(Mp < 99){ 
			int p = (int)(Math.random()*9-0);
			if(p == 0){
				m = 24;}
			if(p == 1){
				m = 25;}
			if(p == 2){
				m = 26;}
			if(p == 3){
				m = 27;}
			if(p == 4){
				m = 28;}
			if(p == 5){
				m = 29;}
			if(p == 6){
				m = 30;}
			if(p == 7){
				m = 31;}
		}
		else if(Mp == 100){ 
			int p = (int)(Math.random()*5-0);
			if(p == 0){
				m = 32;}
			if(p == 1){
				m = 33;}
			if(p == 2){
				m = 34;}
			if(p == 3){
				m = 35;}
		}
		return m;	
	}
	/*Prints and displays the intro of the game
	 * 
	 */
	public static void intro(){
		System.out.println();
		System.out.println("--------------------------------");
		System.out.println("       Welcome to Pokemon       ");
		System.out.println("--------------------------------");
		System.out.println();
		System.out.println("----------- Controls -----------");
		System.out.println("     Normal Gameplay      ");
		System.out.println("a --> left");
		System.out.println("d --> right");
		System.out.println("w --> up");
		System.out.println("s --> down");
		System.out.println("search --> search for pokemon you caught");
		System.out.println("b --> backpack displays");
		System.out.println("u --> user information displays");
		System.out.println("p --> pokedex displays");
		System.out.println();
		System.out.println("     Catching Pokemons    ");
		System.out.println("p --> pokeball thrown");
		System.out.println("g --> greatball thrown");
		System.out.println("r --> razzberry used");
		System.out.println("z --> goldenberry used");
		System.out.println("n --> run away");
		System.out.println();
		System.out.println("         Battling         ");
		System.out.println("a --> quick move");
		System.out.println("s --> charge move");
		System.out.println("r --> run away");
		System.out.println("IMPORTANT:");
		System.out.println("As the game goes each time you come across '|' that indicates that you can enter any of the keys in normal gameplay");
		System.out.println("Input one direction at a time at '|' ");
		System.out.println("--------------------------------");
		System.out.println();
	}
	/*Searches and displays the cp of the pokemon
	 * pre: one string parameter, one string array parameter, one double 2d array parameter
	 */
	public static void search(String Code, String [] PokeC, double[][] pokeC){
		int s1 = -1; //stores the number to find the stats
			 double cp = 0; //stores the cp value 
				for(int i = 0; i < PokeC.length; i++){
					if(PokeC[i].equalsIgnoreCase(Code)){
						s1 = i;}
				}
				if(s1 == -1){
					System.out.println("Incorrect search");}
				else{
					cp = pokeC[2][s1];
					System.out.println("CP "+cp);}
	}
	/*displays the backpack of the user
	 * pre: four integer parameter
	 */
	public static void backpack(int pB, int gB, int rZ, int gZ){
		System.out.println("------- BACKPACK -------");
		System.out.println("Pokeballs: " +pB);
		System.out.println("Greatballs: " +gB);
		System.out.println("Razzberries: " +rZ);
		System.out.println("Goldenberries: " +gZ);
		System.out.println("------------------------");
	}
	/*displays the users information
	 * pre: one string parameter, three integer parameter
	 */
	public static void user(String name, int level, int xp, int pokeTotal){
		System.out.println("------- "+name+" -------");
		System.out.println("Level: " +level);
		System.out.println("XP: " +xp);
		System.out.println("Total Pokemon Caught: " +pokeTotal);
		System.out.println("------------------------");
		System.out.println();
	}
	/*displays the location of the user
	 * pre: two integer parameter
	 */
	public static void location(int dx, int dy){
		System.out.println("---- Location ----");
		//map[row][column]
		//map[dy][dx]
		System.out.println("x: "+dx);
		System.out.println("y: "+dy);
		System.out.println("------------------");
	}
	/* checks which way in the x direction user will move
	 * pre: one string parameter
	 * post: return one boolean value
	 */
	public static boolean moveX(String direction){
		boolean x = false; //if there is a x direction
		if(direction.equals("a")){
			x = true;}
		if(direction.equals("d")){
			x = true;}
		return x;
	}
	/* checks which way in the y direction user will move
	 * pre: one string parameter
	 * post: return one boolean value
	 */
	public static boolean moveY(String direction){
		boolean y = false; //if there is a y direction
		if(direction.equals("w")){
			y = true;}
		if(direction.equals("s")){
			y = true;}
		return y;
	}
	//prints part one of the battle instructions
	public static void battleP1(){
		System.out.println("---------------- BATTLE ----------------");
		System.out.println("You will battle against a trainer who will have three pokemons");
		System.out.println("The three pokemons you will battle against are:");
		System.out.println();
	}
	//prints part two of the battle instructions
		public static void battleP2(){
			System.out.println();
			System.out.println("INSTRUCTIONS: ");
			System.out.println("1) You will choose three pokeomons by typing in the pokeCode");
			System.out.println("2) The battle will be a one on one");
			System.out.println("3) If the input for moves is incorrect there is no second chance to input moves");
			System.out.println("4) The first trainer to defeat all three of their opponents pokemons wins");
			System.out.println();
			System.out.println("CONTROLS: ");
			System.out.println("a --> quick move");
			System.out.println("s --> charge move");
			System.out.println("r --> run away");
			System.out.println("** IMPORTANT **");
			System.out.println("Enter r in the first input to run away");
			System.out.println("                  or                  ");
			System.out.println("Enter r in second and third input to run");
			System.out.println("You will not be able to run away if you enter r only in the third input");
			System.out.println("----------------------------------------");
		}
	/* checks if the user has enough xp to level up
	 * pre: two integer parameter
	 * post: one integer value 
	 */
	public static int levelUp(int xp){
		double chkL; //stores the square root of the xp
		int Ll = 0; //stores the level value
		chkL = Math.sqrt(xp);
		if(chkL > 5){
			Ll = 1;}
		if(chkL > 10){
			Ll = 2;}
		if(chkL > 17){
			Ll = 3;}
		if(chkL > 22){
			Ll = 4;}
		if(chkL > 35){
			Ll = 5;}
		if(chkL > 40){
			Ll = 6;}
		if(chkL > 45){
			Ll = 7;}
		if(chkL > 50){
			Ll = 8;}
		if(chkL > 65){
			Ll = 9;}
		if(chkL > 75){
			Ll = 10;}
		if(chkL > 85){
			Ll = 11;}
		if(chkL > 100){
			Ll = 12;}
		if(chkL > 115){
			Ll = 13;}
		if(chkL > 130){
			Ll = 14;}
		if(chkL > 145){
			Ll = 15;}
		if(chkL > 160){
			Ll = 16;}
		if(chkL > 180){
			Ll = 17;}
		if(chkL > 200){
			Ll = 18;}
		if(chkL > 250){
			Ll = 19;}
		if(chkL > 300){
			Ll = 20;}
		if(chkL > 350){
			Ll = 21;}
		if(chkL > 400){
			Ll = 22;}
		if(chkL > 450){
			Ll = 23;}
		if(chkL > 600){
			Ll = 24;}
		if(chkL > 800){
			Ll = 25;}
		if(chkL > 1000){
			Ll = 26;}
		if(chkL > 1250){
			Ll = 27;}
		if(chkL > 1500){
			Ll = 28;}
		if(chkL > 1750){
			Ll = 29;}
		if(chkL > 2000){
			Ll = 30;}
		return Ll;
	}
}



