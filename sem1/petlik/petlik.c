//Kamil Łapiński
//program będacy realizacją języka Pętlik
#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>
#include <assert.h>
#include <limits.h>

#define N 26 //liczba zmiennych
#define MAX_C 9 //największa cyfra
#define CYFR 1 //początkowa liczba cyfr każdej zmiennej
#define NTR -1 //wartość neutralna w tablicy L, która powinna być z poza przedziału [0, DŁUGOŚĆ_PROGRAMU - 1]; jest konieczna do poprawnego sprawdzania, czy można zastosować kod zoptymalizowany
#define DzP 2 //dzielnik powiększenia tablicy
#define MnP 3 //licznik powiększenia tablicy
#define wP 2 //składnik powiększenia tablicy
#define I INT_MAX - 1 //max(maksymalna liczba cyfr wartości zmiennych; maksymalna długość programu)

struct lista{
	struct lista* nast;
	int w;
};

typedef struct lista TStack;

//zwraca rozmiar, na jaki należy powiększyć tablicę
int wiecej(int n){
	if (n <= (I - wP) / MnP * DzP) return (n / DzP * MnP + wP);
	else return I; //jeżeli już nie da się powiększyć mnożąc rozmiar przez 3/2 i dodając 2, to należy powiększyć na rozmiar maksymalny
}

//wczytuje tablicę char'ów, na którą wskazuje S, n wskazuje na wartość rozmiaru tej tablicy
void czytaj(char** S, int* n){
	char* wynik = NULL;
	int rozmiar = 0;
	int i, c;
	for (i = 0; ((c = getchar()) != '\n') && (c != EOF); ++i){
		if (i == rozmiar){ //należy powiększyć rozmiar
			rozmiar = wiecej(rozmiar);
			wynik = (char*)realloc(wynik, (size_t) rozmiar * sizeof *wynik);
			assert(wynik != NULL);
		}
		wynik[i] = (char)c;
	}
	*S = wynik;
	*n = i;
}

//Powiększa rozmiar tablicy, na którą wskazuje a
void zmien_rozmiar(int** a, int* roz){
	int sroz = *roz; //stary rozmiar
	int* x = *a;

	(*roz) = wiecej(*roz); //nowy rozmiar
	x = (int*)realloc(x, (unsigned long int)(*roz) * sizeof(int));
	assert(x != NULL);

	for(int i = sroz; i < *roz; i++) x[i] = 0; //ustawianie nowego obszaru na 0

	*a = x;

}

//wypisuje wartość zmiennej znajdującej się w tablicy, na którą wskazuje x
void wypisz(int* x, int wiod){
	for (int i = wiod; i >= 0; i--){
		printf("%d", x[i]);
	}
	printf("\n");
}

//true - gdy zmienna jest równa 0; false w p.p.
bool czy_zero(int *x, int wiod){
	if (wiod <= CYFR && x[wiod] == 0) return true;
	//printf("%d %d\n", wiod, x[wiod]);
	return false;
}

//zwraca indeks zmiennej
int ind(char x){
	return (int)(x - 'a');
}

//zwraca wartość góry stosu
int top(TStack* S){
	return (S->w);
}

//dodaje wartość x do stosu
void push(TStack** S, int x){
	TStack* pom = (TStack*)malloc(sizeof(TStack));
	pom->nast = *S;
	pom->w = x;
	*S = pom;
}

//usuwa górną wartość ze stosu
void pop(TStack** S){
	TStack* pom = *S;
	*S = (*S)->nast;
	free(pom);
}

//true - stos pusty, false - stos niepusty
bool empty(TStack* S){
	return (S == NULL);
}

//czyści stos
void clear(TStack** S){
	while (!empty(*S)){
		pop(S);
	}
}

//INC Zmienna; Zmienna = *a
void inc(int** a, int* roz, int* wiod){
	int i = 0; //pozycja cyfry
	int j = *roz; //rozmiar
	int* x = *a;

	//jeżeli nie da się dodać na danej pozycji to ustawiamy cyfrę na 0 i szukamy dalej takiej, do której będzie się dało dodać
	while(i < j && x[i] >= MAX_C){
		x[i] = 0;
		i++;
	}

	if (i >= j){ //należy zwiększyć obszar
		zmien_rozmiar(&x, &j);
	}

	if (i > *wiod) (*wiod)++; //zwiększamy pozycję cyfry wiodącej
	x[i]++;

	*roz = j;
	*a = x;
}

//ADD Zmienna0 Zmienna1; Zmienna0 - *aa; Zmienna1 - b
void add(int** aa, int* roza, int* wioda, int* b, int rozb){
	int* a = *aa;

	int rozi = (*roza); //nowy rozmiar zmiennej, na którą wskazuje a

	int p = 0; //przeniesienie
	int k = 0;

	//dodawanie pisemne
	while(k < (*roza) || k < rozb || p > 0){
		int x = 0;

		if (k >= rozi){ //należy powiększyć obszar
			zmien_rozmiar(&a, &rozi);
		}

		//w poniższych warunkach porównujemy k z (*roza), czyli z nieuaktualnionym rozmiarem, ponieważ niepotrzebny nam nowy obszar do obliczania x
		//obliczenie wartości do dodania
		if (k < (*roza) && k < rozb){
			x = a[k] + b[k] + p;
		}
		else if (k < (*roza)){
			x = a[k] + p;
		}
		else if (k < rozb){
			x = b[k] + p;
		}
		else{
			x = p;
		}

		p = x / (MAX_C + 1); //obliczenie przeniesienia
		x = x % (MAX_C + 1); //zmiana x na wartość do dodania na k-tej pozycji

		a[k] = x;
		if (a[k] > 0 && *wioda < k) (*wioda) = k;
		k++;
	}

	(*roza) = rozi;
	*aa = a;
}

//CLR Zmienna
void clr(int** aa, int* roz, int *wiod){
	int* a = *aa;

	*roz = CYFR; //nowy rozmiar równy początkowej liczbe cyfr zmiennych
	*wiod = CYFR - 1; //nowa pozycja cyfry wiodącej

	a = (int*)realloc(a, (unsigned long int)(*roz) * sizeof(int)); //zmniejszenie obszaru

	for (int i = 0; i < *roz; i++) a[i] = 0; //ustawienie wszystkich cyfr na 0

	*aa = a;
}

//DECR Zmienna; potrzebne przy DJZ
//używać tylko, gdy zmienna jest większa od 0
//zastanów się czy nie chcesz zmniejszać obszaru
void decr(int** aa, int* roz, int* wiod){
	int* x = *aa;
	int i = 0; //pozycja aktualnej cyfry
	int j = *roz; //rozmiar

	while(i < j && x[i] == 0){
		x[i] = MAX_C; //szukamy miejsca na którym możemy odjąć, ustawiając 0 na największą możliwą cyfrę
		i++;
	}

	if (i < j){
		x[i]--; //odejmujemy na pozycji, na której możemy odjąć
		if ((*wiod) != 0 && i >= (*wiod) && x[i] <= 0) (*wiod)--; //jeżeli wyzerowaliśmy cyfrę wiodącą, to pozycja cyfry wiodącej zmniejsza się o 1
	}
	//else nie rozpatrujemy, bo gdyby i >= j, to znaczy, że zmienna jest równa 0, więc nie odejmujemy

	*roz = j;
	*aa = x;
}

//JMP Początek; początek = ind
void jmp(int* i, int ind){
	(*i) = ind;
}

//DJZ Zmienna Adres; Adres = koniec
void djz(int** aa, int* roz, int* wiod, int* i, int koniec){
	int* a = *aa;

	if (czy_zero(a, *wiod)) jmp(i, koniec); //jeżeli zmienna jest równa 0, to skok na koniec
	else decr(&a, roz, wiod); //w przeciwnym wypadku odejmujemy 1

	*aa = a;
}

void wykonuj(int** A, int* roz, int* wiod, char* S, int n, int* L, int* P){
	int i = 0;
	bool g = true; //false - kod zoptymalizowany, true - kod zwykły

	while (i < n){
		if (S[i] == '('){
			//rozpoznawanie który kod zastosować
			if (L[i] == NTR) g = false;
			else g = true;
		}
		else if (S[i] == ')'){
			if (!g){
				int l = ind(S[L[i] + 1]); //indeks początku powtarzaj
				clr(&A[l], &roz[l], &wiod[l]); //CLR S[L[i] + 1]
			}
			else jmp(&i, L[i]); //JMP L[i]

			g = true; //teraz kod zoptymalizowany się kończy
		}
		else{
			int k = ind(S[i]); //indeks zmiennej w tablicy A

			if (i > 0 && S[i - 1] == '('){
				if (g){
					djz(&A[k], &roz[k], &wiod[k], &i, P[i]); //DJZ S[i] P[i]; stosujemy djz, bo jest kod zwykły, a poprzedni znak to (
				}
			}
			else{
				if (g){
					inc(&A[k], &roz[k], &wiod[k]); //INC S[i]
				}
				else{
					int a = ind(S[i]);
					int b = ind(S[L[i] + 1]);
					add(&A[a], &roz[a], &wiod[a], A[b], roz[b]); //ADD S[i] S[L[i] + 1]; S[L[i] + 1] - zmienna bezpośrednio po (
				}
			}
		}

		i++;
	}
}

int main(void){
	int c;
	int* A[N];
	int roz[N], wiod[N]; //roz - tablica rozmiarów zmiennych; wiod - tablica przechowująca pozycję cyfry wiodącej każdej zmiennej

	//ustawianie początkowych wartości zmiennych
	for (int i = 0; i < N; i++){
		A[i] = (int*)calloc(CYFR, sizeof(int));
		roz[i] = CYFR; //początkowy rozmiar zmiennych
		wiod[i] = CYFR - 1;
	}

	c = getchar();
	while(c != EOF){
		if (c == '='){
			c = getchar();
			int i = ind((char)c);
			wypisz(A[i], wiod[i]);
		}
		else{
			ungetc(c, stdin);
			int j;
			char* S = NULL;
			czytaj(&S, &j);

			//L - wskaźnik na tablicę, która dla każdej pozycji przechowuje indeks pierwszego lewego nawiasu wewnątrz, którego znajduje się pozycja i; 0, gdy nie ma takiego nawiasu
			//w przypadku, gdy pod pozycją i znajduje się lewy nawias i wewnątrz tego nawiasu można zastosować kod zoptymalizowany wartość L[i] = NTR
			int* L = (int*)calloc((size_t)j, sizeof(int));

			//P - wskaźnik na tablicę, która dla każdej pozycji przechowuje indeks pierwszego prawego nawiasu wewnątrz, którego znajduje się pozycja i; 0, gdy nie ma takiego nawiasu
			int* P = (int*)calloc((size_t)j, sizeof(int));

			TStack* St = NULL; //wskaźnik na stos przechowujący pozycje nawiasów
			int g = NTR; //pozycja ostatniego lewego nawiasu; na początku NTR, żeby nie kolidowało z realnymi pozycjami nawiasów

			//przygotowanie tablicy L i P

			for (int i = 0; i < j; i++){
				if (S[i] == '('){
					push(&St, i);
					g = i;
				}
				else if (S[i] == ')'){
					if (!empty(St))
						L[i] = top(St);
					pop(&St);
				}
				else if (!empty(St)){
					L[i] = top(St);
				}
			}
			clear(&St);

			g = NTR; //pozycja ostatniego prawego nawiasu

			//instrukcja Powtarzaj jest postaci (Z0, I1, I2, ...)
			//h potrzebne, aby zdecydować, czy możemy zastosować kod zoptymalizowany
			bool h = true; //false, gdy pozycja i to któraś z instrukcji I (czyli wewnątrz Powtarzaj) i jest to instrukcja Zwiększ na zmiennej, która jest zmienną Z0; true w p.p.

			for (int i = j - 1; i >= 0; i--){
				if (S[i] == ')'){
					push(&St, i);
					g = i;
					h = true;
				}
				else if (S[i] == '('){
					if (!empty(St)) //warunek "na wszelki wypadek", ale jeśli dane są poprawne to Stos tutaj nie będzie pusty
						P[i] = top(St);

					if (P[i] == g && h) L[i] = NTR; //jeżeli ostatni prawy nawias łączy się z aktualnym lewym nawiasem i jeżeli h, to możemy zastosować kod zoptymalizowany na i-tej pozycji
					pop(&St);
				}
				else{
					//jeżeli aktualny znak, to instrukcja Zwiększ na zmiennej, która jest zmienną Z0, to nie możemy zastosować kodu zoptymalizowanego
					if (S[L[i]] == '(' && S[L[i] + 1] == S[i] && L[i] + 1 != i) h = false;

					if (!empty(St))
						P[i] = top(St);
				}
			}
			clear(&St);

			wykonuj(A, roz, wiod, S, j, L, P);

			//zwolnienie pamięci St, S, L, P
			free(St);
			free(S);
			free(L);
			free(P);
		}
		c = getchar();
	}

	//zwolnienie pamięci po wszystkich zmiennych
	for (int i = 0; i < N; i++){
		free(A[i]);
	}
}
