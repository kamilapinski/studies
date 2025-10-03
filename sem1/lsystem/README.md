<div class="no-overflow" id="yui_3_18_1_1_1759502512502_395"><hr>

<h2>Wprowadzenie</h2>

<p>System Lindenmayera, w skrócie
<a href="https://en.wikipedia.org/wiki/L-system">
L-system
</a>, to system formalny definiujący języki. Został stworzony w celu
modelowania roślin przez biologa, Aristida Lindenmayera.</p>

<p>Deterministyczny bezkontekstowy L-system, nazywany D0L, jest trójką
<code>(S, A, R)</code>, w której <code>S</code> to alfabet, <code>A</code> jest słowem nad <code>S</code> nazywanym
aksjomatem, a <code>R</code> to zbiór reguł zastępowania, wiążących symbole ze słowami
nad <code>S</code>. Dla każdego elementu <code>S</code>, w <code>R</code> jest co najwyżej jedna reguła.</p>

<p>Słowo <code>w</code> należy do języka definiowanego przez L-system wtedy i tylko
wtedy, gdy istnieje nieujemna liczba całkowita <code>n</code> taka, że <code>w</code> ma
wyprowadzenie długości <code>n</code>. Przyjmujemy, że aksjomat ma wyprowadzenie
długości <code>0</code>. Słowo o wyprowadzeniu długości <code>n + 1</code> powstaje ze słowa
o wyprowadzeniu długości <code>n</code> przez jednoczesne zastąpienie każdego symbolu
słowem określonym przez jego regułę, lub pozostawienie go bez zmian, jeśli
nie ma dla niego reguły.</p>

<p>Rozważamy tekstową interpretację słów języka definiowanego przez L-system.
Interpretacja zaczyna się do ciągu wierszy, nazywanego prologiem, po nim
jest ciąg wierszy odpowiadających poszczególnym symbolom słowa, a na
zakończenie jest ciąg wierszy nazywany epilogiem. Wiersze odpowiadające
symbolom określone są za pomocą reguł interpretacji. Dla każdego symbolu
mamy co najwyżej jedną regułę interpretacji. Symbole słowa, dla których
nie określono interpretacji, są w niej pomijane.</p>

<h2>Polecenie</h2>

<p>Napisz program, który czyta z wejścia długość wyprowadzenia, opis L-systemu
oraz jego interpretacji i pisze na wyjście interpretację wyprowadzonego
słowa należącego do języka definiowanego przez L-system.</p>

<h2>Postać danych</h2>

<p>Na wejściu programu jest wiersz z nieujemną liczbą całkowitą określającą
długość wyprowadzenia, wiersze opisu L-systemu i wiersze opisu
interpretacji słowa.</p>

<p>Opis L-systemu składa się z wiersza zawierającego aksjomat, po którym
następuje ciąg wierszy z regułami zastępowania. Wiersz reguły zastępowania
zaczyna się od zastępowanego symbolu, a po nim jest zastępujące go słowo.
Alfabet L-systemu nie jest jawnie podany. Przyjmujemy, że jest zbiorem
symboli występujących w aksjomacie i regułach zastępowania.</p>

<p>Opis interpretacji słowa zaczyna się od ciągu wierszy prologu, po nim są
wiersze reguł interpretacji, a po nich wiersze epilogu. Każdy z tych trzech
ciągów jest poprzedzony wierszem pustym. Wiersz reguły interpretacji zaczyna
się od interpretowanego symbolu, a reszta wiersza jest tekstem, który dla
tego symbolu należy wypisać na wyjście.</p>

<h2>Postać wyniku</h2>

<p>Wynikiem programu jest ciąg wierszy będący interpretacją słowa.</p>

<h2>Przykłady</h2>

<p>Do treści zadania dołączone są pliki <code>.in</code> z przykładowymi danymi i pliki
<code>.out</code> z wynikami wzorcowymi.</p>

<ul>
<li><p>Dla danych
<a href="https://moodle.mimuw.edu.pl/pluginfile.php/276197/mod_folder/content/0/przyklad1.in?forcedownload=1">
przyklad1.in
</a>
poprawny wynik to
<a href="https://moodle.mimuw.edu.pl/pluginfile.php/276197/mod_folder/content/0/przyklad1.out?forcedownload=1">
przyklad1.out
</a>.</p></li>
<li><p>Dla danych
<a href="https://moodle.mimuw.edu.pl/pluginfile.php/276197/mod_folder/content/0/przyklad2.in?forcedownload=1">
przyklad2.in
</a>
poprawny wynik to
<a href="https://moodle.mimuw.edu.pl/pluginfile.php/276197/mod_folder/content/0/przyklad2.out?forcedownload=1">
przyklad2.out
</a>.</p></li>
<li><p>Dla danych
<a href="https://moodle.mimuw.edu.pl/pluginfile.php/276197/mod_folder/content/0/przyklad3.in?forcedownload=1">
przyklad3.in
</a>
poprawny wynik to
<a href="https://moodle.mimuw.edu.pl/pluginfile.php/276197/mod_folder/content/0/przyklad3.out?forcedownload=1">
przyklad3.out
</a>.</p></li>
</ul>

<h2>Galeria</h2>

<p>Poniższe obrazki powstały na podstawie książki Prusinkiewicza
i Lindenmayera
<a href="https://en.wikipedia.org/wiki/The_Algorithmic_Beauty_of_Plants">
The Algorithmic Beauty of Plants
</a>.</p>

<p>Rozwiązanie zadania, uruchomione na danych z pliku <code>.in</code>, generuje
plik w języku Postscript. Można go przekształcić do formatu <code>.pdf</code>
programem <code>ps2pdf</code>.</p>

<ul>
<li><p>Z pliku
<a href="https://moodle.mimuw.edu.pl/pluginfile.php/292675/mod_folder/content/0/obrazek1.in?forcedownload=1">
obrazek1.in
</a>
powstaje obrazek
<a href="https://moodle.mimuw.edu.pl/pluginfile.php/292675/mod_folder/content/0/obrazek1.pdf?forcedownload=1">
obrazek1.pdf
</a>.</p></li>
<li><p>Z pliku
<a href="https://moodle.mimuw.edu.pl/pluginfile.php/292675/mod_folder/content/0/obrazek2.in?forcedownload=1">
obrazek2.in
</a>
powstaje obrazek
<a href="https://moodle.mimuw.edu.pl/pluginfile.php/292675/mod_folder/content/0/obrazek2.pdf?forcedownload=1">
obrazek2.pdf
</a>.</p></li>
<li><p>Z pliku
<a href="https://moodle.mimuw.edu.pl/pluginfile.php/292675/mod_folder/content/0/obrazek3.in?forcedownload=1">
obrazek3.in
</a>
powstaje obrazek
<a href="https://moodle.mimuw.edu.pl/pluginfile.php/292675/mod_folder/content/0/obrazek3.pdf?forcedownload=1">
obrazek3.pdf
</a>.</p></li>
<li><p>Z pliku
<a href="https://moodle.mimuw.edu.pl/pluginfile.php/292675/mod_folder/content/0/obrazek4.in?forcedownload=1">
obrazek4.in
</a>
powstaje obrazek
<a href="https://moodle.mimuw.edu.pl/pluginfile.php/292675/mod_folder/content/0/obrazek4.pdf?forcedownload=1">
obrazek4.pdf
</a>.</p></li>
<li><p>Z pliku
<a href="https://moodle.mimuw.edu.pl/pluginfile.php/292675/mod_folder/content/0/obrazek5.in?forcedownload=1">
obrazek5.in
</a>
powstaje obrazek
<a href="https://moodle.mimuw.edu.pl/pluginfile.php/292675/mod_folder/content/0/obrazek5.pdf?forcedownload=1">
obrazek5.pdf
</a>.</p></li>
<li><p>Z pliku
<a href="https://moodle.mimuw.edu.pl/pluginfile.php/292675/mod_folder/content/0/obrazek6.in?forcedownload=1">
obrazek6.in
</a>
powstaje obrazek
<a href="https://moodle.mimuw.edu.pl/pluginfile.php/292675/mod_folder/content/0/obrazek6.pdf?forcedownload=1">
obrazek6.pdf
</a>.</p></li>
<li><p>Z pliku
<a href="https://moodle.mimuw.edu.pl/pluginfile.php/292675/mod_folder/content/0/obrazek7.in?forcedownload=1">
obrazek7.in
</a>
powstaje obrazek
<a href="https://moodle.mimuw.edu.pl/pluginfile.php/292675/mod_folder/content/0/obrazek7.pdf?forcedownload=1">
obrazek7.pdf
</a>.</p></li>
<li><p>Z pliku
<a href="https://moodle.mimuw.edu.pl/pluginfile.php/292675/mod_folder/content/0/obrazek8.in?forcedownload=1">
obrazek8.in
</a>
powstaje obrazek
<a href="https://moodle.mimuw.edu.pl/pluginfile.php/292675/mod_folder/content/0/obrazek8.pdf?forcedownload=1">
obrazek8.pdf
</a>.</p></li>
<li><p>Z pliku
<a href="https://moodle.mimuw.edu.pl/pluginfile.php/292675/mod_folder/content/0/obrazek9.in?forcedownload=1">
obrazek9.in
</a>
powstaje obrazek
<a href="https://moodle.mimuw.edu.pl/pluginfile.php/292675/mod_folder/content/0/obrazek9.pdf?forcedownload=1">
obrazek9.pdf
</a>.</p></li>
<li><p>Z pliku
<a href="https://moodle.mimuw.edu.pl/pluginfile.php/292675/mod_folder/content/0/obrazek10.in?forcedownload=1">
obrazek10.in
</a>
powstaje obrazek
<a href="https://moodle.mimuw.edu.pl/pluginfile.php/292675/mod_folder/content/0/obrazek10.pdf?forcedownload=1">
obrazek10.pdf
</a>.</p></li>
</ul>

<h2>Walidacja i testy</h2>

<ul id="yui_3_18_1_1_1759502512502_400">
<li><p>Rozwiązania podlegają walidacji, wstępnie badającej zgodność ze
specyfikacją.</p>

<p>Walidacja sprawdza działanie programu na przykładach dołączonych do
treści zadania.</p>

<p>Pomyślne przejście walidacji jest warunkiem dopuszczenia programu do
testów poprawności. Program, który walidacji nie przejdzie, dostaje
zerową ocenę poprawności.</p></li>
<li><p>Walidacja i testy są prowadzone na komputerze <code>students</code>.</p></li>
<li><p>Programy są kompilowane poleceniem:</p>

<pre><code class="bash">gcc @opcje nazwa.c -o nazwa
</code></pre>

<p>gdzie <code>nazwa.c</code> to nazwa pliku z kodem źródłowym, a plik <code>opcje</code> ma
zawartość:</p>

<pre><code>-std=c17
-pedantic
-Wall
-Wextra
-Wformat-security
-Wduplicated-cond
-Wfloat-equal
-Wshadow
-Wconversion
-Wjump-misses-init
-Wlogical-not-parentheses
-Wnull-dereference
-Wvla
-Werror
-fstack-protector-strong
-fsanitize=undefined
-fno-sanitize-recover
-g
-fno-omit-frame-pointer
-O1
</code></pre>

<p>Opcje <code>-std=c17</code>, <code>-pedantic</code> wskazują, że kompilator ma dbać
o zgodność kodu z aktualnym standardem języka C.</p>

<p>Dzięki opcjom <code>-Wall</code>, <code>-Wextra</code> kompilator zgłosi zauważone usterki.</p>

<p>Opcje <code>-Wformat-security</code>, <code>-Wduplicated-cond</code>, <code>-Wfloat-equal</code>,
<code>-Wshadow</code>, <code>-Wconversion</code>, <code>-Wjump-misses-init</code>,
<code>-Wlogical-not-parentheses</code>, <code>-Wnull-dereference</code> umożliwiają
wykrywanie dodatkowych usterek.</p>

<p>Opcja <code>-Wvla</code> sprawia, że użycie tablic zmiennej długości jest uznawane
za usterkę.</p>

<p>Opcja <code>-Werror</code> wskazuje, że kompilator ma uznać usterki za błędy.</p>

<p>Dzięki opcji <code>-fstack-protector-strong</code>, podczas wykonania programu
zostaną wykryte niektóre błędne odwołania do pamięci na stosie.</p>

<p>Opcje <code>-fsanitize=undefined</code>, <code>-fno-sanitize-recover</code> umożliwiają
wykrywanie operacji, które mają efekt nieokreślony.</p>

<p>Opcje <code>-g</code>, <code>-fno-omit-frame-pointer</code> poprawiają jakość komunikatów
o błędach wykonania.</p>

<p>Opcja <code>-O1</code> włącza optymalizacje, co zwiększa prawdopodobieństwo
ujawnienia się błędów.</p>

<p>Wymagane są wszystkie wymienione opcje kompilatora. Nie będą do nich
dodawane żadne inne.</p>

<p>Zwracamy uwagę, że poszczególne wersje kompilatora <code>gcc</code> mogą się
różnić sposobem obsługi tych samych opcji. Przed wysłaniem rozwiązania
warto więc skompilować je i przetestować na <code>students</code> w sposób
opisany powyżej.</p></li>
<li id="yui_3_18_1_1_1759502512502_399"><p>Podczas walidacji i testów, program <code>nazwa</code> jest uruchamiany pod
kontrolą programu Valgrind poleceniem:</p>

<pre><code class="bash">valgrind --leak-check=full -q --error-exitcode=1 ./nazwa
</code></pre>

<p>Jeśli Valgrind wykryje błąd, to nawet gdyby wynik był prawidłowy,
uznajemy, że program testu nie przeszedł.</p>

<p id="yui_3_18_1_1_1759502512502_398">Opcja <code>-q</code> powoduje, że jedynymi informacjami, wypisywanymi przez
program Valgrind, są komunikaty o błędach.</p>

<p>Opcja <code>--leak-check=full</code> wskazuje Valgrindowi, że powinien, między
innymi, szukać wycieków pamięci.</p>

<p>Opcja <code>--error-exitcode=1</code> określa kod wyjścia programu w przypadku,
gdy Valgrind wykryje błąd.</p></li>
<li><p>Przyjmujemy, że niezerowy wynik funkcji <code>main()</code> informuje o błędzie
wykonania programu.</p></li>
<li><p>Poprawność wyniku sprawdzamy, przekierowując na wejście programu
zawartość pliku <code>.in</code> i porównując rezultat, za pomocą programu <code>diff</code>,
z plikiem <code>.out</code>, np.:</p>

<pre><code class="bash">&lt; przyklad.in ./nazwa | diff - przyklad.out
</code></pre>

<p>Ocena poprawności wyniku jest binarna. Wynik uznajemy za poprawny,
jeżeli program <code>diff</code> nie wskaże żadnej różnicy względem wyniku
wzorcowego.</p></li>
</ul>

<h2>Uwagi i wskazówki</h2>

<ul>
<li><p>Jako rozwiązanie należy wysłać plik tekstowy <code>.c</code> z kodem źródłowym
w języku C.</p></li>
<li><p>Wolno założyć, że dane są poprawne.</p></li>
<li><p>Wolno założyć, że każdy wiersz danych, w tym ostatni, kończy się
reprezentacją końca wiersza <code>'\n'</code>. Należy zadbać, by warunek ten
spełniał także wynik programu.</p></li>
<li><p>Wolno założyć, że znaki na wejściu programu mają kody od zera do
wartości stałej <code>CHAR_MAX</code>, która jest zdefiniowana w pliku nagłówkowym
<code>limits.h</code>.</p></li>
<li><p>Wolno założyć, że najdłuższy wiersz na wejściu programu, bez
reprezentacji końca wiersza, ma nie więcej niż <code>100</code> znaków.</p></li>
</ul>
</div>