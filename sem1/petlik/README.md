<div class="no-overflow"><hr>

<h2>Wprowadzenie</h2>

<p>Rozważamy realizację języka programowania Pętlik. Składnię języka opisuje
gramatyka z symbolem początkowym <code>Program</code>:</p>

<pre><code>Program → CiągInstrukcji
CiągInstrukcji → ε | CiągInstrukcji Instrukcja
Instrukcja → Zwiększ | Powtarzaj
Zwiększ → Zmienna
Powtarzaj → '(' Zmienna CiągInstrukcji ')'
Zmienna → 'a' | .. | 'z'
</code></pre>

<p>Program składa się ze znaków, które w gramatyce są ujęte w apostrofy.
Oprócz nich żadne inne znaki, nawet spacje lub końce wiersza, w kodzie
źródłowym nie mogą wystąpić.</p>

<p>Program ma dostęp do <code>26</code> zmiennych, których wartości są nieujemnymi
liczbami całkowitymi.</p>

<p>Instrukcje wyprowadzone z symbolu <code>CiągInstrukcji</code> są wykonywane
w kolejności od pierwszej do ostatniej.</p>

<p>Instrukcja <code>Zwiększ</code>, w postaci <code>zmienna</code>, jest równoważna instrukcji
języka C:</p>

<pre><code class="c">++zmienna;
</code></pre>

<p>Instrukcja <code>Powtarzaj</code>, w postaci <code>(zmienna...)</code>, jest równoważna
instrukcji języka C:</p>

<pre><code class="c">while (zmienna &gt; 0) {
    --zmienna;
    ...
}
</code></pre>

<p>Realizacja języka składa się z kompilatora optymalizującego, generującego
kod na maszynę wirtualną, oraz z interpretera kodu tej maszyny.</p>

<p>Maszyna wykonuje instrukcje:</p>

<ul>
<li><p><code>INC Zmienna</code> (<em>increment</em>)</p>

<p>zwiększ o jeden wartość zmiennej <code>Zmienna</code>;</p></li>
<li><p><code>ADD Zmienna0 Zmienna1</code> (<em>add</em>)</p>

<p>dodaj do zmiennej <code>Zmienna0</code> wartość zmiennej <code>Zmienna1</code>;</p></li>
<li><p><code>CLR Zmienna</code> (<em>clear</em>)</p>

<p>wyzeruj zmienną <code>Zmienna</code>;</p></li>
<li><p><code>JMP Adres</code> (<em>jump</em>)</p>

<p>skocz do instrukcji o adresie <code>Adres</code>;</p></li>
<li><p><code>DJZ Zmienna Adres</code> (<em>decrement or jump if zero</em>)</p>

<p>jeśli <code>Zmienna</code> ma wartość <code>0</code> to skocz do instrukcji o adresie
<code>Adres</code>, w przeciwnym przypadku zmniejsz o jeden wartość zmiennej
<code>Zmienna</code>;</p></li>
<li><p><code>HLT</code> (<em>halt</em>)</p>

<p>zakończ wykonywanie programu.</p></li>
</ul>

<p>Wykonanie programu w języku maszynowym zaczyna się od pierwszej instrukcji.</p>

<p>Jeśli instrukcja nie określa inaczej, to po jej wykonaniu przechodzimy
do następnej instrukcji w kodzie.</p>

<p>Dla ciągu instrukcji kompilator generuje kod w kolejności od pierwszej do
ostatniej instrukcji. Kod wygenerowany dla programu kończy instrukcją
<code>HLT</code>.</p>

<p>Jeżeli w instrukcji <code>Powtarzaj</code> nie jest zagnieżdżona inna instrukcja
<code>Powtarzaj</code>, czyli w nawiasach jest ciąg zmiennych <code>Zmienna0</code>, ...,
<code>ZmiennaN</code>, dla <code>N &gt;= 0</code>, i jeżeli żadna ze zmiennych <code>Zmienna1</code>, ...,
<code>ZmiennaN</code> nie jest zmienną <code>Zmienna0</code>, to kompilator generuje kod
zoptymalizowany postaci:</p>

<pre><code>ADD Zmienna1 Zmienna0
...
ADD ZmiennaN Zmienna0
CLR Zmienna0
</code></pre>

<p>Jeśli nie są spełnione warunki umożliwiające wygenerowanie dla instrukcji
kodu zoptymalizowanego, to kompilator generuje kod zwykły.</p>

<p>Kodem zwykłym dla instrukcji <code>Zwiększ</code>, postaci <code>Zmienna</code>, jest:</p>

<pre><code>INC Zmienna
</code></pre>

<p>Kodem zwykłym dla instrukcji <code>Powtarzaj</code>, postaci <code>(Zmienna...)</code>, jest:</p>

<pre><code>DJZ Zmienna Koniec
...
JMP Początek
</code></pre>

<p>gdzie <code>Początek</code> to adres pierwszej instrukcji tego ciągu a <code>Koniec</code> to adres
instrukcji bezpośrednio za ostatnią instrukcją tego ciagu.</p>

<h2>Polecenie</h2>

<p>Napisz program będący realizacją języka Pętlik.</p>

<p>Program czyta i wykonuje polecenia:</p>

<ul>
<li><p>wypisania wartości zmiennej;</p></li>
<li><p>wykonania programu w języku Pętlik.</p></li>
</ul>

<p>Przed wykonaniem pierwszego polecenia wartości wszystkich zmiennych są
równe <code>0</code>.</p>

<p>Zmienne zachowują wartości po wykonaniu polecenia. Nie są zerowane przed
każdym wykonaniem programu.</p>

<h2>Postać danych</h2>

<p>Dane programu to ciąg wierszy, w każdym po jednym poleceniu.</p>

<p>Polecenie wypisania wartości zmiennej zaczyna się od znaku <code>=</code>, po którym
jest nazwa zmiennej.</p>

<p>Wiersz polecenia wykonania programu zawiera kod źródłowy w języku Pętlik.</p>

<h2>Postać wyniku</h2>

<p>Wynik programu jest efektem wykonania poleceń wypisania wartości zmiennej.</p>

<p>Wartość zmiennej piszemy dziesiętnie, w jednym wierszu, bez nieznaczących
zer wiodących.</p>

<p>Wartość <code>0</code> jest reprezentowana przez cyfrę <code>0</code> a wartości dodatnie
zaczynają się od cyfry niezerowej.</p>

<h2>Przykłady</h2>

<p>Do treści zadania dołączone są pliki <code>.in</code> z przykładowymi danymi i pliki
<code>.out</code> z wynikami wzorcowymi.</p>

<ul>
<li><p>Dla danych
<a href="https://moodle.mimuw.edu.pl/pluginfile.php/276227/mod_folder/content/0/przyklad1.in?forcedownload=1">
przyklad1.in
</a>
poprawny wynik to
<a href="https://moodle.mimuw.edu.pl/pluginfile.php/276227/mod_folder/content/0/przyklad1.out?forcedownload=1">
przyklad1.out
</a>.</p></li>
<li><p>Dla danych
<a href="https://moodle.mimuw.edu.pl/pluginfile.php/276227/mod_folder/content/0/przyklad2.in?forcedownload=1">
przyklad2.in
</a>
poprawny wynik to
<a href="https://moodle.mimuw.edu.pl/pluginfile.php/276227/mod_folder/content/0/przyklad2.out?forcedownload=1">
przyklad2.out
</a>.</p></li>
<li><p>Dla danych
<a href="https://moodle.mimuw.edu.pl/pluginfile.php/276227/mod_folder/content/0/przyklad3.in?forcedownload=1">
przyklad3.in
</a>
poprawny wynik to
<a href="https://moodle.mimuw.edu.pl/pluginfile.php/276227/mod_folder/content/0/przyklad3.out?forcedownload=1">
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
reprezentacją końca wiersza <code>'\n'</code>. Należy zadbać, by warunek ten
spełniał także wynik programu.</p></li>
<li><p>Wolno założyć, że podczas pracy programu nie nastąpi przepełnienie
pamięci, czyli że wynikiem funkcji <code>malloc()</code> lub <code>realloc()</code>,
wywołanej z odpowiednimi argumentami, nie będzie <code>NULL</code>.</p></li>
<li><p>Wolno założyć, że długość programu w języku Pętlik nie przekroczy
<code>INT_MAX - 1</code>.</p></li>
<li><p>Wolno założyć, że wartości zmiennych, zapisane dziesiętnie, będą
miały mniej niż <code>INT_MAX</code> cyfr.</p></li>
<li><p>Nie wolno nakładać dodatkowych ograniczeń na długość programu
i wartości zmiennych.</p>

<p>W szczególności nie można zakładać, że wartości zmiennych będą
w zakresie któregokolwiek z wbudowanych typów języka C, np. typu <code>int</code>.</p></li>
<li><p>Efektywność rozwiązania może mieć&nbsp;wpływ na ocenę jego jakości.</p>

<p>Rozwiązanie rażąco nieefektywne może nie przejść testów, a nawet
walidacji, z powodu przekroczenia limitu czasu.</p></li>
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