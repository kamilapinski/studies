//Kamil Łapiński, lsystem - zadanie zaliczeniowe
#include <stdlib.h>
#include <stdio.h>
#include <limits.h>

#define N 100 //dlugosc najdluzszego wiersza

//wczytuje liczbę za pomocą getchar()
void readInt(int *n){
	int c;
	(*n) = 0;
	c = getchar();
	while(c != '\n') {
		(*n) = ((*n) + (int)(c - '0')) * 10;
		c = getchar();
	}
	(*n) /= 10;
}

//wczytuje tablicę znaków
void readTab(char Tab[]){
	int c;
	int i = 0;
	c = getchar();
	while(c != '\n') {
		Tab[i++] = (char)c;
		c = getchar();
	}
	Tab[i] = '\n'; //potrzebny znak, żeby nie wyjść poza tablicę
}

//przygotowuje tablicę, ustawia pierwszą wartość na '\n', aby gdy nie będzie interpretacji dla danego symbolu, program miał własność stopu
void prepareTab(char Tab[][N + 1]){
	for (int c = 0; c < CHAR_MAX; c++){
		Tab[c][0] = Tab[c][1] = '\n'; //gdy dany symbol ma na pierwszym miejscu '\n', to znaczy że nie ma dla niego reguły zastępowania/interpretacji
	}
}

//wypisuje tablicę
void printTab(char Tab[]){
	int i = 0;
	while (Tab[i] != '\n'){
		printf("%c", Tab[i++]);
	}
	printf("\n");
}

void wyprowadzenie(int c, char R[][N + 1], char I[][N + 1], int n){
	if (n == 0){
		int i = 1; //od 1 bo na 0 jest c
		while (I[c][i] != '\n')
			printf("%c", I[c][i++]); //wypisujemy interpretację danego symbolu
		if (I[c][0] != '\n') printf("\n"); //jeżeli dany symbol nie ma reguły interpretacji, to nie wypisujemy nawet '\n'
	}
	else{
		int i = 1; //od 1 bo na 0 jest c
		while (R[c][i] != '\n')
			wyprowadzenie(R[c][i++], R, I, n - 1); //robimy wyprowadzenie każdego symbolu rekurencyjnie
		if (R[c][0] == '\n')
			wyprowadzenie(c, R, I, 0); //tutaj możemy od razu wywołać wyprowadzenie z n = 0, bo i tak symbol bez reguły zastępowania zostanie już tylko zinterpretowany i wypisany
	}
}

int main(void){
	char R[CHAR_MAX][N + 1]; //tablica przechowujaca reguly dla kazdego symbolu
	char I[CHAR_MAX][N + 1]; //tablica przechowujaca interpretacje
	char Pr[N + 1], Ep[N + 1]; //tablice prologu i epilogu
	int n; //dlugosc wyprowadzenia
	int c;
	char A[N + 1]; //aksjomat

	//przygotowanie odpowiednie tablic R i I, aby dalsze operacje miały własność stopu
	prepareTab(R);
	prepareTab(I);

	//wczytanie dlugosci wyprowadzenia
	readInt(&n);

	//wczytanie aksjomatu
	readTab(A);

	//wczytanie reguł
	c = getchar();
	while (c != '\n'){
		ungetc(c, stdin);
		readTab(R[c]);
		c = getchar();
	}

	//wczytanie prologu
	c = getchar();
	while (c != '\n'){
		ungetc(c, stdin);
		readTab(Pr);
		printTab(Pr);
		c = getchar();
	}

	//wczytanie interpretacji
	c = getchar();
	while (c != '\n'){
		ungetc(c, stdin);
		readTab(I[c]);
		c = getchar();
	}

	//wyprowadzenie
	int i = 0;
	while (A[i] != '\n')
		wyprowadzenie(A[i++], R, I, n);

	//wczytanie epilogu
	c = getchar();
	while (c != EOF){
		ungetc(c, stdin);
		readTab(Ep);
		printTab(Ep);
		c = getchar();
	}

	return 0;
}
