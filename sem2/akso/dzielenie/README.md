<div class="no-overflow"><h2>Dzielenie</h2>

<p>Zaimplementuj w asemblerze wołaną z języka C funkcję o następującej deklaracji:</p>

<pre><code>int64_t mdiv(int64_t *x, size_t n, int64_t y);
</code></pre>

<p>Funkcja wykonuje dzielenie całkowitoliczbowe z resztą. Funkcja traktuje dzielną,
dzielnik, iloraz i resztę jako liczby zapisane w kodowaniu uzupełnieniowym do
dwójki. Pierwszy i drugi parametr funkcji określają dzielną: <code>x</code> jest
wskaźnikiem na niepustą tablicę <code>n</code> liczb 64-bitowych. Dzielna ma <code>64 * n</code> bitów
i jest zapisana w pamięci w porządku cienkokońcówkowym (ang. <em>little-endian</em>).
Trzeci parametr <code>y</code> jest dzielnikiem. Wynikiem funkcji jest reszta z dzielenia
<code>x</code> przez <code>y</code>. Funkcja umieszcza iloraz w tablicy <code>x</code>.</p>

<p>Jeśli iloraz nie daje się zapisać w tablicy <code>x</code>, to oznacza wystąpienie nadmiaru
(ang. <em>overflow</em>). Szczególnym przypadkiem nadmiaru jest dzielenie przez zero.
Funkcja powinna reagować na nadmiar tak jak rozkazy <code>div</code> i <code>idiv</code>, czyli
zgłaszać przerwanie numer 0. Obsługa tego przerwania w Linuksie polega na
wysłaniu do procesu sygnału <code>SIGFPE</code>. Opis tego sygnału „błąd w obliczeniach
zmiennoprzecinkowych” jest nieco mylący.</p>

<p>Wolno założyć, że wskaźnik <code>x</code> jest poprawny oraz że <code>n</code> ma wartość dodatnią.</p>

<h2>Przykład użycia</h2>

<p>Przykład użycia jest częścią treści zadania. W szczególności z przykładu użycia
należy wywnioskować, jakie są zależności między znakami dzielnej, dzielnika,
ilorazu i reszty.
Przykład użycia znajduje się w niżej załączonym pliku <code>mdiv_example.c</code>.
Można go skompilować i skonsolidować z rozwiązaniem poleceniami:</p>

<pre><code>gcc -c -Wall -Wextra -std=c17 -O2 -o mdiv_example.o mdiv_example.c
gcc -z noexecstack -o mdiv_example mdiv_example.o mdiv.o
</code></pre>

<h2>Oddawanie rozwiązania</h2>

<p>Jako rozwiązanie należy wstawić w Moodle plik o nazwie <code>mdiv.asm</code>.</p>

<h2>Kompilowanie</h2>

<p>Rozwiązanie będzie kompilowane poleceniem:</p>

<pre><code>nasm -f elf64 -w+all -w+error -o mdiv.o mdiv.asm
</code></pre>

<p>Rozwiązanie musi się kompilować w laboratorium komputerowym.</p>

<h2>Ocenianie</h2>

<p>Ocena będzie się składała z dwóch części.</p>

<ol>
<li><p>Zgodność rozwiązania ze specyfikacją będzie oceniania za pomocą testów
automatycznych, za które można dostać maksymalnie 7 punktów. Sprawdzane będą
też przestrzeganie reguł ABI, poprawność odwołań do pamięci i zajętość pamięci.
Od wyniku testów automatycznych zostanie odjęta wartość proporcjonalna do
rozmiaru dodatkowej pamięci wykorzystywanej przez rozwiązanie (sekcje <code>.bss</code>,
<code>.data</code>, <code>.rodata</code>, stos, sterta). Ponadto zostanie ustalony próg rozmiaru
sekcji <code>.text</code>. Przekroczenie tego progu będzie skutkowało odjęciem od oceny
wartości proporcjonalnej do wartości tego przekroczenia. Dodatkowym kryterium
automatycznej oceny rozwiązania będzie szybkość jego działania. Rozwiązanie
zbyt wolne nie uzyska maksymalnej oceny. Za błędną nazwę pliku odejmiemy jeden
punkt.</p></li>
<li><p>Za formatowanie i jakość kodu można dostać maksymalnie 3 punkty. Tradycyjne
formatowanie programów w asemblerze polega na rozpoczynaniu etykiet od pierwszej
kolumny, a mnemoników rozkazów od wybranej ustalonej kolumny. Nie stosuje się
innych wcięć. Taki format mają przykłady pokazywane na zajęciach. Kod powinien
być dobrze skomentowany, co oznacza między innymi, że każdy blok kodu powinien
być opatrzony informacją, co robi. Należy opisać przeznaczenie rejestrów.
Komentarza wymagają wszystkie kluczowe lub nietrywialne linie kodu. W przypadku
asemblera nie jest przesadą komentowanie prawie każdej linii kodu, ale należy
unikać komentarzy opisujących to, co widać.</p></li>
</ol>

<p>Wystawienie oceny może zostać uzależnione od osobistego wyjaśnienia szczegółów
działania programu prowadzącemu zajęcia.</p>

<p><strong>Rozwiązania należy implementować samodzielnie pod rygorem niezaliczenia
przedmiotu. Zarówno korzystanie z cudzego kodu, jak i prywatne lub publiczne
udostępnianie własnego kodu jest zabronione.</strong></p>

<h2>Załącznik</h2>
</div>