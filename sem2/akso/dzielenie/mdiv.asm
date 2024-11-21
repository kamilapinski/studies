global mdiv

section .text

mdiv:
    ; RAX - aktualna dzielna
    ; RCX - iterator
    ; RDX - reszta z poprzedniego dzielenia
    ; RSI - rozmiar tablicy
    ; RDI - wskaźnik na tablicę
    ; R8 - zapasowy wskaźnik na tablicę
    ; R9 - dzielnik
    ; R10 - -1 jeśli dzielna jest ujemna, 0 jeśli dodatnia (czyli jest pierwsza zamiana w pętli divide_loop)
    ; r11 - -1 jeśli tylko dzielna lub tylko dzielnik jest ujemny, 0 wpp (czyli jest druga zamiana w pętli divide_loop)

    xor     r10, r10
    xor     r11, r11

    mov     r9, rdx                 ; r9 staje się dzielnikiem
    mov     r8, rdi                 ; zapasowy wskaźnik
    lea     rdi, [rdi + rsi * 8 - 8]    ; wyznaczamy wskaźnik na najbardziej znaczący element (rdi)

    mov     rax, [rel rdi]
    mov     r10, rax                ; sprawdzamy znak dzielnej
    sar     r10, 63                 ; oznaczając rejestr r10

    mov     r11, r9                 ; sprawdzamy znak dzielnika
    sar     r11, 63                 ; oznaczając rejestr r11

    xor     r9, r11                 ; zamiana dzielnika na dodatni
    sub     r9, r11

    xor     r11, r10                ; teraz r11 = -1, gdy tylko dzielna lub tylko dzielnik jest ujemny, 0 wpp
                                    ; (czyli -1 gdy druga zamiana jest potrzebna)

    mov     al, r10b                ; wpisujemy do rax, że potrzebna jest pierwsza zamiana

negation:
    lea     rdi, [r8]               ; ustawiamy wskaźnik, na najmniej znaczący element 
    lea     rcx, [rsi]              ; przygotowujemy iterator
    
    test    al, al                  ; w rax jest r10 lub r11, czyli sprawdzamy czy potrzebna jest odpowiednio pierwsza i druga zamiana
    jz      .exit
    stc

.start:
    not     qword [rel rdi]          ; dzielimy aktualny segment liczby przez dzielnik; not nie zmienia flagi CF
    adc     qword [rel rdi], 0
    lea     rdi, [rdi + 8]          ; przechodzimy do kolejnego elementu tablicy (x); nie modyfikuje flagi CF
    loop    .start

.exit:
    cmp     r10b, 1                 ; jeśli już dzieliliśmy to skaczemy do end
    je      end

divide_loop:
    lea     rdi, [r8 + rsi * 8 - 8] ; teraz wskazuje na najbardziej znaczący segment
    xor     rdx, rdx                ; zerujemy resztę
    lea     rcx, [rsi]                ; przygotowujemy iterator

.start:
    mov     rax, [rel rdi]
    div     r9                      ; dzielimy aktualny segment liczby przez dzielnik
    mov     [rel rdi], rax

    lea     rdi, [rdi - 8]          ; przechodzimy do kolejnego elementu tablicy (x)
    loop    .start

.exit:
    mov     r9, rdx                 ; zapisujemy resztę, dzielnik nie będzie nam już potrzebny

    xor     r9, r10                 ; jeśli dzielna była ujemna, to tu zmienimy resztę na ujemny
    sub     r9, r10

    mov     al, r11b                ; wpisujemy do rax informację o tym, czy potrzebna jest druga zamiana (potrzebne w negation)
    mov     r10b, 1                 ; wpisujemy informację, że już dzieliliśmy
        
    jmp     negation

end:
    ; jeśli tylko dzielnik lub tylko dzielna ujemna, to wynik powinien ujemny, więc nie sprawdzamy czy znak dobrze wyszedł
    test     r11b, r11b              ; teraz oznacza, to, że tylko dzielnik lub tylko dzielna była ujemna            
    jnz      .after_check
        
    ; wynik musi być dodatni
    mov     rax, [rel r8 + 8 * rsi - 8]
    cmp     rax, 0
    jl      overflow

.after_check:

    mov     rax, r9                ; przypisanie reszty do rax
    ret

overflow: ; obsluga nadmiaru
    xor     al, al
    div     al