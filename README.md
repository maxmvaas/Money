# Money

Приложение для конвертирования различных валют в рубли. Курс валют почти на каждый день берётся из API центрального банка России. (Некоторые даты по каким-то причинам не содержат курс валют, выходные дни точно).
При скачивании, данные кэшируются в локальную базу данных и при следующем обновлении экрана или перезаходите в приложение, будут браться из локальной базы данных.
К тому же, в приложении были добавлены Unit-тесты на часть функционала. 
Использованные технологии: JUnit, Google Navigation Component, Retrofit, Koin, Room, Coroutines. Для разработки использован подход Single Activity. 

<details>
<summary><b>Скриншоты</b></summary>
    	<br>1. Главный экран приложения:
        ![Imgur](https://i.imgur.com/vibo7mk.jpg)
        <br>2. Экран ошибки:
        ![Imgur](https://i.imgur.com/SaILIRS.jpg)
        <br>3. Выбор даты, на которую необходимо получить списиок курсов валют:
        ![Imgur](https://i.imgur.com/dmx7Vow.jpg)
        <br>4. Экран конвертирования валют:
        ![alt text](http://i.imgur.com/IfuLLgn.jpg)
        ![Imgur](https://i.imgur.com/bXH7wWM.jpg)
        ![Imgur](https://i.imgur.com/k6HB13g.jpg)
        ![Imgur](https://i.imgur.com/b4tO4dD.jpg)
    <img src="[relative/path/in/repository/to/image.svg](https://i.imgur.com/b4tO4dD.jpg)" width="128"/>
</details>
