# M223 Punchclock

Folgende Schritte sind notwendig um die Applikation zu erstellen und zu starten: 
1. Stellen Sie sicher, dass OpenJDK 11 oder höher installiert und JAVA_HOME korrekt gesetzt ist.  
2. Installieren Sie (falls noch nicht vorhanden) Apache Maven 3.8.1 oder höher
3. Wechseln Sie auf der Kommandozeile in den Ordner dieser Appliation. 
`cd m223-helloworld-quarkus/`
4. Starten Sie die Applikation mit 
```shell script
./mvnw compile quarkus:dev
```

Folgende Dienste stehen während der Ausführung im Profil dev zur Verfügung:

Swagger API: http://localhost:8080/q/swagger-ui/

H2 Console: http://localhost:8080/h2/ 
Datenquelle: jdbc:h2:mem:punchclock
Benutzername: zli
Passwort: zli

#About
In diesem Projekt kann man sich als User anmelden und seine Zeit tracken.
Diese Zeiten können nach erstellen geloschen oder editiert werden.
Es ist gedacht das man dies Privat oder als Firma brauchen kann, deswegen kann man
angeben wo man gearbeitet hat und welche Art von Arbeiten man dann gemacht hat.

Die Applikation kann gestartet werden mit ./mvnw compile quarkus:dev im root folder.

Man kann sich unter http://localhost:8080/adminLogin.html mit "Username: Admin, Passwort: 123"
als Admin anmelden.

#SQL Importe
Es werden 3 Kategorien, 3 Orte und 2 Benutzer hinzugefügt.
