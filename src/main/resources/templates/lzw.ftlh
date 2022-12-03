<html>

<head>
    <!-- Compiled and minified CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
    <!-- Compiled and minified JavaScript -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <title>
        LZW + Б/У - главная
    </title>

</head>

<body>

<nav>
    <div class="nav-wrapper">
        <a href="#" class="brand-logo">&nbsp;&nbsp;&nbsp;Алгоритм LZW + Б/У - главная</a>
        <ul id="nav-mobile" class="right hide-on-med-and-down">
            <li><a href="/">На главную</a></li>
            <li><a href="https://vk.com/httprequest">Заглянуть к автору</a></li>
        </ul>
    </div>
</nav>

<div class="container">
<#if nomap == "true">
    <h2>Не загружен алфавит.</h2>
<#else>
    <h2>Алфавит:</h2>
    <#list map as k>
        ${k},&nbsp;
    </#list>
</#if>

<a class="waves-effect waves-light btn" href="/lzw/cp">
    <i class="material-icons right">cloud</i>Загрузить алфавит
</a>

<hr>

<#if nomap == "false">
    <h3>Закодировать строку:</h3>
    <form action="/lzw/encode" method="post">

        <div class="input-field col s6">
            <input id="first_name" type="text" name="text">
            <label for="first_name">Введите текст для кодирования</label>
        </div>
        <input class="waves-effect waves-light btn" type="submit" value="Закодировать!">

    </form>
    <h3>Раскодировать строку:</h3>
    <form action="/lzw/decode" method="post">

        <div class="input-field col s6">
            <input id="first_name3" type="text" name="text">
            <label for="first_name3">Введите текст для декодирования</label>
        </div>
        <div class="input-field col s6">
            <input id="first_name2" type="number" name="pos">
            <label for="first_name2">Введите позицию</label>
        </div>
        <input class="waves-effect waves-light btn" type="submit" value="Раскодировать!">

    </form>
<#else>
    <h3>Невозможно выполнять преобразования без алфавита.</h3>
</#if>
</div>
</body>

</html>