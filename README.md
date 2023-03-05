# Student-Grade-Platform
Object-Oriented Programming

Programul folosit: IntelliJ IDEA

Grad dificultate: mediu

Timp alocat rezolvarii: 2 saptamani

Clasele:

->Catalog -am utilizat sablonul de proiectare Singleton
          -addCourse: adauga un curs
          -removeCourse: sterge un curs
          
->User - superclasa pentru clasele Student, Parent, Assistant si Teacher
       - contine settere si gettere corespunzatoare
       
->Grade - contine partialScore, examScore de tip Double; studentul de tip Student si numele cursului, de tip String
        - getTotal: returneaza nota totala
        
->Group - Fiecare grupa va avea asociat un obiect de tip Assistant si un ID de tip String

->Course - clasa abstracta ce contine: un nume (de tipul String), un profesor
titular, o multime de asistenti (colectie fara duplicate), o colectie ordonata cu obiecte de tipul Grade, un dictionar ce contine grupele (cheia este de ID-ul grupei, iar valoarea este grupa) si un numar intreg ce reprezinta numarul de puncte credit.
         - foloseste sablonul de proiectare Builder
         
         - addAssistant: Seteaza asistentul in grupa cu ID-ul indicat, daca nu exista deja, adauga asistentul si in multimea asistentilor
         - addStudent: Adauga studentul in grupa cu ID-ul indicat
         - addGroup: Adauga o grupa
         - getGrade: Returneaza nota unui student sau null
         - addGrade: Adauga o nota
         - getAllStudents: Returneaza o lista cu tori studentii cursului
         - gettAllStudentGrades: Returneaza un dictionar cu situatia studentilor
         - getGraduatedStudents: Determina studentii promovati
         -contine o clasă interna abstracta CourseBuilder ce va fi extinsa in clasele pentru cele două tipuri de cursuri (clasele interne FullCourseBuilder si PartialCourseBuilder).
       
       
Design pattern-uri:
->Observer: parintii pot primi notificari legate de situatia copiilor

->Strategy: profesorul alege strategia pentru a alege cel mai bun student, la sfarsitul semestrului

->Visitor: profesorul/ asistentul poate completa notele de la examen/ de pe parcursul semestrului

->Memento: neimplementata


Testarea: - (din cauza lipsei de timp) am creat un fisier in care cursurile sunt delimitate prin * si grupele fiecarui curs cu #. Pentru fiecare se respecta urmatorul tipar:

CURS: nume_curs //nr_credite tip_curs //strategie_curs //firstname_profesor //lastname_profesor //grupe

GRUPA: id_grupa //firstname_asistent //lastname_asistent //studenti

STUDENT: firstname lastname fn_mama ln_mama fn_tata ln_tata nota_examen nota_partial

---ulterior, am incercat parsarea cu json simple, Test_citire_json.java cu fisierul Test_citire.json

Interfata grafica:

-pagina principala contine 3 campuri FIRSTNAME, LASTNAME, TYPE(care poate fi unul din tipurile de useri)

-am implementat doar pagina STUDENT care ofera informatii precum: numele profesorului de la curs, numele asistentilor, numele asistentului de la laborator, numele parintilor studentului, notele pe parcurs si de la examen; la apasarea butonului unui curs la care este inscris studentul. 

![image](https://user-images.githubusercontent.com/94400419/222975029-2adc6083-9806-433d-888d-b1b98e2f71ff.png)
![image](https://user-images.githubusercontent.com/94400419/222975040-9d33792a-a578-4a4b-9d9e-1b2d2948d7f8.png)




