#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#define K 8 //liczba kolumn
#define W 8 //liczba wierszy

//i - numer wiersza
//j - numer kolumny

//konwertuje współrzędne ci, cj na liczby i, j oraz ustawia tab[i][j] na c
void set(char tab[][K], char ci, char cj, char c){
	int i = (int)(ci - '0' - 1);
	int j = (int)(cj - 'a');

	if (i >= 0 && i < W && j >= 0 && j < K)
		tab[i][j] = c;
}

//zmiana tury; zmiana znaku z B na C lub z C na B
void nextTurn(char* turn){
	if ((*turn) == 'B') (*turn) = 'C';
	else (*turn) = 'B';
}

//prepareTab przygotowuje plansze do gry
void prepareTab(char tab[][K]){
	for (int i = 0; i < W; i++){
		for (int j = 0; j < K; j++){
			tab[i][j] = '-';
		}
	}

	//ustawienie poczatkowych zetonow B i C na planszy
	tab[W / 2 - 1][K / 2 - 1] = tab[W / 2][K / 2] = 'B';
	tab[W / 2 - 1][K / 2] = tab[W / 2][K / 2 - 1] = 'C';
}

//wypisuje jak wyglada plansza
void printTab(char tab[][K]){
	for (int i = 0; i < W; i++){
		for (int j = 0; j < K; j++){
			printf("%c", tab[i][j]);
		}
		printf("\n");
	}
}

//idzie w konkretnym kierunku i jezeli spotka odpowiedni zeton, to wraca odwracajac zetony ktore spotkal
bool checkAndFlip(char tab[][K], char c, int i, int j, int ti, int tj){
	//jezeli funkcja napotka c, to rozpoczyna zamienianie
	//ti - zmiana w i jaka musi się dokonywać przy każdym nastepnym wywolaniu checkAndFlip
	//tj - zmiana w j jaka musi się dokonywać przy każdym nastepnym wywolaniu checkAndFlip

	i += ti;
	j += tj;

	if (i < 0 || i >= W || j < 0 || j >= K || tab[i][j] == '-')
		return false; //jeżeli wyjdzie poza tablicę lub spotka '-', to w tę stronę nie da się nic obrócić
	else if (tab[i][j] == c)
		return true; //jeżeli spotka c to już cofa się i wie, że może obracać

	if (checkAndFlip(tab, c, i, j, ti, tj)){ //to oznacza, że gdzieś dalej było c i może obracać
		tab[i][j] = c;
		return true;
	}
	return false;
}

//dokonuje odwracania wszystkich zetonow po polozeniu zetonu na pozycji i, j
void flip(char tab[][K], char ci, char cj){
	int i = (int)(ci - '0' - 1);
	int j = (int)(cj - 'a');

	//sprawdza zetony w kierunku "północnym"
	checkAndFlip(tab, tab[i][j], i, j, -1, 0);
	//sprawdza zetony w kierunku "północno-wschodnim"
	checkAndFlip(tab, tab[i][j], i, j, -1, 1);
	//sprawdza zetony w kierunku "wchodnim"
	checkAndFlip(tab, tab[i][j], i, j, 0, 1);
	//sprawdza zetony w kierunku "południowo-wschodnim"
	checkAndFlip(tab, tab[i][j], i, j, 1, 1);
	//sprawdza zetony w kierunku "poludniowym"
	checkAndFlip(tab, tab[i][j], i, j, 1, 0);
	//sprawdza zetony w kierunku "poludniowo-zachodnim"
	checkAndFlip(tab, tab[i][j], i, j, 1, -1);
	//sprawdza zetony w kierunku "zachodnim"
	checkAndFlip(tab, tab[i][j], i, j, 0, -1);
	//sprawdza zetony w kierunku "polnocno-zachodnim"
	checkAndFlip(tab, tab[i][j], i, j, -1, -1);
}

//sprawdza, czy położenie żetonu na pozycji i, j jest legalne (patrząc w jednym z ośmiu kierunków)
bool check(char tab[][K], char c, int i, int j, int ti, int tj){
	//ti - zmiana w i jaka musi się dokonywac przy kazdym nastepnym wywolaniu check
	//tj - zmiana w j jaka musi się dokonywac przy kazdym nastepnym wywolaniu check

	i += ti;
	j += tj;

	bool g = false; //true - jezeli byl przeciwny zeton
	bool h = false; //true - jezeli byl wlasciwy zeton

	while (!h && i >= 0 && i < W && j >= 0 && j < K && tab[i][j] != '-'){
		if (tab[i][j] != c) g = true;
		else h = true;
		i += ti;
		j += tj;
	}

	return (h && g);

}

//wypisuje legalne ruchy
void printMoves(char tab[][K], char turn){

	for (int j = 0; j < K; j++){
		for (int i = 0; i < W; i++){
			bool g = false;

			if (tab[i][j] == '-'){
				//sprawdzenie czy położenie żetonu na i, j jest legalne
				//w kierunku "północnym"
				g |= check(tab, turn, i, j, -1, 0);
				//w kierunku "północno-wschodnim"
				g |= check(tab, turn, i, j, -1, 1);
				//w kierunku "wchodnim"
				g |= check(tab, turn, i, j, 0, 1);
				//w kierunku "południowo-wschodnim"
				g |= check(tab, turn, i, j, 1, 1);
				//w kierunku "poludniowym"
				g |= check(tab, turn, i, j, 1, 0);
				//w kierunku "poludniowo-zachodnim"
				g |= check(tab, turn, i, j, 1, -1);
				//w kierunku "zachodnim"
				g |= check(tab, turn, i, j, 0, -1);
				//w kierunku "polnocno-zachodnim"
				g |= check(tab, turn, i, j, -1, -1);
			}

			if (g) printf(" %c%d", (char)(j + 'a'), (i + 1));
		}
	}
	printf("\n");
}

int main(void){
	char tab[W][K], col, turn;
	int row;

	prepareTab(tab);
	turn = 'C';

	//pierszy prompt
	printf("%c", turn);
	printMoves(tab, turn);

	col = (char)getchar();
	while (col != '='){
		if (col != '-'){ //jeżeli nie ma rezygnacji z ruchu
			row = (int)getchar();

			//wykonanie ruchu
			set(tab, (char)row, col, turn);

			//obrócenie żetonów
			flip(tab, (char)row, col);
		}

		//zmiana gracza
		nextTurn(&turn);

		//wypisanie tekstu zachęty (prompt)
		printf("%c", turn);
		printMoves(tab, turn);

		getchar();
		col = (char)getchar();
	}

	return 0;
}
