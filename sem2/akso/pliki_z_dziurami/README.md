<div class="no-overflow"><span class="filter_mathjaxloader_equation"><span class="nolink"><h2>Pliki z dziurami</h2>

<p>Pliki w Linuksie mogą być dziurawe. Na potrzeby tego zadania przyjmujemy, że
plik z dziurami składa się z ciągłych fragmentów. Na początku fragmentu jest
dwubajtowa długość w bajtach danych we fragmencie. Potem są dane. Fragment
kończy się czterobajtowym przesunięciem, które mówi, o ile bajtów trzeba się
przesunąć od końca tego fragmentu do początku następnego fragmentu. Długość
danych w bloku jest 16-bitową liczbą w naturalnym kodzie binarnym. Przesunięcie
jest 32-bitową liczbą w kodowaniu uzupełnieniowym do dwójki. Liczby w pliku
zapisane są w porządku cienkokońcówkowym (ang. <em>little-endian</em>). Pierwszy
fragment zaczyna się na początku pliku. Ostatni fragment rozpoznaje się po tym,
że jego przesunięcie wskazuje na niego samego. Fragmenty w pliku mogą się
stykać i  nakładać.</p>

<h2>Suma kontrolna pliku</h2>

<p>Sumę kontrolną pliku obliczamy za pomocą
<a href="https://pl.wikipedia.org/wiki/Cykliczny_kod_nadmiarowy">cyklicznego kodu nadmiarowego</a>
(ang. <em>CRC</em>, <em>cyclic redundancy code</em>), biorąc pod uwagę dane w kolejnych
fragmentach pliku. Dane pliku przetwarzamy bajtami. Przyjmujemy, że najbardziej
znaczący bit bajtu z danymi i wielomianu (dzielnika) CRC zapisujemy po lewej
stronie.</p>

<h2>Polecenie</h2>

<p>Zaimplementuj w asemblerze program <code>crc</code>, który oblicza sumę kontrolną danych
zawartych w podanym pliku z dziurami:</p>

<pre><code>./crc file crc_poly
</code></pre>

<p>Parametr <code>file</code> to nazwa pliku. Parametr <code>crc_poly</code> to ciąg zer i jedynek
opisujący wielomian CRC. Nie zapisujemy współczynika przy najwyższej potędze.
Maksymalny stopień wielomianu CRC to 64 (maksymalna długość dzielnika CRC to
65). Przykładowo <code>11010101</code> oznacza wielomian <span class="MathJax_Preview" style="color: inherit;"></span><span class="MathJax" id="MathJax-Element-1-Frame" tabindex="0" style="position: relative;" data-mathml="&lt;math xmlns=&quot;http://www.w3.org/1998/Math/MathML&quot;&gt;&lt;msup&gt;&lt;mi&gt;x&lt;/mi&gt;&lt;mn&gt;8&lt;/mn&gt;&lt;/msup&gt;&lt;mo&gt;+&lt;/mo&gt;&lt;msup&gt;&lt;mi&gt;x&lt;/mi&gt;&lt;mn&gt;7&lt;/mn&gt;&lt;/msup&gt;&lt;mo&gt;+&lt;/mo&gt;&lt;msup&gt;&lt;mi&gt;x&lt;/mi&gt;&lt;mn&gt;6&lt;/mn&gt;&lt;/msup&gt;&lt;mo&gt;+&lt;/mo&gt;&lt;msup&gt;&lt;mi&gt;x&lt;/mi&gt;&lt;mn&gt;4&lt;/mn&gt;&lt;/msup&gt;&lt;mo&gt;+&lt;/mo&gt;&lt;msup&gt;&lt;mi&gt;x&lt;/mi&gt;&lt;mn&gt;2&lt;/mn&gt;&lt;/msup&gt;&lt;mo&gt;+&lt;/mo&gt;&lt;mn&gt;1&lt;/mn&gt;&lt;/math&gt;" role="presentation"><nobr aria-hidden="true"><span class="math" id="MathJax-Span-1" style="width: 14.156em; display: inline-block;"><span style="display: inline-block; position: relative; width: 11.587em; height: 0px; font-size: 122%;"><span style="position: absolute; clip: rect(1.478em, 1011.48em, 2.68em, -999.997em); top: -2.456em; left: 0em;"><span class="mrow" id="MathJax-Span-2"><span class="msubsup" id="MathJax-Span-3"><span style="display: inline-block; position: relative; width: 0.986em; height: 0px;"><span style="position: absolute; clip: rect(3.391em, 1000.5em, 4.156em, -999.997em); top: -3.986em; left: 0em;"><span class="mi" id="MathJax-Span-4" style="font-family: STIXGeneral; font-style: italic;">𝑥</span><span style="display: inline-block; width: 0px; height: 3.992em;"></span></span><span style="position: absolute; top: -4.369em; left: 0.549em;"><span class="mn" id="MathJax-Span-5" style="font-size: 70.7%; font-family: STIXGeneral;">8</span><span style="display: inline-block; width: 0px; height: 3.992em;"></span></span></span></span><span class="mo" id="MathJax-Span-6" style="font-family: STIXGeneral; padding-left: 0.276em;">+</span><span class="msubsup" id="MathJax-Span-7" style="padding-left: 0.276em;"><span style="display: inline-block; position: relative; width: 0.986em; height: 0px;"><span style="position: absolute; clip: rect(3.391em, 1000.5em, 4.156em, -999.997em); top: -3.986em; left: 0em;"><span class="mi" id="MathJax-Span-8" style="font-family: STIXGeneral; font-style: italic;">𝑥</span><span style="display: inline-block; width: 0px; height: 3.992em;"></span></span><span style="position: absolute; top: -4.369em; left: 0.549em;"><span class="mn" id="MathJax-Span-9" style="font-size: 70.7%; font-family: STIXGeneral;">7</span><span style="display: inline-block; width: 0px; height: 3.992em;"></span></span></span></span><span class="mo" id="MathJax-Span-10" style="font-family: STIXGeneral; padding-left: 0.276em;">+</span><span class="msubsup" id="MathJax-Span-11" style="padding-left: 0.276em;"><span style="display: inline-block; position: relative; width: 0.986em; height: 0px;"><span style="position: absolute; clip: rect(3.391em, 1000.5em, 4.156em, -999.997em); top: -3.986em; left: 0em;"><span class="mi" id="MathJax-Span-12" style="font-family: STIXGeneral; font-style: italic;">𝑥</span><span style="display: inline-block; width: 0px; height: 3.992em;"></span></span><span style="position: absolute; top: -4.369em; left: 0.549em;"><span class="mn" id="MathJax-Span-13" style="font-size: 70.7%; font-family: STIXGeneral;">6</span><span style="display: inline-block; width: 0px; height: 3.992em;"></span></span></span></span><span class="mo" id="MathJax-Span-14" style="font-family: STIXGeneral; padding-left: 0.276em;">+</span><span class="msubsup" id="MathJax-Span-15" style="padding-left: 0.276em;"><span style="display: inline-block; position: relative; width: 0.986em; height: 0px;"><span style="position: absolute; clip: rect(3.391em, 1000.5em, 4.156em, -999.997em); top: -3.986em; left: 0em;"><span class="mi" id="MathJax-Span-16" style="font-family: STIXGeneral; font-style: italic;">𝑥</span><span style="display: inline-block; width: 0px; height: 3.992em;"></span></span><span style="position: absolute; top: -4.369em; left: 0.549em;"><span class="mn" id="MathJax-Span-17" style="font-size: 70.7%; font-family: STIXGeneral;">4</span><span style="display: inline-block; width: 0px; height: 3.992em;"></span></span></span></span><span class="mo" id="MathJax-Span-18" style="font-family: STIXGeneral; padding-left: 0.276em;">+</span><span class="msubsup" id="MathJax-Span-19" style="padding-left: 0.276em;"><span style="display: inline-block; position: relative; width: 0.986em; height: 0px;"><span style="position: absolute; clip: rect(3.391em, 1000.5em, 4.156em, -999.997em); top: -3.986em; left: 0em;"><span class="mi" id="MathJax-Span-20" style="font-family: STIXGeneral; font-style: italic;">𝑥</span><span style="display: inline-block; width: 0px; height: 3.992em;"></span></span><span style="position: absolute; top: -4.369em; left: 0.549em;"><span class="mn" id="MathJax-Span-21" style="font-size: 70.7%; font-family: STIXGeneral;">2</span><span style="display: inline-block; width: 0px; height: 3.992em;"></span></span></span></span><span class="mo" id="MathJax-Span-22" style="font-family: STIXGeneral; padding-left: 0.276em;">+</span><span class="mn" id="MathJax-Span-23" style="font-family: STIXGeneral; padding-left: 0.276em;">1</span></span><span style="display: inline-block; width: 0px; height: 2.462em;"></span></span></span><span style="display: inline-block; overflow: hidden; vertical-align: -0.13em; border-left: 0px solid; width: 0px; height: 1.203em;"></span></span></nobr><span class="MJX_Assistive_MathML" role="presentation"><math xmlns="http://www.w3.org/1998/Math/MathML"><msup><mi>x</mi><mn>8</mn></msup><mo>+</mo><msup><mi>x</mi><mn>7</mn></msup><mo>+</mo><msup><mi>x</mi><mn>6</mn></msup><mo>+</mo><msup><mi>x</mi><mn>4</mn></msup><mo>+</mo><msup><mi>x</mi><mn>2</mn></msup><mo>+</mo><mn>1</mn></math></span></span><script type="math/tex" id="MathJax-Element-1">x^8+x^7+x^6+x^4+x^2+1</script>. Wielomian
stały uznajemy za niepoprawny.</p>

<p>Program wypisuje na standardowe wyjście obliczoną sumę kontrolną jako napis
składający się z zer i jedynek, zakończony znakiem nowego wiersza <code>\n</code>. Program
sygnalizuje poprawne zakończenie kodem <code>0</code>.</p>

<p>Program powinien korzystać z funkcji systemowych Linuksa: <code>sys_open</code>,
<code>sys_read</code>, <code>sys_write</code>, <code>sys_lseek</code>, <code>sys_close</code>, <code>sys_exit</code>.</p>

<p>Program powinien sprawdzać poprawność parametrów i poprawność wykonania funkcji
systemowych (z wyjątkiem <code>sys_exit</code>). Jeśli któryś z parametrów jest niepoprawny
lub wywołanie funkcji systemowej zakończy się błędem, to program powinien
zakończyć się kodem <code>1</code>. W każdej sytuacji program powinien przed zakończeniem
jawnie wywołać funkcję <code>sys_close</code> dla pliku, który otworzył.</p>

<p>Dla uzyskania zadowalającej szybkości działania programu odczyty należy
buforować. Należy dobrać optymalny rozmiar bufora, a informację o tym umieścić
w komentarzu.</p>

<h2>Oddawanie rozwiązania</h2>

<p>Jako rozwiązanie należy wstawić w Moodle plik o nazwie <code>crc.asm</code>.</p>

<h2>Kompilowanie</h2>

<p>Rozwiązanie będzie kompilowane poleceniami:</p>

<pre><code>nasm -f elf64 -w+all -w+error -o crc.o crc.asm
ld --fatal-warnings -o crc crc.o
</code></pre>

<p>Rozwiązanie musi się kompilować i działać w laboratorium komputerowym.</p>

<h2>Przykłady użycia</h2>

<p>Na komputerach w laboratorium i na maszynie <code>students</code> rozwiązanie powinno
wypisywać poniższe wyniki:</p>

<pre><code>$ ./crc /home/students/inf/PUBLIC/SO/zadanie_3_2024/plik1 1; echo $?
1
0
</code></pre>

<pre><code>$ ./crc /home/students/inf/PUBLIC/SO/zadanie_3_2024/plik1 011; echo $?
010
0
</code></pre>

<pre><code>$ ./crc /home/students/inf/PUBLIC/SO/zadanie_3_2024/plik1 11010101; echo $?
01010111
0
</code></pre>

<pre><code>$ ./crc /home/students/inf/PUBLIC/SO/zadanie_3_2024/plik2 1; echo $?
1
0
</code></pre>

<pre><code>$ ./crc /home/students/inf/PUBLIC/SO/zadanie_3_2024/plik2 011; echo $?
111
0
</code></pre>

<pre><code>$ ./crc /home/students/inf/PUBLIC/SO/zadanie_3_2024/plik2 11010101; echo $?
10110011
0
</code></pre>

<pre><code>$ ./crc /home/students/inf/PUBLIC/SO/zadanie_3_2024/plik 1; echo $?
1
</code></pre>

<pre><code>$ ./crc /home/students/inf/PUBLIC/SO/zadanie_3_2024/plik1 ""; echo $?
1
</code></pre>

<p>Inne przykłady można wygenerować za pomocą
<a href="https://rndtool.info/CRC-step-by-step-calculator">kalkulatora CRC</a>.</p>

<h2>Ocenianie</h2>

<p>Zgodność rozwiązania ze specyfikacją będzie oceniania za pomocą testów
automatycznych, za które można otrzymać maksymalnie 7 punktów. Za błędną nazwę
pliku odejmiemy jeden punkt.</p>

<p>Za jakość kodu i styl programowania można dostać maksymalnie 3 punkty.
Tradycyjny styl programowania w asemblerze polega na rozpoczynaniu etykiet
od pierwszej kolumny, a mnemoników rozkazów od wybranej ustalonej kolumny.
Nie stosuje się innych wcięć. Taki styl mają przykłady pokazywane na zajęciach.
Kod powinien być dobrze skomentowany, co oznacza między innymi, że każdy blok
kodu powinien być opatrzony informacją, co robi. Należy opisać przeznaczenie
rejestrów. Komentarza wymagają wszystkie kluczowe lub nietrywialne linie kodu.
W przypadku asemblera nie jest przesadą komentowanie prawie każdej linii kodu,
ale należy unikać komentarzy opisujących to, co widać.</p>

<p>W tym zadaniu priorytetem jest szybkość działania programu, ale oceniane będą
też rozmiary sekcji i wykorzystanie stosu.</p>

<p>Wystawienie oceny może zostać uzależnione od osobistego wyjaśnienia szczegółów
działania programu prowadzącemu zajęcia.</p>

<p><strong>Rozwiązania należy implementować samodzielnie pod rygorem niezaliczenia
przedmiotu. Zarówno korzystanie z cudzego kodu, jak i prywatne lub publiczne
udostępnianie własnego kodu jest zabronione.</strong></p>
</span></span></div>