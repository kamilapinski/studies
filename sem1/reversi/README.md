<div class="no-overflow"><hr>

<h2>Wprowadzenie</h2>

<p>Gra
<a href="https://en.wikipedia.org/wiki/Reversi">
<em>Reversi</em>
</a>,
znana też pod nazwą <em>Othello</em>, jest rozgrywana na <code>64</code>-polowej planszy
podzielonej na wiersze od <code>1</code> do <code>8</code> i kolumny od <code>a</code> do <code>h</code>. Pola
nazywamy, wskazując najpierw kolumnę a następnie wiersz.</p>

<p>W Reversi gra się dwustronnymi czarno-białymi pionami. Na każdym polu może
być co najwyżej jeden pion. Jeśli pion w danej chwili jest odwrócony do
góry stroną czarną, nazywamy go pionem czarnym, jeśli białą - jest pionem
białym.</p>

<p>Partię gry w Reversi zaczyna się na planszy z pionami białymi na polach
<code>d4</code> i <code>e5</code> oraz pionami czarnymi na polach <code>d5</code> i <code>e4</code>. Pozostałe pola są
puste.</p>

<p>W grze bierze udział dwóch graczy, nazywanych <em>Czarnym</em> i <em>Białym</em>, od
koloru pionów, którym się posługują. Grę rozpoczyna gracz Czarny.</p>

<p>Gracze wykonują, na przemian, po jednym ruchu polegającym na położeniu
na planszy piona swojego koloru. Jeśli na wszystkich polach w <em>linii</em>,
czyli w wierszu, kolumnie lub przekątnej, między położonym właśnie pionem
a innym pionem tego samego koloru, są piony w kolorze przeciwnym, zmieniają
one kolor, czyli są odwracane. Położenie na planszy jednego piona może
spowodować jednoczesną zmianę kilku linii pionów.</p>

<p>Ruch jest legalny, tylko gdy powoduje zmianę koloru co najmniej jednego
piona na planszy. Jeśli w danej chwili gracz nie może wykonać legalnego
ruchu to rezygnuje z niego i nie kładzie na planszy piona.</p>

<p>Choć nie jest to zgodne z regułami gry w Reversi, w tym zadaniu pozwalamy
graczowi zrezygnować z ruchu nawet, gdy może wykonać ruch legalny.</p>

<p>Kolejna reguła, która również u nas nie obowiązuje, określa że gra
automatycznie kończy się, gdy żaden z graczy nie może wykonać legalnego
ruchu. Zwycięzcą zostaje wówczas gracz, który ma na planszy więcej pionów
swojego koloru.</p>

<h2>Polecenie</h2>

<p>Napisz program umożliwiający grę w Reversi dwóm graczom.</p>

<p>Program, w pętli:</p>

<ol>
<li><p>pisze tekst zachęty (ang. <em>prompt</em>) wskazujący, do którego gracza
należy ruch i jakie legalne ruchy może on wykonać;</p></li>
<li><p>czyta polecenie aktualnego gracza;</p></li>
<li><p>jeśli wczytał polecenie przerwania gry, kończy pracę;</p></li>
<li><p>jeśli wczytał polecenie rezygnacji z ruchu, wraca na początek pętli;</p></li>
<li><p>jeśli wczytał polecenie wykonania ruchu, to wykonuje ten ruch i wraca
na początek pętli.</p></li>
</ol>

<p>Program nie kończy pracy przed dojściem do polecenia przerwania gry,
nawet gdyby stwierdził, że żaden z graczy nie może wykonać legalnego
ruchu. Nie uznaje też za błąd sytuacji, w której użytkownik rezygnuje
z ruchu lub prosi o przerwania gry, choć może wykonać legalny ruch.</p>

<h2>Postać danych</h2>

<p>Na wejściu programu są polecenia graczy. Każde polecenie zajmuje jeden
wiersz. Wszystkie polecenia mają poprawną postać. Polecenia wykonania ruchu
wskazują ruch legalny. Po poleceniu przerwania gry jest dowolny tekst,
ignorowany przez program.</p>

<ul>
<li><p>Polecenie przerwania gry ma postać wiersza o treści <code>=</code>.</p></li>
<li><p>Polecenie rezygnacji z ruchu ma postać wiersza o treści <code>-</code>.</p></li>
<li><p>Polecenie wykonania ruchu ma postać wiersza z nazwą pola.</p></li>
</ul>

<h2>Postać wyniku</h2>

<p>Na wyjściu programu jest ciąg tekstów zachęty. Każdy z nich zajmuje jeden
wiersz.</p>

<p>Tekst zachęty zaczyna się od wskazania gracza, do którego należy ruch:</p>

<ul>
<li><p>znak <code>C</code> wskazuje gracza Czarnego;</p></li>
<li><p>znak <code>B</code> wskazuje gracza Białego.</p></li>
</ul>

<p>Resztę treści tekstu zachęty stanowi ciąg nazw pól, na których gracz może
położyć swojego piona. Nazwy są uporządkowane leksykograficznie. Każda
nazwa jest poprzedzona spacją.</p>

<h2>Przykłady</h2>

<p>Do treści zadania dołączone są pliki <code>.in</code> z przykładowymi danymi i pliki
<code>.out</code> z wynikami wzorcowymi.</p>

<ul>
<li><p>Dla danych
<a href="https://moodle.mimuw.edu.pl/pluginfile.php/276156/mod_folder/content/0/przyklad1.in?forcedownload=1">
przyklad1.in
</a>
poprawny wynik to
<a href="https://moodle.mimuw.edu.pl/pluginfile.php/276156/mod_folder/content/0/przyklad1.out?forcedownload=1">
przyklad1.out
</a>.</p></li>
<li><p>Dla danych
<a href="https://moodle.mimuw.edu.pl/pluginfile.php/276156/mod_folder/content/0/przyklad2.in?forcedownload=1">
przyklad2.in
</a>
poprawny wynik to
<a href="https://moodle.mimuw.edu.pl/pluginfile.php/276156/mod_folder/content/0/przyklad2.out?forcedownload=1">
przyklad2.out
</a>.</p></li>
<li><p>Dla danych
<a href="https://moodle.mimuw.edu.pl/pluginfile.php/276156/mod_folder/content/0/przyklad3.in?forcedownload=1">
przyklad3.in
</a>
poprawny wynik to
<a href="https://moodle.mimuw.edu.pl/pluginfile.php/276156/mod_folder/content/0/przyklad3.out?forcedownload=1">
przyklad3.out
</a>.</p></li>
</ul>

<h2>Walidacja i testy</h2>

<ul>
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
<li><p>Podczas walidacji i testów, program <code>nazwa</code> jest uruchamiany pod
kontrolą programu Valgrind poleceniem:</p>

<pre><code class="bash">valgrind --leak-check=full -q --error-exitcode=1 ./nazwa
</code></pre>

<p>Jeśli Valgrind wykryje błąd, to nawet gdyby wynik był prawidłowy,
uznajemy, że program testu nie przeszedł.</p>

<p>Opcja <code>-q</code> powoduje, że jedynymi informacjami, wypisywanymi przez
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
reprezentacją końca wiersza <code>'\n'</code>.</p>

<p>Należy zadbać, by warunek ten spełniał także wynik programu.</p></li>
<li><p>Pod Linuxem, pracując z programem interakcyjnie na konsoli, koniec
danych sygnalizujemy, naciskając klawisze <code>Ctrl</code>-<code>D</code>.</p></li>
<li><p>W przygotowaniu danych testowych może pomóc polecenie <code>tee</code>.
Przesyła ono dane z wejścia na wyjście, jednocześnie zapisując ich
kopię w pliku, którego nazwa jest argumentem polecenia.</p>

<p>Wykonanie polecenia:</p>

<pre><code class="bash">tee test.in | ./nazwa
</code></pre>

<p>uruchomi program <code>nazwa</code>, przekazując mu na wejście i jednocześnie
zapisując do pliku <code>test.in</code>, to, co wpisze użytkownik.</p>

<p>Test na tych samych danych będzie można powtórzyć poleceniem:</p>

<pre><code class="bash">&lt; test.in ./nazwa &gt; test.out
</code></pre></li>
</ul>
</div>