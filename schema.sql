DROP TABLE gracze;

CREATE TABLE gracze (
id INT PRIMARY KEY,
imie VARCHAR(30),
nazwisko VARCHAR(30)
);

DROP TABLE druzyny;

CREATE TABLE druzyny (
id INT PRIMARY KEY,
nazwa VARCHAR(30),
kraj VARCHAR(30),
liga VARCHAR(30)
);

DROP TABLE mecze;

CREATE TABLE mecze (
id INT PRIMARY KEY,
gospodarz_id INT,
gospodarz_bramki INT,
gospodarz_druzyna_id INT,
gosc_id INT,
gosc_bramki INT,   
gosc_druzyna_id INT,
czas_rozegrania DATETIME DEFAULT CURRENT_TIMESTAMP,

FOREIGN KEY (gospodarz_id) REFERENCES gracze(id),
FOREIGN KEY (gosc_id) REFERENCES gracze(id),
FOREIGN KEY (gospodarz_druzyna_id) REFERENCES druzyny(id),
FOREIGN KEY (gosc_druzyna_id) REFERENCES druzyny(id)

);



