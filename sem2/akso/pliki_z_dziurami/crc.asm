; Kamil Łapiński
global _start
    
SYS_OPEN    equ 2
SYS_READ    equ 0
SYS_WRITE   equ 1
SYS_LSEEK   equ 8
SYS_CLOSE   equ 3
SYS_EXIT    equ 60
STDOUT      equ 1

section .bss
    filename: resb 256      ; bufor z nazwą pliku
    crc_poly: resb 8        ; bufor z wielomianem CRC
    crc_poly_length: resb 1 ; bufor z długością wielomianu (w bitach) [1, 65]
    
    buffer: resb 16         ; bufor do odczytu danych
    bytes_read: resb 1      ; liczba wczytanych bajtów do buffer [0, 16]
    data_length: resb 2     ; zmienna na długość danych
    shift: resb 4           ; zmienna na przesunięcie

    data_read: resb 1       ; zmienna na informację czy już wszystkie dane wczytane

    answer: resb 8          ; zmienna na wynik

    filedesc: resd 1        ; deskryptor pliku (rezerwacja 1 * słowo 4-bajtowe) 

section .text

_start:

    mov     rdi, [rsp]          ; załadowanie do rdi ilości parametrów
    mov     rsi, [rsp + 16]     ; załadowanie do rsi adresu nazwy pliku

    ; Sprawdzenie czy jest podana poprawna ilość elementów
    cmp     rdi, 3
    jne     _error

    ; Ładowanie nazwy pliku do bufora filename
    lea     rdi, [filename]
    call    copy_string

    ; Ładowanie wielomianu CRC do bufora crc_poly
    lea     rdi, [crc_poly]
    mov     rsi, [rsp + 24]         ; załadowanie do rsi adresu wielomianu CRC

    ; Konwertowanie wielomianu crc na liczbę

    ; iterator w rcx
    ; w rdx będziemy przechowywać wynik
    xor     rdx, rdx
    xor     rcx, rcx
.crc_to_number_loop:
    shl     rdx, 1
    mov     al, [rsi + rcx]
    cmp     al, '1'
    jne     .check

    or      rdx, 1
.check:
    cmp     al, '0'
    je      .param_good
    cmp     al, '1'
    je      .param_good
    test    al, al
    jnz     _error
    
.param_good:
    inc     rcx
    mov     al, [rsi + rcx]
    test    al, al          ; kopiujemy dopóki al nie jest znakiem `\0'
    jnz     .crc_to_number_loop

    ;shr     rdx, 1
    inc     rcx
    mov     [crc_poly], rdx         ; ładujemy do bufora dzielnik crc
    mov     [crc_poly_length], cl   ; łądujemy do bufora długość dzielnika

    ; Otwieranie pliku
    ;   rax - numer funkcji systemowej sys_open
    ;   rdi - adres nazwy pliku
    ;   rsi - flagi (0 - read only)
    mov     rax, SYS_OPEN
    mov     rdi, filename
    xor     rsi, rsi
    syscall

    ; Sprawdzenie, czy wystąpił błąd przy otwieraniu
    cmp     rax, 0
    jl      _error

    mov     [filedesc], rax     ; załadowanie deskryptora pliku, do filedesc

    ; Wyzerowanie rdx, żeby korzystać z jego 8 najmłodszych bitów
    xor     rdx, rdx

    ; r9 - liczba bajtów danych do wczytania w aktualnym fragmencie
    ; r9 = 0 - trzeba wczytać przesunięcie
    ; r9 = -4 - trzeba wczytać długość danych
    mov     r9, -4

    push    rbx
    ; Liczba sztucznych bajtów
    mov     rbx, 16
.crc_calculate:
    ; Sprawdzenie, czy bufor jest wypełniony
    ;   r10b - liczba bajtów w buforze buffer
    ;cmp     r10b, 16
    xor     rdi, rdi
    call    fragment_read

    ;cmp     r10b, 16
    ;jne     _success

    cmp     rbx, 16
    je      _success

    ; Tutaj bufor musi być cały wypełniony

    ; Ładujemy dzielnik crc
    mov     rsi, [crc_poly]
    xor     r8, r8

    ; Obliczamy liczbę, o którą trzeba przesunąć dzielnik crc
    mov     cl, 64
    sub     cl, [crc_poly_length]

    cmp     cl, -1
    jne     .continue
    xor     cl, cl
    shr     rsi, 1
    adc     r8, 0
    shl     r8, 63

.continue:
    ; Przesuwamy dzielnik crc
    shl     rsi, cl

    ; Ładujemy aktualną część danych z bufora
    mov     rax, [buffer]
    mov     rdx, [buffer + 8]

    bswap   rax         ; odwraca kolejność bajtów w rax
    bswap   rdx

    ; Liczymy crc
    xor     rcx, rcx
    mov     cl, 64      ; ładujemy iterator
    
    test    rdi, rdi
    jz      .crc_calculate_loop
    sub     rdi, 8
    imul    rdi, 8
    sub     rcx, rdi
    xor     rdi, rdi

.crc_calculate_loop:
    cmp     rax, 0
    jge     .skip
    xor     rax, rsi
    xor     rdx, r8
.skip:
    shl     rax, 1
    shl     rdx, 1
    adc     rax, 0
    inc     rdi
    loop    .crc_calculate_loop

    shr     rdi, 3
    add     rbx, rdi

    bswap   rax
    bswap   rdx
    mov     qword [buffer], rax
    mov     qword [buffer + 8], rdx
    
    sub     r10, 8

    jmp     .crc_calculate

_success:
    ; Zamykanie pliku
    call    close_file

    ; Wypisanie wyniku
    call    write

    xor     rdi, rdi

exit:
    pop     rbx
    ; Zakończenie programu
    ;   rax - numer funkcji systemowej sys_exit
    ;   rdi - status (powodzenie 0)
    mov     rax, SYS_EXIT
    syscall

    ret


close_file:
    ; Zamykanie pliku
    ;   rax - numer funkcji systemowej sys_close
    ;   rdi - deskryptor pliku
    mov     rax, SYS_CLOSE
    mov     rdi, [filedesc]
    syscall

    ; Sprawdzenie czy wystąpił błąd
    cmp     rax, 0
    jl      _error

    ret


fragment_read:
    ; r9 - liczba bajtów danych do wczytania
    
    ; Sprawdzamy, czy jest jeszcze coś do wczytania
    mov     al, [data_read]
    test    al, al
    jnz     .return

    ; Sprawdzamy, czy musimy jeszcze dokładać do bufora dane
    cmp     r10b, 16
    jge     .return

.len_read:
    ; Sprawdzenie, czy teraz trzeba wczytać dane lub przesunięcie
    cmp     r9, -4
    jne     .data_read

    ; Wczytanie długości danych
    mov     rdx, 2
    lea     rsi, [data_length]
    call    read
    movzx   r9, word [data_length]

.data_read:
    ; Sprawdzenie, czy teraz trzeba wczytać przesunięcie
    test    r9, r9
    jz      .shift_read

    ; r9 - długość danych w bajtach
    ; rdx - ilość bajtów do wczytania

    ; Wyznaczamy liczbę bajtów do wczytania
    mov     rdx, 16
    sub     rdx, r10
    call    min

    lea     rsi, [buffer + r10]
    call    read
    add     r10b, dl        ; zwiększyliśmy liczbę bajtów w buffer o dl

    sub     rbx, rdx
    sub     r9, rdx         ; zmniejszamy liczbę bajtów danych do wczytania

.shift_read:
    ; Sprawdzenie, czy trzeba wczytać przesunięcie
    test    r9, r9
    jnz     .return

    ; Zmieniamy r9 na -4, bo teraz znowu będzie trzeba wczytać długość danych
    mov     r9, -4

    ; Wczytanie przesunięcia
    mov     rdx, 4
    lea     rsi, [shift]
    call    read

    mov     eax, [shift]   ; wprowadzenie wartości przesunięcia do rsi
    movsxd  rsi, eax

    ; Przesunięcie wskaźnika pliku
    call    lseek

    ; Załadowanie do edx długości całego fragmentu
    movzx   rdx, word [data_length]
    add     rdx, 6
    neg     rdx

    ; Sprawdzenie, czy przesunięcie wskazuje na ten sam fragment
    xor     rsi, rdx
    test    rsi, rsi
    sete    al
    mov     [data_read], al

    ; Jeżeli przesunięcie nie wskazuje na ten sam fragment, to dalej wczytujemy
    test    rsi, rsi
    jne     fragment_read

    ; Jeżeli przesunięcie wskazuje na ten sam fragment, to musimy, dołozyć zer do bufora

    xor     rdi, rdi        ; w rdi będziemy tymczasowo przechowywać liczbę bajtów "sztucznych"
.add_zeros_loop:
    cmp     r10b, 16
    jge     .return

    mov     byte [buffer + r10],  0
    
    inc     rdi
    inc     r10b
    jmp     .add_zeros_loop

.return:
    ret

copy_string:
    xor     rcx, rcx
.copy_loop:
    mov     al, [rsi + rcx]
    mov     [rdi + rcx], al
    inc     rcx
    test    al, al          ; kopiujemy dopóki al nie jest znakiem `\0'
    jnz     .copy_loop
    ret

lseek:
    ; Przed wywołaniem lseek trzeba w rsi ustawić wartości przesunięcia
    ; Przesuwanie wskaźnika pliku
    ;   rax - numer funkcji systemowej sys_leek
    ;   rdi - deksryptor pliku
    ;   rsi - wartość przesunięcia
    ;   rdx - tryb przesunięcia
    mov     rax, SYS_LSEEK
    mov     rdi, [filedesc]
    mov     rdx, 1      ; tryb przesunięcia SEEK_CUR (przesunięcie względem aktualnej pozycji wskaźnika)
    syscall
    xor     rdi, rdi    ; potrzebne zerowanie

    ; Sprawdzanie, czy wystąpił błąd
    cmp     rax, 0
    jl      _error_and_close

    ret

read:
    ; Wywołując read trzeba 
    ;   w rdx ustawić ile bajtów chce się czytać
    ;   w rsi ustawić adres bufora

    ; Czytanie pliku
    ;   rax - numer funkcji systemowej sys_read
    ;   rdi - deskryptor pliku
    ;   rsi - wskaźnik na bufor
    ;   rdx - maksymalna liczba bajtów do odczytania

    mov     rdi, [filedesc]
    mov     rax, SYS_READ
    syscall
    xor     rdi, rdi    ; potrzebne zerowanie

    ; Sprawdzenie, czy nie wystąpił błąd podczas czytania pliku
    cmp     rax, 0
    jle      _error_and_close

    ; Sprawdzenie, czy koniec pliku
    je      _success
    
    ; Sprawdzenie, czy wczytano tyle bajtów ile trzeba było wczytać
    cmp     rax, rdx
    jne     _error_and_close

    ret
    
write:
    mov     rax, [buffer]
    bswap   rax
    movzx   ecx, byte [crc_poly_length]
    dec     ecx

    xor     r10, r10
    lea     rsi, [answer]
.to_string:
    mov     dl, '0'
    cmp     rax, 0
    jge     .to_buffor
    inc     dl
.to_buffor:
    mov     [answer + r10], dl
    inc     r10
    shl     rax, 1
    loop    .to_string

    mov     dl, 10
    mov     [answer + r10], dl

    ; Wypisanie wyniku
    ;   rax - numer funkcji systemowej sys_write
    ;   rdi - deskryptor pliku (w tym przypadku 1 bo wypisujemy na stdout)
    ;   rsi - adres bufora
    ;   rdx - liczba bajów
    mov     rax, SYS_WRITE
    mov     rdi, STDOUT
    lea     rsi, [answer]
    movzx   rdx, byte [crc_poly_length]
    syscall

    ; Sprawdzamy, czy nie wystąpił błąd
    cmp     rax, 0
    jl      _error

    ret

min:
    ; funkcja wpisująca do rdx minimum z rdx i r9
    cmp     rdx, r9
    jl      .return
    mov     rdx, r9
.return:
    ret

_error_and_close:
    call    close_file
_error:
    mov     rdi, 1
    call    exit

    ret