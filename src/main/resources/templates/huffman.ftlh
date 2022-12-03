<html>

<head>
    <!-- Compiled and minified CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
    <!-- Compiled and minified JavaScript -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <title>
        Хаффман - главная
    </title>

</head>

<body>

<nav>
    <div class="nav-wrapper">
        <a href="#" class="brand-logo">&nbsp;&nbsp;&nbsp;Алгоритм Хаффмана - главная</a>
        <ul id="nav-mobile" class="right hide-on-med-and-down">
            <li><a href="/">На главную</a></li>
            <li><a href="https://vk.com/httprequest">Заглянуть к автору</a></li>
        </ul>
    </div>
</nav>

<div class="container">
<#if nomap == "true">
    <h2>Не загружена таблица кодировки.</h2>
<#else>
    <h2>Таблица кодировки:</h2>
    <#list map as k, v>
        ${k} -> ${v} <br/>
    </#list>
</#if>

<a class="waves-effect waves-light btn" href="/huffman/cp">
    <i class="material-icons right">cloud</i>Загрузить таблицу кодировки
</a>

    <a class="waves-effect waves-light btn" href="/huffman/code">
        <i class="material-icons right">cloud</i>Загрузить уже созданный код
    </a>

<hr>

<#if nomap == "false">
    <h3>Закодировать строку:</h3>
    <form action="/huffman/encode" method="post">

        <div class="input-field col s6">
            <input id="first_name" type="text" name="text">
            <label for="first_name">Введите текст для кодирования</label>
        </div>
        <input class="waves-effect waves-light btn" type="submit" value="Закодировать!">

    </form>
    <h3>Раскодировать строку:</h3>
    <form action="/huffman/decode" method="post">

        <div class="input-field col s6">
            <input id="first_name" type="text" name="text">
            <label for="first_name">Введите текст для декодирования</label>
        </div>
        <input class="waves-effect waves-light btn" type="submit" value="Раскодировать!">

    </form>
<#else>
    <h3>Невозможно выполнять преобразования без таблицы кодировки.</h3>
</#if>
</div>
</body>

</html>